(ns mars-rover-clj.core-test
  (:require [clojure.test :refer :all]
            [mars-rover-clj.core :refer :all]))

(def GRID-NO-OBSTACLES {:x 10 :y 10 :obstacles ()})

(deftest mars-rover-test
  (testing "Rover starts on origin facing north"
    (is (= "0:0:N" (rover-position GRID-NO-OBSTACLES ""))))

  (testing "M moves rover forward one square"
    (is (= "1:0:N" (rover-position GRID-NO-OBSTACLES "M")))
    (is (= "2:0:N" (rover-position GRID-NO-OBSTACLES "MM")))
    (is (= "7:0:N" (rover-position GRID-NO-OBSTACLES "MMMMMMM"))))

  (testing "L rotates rover anticlockwise"
    (is (= "0:0:W" (rover-position GRID-NO-OBSTACLES "L")))
    (is (= "0:0:S" (rover-position GRID-NO-OBSTACLES "LL")))
    (is (= "0:0:E" (rover-position GRID-NO-OBSTACLES "LLL")))
    (is (= "0:0:N" (rover-position GRID-NO-OBSTACLES "LLLL"))))

  (testing "R rotates rover clockwise"
    (is (= "0:0:E" (rover-position GRID-NO-OBSTACLES "R")))
    (is (= "0:0:S" (rover-position GRID-NO-OBSTACLES "RR")))
    (is (= "0:0:W" (rover-position GRID-NO-OBSTACLES "RRR")))
    (is (= "0:0:N" (rover-position GRID-NO-OBSTACLES "RRRR"))))

  (testing "Rover moves in the direction it is facing"
    (is (= "0:2:E" (rover-position GRID-NO-OBSTACLES "RMM")))
    (is (= "1:2:N" (rover-position GRID-NO-OBSTACLES "RMMLM")))
    (is (= "3:3:S" (rover-position GRID-NO-OBSTACLES "RMMLMRMLMMLL")))
    (is (= "0:0:S" (rover-position GRID-NO-OBSTACLES "MLLM")))
    (is (= "0:0:W" (rover-position GRID-NO-OBSTACLES "RMMLLMM"))))

  (testing "Rover wraps round at the end of the grid"
    (is (= "1:0:N" (rover-position GRID-NO-OBSTACLES "MMMMMMMMMMM")))
    (is (= "9:0:S" (rover-position GRID-NO-OBSTACLES "LLM")))
    (is (= "0:9:W" (rover-position GRID-NO-OBSTACLES "LM")))))


; wraps around
; stops at an obstacle
