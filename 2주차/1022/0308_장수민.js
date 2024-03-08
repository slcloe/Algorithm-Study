// 입력 부분
const readline = require("readline").createInterface({
    input: process.stdin,
    output: process.stdout,
  });
  
let input;
  
readline
    .on("line", (line) => {
      input = line;
      readline.close();
    })
    .on("close", () => {
      solution(input);
      process.exit();
    });

const solution = (input) => {
    const [r1, c1, r2, c2] = [...input.split(" ").map((val) => Number(val))];
    
    const board = Array.from(Array(r2 - r1 + 1), () =>
      Array(c2 - c1 + 1).fill("")
    );
    
    // 출력을 위해 계속 갱신해줘야 하는 최댓값
    let max = Number.MIN_SAFE_INTEGER;
    
    for (let i = 0; i < r2 - r1 + 1; i++) {
        for (let j = 0; j < c2 - c1 + 1; j++) {
            const x = c1 + j;
            const y = r1 + i;
            const basis = Math.max(Math.abs(x), Math.abs(y));
            const distX = basis - x;
            const distY = basis - y;
            if (x === y) {
                if (x >= 0) board[i][j] = Math.pow(basis * 2 + 1, 2);
                else board[i][j] = Math.pow(basis * 2 + 1, 2) - distX - distY;
            } else if (x > y) {
                board[i][j] = Math.pow(basis * 2 - 1, 2) + distX + distY;
            } else {
                board[i][j] = Math.pow(basis * 2 + 1, 2) - distX - distY;
            }
            max = Math.max(max, board[i][j]);
        }
    }
    
    // 출력 형식 맞추기
    const length = max.toString().length;
    const result = board.map((row) =>
        row.map((col) => {
            col = col.toString();
            while (col.length < length) {
                col = " " + col;
            }
        return col;
        })
    );
    
    result.forEach((row) => console.log(row.join(" ")));
  };