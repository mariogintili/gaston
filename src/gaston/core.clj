(ns gaston.core
  (:require [ring.adapter.jetty :as jetty])
  (:require [environ.core :refer [env]])
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


(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (wrap-params request-handler) {:port port :join? false})))