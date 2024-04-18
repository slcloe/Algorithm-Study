import java.util.*;
/*
문제풀이

1. Wagon 클래스 생성 ( 수레에 대한 정보 저장 (위치, 색깔, 방문배열))
2. red, blue 각각 wagon 객체를 생성하여 우선순위큐에 삽입
3. red, blue 의 가능한 이동 경로를 ArrayList에 저장 (함수 : alWagonCase)
  3-1) 만약 도착지점에 왔다면 depth 만 +1 하고 반환한다. ( 도착하면 이동할 필요가 없기 때문 )
4. 이중포문을 사용하여 우선순위큐에 offer 한다
  4-1) 이때 두 수레의 위치가 서로바뀌거나, 겹치는 경우는 제외

의문점
1. dx, dy 의 순서를 바꾸니 답이 달라졌다.
2. 이유는 
            // wagon.depth++;
            // wagons.add(wagon);
  이 부분 때문이였는데, 
  depth가 제대로 ++ 되지 않고 ArrayList에 add 가 되어서 생긴 문제다.

3. 왠지 모르겠따.ㅠ 알듯말듯

*/
class Solution {
    int[][] pos = new int[3][2];
    int n, m;
    int dx[] = new int[] {0, 0, -1, 1};
    int dy[] = new int[] {-1, 1, 0, 0};
    
    class Wagon{
        int pos[];
        boolean v[][];
        int depth;
        int color;
        boolean cur;
        
        public Wagon(int x, int y, boolean v[][], int depth, int color, boolean cur){
            pos = new int[] {x, y};
            
            this.v = new boolean[n][m];
            for(int i = 0; i < n ;i++){
                for(int j = 0 ; j < m ;j++){
                    this.v[i][j] = v[i][j];   
                }
            }
            this.v[x][y] = true;
            this.depth = depth;
            this.color = color;
            this.cur = cur;
        }
    }
    
    ArrayList<Wagon> calWagonCase(Wagon wagon, int[][] maze) {
        ArrayList<Wagon> wagons = new ArrayList<>();
        boolean cur = false;
        
        if (wagon.cur){
            // wagon.depth++;
            // wagons.add(wagon);
            wagons.add(new Wagon(wagon.pos[0], wagon.pos[1], wagon.v, wagon.depth + 1, wagon.color, wagon.cur));
            return wagons;
        }
        
        for(int i = 0;i < 4; i++){
            int tx = dx[i] + wagon.pos[0];
            int ty = dy[i] + wagon.pos[1];
            
            if (tx < 0 || tx >= n || ty < 0 || ty >= m) continue;
            if (wagon.v[tx][ty]) continue;
            if (maze[tx][ty] == 5) continue;
            
            cur = wagon.cur;
            if (maze[tx][ty] == 2 + wagon.color) cur = true;
            wagons.add(new Wagon(tx, ty, wagon.v, wagon.depth + 1, wagon.color, cur));
        }
        // System.out.println("size" + wagons.size());
       return wagons;  
    }
    
    
    int bfs(int[][] maze) {
        
        PriorityQueue<Wagon[]> pq = new PriorityQueue<>((o1, o2) -> (o1[0].depth - o2[0].depth ));
        
        Wagon red = new Wagon(pos[1][0], pos[1][1], new boolean[n][m], 0, 1, false);
        Wagon blue = new Wagon(pos[2][0], pos[2][1], new boolean[n][m], 0, 2, false);
        pq.offer(new Wagon[]{red, blue});
        
        while(!pq.isEmpty()){
            Wagon[] wagon = pq.poll();
            
            System.out.println(wagon[0].depth + " " + wagon[1].depth);
            System.out.println(wagon[0].pos[0] + " " + wagon[0].pos[1]);
            System.out.println(wagon[1].pos[0] + " " + wagon[1].pos[1]);
            System.out.println();
            if (wagon[0].cur && wagon[1].cur)
                return wagon[0].depth;
            
            ArrayList<Wagon> redWagon = calWagonCase(wagon[0], maze);
            ArrayList<Wagon> blueWagon = calWagonCase(wagon[1], maze);
            
            if (redWagon.size() == 0 || blueWagon.size() == 0) continue;
            
            for(int i = 0;i < redWagon.size(); i++){
                red = redWagon.get(i);
                for(int j = 0; j < blueWagon.size();j++){
                    blue = blueWagon.get(j);
                    if (red.pos[0] == blue.pos[0] && red.pos[1] == blue.pos[1])
                        continue;
                    if (red.pos[0] == wagon[1].pos[0] && red.pos[1] == wagon[1].pos[1]
                       && blue.pos[0] == wagon[0].pos[0] && blue.pos[1] == wagon[0].pos[1])
                        continue;
                     pq.offer(new Wagon[]{red, blue});
                 }
            }
        }
        
        return 0;
    }
    
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        
        for (int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if (maze[i][j] == 1 || maze[i][j] == 2) {
                    pos[maze[i][j]][0] = i;
                    pos[maze[i][j]][1] = j;
                }
            }
        }

        return bfs(maze);
    }
}
