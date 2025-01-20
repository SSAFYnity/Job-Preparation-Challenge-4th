import java.util.*;
import java.util.regex.*;

class Solution {
    Set<Integer> set = new HashSet<>();
    
    boolean isUsed(int bit, int idx) { return (bit & (1 << idx)) != 0; }
    int use(int bit, int idx) { return bit | (1 << idx); }
    int clear(int bit, int idx) { return bit & ~(1 << idx); }
    
    void back(ArrayList<Integer>[] canChoose, int bidx, int bit) {
        if (bidx == canChoose.length) {
            set.add(bit);
            return;
        }
        
        for (int i : canChoose[bidx]) {
            if (isUsed(bit, i)) continue;
            back(canChoose, bidx+1, use(bit, i));
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        int ulen = user_id.length;
        int blen = banned_id.length;
        for (int i = 0; i < blen; i++) {
            banned_id[i] = banned_id[i].replaceAll("\\*", "\\.");
        }
        
        ArrayList<Integer>[] canChoose = new ArrayList[blen];
        for (int i = 0; i < blen; i++) {
            canChoose[i] = new ArrayList<>();
            for (int j = 0; j < ulen; j++) {
                if (Pattern.matches(banned_id[i], user_id[j])) canChoose[i].add(j);
            }
            // System.out.println(canChoose[i]);
        }
        
        back(canChoose, 0, 0);
        
        return set.size();
    }
}