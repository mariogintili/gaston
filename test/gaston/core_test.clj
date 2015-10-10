(ns gaston.core-test
  (:require [clojure.test :refer :all]
            [gaston.core :refer :all]
            [ring.mock.request :as mock]
            [taoensso.carmine :as car :refer (wcar)]))

(defn setup-env []
  (wcar* (car/set "ember-app-name:current" "ember-app-name:default-revision"))
  (wcar* (car/set "ember-app-name:default-revision" "default-revision-value"))
  (wcar* (car/set "ember-app-name:old-revision" "old-revision")))

(defn teardown-env []
  (wcar* (car/flushall)))

(defn gaston-test-setup [f]
  (setup-env)
  (f)
  (teardown-env))

(use-fixtures :once gaston-test-setup)

(deftest request-without-an-index-key
  (is (= {:status 200
          :headers {"Content-Type" "text/html"}
          :body "default-revision-value"}
          (request-handler (mock/request :get "/")))))
