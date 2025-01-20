from itertools import permutations

def solution(user_id, banned_id):
    answer = []
    for perm in permutations(user_id, len(banned_id)):
        find = True
        for i in range(len(perm)):
            if len(perm[i]) != len(banned_id[i]):
                find = False
                break
            else:
                for j in range(len(banned_id[i])):
                    if banned_id[i][j] == '*':
                        continue
                    elif banned_id[i][j] != perm[i][j]:
                        find = False
                        break
        if find:
            # 중복 조심
            if set(perm) not in answer:
                answer.append(set(perm))

    return len(answer)