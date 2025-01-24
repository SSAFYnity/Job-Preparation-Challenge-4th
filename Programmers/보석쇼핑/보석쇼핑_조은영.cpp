#include <string>
#include <vector>
#include <set>
#include <unordered_map>
using namespace std;

vector<int> solution(vector<string> gems) {
    vector<int> answer;
    set<string>s;
    unordered_map<string, int>temp;

    int start = 0, end = 0, len = 1e9;
    int startAns = 1e9, endAns = 0;

    for (int i = 0; i < gems.size(); i++) {
        s.insert(gems[i]);
    }


    while (end < gems.size()) {
        temp[gems[end]]++;
        while (s.size() == temp.size()) {
            // -> len 추가해서 수정
            if (end - start + 1 < len) {
                startAns = start;
                endAns = end;
                len = end - start + 1;
            }
            temp[gems[start]]--;
            if (temp[gems[start]] == 0) temp.erase(gems[start]);
            start++;
        }
        end++;
    }

    answer.push_back(startAns + 1);
    answer.push_back(endAns + 1);

    return answer;
}