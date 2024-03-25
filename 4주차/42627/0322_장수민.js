function solution(jobs) {
  let index = 0;
  let now = 0;
  let sum = 0;
  const length = jobs.length;

  jobs.sort((a, b) => a[0] - b[0]); // 요청 순서에 따라 오름차순 정렬

  const waiting = [];

  while (index < jobs.length || waiting.length > 0) {
    if (index < jobs.length && now >= jobs[index][0]) {
      waiting.push(jobs[index]);
      index += 1;

      waiting.sort((a, b) => a[1] - b[1]); // 그리디하게 소요시간이 적은 것부터 처리
      continue;
    }

    if (!waiting.length) {
      now = jobs[index][0];
    } else {
      const first = waiting.shift();
      sum += now - first[0] + first[1];
      now += first[1];
    }
  }

  return Math.floor(sum / length);
}
