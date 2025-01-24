import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        //모든 종류의 스트링과 갯수를 저장
        HashMap<String,Integer> hash = new HashMap<>();
        Set<String> setSize = new HashSet<>();
        for(String token : gems){
            setSize.add(token);
        }
        int kindSize = setSize.size();
        int lt = 0;
        int resultLt = 0;
        int resultRt = 0;
        int minGap = Integer.MAX_VALUE;
        for(int rt = 0; rt<gems.length; rt++){
            hash.put(gems[rt],hash.getOrDefault(gems[rt],0)+1);
            while(hash.get(gems[lt])>1){
                int tmpValue = hash.get(gems[lt]);
                hash.put(gems[lt], tmpValue - 1);
                lt++;
            }
            if(kindSize == hash.size() && minGap > (rt - lt)){
                minGap = rt - lt;
                resultRt = rt+1;
                resultLt = lt+1;
            }
        }
        int[] answer = {resultLt,resultRt};
        return answer;
    }
}
