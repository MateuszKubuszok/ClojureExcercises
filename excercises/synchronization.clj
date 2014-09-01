(ns excercises.synchronization)

(defn initiate-model [] '()) ; initiate model as empty list

(def model (ref (initiate-model))) ; create model

(defn update-model-fun [fun] ; function
  (fn [mod arg]              ; returning function
    (fun mod arg)))          ; that calls passed function for arguments

(defn update-model [fun arg]                   ; takes function and arg and
  (dosync                                      ; runs transaction that will
    (alter model (update-model-fun fun) arg))) ; update our model with them

; adds 3 values to the model
(update-model conj 1)
(update-model conj 2)
(update-model conj 3)

; sorts model list - ascending order, needed to reverse order of arguments
(update-model #(sort %2 %1) <)
