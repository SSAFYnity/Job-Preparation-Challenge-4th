N = int(input())
cookie = [list(map(str, input())) for _ in range(N)]


# 심장의 위치 반환, 심장은 머리 바로 아래에 있다.
def check_heart():
    for x in range(N):
        for y in range(N):
            if cookie[x][y] == "*":
                return [x+1, y]


def check_arms(h):
    left_arm, right_arm = 0, 0
    for y in range(N):
        if cookie[h[0]][y] == "*":
            if y < h[1]:
                left_arm += 1
            elif y > h[1]:
                right_arm += 1

    return left_arm, right_arm


def check_waist(h):
    waist = 0
    for x in range(h[0]+1, N):
        if cookie[x][h[1]] == "*":
            waist += 1
        else:
            waist_end = [x, h[1]]
            break
    return waist, waist_end

def check_legs(w):
    left_leg, right_leg = 0, 0
    for x in range(w[0], N):
        # 왼다리
        if cookie[x][w[1]-1] == "*":
            left_leg += 1
        # 오른다리
        if cookie[x][w[1]+1] == "*":
            right_leg += 1
    return left_leg, right_leg

heart = check_heart()
left_arm, right_arm = check_arms(heart)
waist, waist_end = check_waist(heart)
left_leg, right_leg = check_legs(waist_end)

body_len = [left_arm, right_arm, waist, left_leg, right_leg]

print(heart[0]+1, heart[1]+1)
print(" ".join(map(str, body_len)))


