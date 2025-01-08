from collections import deque
import sys
input = sys.stdin.readline
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

time = 0

def solve(i, j):
    # 빙하 찾기
    q = deque()
    q.append((i, j))
    ice = [(i, j)] # 녹아야 할 빙하들 위치
    melt = [] # 각 빙하별 녹아야하는 수
    visited[i][j] = True
    while q:
        r, c = q.popleft()
        cnt = 0 # 주변 얼음 개수
        for k in range(4):
            nr = r + dr[k]
            nc = c + dc[k]
            if 0 <= nr < N and 0 <= nc < M:
                if not visited[nr][nc] and board[nr][nc] > 0:
                    q.append((nr, nc))
                    ice.append((nr, nc))
                    visited[nr][nc] = True
                elif board[nr][nc] == 0:
                    cnt += 1
        melt.append(cnt)

    # 빙하 녹이기
    for idx in range(len(ice)):
        r, c = ice[idx][0], ice[idx][1]
        board[r][c] -= melt[idx]
        if board[r][c] < 0:
            board[r][c] = 0


while True:
    cnt_bing = 0 # 빙하 덩어리 개수
    visited = [[False] * M for _ in range(N)]
    for i in range(N):
        for j in range(M):
            if board[i][j] > 0 and not visited[i][j]:
                cnt_bing += 1
                solve(i, j)

    if cnt_bing >= 2:
        print(time)
        exit()
    elif cnt_bing == 0:
        print(0)
        exit()
    else:
        time += 1