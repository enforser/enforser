(ns blog.pages.blog
  (:require [blog.pages.posts.zcash-ovk :as ovk]))

(defn page
  [context]
  [:div {:class "container"}
   [:div {:class "row"}
    [:pre
     [:code
      "
#
# Author: Matthew Fors
#
      "
      [:li [:a {:href "zcash-outgoing-viewing-key.html"} (str "[07-12-2020] " ovk/title)]]
      [:li [:a {:href "decrypt-zcash.html"} "[26-10-2020] Zcash: Decrypt Sapling Outputs"]]
      [:li [:a {:href "learning-functional-first.html"} "[24-02-2020] Functional First"]]
      "\n"
     ]]]])
