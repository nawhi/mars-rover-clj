(ns mars-rover-clj.core
  (:require [clojure.string :as str]))

(defn grid-to-string [grid] (str/join ":" (list (:x grid) (:y grid) (name (:face grid)))))

(defn rotate-left [old-face]
  (case old-face
    :N :W
    :W :S
    :S :E
    :E :N))

(defn next-position [position move grid]
  (case move
    \M {:x (mod (inc (:x position)) (:height grid)) :y 0 :face "N"}
    \L {:x (:x position) :y (:y position) :face (rotate-left (:face position))}
    ))

(defn rover-position
  "Given a grid and a list of moves, calculate the rover's final position"
  [grid moves]
  (loop [position {:x 0 :y 0 :face :N} moves moves]
    (if (empty? moves)
      (grid-to-string position)
      (let [move (first moves)
            new-pos (next-position position move grid)]
        (recur new-pos (subs moves 1))))))
