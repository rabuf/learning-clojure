(ns blottsbooks.core
  (:gen-class))
(defn say-welcome [what]
  (println "Welcome to" what))

(defn -main []
  "Welcome people to the store."
  (say-welcome "Blotts Books"))
