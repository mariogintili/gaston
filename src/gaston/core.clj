(ns gaston.core
  (:require [ring.adapter.jetty :as jetty]))

(defn request-handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello Clojure, Hello Ring!"})

(defn -main []
  "I don't do a whole lot ... yet."
  (jetty/run-jetty request-handler {:port 3000}))
