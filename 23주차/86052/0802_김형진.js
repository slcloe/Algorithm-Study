function solution(grid) {
    const [R, C] = [grid.length, grid[0].length];
    const v = Array.from({length: R}, () => Array.from({length: C}, () => Array(4).fill(false)));
    const dir = [[-1, 0], [0, 1], [1, 0], [0, -1]];
    const answer = [];

    const getDir = (dir) => {
        return dir < 0 ? dir + 4 : dir % 4;
    };

    const getPosition = (p, boundary) => {
        if (p >= boundary) return 0;
        if (p < 0) return boundary - 1;
        return p;
    };

    for (let i = 0; i < R; i++) {
        for (let j = 0; j < C; j++) {
            for (let d = 0; d < 4; d++) {
                if (v[i][j][d]) continue;

                let ci = i;
                let cj = j;
                let cd = d;
                let cnt = 0;

                while (!v[ci][cj][cd]) {
                    v[ci][cj][cd] = true;
                    cnt++;
                    if (grid[ci][cj] === "L") {
                        cd = getDir(cd - 1);
                    } else if (grid[ci][cj] === "R") {
                        cd = getDir(cd + 1);
                    }
                    ci += dir[cd][0];
                    cj += dir[cd][1];

                    ci = getPosition(ci, R);
                    cj = getPosition(cj, C);
                }
                answer.push(cnt);
            }
        }
    }
    return answer.sort((a, b) => a - b);
}
