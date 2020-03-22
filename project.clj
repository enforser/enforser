(defproject blog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.8.0"]
                 [optimus "0.20.2"]
                 [stasis "2.5.0"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler blog.core/app}
  :main ^:skip-aot blog.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
