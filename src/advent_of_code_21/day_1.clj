(ns advent-of-code-21.day-1
  (:require [advent-of-code-21.utils :as u])
  (:gen-class))

(def ^:dynamic counter 0)
(def d1-data (u/slurp-edn "resources/day_1.txt"))

(defn- reducer-fn [last next]
   (if (< last next)
     (prn next 'increased '-> (swap! counter inc))
     (prn last 'decreased))
  next)

(defn d1-one []
  (with-bindings {#'counter (atom 0)}
    (reduce reducer-fn d1-data)
    (doto @counter
      (prn 'Larger 'measurements))))

(defn- sliding-reducer-fn [last next-val]
  (let [next' (conj (take 2 last) next-val)
        sum'  (- (u/sum next') (u/sum last))]
    (cond
      (< (count last) 3) (prn 'building next')
      (pos? sum')        (prn (u/sum next') 'increased '-> (swap! counter inc))
      (zero? sum')       (prn (u/sum next') 'no 'change)
      (neg? sum')        (prn (u/sum next') 'decreased))
    next'))

(defn d1-two []
  (with-bindings {#'counter (atom 0)}
    (reduce sliding-reducer-fn nil d1-data)
    (doto @counter
      (prn 'Larger 'sliding 'measurements))))
