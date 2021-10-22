package com.datnek.elearning.lib.common.helper;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class StringHelper {

    private static final String ALPHABET_STRING = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC_STRING = "0123456789";
    public static final String ALPHA_NUMERIC_STRING = ALPHABET_STRING.toUpperCase() + ALPHABET_STRING.toLowerCase() + NUMERIC_STRING;
    public static final String PHONE_NUMBER_REGEX = "\\d{6,14}";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String REMOVE_EMOJIS_REGEX = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
    public static final String ALGORITHM = "SHA-512";
    public static final String PATH_DELIMITER = "/";
    public static final int GENERATED_IDENTIFIER_MAX = 10;
    public static final int GENERATED_IDENTIFIER_MIN = 2;
    public static final int GENERATED_PASSWORD_LENGTH = 4;
    public static final int MAX_FILE_SIZE = 25;
    public static final int MAX_TITLE_SIZE = 255;
    public static final int MAX_DESCRIPTION_SIZE = 2000;

    public static String removeDuplicatedWhiteSpace(String string){
        if (StringUtils.isNotEmpty(string)){
            return string.replaceAll("\\s+", " ").trim();
        }
        return string;
    }

    /**
     * capitalize the first character
     * @param string
     * @return
     */
    public static String capitalizeFirstCharacter(String string){
        if(StringUtils.isNotEmpty(string)){
            string = Character.toUpperCase(string.charAt(0)) + string.substring(1).toLowerCase();
        }
        return string;
    }

    /**
     * generate a random alphanumeric
     * @param numberOfChar
     * @return
     */
    public static String generateRandomAlphaNumeric(int numberOfChar ){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfChar; i++){
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    /**
     * generate a random alphanumeric
     * @param numberOfChar
     * @return
     */
    public static String generateRandomAlphaBetic(int numberOfChar ){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfChar; i++){
            int character = (int)(Math.random()*ALPHABET_STRING.length());
            builder.append(ALPHABET_STRING.charAt(character));
        }
        return builder.toString();
    }

    /**
     * generate a random alphanumeric
     * @param numberOfChar
     * @return
     */
    public static String generateRandomNumeric(int numberOfChar ){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfChar; i++){
            int character = (int)(Math.random()*NUMERIC_STRING.length());
            builder.append(NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    /**
     * generate random number with max
     * @param max
     * @return
     */
    public static Integer generateRandomNumber(int max){
        return (new Random()).nextInt(max);
    }

    /**
     * generate a secure password
     * @param passwordToHash
     * @param salt
     * @return
     */
    public static String generateSecurePassword(String passwordToHash, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String splitCurrentTimestamp(int min, int max){
        return String.valueOf(System.currentTimeMillis()).substring(min, max);
    }

    public static String generateIdentifier(){
        return splitCurrentTimestamp(GENERATED_IDENTIFIER_MIN, GENERATED_IDENTIFIER_MAX);
    }

    /*public static boolean isUrlValid(String url) {
        // Get an UrlValidator using default schemes
        UrlValidator defaultValidator = new UrlValidator();
        return defaultValidator.isValid(url);
    }*/

    /**
     * remove all unicode characters from a string
     * @param string incoming string
     * @return string without unicode characters
     */
    public static String removeUnicodeCharacters(String string){
        return string.replaceAll(REMOVE_EMOJIS_REGEX, "");
    }

    /**
     * parse string to int
     * @param s: string to convert to integer
     * @return integer or 0 otherwise
     */
    public static int parseInt(String s){
        try {
            return Integer.parseInt(s);
        }catch (Exception e){
            return 0;
        }
    }
}

