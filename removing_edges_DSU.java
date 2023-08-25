import javax.lang.model.type.ArrayType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static class Graph {
        static int[] parent;
        static int[] rank;
        static ArrayList<ArrayList<Integer>> adj;

        public Graph(int nodes) {
            parent = new int[nodes];
            rank = new int[nodes];

            for (int i = 0; i < nodes; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            adj = new ArrayList<>();
            for (int i = 0; i < nodes; i++)
                adj.add(new ArrayList<>());
        }

        public static boolean addEdge(int u, int v) {
            int up = find(u);
            int vp = find(v);

            if (up == vp)
                return true;
            else {
                if (rank[up] < rank[vp]) {
                    parent[up] = vp;
                } else if (rank[up] > rank[vp]) {
                    parent[vp] = up;
                } else {
                    parent[up] = vp;
                    rank[vp]++;
                }
            }
            adj.get(u).add(v);
            adj.get(v).add(u);
            return false;
        }

        public static int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }
    }

    static void solveOne(int testCase, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph g = new Graph(n);
        int cnt = 0;

        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            u--;
            v--;
            if (Graph.addEdge(u, v))
                cnt++;
        }
        out.println(cnt);
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
