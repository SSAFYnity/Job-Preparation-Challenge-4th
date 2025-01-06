import java.util.*;
import java.io.*;

public class Solution {

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> list = new ArrayList<>();

        if(cacheSize == 0) return cities.length*5;

        for (int i = 0; i < cities.length; i++) {
            String temp = cities[i].toLowerCase();

            if(list.contains(temp)) {
                list.remove((temp));
                list.add(temp);
                answer +=1;
            }else{
                if(list.size()==cacheSize){
                    list.remove(0);
                }
                list.add(temp);
                answer+=5;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int s1 = solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
        int s2 = solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});
        int s3 = solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
        int s4 = solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});

    }
}