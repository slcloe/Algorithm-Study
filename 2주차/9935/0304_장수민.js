// stack 자료구조에 타겟이 되는 문자열을 순차적으로 넣으면서
// 뒤에서 폭발 문자열의 길이만큼 확인해서 폭발시키기

const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const input = [];
rl.on('line', (line) => {
    input.push(line);
})
rl.on('close', () => {
    const targetString = input[0];
    const explodedString = input[1];
    const checkLength = explodedString.length;

    let result = 'FRULA';

    const stack = [];
    for (let character of targetString) {
        stack.push(character);
        if (stack.length >= checkLength) {
            let isSameArray = true;
            for (let i = checkLength; i > 0; i--) {
                if (stack[stack.length - i] !== explodedString[checkLength - i]) {
                    isSameArray = false;
                    break;
                }
            }
            
            if (isSameArray) {
                for (let i = 0; i < checkLength; i++) stack.pop();
            }
        }
    }

    console.log(stack.length === 0 ? result : stack.join(''));
})