// N*N 행렬
const input =
    require('fs').readFileSync(process.platform === "linux" ? "/dev/stdin" : "BOJ/input/input3.txt")
    .toString().split("\n")
const [N, B] = input[0].split(' ').map(e => parseInt(e))
const matrix= [];
for(let i=1;i<=N;i++){
    matrix.push(input[i].split(' ').map(e=>parseInt(e)))
}

function mul(B){
    if(B===1) return  matrix.map(e => e.map(value => value % 1000));
    let temp = mul(parseInt(B/2))
    temp = cal(temp,temp)
    if(B%2) temp = cal(temp,matrix)
    return temp
}
function cal(A,B){
    let result = Array.from(Array(N),()=>Array(N).fill(0))
    for(let i=0;i<N;i++){
        for(let j=0;j<N;j++){
            for(let k=0;k<N;k++){
                result[i][j] += A[i][k]*B[k][j]
            }
            result[i][j]%=1000
        }
    }
    return result
}
console.log(mul(B).map(e=>e.join(' ')).join('\n'))