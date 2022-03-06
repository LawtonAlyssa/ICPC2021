import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinusSMS {
    static Kattio kattio;
    static int min = Integer.MAX_VALUE;
    static Set<Character> characters = new HashSet<>();

    private static void solveProblem() {
        char[] charSet = kattio.getWord().toCharArray();
        char[] target = kattio.getWord().toCharArray();
        for (char c : target) characters.add(c);
        int numberOfQueries = kattio.getInt();
        Set<char[]> found = new HashSet<>();
        int max = target.length;

        for (int digits = 2; digits <= max; digits++) {
            Set<char[]> testables = getTestables(0, digits);
            for(char[] testable : testables) {
                if(isSMS(target, testable)) {
                    found.add(testable);
                    max = digits;
                }
            }
        }

        for (int i = 0; i < numberOfQueries; i++) {
            char[] query = kattio.getWord().toCharArray();
            kattio.println(found.stream().anyMatch(c -> Arrays.equals(c, query)) ? 1 : 0);
        }
    }

    private static Set<char[]> getTestables(int i, int digits) {
        if (i == digits - 1) {
            return characters.stream().map(c -> new char[]{c}).collect(Collectors.toSet());
        }
        Set<char[]> recursive = getTestables(i + 1, digits);
        return characters
                .stream()
                .flatMap(c -> recursive
                        .stream()
                        .map(chars -> {
                            char[] copy = Arrays.copyOf(chars, chars.length + 1);
                            copy[chars.length] = c;
                            return copy;
                        }))
                .collect(Collectors.toSet());
    }

    private static boolean isSMS(char[] target, char[] query) {
        if (min < query.length) {
            return false;
        }
        int a = 0;
        int b = 0;
        while (a < target.length && b < query.length) {
            if (!characters.contains(query[b])) {
                return false;
            }
            if (target[a] == query[b]) {
                a++;
                b++;
            } else {
                a++;
            }
        }
        if (b == query.length) {
            return false;
        }
        if (query.length < min) {
            min = query.length;
            //kattio.printf("min to %d because of %s%n", min, Arrays.toString(query));
            for (int i = 0; i < query.length; i++) {
                char[] trying = new char[query.length - 1];
                for (int j = 0; j < query.length - 1; j++) {
                    trying[j] = query[j < i ? j : j + 1];
                }
                isSMS(target, trying);
            }
        }
        return true;
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
