(ns ^:figwheel-always quie.qui
  (:require [quiescent.core :as q]
            [weasel.repl :as repl]
            [sablono.core :as html :refer-macros [html]]))
(repl/connect "ws://localhost:9001")

;; the data store
(defonce world (atom {:text "Hellooo!"}))

;; the component
(q/defcomponent myin [data]
  (html [:input {:type "text" :value (:text data)
                 :on-change
                 #(swap! world assoc :text
                         (-> % .-target .-value))} ]))

;; stick component on page
(defn render [cmp data]
  (q/render (cmp data) (.getElementById js/document "app")))

;; if data store changes, re-render page
(add-watch world ::render (fn [_ _ _ data] (render myin data)))

;; when pages is reloaded, modify 'world' triggering a re-render
(defn on-js-reload [] (swap! world update-in [:tmp-dev] not))




