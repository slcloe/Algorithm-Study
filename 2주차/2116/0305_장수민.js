const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
})

const input = [];

rl.on('line', (line) => {
    input.push(line);
});

// 가장 밑에 들어가는 첫번째 주사위가 어떻게 놓이는지가 관건
// 위 아래 쌍은 (0, 5), (1, 3), (2, 4)
// 위 아래 면을 제외하고 가장 큰 수를 골라서 더하기

rl.on('close', () => {
    const n = input[0];
    const dices = [];
    for (let i = 1; i <= n; i++) {
        dices.push(input[i].split(' ').map(Number));
    }

    const pair = {
        0: 5,
        1: 3,
        2: 4,
        3: 1,
        4: 2,
        5: 0,
    };
    
    let result = 0;
    for (let i = 0; i < 6; i++) {
        const diceUp = dices[0][i];
        const diceBottom = dices[0][pair[i]];

        let tempResult = 0;

        // 첫번째 주사위에서 가장 큰 수 찾기
        tempResult += Math.max(...([1, 2, 3, 4, 5, 6].filter(v => v !== diceUp && v !== diceBottom)));
        
        // 재귀 함수 선언
        const recursive = (prevUp, j) => {
            const bottomIndex = dices[j].indexOf(prevUp);
            const diceUp = dices[j][pair[bottomIndex]];
            const diceBottom = dices[j][bottomIndex];

            tempResult += Math.max(...([1, 2, 3, 4, 5, 6].filter(v => v !== diceUp && v !== diceBottom)));
            if (j < n - 1) recursive(diceUp, j + 1);
        }

        // 호출
        recursive(diceUp, 1);
        result = Math.max(result, tempResult);
    }

    console.log(result);
});