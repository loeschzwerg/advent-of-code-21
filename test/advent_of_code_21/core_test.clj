(ns advent-of-code-21.core-test
  (:require [clojure.test :refer :all]
            [advent-of-code-21.day-1 :refer :all]
            [advent-of-code-21.day-2 :refer :all]
            [advent-of-code-21.day-3 :refer :all]
            [advent-of-code-21.day-4 :refer :all]))

(deftest d1-test
  (testing "First day"
    (is (= 1184 (d1-one)))
    (is (= 1158 (d1-two)))))

(deftest d2-test
  (is (= 1990000 (d2-one)))
  (is (= 900 (with-bindings {#'d2-data [[:forward 5] [:down 5] [:forward 8] [:up 3] [:down 8] [:forward 2]]}
               (d2-two))))
  (is (= 1975421260 (d2-two))))

(deftest d3-test
  (let [example     ["00100" "11110" "10110" "10111" "10101" "01111" "00111" "11100" "10000" "11001" "00010" "01010"]
        gamma       2r10110 ;; 22
        epsilon     2r01001 ;; 9
        consumption (* gamma epsilon)
        oxygen      23
        co2         10]
    (is (= consumption (d3-one example)))
    (is (= 1025636 (d3-one d3-data)))
    (is (= oxygen (keep-common example max-key)))
    (is (= (* oxygen co2) (d3-two example)))
    (is (< 793616 (d3-two d3-data)))))

(deftest d4-test
  (let [example {:draws [7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1]
                 :boards ['([14 21 17 24  4]
                            [10 16 15  9 19]
                            [18  8 23 26 20]
                            [22 11 13  6  5]
                            [ 2  0 12  3  7])
                          '([ 3 15  0  2 22]
                            [ 9 18 13 17  5]
                            [19  8  7 25 23]
                            [20 11 10 24  4]
                            [14 21 16 12  6])]}]
    (testing "Example input - day 4 part one" 
      (is (= 4512 (d4-one example))))
    (testing "Actual Input - day 4 part one"
      (is (< 8424 (d4-one d4-data)))
      (is (> 30240 (d4-one d4-data)))
      (is (= 8442 (d4-one d4-data))))
    
    (testing "Example input - day 4 part two"
      (is (= 1924 (d4-two example))))
    (testing "Actual input - day 4 part two"
      (is (= 4590 (d4-two d4-data))))))