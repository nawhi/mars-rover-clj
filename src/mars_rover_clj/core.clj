(ns mars-rover-clj.core
  (:require [clojure.string :as str]))

(defn grid-to-string [grid] (str/join ":" (list (:x grid) (:y grid) (:face grid))))

(defn rover-position
  "Given a grid and a list of moves, calculate the rover's final position"
  [grid moves]
  (loop [position {:x 0 :y 0 :face "N"} moves moves]
    (if (empty? moves)
      (grid-to-string position)
      (recur { :x 1 :y 0 :face "N"} (subs moves 1)))))
