(ns blog.pages.posts.decrypt-zcash)

(def title "Zcash: Decrypt Sapling Outputs")

(defn content
  [context]
  [:div {:class "container"}
   [:div {:class "row"}
    [:h3 title]]
   [:div {:class "row"}
    [:p "A Zcash transaction may consist of both transparent and shielded outputs. Shielded outputs describe funds which are moving to a private z-address. If you are using a wallet which manages your z-address for you, it likely handles your addresses \"Incoming Viewing Key\" to decrypt the shielded output which defines the payment being made. This key can be used to decrypt any payment made to that z-address, so it is likely not something which is recommended to be disclosed - as that would reveal all past and future payments to the address."]
    [:p "There are other viewing keys in Zcash which are more restrictive in terms of what information they disclose. This post is concerned with the \"Outgoing Cipher Key\" (OCK). The OCK is a 32-byte key which has the ability to decrypt a single output of a transaction. This is valuable because it allows specific payments to be disclosed to third parties, without revealing any information outside of the single output."]
    [:p "The following form accepts a raw transaction and an OCK. To decrypt, each shielded output of the transaction is applied against the given OCK until one is successfully decrypted."]
    [:p "To protect your privacy all decryption processing on this page is done entirely in your browser. This avoids your OCK from needing to be shared over the web, to be decrypted on some unknown server. This is done by using WebAssembly to take advantage of the Outgoing Cipher Key decryption already defined in the "
     [:a {:href "https://github.com/zcash/librustzcash"} "librustzcash"]
     " rust crate. The WebAssembly/Rust interop can be found "
     [:a {:href "https://github.com/enforser/enforser/tree/master/decrypt-zcash"} "here"]
     "."]]
   [:div {:class "row"}
    [:p "Raw Transaction" ]
    [:textarea {:id "rawTx" :cols "60"}]
    [:p "Outgoing Cipher Key"]
    [:textarea {:id "ock" :cols "40"}]
    [:br]
    [:input {:id "decryptForm" :type "submit" :value "Decrypt!"}]]
   [:br]
   [:p {:hidden true :id "message"}]
   [:table {:id "results" :hidden true}
    [:tr
     [:td "Value"]
     [:td {:id "zec"}]]
    [:tr
     [:td "Address"]
     [:td {:id "address"}]]
    [:tr
     [:td "Memo"]
     [:td {:id "memo"}]]]
   [:script {:type "module"}
    "
      import init, { greet } from './decrypt-zcash-assets/decrypt_zcash.js';
      async function decryptOutput() {
        await init();
        document.getElementById('results').hidden = true;
        document.getElementById('message').innerHTML = '<i>Decrypting! Hold tight.</i>';
        document.getElementById('message').hidden = false;
        try {
            console.log(document.getElementById('rawTx').value)
            console.log(document.getElementById('ock').value)
            const parsed = greet(
              document.getElementById('rawTx').value,
              document.getElementById('ock').value
            );
            const [zatoshis, address, memo] = parsed.split('|', 3);
            const zec = parseInt(zatoshis, 10) / 100000000 + ' ZEC';
            const decrypted = {
              zatoshis,
              zec,
              address,
              memo
            };
            console.log(decrypted.memo);
            document.getElementById('memo').innerHTML = decrypted.memo.length < 1 ? '<i>no memo provided</i>' : decrypted.memo;
            document.getElementById('zec').innerHTML = decrypted.zec;
            document.getElementById('address').innerHTML = decrypted.address;
            document.getElementById('message').hidden = true;
            document.getElementById('results').hidden = false;
        } catch (error) {
          console.error(error);
          document.getElementById('message').innerHTML = 'Failed to parse transaction';
          document.getElementById('message').hidden = false;
        }
        return false;
      }

      document.getElementById('decryptForm').addEventListener('click', decryptOutput);
    "]])
