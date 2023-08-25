import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static class DSU {
        static int[] rank;
        static int[] parent;

        static ArrayList<ArrayList<Integer>> adj;

        public DSU(int N) {
            rank = new int[N + 1];
            parent = new int[N + 1];

            adj = new ArrayList<>();
            for (int i = 0; i <= N; i++)
                adj.add(new ArrayList<>());

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public static int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        public static void merge(int U, int V) {
            int up = find(U);
            int vp = find(V);
            if (rank[up] > rank[vp]) {
                parent[vp] = up;
            } else if (rank[up] < rank[vp]) {
                parent[up] = vp;
            } else {
                parent[up] = vp;
                rank[vp]++;
            }
            adj.get(U).add(V);
            adj.get(V).add(U);
        }
    }

    static void solveOne(int testCase, FastReader in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();

        int[] U = new int[M];
        int[] V = new int[M];

        DSU d = new DSU(N);
        for (int i = 0; i < M; i++) {
            U[i] = in.nextInt();
            V[i] = in.nextInt();
            DSU.merge(U[i], V[i]);
        }

        int[] vx = new int[N + 1];
        int[] es = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            vx[DSU.find(i)]++;
        }
        for (int i = 1; i <= N; i++) {
            es[DSU.find(U[i - 1])]++;
        }

        boolean ans = true;
        for (int i = 1; i <= N; i++) {
            if (es[i] != vx[i]) {
                ans = false;
                break;
            }
        }

        out.println(ans ? "Yes" : "No");
    }

    public static void main(String[] args) throws IOException {
        FastReader s = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int t = 1;
        for (int testCase = 1; testCase <= t; ++testCase) {
            solveOne(testCase, s, out);
        }
        out.close();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st = new StringTokenizer("");

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
