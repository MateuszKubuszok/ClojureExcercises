(ns excercises.lazy-seq)

; integer/natural sequences
(defn integers-from "Integers from i up" [i]
  (cons i (lazy-seq (integers-from (inc i)))))
(defn naturals-0 "Natural numbers from 0" [] (integers-from 0N))
(defn naturals-1 "Natural numbers from 1" [] (integers-from 1N))

; Fibonacci numbers/sequence
(defn fibos
  "Lazy sequence of Fibonacci numbers"
  ([] (let [a 0N]
    (lazy-seq (fibos a))))
  ([a] (let [b 1]
    (cons a (cons b (lazy-seq (fibos a b))))))
  ([a b] (let [c (+ a b)]
    (cons c (lazy-seq (fibos b c))))))
(defn fibo
  "Nth Fibonacci number"
  ([n] (fibo n 0N))
  ([n z] (nth (fibos z) n)))

; Catalan numbers/sequence
(defn catalan
  "Nth Catalan number"
  [n]
  (if (zero? n) ; stop condition
    1N                                       ; c_0 = 0
    (reduce                                  ; recursive definition:
      +                                      ; sum
      (map
        #(* (catalan %) (catalan (- n 1 %))) ; c_i * c_(n-1-i)
        (take n (naturals-0))))))            ; where i in [0,n)
(def catalan (memoize catalan)) ; caching recursive results
(defn catalans
  "Lazy sequence of Catalan numbers"
  ([] (catalans 0N))
  ([n] (cons (catalan n) (lazy-seq (catalans (inc n))))))

; Bell numbers
(defn next-bell-row "Next row in Bell triangle" [n p]
  (loop [c [(nth p (dec n))] i 0] ; arrays are 0-indexed
    (if (= i n)
      c
      (recur
        (conj c (+ (nth p i) (nth c i)))
        (inc i)))))
(def next-bell-row (memoize next-bell-row))
(defn bell-row "Calculates nth row in Bell triangle" [n]
  (if (<= n 1)
    [1N]
    (let [pn (dec n)]
      (next-bell-row pn (bell-row pn)))))
(def bell-row (memoize bell-row))
(defn bell "Nth Bell number" [n] (first (bell-row n)))
(def bell (memoize bell))
(defn bells
  "Lazy sequence of Bell numbers"
  ([] (bells 1))
  ([n]
    (cons (bell n) (lazy-seq (bells (inc n))))))


(take 400 (naturals-0))

(fibo 100)
(take 100 (fibos))

(catalan 100)
(take 100 (catalans))

(bell 100)
(take 10 (bells))
