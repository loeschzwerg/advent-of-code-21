(ns advent-of-code-21.core-test
  (:require [clojure.test :refer :all]
            [advent-of-code-21.day-1 :refer :all]
            [advent-of-code-21.day-2 :refer :all]
            [advent-of-code-21.day-3 :refer :all]))

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
