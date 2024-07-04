function solution(maps) {
    const answer = [];
    const dir = [[0,1],[1,0],[0,-1],[-1,0]];
    const [row, col] = [maps.length, maps[0].length];
    const visited = Array.from({length: row}, () => Array(col).fill(false));

    for (let i = 0; i < row; i++) {
        for (let j = 0; j < col; j++) {
            if (maps[i][j] !== 'X' && !visited[i][j]) {
                BFS(i, j);
            }
        }
    }

    function BFS(i, j) {
        let tmp = 0;
        const q = [[i, j]];
        visited[i][j] = true;
        tmp += parseInt(maps[i][j]);

        while (q.length > 0) {
            const [ci, cj] = q.shift();
            for (let d = 0; d < 4; d++) {
                const ni = ci + dir[d][0];
                const nj = cj + dir[d][1];
                if (ni < row && ni >= 0 && nj < col && nj >= 0 && !visited[ni][nj] && maps[ni][nj] !== 'X') {
                    visited[ni][nj] = true;
                    tmp += parseInt(maps[ni][nj]);
                    q.push([ni, nj]);
                }
            }
        }
        answer.push(tmp);
    }

    return answer.length === 0 ? [-1] : answer.sort((i, j) => i - j);
}
