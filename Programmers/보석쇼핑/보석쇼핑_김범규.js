function solution(gems) {
    //보석의 종류...
    const gemKinds = [... new Set(gems)];
    let gemMap = {};
    let answer = [0, gems.length - 1];
    let start = 0;
    let end = 0;
    
    //이때까지 몇개 나왔는가 저장
    gemKinds.forEach(gem => {
        gemMap[gem] = 0;    
    })
    
    //보석 세는용
   let count = 0;
    
    while(end < gems.length){
        const gem = gems[end];
        
        if(gemMap[gem] === 0) count++;
        gemMap[gem] += 1;
        end++;
        
        //count가 보석 종류만큼 모였다면 검사
        while(count === gemKinds.length){
            if(end - start < answer[1] - answer[0] + 1){
                answer = [start, end - 1];
            }
            
            const startGem = gems[start];
            gemMap[startGem] -= 1;
            if(gemMap[startGem] === 0) count--;
            start++;
        }
    }
    
    return [answer[0] + 1, answer[1] + 1];
}