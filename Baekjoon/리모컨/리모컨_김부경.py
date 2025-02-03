n = int(input())
broken_button_cnt = int(input())
if broken_button_cnt:
    broken_button = set(input().split())
else:
    broken_button = set()
# 지금 보고 있는 채널은 100번
# 최소 버튼 누르는 횟수
ans = abs(100-n)
if n != 100:
    for channel in range(1000001):
        str_channel = str(channel)
        is_possible = True
        for button in str_channel:
            if button in broken_button:
                is_possible = False
                break
        if is_possible:
            ans = min(ans, len(str_channel) + abs(n-channel))
print(ans)
