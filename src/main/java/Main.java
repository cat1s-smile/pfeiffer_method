package main.java;

import main.java.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(args[0])));
        char[][] key = Utils.getKey(args[1]);
        String encrypted = Playfair.encrypt(text, key);
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(args[2]));
        writer1.append(encrypted);
        writer1.close();
        String decrypted = Playfair.decrypt(encrypted.toString(), key);
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(args[3]));
        writer2.append(decrypted);
        writer2.close();
    }
}
