(ns mars-rover-clj.core
  (:require [clojure.string :as str]))

(defn- grid-to-string [grid] (str/join ":" (list (:x grid) (:y grid) (name (:face grid)))))

(defn- rotate-left [old-face]
  (case old-face
    :N :W
    :W :S
    :S :E
    :E :N))

(defn- rotate-right [old-face]
  (case old-face
    :N :E
    :E :S
    :S :W
    :W :N))

(defn- dimension-of [face]
  (case face (:N :S) :y (:E :W) :x))

(defn- update-dimension-of [face]
  (case face (:N :E) inc (:S :W) dec))

(defn- move-forward [position move grid]
  (let [dimension (dimension-of (:face position))
        update-dimension (update-dimension-of (:face position))]
    (update position dimension #(mod (update-dimension %) (dimension grid)))))

(defn- next-position [position move grid]
  (case move
    \M (move-forward position move grid)
    \L (update position :face rotate-left)
    \R (update position :face rotate-right)))

(defn- coords [position] (select-keys position [:x :y]))

(defn rover-position
  "Given a grid and a list of moves, calculate the rover's final position"
  [grid moves]
  (loop [position {:x 0 :y 0 :face :N} moves moves]
    (if (empty? moves)
      (grid-to-string position)
      (let [move (first moves)
            new-pos (next-position position move grid)]
        (if (some #{(coords new-pos)} (:obstacles grid))
          (str "O:" (grid-to-string position))
          (recur new-pos (subs moves 1)))))))
