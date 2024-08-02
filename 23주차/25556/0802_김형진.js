// 스택에 값을 넣을 때 무조건 더 큰수가 들어와야 함
const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'input2.txt').toString().split('\n');
const N = parseInt(input[0]);
const nums = input[1].split(' ').map(e => parseInt(e));

class Stack {
    constructor() {
        this._arr = [];
    }
    push(item) {
        this._arr.push(item);
    }
    pop() {
        return this._arr.pop();
    }
    peek() {
        return this._arr[this._arr.length - 1];
    }
    isEmpty() {
        return this._arr.length === 0;
    }
}

const Stacks = Array.from({ length: 4 }, () => new Stack());
let YES = true;

for (const num of nums) {
    let pushed = false;
    for (const stack of Stacks) {
        if (stack.isEmpty() || stack.peek() < num) {
            stack.push(num);
            pushed = true;
            break;
        }
    }
    if (!pushed) {
        YES = false;
        break;
    }
}

console.log(YES ? 'YES' : 'NO');
