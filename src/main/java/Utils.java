package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;


public class Utils {
    private static final Random r = new Random();

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

    public static char randChar(int i) {
        char c = 'A';
        switch (i) {
            case 0:
                c = (char) (r.nextInt(5) + 'A');
                break;
            case 1:
                c = (char) (r.nextInt(5) + 'G');
                break;
            case 2:
                c = (char) (r.nextInt(5) + 'N');
                break;
        }
        return c;
    }

    public static int classOfRand(char c) {
        if (c >= 'A' && c <= 'F' )
            return 0;
        else if (c >= 'G' && c <= 'L' )
            return 1;
        else if (c >= 'N' && c <= 'S' )
            return 2;
        else return -1;
    }
}
