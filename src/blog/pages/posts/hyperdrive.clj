(ns blog.pages.posts.hyperdrive)

(def title "Hyperdrive: A Common Wallet Architecture for Blockchains")

(defn content
  [context]
  [:div {:class "container"}
   [:div {:class "row"}
    [:h3 title]]
   [:div {:class "row"}
    [:p "I recently had a patent go through with my name on it. According to the United States Patent Office, I am officially an inventor. Despite my somewhat conflicting feelings about the ethicality of patents in software it is a pretty cool achievement to have solved a complex problem in a unique way. One of the problems that needs to be solved by centralized exchanges with broad offerings of cryptocurrency networks, is how to manage many crypto wallets at scale. Reducing the shared problem of each wallet into a common architecture, has enabled us to solve problems common across multiple blockchains once and only once. This lets us focus on solving every problem really well, and on solving new problems!"]
    [:p "You can find the patent " [:a {:href "https://patents.justia.com/patent/12182801"} "here"] ", and a more human digestable blog post on the Gemini blog " [:a {:href "https://www.gemini.com/blog/how-geminis-hyperdrive-wallet-helps-us-build-on-blockchain-infrastructure"} "here"] "."]]])
