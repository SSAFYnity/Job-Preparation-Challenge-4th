import heapq

def solution(n, paths, gates, summits):
    
    def dijkstra():
        # 최소 intensity 저장
        min_intensity = [50001, 10000001]
        
        visited = [10000001]*(n+1)
        heap = []
        # 각 출발점에서 산봉우리 가는 길 탐색
        for gate in gates:
            heap.append((0, gate))
            visited[gate] = 0
        
        while heap:
            time, now = heapq.heappop(heap)
            
            # 저장된 시간이 더 작거나  탐색 안함
            if visited[now] < time:
                continue
                
            # 산봉우리를 만나면 intensity 갱신
            if now in set_summits:
                # 최소 intensity 찾기
                if visited[now] < min_intensity[1]:
                    min_intensity[1] = visited[now]
                    min_intensity[0] = now
                # 가장 낮은 등산코스를 선택
                elif visited[now] == min_intensity[1] and now < min_intensity[0]:
                    min_intensity[0] = now
                continue

            for next_time, next in graph[now]:
                # 출발점을 만나면 탐색 안함
                if next in set_gates:
                    continue
                max_time = max(time, next_time)
                # intensity(가장 긴 시간) 작으면 값 갱신
                if max_time < visited[next]:
                    visited[next] = max_time
                    heapq.heappush(heap, (max_time, next))
        
        return min_intensity

    
    # 등산로 양방향 그래프로 저장
    graph = [[] for _ in range(n+1)]
    for i, j, w in paths:
        graph[i].append((w, j))
        graph[j].append((w, i))
    
    set_summits = set(summits)
    set_gates = set(gates)

    # 최소 intensity 찾기
    answer = dijkstra()
    return answer
