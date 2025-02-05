# DFS + DP
# DP가 되는 이유 : 임의의 점(x, y)에서 도착점까지 가는 경우의 수를 구하면 그 이전에 어떤 경로로 (x, y)에 도착하기만 하면 그때부터의 경우의 수는 다시 안구해도 됨
# 즉, 도착 지점까지 가는 경우의 수는 도착지점이 아닌 임의의 점들에서 도착지점까지 가는 경우의 수를 합한거랑 같아짐.

import sys
sys.setrecursionlimit(10**9)
M, N = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(M)]
dp = [[-1]*N for _ in range(M)]
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def dfs(r, c):
    if r == M-1 and c == N-1:
        return 1

    # 이미 방문한 곳이면면
    if dp[r][c] != -1:
        return dp[r][c]

    # 방문하지 않았다면
    dp[r][c] = 0
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]
        if 0 <= nr < M and 0 <= nc < N:
            if graph[r][c] > graph[nr][nc]:
                dp[r][c] += dfs(nr, nc)
    return dp[r][c]

print(dfs(0, 0))