#include <string>
#include <vector>
#include <set>
using namespace std;
bool visited[9];
set<string>s;

void recur(int idx, vector<string>& user, vector<string>& banned) {
    if (idx == banned.size()) {
        string temp = "";
        for (int i = 0; i < user.size(); i++) {
            if (visited[i]) temp += to_string(i);
        }
        s.insert(temp);
        return;
    }
    // Ã£±â
    for (int i = 0; i < user.size(); i++) {
        bool flag = true;
        if (user[i].length() != banned[idx].length() || visited[i]) continue;
        for (int j = 0; j < user[i].length(); j++) {
            if (banned[idx][j] == '*') continue;
            if (user[i][j] != banned[idx][j]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            visited[i] = true;
            recur(idx + 1, user, banned);
            visited[i] = false;
        }
    }
}

int solution(vector<string> user_id, vector<string> banned_id) {
    int answer = 0;

    recur(0, user_id, banned_id);
    answer = s.size();

    return answer;
}