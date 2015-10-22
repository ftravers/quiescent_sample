(ns ^:figwheel-always quie.qui
  (:require [quiescent.core :as q]
            [weasel.repl :as repl]
            [sablono.core :as html :refer-macros [html]]))
(repl/connect "ws://localhost:9001")

;; the data store
(defonce world (atom {:text "Hellooo!"}))

;; the component
(q/defcomponent Root [data] (html [:h1 (:text data)]))

;; (q/defcomponent )
;; [:input {:type "text" :ref "new-contact"}]

;; stick component on page
(defn render [cmp data]
  (q/render (cmp data) (.getElementById js/document "app")))

;; if data store changes, re-render page
(add-watch world ::render (fn [_ _ _ data] (render Root data)))

(defn on-js-reload [] (swap! world update-in [:tmp-dev] not))




