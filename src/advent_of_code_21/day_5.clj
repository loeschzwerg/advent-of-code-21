(ns advent-of-code-21.day-5
  (:require
   [advent-of-code-21.utils :as u]))

(def data (u/slurp-input "resources/day_5.txt"))

(defn- grid-size [data]
  {:x (reduce #(max %1 (first %2) (nth %2 3)) 0 data)
   :y (reduce #(max %1 (second %2) (last %2)) 0 data)})
  
(defn- grid [{:keys [x y]}]
  (into []
    (for [_ (range (inc y))]
      (into [] (repeatedly (inc x) (fn [] 0))))))

(defn- coords [[x1 y1 _ x2 y2]]
  (for [x (range (min x1 x2) (inc (max x1 x2)))
        y (range (min y1 y2) (inc (max y1 y2)))
        :when (or (= x1 x2) (= y1 y2))]
    [x y]))

(defn- insert-coords [grid' movements]
  (reduce (fn [grid* [x y]]
            (prn 'update x y 'at (get (get grid* y) x))
            (update grid* y update x inc))
          grid'
          movements))

(defn- insert-vents [grid' data]
  (reduce insert-coords grid' (mapv coords data)))

(defn- junctions [vents]
  (reduce (fn [agg x] (+ agg (count (filter #(< 1 %) x))))
          0
          vents))

(defn one [data]
  (let [grid' (grid (grid-size data))
        vents (insert-vents grid' data)]
    (junctions vents)))

(comment
  (prn 'RESULT1 (one data))
)
  
(defn- coords-diag [[x1 y1 _ x2 y2]]
  (if (or (= x1 x2) (= y1 y2))
    (coords [x1 y1 '-> x2 y2])
    (loop [x x1
           y y1
           agg []]
      (if (if (< x1 x2) (= x (inc x2)) (= x (dec x2)))
       agg 
       (recur
        ((if (< x1 x2) inc dec) x)
        ((if (< y1 y2) inc dec) y)
        (conj agg [x y]))))))

(defn insert-vents-diag [grid' data]
  (reduce insert-coords grid' (mapv coords-diag data)))

(defn two [data]
  (let [grid' (grid (grid-size data))
        vents (insert-vents-diag grid' data)]
    (junctions vents)))

(comment
  (prn 'RESULT2 (two data))
)