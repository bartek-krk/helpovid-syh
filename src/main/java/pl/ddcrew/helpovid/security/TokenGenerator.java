package pl.ddcrew.helpovid.security;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TokenGenerator {
    public static String generate(){
        int KEY_LENGTH = 30;

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i=0; i<KEY_LENGTH; i++) numbers.add((int) ((Math.random() * (122 - 97)) + 97));

        StringBuilder apiKey = new StringBuilder();
        for(int number : numbers) {
            String currentChar = Character.toString((char) number);
            apiKey.append(currentChar);
        }

        return apiKey.toString();
    }
}
