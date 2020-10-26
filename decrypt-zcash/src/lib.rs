use wasm_bindgen::prelude::*;
use pairing::bls12_381::Bls12;
use zcash_primitives::{
    consensus::MainNetwork,
    note_encryption::try_sapling_output_recovery_with_ock,
    transaction::Transaction,
    primitives::PaymentAddress,
    JUBJUB,
};
use bech32::{Bech32, ToBase32};
use std::str;

extern crate hex;

#[wasm_bindgen]
extern {
    pub fn alert(s: &str);
}

#[wasm_bindgen]
pub fn greet(raw_tx: &str, ock: &str) -> JsValue {
    let decoded = hex::decode(raw_tx).expect("Failed to parse raw transaction");
    let outgoing_cipher_key = hex::decode(ock).expect("Failed to parse outgoing cipher key");
    let tx = Transaction::read(decoded.as_slice()).unwrap();
    let output_details = {
        tx.shielded_outputs
            .iter()
            .flat_map(
                |so|
                try_sapling_output_recovery_with_ock::<MainNetwork>(
                    1046401, // try to decrypt with post canopy memo
                    &outgoing_cipher_key,
                    &so.cmu,
                    &so.ephemeral_key.as_prime_order(&JUBJUB).unwrap(),
                    &so.enc_ciphertext,
                    &so.out_ciphertext
                )
                .or_else(
                    ||
                    try_sapling_output_recovery_with_ock::<MainNetwork>(
                        1046300, // try to decrypt with pre-canopy memo
                        &outgoing_cipher_key,
                        &so.cmu,
                        &so.ephemeral_key.as_prime_order(&JUBJUB).unwrap(),
                        &so.enc_ciphertext,
                        &so.out_ciphertext
                    )
                )
            )
            .map(|(note, addr, memo)| {
                let address_str = Bech32::new(
                    "zs".into(),
                    PaymentAddress::<Bls12>::from_bytes(&addr.to_bytes(), &JUBJUB)
                        .unwrap()
                        .to_bytes()
                        .to_vec()
                        .to_base32()
                    ).unwrap().to_string();
                let memo_str = memo.to_utf8().map(|x| x.unwrap()).unwrap_or("".to_owned());
                let value_str = note.value.to_string();
                [value_str, address_str, memo_str].join("|")
            })
            .next()
            .unwrap_or("".to_owned())
    };
    JsValue::from_str(&output_details)
}
