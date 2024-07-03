// 뱀과 사다리 게임
// 10*10 보드판
// 1~100 칸, 현재칸 + 주사위나온 칸 = 다음칸
// 사다리[0] -> 사다리[1] (사다리 수 1~15) : N
// 뱀[0] -> 뱀[1] (뱀 수 1~15) : M

const input =
    require('fs').readFileSync(process.platform === "linux" ? "/dev/stdin" : "BOJ/week19/BOJ_16928_뱀과 사다리 게임/input2.txt")
    .toString().split("\n")
const [N, M] = input[0].split(' ').map(e => parseInt(e))

const ladder = Array(101).fill(0)
const snake = Array(101).fill(0)
const visited = Array(101).fill(0)

for (let i = 1; i <= N; i++) {
    const [x, y] = input[i].split(' ').map(e => parseInt(e))
    ladder[x] = y
}
for (let i = N + 1; i <= N + M; i++) {
    const [x, y] = input[i].split(' ').map(e => parseInt(e))
    snake[x] = y
}

function bfs(){
    const q = [];
    q.push(1)
    visited[1] = 1

    while(q.length > 0){
        let cur = q.shift()
        if(cur === 100) return visited[100] - 1

        for(let i = 1; i <= 6; i++){
            let next = cur + i
            if(next > 100) continue
            if(ladder[next] !== 0) next = ladder[next]
            if(snake[next] !== 0) next = snake[next]
            if(visited[next] === 0){
                visited[next] = visited[cur] + 1
                q.push(next)
            }
        }
    }
    return -1
}
console.log(bfs())