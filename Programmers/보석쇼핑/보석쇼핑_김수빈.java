import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        HashMap<String, Boolean> jewels = new HashMap<>();
        for (String gem : gems) {
            jewels.putIfAbsent(gem, false);
        }

        int minSection = Integer.MAX_VALUE;
        int[] section = new int[2];
        for (int i = 0; i < gems.length; i++) {
            HashMap<String, Boolean> temp = new HashMap<String, Boolean>(jewels);
            int num = 0;
            for (int j = i; j < gems.length; j++) {
                num += 1;
                temp.replace(gems[j], true);
                boolean flag = true;
                for (boolean t : temp.values()) {
                    if (!t) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    if (minSection > num) {
                        minSection = num;
                        section[0] = i + 1;
                        section[1] = j + 1;
                    }
                }
            }
        }
        return section;
    }
}
