(ns blog.pages.index)

(defn page
  [context]
  [:div {:class "container"}
   [:div {:class "row"}
    [:img {:src "photos/me.jpg" :class "image-of-me three columns"}]
    [:div {:class "nine columns"}
    [:p
     "Welcome! My name is Matthew Fors."]
    [:p
     "I'm a software engineer currently located in NYC. I moved to New York in May of 2019 with my then girlfriend and dog from Ottawa, Canada. While in Ottawa, I attended Carleton University from 2014 through to 2019 to complete a Bachelors degree in computer science, with a minor in philosophy."]
    [:p
     "In my spare time, you may find me exploring new vegan restaurants, watching reality TV, or hanging out at a punk show. My philosophical interests include consequentialist ethics and philosophy of mind."]
    [:p
     "This site is a project of mine that is meant to contain my thoughts on various topics, including; ethics, software engineering, functional programming, philosophy, and more. It is also meant to serve as a reference point for the "
     [:a {:href "/contact.html"} "rest of my online presence"]
     "."]
    [:p "The site is written in "
     [:a {:href "https://clojure.org/"} "Clojure"]
     " , with "
     [:a {:href "https://github.com/weavejester/hiccup"} "Hiccup"]
     " syntax, and exports assets with "
     [:a {:href "https://github.com/magnars/stasis"} "Stasis"]
     "."]]]])
