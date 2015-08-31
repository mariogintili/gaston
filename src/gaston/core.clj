(ns gaston.core
  (:require [ring.adapter.jetty :as jetty])
  (:require [environ.core :refer [env]])
  (:use ring.middleware.params)
  (:require [taoensso.carmine :as car :refer (wcar)]))

(def redis-connection {:pool {} :spec {:uri (env :redis-url)}})
(defmacro wcar* [& body] `(car/wcar redis-connection ~@body))

(defn bootstrap-index [{query-params :query-params}]
  "Look for an index key, replace with 'current' if not found"
  (get query-params "index_key" (wcar* (car/get "codecards:current"))))


(defn request-handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (wcar* (car/get (bootstrap-index request)))})

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (wrap-params request-handler) {:port port :join? false})))
