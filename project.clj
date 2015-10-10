(defproject gaston "0.1.0"
  :description "A clojure implementation for the 'Lighting approach' employed in Ember-CLI"
  :url "https://gaston-codecards.herokuapp.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring "1.4.0"]
                 [com.taoensso/carmine "2.11.1"]
                 [ring/ring-mock "0.3.0"]
                 [environ "1.0.0"]]
  :main ^:skip-aot gaston.core
  :target-path "target/%s"
  :min-lein-version "2.5.1"
  :plugins [[environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :profiles {:production {:env {:production true}} :uberjar {:aot :all}})
