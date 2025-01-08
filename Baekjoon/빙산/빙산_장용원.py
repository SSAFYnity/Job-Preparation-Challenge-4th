N, M = map(int, input().split())
iceberg = [list(map(int, input().split())) for _ in range(N)]

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]


def melt_iceberg(iceberg):
    melted = [[0] * M for _ in range(N)]
    for i in range(N):
        for j in range(M):
            if iceberg[i][j] > 0:
                for d in range(4):
                    ny, nx = i + dy[d], j + dx[d]
                    if N > ny >= 0 == iceberg[ny][nx] and 0 <= nx < M:
                        melted[i][j] += 1
    for i in range(N):
        for j in range(M):
            iceberg[i][j] = max(0, iceberg[i][j] - melted[i][j])


def is_separated(iceberg):
    visited = [[False] * M for _ in range(N)]
    found_ice = False

    def dfs(y, x):
        stack = [(y, x)]
        while stack:
            cy, cx = stack.pop()
            if visited[cy][cx]:
                continue
            visited[cy][cx] = True
            for d in range(4):
                ny, nx = cy + dy[d], cx + dx[d]
                if 0 <= ny < N and 0 <= nx < M and iceberg[ny][nx] > 0 and not visited[ny][nx]:
                    stack.append((ny, nx))

    for i in range(N):
        for j in range(M):
            if iceberg[i][j] > 0 and not visited[i][j]:
                if found_ice:  # 두 번째 빙산 발견 시 분리된 것으로 판단
                    return True
                dfs(i, j)
                found_ice = True
    return False


def all_melted(iceberg):
    return all(iceberg[i][j] == 0 for i in range(N) for j in range(M))


ans = 0
while True:
    if all_melted(iceberg):
        ans = 0
        break

    if is_separated(iceberg):
        break

    melt_iceberg(iceberg)
    ans += 1

print(ans)
