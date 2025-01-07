import java.util.*;
import java.io.*;

public class 정육점_김수빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 그 덩어리보다 싼 고기들을 덤으로 얻을 수 있음
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);  // 덩어리 개수
        int M = Integer.parseInt(input[1]);  // 필요 고기 양
        int[][] meats = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] meat = br.readLine().split(" ");
            meats[i][0] = Integer.parseInt(meat[0]);  // 고기 덩어리 무게
            meats[i][1] = Integer.parseInt(meat[1]);  // 고기 덩어리 가격
        }
        // 고기 가격 오름차순 정렬
        // 고기 크기 내림차순 정렬
        Arrays.sort(meats, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return b[0] - a[0];
            }
        });

        int minCost = 2147483647;  // 최소 가격
        int standardPrice = -1;  // 기준 가격
        int totalWeight = 0;  // 총 무게
        int totalCost = 0;  // 총 지불금액
        boolean flag = false;

        for (int[] meat : meats) {
            int currWeight = meat[0];
            int currCost = meat[1];
            // 이전 가격보다 현재 가격이 높다면
            if (standardPrice < currCost) {
                standardPrice = currCost;  // 기준 가격 교체
                totalCost = currCost;  // 총 가격 교체
            } else {
                totalCost += currCost;  // 총 가격
            }
            // 총 무게 증가
            totalWeight += currWeight;
            // 총 무게가 만약 기준 무게보다 무겁다면
            if (totalWeight >= M) {
                flag = true;  // 플래그 변수 교체
                // 최소 금액보다 총 금액이 작다면
                if (minCost > totalCost) {
                    minCost = totalCost;
                }
            }
        }
        // 최소가격 변경이 있다면 플래그 변수 변화 있음
        if (flag) {
            System.out.println(minCost);
        } else {
            System.out.println(-1);
        }
    }
}
