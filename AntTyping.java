import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AntTyping {
    static Kattio kattio;

    private static void solveProblem() {
        Kattio r = new Kattio(System.in);
        String line = r.line;
        int[][] grid = new int[3][3];
        boolean[][] visited = new boolean[3][3];
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                grid[i][j] = i*j;
            }
        }
        int answer = 0;
        for(char c : line.toCharArray()){
            int num = c-'0';
            for(int i = 1; i <= 3 ; i++){
                for(int j = 1; j <= 3; j++){
                    if(num == i*j){
                        answer += DFS(grid,i,j,visited,num);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static int DFS(int[][] grid, int i, int j, boolean[][] visited, int num){
        if(i < 0 || j < 0 || i > grid.length || j > grid[0].length || visited[i][j]){
            return 0;
        }
        visited[i][j] = true;
        if(grid[i][j] == num){
            return 1;
        }
        int sum = 
        DFS(grid,i+1,j,visited,num) +
        DFS(grid,i-1,j,visited,num) +
        DFS(grid,i,j+1,visited,num) +
        DFS(grid,i,j-1,visited,num);
        return sum;
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
