(ns create-firebase-expo-cljs-app.lib
  (:require [create-cljs-app.lib :as cca-lib]
            [create-cljs-app.utils :refer [has-binary-on-PATH?]]
            [create-expo-cljs-app.lib :refer [get-commands expo-cli-warning]]
            ["chalk" :refer [blue green red yellow]]))


(defn done-msg
  [name path abs-path commands install-failed?]
  (.log
    js/console
    (str
      "\nSuccess! Created " name " at " abs-path "
Inside that directory, you can run several commands:

  " (blue (:shadow commands)) "
    Starts the shadow compiler.

  " (blue (:start commands)) "
    Starts the javascript bundler.

We suggest that you begin by: \n  "

      (blue (str "cd " path)) "\n  "
      (when install-failed? (str (blue (:install commands)) "\n  "))
      (blue (:shadow commands)) "\n  "
      "Then in " (yellow "another") " terminal session run:\n  "
      (blue (:start commands)) "\n\n"
      "Happy hacking! \n")))

(defn create [cwd path]
  (cca-lib/create cwd path {:done-msg       done-msg
                            :get-commands   get-commands
                            :has-extras?    (fn [] (has-binary-on-PATH? "expo"))
                            :extras-warning expo-cli-warning}))

(def exports #js {:create create})
