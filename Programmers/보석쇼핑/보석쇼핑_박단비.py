def solution(gems):
    N = len(gems)
    answer = [0, N - 1]
    kind = len(set(gems))  # 보석 종류 수
    dict = {gems[0]: 1}  # 보석 종류 체크 딕셔너리
    left, right = 0, 0

    while left < len(gems) and right < len(gems):
        # 딕셔너리에 보석 종류가 다 있는 경우
        if len(dict) == kind:
            if right - left < answer[1] - answer[0]:
                answer = [left, right]
            else:
                dict[gems[left]] -= 1
                if dict[gems[left]] == 0:
                    del dict[gems[left]]  # 개수 0이면 key도 없애줘야됨
                left += 1
        else:
            right += 1

            if right == len(gems):
                break

            if gems[right] in dict:
                dict[gems[right]] += 1

            else:
                dict[gems[right]] = 1

    return answer[0] + 1, answer[1] + 1