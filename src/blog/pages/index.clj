(ns blog.pages.index)

(defn page
  [context]
  [:div {:class "container"}
   [:div {:class "row"}
    [:img {:src "photos/mountain-matt.png" :class "image-of-me five columns"}]
    [:div {:class "seven columns"}
    [:p
     "Welcome! My name is Matthew Fors."]
    [:p
     "I'm currently located in Canada. In the past few years I've lived in New York, Vancouver, Montreal, and spent a few months in India/Nepal. I've been working as a software engineer at Gemini building cryptocurrency wallets since 2020."]
    [:p
     "In my spare time, you may find me cooking up new vegan recipes, shredding a ski hill, or on a dancefloor seeking out the best sound system."]
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
