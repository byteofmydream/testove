package utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static String getConfirmMailLink(String mailHtmlString) {
//        String regExp = "/authorize[^\"]+/g";
//        Pattern p = Pattern.compile(regExp);
//        Matcher m = p.matcher(mailHtmlString);
//        return "https://my.rozetka.com.ua" + m.group(0);
        //Shame on me
        return mailHtmlString.substring(mailHtmlString.indexOf("/authorize")-25,mailHtmlString.indexOf("utm_content%3Dconfirm")+21);
    }

    public static String getConfirmMailPass(String mailHtmlString) {
        //I realized how bad I'm in regexp writing

//        String regExp = "[a-z]{9}O12!";
//        Pattern p = Pattern.compile(regExp);
//        Matcher m = p.matcher(mailHtmlString);
        if (!mailHtmlString.contains("O12!")) return null;
        int position = mailHtmlString.indexOf("O12!");
        return mailHtmlString.substring(position-9,position+4);
    }

    public static String generateRandomString(String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(new Random().nextInt(characters.length()));
        }
        return new String(text);
    }

    public static String generateRandomPass(){
        return generateRandomString("qwertyuiopasdfghjklzxcvbnm",9) + "O12!";
    }
}
