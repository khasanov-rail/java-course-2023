package edu.hw8.Task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task3 {

    private static final String MD5_ALGORITHM = "MD5";
    private static final int MAX_PASSWORD_LENGTH = 4;

    private final Map<String, String> passwordHashes;

    public Task3(Map<String, String> passwordHashes) {
        this.passwordHashes = new HashMap<>(passwordHashes);
    }

    // Однопоточная версия
    public Map<String, String> crackPasswords() throws NoSuchAlgorithmException {
        Map<String, String> crackedPasswords = new HashMap<>();
        MessageDigest md = MessageDigest.getInstance(MD5_ALGORITHM);
        String currentPassword = "";

        while (true) {
            currentPassword = nextPassword(currentPassword);
            if (currentPassword.isEmpty()) {
                break;
            }

            String currentHash = toMD5Hash(md, currentPassword);

            if (passwordHashes.containsKey(currentHash)) {
                crackedPasswords.put(passwordHashes.get(currentHash), currentPassword);
                passwordHashes.remove(currentHash);
            }
        }

        return crackedPasswords;
    }

    // Многопоточная версия
    public Map<String, String> crackPasswordsMultiThreaded(int threadCount) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Map<String, String> crackedPasswords = Collections.synchronizedMap(new HashMap<>());

        String currentPassword = "";
        while (true) {
            currentPassword = nextPassword(currentPassword);
            if (currentPassword.isEmpty()) {
                break;
            }

            String finalCurrentPassword = currentPassword;
            executor.execute(() -> {
                try {
                    MessageDigest mdThread = MessageDigest.getInstance(MD5_ALGORITHM);
                    String currentHash = toMD5Hash(mdThread, finalCurrentPassword);
                    synchronized (passwordHashes) {
                        if (passwordHashes.containsKey(currentHash)) {
                            crackedPasswords.put(passwordHashes.get(currentHash), finalCurrentPassword);
                            passwordHashes.remove(currentHash);
                        }
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        return crackedPasswords;
    }

    // Генерирует следующий пароль, основываясь на текущем пароле
    private String nextPassword(String current) {
        if (current.isEmpty()) {
            return "a";
        }
        char[] chars = current.toCharArray();
        int index = chars.length - 1;
        while (index >= 0) {
            if (chars[index] < 'z') {
                chars[index]++;
                return new String(chars);
            } else {
                chars[index] = 'a';
                index--;
            }
        }
        return current.length() < MAX_PASSWORD_LENGTH ? "a" + new String(chars) : "";
    }

    // Вычисляет MD5 хеш для данного пароля
    private String toMD5Hash(MessageDigest md, String password) {
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
