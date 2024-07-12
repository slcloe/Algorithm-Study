const input = require('fs').readFileSync(process.platform === "linux" ? "/dev/stdin" : "input1.txt").toString().split("\n");
const N = parseInt(input.shift());
const start = [Math.floor(N / 2), Math.floor(N / 2)];

const map = [];
const di = [0, 1, 1, 1, 0, -1, -1, -1];
const dj = [-1, -1, 0, 1, 1, 1, 0, -1];
let result = 0;

for (const row of input) {
    map.push(row.split(' ').map(e => parseInt(e)));
}

const isOut = (i, j) => {
    return (i < 0 || i >= N || j < 0 || j >= N);
}

const moveSand = (i, j, sand) => {
    const fSand = Math.floor(sand);
    if (isOut(i, j)) {
        result += fSand;
    } else {
        map[i][j] += fSand;
    }
    return fSand;
}

const spread = (i, j, d) => {
    let sand = map[i][j];
    map[i][j] = 0;
    const ai = i + di[d];
    const aj = j + dj[d];
    let alpha = sand;

    alpha -= moveSand(i + di[d] * 2, j + dj[d] * 2, sand * 0.05);
    alpha -= moveSand(i + di[(d + 2) % 8], j + dj[(d + 2) % 8], sand * 0.07);
    alpha -= moveSand(i + di[(d + 6) % 8], j + dj[(d + 6) % 8], sand * 0.07);
    alpha -= moveSand(i + di[(d + 2) % 8] * 2, j + dj[(d + 2) % 8] * 2, sand * 0.02);
    alpha -= moveSand(i + di[(d + 6) % 8] * 2, j + dj[(d + 6) % 8] * 2, sand * 0.02);
    alpha -= moveSand(i + di[(d + 1) % 8], j + dj[(d + 1) % 8], sand * 0.1);
    alpha -= moveSand(i + di[(d + 7) % 8], j + dj[(d + 7) % 8], sand * 0.1);
    alpha -= moveSand(i + di[(d + 3) % 8], j + dj[(d + 3) % 8], sand * 0.01);
    alpha -= moveSand(i + di[(d + 5) % 8], j + dj[(d + 5) % 8], sand * 0.01);

    moveSand(ai, aj, alpha);
}

const moveTonado = (i, j, d, cnt, toGo, toGoCnt) => {
    spread(i, j, d);

    if (i === 0 && j === 0) return;

    if (toGoCnt === Math.floor(toGo / 2) || toGoCnt === toGo) d = (d + 2) % 8;

    const ni = i + di[d];
    const nj = j + dj[d];

    if (toGoCnt === toGo) {
        toGo += 2;
        toGoCnt = 0;
    }

    moveTonado(ni, nj, d, cnt + 1, toGo, toGoCnt + 1);
}

moveTonado(start[0], start[1], 0, 0, 2, 0);

console.log(result);
