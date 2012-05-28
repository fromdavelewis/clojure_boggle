(ns clojure_boggle.core)
(use '[clojure.java.io :only (reader)])
(use '[clojure.string :only (join split)])

(def words_file "words.txt")
(def words (set (line-seq (reader words_file))))
(def puzzle ["I" "A" "D" "S" "O" "A" "N" "P" "L" "O" "T" "R" "A" "Y" "F" "A"])
(def MIN_RIGHT 0)
(def MIN_DOWN 0)
(def MAX_RIGHT 3)
(def MAX_DOWN 3)
(def ROW_SIZE (- (+ MAX_RIGHT 1) MIN_RIGHT))
(def COL_SIZE (- (+ MAX_DOWN 1) MIN_DOWN))

(defn get-grid-letter [x y grid]
    "Simulated x, y getter access to a grid represented as a vector"
    (let [grid-index (+ x (* y ROW_SIZE)) grid-letter (nth grid grid-index)]
        grid-letter)
)

(defn set-grid-letter [x y grid newval]
    "Simulated x, y setter access to a grid represented as a vector"
    (let [grid-index (+ x (* y ROW_SIZE))]
        (assoc grid grid-index newval))
)

(defn valid? [x y puzzle_state]
    "Is this a valid letter?"
    (and 
        (<= x MAX_RIGHT)
        (<= y MAX_DOWN)
        (>= x MIN_RIGHT)
        (>= y MIN_DOWN)
        (not (= (get-grid-letter x y puzzle_state) "0"))
    )
)

(declare advance add-letter)

(defn advance [x y puzzle_state word_state]
    "Recurse to add more letters"
;    (println word_state)
    (if (and (>= (count word_state) 3) (contains? words word_state)) (println word_state))
    ;right
    (if (valid? (+ x 1) y puzzle_state) (add-letter (+ x 1) y puzzle_state word_state))
    (if (valid? (+ x 1) (+ y 1) puzzle_state) (add-letter (+ x 1) (+ y 1) puzzle_state word_state))
    (if (valid? (+ x 1) (- y 1) puzzle_state) (add-letter (+ x 1) (- y 1) puzzle_state word_state))
    ;left
    (if (valid? (- x 1) y puzzle_state) (add-letter (- x 1) y puzzle_state word_state))
    (if (valid? (- x 1) (+ y 1) puzzle_state) (add-letter (- x 1) (+ y 1) puzzle_state word_state))
    (if (valid? (- x 1) (- y 1) puzzle_state) (add-letter (- x 1) (- y 1) puzzle_state word_state))
    ;down
    (if (valid? x (+ y 1) puzzle_state) (add-letter x (+ y 1) puzzle_state word_state))
    ;up
    (if (valid? x (- y 1) puzzle_state) (add-letter x (- y 1) puzzle_state word_state))
)

(defn add-letter [x y puzzle_state word_state]
    "Add letter in new landing place to the word and keep going"
    (let [grid-letter (get-grid-letter x y puzzle_state)]
        (advance x y (set-grid-letter x y puzzle_state "0") (str word_state grid-letter))
    )
)

(doseq [x (range 0 4)]
    (doseq [y (range 0 4)]
        (add-letter x y puzzle "")
    )
)
