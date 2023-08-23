import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static void solveOne(int testCase, FastReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList());

        boolean[] vis = new boolean[n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            u--;
            v--;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                cnt++;
                dfs(i, adj, vis);
            }
        }
        out.println(cnt);
    }

    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[node] = true;
        for (Integer child : adj.get(node)) {
            if (!vis[child])
                dfs(child, adj, vis);
        }
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
