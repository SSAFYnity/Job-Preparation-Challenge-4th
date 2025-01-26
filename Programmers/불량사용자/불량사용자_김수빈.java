package Programmers;
import java.util.*;

public class 불량사용자_김수빈 {
    public static boolean isSame(String winner, String cheater) {
        // 정규식 변환
        String regex = cheater.replace("*", ".");
        return winner.matches(regex);
    }

    public static void backtrack(ArrayList<ArrayList<Integer>> sameCases, int idx, HashSet<Integer> selected, HashSet<HashSet<Integer>> uniqueComb) {
        if (idx == sameCases.size()) {
            uniqueComb.add(new HashSet<>(selected));
            return;
        }

        for (Integer user : sameCases.get(idx)) {
            if (selected.contains(user)) {
                continue;
            }
            selected.add(user);
            backtrack(sameCases, idx + 1, selected, uniqueComb);
            selected.remove(user);
        }
    }

    public static int solution(String[] user_id, String[] banned_id) {
        ArrayList<ArrayList<Integer>> sameCases = new ArrayList<>();
        for (int i = 0; i < banned_id.length; i++) {
            ArrayList<Integer> innerArr = new ArrayList<>();
            for (int j = 0; j < user_id.length; j++) {
                // 둘이 똑같은 거면 j를 넣기
                if (isSame(user_id[j], banned_id[i])) {
                    innerArr.add(j);
                }
            }
            sameCases.add(innerArr);
        }
        HashSet<HashSet<Integer>> uniqueComb = new HashSet<>();
        backtrack(sameCases, 0, new HashSet<Integer>(), uniqueComb);


        int answer = uniqueComb.size();
        return answer;
    }

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        int result = solution(user_id, banned_id);
        System.out.println(result);
    }
}
