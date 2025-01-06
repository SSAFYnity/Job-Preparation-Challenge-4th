import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length*5;
        }
        DatabaseServer dbServer = new DatabaseServer(cacheSize);
        int excutionTime = 0;
        for (String city : cities) {
            String cityLowerCase = city.toLowerCase();
            excutionTime += dbServer.getExcutionTime(cityLowerCase);
        }
        return excutionTime;
    }
}
class DatabaseServer {
    private Set<String> cacheData;
    private int capacity;

    public DatabaseServer(int capacity) {
        this.cacheData = new LinkedHashSet<>();
        this.capacity = capacity;
    }
    public int getExcutionTime(String city) {
        if (cacheData.contains(city)) {
            cacheHit(city);
            return 1;
        }
        cacheMiss(city);
        return 5;
    }

    private void cacheMiss(String city) {
        if (cacheData.size() >= capacity) {
            Iterator<String> cacheDataIter = cacheData.iterator();
            String oldestData = cacheDataIter.next();
            cacheData.remove(oldestData);
        }
        cacheData.add(city);
    }
    private void cacheHit(String city) {
        cacheData.remove(city);
        cacheData.add(city);
    }
}