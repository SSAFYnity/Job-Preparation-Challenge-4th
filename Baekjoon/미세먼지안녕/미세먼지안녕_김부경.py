import sys
input = sys.stdin.readline

# 미세먼지 확산
def spread_dust():
    # 확산될 먼지 저장
    new_house = [[0]*c for _ in range(r)]
    # 먼지 위치 찾기
    for x in range(r):
        for y in range(c):
            # 먼지가 있으면 탐색
            if house[x][y] == 0:
                continue
            # 먼지 저장
            new_house[x][y] += house[x][y]
            # 청정기가 있으면 탐색 안함
            if house[x][y] == -1:
                continue
            # 확산될 먼지
            dust = house[x][y] // 5
            # 확산 될 양이 없으면 확산 안함
            if dust <= 0:
                continue
            # 확산
            for k in range(4):
                nx = x + di[k]
                ny = y + dj[k]
                if 0 <= nx < r and 0 <= ny < c and house[nx][ny] != -1:
                    new_house[nx][ny] += dust
                    new_house[x][y] -= dust
    return new_house

# 공기청정기 작동
def operate_air_purifier():
    # 공기청정기 작동 방향
    for d in ["UP", "DOWN"]:
        # 방향에 따른 경계
        sr, sc, er, ec = air_purifier_boundary[d]
        for x, y, k in air_purifier_dir[d]:
            # 한 방향씩 탐색
            while sr <= x + di[k] <= er and sc <= y + dj[k] <= ec:
                # 다음 위치가 공기청정기이면 0
                if house[x + di[k]][y + dj[k]] == -1:
                    house[x][y] = 0
                    break
                # 다음 위치 먼지 가져와 현재 위치에 덮어쓰기
                house[x][y] = house[x + di[k]][y + dj[k]]
                x += di[k]
                y += dj[k]

# 남아있는 먼지 출력
def count_dust():
    cnt = 0
    for x in range(r):
        for y in range(c):
            if house[x][y] > 0:
                cnt += house[x][y]
    return cnt


r, c, t = map(int, input().split())
house = [list(map(int, input().split())) for _ in range(r)]
air_purifier = []
for i in range(r):
    if house[i][0] == -1:
        air_purifier.append(i)
        if len(air_purifier) == 2:
            break
# 동 남 서 북
di = [0, 1, 0, -1]
dj = [1, 0, -1, 0]
apr1 = air_purifier[0]
apr2 = air_purifier[1]
# 공기청정기로 들어오는 위치부터 가동 반대 방향으로 탐색하면서 다음 위치 먼지 가져와 덮어쓰기
# 탐색을 시작할 위치, 탐색 방향 (청정기 이후 위치부터 잡기)
air_purifier_dir = {"UP": [(apr1-1, 0, 3), (0, 0, 0), (0, c-1, 1), (apr1, c-1, 2)], "DOWN": [(apr2+1, 0, 1), (r-1, 0, 0), (r-1, c-1, 3), (apr2, c-1, 2)]}
air_purifier_boundary = {"UP": (0, 0, apr1, c-1), "DOWN": (apr2, 0, r-1, c-1)}
for _ in range(t):
    house = spread_dust()
    operate_air_purifier()
print(count_dust())
