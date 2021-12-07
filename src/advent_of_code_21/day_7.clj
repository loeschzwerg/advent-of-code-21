(ns advent-of-code-21.day-7
  (:require [advent-of-code-21.utils :as u]))

(def data (u/slurp-input "resources/day_7.txt"))

(defn one [data]
  (let [freqs (frequencies data)
        _     (prn 'freqs freqs)
        min'  (apply min (keys freqs))
        max'  (apply max (keys freqs))]
    (prn min' max')
    (->> (for [x (range min' (inc max'))]
           [(u/sum (mapv #(* (second %)
                            (- (max x (first %))
                               (min x (first %))))
                     freqs))
            x])
        (apply min-key first)
        second)))
