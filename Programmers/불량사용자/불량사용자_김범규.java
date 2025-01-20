import java.util.HashSet;

class Solution {
    String[] userId;
    String[] bannedId;
    HashSet<HashSet<String>> result = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        //깊은 복사를 통해 값 변경 막기
        userId = user_id;
        bannedId = banned_id;
        
        dfs(new HashSet<>(), 0);
        
        return result.size();
    }
    
    //dfs (set에 조합을 저장하기)
    public void dfs(HashSet<String> set, int depth){
        //금지 다 채웠다면
        if(depth == bannedId.length){
            result.add(set);
            return;
        }
        
        //유저 아이디를 순회하면서...
        for(int i = 0 ; i < userId.length; i++){
            //이미 이 유저아이디가 들어가있다면 패스
            if(set.contains(userId[i])){
                continue;
            }
            
            //아니라면 같은 문자열인지 보고 set에 삽입후 dfs
            if(check(userId[i], bannedId[depth])){
                set.add(userId[i]);
                dfs(new HashSet<>(set), depth + 1);
                //백트래킹
                set.remove(userId[i]);
            }
            
        }
    }
    
    public boolean check(String userId, String bannedId){
        //길이 체크
        if(userId.length() != bannedId.length()){
            return false;
        }
        
        //문자열이 맞는지 확인하기
        for(int i = 0; i < userId.length(); i++){
            if(bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)){
                return false;
            }
        }
        //다 통과하면 일치하는 문자열
        return true;
    }
}