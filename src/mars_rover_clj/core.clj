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

(defn dimension-of [face]
  (case face (:N :S) :x (:E :W) :y))

(defn update-dimension-of [face]
  (case face (:N :E) inc (:S :W) dec))

(defn next-position [position move grid]
  (case move
    \M (let [dimension (dimension-of (:face position))
             update-dimension (update-dimension-of (:face position))]
         (update position dimension #(mod (update-dimension %) (dimension grid))))
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
