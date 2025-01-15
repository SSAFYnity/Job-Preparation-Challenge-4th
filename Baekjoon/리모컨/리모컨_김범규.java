package Baekjoon.리모컨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 리모컨_김범규 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 이동하려는 채널
        N = Integer.parseInt(br.readLine());

        // 고장난 버튼이 있을경우
        M = Integer.parseInt(br.readLine());
        Set<Integer> broken = new HashSet<>();

        // 고장난 버튼이 있는 경우에만 수행
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 값 추가
            for (int i = 0; i < M; i++) {
                broken.add(Integer.parseInt(st.nextToken()));
            }
        }

        // 현재 채널이면 바로 끝
        if (N == 100) {
            System.out.println(0);
            return;
        } else {
            System.out.println(minClicks(100, N, broken));
        }
    }

    public static int minClicks(int current, int target, Set<Integer> broken) {

        // 업 다운만 하는 경우.
        int minClicks = Math.abs(target - current);

        // 숫자를 눌러 이동하는 경우
        for (int channel = 0; channel <= 999999; channel++) {
            if (checkBrokenButton(channel, broken)) {
                int clicks = String.valueOf(channel).length() + Math.abs(channel - target);
                minClicks = Math.min(minClicks, clicks);
            }
        }

        return minClicks;
    }

    // 부서진 번호 없이도 입력가능한지 확인
    public static boolean checkBrokenButton(int channel, Set<Integer> broken) {
        if (channel == 0) {
            return !broken.contains(0);
        }

        while (channel > 0) {
            int digit = channel % 10;
            if (broken.contains(digit)) {
                return false;
            }
            channel /= 10;
        }

        return true;
    }
}