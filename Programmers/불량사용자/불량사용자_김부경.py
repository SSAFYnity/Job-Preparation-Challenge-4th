def solution(user_id, banned_id):
    # 불량 사용자에 매핑되는 지 확인
    def is_mappable(x, y):
        # x: 사용자, y: 불량 사용자
        length = len(x)
        for k in range(length):
            if y[k] != "*" and x[k] != y[k]:
                return False
        return True

    # 제재 아이디 목록 가져오기
    def dfs(idx):
        nonlocal answer
        # 불량 사용자를 모두 탐색하면 매핑된 사용자들 반환
        if idx == m:
            # 중복 방지
            answer.add(tuple(sorted(visited)))
            return
        # 불량 사용자에 매핑되는 사용자 탐색
        for k in bad_candidates[banned_id[idx]]:
            if k not in visited:
                visited.add(k)
                dfs(idx+1)
                visited.discard(k)
                
        
    # 불량사용자: {후보}
    bad_candidates = dict()
    n = len(user_id)
    m = len(banned_id)
    # 불량 사용자 기준으로 매핑되는 사용자 탐색
    for i in range(m):
        # 불량 사용자
        bad_user = banned_id[i]
        bad_candidates[bad_user] = set()
        for j in range(n):
            # 사용자
            user = user_id[j]
            # 불량 사용자에 매핑되는지 체크
            if len(bad_user) == len(user) and is_mappable(user, bad_user):
                # 매핑되면 사용자 인덱스 저장
                bad_candidates[bad_user].add(j)
    # 제재 목록 저장
    answer = set()
    # 방문 체크
    visited = set()
    # 제재 목록 탐색
    dfs(0)
        
    return len(answer)
