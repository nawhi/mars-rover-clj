(ns mars-rover.core
  (:require [clojure.string :as str]))

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

(defn- move-type [face]
  (case face
    :N [inc :y]
    :E [inc :x]
    :S [dec :y]
    :W [dec :x]))

(defn- forward [{face :face :as old-pos} grid]
  (let [[inc-or-dec direction] (move-type face)]
    (update old-pos direction #(mod (inc-or-dec %) (direction grid)))))

(defn- next-position [position move grid]
  (case move
    \M (forward position grid)
    \L (update position :face rotate-left)
    \R (update position :face rotate-right)))

(defn- coords [{:keys [x y]}] {:x x :y y})

(defn- finished [{:keys [x y face]}]
  (str/join ":" (list x y (name face))))

(defn- obstructed [position]
  (str "O:" (finished position)))

(defn rover-position
  "Given a grid and a list of moves, calculate the rover's final position"
  [grid moves]
  (loop [position {:x 0 :y 0 :face :N} moves moves]
    (if (empty? moves)
      (finished position)
      (let [move (first moves)
            new-pos (next-position position move grid)]
        (if (some #{(coords new-pos)} (:obstacles grid))
          (obstructed position)
          (recur new-pos (subs moves 1)))))))
