(ns gaston.core
  (:require [ring.adapter.jetty :as jetty])
  (:use ring.middleware.params))

(defn bootstrap-index [{query-params :query-params}]
  "Look for an index key, replace with 'current' if not found"
  (pr query-params)
  (def redis_key (get query-params "index_key" "current"))
  (pr redis_key)
  (str "<codecards>:" (str redis_key)))

(defn request-handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (bootstrap-index request)})


(defn -main []
  "I don't do a whole lot ... yet."
  (jetty/run-jetty (wrap-params request-handler) {:port 3000}))
