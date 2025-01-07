import java.io.*;
import java.util.*;

public class Main {

    public static class Meat implements Comparable<Meat> {
        int w;
        int c;

        public Meat(int w, int c) {
            this.w = w;
            this.c = c;
        }

        @Override
        public int compareTo(Meat o) {
            if (this.c == o.c) {
                return o.w - this.w;
            } else {
                return this.c - o.c;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Meat> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new Meat(w,c));
        }

        Collections.sort(list);

        int totalWeight = list.get(0).w;
        int totalPrice = list.get(0).c;

        int result = Integer.MAX_VALUE;

        boolean flag = false;

        for (int i = 1, size = list.size(); i < size; i++) {
            if (list.get(i).c == list.get(i - 1).c) {
                totalPrice += list.get(i).c;
            } else {
                totalPrice = list.get(i).c;
            }

            totalWeight += list.get(i).w;

            if (totalWeight >= m) {
                flag = true;
                result = Math.min(result, totalPrice);
            }
        }

        System.out.println(flag ? result : -1);

    }
}