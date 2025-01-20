import java.util.HashSet;

class Solution {
    String[] userIds;
    String[] bannedIds;
    boolean[] visited;
    HashSet<HashSet<String>> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        userIds = user_id;
        bannedIds = banned_id;
        visited = new boolean[userIds.length];
        
        findBannedCombinations(new HashSet<>(), 0);
        
        return result.size();
    }

    void findBannedCombinations(HashSet<String> selectedUsers, int depth) {
        if (depth == bannedIds.length) {
            result.add(new HashSet<>(selectedUsers));
            return;
        }

        for (int i = 0; i < userIds.length; i++) {
            if (visited[i] || !isMatch(userIds[i], bannedIds[depth])) 
                continue;

            visited[i] = true;
            selectedUsers.add(userIds[i]);
            findBannedCombinations(selectedUsers, depth + 1);
            selectedUsers.remove(userIds[i]);
            visited[i] = false;
        }
    }

    boolean isMatch(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) 
            return false;

        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
}
