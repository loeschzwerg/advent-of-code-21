(ns advent-of-code-21.utils
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))
;; day 1
(defn slurp-edn [file]
  (->> file
      slurp
      str/split-lines
      (mapv edn/read-string)))


(defn sum [coll]
  (reduce + coll))

;; day 2
(defn slurp-kv [file]
  (->> file
       slurp
       str/split-lines
       (mapv #(str/split % #" "))
       (mapv (fn [[k v]] [(keyword k) (edn/read-string v)]))))
