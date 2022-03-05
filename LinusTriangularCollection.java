import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class LinusTriangularCollection {
    static Kattio kattio;

    private static void solveProblem() {
        final int n = kattio.getInt();
        final Integer[] set = new Integer[n];
        LinkedList<Integer[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            set[i] = kattio.getInt();
        }
        Arrays.sort(set);
        int answer = 0;
        for (int i = 0; i < n - 2; i++) {
            final int a = set[i];
            for (int j = i + 1; j < n - 1; j++) {
                final int b = set[j];
                for (int k = j + 1; k < n; k++) {
                    final int c = set[k];
                    if (a + b > c && a + c > b && b + c > a) {
                        final Integer[] found = {i, j, k};
                        kattio.printf("Found: %s (%d, %d, %d)%n", Arrays.toString(found), a, b, c);
                        queue.add(found);
                        answer++;
                    }
                }
            }
        }
        int digits = 3;
        while (!queue.isEmpty()) {
            if (digits < queue.peek().length) {
                queue = queue.stream()
                        .distinct()
                        .sorted(Comparator.comparingInt(a -> a.length))
                        .collect(Collectors.toCollection(LinkedList::new));
            }
            final Integer[] combination = queue.poll();
            kattio.printf("Combination: %s%n", Arrays.toString(combination));
            if (queue.isEmpty()) {
                break;
            }
            final Queue<Integer[]> innerQueue = new LinkedList<>(queue);
            final List<Integer[]> pollCache = new ArrayList<>();
            while (!innerQueue.isEmpty()) {
                int twoEquals = 0;
                Integer[] poll = null;
                while (twoEquals < combination.length && !innerQueue.isEmpty()) {
                    poll = innerQueue.poll();
                    if (poll.length != combination.length || Arrays.equals(combination, poll)) {
                        poll = null;
                        break;
                    }
                    kattio.printf("Poll: %s%n", Arrays.toString(poll));
                    final Integer[] pollCopy = poll;
                    if (allButOneEqual(combination, poll) && pollCache.stream().allMatch(x -> allButOneEqual(pollCopy, x))) {
                        kattio.println("Two equals");
                        pollCache.add(pollCopy);
                        twoEquals++;
                    } else {
                        break;
                    }
                }
                if (twoEquals == combination.length && poll != null) {
                    kattio.println("PLUS ONE");
                    final Integer[] joined = joinSets(combination, poll);
                    if (queue.stream().noneMatch(x -> Arrays.equals(x, joined))) {
                        queue.add(joined);

                    }
                    answer++;
                }
            }
        }
        kattio.println(answer);
    }

    private static Integer[] joinSets(Integer[] a, Integer[] b) {
        final Set<Integer> set = new HashSet<>();
        set.addAll(Arrays.asList(a));
        set.addAll(Arrays.asList(b));
        final Integer[] result = set.toArray(Integer[]::new);
        kattio.printf("%s + %s = %s%n", Arrays.toString(a), Arrays.toString(b), Arrays.toString(result));
        return result;
    }

    private static boolean allButOneEqual(Integer[] a, Integer[] b) {
        final List<Integer> bList = Arrays.asList(b);
        return Arrays.stream(a).filter(o -> !bList.contains(o)).count() == 1;
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
