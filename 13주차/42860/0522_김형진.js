function solution(name) {
    var answer = 0;
    let min = name.length - 1;
  
    for (let i = 0; i < name.length; i++) {
      // 알파벳 돌리기
      let cur = name.charCodeAt(i);
      if (cur < 78) {
        answer += cur % 65;
      } else {
        answer += 91 - cur;
      }
  
      let next = i + 1;
  
      while (next < name.length && name.charCodeAt(next) === 65) {
        next += 1;
      }
      min = Math.min(
        min,
        i * 2 + name.length - next, 
        i + (name.length - next) * 2 
      );
    }
    answer += min;
    return answer;
  }