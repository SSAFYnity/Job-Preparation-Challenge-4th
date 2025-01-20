import java.util.*;
class Solution {
    private int answer = 0;
    private String[] userIds;
    private List<List<String>> candidates = new ArrayList<>();
    public int solution(String[] userIds, String[] bannedIds) {
        this.userIds = userIds;
        List<String> candidate = new ArrayList<>();
        boolean[] visited = new boolean[userIds.length];
        executePermutations(candidate, visited, bannedIds.length);
        return getBanList(bannedIds).size();

    }
    private Set<Set<String>> getBanListSize(String[] bannedIds) {
        Set<Set<String>> banList = new HashSet<>();
        for (List<String> candidate : candidates) {
            String[] candiArr = candidate.toArray(new String[0]);
            if (isBanned(candiArr, bannedIds)) {
                banList.add(new HashSet<>(candidate));
            }
        }
        return banList;
    }
    private boolean isBanned(String[] candidate, String[] bannedId) {
        for (int i = 0; i < bannedId.length; i++) {
            if (candidate[i].length() != bannedId[i].length()) {
                return false;
            }
            for (int j = 0; j < candidate[i].length(); j++) {
                if (bannedId[i].charAt(j) == '*' ||
                        bannedId[i].charAt(j) == candidate[i].charAt(j)) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private void executePermutations(List<String> current, boolean[] visited, int size) {
        if (current.size() == size) {
            candidates.add(new ArrayList<>(current));
            return;
        }
        for (int index = 0; index < userIds.length; index++) {
            if (!visited[index]) {
                visited[index] = true;
                current.add(userIds[index]);
                executePermutations(current, visited, size);
                current.remove(current.size() - 1);
                visited[index] = false;
            }
        }
    }
}