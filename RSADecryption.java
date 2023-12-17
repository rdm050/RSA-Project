package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class RSADecryption {

    public static void main(String[] args) throws IOException {

        String ciphertext = args[0];
        String cipherresults = args[1];
        int publickey_d = Integer.parseInt(args[3]);
        int publickey_n = Integer.parseInt(args[4]);

        File cp = new File(ciphertext);
        Scanner sc = new Scanner(cp);

        String str;
        StringBuilder filestring= new StringBuilder();

        while(sc.hasNextLine()){
            str = sc.nextLine();
            filestring.append(Decrypt(str,publickey_n,publickey_d));
        }

        System.out.println(filestring);
        BigInteger d3 = new BigInteger(String.valueOf(filestring));
        System.out.println(cipherToString(d3,cipherresults));


    }

    public static String Decrypt(String str, int n, int d){
        BigInteger bis = new BigInteger(String.valueOf(str));
        BigInteger nis = new BigInteger(String.valueOf(n));


        BigInteger results = bis.pow(d);

        BigInteger r3 = results.mod(nis);
        System.out.println(r3);

        String finalresults = r3.toString();

        return finalresults;


    }

    public static String cipherToString(BigInteger message, String decryptedresults) throws IOException {
        String cipherString = message.toString();
        String output = "";
        int i = 0;
        while (i < cipherString.length()) {
            int temp = Integer.parseInt(cipherString.substring(i, i + 2));
            char ch = (char) temp;
            output = output + ch;
            i = i + 2;
        }

        File cipherresults = new File(decryptedresults);
        FileWriter fw = new FileWriter(cipherresults);

        fw.write(output);
        fw.close();

        return output;
    }

}
