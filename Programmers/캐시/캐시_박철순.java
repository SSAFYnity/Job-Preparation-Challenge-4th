import java.util.*;
import java.io.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {

        List<String> cache = new ArrayList<>(); 
        int result = 0; 
        
        // 캐시 사이즈 0이면 모두 Miss 
        if (cacheSize == 0) {
            return 5 * cities.length; 
        }
        
        for (String city : cities) {
            city = city.toLowerCase(); // 대소문자 구분 X 
            
            if (cache.contains(city)) { // 1. hit
                // 순서 최신화 
                cache.remove(city); 
                cache.add(city); 
                result += 1; 
            } else { // 2. miss 
                if (cache.size() < cacheSize) {
                    // 2-1. 캐시 공간 여유 있을 때 
                    cache.add(city); 
                } else {
                    // 2-2. 캐시 공간 여유 없을 때 
                    cache.remove(0); 
                    cache.add(city); 
                }
                result += 5; 
            }
        }
        return result; 
    }
    
}