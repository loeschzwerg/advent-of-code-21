(ns advent-of-code-21.day-2
  (:require
   [clojure.string :as str]
   [advent-of-code-21.utils :as u]))

(def ^:dynamic d2-data (u/slurp-kv "resources/day_2.txt"))

(defn- move-submarine
  "Reduces kv onto m1"
  [m1 kv]
  (prn m1 kv)
  (case (first kv)
    :forward (update m1 :forward (partial + (second kv)))
    :up      (update m1 :depth (fn [a] (- a (second kv))))
    :down    (update m1 :depth (partial + (second kv)))))

(defn d2-one []
  (doto (->> d2-data
             (reduce move-submarine {:forward 0 :depth 0})
             vals
             (reduce *))
    (prn 'planned 'course)))

(defn- move-submarine-aimed [m1 [k v]]
  (prn m1 k v)
  (case k
    :forward (-> m1
               (update :forward (partial + v))
               (update :depth (partial + (* v (:aim m1)))))
    :up      (update m1 :aim (fn [a] (- a v)))
    :down    (update m1 :aim (partial + v))))

(defn d2-two []
  (let [last-position (reduce move-submarine-aimed {:forward 0 :depth 0 :aim 0} d2-data)
        result        (reduce * (vals (select-keys last-position [:forward :depth])))]
    (prn result 'planned 'course 'with 'aim)
    result))