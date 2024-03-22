const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];
rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [N, M, L, K] = input[0].split(" ").map(Number);
  const locations = [];
  for (let i = 1; i < K + 1; i++) {
    const [x, y] = input[i].split(" ").map(Number);
    locations.push([x, y]);
  }

  let answer = 0;

  for (let i = 0; i < K; i++) {
    for (let j = 0; j < K; j++) {
      const edgeX = locations[i][0];
      const edgeY = locations[j][1];
      let cnt = 0;
      for (const [x, y] of locations) {
        if (edgeX <= x && edgeX + L >= x && edgeY <= y && edgeY + L >= y) cnt++;
      }
      answer = Math.max(answer, cnt);
    }
  }

  console.log(K - answer);
  process.exit(0);
});
