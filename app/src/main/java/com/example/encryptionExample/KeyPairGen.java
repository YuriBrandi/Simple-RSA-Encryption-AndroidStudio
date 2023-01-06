package com.example.encryptionExample;

import java.security.*;

public class KeyPairGen {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public KeyPairGen() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); //Using RSA
        keyGen.initialize(1024); //customizable
        KeyPair pair = keyGen.generateKeyPair(); //Generate Key Pair
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }


    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

}