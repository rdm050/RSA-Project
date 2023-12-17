package com.company;

import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class RSAEncryption {

    public static void main(String[] args) throws IOException {

        String txtfile = args[0];
        String outputfile = args[1];
        int blocksize = Integer.parseInt(args[2]);
        int privatekey_e = Integer.parseInt(args[3]);
        int privatekey_n = Integer.parseInt(args[4]);


        File plaintext = new File(txtfile);
        Scanner sc = new Scanner(plaintext);

        String str;
        StringBuilder filestring= new StringBuilder();

        while(sc.hasNextLine()){
            str = sc.nextLine();
            filestring.append(str);
        }

        System.out.println(filestring);
        String encryptstring = filestring.toString();

        BigInteger bi = stringCipher(encryptstring);
        System.out.println(bi);

        encrypt(bi,privatekey_n,privatekey_e,blocksize,outputfile);

        }

    public static BigInteger stringCipher(String message) {
        message = message.toUpperCase();
        String cipherString = "";
        int i = 0;
        while (i < message.length()) {
            int ch = message.charAt(i);
            cipherString = cipherString + ch;
            i++;
        }
        BigInteger cipherBig = new BigInteger(String.valueOf(cipherString));
        return cipherBig;
    }

    public static void encrypt(BigInteger bi, int n, int e, int blocksize, String output) throws IOException {
        File cipher = new File(output);
        FileWriter fw = new FileWriter(cipher);
        String bis = bi.toString();
        String[] results = bis.split("(?<=\\G.{" + blocksize   + "})");

        for(int i = 0; i < results.length; i++){
            String m = results[i];
            int num = Integer.parseInt(m);
            int num2 = (int)(Math.pow(num,e) % n);

            String ciphertext = Integer.toString(num2);
            System.out.println(ciphertext);

            fw.write(ciphertext);
            fw.write(System.lineSeparator());

        }

        fw.close();
    }
}
