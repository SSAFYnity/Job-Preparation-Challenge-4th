def solution(routes):
    n = len(routes)
    routes.sort(key = lambda x: x[1])
    answer = 1
    end = routes[0][1]
    for i in range(1, n):
        if end < routes[i][0]:
            answer += 1
            end = routes[i][1]
            
    return answer
