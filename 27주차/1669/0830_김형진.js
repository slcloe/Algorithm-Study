const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'input1.txt').toString().split(" ").map(Number);
const [N, D] = input;
const gap = Math.abs(N - D);

if (N === D) {
    console.log(0);
} else {
    let base = 1;
    while (true) {
        let sum = base * base + base;
        if (sum >= gap) {
            console.log(base * 2 + (sum - base >= gap ? -1 : 0));
            break;
        }
        base++;
    }
}
