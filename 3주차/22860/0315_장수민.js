const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const strToNum = (str) => str.split(' ').map(Number);

const input = [];
rl.on('line', (line) => {
  input.push(line);
});

rl.on('close', () => {
  const [N, M] = strToNum(input[0]);

  const fileFolder = input.slice(1, N + M + 1).map((e) => e.split(' '));
  const query = input.slice(N + M + 2).map((e) => e.split('/'));
  let answer = '';

  const tree = {};
  fileFolder.forEach(([p, f, c]) => {
    if (tree[p]) tree[p].push([f, c]);
    else {
      tree[p] = [];
      tree[p].push([f, c]);
    }
    // folder(f)라는 속성이 있는지 확인하기
    if (c === '1' && !tree.hasOwnProperty(f)) tree[f] = [];
  });

  query.forEach((q) => {
    const folder = q.at(-1); // 가장 마지막 인덱스의 값
    bfs(folder);
  });

  function bfs(folder) {
    // 너비 우선 탐색 진행
    let files = [];
    let filesNoDup = new Set(); // 중복되는 파일을 처리하기 위한 Set
    const queue = [folder];
    let front = 0;
    while (queue.length > front) {
      const f = queue[front++];
      for (let next of tree[f]) {
        if (next[1] === '1') {
          // 폴더일 경우
          queue.push(next[0]);
        } else {
          // 파일일 경우
          files.push(next[0]);
          filesNoDup.add(next[0]);
        }
      }
    }
    answer += `${filesNoDup.size} ${files.length}\n`;
  }

  console.log(answer);
  process.exit(0);
});
