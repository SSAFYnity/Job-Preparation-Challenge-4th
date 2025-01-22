import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedHashMap<String, Integer> cache = new LinkedHashMap<>(cacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > cacheSize;
            }
        };
        for (int i = 0; i < cities.length; i++) {
            String c = cities[i].toLowerCase();
            if (cache.containsKey(c)) {
                answer++;
                cache.put(c, i);
            } else {
                answer += 5;
                cache.put(c, i);
            }
        }
        return answer;
    }
}