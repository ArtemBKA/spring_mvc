package com.bka.service;

import org.springframework.stereotype.Component;

@Component
public class PasswordGeneratorService {
    public String generatePassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            int r = (int) (Math.random() * 57) + 65;
            if (r % 2 == 0) {
                password.append((char) r);
            } else {
                password.append(r);
            }
        }
        return password.toString();
    }
}
