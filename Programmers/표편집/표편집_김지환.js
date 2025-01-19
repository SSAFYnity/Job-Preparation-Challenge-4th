function solution(n, k, cmd) {
    let linked = Array(n).fill().map((_, i) => ({
        prev : i - 1,
        next : i + 1,
        deleted : false
    }))
    linked[n - 1].next = -1;
    
    let stack = [];
    let current = k;
    
    cmd.forEach((order) =>{
        const [dir, count] = order.split(' ');
        
        switch(dir){
            case 'D':{
                let move = Number(count);
                while(move > 0){
                    current = linked[current].next;
                    move--;
                }
                break;
            }
            case 'U':{
                let move = Number(count);
                while(move > 0){
                    current = linked[current].prev;
                    move--;
                }
                break;
            }
            case 'C':{
                const prev = linked[current].prev;
                const next = linked[current].next;
                linked[current].deleted = true;
                stack.push(current)
            
                if(prev >= 0) linked[prev].next = next;
                if(next >= 0) linked[next].prev = prev;
                
                // 마지막 행이 아닌 경우 next, 마지막인 경우 prev
                current = next >= 0 ? next : prev
                break;
            }
            case 'Z':{
                const history = stack.pop();
                linked[history].deleted = false;
                
                const prev = linked[history].prev;
                const next = linked[history].next;
                
                if(prev >= 0) linked[prev].next = history
                if(next >= 0) linked[next].prev = history                
                break;
            }
        }
    })
    
    return linked.map((node)=>{
        return node.deleted ? 'X' : 'O'
    }).join('')
}