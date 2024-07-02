function solution(temperature, t1, t2, a, b, onboard) {
    let temp = temperature > t2 ? t1 - (temperature - t2) : temperature;
    let INF = 987654321;
    t1 -= temp;
    t2 -= temp;
    temp = 0;

    let dp = Array.from({ length: onboard.length }, () => Array(t2 + 2).fill(INF));
    dp[0][0] = 0;

    for (let i = 1; i < onboard.length; i++) {
        for (let j = 0; j <= t2 + 1; j++) {
            if (onboard[i] === 1 && (j < t1 || j > t2)) continue;
            let min = INF;
            if (j === 0) {
                min = Math.min(min, dp[i-1][j]);
                if (j + 1 <= t2 + 1) min = Math.min(min, dp[i-1][j+1]);
            } else {
                if (j - 1 >= 0) min = Math.min(min, dp[i-1][j-1] + a);
                min = Math.min(min, dp[i-1][j] + b);
                if (j + 1 <= t2 + 1) min = Math.min(min, dp[i-1][j+1]);
            }
            dp[i][j] = min;
        }
    }

    let result = Infinity;
    for (let j = 0; j <= t2 + 1; j++) result = Math.min(result, dp[onboard.length - 1][j]);
    return result;
}
