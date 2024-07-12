function solution(places) {
    const dir = [[0, -1],[0, 1],[1, 0],[-1, 0]];
    
    const isOOB = (i, j) => i < 0 || i >= 5 || j < 0 || j >= 5;
  
    const isAvailable = (i, j, checked) => !isOOB(i, j) && checked[i][j] === 0;
  
    const BFS = (start, room, checked) => {
      let q = [start];
    
      while (q.length > 0) {
        const [i, j, n] = q.shift();
    
        if (n !== 0 && room[i][j] === "P") return false;
    
        dir.forEach(([di, dj]) => {
          const ni = i + di;
          const nj = j + dj;
    
          if (isAvailable(ni, nj, checked) && room[ni][nj] !== "X") {
            if (n < 2) {
              checked[ni][nj] = 1;
              q.push([ni, nj, n + 1]);
            }
          }
        });
      }
    
      return true;
    };
  
    const checkDistance = (room) => {
      let checked = Array.from({ length: 5 }, () => Array(5).fill(0));
      for (let i = 0; i < 5; i++) {
        for (let j = 0; j < 5; j++) {
          if (room[i][j] !== "P") continue;
          checked[i][j] = 1;
          if (!BFS([i, j, 0], room, checked)) return 0;
        }
      }
      return 1;
    };
  
    return places.map(checkDistance);
  }
  