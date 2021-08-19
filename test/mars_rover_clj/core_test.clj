(ns mars-rover-clj.core-test
  (:require [clojure.test :refer :all]
            [mars-rover-clj.core :refer :all]))

(def GRID-NO-OBSTACLES { :width 10 :height 10 :obstacles ()})

(deftest a-test
  (testing "Mars Rover"
    (is (= (rover-position GRID-NO-OBSTACLES "") "0:0:N"))))


; wraps around
; stops at an obstacle
