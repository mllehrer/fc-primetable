(ns fc-primetable.core)

(defn check-prime [n]
  "Checks if a number is divisible by prime factors up to the square root"
  (if (= 0 (mod n 2))
    false
    (loop [i 3]
      (if (> i (int (Math/sqrt n)))
        true
        (if (= 0 (mod n i))
          false
          (recur (+ i 2)))))))

(defn find-n-primes [n]
  "Adds prime numbers to a collection and returns the collection of n primes"
  (loop [nums 2 coll [2]]
    (if (>= (count coll) n)
      coll
      (recur (inc nums)
             (if (check-prime nums)
               (conj coll nums)
               coll)))))

(defn format-col [n col-width]
  (let [content-width (count (str n))]
    (if (> col-width (count (str n)))
      (str (apply str (repeat (- col-width content-width) \space)) n)
      n)))

(defn print-table
  "Creates a table with the input list as row headers and column headers"
  [numbers]
  (let [col-width (count (str (* (last numbers) (last numbers))))
        num-cols (count numbers)
        table-width (- (+ col-width (* num-cols col-width)) (/ num-cols 10))]
    (println (format-col " X" col-width) (interleave (repeat "|") (map #(format-col % col-width) numbers)))
    (dotimes [n (count numbers)]
      (println (repeat table-width "-"))
      (println
        (interpose "|"
                   (cons
                     (format-col (nth numbers n) col-width)
                     (map #(format-col % col-width) (map #(* % (nth numbers n)) numbers))))))))

(defn main [n]
  "Prints a table of n prime number products to stdout"
  (print-table (find-n-primes (read-string n))))
