const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

const input = [];

rl.on('line', (line) => {
    input.push(line);
}).on('close', () => {
    const N = Number(input[0]);
    const arr = input[1].split(' ').map((el) => Number(el));

    let result = 0;

    arr.sort((a, b) => a - b); // 이분탐색을 위한 오름차순 정렬
    
    for (let i = 0; i < N; i++) { // 입력으로 주어진 모든 수가 다른 두 수의 합인지 고려해야 함
        let left = 0;
        let right = N - 1;
        const target = arr[i];

        while (left < right) {
            if (left === i) left++; // target의 인덱스와 left와 같아서는 안됨
            else if (right === i) right--; // target의 인덱스와 right와 같아서는 안됨
            else if (arr[left] + arr[right] === target) { // 두 수의 합이라면 다음 수로 넘어감
                result++;
                break;
            } else if (arr[left] + arr[right] < target) {
                left++;
            } else if (arr[left] + arr[right] > target) {
                right--;
            }
        }
    }

    console.log(result);
    process.exit(0);
})