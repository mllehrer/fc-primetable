(ns fc-primetable.core-test
  (:require [clojure.test :refer :all]
            [fc-primetable.core :refer :all]))


(def first-ten-primes [2 3 5 7 11 13 17 19 23 29])

(deftest find-ten-primes-test
  (testing "It generates 10 primes"
    (is (= first-ten-primes (find-n-primes 10)))))

(deftest find-n-primes-test
  (testing "It generates n primes up to large numbers"
    (let [n (rand-int 10001)]
      (is (= true (check-prime (last (find-n-primes n))))))))

(deftest print-table-test
  (testing "It prints a table (used to check for errors, stdout shows output)"
    (is (= nil (print-table (find-n-primes 10))))))

(deftest print-table-random-test
  (testing "It prints a table for any n (used to check for errors, stdout shows output)"
    (is (= nil (print-table (find-n-primes (rand-int 10)))))))
