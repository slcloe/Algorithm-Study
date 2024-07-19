const input = require('fs').readFileSync(process.platform === "linux" ? "/dev/stdin" : "input1.txt").toString().trim();
const nums = input.split('').map(Number);
const n = nums.length;

const dp = Array.from({ length: n + 1 }, () => [0, 0]);

dp[1][0] = 1;

for (let i = 1; i < n; i++) {
    const prev = nums[i - 1];
    const cur = nums[i];
    const num = prev * 10 + cur;

    if (cur !== 0) {
        dp[i + 1][0] = dp[i][0] + dp[i][1];
    }
    if (num >= 10 && num <= 34) {
        dp[i + 1][1] = dp[i][0];
    }
}

console.log(dp[n][0] + dp[n][1]);
