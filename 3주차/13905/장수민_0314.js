const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const strToNum = (str) => str.split(" ").map(Number);

const input = [];
rl.on("line", (line) => {
  input.push(line);
});

const findParent = (parents, x) => {
  if (parents[x] === x) return x;
  parents[x] = findParent(parents, parents[x]);
  return parents[x];
};

const union = (parents, a, b) => {
  let rootA = findParent(parents, a);
  let rootB = findParent(parents, b);

  if (rootA < rootB) {
    parents[rootB] = rootA;
  } else {
    parents[rootA] = rootB;
  }
};

const isConnected = (parents, a, b) => {
  a = findParent(parents, a);
  b = findParent(parents, b);

  return a === b ? true : false;
};

rl.on("close", () => {
  let index = 0;
  // N : 집의 수, M : 다리의 수, s : 숭이의 출발 위치, e : 혜빈이의 위치
  const [N, M] = strToNum(input[index++]);
  const [s, e] = strToNum(input[index++]);

  const parents = Array.from({ length: N + 1 }, () => 0);
  const edges = [];
  let cost = 1000000;

  for (let i = 1; i <= N; i++) parents[i] = i;

  for (let i = 0; i < M; i++) {
    const [h1, h2, k] = strToNum(input[index++]);
    edges.push([h1, h2, k]);
  }

  // 가중치 기준 내림차순 정렬
  // 더 작은 가중치를 찾기 위해 Math.min 메서드를 활용하려면 가중치가 높은 순부터 진행해야 함.
  edges.sort((a, b) => b[2] - a[2]);
  //   console.log(edges);

  for (let i = 0; i < M; i++) {
    const [h1, h2, k] = edges[i];

    // 다리가 연결된 순간(숭이와 혜빈이가 연결되어있다는 사실을 알았을 때) 반복문 종료
    if (isConnected(parents, s, e)) break;

    union(parents, h1, h2);

    // console.log(h1, h2, parents);
    cost = Math.min(cost, k);
  }

  isConnected(parents, s, e) ? console.log(cost) : console.log(0);
  process.exit(0);
});
