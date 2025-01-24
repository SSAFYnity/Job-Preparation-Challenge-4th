import java.util.*;
import java.awt.Point;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> gemSet = new HashSet<>(List.of(gems));
        int gemCnt = gemSet.size();
        Map<String, Integer> statusMap = new HashMap<>();
        List<Point> list = new ArrayList<>();
        int s = 0;
        for (int e = 0; e < gems.length; e++) {
            statusMap.put(gems[e], statusMap.getOrDefault(gems[e], 0) + 1);
            if (statusMap.size() < gemCnt)
                continue;
            while (true) {
                list.add(new Point(s + 1, e + 1));
                s++;
                statusMap.put(gems[s - 1], statusMap.get(gems[s - 1]) - 1);
                if (statusMap.get(gems[s - 1]) == 0) {
                    statusMap.remove(gems[s - 1]);
                    break;
                }
            }
        }

        Collections.sort(list, (o1, o2) -> {
            if (o1.y - o1.x == o2.y - o2.x)
                return o1.x - o2.x;
            return (o1.y - o1.x) - (o2.y - o2.x);
        });

        int[] answer = new int[2];
        answer[0] = list.get(0).x;
        answer[1] = list.get(0).y;

        return answer;
    }
}