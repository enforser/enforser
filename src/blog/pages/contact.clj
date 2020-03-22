(ns blog.pages.contact)

(defn page
  [context]
  [:div {:class "container"}
   [:div {:class "row"}
    [:pre
     [:code
     "
#
# How to get in touch:
#
     "
     [:li [:a {:href "mailto:trailcapital@gmail.com"} "E-mail"]]
     [:li [:a {:href "https://www.linkedin.com/in/matthew-fors-8b655699/"} "LinkedIn"]]
     [:li [:a {:href "https://github.com/enforser"} "Github"]]
     ]]]])
