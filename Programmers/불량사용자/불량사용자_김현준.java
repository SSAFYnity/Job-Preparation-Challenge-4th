import java.util.*;

class Solution {
    static List<Set<String>> firstSet = new ArrayList<>();
    static Set<Set<String>> finalSet = new HashSet<>();
    static int ans = 0;
    static boolean[] v;
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 1;
        // 가능한 모든 경우를 탐색
        for(int i=0;i<banned_id.length;i++) {
            char[] c = banned_id[i].toCharArray();
            Set<String> set = new HashSet<>();
            for(int j=0;j<user_id.length;j++) {
                int cnt = 0;
                for(int k=0;k<user_id[j].length();k++) {
                    // k 가 banned_id 보다 갯수가 많으면 x
                    if(k > c.length - 1) break;
                    if(c[k] == '*' || c[k] == user_id[j].charAt(k)) {
                        cnt++;
                    }

                    if(cnt == c.length && k == user_id[j].length()-1) {
                        set.add(user_id[j]);
                    }
                }
            }
            firstSet.add(set);

        }

        System.out.println(firstSet.toString());
        // 여기서 dfs로 순서 상관없이 조회가능한 갯수 체크
        // v = new boolean[set.size()];
        dfs(0, new HashSet<>(), banned_id.length);
        // System.out.println(finalSet.toString());
        return finalSet.size();
    }

    public static void dfs(int idx, Set<String> current, int size) {
        // basis
        if(idx == size) {
            finalSet.add(new HashSet<>(current));
            return;
        }

        for(String user : firstSet.get(idx)) {
            if(!current.contains(user)) {
                current.add(user);
                dfs(idx + 1, current, size);
                current.remove(user);
            }
        }
    }
}