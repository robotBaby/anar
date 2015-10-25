(defproject anar "0.1.0-SNAPSHOT"
  :description "Integration of Riemann monitoring with Timbre logs"
  :url "http://mphasis.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.taoensso/timbre "4.0.2"]
                 [riemann-clojure-client "0.3.2"]])

 :source-paths ["src"]

:repositories [["releases"
                  {:url "http://54.173.78.58/repository/internal"
                   :username :env/archiva_username
                   :password :env/archiva_password}]

                 ["snapshots"
                  {:url "http://54.173.78.58/repository/snapshots"
                   :username :env/archiva_username
                   :password :env/archiva_password}]]
