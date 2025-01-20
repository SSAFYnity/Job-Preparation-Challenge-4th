import java.util.*;
import java.io.*;
class Solution {
    static boolean [] isVisitList;
    static String [] banIdList, userIdList;
    static HashSet<HashSet<String>> answerSet = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        isVisitList = new boolean[user_id.length];
        banIdList = banned_id;
        userIdList = user_id;
        HashSet<String> set = new HashSet<>();
        combination(0,set);
        return answerSet.size();
    }

    //조합으로 돌린다.
    static void combination(int depth, HashSet<String> set ){
        if(depth == banIdList.length){ //탐색 종료하고 정답 리스트 비교
            answerSet.add(set);
            return;
        }
        //탐색 
        for(int i = 0; i<userIdList.length; i++){
            if(set.contains(userIdList[i])) continue;
            if(canBanUser(userIdList[i], banIdList[depth])){
                set.add(userIdList[i]);
                combination(depth+1,new HashSet<>(set));
                set.remove(userIdList[i]);
            }
        }
    }

    //조건에 해당하는지 확인하는 함수 
    static boolean canBanUser(String userId,String banId){
        if(banId.length() != userId.length()) return false;
        for(int i = 0; i<userId.length(); i++){
            if(banId.charAt(i)!= '*' && banId.charAt(i) != userId.charAt(i)) return false;
        }
        return true;
    }
}
