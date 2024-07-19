const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'input2.txt').toString().split('\n');

let [N, M] = input[0].split(' ').map(Number);
const MAXN = 100000;
const arr = new Array(MAXN + 1).fill(0);
let min_tree = [];
let max_tree = [];

const init = (node, start, end) => {
    if (start === end) {
        min_tree[node] = max_tree[node] = arr[start];
        return;
    } else {
        const mid = Math.floor((start + end) / 2);
        init(node * 2, start, mid);
        init(node * 2 + 1, mid + 1, end);
        min_tree[node] = Math.min(min_tree[node * 2], min_tree[node * 2 + 1]);
        max_tree[node] = Math.max(max_tree[node * 2], max_tree[node * 2 + 1]);
        return;
    }
};

const h = Math.ceil(Math.log2(N));
min_tree = new Array(1 << (h + 1)).fill(0);
max_tree = new Array(1 << (h + 1)).fill(0);

for (let i = 1; i <= N; i++) {
    arr[i] = parseInt(input[i]);
}
init(1, 1, N);