import java.io.*;
import java.util.*;

public class LinusTournamentSeeding {
    static Kattio kattio;

    private static void solveProblem() {
        int numberOfRounds = kattio.getInt();
        int threshold = kattio.getInt();
        long numberOfPlayers = (1L << numberOfRounds);
        final List<Integer> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(kattio.getInt());
        }
        int answer = 0;
        players.sort(Collections.reverseOrder());
        Iterator<Integer> iterator = players.iterator();
        final List<Integer> fixed = new ArrayList<>();
        Integer first = iterator.next();
        fixed.add(first);
        Integer second = iterator.next();
        fixed.add(second);
        if (delta(first, second) <= threshold) {
            answer++;
        }
        final List<Integer> competing = new ArrayList<>();
        for (int i = 1; i < numberOfRounds; i++) {
            int x = 1 << i;
            for (int j = 0; j < x; j++) {
                competing.add(iterator.next());
            }
            fixed.sort(Collections.reverseOrder());
            competing.sort(Collections.reverseOrder());
            //kattio.println(Arrays.toString(players.toArray()));
            //kattio.println(Arrays.toString(fixed.toArray()));
            //kattio.println(Arrays.toString(competing.toArray()));
            int f = 0;
            int c = 0;
            while (f < x && c < x) {
                //kattio.println(f);
                //kattio.println(c);
                int ff = fixed.get(f);
                int cc = competing.get(c);
                //kattio.println(ff);
                //kattio.println(cc);
                if (delta(ff, cc) <= threshold) {
                    answer++;
                    f++;
                    c++;
                } else if (ff > cc) {
                    f++;
                } else {
                    c++;
                }
            }
            fixed.addAll(competing);
            competing.clear();
        }
        kattio.println(answer);
    }

    static class Obj {
        int index;
        boolean[] thresholdHit;

        public Obj(int index, boolean[] thresholdHit) {
            this.index = index;
            this.thresholdHit = thresholdHit;
        }
    }

    private static int delta(Integer a, Integer b) {
        return Math.abs(a - b);
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
