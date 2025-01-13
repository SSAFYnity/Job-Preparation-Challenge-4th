import sys
input = sys.stdin.readline

# 테트로미노는 회전이나 대칭을 시켜도 된다 -> 4개의 정사각형을 4방향 탐색으로 4번 움직인 모양
def dfs(idx, x, y, ssum):
    global ans

    if idx == 4:
        if ans < ssum:
            ans = ssum
        return

    for k in range(4):
        nx = x + di[k]
        ny = y + dj[k]
        if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny]:
            # 방문 체크
            visited[nx][ny] = 1
            dfs(idx+1, nx, ny, ssum+graph[nx][ny])
            # ㅗ,ㅜ,ㅏ,ㅓ 모양은 깊이 2 위치에서 두 블록 탐색 -> (x, y) 위치에서 두번 탐색
            if idx == 2:
                dfs(idx + 1, x, y, ssum + graph[nx][ny])
            visited[nx][ny] = 0


n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
# 동 남 서 북
di = [0, 1, 0, -1]
dj = [1, 0, -1, 0]

ans = 0
# 방문 체크
visited = [[0]*m for _ in range(n)]
for i in range(n):
    for j in range(m):
        visited[i][j] = 1
        dfs(1, i, j, graph[i][j])
        visited[i][j] = 0
print(ans)
