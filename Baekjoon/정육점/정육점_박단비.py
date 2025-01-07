# # 이 코드는 같은 가격의 고기에 대한 처리를 안함
# N, M = map(int, input().split())
# meat = []
# for _ in range(N):
#     gram, cost = map(int, input().split())
#     meat.append((gram, cost))
#
# meat.sort(key=lambda x : x[1])
#
# gram_arr = [0] * N
# gram_arr[0] = meat[0][0]
# for i in range(1, N):
#     gram_arr[i] = gram_arr[i-1] + meat[i][0]
#
# ans = 1e9
# for i in range(N):
#     if gram_arr[i] >= M:
#         ans = meat[i][1]
# if ans == 1e9:
#     print(-1)
# else:
#     print(ans)
#
# '''
# 3 3000
# 1000 5
# 1000 5
# 1000 5
# -> 15
# '''

# 가격(오름차순), 무게(내림차순) 순으로 정렬

# 고기의 무게를 더하면서 해당 고기가 이전에 탐색한 고기 가격이랑 일치하는지 확인

# 현재까지 고기 무게가 M 이상일 경우 정답 갱신

N, M = map(int, input().split())
meat = []
for _ in range(N):
    gram, cost = map(int, input().split())
    meat.append((gram, cost))

meat.sort(key=lambda x : (x[1], -x[0]))
ans = 2147483648
weight, same = 0, 0

for i in range(N):
    weight += meat[i][0]
    if i >= 1 and meat[i-1][1] == meat[i][1]:
        same += meat[i][1]
    else:
        same = 0

    if weight >= M:
        ans = min(ans, same+meat[i][1])
if ans == 2147483648:
    print(-1)
else:
    print(ans)