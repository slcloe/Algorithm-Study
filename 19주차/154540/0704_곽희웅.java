import java.util.*;
class Solution {
    
    /*
    접근 방식 : 전체 방문 배열을 하나 생성 후, 처리를 해주며 더해서 리스트에 넣고 정렬 후 배열 변환
    
    */
    
    static char[][] map;
    static List<Integer> list;
    static boolean[][] v;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public int[] solution(String[] maps) {
        map = new char[maps.length][maps[0].length()];
        v = new boolean[maps.length][maps[0].length()];
        list = new ArrayList<>();
        
        for(int i=0; i<maps.length; i++) {
            for(int j=0; j<maps[0].length(); j++) {
                map[i][j] = maps[i].charAt(j);
            }
        }
        
        for(int i=0; i<maps.length; i++) {
            for(int j=0; j<maps[0].length(); j++) {
                if(!v[i][j] && map[i][j] != 'X') bfs(i, j);
            }
        }
        if(list.isEmpty()) return new int[] {-1};
    
        Collections.sort(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    static void bfs(int x, int y) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {x, y});
        v[x][y] = true;
        int sum = 0;
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            sum += map[cur[0]][cur[1]] - '0';
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length && !v[nx][ny] && map[nx][ny] != 'X') {
                    dq.offer(new int[] {nx, ny});
                    v[nx][ny] = true;
                }
            }
        }
        list.add(sum);
    }
}