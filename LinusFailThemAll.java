import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LinusFailThemAll {
    static Kattio kattio;
    static Boolean[][] answers;
    static int numberOfStudents;
    static int numberOfQuestions;
    static boolean[] answer;

    private static void solveProblem() {
        numberOfStudents = kattio.getInt();
        numberOfQuestions = kattio.getInt();
        answers = new Boolean[numberOfStudents][numberOfQuestions];
        answer = new boolean[numberOfQuestions];
        for (int i = 0; i < numberOfStudents; i++) {
            char[] in = kattio.getWord().toCharArray();
            for (int j = 0; j < numberOfQuestions; j++) {
                switch (in[j]) {
                    case 'F':
                        answers[i][j] = false;
                        break;
                    case 'T':
                        answers[i][j] = true;
                        break;
                    default:
                        answers[i][j] = null;
                        break;
                }
            }
        }
        //kattio.println(Arrays.deepToString(answers));
        boolean solve = solve(0, false, new boolean[numberOfStudents]);
        if (!solve) {
            kattio.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (boolean b : answer) {
                sb.append(b ? 'T' : 'F');
            }
            kattio.println(sb.toString());
        }
    }

    private static boolean solve(int index, boolean correct, boolean[] alreadyOneCorrect) {
        boolean[] aoc = Arrays.copyOf(alreadyOneCorrect, alreadyOneCorrect.length);
        if (index == numberOfQuestions) {
            return true;
        }
        for (int i = 0; i < numberOfStudents; i++) {
            //kattio.println(correct);
            //kattio.println(Arrays.toString(alreadyOneCorrect));
            //kattio.println(answers[i][index]);
            if (Boolean.valueOf(correct).equals(answers[i][index])) {
                //kattio.println("woo");
                if (alreadyOneCorrect[i]) {
                    return false;
                }
                aoc[i] = true;
            }
        }
        answer[index] = correct;
        boolean solve1 = solve(index + 1, false, aoc);
        if (solve1) {
            return true;
        }
        boolean solve2 = solve(index + 1, true, aoc);
        if (solve2) {
            return true;
        }
        return false;
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
