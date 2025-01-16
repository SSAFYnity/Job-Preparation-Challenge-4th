# 전파가 전달 안되는 구간을 리스트에 넣고 w 범위로 나눠서 개수 세기
def solution(n, stations, w):
    answer = 0
    no_light = []  # 전파 전달 안되는 구간 길이 담을 배열
    no_light.append(stations[0] - w - 1)  # 맨앞 기지국
    for i in range(1, len(stations)):
        no_light.append((stations[i] - w - 1) - (stations[i - 1] + w + 1) + 1)

    no_light.append(n - (stations[-1] + w))

    for dist in no_light:
        if dist <= 0:
            continue
        else:
            answer += (dist // (2 * w + 1))
            if (dist % (2 * w + 1)):
                answer += 1

    return answer