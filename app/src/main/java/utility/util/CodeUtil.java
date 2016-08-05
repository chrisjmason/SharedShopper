package utility.util;

import java.util.Random;

public class CodeUtil {

    public static String getCode(){
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int length = alphabet.length();
        String code = new String();
        Random r = new Random();

        for(int i = 0; i < 8; i++){
            code = code + alphabet.charAt(r.nextInt(length));
        }

        return code;
    }
}
