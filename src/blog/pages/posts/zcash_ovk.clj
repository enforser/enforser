(ns blog.pages.posts.zcash-ovk)

(def title "Zcash: Outgoing Viewing Key to Enable Output Decryption")

(defn content
  [context]
  [:div {:class "container"}
   [:div {:class "row"}
    [:h3 title]]
   [:div {:class "row"}
    [:p
     "This post extends "
     [:a {:href "/decrypt-zcash.html"} "Decrypt Zcash (Part I)"]
     ", where I discussed what an Outgoing Cipher Key is and how to decrypt a shielded output from a Zcash transaction with it."
     " Here I will discuss Outgoing Viewing Keys (OVK), which enable the derivation of the OCK mentioned in Part I."]
    [:p "An Outgoing Viewing Key is used when a shielded output is created for a Zcash transaction. An OVK can be any 32-byte sequence. The OVK which is used to create a shielded output can later be used to derive the OCK (the key which can be used to decrypt the output later). The OCK is the blake2b256 hash of four fields of an output description with along with a personalization. Per section \"4.5 Output Descriptions\" of the Zcash protocol spec these fields are: \"cv\", \"cmu\", \"epk\", and \"ovk\" (outgoing viewing key)."]
    [:pre [:code
     "
;; Assuming each of the above fields is already defined as a list of bytes, you can calculate
;; the OCK of an output with:
(def personalization \"Zcash_Derive_ock\")
(def bytes (concat ovk cv cmu epk))
(when (not= 128 (count bytes)) (throw (Exception. \"Input must be 128 bytes\")))
;; The 32-byte hash is the OCK for the given output description.
(blake2b256 bytes personalization)
     "]]

    [:p "What is special about the Outgoing Viewing Key is that it is the only field in the above process which is not available after output description construction. In other words, the OVK is used to create the shielded output, but it cannot later be derived from the output. The effect of this is that the Outgoing Viewing Key must be remembered by the person or program constructing the transaction, in order to have the capability of deriving an OCK to decrypt the output in the future."]
    [:p "Since the Outgoing Viewing Key can be any 32-byte sequence, it enables a lot of flexibility in creating schemes to disclose groups of payment information to others. If you want a 3rd party to be able to audit some number of transactions on chain, then you must simply use the same OVK when constructing them - and then they will be able to derive the OCKs required to decrypt each output given the single outgoing viewing key."]
    [:p "In the case of ZEC wallets, a user could have the ability to retroactively derive an OCK for any of their past transactions if the wallet maintains the OVK used behind the scenes. Compare that to the current implementation of most ZEC wallets which use some unknown and soon forgotten OVK. In the latter case you no longer have the option to generate an OCK to disclose the details behind a single payment. Using a known and remembered OVK at output construction time allows users to generate an OCK as a proof of payment. Imagine you are making an anonymous donation to a charity with Zcash, but to file your taxes with the tax receipt you must provide proof of payment on chain. In this case you could disclose that single payment asking your wallet to use the secret OVK to generate the OCK which can decrypt the relevant payment output."]
    [:p
     "The following form accepts a raw transaction and an outgoing viewing key. It derives all possible OCKs from the transaction. "
     [:a {:href "/decrypt-zcash.html"} "\"Decrypt Zcash: Part 1\""]
     " explains how to use those keys to decrypt an output from a transaction on chain."]
    [:p "To protect your privacy all decryption processing on this page is done entirely in your browser. This avoids your viewing keys from needing to be shared over the web, to be decrypted on some unknown server. This is done by using WebAssembly to take advantage of the crypto and decryption already defined in the "
     [:a {:href "https://github.com/zcash/librustzcash/tree/master/zcash_primitives"} "zcash_primitives"]
     " rust crate. The WebAssembly/Rust interop can be found "
     [:a {:href "https://github.com/enforser/enforser/tree/master/decrypt-zcash"} "here"]
     "."]]
   [:i  "Note that while this form returns the OCKs as an example, a wallet or payment disclosure service can go a step further and handle deriving OCKs and trying to decrypt with them in a single go."]
   [:br]
   [:br]
   [:div {:class "row"}
    [:p "Raw Transaction" ]
    [:textarea {:id "rawTx" :cols "60"}]
    [:p "Outgoing Viewing Key (Remove '0x' prefix)"]
    [:textarea {:id "ovk" :cols "40"}]
    [:br]
    [:input {:id "decryptForm" :type "submit" :value "Derive Outgoing Cipher Keys!"}]]
   [:br]
   [:p {:hidden true :id "message"}]
   [:div {:id "results" :hidden true}
    [:p
     "The following are your possible Outgoing Cipher Keys. Head on over to "
     [:a {:href "/decrypt-zcash.html"} "Decrypt Zcash"]
     " to try and parse outputs of the transaction with these OCKs. Keep in mind that we've generated an OCK per output, but the decryption will only be successful if the viewing key used to generate is the same as the one initially used to construct the output."]]
   [:script {:type "module"}
    "
      import init, { findocks } from './decrypt-zcash-assets/decrypt_zcash.js';
      async function decryptOutput() {
        await init();
        document.getElementById('results').hidden = true;
        document.getElementById('message').innerHTML = '<i>Decrypting! Hold tight.</i>';
        document.getElementById('message').hidden = false;
        try {
            console.log(document.getElementById('rawTx').value)
            console.log(document.getElementById('ovk').value)
            const parsed = findocks(
              document.getElementById('rawTx').value,
              document.getElementById('ovk').value
            );
            console.log(parsed);
            const ocks = parsed.split('|', 3).map(ock => '    - ' + ock);
            document.getElementById('message').hidden = true;
            document.getElementById('results').hidden = false;
            document.getElementById('results').innerHTML = '<p>' + ocks.join('</p><p>') + '</p>';

        } catch (error) {
          console.error(error);
          document.getElementById('message').innerHTML = 'Failed to parse transaction';
          document.getElementById('message').hidden = false;
        }
        return false;
      }

      document.getElementById('decryptForm').addEventListener('click', decryptOutput);
    "]])
