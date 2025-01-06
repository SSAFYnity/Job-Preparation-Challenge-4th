import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
                if (cacheSize == 0)
            return 5 * cities.length;
        int answer = 0;
        List<String> cache = new ArrayList<>();
        for (String city : cities) {
            String lowCity = city.toLowerCase();
            if (cache.contains(lowCity)) {
                cache.remove(lowCity);
                cache.add(lowCity);
                answer++;
            } else {
                if (cache.size() >= cacheSize)
                    cache.remove(0);
                cache.add(lowCity);
                answer += 5;
            }
        }
        return answer;
    }
}