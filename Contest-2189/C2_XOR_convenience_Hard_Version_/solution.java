import java.io.*;
import java.util.*;

public class Main {

    static int msb(int n) {
        int x = 1;
        while ((x << 1) <= n) x <<= 1;
        return x;
    }

    static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    static int pickRoot(int n) {
        return (n % 2 != 0) ? n : msb(n);
    }

    static void solveCase(int n, StringBuilder sb) {
        if (isPowerOfTwo(n)) {
            sb.append("-1\n");
            return;
        }

        int[] p = new int[n + 1];
        boolean[] used = new boolean[n + 1];

        // primitive array instead of ArrayList<Integer>
        int[] active = new int[n];
        int activeSize = 0;

        int root = pickRoot(n);

        p[n] = root;
        used[root] = true;
        active[activeSize++] = root;

        for (int i = n - 1; i >= 1; i--) {
            int best = -1;

            // indexed loop over primitive array (fast)
            for (int idx = 0; idx < activeSize; idx++) {
                int u = active[idx];
                int v = u ^ i;
                if (v >= 1 && v <= n && !used[v]) {
                    best = v;
                    break;
                }
            }

            p[i] = best;
            used[best] = true;
            active[activeSize++] = best;
        }

        for (int i = 1; i <= n; i++) {
            if (i > 1) sb.append(' ');
            sb.append(p[i]);
        }
        sb.append('\n');
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            solveCase(n, sb);
        }

        System.out.print(sb.toString());
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buf = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do c = read(); while (c <= ' ');
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}