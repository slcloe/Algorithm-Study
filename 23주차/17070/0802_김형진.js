const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'input1.txt').toString().split('\n');
const N = parseInt(input.shift());
const map = Array.from(input, (e) => e.split(' ').map(Number));
const dp = Array.from({length: 3}, () => Array.from({length: N}, () => Array(N).fill(0)));

function PASS(i, j) {
    return i >= 0 && i < N && j >= 0 && j < N && map[i][j] === 0;
}

dp[0][0][1] = 1; 

for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
        if (PASS(i, j+1)) dp[0][i][j+1] += dp[0][i][j] + dp[2][i][j];
        if (PASS(i+1, j)) dp[1][i+1][j] += dp[1][i][j] + dp[2][i][j];
        if (PASS(i+1, j+1) && PASS(i+1, j) && PASS(i, j+1)) dp[2][i+1][j+1] += dp[0][i][j] + dp[1][i][j] + dp[2][i][j];
    }
}

console.log(dp[0][N-1][N-1] + dp[1][N-1][N-1] + dp[2][N-1][N-1]);
