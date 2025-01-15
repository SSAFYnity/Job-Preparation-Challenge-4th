N = int(input()) # 최종적으로 가려는 번호
M = int(input()) # 고장난 버튼 개수
if M > 0:
    broken_button = list(input().split())
else:
    broken_button = []

ans = abs(100 - N) #  ++ or --로 이동할 경우(최대값)

# 작은수에서 큰수로 갈때는 500,000까지만 필요하지만
# 큰 수에서 작은수로 갈 수도 있으니깐 1,000,000까지 봐야 됨.

for num in range(1000001):
    for n in str(num):
        if n in broken_button: # 이 수를 번호를 눌러서 만들 수 없으면 패스
            break
    else: # num을 번호를 눌러서 만들 수 있다면
        ans = min(ans, len(str(num)) + abs(num - N))
print(ans)
