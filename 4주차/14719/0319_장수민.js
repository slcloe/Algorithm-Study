const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];
rl.on("line", (line) => {
  input.push(line);
});

rl.on("close", () => {
  const [H, W] = input[0].split(" ").map(Number);
  const heights = input[1].split(" ").map(Number);

  const board = Array.from({ length: H }, () => []);
  board.forEach((arr) => {
    for (let i = 0; i < W; i++) arr.push(0);
  });

  for (let i = 0; i < W; i++) {
    for (let j = 0; j < heights[i]; j++) {
      board[j][i] = 1;
    }
  }

  let water = 0;

  for (let i = 0; i < H; i++) {
    for (let j = 0; j < W; j++) {
      if (board[i][j] !== 1) {
        checkRow(i, j);
      }
    }
  }

  function checkRow(y, x) {
    let isSurroundedRow = true;
    let cnt = 0;

    while (board[y][x] !== 1 && x < W) {
      if (x === 0 || x === W - 1) {
        isSurroundedRow = false;
      }

      board[y][x] = 1;
      cnt++;
      x++;
    }

    if (isSurroundedRow) water += cnt;
  }

  console.log(water);
  process.exit(0);
});
