import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static int N;
    static int X;
    static int Y;
    static int[] food1;
    static int[] food2;

    static int[][][] dp;

    static void solveOne(int testCase, FastReader in, PrintWriter out) {
        N = in.nextInt();
        X = in.nextInt();
        Y = in.nextInt();

        food1 = new int[N];
        food2 = new int[N];

        for (int i = 0; i < N; i++) {
            food1[i] = in.nextInt();
            food2[i] = in.nextInt();
        }
        dp = new int[N][X + 1][Y + 1];
        for (int[][] a1 : dp) {
            for (int[] a2 : a1)
                Arrays.fill(a2, -1);
        }
        int ans = solve(0, X, Y);
        out.println(ans > N ? -1 : ans);

    }

    private static int solve(int currentPosition, int X, int Y) {
        if (X == 0 && Y == 0)
            return 0;
        if(currentPosition == N)
            return Integer.MAX_VALUE >> 1;
        if(dp[currentPosition][X][Y] != -1)
            return dp[currentPosition][X][Y];

        int op1 = 1 + solve(currentPosition + 1, Math.max(0, X - food1[currentPosition]), Math.max(0, Y - food2[currentPosition]));
        int op2 = solve(currentPosition + 1, X, Y);
        return  dp[currentPosition][X][Y] = Math.min(op1, op2);
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
