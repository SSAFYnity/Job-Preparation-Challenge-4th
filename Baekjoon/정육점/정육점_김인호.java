import java.io.*;
import java.util.*;

// 고기 정보 클래스
class MeatInfo implements Comparable<MeatInfo> {
    int weight; // 고기 무게
    int price;  // 고기 가격

    public MeatInfo(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    // 가격 오름차순으로 정렬, 가격이 같다면 무게의 내림차순으로 정렬
    @Override
    public int compareTo(MeatInfo other) {
        if (this.price == other.price) {
            return other.weight - this.weight; // 무게는 내림차순
        }
        return this.price - other.price; // 가격은 오름차순
    }
}

public class MeatShop {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int loafCount = Integer.parseInt(st.nextToken()); // 고기 덩어리 수
        long requiredMeat = Long.parseLong(st.nextToken()); // 필요한 총 고기 양

        MeatInfo[] meatInfos = new MeatInfo[loafCount];
        int totalWeight = 0; // 모든 고기 덩어리의 총 무게

        // 고기 정보 입력
        for (int i = 0; i < loafCount; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            meatInfos[i] = new MeatInfo(weight, price);
            totalWeight += weight;
        }

        // 필요한 고기 양이 전체 고기 양보다 많다면 불가능
        if (totalWeight < requiredMeat) {
            System.out.println(-1);
            return;
        }

        // 고기 정보 정렬
        Arrays.sort(meatInfos);

        int accumulatedWeight = 0; // 누적된 고기 무게
        int currentPriceSum = 0;   // 현재 가격 합계
        int minPrice = Integer.MAX_VALUE; // 최소 가격 초기화
        int previousPrice = -1; // 이전 가격 기억

        // 최소 가격 계산
        for (MeatInfo meat : meatInfos) {
            accumulatedWeight += meat.weight;

            // 새로운 가격 그룹 시작
            if (previousPrice != meat.price) {
                currentPriceSum = meat.price;
            } else {
                // 동일한 가격 그룹
                currentPriceSum += meat.price;
            }

            previousPrice = meat.price;

            // 필요한 고기 양을 만족하면 최소 가격 갱신
            if (accumulatedWeight >= requiredMeat) {
                minPrice = Math.min(minPrice, currentPriceSum);
            }
        }

        System.out.println(minPrice);
    }
}
