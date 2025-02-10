N = int(input())
nums = list(map(int, input().split()))
stack = [] # 인덱스 저장
ans = [-1] * N
stack.append(0)
for i in range(1, N):
    while stack and nums[stack[-1]] < nums[i]:
        ans[stack[-1]] = nums[i]
        stack.pop()
    stack.append(i)

print(*ans)