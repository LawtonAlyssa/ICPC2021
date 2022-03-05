import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LinusLCS {
    static Kattio kattio;

    private static void solveProblem() {
        final int numberOfStrings = kattio.getInt();
        final int numberOfCharacters = kattio.getInt();
        final char[] firstInput = kattio.getWord().toCharArray();
        final char[][] subsequences = new char[1 << numberOfCharacters][0];
        
        for (int bitMap = 0; bitMap < (1 << numberOfCharacters); bitMap++) {
            subsequences[bitMap] = new char[Integer.bitCount(bitMap)];
            int index = 0;
            for (int i = 0; i < numberOfCharacters; i++) {
                if (((1 << i) & bitMap) == (1 << i)) {
                    subsequences[bitMap][index++] = firstInput[i];
                }
            }
            kattio.println(bitMap);
            kattio.flush();
        }
        kattio.println(Arrays.deepToString(subsequences));
        for (int i = 1; i < numberOfStrings; i++) {
            final char[] nextInput = kattio.getWord().toCharArray();
            for (int j = 0; j < subsequences.length; j++) {
                final char[] subsequence = subsequences[j];
                if (subsequence.length == 0) {
                    continue;
                }
                int subsequenceIndex = 0;
                for (char c : nextInput) {
                    if (c == subsequence[subsequenceIndex]) {
                        subsequenceIndex++;
                        if (subsequenceIndex == subsequence.length) {
                            break;
                        }
                    }
                }
                if (subsequenceIndex != subsequence.length) {
                    subsequences[j] = new char[0];
                }
            }
        }
        kattio.println(Arrays.stream(subsequences).mapToInt(x -> x.length).max().orElse(1));
    }

    public static void main(String[] args) {
        // Initialize fast IO
        kattio = new Kattio(System.in);
        // The actual problem code
        solveProblem();
        // Flush fast IO, so that everything is printed to the console
        kattio.flush();
        kattio.close();
    }

    /**
     * Faster input reading than with {@link java.util.Scanner}
     * <p>
     * <a href="https://open.kattis.com/help/java">Source</a>
     * <p>
     *
     * @author Kattis
     */
    static class Kattio extends PrintWriter {
        public Kattio(InputStream i) {
            this(i, System.out);
        }

        public Kattio(InputStream i, OutputStream o) {
            super(new BufferedWriter(new OutputStreamWriter(o)));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public boolean hasMoreTokens() {
            return peekToken() != null;
        }

        public int getInt() {
            return Integer.parseInt(nextToken());
        }

        public double getDouble() {
            return Double.parseDouble(nextToken());
        }

        public long getLong() {
            return Long.parseLong(nextToken());
        }

        public String getWord() {
            return nextToken();
        }

        private BufferedReader r;

        private String line;

        private StringTokenizer st;

        private String token;

        private String peekToken() {
            if (token == null) {
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null) {
                            return null;
                        }
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}
