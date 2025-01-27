def solution(gems):
    # 탐색 길이
    n = len(gems)
    # 모든 보석을 포함하는 가장 짧은 구간 저장
    answer = [1, n]
    # 보석 종류의 개수
    m = len(set(gems))
    # 획득 보석
    get_gems = dict()
    # 처음 보석 저장
    get_gems[gems[0]] = 1
    # 투 포인터
    start = 0
    end = 0
    while start <= end and end < n:
        # 모든 종류의 보석을 1개 이상 포함하면
        if m == len(get_gems):
            # 구간 길이 비교후 더 적으면 갱신
            if end-start < answer[1] - answer[0]:
                answer = [start+1, end+1]
            # start 지점에 해당하는 보석의 개수 감소
            if get_gems[gems[start]] > 1:
                # 1보다 크면 개수 줄이기
                get_gems[gems[start]] -= 1
            else:
                # 1이면 0이 되므로 삭제
                del get_gems[gems[start]]
            # 다음 시작점 탐색
            start += 1
        else:
            # 끝 지점 증가
            end += 1
        
            if end == n:
                break
                
            # 보석 개수 증가
            if get_gems.get(gems[end]):
                get_gems[gems[end]] += 1
            else:
                get_gems[gems[end]] = 1

    return answer
