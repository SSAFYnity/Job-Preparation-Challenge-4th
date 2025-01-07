import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N덩어리의 고기
		// 덩어리의_개수 은혜가_필요한_고기의_양

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int total = 0;
		List<Item> items = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			// 무게의 총 합과 가격의 총합은 각각 2147483647을 넘지 x
			// cost 기준으로 정렬
			items.add(new Item(weight, cost));
			total += weight;
		}
		if (total < M) {
			System.out.println(-1);
			return;
		}
		Collections.sort(items);

		int min = Integer.MAX_VALUE;

		int before = items.get(0).c;
		int price = 0;
		int buyWeight = 0;
		for (int i = 0; i < N; i++) {
			buyWeight += items.get(i).w;
			if (before != items.get(i).c) {
				price = before = items.get(i).c;
			} else { // 같은 가격이면 무게 합침 -> 왜? 더 싼 경우에만 덤을 줌
				price += items.get(i).c;
			}
			if (buyWeight >= M) { // 구매하려는 고기 양을 충족하는가
				min = Math.min(min, price);
			}
		}
		System.out.println(min);

	}

	static class Item implements Comparable<Item> {
		int w, c;

		Item(int w, int c) {
			this.w = w;
			this.c = c;
		}

		@Override
		public int compareTo(Item o) { // 가격 저렴, 무게 무거운것
			if (this.c == o.c) {
				return o.w - this.w;
			}
			return this.c - o.c;
		}
	}

}
