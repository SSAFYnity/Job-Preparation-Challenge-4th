from collections import deque
import sys
input = sys.stdin.readline
R, C, T = map(int, input().split())
board = []
machine = [] # 공기청정기 위치
for i in range(R):
    arr = list(map(int, input().split()))
    for j in range(C):
        if arr[j] == -1:
            machine.append(i)
    board.append(arr)

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def spread():
    q = deque()
    for i in range(R):
        for j in range(C):
            if board[i][j] > 0:
                q.append((i, j, board[i][j] // 5))
    while q:
        r, c, dust = q.popleft()
        for k in range(4):
            nr = r + dr[k]
            nc = c + dc[k]
            if 0 <= nr < R and 0 <= nc < C and board[nr][nc] != -1:
                board[nr][nc] += dust
                board[r][c] -= dust

def clean():
    # 위쪽 공기청정기 작동
    for i in range(machine[0] - 1, 0, -1):
        board[i][0] = board[i - 1][0]
    for j in range(C - 1):
        board[0][j] = board[0][j + 1]
    for i in range(machine[0]):
        board[i][C - 1] = board[i + 1][C - 1]
    for j in range(C - 1, 1, -1):
        board[machine[0]][j] = board[machine[0]][j - 1]
    board[machine[0]][1] = 0

    # 아래쪽 공기청정기 작동
    for i in range(machine[1] + 1, R - 1):
        board[i][0] = board[i + 1][0]
    for j in range(C - 1):
        board[R - 1][j] = board[R - 1][j + 1]
    for i in range(R - 1, machine[1], -1):
        board[i][C - 1] = board[i - 1][C - 1]
    for j in range(C - 1, 1, -1):
        board[machine[1]][j] = board[machine[1]][j - 1]
    board[machine[1]][1] = 0

time = 0
while time < T:
    spread()

    clean()

    time += 1

ans = 0
for i in range(R):
    for j in range(C):
        if board[i][j] > 0:
            ans += board[i][j]
print(ans)