package com.kantora.testove.components;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kantora.testove.models.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import utils.StringUtils;
import utils.TestDataProvider;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Component
public class TempMailApiRestHandler {

    @Autowired
    private RestTemplate restTemplate;

    public final String EMAIL = TestDataProvider.getData().getEmail();

    public final String MD5HASH = getMd5(EMAIL);
    public final String GET_LETTERS_URL = String.format("http://api.temp-mail.ru/request/mail/id/%s/format/json/", MD5HASH);
    public final String DEL_LETTER_URL = String.format("http://api.temp-mail.ru/request/delete/id/%s/", MD5HASH);


    public static String getMd5(String s) {
        byte[] bytesOfMessage = new byte[0];
        try {
            bytesOfMessage = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] thedigest = new byte[0];
        thedigest = md.digest(bytesOfMessage);
        BigInteger bigInt = new BigInteger(1, thedigest);
        String hashtext = bigInt.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    public String getConfirmLinkFromMail() {
        sleep(10);
        return getConfirmLinkFromMail(5);
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getConfirmLinkFromMail(int tries) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> exchange = restTemplate.exchange(GET_LETTERS_URL, HttpMethod.GET, entity, String.class);
        String respBody = exchange.getBody();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<EmailResponse>>() {
        }.getType();
        List<EmailResponse> emails = gson.fromJson(respBody, listType);

        for (EmailResponse response : emails) {
            if (StringUtils.getConfirmMailPass(response.mail_text_only).equals(TestDataProvider.getData().getPassword())) {
                return StringUtils.getConfirmMailLink(response.mail_text_only);
            }
        }
        sleep(5);
        if (tries > 0) {
            getConfirmLinkFromMail(--tries);
        }

        System.out.println("No letter found");
        return null;
    }

    public void removeMessages() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> exchange = restTemplate.exchange(DEL_LETTER_URL, HttpMethod.DELETE, entity, String.class);
    }
}
