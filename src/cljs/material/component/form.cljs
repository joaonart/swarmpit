(ns material.component.form
  (:refer-clojure :exclude [comp])
  (:require [material.components :as cmp]
            [material.icon :as icon]
            [sablono.core :refer-macros [html]]
            [swarmpit.time :as time]))

(defn item-main
  ([name value]
   (item-main name value true))
  ([name value separator?]
   (cmp/box
     {:className "Swarmpit-form-item-wrapper"}
     (when separator?
       (cmp/divider {}))
     (cmp/box
       {:className "Swarmpit-form-item"}
       (cmp/box
         {:className "Swarmpit-form-item-name"}
         (cmp/typography {:variant "body2"} name))
       (cmp/box
         {:className "Swarmpit-form-item-value"}
         (cmp/typography {:variant "body2"} value))))))

(defn item-date
  [date]
  (html
    [:time {:date-time date
            :title     (time/simplify date)}
     (time/humanize date)]))

(defn item [name value]
  (html
    [:div {:class "Swarmpit-row-space"
           :key   (str "sri-" name)}
     (cmp/typography {:variant "body2"
                      :color   "textSecondary"} name)
     (cmp/typography {:variant "body2"} value)]))

(defn item-info [message]
  (cmp/typography {:variant "body2"
                   :color   "textSecondary"} message))

(defn message [comp]
  (html
    [:span.Swarmpit-message
     (icon/info {:style {:marginRight "8px"}})
     [:span comp]]))

(defn error-message [text]
  (cmp/snackbar-content
    {:className "Swarmpit-label-red"
     :elevation 0
     :message   (html [:span.Swarmpit-message
                       (icon/error {:className "Swarmpit-message-icon"}) text])}))

(defn item-icon [icon comp]
  (html
    [:span.Swarmpit-message
     (icon {:style {:marginRight "8px"
                    :fontSize    "16px"}})
     [:span comp]]))

(defn item-id [id]
  (html
    [:div.Swarmpit-form-card-icon-item
     (icon/fingerprint
       {:className "Swarmpit-form-card-icon"})
     [:span.Swarmpit-form-card-icon-item-id
      (cmp/typography
        {:color     "textSecondary"
         :className "Swarmpit-form-card-icon-text"} id)]]))

(defn item-labels [labels]
  (html
    [:div {:class "Swarmpit-form-card-labels"
           :key   "item-labels"}
     labels]))

(defn section
  ([name]
   (section name nil))
  ([name button]
   (html
     [:div {:class "Swarmpit-form-section"
            :id    name}
      [:div
       (cmp/typography
         {:variant "h6"} name)]
      [:div button]])))

(defn subsection
  ([name]
   (subsection name nil))
  ([name button]
   (html
     [:div.Swarmpit-form-section
      [:div
       (cmp/typography
         {:variant "subtitle2"} name)]
      [:div button]])))

(defn open-in-new [text href]
  (html
    [:a {:href      href
         :className "Swarmpit-new-tab"
         :target    "_blank"}
     [:div text]
     [:div (icon/open-in-new
             {:className "Swarmpit-new-tab-ico"})]]))