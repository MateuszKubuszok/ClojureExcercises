(ns excercises.simple-sequence)

(defn
  ^{:doc  "Exponent for natural powers"
    :test #(assert (= 32 (exp 2 5)))}
  exp [x n]
  (reduce * (repeat n x))) ; generates list of n xs and calculates their product

(defn
  ^{:doc  "1/(2^n) fraction"
    :test #(assert (= (/ 1 32) (nth-2-frac 2 5)))}
  nth-2-frac [n]
  (/ 1 (exp 2 n))) ; returns 1/(2^n) fraction

(defn
  ^{:doc  "1/(2^n) fraction lazy sequence"
    :test #(assert (=
                     (list 1 (/ 1 2) (/ 1 4) (/ 1 8) (/ 1 16))
                     (take 5 (nth-2-frac-seq))))}
  nth-2-frac-seq
  ([]  (nth-2-frac-seq 0)) ; makes (nth-2-frac-seq) call (nth-2-frac-seq 0)
  ([n] (cons                            ; appends
         (nth-2-frac n)                 ; 1/(2^n) in front of
         (lazy-seq                      ; lazy sequence of
           (nth-2-frac-seq (inc n)))))) ; nth-2-frac-seq's following elements

(take 40 (nth-2-frac-seq)) ; first 40 elements of 1/(2^n) sequence
