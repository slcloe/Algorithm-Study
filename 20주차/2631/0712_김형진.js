const input =
    require('fs').readFileSync(process.platform === "linux" ? "/dev/stdin" : "input1.txt")
    .toString().split("\n")
const N = parseInt(input.shift());
const children = input.map(e=>parseInt(e))
const dp = Array(N).fill(1);

for(let i= 0; i<N; i++){
    for(let j= 0; j<i; j++){
        if(children[j] < children[i] && dp[j]>dp[i]) dist=  Math.max(dist,dp[j])
    }
    dp[i]= dist+1;
}
console.log(N-Math.max(...dp));