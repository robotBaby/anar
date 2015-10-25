(ns anar.riemann-appender
  "Appends timbre logs to Reimann"
  (:require 
       [taoensso.timbre :as timbre
           :refer (log  trace  debug  info  warn  error  fatal  report
              logf tracef debugf infof warnf errorf fatalf reportf
              spy get-env log-env)]
      [riemann.client :as r]
      [clojure.string  :as str]))


(def defaults
  {:host "localhost"
   :port 5555})

(defn riemann-conn-and-log 
  [conn service state log-message]  
  (-> conn (r/send-event {:service  service :state state :description log-message})  
      (deref 5000 ::timeout) ))


(defn riemann-appender

  [& {:keys [host port]
      :or {host (:host defaults)
           port (:port defaults)}}]

  {:enabled?   true
   :async?     false
   :min-level  nil
   :rate-limit nil
   :output-fn  :inherit
   :fn (let [conn (r/tcp-client {:host host :port port})]
         (fn [data]
           (let [{:keys [?ns-str level output-fn]} data
                 formatted-output-str (output-fn data)]
             (riemann-conn-and-log conn (str "[" (or ?ns-str "?ns") "]" ) (str/upper-case (name level)) formatted-output-str )
             )))})
