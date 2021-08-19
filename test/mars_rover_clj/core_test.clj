(ns mars-rover-clj.core-test
  (:require [clojure.test :refer :all]
            [mars-rover-clj.core :refer :all]))

(def GRID-NO-OBSTACLES { :width 10 :height 10 :obstacles ()})

(deftest a-test
  (testing "Rover starts on origin facing north"
    (is (= "0:0:N" (rover-position GRID-NO-OBSTACLES ""))))
  (testing "M moves rover forward one square"
    (is (= "1:0:N" (rover-position GRID-NO-OBSTACLES "M")))))


; wraps around
; stops at an obstacle
