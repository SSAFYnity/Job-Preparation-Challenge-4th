# 4방향 탐색으로 빙산 녹이기
def melt_iceberg():
    global ocean, icebergs
    # 빙산의 변경 정보 저장
    iceberg_info = []

    for x, y in icebergs:
        # 동서남북으로 붙어있는 0개수 저장
        water = 0
        # 4방향 탐색
        for k in range(4):
            nx = x + dx[k]
            ny = y + dy[k]
            if 0 <= nx < n and 0 <= ny < m and ocean[nx][ny] == 0:
                water += 1
        # 녹는 빙산 정보 저장
        if water > 0:
            iceberg_info.append((x, y, water))
    # 0개수 만큼 녹이기
    for x, y, w in iceberg_info:
        if ocean[x][y] > w:
            ocean[x][y] -= w
            continue
        # 높이는 0보다 더 줄지 않음
        ocean[x][y] = 0
        # 빙산 다 녹음
        icebergs.discard((x, y))


# 연결된 빙산 개수 찾기
def count_iceberg_bfs(x, y):
    global visited

    stack = [(x, y)]
    visited.add((x, y))

    while stack:
        x, y = stack.pop()
        # 4방향 탐색
        for k in range(4):
            nx = x + dx[k]
            ny = y + dy[k]
            if 0 <= nx < n and 0 <= ny < m and ocean[nx][ny] and (nx, ny) not in visited:
                visited.add((nx, ny))
                stack.append((nx, ny))


n, m = map(int, input().split())
icebergs = set()
ocean = []
for i in range(n):
    row = list(map(int, input().split()))
    for j in range(m):
        if row[j] != 0:
            icebergs.add((i, j))
    ocean.append(row)

ans = 0
year = 0
# 동서남북
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
while icebergs:
    year += 1
    # 빙산 녹이기
    melt_iceberg()
    # 빙산 덩어리 개수 세기
    iceberg_cnt = 0
    # 방문 체크
    visited = set()
    for i, j in icebergs:
        if (i, j) not in visited:
            # 빙산 개수 증가
            iceberg_cnt += 1
            # 이미 빙산이 있으면 2개 이상 확정
            if iceberg_cnt > 1:
                break
            count_iceberg_bfs(i, j)
    #  두 덩어리 이상으로 분리
    if iceberg_cnt >= 2:
        ans = year
        break
print(ans)
