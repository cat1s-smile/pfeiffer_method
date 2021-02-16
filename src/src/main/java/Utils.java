package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Utils {
    public static char[][] getKey(String keyFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(keyFile));
        char[][] key = new char[5][5];
        int i = 0;
        String line = reader.readLine();
        while (line != null) {
            String[] str = line.split(" ");
            int j = 0;
            for (; j < 5; j++) {
                if (str[j].length() != 1)
                    throw new IllegalArgumentException("invalid key format");
                key[i][j] = (str[j]).charAt(0);
            }
            i++;
            if (j != 5)
                throw new IllegalArgumentException("invalid key format");
            line = reader.readLine();
        }
        reader.close();
        if (i != 5)
            throw new IllegalArgumentException("invalid key format");
        return key;
    }
}
