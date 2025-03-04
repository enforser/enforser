(ns blog.core
  (:require
    [blog.pages.index :as index]
    [blog.pages.contact :as contact]
    [blog.pages.blog :as blog]
    [blog.pages.posts.learning-functional-first :as fp-first]
    [blog.pages.posts.hyperdrive :as hyperdrive]
    [blog.pages.posts.decrypt-zcash :as decrypt-zcash]
    [blog.pages.posts.zcash-ovk :as zcash-ovk]
    [clojure.pprint :as pprint]
    [clojure.string :as st]
    [ring.middleware.content-type :refer [wrap-content-type]]
    [stasis.core :as stasis]
    [hiccup.core :refer [html]]
    [me.raynes.fs :as fs]
    [optimus.prime :as optimus]
    [optimus.assets :as assets]
    [optimus.optimizations :as optimizations]
    [optimus.strategies :refer [serve-live-assets]]
    [optimus.export]))

(def target-dir "docs")

(defn get-assets
  []
  (assets/load-assets "public" [#"/styles/.*\.css"
                                #"/photos/.*\.jpg"
                                #"/photos/.*\.png"
                                #"/photos/.*\.ico"]))
(defn header-item
  [page selected]
  [:h5 {:class (str "two columns nav-item" (when (= page selected) " selected-nav"))
        :style "width: 100/12%;"
        :onClick (str "location.href='" (.toLowerCase page) ".html'")}
   page])

(defn header
  [selected]
  [:div {:class "container"}
     [:div {:class "row nav" :style "padding: 20px 0 30px 0"}
      (header-item "About" selected)
      (header-item "Blog" selected)
      (header-item "Contact" selected)
      [:h5
       {:class "six columns right-text bold nav-item"
        :onClick (str "location.href='index.html'")}
       "(enforser)"]]])

(defn hiccup-with-header
  [title link-to-hiccup & body]
  [:html
   [:head
    [:title (str title " - Matthew Fors")]
    [:link {:rel "shortcut icon" :type "image/x-icon" :href "photos/favicon.ico"}]
    [:link {:rel "stylesheet" :href "styles/normalize.css"}]
    [:link {:rel "stylesheet" :href "styles/skeleton.css"}]
    [:link {:rel "stylesheet" :href "styles/style.css"}]]
   (conj [:body (header title)]
         body
         [:div {:class "container"}
          [:a {:class "twelve columns"
               :style "text-align: right"
               :href (str "/" (.toLowerCase title) (if link-to-hiccup "-as-code.html" ".html"))}
           (if link-to-hiccup "Hiccup" "Back")]])])

(defn to-html
  [& [as-hiccup?]]
  (fn [title & body]
    (if as-hiccup?
      (html (hiccup-with-header
              title
              false
              [:div {:class "container"}
               [:pre
                [:code
                 (with-out-str (pprint/pprint (hiccup-with-header title true body)))]]]))
      (html (hiccup-with-header title true body)))))

(defn get-pages
  ([]
   (merge (get-pages true)
          (get-pages false)))
  ([as-hiccup]
   (reduce-kv
     (fn [m k v]
       (assoc m (str "/" k (when as-hiccup "-as-code") ".html") v))
     {}
     {"index" #((to-html as-hiccup) "About" (index/page %))
      "about" #((to-html as-hiccup) "About" (index/page %))
      "blog" #((to-html as-hiccup) "Blog" (blog/page %))
      "contact" #((to-html as-hiccup) "Contact" (contact/page %))
      "learning-functional-first" #((to-html as-hiccup) "learning-functional-first" (fp-first/content %))
      "decrypt-zcash" #((to-html as-hiccup) "decrypt-zcash" (decrypt-zcash/content %))
      "hyperdrive" #((to-html as-hiccup) "hyperdrive" (hyperdrive/content %))
      "zcash-outgoing-viewing-key" #((to-html as-hiccup) "zcash-outgoing-viewing-key" (zcash-ovk/content %))})))

(def app
  (-> (stasis/serve-pages (get-pages))
      (optimus/wrap get-assets optimizations/all serve-live-assets)
      wrap-content-type))

(defn export []
  (let [assets (optimizations/all (get-assets) {})
        pages (get-pages)]
    (stasis/empty-directory! target-dir)
    (fs/copy-dir "decrypt-zcash/decrypt-zcash-assets" "docs")
    (optimus.export/save-assets assets target-dir)
    (stasis/export-pages pages target-dir {:optimus-assets assets})))
