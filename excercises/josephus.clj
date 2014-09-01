(ns excercises.josephus
  (:require [clojure.set]))

;;; Josephus problem for k=2 and n=1500

(def start-from 1)
(def step 2)

(defn next-start [start people]
  (-> people count (+ start) (mod step)))

(defn kill [start people]
  (let [killed (take-nth step (drop start people))]
    (sort (clojure.set/difference (set people) (set killed)))))

(defn last-man-standing [n]
  (loop [s start-from p (map inc (range n))]
    (if (== 1 (count p)) (first p) (recur (next-start s p) (kill s p)))))

(println (last-man-standing 1500) " is last man standing")
