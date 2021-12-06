(ns advent-of-code-21.day-6
  (:require
   [advent-of-code-21.utils :as u]))

(def data (u/slurp-input "resources/day_6.txt"))

(defn one [data times]
  (loop [i 0
         agg (frequencies data)]
    (prn 'lanterns i agg)
    (if (= i times)
      (u/sum (vals agg))
      (recur (inc i)
             (reduce
              (fn [a b]
                (if (= (first b) 0)
                 (-> a
                     (update 6 #(+ (or % 0) (last b)))
                     (assoc 8 (last b)))
                 (update a (dec (first b)) #(+ (or % 0) (last b)))))
              {} agg)))))