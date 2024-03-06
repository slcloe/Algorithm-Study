const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const input = [];
rl.on('line', (line) => {
    input.push(line);
});

// 자식 노드들이 많을수록 빠르게 전화를 걸어야 한다.
// 완전 탐색으로 자식이 많은 순대로 정렬하고, 가장 높은 값을 상위 노드로 올린다.

rl.on('close', () => {
    const N = Number(input[0]);
    const numbers = input[1].split(' ').map(Number);

    const node = [];
    for (let i = 0; i < N; i++) node.push([]);
    const childCnt = [];
    for (let i = 0; i < N; i++) childCnt.push(0);

    for (let i = 1; i < N; i++) {
        node[numbers[i]].push(i);
    }

    ;(function dfs(v) {
        if (node[v].length === 0) childCnt[v] = 0;
        else {
            const childNode = [];
            for (let child of node[v]) {
                dfs(child);
                childNode.push(childCnt[child]);
            }

            childNode.sort((a, b) => b - a);
            for (let i = 0; i < childNode.length; i++) {
                childNode[i] = childNode[i] + i + 1;
            }

            childCnt[v] = Math.max(...childNode);
        }
    })(0);

    console.log(childCnt[0]);
});