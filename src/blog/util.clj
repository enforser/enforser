(ns blog.util)

(defmacro text
  "stringifies symbols passed, helps write text inline w/ hiccup"
  [& words]
  (apply str (interpose " " words)))
