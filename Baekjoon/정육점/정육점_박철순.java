import java.util.*;
import java.io.*;
 
public class Main_2258 {
	static int N; 
	static int targetWeight; 
	
	static class Meat implements Comparable<Meat> {
		int weight; 
		int cost;
		
		public Meat (int weight, int cost) {
			this.weight = weight; 
			this.cost = cost; 
		}
		
		@Override
		public int compareTo(Meat o) {
			// 가격 오름차순, 무게 내림차순 
			if (this.cost == o.cost) {
				return o.weight - this.weight;
			} 
			return this.cost - o.cost; 
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		PriorityQueue<Meat> pq = new PriorityQueue<>(); 

		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		targetWeight = Integer.parseInt(st.nextToken());
		
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pq.add(new Meat(weight, cost));
		}
		
		int totalWeight = 0; 
		long minCost = Long.MAX_VALUE; 
		int currCost = 0; 
		int prevCost = -1; 
		
		while(!pq.isEmpty()) {
			Meat meat = pq.poll(); 
			totalWeight += meat.weight; 
			
			if (meat.cost == prevCost) { 
				// 이전 고기 가격 = 현재 고기 가격
				currCost += meat.cost; 
			} else { 
				// 이전 고기 가격 < 현재 고기 가격 
				currCost = meat.cost; 
				prevCost = meat.cost;
			}
			
			if (totalWeight >= targetWeight) {
				minCost = Math.min(minCost, currCost);
			}
		}
		
		if (minCost == Long.MAX_VALUE) {
			minCost = -1; 
		}
		
		System.out.println(minCost);
	}

}
