package main.java;

public class Playfair {
    public static String encrypt(String text, char[][] key) {
        StringBuilder encryptedText = new StringBuilder();
        //text.
        //encryptedText
        String[] words = text.split(" ");
        for (String word : words) {
            word = word.replaceAll("j", "i");
            int length = word.length();
            if (length % 2 != 0) {
                word += "x";
                length++;
            }
            for (int i = 0; i < length;) {
                char txt1 = text.charAt(i++);
                char txt2 = text.charAt(i++);
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
                calc2: {
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
                    txt1 = key[txt2_j][txt1_k];
                    txt2 = key[txt1_k][txt2_k];
                }

            }

        }
        int length = text.length();
        while (i < length) {


        }
        return null;
    }


}
