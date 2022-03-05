import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class LinusTreeHopping {
    static Kattio kattio;

    private static void solveProblem() {
        int numberOfTestcases = kattio.getInt();
        Queue<Obj> q = new LinkedList<>();
        while (numberOfTestcases-- > 0) {
            int numberOfNodes = kattio.getInt();
            boolean[][] nodes = new boolean[numberOfNodes][numberOfNodes];
            for (int i = 0; i < numberOfNodes - 1; i++) {
                int a = kattio.getInt() - 1;
                int b = kattio.getInt() - 1;
                nodes[a][b] = true;
                nodes[b][a] = true;
            }
            boolean valid = true;
            int last;
            int current = kattio.getInt() - 1;
            for (int i = 0; i < numberOfNodes - 1; i++) {
                last = current;
                current = kattio.getInt() - 1;
                if (!valid) {
                    continue;
                }

                boolean validLocal = false;
                boolean[] visited = new boolean[numberOfNodes];
                q.add(new Obj(last, 0));
                while (!q.isEmpty()) {
                    Obj x = q.poll();
                    visited[x.node] = true;
                    if (x.depth > 3) {
                        break;
                    }
                    if (x.node == current) {
                        validLocal = true;
                        break;
                    }
                    boolean[] node = nodes[x.node];
                    for (int j = 0; j < node.length; j++) {
                        boolean b = node[j];
                        if (!visited[j] && b) {
                            q.add(new Obj(j, x.depth + 1));
                        }
                    }
                }
                if (!validLocal) {
                    valid = false;
                }

                q.clear();
            }
            kattio.println(valid ? 1 : 0);
        }
    }

    static class Obj {
        int node;
        int depth;

        public Obj(int node, int depth) {
            this.node = node;
            this.depth = depth;
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
