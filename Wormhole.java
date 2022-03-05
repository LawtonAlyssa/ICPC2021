import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Wormhole {
    static Kattio kattio;

    private static void solveProblem() {
        final int numberOfTestcases = kattio.getInt();
        for(int testcaseNumber = 1; testcaseNumber <= numberOfTestcases; testcaseNumber++) {
            kattio.printf("Case %s%n", testcaseNumber);
            final int numberOfPlanets = kattio.getInt();
            
        }
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
