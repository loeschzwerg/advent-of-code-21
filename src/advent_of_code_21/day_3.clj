(ns advent-of-code-21.day-3
  (:require [clojure.string :as str]
            [clojure.set :as set]))


(def ^:dynamic d3-data (str/split-lines (slurp "resources/day_3.txt")))

(defn- delta [freqs f]
  (Integer/parseInt
    (->> freqs
         (map keys)
         (map #(apply f %))
         (map #(%1 %2) freqs)
         (apply str))
    2))

(defn d3-one [data]
  (let [freqs (->> data
                   (map (partial into []))
                   (apply mapv str)
                   (map frequencies)
                   (map set/map-invert))]
    (* (delta freqs max) (delta freqs min))))

(defn- common [col key-fn]
  (let [res (->> col
                 frequencies
                 set/map-invert)]
    (if (apply = (keys res))
      (if (= key-fn min-key) \0 \1)
      (->> res
          (apply key-fn first)
          second))))

(defn keep-common [rows key-fn]
  (loop [i 0
         r rows]
    (prn i (count r))
    (if (>= 1 (count r))
      (Integer/parseInt (first r) 2)
      (let [col (mapv #(nth % i) r)
            com (common col key-fn)]
        (prn 'com com 'col col)
        (recur
          (inc i)
          (filterv #(= (nth % i) com) r))))))


(defn d3-two [data]
  (prn (count (first data)))
  (* (keep-common data max-key) (keep-common data min-key)))
