# 이분그래프 판단 : 모든 정점의 양 끝이 다른 곳에 속해야 됨.
# 이분그래프가 아닌 예 : 5와 2가 연결되어 있는데 2를 이미 방문한 상태.
#                       즉, 2는 A 또는 B 중 하나가 이미 결정된 상태. 5에서 2를 갈 수 있다면
#                       5가 A인 상태라면 2도 A이니깐 이분그래프가 아님
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def dfs(index, color): # return 값이 True이면 이분그래프
    visited[index] = color
    for next_node in graph[index]:
        if visited[next_node] == 0:
            # 지금이 color이라면 연결되어서 방문하는 곳은 3-color이면 됨.
            # c가 1이면 방문하는 곳이 2가 되고, c가 2이면 방문하는 곳이 1이 됨
            if not dfs(next_node, 3-color): # 이분그래프가 아니라면 더이상 탐색할 필요가 없음
                return False
        elif visited[index] == visited[next_node]:
            # 색이 정해진게 일치하면 false
            return False
    return True

K = int(input())
for _ in range(K):
    V, E = map(int, input().split())
    graph = [[] for _ in range(V+1)]
    for _ in range(E):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)
    visited = [0] * (V+1) # 이분그래프를 A 혹은 B로 표시. 방문표시까지. 0은 방문 안함. 1은 A, 2는 B

    ans = True
    for i in range(1, V+1):
        if visited[i] == 0:
            if not dfs(i, 1):
                ans = False
                break
    if ans:
        print("YES")
    else:
        print("NO")