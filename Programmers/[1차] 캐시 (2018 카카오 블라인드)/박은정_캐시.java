import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Solution {
	public int solution(int cacheSize, String[] cities) {
		int answer = 0;
		// city이름,
		if (cacheSize == 0) {
			return cities.length * 5;
		}
		Map<String, Integer> caches = new HashMap<>();
		for (int i = 0; i < cities.length; i++) {
			String city = cities[i].toLowerCase();
			if (caches.keySet().contains(city)) { // cache hit
				answer += 1;
			} else { // cache miss
				if (caches.keySet().size() == cacheSize) {
					// i가 가장 작은 거 빼내야 함
					int minI = Integer.MAX_VALUE;
					String minKey = "";
					Iterator<Map.Entry<String, Integer>> entry = caches.entrySet().iterator();
					while (entry.hasNext()) {
						Map.Entry<String, Integer> element = entry.next();
						if (minI > element.getValue()) {
							minI = element.getValue();
							minKey = element.getKey();
						}
					}
					caches.remove(minKey);
				}
				answer += 5;
			}
			caches.put(city, i);
		}
		return answer;
	}
}