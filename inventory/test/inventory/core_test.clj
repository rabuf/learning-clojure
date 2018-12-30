(ns inventory.core-test
  (:require [clojure.test :refer :all]
            [inventory.core :refer :all]
            [clojure.test.check.clojure-test :as ctest]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(def title-gen (gen/such-that not-empty gen/string-alphanumeric))
(def author-gen (gen/such-that not-empty gen/string-alphanumeric))
(def copies-gen (gen/such-that (complement zero?) gen/pos-int))

(def book-gen
  (gen/hash-map :author author-gen :title title-gen :copies copies-gen))

(def inventory-gen
  (gen/not-empty (gen/vector book-gen)))

(def inventory-and-book-gen
  (gen/let [inventory inventory-gen
            book (gen/elements inventory)]
    {:inventory inventory :book book}))

(ctest/defspec find-by-title-finds-books 50
  (prop/for-all [i-and-b inventory-and-book-gen]
                (= (find-by-title (-> i-and-b :book :title) (:inventory i-and-b))
                   (:book i-and-b))))
