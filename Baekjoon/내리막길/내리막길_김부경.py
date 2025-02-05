import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def dfs(x, y):
    # 목표 지점에 도착하면 1(경우의 수) 반환
    if x == m-1 and y == n-1:
        return 1
    # 방문한 적 있다면 해당 위치에 저장된 목표 지점 도착 경우의 수 반환
    if visited[x][y] != -1:
        return visited[x][y]

    # 방문 체크
    visited[x][y] = 0

    for k in range(4):
        nx = x + dx[k]
        ny = y + dy[k]
        if 0 <= nx < m and 0 <= ny < n and mountains[x][y] > mountains[nx][ny]:
            visited[x][y] += dfs(nx, ny)

    return visited[x][y]


# 세로: M, 가로: N
m, n = map(int, input().split())
mountains = [list(map(int, input().split())) for _ in range(m)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
visited = [[-1]*n for _ in range(m)]
print(dfs(0, 0))
