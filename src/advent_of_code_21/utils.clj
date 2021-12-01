(ns advent-of-code-21.utils
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn slurp-edn [file]
  (->> file
      slurp
      str/split-lines
      (mapv edn/read-string)))


(defn sum [coll]
  (reduce + coll))
