function solution(routes) {
    let answer = 1;
    routes.sort((a, b) => a[1] - b[1])
    let tv = routes[0][1]
    
    routes.forEach((car) =>{
        const [start, end] = car;
        if(start > tv){
            tv = end;
            answer++;
        }
    })
    return answer;
}