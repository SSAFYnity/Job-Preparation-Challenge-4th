import sys
input = sys.stdin.readline

n, m = map(int, input().split())
meat = dict()
# 전체 무게 저장
total_weight = 0
for _ in range(n):
    # 각 고기 무게, 가격
    w, p = map(int, input().split())
    # 가격 기준으로 저장
    if p in meat:
        meat[p].append(w)
    else:
        meat[p] = [w]
    total_weight += w
# 최대 비용
max_val = 2147483648
ans = max_val
# 필요한 양이상 구할 수 있으면
if total_weight >= m:
    # 무게 누적
    sum_weight = 0
    # 가격 기준 오름차순 정렬 -> 싼 고기들은 얼마든지 덤으로 획득 가능
    for price in sorted(meat):
        # 같은 가격이 있다면, 무게 기준 내림차순 정렬 -> 같은 가격은 비용 추가
        meat[price].sort(reverse=True)
        # 같은 가격 개수 저장
        cnt = 0
        for weight in meat[price]:
            # 개수 증가
            cnt += 1
            # 무게 추가
            sum_weight += weight
            # 필요한 양 이상이면 탐색 종료
            if sum_weight >= m:
                # 같은 가격 두개 합한 것보다 그 다음 가격이 작을 수 있음
                ans = min(ans, price * cnt)
                # 같은 가격은 더 탐색할 필요없음 -> 현재 비용이 최소
                break
        # 한 개 가격이면 더 이상 비교할 필요 없음
        if cnt == 1 and ans != max_val:
            break
# 불가능하면
if ans == max_val:
    ans = -1
print(ans)
