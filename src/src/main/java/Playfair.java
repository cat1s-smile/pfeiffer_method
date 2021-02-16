package main.java;

public class Playfair {
    private static void encryptLine(StringBuilder text, String line, char[][] key) {
        String[] words = line.split(" ");
        for (String word : words) {
            word = word.replaceAll("j", "i");
            int length = word.length();
            if (length % 2 != 0) {
                word = word.concat("x");
                length++;
            }
            for (int i = 0; i < length; ) {
                char txt1 = word.charAt(i++);
                char txt2 = word.charAt(i++);
                if (txt1 == txt2)
                    txt2 = 'x';
                int txt1_j = -1, txt1_k = -1;
                calc1:
                {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            if (key[j][k] == txt1) {
                                txt1_j = j;
                                txt1_k = k;
                                break calc1;
                            }
                        }
                    }
                }
                int txt2_j = -1, txt2_k = -1;
                calc2:
                {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            if (key[j][k] == txt2) {
                                txt2_j = j;
                                txt2_k = k;
                                break calc2;
                            }
                        }
                    }
                }
                if (txt1_j != txt2_j && txt1_k != txt2_k) {
                    txt1 = key[txt1_j][txt2_k];
                    txt2 = key[txt2_j][txt1_k];
                }
                if (txt1_j == txt2_j && txt1_k != txt2_k) {
                    txt1 = key[txt1_j][(txt1_k + 1) % 5];
                    txt2 = key[txt2_j][(txt2_k + 1) % 5];
                }
                if (txt1_j != txt2_j && txt1_k == txt2_k) {
                    txt1 = key[(txt1_j + 1) % 5][txt1_k];
                    txt2 = key[(txt2_j + 1) % 5][txt2_k];
                }
                if (txt1_j == txt2_j && txt1_k == txt2_k) { // 'x' == 'x'
                    txt1 = txt2 = key[txt1_j][(txt1_k + 1) % 5];
                }
                text.append(txt1).append(txt2);
            }
            text.append(' ');
        }
        if (words.length != 0)
            text.deleteCharAt(text.length() - 1);
    }

    public static String encrypt(String text, char[][] key) {
        String[] lines = text.split("\n");
        StringBuilder encryptedText = new StringBuilder();
        for (String line : lines) {
            encryptLine(encryptedText, line.replaceAll("[^a-zA-Z ]", ""), key);
            encryptedText.append("\n");
        }
        return encryptedText.toString();
    }

    public static String decrypt(String text, char[][] key) {
        String[] lines = text.split("\n");
        StringBuilder encryptedText = new StringBuilder();
        for (String line : lines) {
            decryptLine(encryptedText, line, key);
            encryptedText.append("\n");
        }
        return encryptedText.toString();
    }

    private static void decryptLine(StringBuilder encryptedText, String line, char[][] key) {
        String[] words = line.split(" ");
        for (String word : words) {
            int length = word.length();
            for (int i = 0; i < length; ) {
                char txt1 = word.charAt(i++);
                char txt2 = word.charAt(i++);
                int txt1_j = -1, txt1_k = -1;
                calc1:
                {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            if (key[j][k] == txt1) {
                                txt1_j = j;
                                txt1_k = k;
                                break calc1;
                            }
                        }
                    }
                }
                int txt2_j = -1, txt2_k = -1;
                calc2:
                {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            if (key[j][k] == txt2) {
                                txt2_j = j;
                                txt2_k = k;
                                break calc2;
                            }
                        }
                    }
                }
                if (txt1_j != txt2_j && txt1_k != txt2_k) {
                    txt1 = key[txt1_j][txt2_k];
                    txt2 = key[txt2_j][txt1_k];
                }
                if (txt1_j == txt2_j && txt1_k != txt2_k) {
                    txt1 = key[txt1_j][(txt1_k + 4) % 5];
                    txt2 = key[txt2_j][(txt2_k + 4) % 5];
                }
                if (txt1_j != txt2_j && txt1_k == txt2_k) {
                    txt1 = key[(txt1_j + 4) % 5][txt1_k];
                    txt2 = key[(txt2_j + 4) % 5][txt2_k];
                }
                if (txt1_j == txt2_j && txt1_k == txt2_k) { // 'x' == 'x'
                    txt1 = txt2 = key[txt1_j][(txt1_k + 4) % 5];
                }
                encryptedText.append(txt1).append(txt2);
            }
            if (word.length() > 1 && word.charAt(length - 1) == word.charAt(length - 2)) {
                encryptedText.deleteCharAt(encryptedText.length() - 1);
                length--;
            }
            encryptedText.append(' ');
        }
        if (words.length != 0)
            encryptedText.deleteCharAt(encryptedText.length() - 1);
    }


}
