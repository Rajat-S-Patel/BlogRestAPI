package com.rajat.blogapi;

import java.util.Random;

public class RandomIdGenerator {
    
    private static final String chars="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String getStringId(int len){
        Random rnd= new Random();
        StringBuilder str=new StringBuilder(len);
        for(int i=0;i<len;i++){
            str.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return str.toString();
    }
}
