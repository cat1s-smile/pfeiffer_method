package main.java;

public class Playfair {
    private static StringBuilder encryptLine(StringBuilder text, String line, char[][] key) {
        //line.
        //encryptedline
        String[] words = line.split(" ");
        for (String word : words) {
            word = word.replaceAll("j", "i");
            int length = word.length();
            if (length % 2 != 0) {
                word = word.concat("x");
                length++;
            }
            for (int i = 0; i < length; ) {
                char txt1 = line.charAt(i++);
                char txt2 = line.charAt(i++);
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
        text.deleteCharAt(text.length() - 1); // #FIXME or kill me
        return text;
    }

    public static StringBuilder encrypt(String text, char[][] key) {
        String[] lines = text.split("\n");
        StringBuilder encryptedText = new StringBuilder();
        for (String line : lines) {
            encryptLine(encryptedText, line, key);
        }
        return encryptedText;
    }


}
