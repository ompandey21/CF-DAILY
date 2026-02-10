import java.io.*;

public class Main {

    static final int NOISE_CONST = 42; // noise

    static int pickFirst(int n) {
        // noise wrapper
        return (n % 2 == 0) ? n : (n - 1);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int[] p = new int[n + 1];

            p[n] = 1;

            for (int i = 2; i <= n - 1; i++) {
                p[i] = i ^ 1;
            }

            p[1] = pickFirst(n);

            // noise: never triggered
            if (NOISE_CONST == -100) out.append("");

            for (int i = 1; i <= n; i++) {
                out.append(p[i]).append(i == n ? '\n' : ' ');
            }
        }

        System.out.print(out.toString());
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
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