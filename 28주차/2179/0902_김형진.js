const fs = require('fs')
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'input2.txt').toString().split('\n')
const N = parseInt(input[0]);
input.shift()

let [S, T, sim] = ['','',0];
const check = (s,t) =>{
    const len = Math.min(s.length, t.length)
    if(len<sim) return
    else checkPrefix(s,t,len)
}
const checkPrefix = (s, t, len) =>{
    let cnt = 0;
    for(let i= 0 ; i< len; i++){
        if(s[i]==t[i]) cnt ++
        else break;
    }
    if(cnt>sim){
        S = s;
        T = t;
        sim = cnt;
    }
}

for(let i= 0; i<N; i++){
    for(let l= i; l<N; l++){
        if(i===l)continue;
        check(input[i],input[l])
    }
}
console.log(S)
console.log(T)