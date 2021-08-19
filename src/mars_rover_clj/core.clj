(ns mars-rover-clj.core
  (:require [clojure.string :as str]))

(defn grid-to-string [grid] (str/join ":" (list (:x grid) (:y grid) (name (:face grid)))))

(defn rotate-left [old-face]
  (case old-face
    :N :W
    :W :S
    :S :E
    :E :N))

(defn rotate-right [old-face]
  (case old-face
    :N :E
    :E :S
    :S :W
    :W :N))



(defn next-position [position move grid]
  (case move
    \M (let [dimension (case (:face position) (:N :S) :x (:E :W) :y)]
         (update position dimension #(mod (inc %) (:height grid))))
    \L (update position :face rotate-left)
    \R (update position :face rotate-right)))

(defn rover-position
  "Given a grid and a list of moves, calculate the rover's final position"
  [grid moves]
  (loop [position {:x 0 :y 0 :face :N} moves moves]
    (if (empty? moves)
      (grid-to-string position)
      (let [move (first moves)
            new-pos (next-position position move grid)]
        (recur new-pos (subs moves 1))))))
