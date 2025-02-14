from collections import deque

# 물, 고슴도치 -> 돌 X
# 고슴도치 -> 물 X
# 물 -> 비버 집 X

# 물이 못 가는 곳 : 돌, 비버 집
# 고슴도치가 못 가는 곳 : 돌, 물

# 물부터 큐에 입력하기
R, C = map(int, input().split())

board = []
water =[]

for i in range(R):
    line = list(input())
    board.append(line)
    for j in range(C):
        if line[j] == 'D':
            e_r = i
            e_c = j
        if line[j] == 'S':
            s_r = i
            s_c = j
        if line[j] == '*':
            water.append((i, j))

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
ans = -1 # 비버 굴로 갈 수 있는지 판단하기 위해서 음수로 두기

# 물인 곳은 처음부터 다 q에 넣고, 방문 처리하고 시작
q = deque()
visited = [[False] * C for _ in range(R)]
for wr, wc in water:
    q.append((wr, wc, 0))
    visited[wr][wc] = True

def bfs(q):
    global ans
    q.append((s_r, s_c, 0))
    visited[s_r][s_c] = True
    while q:
        r, c, time = q.popleft()
        if r == e_r and c == e_c:
            ans = time
        for k in range(4):
            nr = r + dr[k]
            nc = c + dc[k]
            if 0 <= nr < R and 0 <= nc < C:
                if board[r][c] == '*': # 물인 경우
                    # 돌, 비버집이 아닌 경우. 빈 공간일 경우만 이동
                    if board[nr][nc] == '.' and not visited[nr][nc]:
                        q.append((nr, nc, time+1))
                        visited[nr][nc] = True
                        board[nr][nc] = '*'
                elif board[r][c] == 'S': # 고슴도치인 경우
                    # 돌, 물이 아닌 경우. 빈 공간이거나 비버 집인 경우만 이동
                    if (board[nr][nc] == '.' or board[nr][nc] == 'D') and not visited[nr][nc]:
                        q.append((nr, nc, time+1))
                        visited[nr][nc] = True
                        board[nr][nc] = 'S'

bfs(q)
if ans == -1:
    print("KAKTUS")
else:
    print(ans)