import java.util.*; 
import java.util.regex.*; 

class Solution {
    String[] userIds; 
    String[] bannedIds; 
    Set<HashSet<String>> bannedUserSet;
    
    private void dfs (HashSet<String> userSet, int depth) {
        if (depth == bannedIds.length) {
            bannedUserSet.add(userSet); 
            return; 
        }
        
        for (int i=0; i<userIds.length; i++) {
            if (userSet.contains(userIds[i])) continue; 
            
            if (isContain2(userIds[i], bannedIds[depth])) {
                userSet.add(userIds[i]); 
                dfs(new HashSet<>(userSet), depth+1);
                userSet.remove(userIds[i]); 
            }
        }
    }
    
    private boolean isContain (String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false; 
        }
        
        for (int i=0;i<userId.length(); i++) {
            if (bannedId.charAt(i) != '*' && bannedId.charAt(i) != userId.charAt(i)) {
                return false; 
            }
        }
        return true; 
    }

    private boolean isContain2 (String userId, String bannedId) {
        Pattern p = Pattern.compile(bannedId.replace('*', '.'));
        Matcher m = p.matcher(userId);
        return m.matches();
    }
    
    public int solution(String[] userIds, String[] bannedIds) {
        this.userIds = userIds; 
        this.bannedIds = bannedIds; 
        bannedUserSet = new HashSet<>();
        
        dfs(new HashSet<String>(), 0); 

        return bannedUserSet.size(); 
        
    }
}
