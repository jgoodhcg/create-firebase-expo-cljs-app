(ns app.fx
  (:require
   ["react-native-router-flux" :as nav]
   ["firebase" :as firebase]
   [re-frame.core :refer [reg-fx dispatch]]
   [applied-science.js-interop :as j]))

(reg-fx :navigate
        (fn [screen]
          (j/call nav/Actions screen)))

(reg-fx :firebase-init
        (fn [config]
          (-> firebase (.initializeApp (clj->js config)))))
