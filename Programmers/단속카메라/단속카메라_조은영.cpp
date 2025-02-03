#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(vector<int>& v1, vector<int>& v2) {
    if (v1[1] == v2[1]) return v1[0] < v2[0];
    else return v1[1] < v2[1];
}

int solution(vector<vector<int>> routes) {
    int answer = 1;

    sort(routes.begin(), routes.end(), cmp);

    int position = routes[0][1];

    for (int i = 1; i < routes.size(); i++) {
        if (routes[i][0] > position) {
            position = routes[i][1];
            answer++;
        }
    }

    return answer;
}

// print(solution([[-2,-1], [1,2],[-3,0]])) #2
// print(solution([[0,0],])) #1
// print(solution([[0,1], [0,1], [1,2]])) #1
// print(solution([[0,1], [2,3], [4,5], [6,7]])) #4
// print(solution([[-20,-15], [-14,-5], [-18,-13], [-5,-3]])) #2
// print(solution([[-20,15], [-14,-5], [-18,-13], [-5,-3]])) #2
// print(solution([[-20,15], [-20,-15], [-14,-5], [-18,-13], [-5,-3]])) #2