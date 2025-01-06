from collections import deque

def solution(cacheSize, cities):
    answer = 0
    if cacheSize == 0:
        answer = 5 * len(cities)
        return answer

    q = deque()
    for city in cities:
        city = city.lower()
        # city가 q에 포함되어 있는 경우
        if city in q:
            # LRU는 이미 캐시에 있는 도시가 새로 히트할때 기존의 위치에서 제거하고 새로 추가해야함
            # EX) 3, ["A", "B", "C", "A", "D", "G", "A"] 결과 27
            # 캐시에 ["A","B","C"] 이미 3칸들어와 있을경우
            # 4번째 "A"를 히트 하면서 ["B","C","A"]로 변경이 되어야 함.
            answer += 1
            # LRU의 경우
            q.remove(city)
            q.append(city)
        # city가 q에 포함되어 있지 않은 경우
        else:
            # 캐시가 다 찬 경우
            if len(q) == cacheSize:
                q.popleft()
                q.append(city)
                answer += 5
            else:
                q.append(city)
                answer += 5

    return answer