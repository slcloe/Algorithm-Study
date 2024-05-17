function solution(board) {
    var answer = Number.MAX_SAFE_INTEGER;
    
    const di = [-1, 0, 1, 0];
    const dj = [0, 1, 0, -1];
    
    const Length = board.length;
    const visited = Array.from(new Array(Length), () => new Array(Length).fill(0));
    
    const q= [[0,0,-1,0]];
    
    while(q.length){
        const [i, j, d, price] = q.shift();
        if (i === Length-1 && i === Length-1) {
            answer = Math.min(answer, price);
        }
        for(let dir= 0; dir<4; dir++){
            const ni = i+ di[dir];
            const nj = j+ dj[dir];
            
             if(ni<0 || ni >= Length || nj <0 || nj>=Length || board[ni][nj]===1)continue; 
        
            let nextPrice = price

            nextPrice += 100;
            if (d % 2 === 0 && dir % 2 === 1 || d % 2 === 1 && dir % 2 === 0) nextPrice += 500;

            if (!visited[ni][nj] || visited[ni][nj] >= nextPrice) {
                visited[ni][nj] = nextPrice;
                q.push([ni, nj, dir, nextPrice]);
            }
        }
    }
       
    return visited[Length-1][Length-1];
}