(ns ^:figwheel-always quie.qui
  (:require [quiescent.core :as q]
            [weasel.repl :as repl] ;; for CLJS REPL
            [sablono.core :as html :refer-macros [html]]))
(repl/connect "ws://localhost:9001") ;; for CLJS REPL

;; the data store
(defonce world (atom {:text "Hello!"}))

;; define component
(q/defcomponent Root [data]
  (html [:input {:type "text" :value (:text data)
                 :on-change #(swap! world assoc :text (-> % .-target .-value))}]))
 
;; render component on page
(defn render [data]
  (q/render (Root data) (.getElementById js/document "app")))

;; if data store changes, re-render page
(add-watch world ::render (fn [_ _ _ data] (render data))) 

;; on file reload, modify 'world' triggering a re-render
(defn on-js-reload [] (swap! world update-in [:tmp-dev] not))




