import java.io.*;
import java.util.*;

public class LinusTournamentSeeding {
    static Kattio kattio;

    private static void solveProblem() {
        int numberOfRounds = kattio.getInt();
        int threshold = kattio.getInt();
        long numberOfPlayers = (1 << numberOfRounds);
        final List<Integer> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(kattio.getInt());
        }
        int answer = 0;
        Collections.sort(players);
        Iterator<Integer> iterator = players.iterator();
        Integer first = iterator.next();
        iterator.remove();
        Integer second = iterator.next();
        iterator.remove();
        if (delta(first, second) <= threshold) {
            answer++;
        }
        if (numberOfRounds == 1) {
            kattio.println(answer);
            return;
        }
        Integer third = iterator.next();
        iterator.remove();
        Integer fourth = iterator.next();
        iterator.remove();
        boolean b1 = delta(first, third) <= threshold;
        boolean b2 = delta(second, fourth) <= threshold;
        boolean b3 = delta(first, fourth) <= threshold;
        boolean b4 = delta(second, third) <= threshold;
        if (b1 && b2) {
            answer += 2;
        } else if (b3 && b4) {
            answer += 2;
        } else if (b1 || b2 || b3 || b4) {
            answer++;
        }
        if (numberOfRounds == 2) {
            kattio.println(answer);
            return;
        }

        while (iterator.hasNext()) {

        }
    }

    private static int delta(Integer a, Integer b) {
        return Math.abs(a.intValue() - b.intValue());
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
