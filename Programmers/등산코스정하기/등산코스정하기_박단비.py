'''
일반적인 다익스트라 알고리즘에서 최단거리 배열 갱신 방식은 아래와 같음
if dist[to] > dist[from] + weight then
    dist[to] = dist[from] + weight

이를 아래와 같이 변형해 intensity 배열을 갱신할 수 있음
if intensity[to] > max(intensity[from], weight) then
    intensity[to] = max(intensity[from], weight)
'''

from heapq import heappop, heappush
from math import inf


def solution(n, paths, gates, summits):
    answer = []
    graph = [[] for _ in range(n + 1)]
    for a, b, w in paths:
        graph[a].append((b, w))
        graph[b].append((a, w))

    # 산봉우리 판별하기
    is_summit = [False] * (n + 1)
    for summit in summits:
        is_summit[summit] = True

    # gates를 모두 시작 위치로 둠
    distance = [inf] * (n + 1)
    q = []
    for gate in gates:
        distance[gate] = 0
        heappush(q, (0, gate))  # 가중치랑 입구 번호

    # 다익스트라
    while q:
        intensity, node = heappop(q)
        # 산봉우리면 continue. 그래야지 산봉우리 2개 방문 안함
        # intensity가 더 커도 방문 안함.
        if is_summit[node] or distance[node] < intensity:
            continue

        for next_node, weight in graph[node]:
            new_intensity = max(intensity, weight)
            if new_intensity < distance[next_node]:
                distance[next_node] = new_intensity
                heappush(q, (new_intensity, next_node))

    # 결과
    # 거리가 같으면 산봉우리 번호가 작아야하기 때문에
    # 산봉우리 정렬
    answer = [-1, inf]
    for summit in sorted(summits):
        if distance[summit] < answer[1]:
            answer[0] = summit
            answer[1] = distance[summit]

    return answer