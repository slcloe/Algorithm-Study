const fs = require('fs')
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'input1.txt').toString().split('\n')
const [D,N] = input[0].split(' ').map(Number);
const oven = input[1].split(' ').map(Number);
const dough = input[2].split(' ').map(Number);

// 오븐 지름 조정 (윗부분의 지름이 더 작으면 반죽이 들어갈 수 없음)
// => 내려갈 수록 좁아지는 구조로

for(let i= 0; i< D; i++){
    if(oven[i]<oven[i+1]) oven[i+1]= oven[i] 
}

let idx = 0; 
for(let i= D; i>= 0; i--){
    if(oven[i]>=dough[idx]) idx ++

    if(N===idx){
        console.log(i+1)
        return 
    }
}
console.log(0)