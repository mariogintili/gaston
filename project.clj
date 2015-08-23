(defproject gaston "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://gaston-codecards.herokuapp.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring "1.4.0"]
                 [com.taoensso/carmine "2.11.1"]
                 [environ "1.0.0"]]
  :main ^:skip-aot gaston.core
  :target-path "target/%s"
  :plugins [[environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :profiles {:production {:env {:production true}} :uberjar {:aot :all}})
