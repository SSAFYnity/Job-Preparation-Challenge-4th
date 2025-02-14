import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 덩어리의 개수
        int M = Integer.parseInt(st.nextToken());   // 필요한 고기의 양

        Map<Integer, PriorityQueue<Integer>> meatMap = new HashMap<>();
        Map<Integer, Integer> costMap = new HashMap<>();
        Map<Integer, Integer> maxWeightMap = new HashMap<>();
        Set<Integer> costSet = new HashSet<>();
        int totalWeight = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (costMap.containsKey(c))
                costMap.put(c, costMap.get(c) + w);
            else
                costMap.put(c, w);
            if (maxWeightMap.containsKey(c)) {
                if (maxWeightMap.get(c) < w)
                    maxWeightMap.put(c, w);
            } else
                maxWeightMap.put(c, w);
            meatMap.computeIfAbsent(c, k -> new PriorityQueue<>(Collections.reverseOrder())).add(w);
            costSet.add(c);
            totalWeight += w;
        }
        
        if (totalWeight < M) {
            System.out.println(-1);
            return;
        }

        List<Integer> costList = new ArrayList<>(costSet);
        Collections.sort(costList);
        int weight = 0;
        for (int i = 0; i < costList.size(); i++) {
            int cost = costList.get(i);
            if (weight + maxWeightMap.get(cost) >= M) {
                // 더 작은 가격으로도 가능한지 확인
                if (i != 0) {
                    int tempCost = costList.get(i - 1);
                    while (!meatMap.get(tempCost).isEmpty()) {
                        weight += meatMap.get(tempCost).poll();
                        cost += tempCost;
                        if (weight >= M) {
                            System.out.println(cost - tempCost);
                            return;
                        }
                    }
                }
                System.out.println(cost);
                return;
            }

            if (i == costList.size() - 1) {
                int tempCost = cost;
                while (!meatMap.get(tempCost).isEmpty()) {
                    weight += meatMap.get(tempCost).poll();
                    cost += tempCost;
                    if (weight >= M) {
                        System.out.println(cost - tempCost);
                        return;
                    }
                }
                break;
            }
            weight += costMap.get(cost);
        }

        System.out.println(-1);
    }
}