(ns gaston.core
  (:require [ring.adapter.jetty :as jetty])
  (:require [environ.core :refer [env]])
  (:use ring.middleware.params)
  (:require [taoensso.carmine :as car :refer (wcar)]))

(defn bootstrap-index [{query-params :query-params}]
  "Look for an index key, replace with 'current' if not found"
  (def redis_key (get query-params "index_key" "current"))
  (str "<codecards>:" (str redis_key)))

(def redis-connection {:pool {} :spec {:host (or (env :redis-url) "localhost") :port (or (env :redis-port) 6379) }} )
(defmacro wcar* [& body] `(car/wcar redis-connection ~@body))

(defn request-handler [request]
  (def index_key (bootstrap-index request))
  (def server-connection {})
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (wcar* (car/get index_key))})


(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (wrap-params request-handler) {:port port :join? false})))