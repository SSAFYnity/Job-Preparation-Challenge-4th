import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static List<Integer>[] map;
    static int[] arr;

    static boolean ok = true;

    static boolean dfs(int start) {
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.add(start);
        arr[start] = 1;

        while (!que.isEmpty()) {
            int now = que.poll();
            for (int i : map[now]) {
                if (arr[i] == arr[now]) return false;
                else if (arr[i] == 0) {
                    arr[i] = (~arr[now]) & 3;
                    que.add(i);
                }
            }
        }

        return true;
    }

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static void mapSetting(int one, int two) {
        map[one].add(two);
        map[two].add(one);
    }

    public static void main(String[] args) throws IOException {

        int T = read();

        for (int t = 0; t < T; t++) {
            int v = read();
            int e = read();
            arr = new int[v+1];
            map = new ArrayList[v+1];
            ok = true;
            for (int i = 1; i <= v; i++) map[i] = new ArrayList<>();
            for (int i = 0; i < e; i++) mapSetting(read(), read());

            for (int i = 1; i < v+1 && ok; i++) {
                if (arr[i] != 0) continue;
                ok &= dfs(i);
            }
            sb.append(ok ? "YES\n" : "NO\n");
        }

        System.out.println(sb.toString());
    }
}

