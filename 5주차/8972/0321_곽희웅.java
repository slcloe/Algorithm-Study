import java.io.*;
import java.util.*;
public class 0321_곽희웅 {
    /*
    접근 방식 : 최대 100x100 배열의 크기에서 계속 이동시켜주기보다는 좌표 자체로 계산하는 게 자원을 덜 낭비할 것이라고 고려
    자료 구조 : Deque을 써서 다른 아두이노들의 입출력 관리

    주안점
    1. 종수가 먼저 움직이고 다른 아두이노가 움직이며, 같은 위치에 겹쳤을 때 다 없애줘야 하기 때문에 옮긴 후에 처리해줘야 함
    2. 방향이 시계, 반시계가 아니라 키보드 넘패드 순서임
    3. 아두이노가 움직이는 좌표 값을 계산할 때 소괄호 주의
     */
    static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int R, C;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // 다른 아두이노들이 저장될 Deque
        Deque<int[]> dq = new ArrayDeque<>();
        int meX = 0; // 종수의 X 좌표
        int meY = 0; // 종수의 Y 좌표
        for(int i=0; i<R; i++) {
            String S = br.readLine();
            for(int j=0; j<C; j++) {
                if(S.charAt(j) == 'I') {
                    meX = i;
                    meY = j;
                } else if(S.charAt(j) == 'R') dq.offer(new int[] {i, j});
            }
        }
        String move = br.readLine();
        boolean flag = false;
        for(int i=0; i<move.length(); i++) {
            // 방향이 1부터 주어지는데, 방향 배열은 0부터이기 때문에 '1'
            meX += dx[move.charAt(i) - '1'];
            meY += dy[move.charAt(i) - '1'];
            if(moveArduino(dq, meX, meY)) {
                flag = true;
                System.out.println("kraj " + (i + 1));
                break;
            }
        }
        if(!flag) {
            StringBuilder sb = new StringBuilder();
            char[][] resultMap = new char[R][C];
            for(int i=0; i<R; i++) Arrays.fill(resultMap[i], '.');
            resultMap[meX][meY] = 'I';
            while(!dq.isEmpty()) {
                int[] cur = dq.poll();
                resultMap[cur[0]][cur[1]] = 'R';
            }
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    sb.append(resultMap[i][j]);
                }
                sb.append("\n");
            }
            System.out.print(sb.toString() );
        }
    }

    static boolean moveArduino(Deque<int[]> dq, int meX, int meY) {
        int size = dq.size();
        int[][] map = new int[R][C];
        while(size-- > 0) {
            int[] cur = dq.poll();
            int dir = 0;
            int min = Integer.MAX_VALUE;
            for(int i=0; i<9; i++) {
                if(min > Math.abs(meX-(cur[0]+dx[i])) + Math.abs(meY-(cur[1]+dy[i]))) {
                    min = Math.abs(meX-(cur[0]+dx[i])) + Math.abs(meY-(cur[1]+dy[i]));
                    dir = i;
                }
            }
            if(min == 0) return true;
            else {
                dq.offer(new int[] {cur[0]+dx[dir], cur[1]+dy[dir]});
                map[cur[0]+dx[dir]][cur[1]+dy[dir]]++;
            }
        }

        size = dq.size();
        for(int i=0; i<size; i++) {
            int[] cur = dq.poll();
            if(map[cur[0]][cur[1]] == 1) dq.offer(cur);
        }
        return false;
    }
}
