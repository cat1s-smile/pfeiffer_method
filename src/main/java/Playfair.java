package main.java;

public class Playfair {
    public static String encryptBigram(char c1, char c2, char[][] key, boolean dual, boolean jFirst, boolean jSecond, boolean last) {
        int c1_j = -1, c1_k = -1;
        calc1:
        {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (key[j][k] == c1) {
                        c1_j = j;
                        c1_k = k;
                        break calc1;
                    }
                }
            }
        }
        int c2_j = -1, c2_k = -1;
        calc2:
        {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (key[j][k] == c2) {
                        c2_j = j;
                        c2_k = k;
                        break calc2;
                    }
                }
            }
        }
        if (c1_j != c2_j && c1_k != c2_k) {
            c1 = key[c1_j][c2_k];
            c2 = key[c2_j][c1_k];
        }
        if (c1_j == c2_j && c1_k != c2_k) {
            c1 = key[c1_j][(c1_k + 1) % 5];
            c2 = key[c2_j][(c2_k + 1) % 5];
        }
        if (c1_j != c2_j && c1_k == c2_k) {
            c1 = key[(c1_j + 1) % 5][c1_k];
            c2 = key[(c2_j + 1) % 5][c2_k];
        }
        if (c1_j == c2_j && c1_k == c2_k) { // 'x' == 'x'
            c1 = c2 = key[c1_j][(c1_k + 1) % 5];
        }
        char[] result = new char[6];
        int i = 0;
        result[i++] = c1;
        if (jFirst)
            result[i++] = Utils.randChar(2);
        if (last) {
            result[i++] = c2;
            result[i++] = Utils.randChar(1);
        }
        else if (dual) {
            result[i++] = Utils.randChar(0);
            result[i++] = c2;
            if (jSecond)
                result[i++] = Utils.randChar(2);
        }
        else if (jSecond) {
            result[i++] = c2;
            result[i++] = Utils.randChar(2);
        }
        else
            result[i++] = c2;
        return new String(result, 0, i);
    }

    private static void encryptLine(StringBuilder text, String line, char[][] key) {
        String[] words = line.split(" ");
        for (String word : words) {
            int length = word.length();
            for (int i = 0; i < length; ) {
                boolean dualChar = false, jFirst = false, jSecond = false, last = false;
                char c1 = word.charAt(i++);
                if (c1 == 'j') {
                    jFirst = true;
                    c1 = 'i';
                }
                if (i == length)
                    last = true;
                char c2 = 'x';
                if (!last) {
                    c2 = word.charAt(i++);
                    if (c2 == 'j') {
                        jSecond = true;
                        c2 = 'i';
                    }
                    if (c1 == c2) {
                        dualChar = true;
                        c2 = 'x';
                    }
                }
                text.append(encryptBigram(c1, c2, key, dualChar, jFirst, jSecond, last));
            }
            text.append(' ');
        }
        if (words.length != 0)
            text.deleteCharAt(text.length() - 1);

        /* String[] words = line.split(" ");
        for (String word : words) {
            int length = word.length();
            //word = word.replaceAll("j", "i" + Utils.randChar(2));
            //int length = word.length();
            StringBuilder preparedWord = new StringBuilder();
            if (length % 2 != 0) {
                word += "x" + Utils.randChar(1);
                length++;
            }
            for (int i = 0; i < length; ) {
                char txt1 = word.charAt(i++);
                if (txt1 == 'j')
                    preparedWord.append('i').append(Utils.randChar(2));
                else {
                    preparedWord.append(txt1);
                    if(Utils.classOfRand(txt1) == 1)
                        break;
                }
                char txt2 = word.charAt(i++);
                if (txt1 == txt2)
                    preparedWord.append(Utils.randChar(0)).append('x');
                else
                    preparedWord.append(txt2);


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
            text.deleteCharAt(text.length() - 1); */
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
                char[] bigram = new char[6];
                int m = 0, letterCnt = 1;
                bigram[m++] = word.charAt(i++);
                for (; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (Utils.classOfRand(c) == -1) // if letter
                        letterCnt++;
                    if (letterCnt == 3)
                        break;
                    bigram[m++] = c;
                }
                encryptedText.append(decryptBigram(new String(bigram, 0, m), key));
                
            }
           /* if (word.length() > 1 && word.charAt(length - 1) == word.charAt(length - 2)) {
                encryptedText.deleteCharAt(encryptedText.length() - 1);
                length--;
            }*/
            encryptedText.append(' ');
        }
        if (words.length != 0)
            encryptedText.deleteCharAt(encryptedText.length() - 1);
    }

    private static String decryptBigram(String bigram, char[][] key) {
        char c1 = bigram.charAt(0);
        char c2 = 0;
        boolean dual = false, jFirst = false, jSecond = false, last = false;
        switch (Utils.classOfRand(bigram.charAt(1))) {
            case 0:
                dual = true;
                break;
            case 2:
                jFirst = true;
                break;
            case -1:
                c2 = bigram.charAt(1);
                break;
        }
        switch (bigram.length()) {
            case 3:
                if (Utils.classOfRand(bigram.charAt(2)) == 1)
                    last = true;
                else if (Utils.classOfRand(bigram.charAt(2)) == 2)
                    jSecond = true;
                else
                    c2 = bigram.charAt(2);
                break;
            case 4:
                if (Utils.classOfRand(bigram.charAt(1)) == 0) {
                    dual = true;
                    jSecond = true;
                    c2 = bigram.charAt(2);
                }
                else {
                    dual = true;
                    jFirst = true;
                    c2 = bigram.charAt(3);
                }
                break;
            case 5:
                c2 = bigram.charAt(3);
                dual = true;
                jFirst = true;
                jSecond = true;
                break;
        }
       /* for (int j = 1; j < bigram.length(); j++) {
            if (Utils.classOfRand(bigram.charAt(j)) == -1) {
                c2 = bigram.charAt(j);
                break;
            }
        }*/
        int c1_j = -1, c1_k = -1;
        calc1:
        {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (key[j][k] == c1) {
                        c1_j = j;
                        c1_k = k;
                        break calc1;
                    }
                }
            }
        }
        int c2_j = -1, c2_k = -1;
        calc2:
        {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (key[j][k] == c2) {
                        c2_j = j;
                        c2_k = k;
                        break calc2;
                    }
                }
            }
        }
        if (c1_j != c2_j && c1_k != c2_k) {
            c1 = key[c1_j][c2_k];
            c2 = key[c2_j][c1_k];
        }
        if (c1_j == c2_j && c1_k != c2_k) {
            c1 = key[c1_j][(c1_k + 4) % 5];
            c2 = key[c2_j][(c2_k + 4) % 5];
        }
        if (c1_j != c2_j && c1_k == c2_k) {
            c1 = key[(c1_j + 4) % 5][c1_k];
            c2 = key[(c2_j + 4) % 5][c2_k];
        }
        if (c1_j == c2_j && c1_k == c2_k) { // 'x' == 'x'
            c1 = c2 = key[c1_j][(c1_k + 4) % 5];
        }
        if (dual)
            c2 = c1;
        if (jFirst)
            c1 = 'j';
        if (jSecond)
            c2 = 'j';
        String res = String.valueOf(c1);
        if (last)
            return res;
        else
            return res + c2;
    }
}
