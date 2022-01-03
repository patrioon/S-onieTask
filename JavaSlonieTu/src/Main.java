import java.io.IOException;

public class Main {

    private static final int MAXN = 1000000;

    private static final int[] oryg = new int[MAXN];
    private static final int[] masa = new int[MAXN];
    private static final int[] dcel = new int[MAXN];
    private static final boolean[] czy = new boolean[MAXN];

    public static void main(String[] args) throws IOException,ArrayIndexOutOfBoundsException {
        int n = 0;
        int m_najlzejszego_slonia_w_ogole = 1 << 30;
        long wynik = 0;
        n = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));

        for (int i = 0; i < n; ++i) {
            masa[i] = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
            m_najlzejszego_slonia_w_ogole = Math.min(m_najlzejszego_slonia_w_ogole, masa[i]);
        }
        for (int i = 0; i < n; ++i) {
            oryg[i] = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
            --oryg[i];
        }
        for (int i = 0, k = 0; i < n; ++i) {
            k = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
            dcel[--k] = oryg[i];
        }
        for (int i = 0; i < n; ++i) {
            if (czy[i]) {
                continue;
            }
            int k = i;
            int m_najlzejszego_slonia_w_cyklu = 1 << 30;
            int c = 0;
            long suma = 0;

            do {
                suma += masa[k];
                m_najlzejszego_slonia_w_cyklu = Math.min(m_najlzejszego_slonia_w_cyklu, masa[k]);
                k = dcel[k];
                czy[k] = true;
                c++;
            } while (i != k);
            wynik += Math.min(suma + (long) (c - 2) * m_najlzejszego_slonia_w_cyklu, suma + m_najlzejszego_slonia_w_cyklu + (long) (c + 1) * m_najlzejszego_slonia_w_ogole);
        }
        System.out.print(wynik);
        System.out.print('\n');
    }

//Helper class
    public static final class Arrays {
        public static boolean[] padWithDefaultBooleanInstances(int length, boolean[] existingItems) {
            if (length > existingItems.length) {
                boolean[] array = new boolean[length];

                for (int i = 0; i < existingItems.length; i++) {
                    array[i] = existingItems[i];
                }

                return array;
            } else
                return existingItems;
        }

        public static <T extends java.io.Closeable> void deleteArray(T[] array) {
            for (T element : array) {
                if (element != null) {
                    try {
                        element.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

//Helper class
    public final static class ConsoleInput  {
        private static boolean goodLastRead = false;

        public static boolean lastReadWasGood() {
            return goodLastRead;
        }

        public static String readToWhiteSpace(boolean skipLeadingWhiteSpace) throws IOException{
            String input = "";
            char nextChar;
            while (Character.isWhitespace(nextChar = (char) System.in.read())) {
                //accumulate leading white space if skipLeadingWhiteSpace is false:
                if (!skipLeadingWhiteSpace) {
                    input += nextChar;
                }
            }
            //the first non white space character:
            input += nextChar;

            //accumulate characters until white space is reached:
            while (!Character.isWhitespace(nextChar = (char) System.in.read())) {
                input += nextChar;
            }

            goodLastRead = input.length() > 0;
            return input;
        }

        public static String scanfRead() throws IOException {
            return scanfRead(null, -1);
        }

        public static String scanfRead(String unwantedSequence) throws IOException {
            return scanfRead(unwantedSequence, -1);
        }

        public static String scanfRead(String unwantedSequence, int maxFieldLength) throws IOException {
            String input = "";

            char nextChar;
            if (unwantedSequence != null) {
                nextChar = '\0';
                for (int charIndex = 0; charIndex < unwantedSequence.length(); charIndex++) {
                    if (Character.isWhitespace(unwantedSequence.charAt(charIndex))) {
                        //ignore all subsequent white space:
                        while (Character.isWhitespace(nextChar = (char) System.in.read())) {
                        }
                    } else {
                        //ensure each character matches the expected character in the sequence:
                        nextChar = (char) System.in.read();
                        if (nextChar != unwantedSequence.charAt(charIndex))
                            return null;
                    }
                }

                input = (new Character(nextChar)).toString();
                if (maxFieldLength == 1)
                    return input;
            }

            while (!Character.isWhitespace(nextChar = (char) System.in.read())) {
                input += nextChar;
                if (maxFieldLength == input.length())
                    return input;
            }

            return input;
        }
    }
}
