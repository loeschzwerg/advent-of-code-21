(ns advent-of-code-21.day-4
  (:require
   [advent-of-code-21.utils :as u]))

(def d4-raw 
  (u/slurp-input "resources/day_4.txt"))

(def d4-data
  {:draws  (ffirst d4-raw)
   :boards (rest d4-raw)})

(defn- draw [data idx]
  (nth (:draws data) (dec idx)))

(defn- calc-rest-sum [drawn board]
  (let [cleaned (mapv #(remove drawn %) board)]
    (doto
      (u/sum (reduce into cleaned))
      (prn 'BINGO board))))

(defn- bingo? [drawn board]
  (seq (for [row (into board (u/transpose board))
             :when (every? drawn row)]
         (calc-rest-sum drawn board))))

(defn d4-one [data]
  (loop [idx 5]
    (let [drawn  (->> data :draws (take idx) set)
          bingos (keep (partial bingo? drawn) (:boards data))]
      (prn 'DRAW idx '> (draw data idx) bingos)
      (if (seq (first bingos))
        (* (draw data idx) (u/sum (first bingos)))
        (recur (inc idx))))))

(comment
  (prn 'WINNER (d4-one d4-data))
)

(defn d4-two [data]
  (loop [idx    5
         boards (:boards data)]
    (let [drawn   (->> data :draws (take idx) set)
          pending (remove (partial bingo? drawn) boards)]
      (prn 'DRAW idx '> (draw data idx) 'pending (count pending))
      (if (= 0 (count pending))
        (* (calc-rest-sum drawn (first boards)) (draw data idx))
        (recur (inc idx) pending)))))

(comment 
  (prn 'LAST (d4-two d4-data))
)