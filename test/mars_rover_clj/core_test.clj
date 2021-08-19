(ns mars-rover-clj.core-test
  (:require [clojure.test :refer :all]
            [mars-rover-clj.core :refer :all]))

(def GRID-NO-OBSTACLES { :width 10 :height 10 :obstacles ()})

(deftest a-test
  (testing "Rover starts on origin facing north"
    (is (= "0:0:N" (rover-position GRID-NO-OBSTACLES ""))))
  (testing "M moves rover forward one square"
    (is (= "1:0:N" (rover-position GRID-NO-OBSTACLES "M")))
    (is (= "2:0:N" (rover-position GRID-NO-OBSTACLES "MM")))
    (is (= "7:0:N" (rover-position GRID-NO-OBSTACLES "MMMMMMM"))))
  (testing "L rotates rover anticlockwise"
    (is (= "0:0:W" (rover-position GRID-NO-OBSTACLES "L"))))
  (testing "Rover wraps round at the end of the grid"
    (is (= "1:0:N" (rover-position GRID-NO-OBSTACLES "MMMMMMMMMMM")))))


; wraps around
; stops at an obstacle
