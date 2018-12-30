(ns inventory.core
  (:require [clojure.spec.alpha :as s])
  (:require [clojure.spec.test.alpha :as st]))

(defn find-by-title
  "Search for a book by title, where title is a string and books is a
  collection of books maps each of which must have a :title entry."
  [title books]
  (some #(when (= (:title %) title) %) books))

(defn number-of-copies
  "Return the number of copies in inventory of the given title,
  where title is a string and books is a collection of book maps each
  of which must have a :title entry"
  [title books]
  (:copies (find-by-title title books)))
(s/def ::title string?)
(s/def ::author string?)
(s/def ::copies int?)
(s/def ::book (s/keys :req-un [::title ::author ::copies]))
(s/def ::inventory (s/coll-of ::book))
(s/fdef find-by-title
  :args (s/cat :title ::title
               :books ::inventory))
