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

;; day 4
(defn slurp-lines [file-name]
  (->> file-name
       slurp
       str/split-lines))

(defn read-vec
  "a,b,... -> [a b ...]"
  [string]()
  (edn/read-string (format "[%s]" string)))

(defn transpose [matrix]
  (apply mapv vector matrix))

;; general slurp
(defn parse-tuple [a & tokens]
  #_(prn '--> a tokens)
  (let [first' (edn/read-string a)
        toks   (if (= 1 (count tokens))
                 (edn/read-string (first tokens))
                 (mapv edn/read-string tokens))]
    (cond
      (not tokens)
      first'
      (symbol? first')
      {first' toks}
      :else
      (cons first' toks))))

(defn- parse-line [line]
  #_('-> line)
  (apply parse-tuple line))

(defn- parse-part [part]
  #_(prn '> part)
  (->> part
       (mapv #(str/trim %))
       (mapv #(str/split % #"( +|,)"))
       (mapv parse-line)))

(defn slurp-input [file-name]
  (let [file    (slurp file-name)
        lines   (str/split-lines file)
        parts   (partition-by empty? lines) ;; empty lines mark a break
        cleaned (remove (comp empty? first) parts)] ;; remove empty parts
    (cond
      (= 1 (count lines))
      (first (parse-part lines))
      
      (= 1 (count cleaned)) 
      (parse-part (first cleaned))
      
      :else 
      (mapv parse-part cleaned))))

(comment
  (slurp-input "resources/day_1.txt")
  (slurp-input "resources/day_2.txt")
  (slurp-input "resources/day_3.txt")
  (clojure.pprint/pprint (slurp-input "resources/day_4.txt"))
  )