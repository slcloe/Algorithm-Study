// js로는 제출 불가
const solution = (m, n, picture)=>{
    const di = [-1, 0, 1, 0];
    const dj = [0, 1, 0, -1];
    let visited = Array.from({ length: m }, () => Array(n).fill(false));
    
    let cnt = 0;
    let max = 0;

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (picture[i][j] !== 0 && !visited[i][j]) {
                max = Math.max(max, bfs(m, n, i, j, picture, visited, di, dj));
                cnt++;
            }
        }
    }
    return [cnt, max];
}
const isOOB = (m,n,i,j)=> i<0 || i>=m || j<0 || j>=n;

const bfs =(m, n, x, y, picture, visited, di, dj)=>{
    let range = 1;
    const target = picture[x][y];
    const q = [];

    visited[x][y] = true;
    q.push([x, y]);

    while (q.length > 0) {
        const [ci, cj] = q.shift();

        for (let i = 0; i < 4; i++) {
            const ni = ci + di[i];
            const nj = cj + dj[i];

            if (!isOOB(m,n,ni,nj) && picture[ni][nj]=== target && !visited[ni][nj]) {
                q.push([ni, nj]);
                visited[ni][nj] = true;
                range++;
            }
        }
    }

    return range;
}

console.log(solution(6,4,[[1, 1, 1, 0], [1, 2, 2, 0], [1, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 3], [0, 0, 0, 3]]))