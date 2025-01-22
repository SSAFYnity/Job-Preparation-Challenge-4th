#include <string>
#include <vector>

using namespace std;

int dp[26][26][4];
int dx[4] = { 0,0,1,-1 }; // 하 상
int dy[4] = { 1,-1,0,0 }; // 우 좌
int minCost = 1e9;

bool isRange(int x, int y, int n) {
    return x >= 0 && x < n&& y >= 0 && y < n;
}

void dfs(vector<vector<int>>& board, int x, int y, int cost, int dir) {
    if (minCost < cost) {
        return;
    }

    // 도착하면
    if (x == board.size() - 1 && y == board.size() - 1) {
        minCost = min(minCost, cost);
        return;
    }

    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        // 연산자 우선순위 잘 생각하기..
        if (isRange(nx, ny, board.size()) && board[nx][ny] == 0) {
            int nextCost = cost + 100;
            if ((dir == 0 || dir == 1) && (i == 2 || i == 3)) nextCost += 500;
            else if ((dir == 2 || dir == 3) && (i == 0 || i == 1)) nextCost += 500;
            if (dp[nx][ny][i] > nextCost) {
                dp[nx][ny][i] = nextCost;
                dfs(board, nx, ny, nextCost, i);
            }
        }
    }
}

int solution(vector<vector<int>> board) {
    int answer = 0;

    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board.size(); j++) {
            for (int k = 0; k < 4; k++) {
                dp[i][j][k] = 1e9;
            }
        }
    }

    dfs(board, 0, 0, 0, -1);
    answer = minCost;

    return answer;
}