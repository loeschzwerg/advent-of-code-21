(ns advent-of-code-21.core-test
  (:require [clojure.test :refer :all]
            [advent-of-code-21.day-1 :refer :all]
            [advent-of-code-21.day-2 :refer :all]))

(deftest d1-test
  (testing "First day"
    (is (= 1184 (d1-one)))
    (is (= 1158 (d1-two)))))

(deftest d2-test
  (is (= 1990000 (d2-one)))
  (is (= 900 (with-bindings {#'d2-data [[:forward 5] [:down 5] [:forward 8] [:up 3] [:down 8] [:forward 2]]}
               (d2-two))))
  (is (= 1975421260 (d2-two))))