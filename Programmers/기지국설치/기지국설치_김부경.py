def solution(n, stations, w):
  
    # 공간 사이의 기지국 개수 가져오기
    def get_station_cnt(e, s):
        # 가능 기지국 개수 
        cnt = (e-s)//(2*w+1)
        if (e-s)%(2*w+1):
            cnt += 1
        return cnt
    
    # 최소 기지국 개수 저장
    answer = 0
    # 기지국 증설 가능한 공간 시작 위치
    start = 1
    end = n
    for station in stations:
        # 해당 기지국의 전파 전달 가능 위치 
        left = station - w
        # 전파가 없는 공간의 왼쪽 끝보다 해당 기지국 전파의 왼쪽 끝 위치가 크면
        # 그 사이 공간에 기지국 설치
        if start < left:
            answer += get_station_cnt(left, start)
        # 시작 위치 갱신
        start = station + w + 1
    # 마지막 기지국 뒷 공간이 남아있으면
    if start < end+1:
        answer += get_station_cnt(end+1, start)
        
    return answer
