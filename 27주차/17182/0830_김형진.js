const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'input2.txt').toString().trim().split("\n");

const [N, K] = input[0].split(" ").map(Number);
const time = input.slice(1).map(line => line.split(" ").map(Number));
let visited = Array(N).fill(0);
visited[K] = 1;
let answer = Infinity;

for (let k = 0; k < N; k++) {
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            time[i][j] = Math.min(time[i][j], time[i][k] + time[k][j]);
        }
    }
}

const findMin= (curr, cost, cnt)=>{
    if (cnt === N) {
        answer = Math.min(answer, cost);
        return;
    }
    for (let i = 0; i < N; i++) {
        if (visited[i] === 0) {
            visited[i] = 1;
            findMin(i, cost + time[curr][i], cnt + 1);
            visited[i] = 0;
        }
    }
}

findMin(K, 0, 1);
console.log(answer);
