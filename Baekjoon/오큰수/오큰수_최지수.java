import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static StringBuilder sb = new StringBuilder();

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {

        n = read();

        int[] arr = new int[n];
        int[] stack = new int[n];
        int[] nge = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = read();
            nge[i] = -1;
        }

        int now = 0;
        int top = 0;
        while (now < n) {
            if (top == 0) stack[top++] = now;
            else if (arr[now] > arr[stack[top-1]]) {
                nge[stack[--top]] = arr[now];
                while (top > 0) {
                    if (arr[now] > arr[stack[top-1]]) {
                        nge[stack[--top]] = arr[now];
                    } else break;
                }
                stack[top++] = now;
            } else {
                stack[top++] = now;
            }
            now++;
        }

        for (int i = 0; i < n; i++) {
            sb.append(nge[i] + " ");
        }

        System.out.println(sb.toString());
    }
}

