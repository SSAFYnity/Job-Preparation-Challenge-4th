def dragon_curve(i, j, direction, generation):
    # 시작점 방문 체크
    graph[i][j] = 1
    # 움직인 방향 저장
    directions = []
    # 0세대 움직임
    if 0 <= i + di[direction] <= 100 and 0 <= j + dj[direction] <= 100:
        # 0세대 이동
        i += di[direction]
        j += dj[direction]
        # 이동 방향 저장
        directions.append(direction)
        # 이동 위치 표시
        if not graph[i][j]:
            graph[i][j] = 1

    # 1세대 부터 움직임 탐색
    for ng in range(generation):
        # 이때 까지 움직인 횟수
        m = len(directions)
        # ng+1 세대의 이동
        # 이전 세대의 끝 움직임부터 회전하여 붙임
        for k in range(m-1, -1, -1):
            # 시계 방향으로 90도 회전시킨 방향
            nk = (directions[k]+1) % 4
            if 0 <= i + di[nk] <= 100 and 0 <= j + dj[nk] <= 100:
                i += di[nk]
                j += dj[nk]
                directions.append(nk)
                if not graph[i][j]:
                    graph[i][j] = 1


def count_squares():
    squares = 0
    for i in range(100):
        for j in range(100):
            if graph[i][j] and graph[i][j + 1] and graph[i + 1][j + 1] and graph[i + 1][j]:
                squares += 1
    return squares


n = int(input())
graph = [[0]*101 for _ in range(101)]
# 동 북 서 남
di = [0, -1, 0, 1]
dj = [1, 0, -1, 0]

for _ in range(n):
    # x와 y는 드래곤 커브의 시작 점, d는 시작 방향, g는 세대
    # x: x축(열), y: y축(행)
    x, y, d, g = map(int, input().split())
    # 행위치, 열위치, 시작 방향, 탐색 세대
    dragon_curve(y, x, d, g)
print(count_squares())
