import java.io.*;
import java.util.*;
public class 0317_곽희웅 {
    /*
    접근 방식 : 최대 500x500의 배열을 탐색해서 양쪽+밑쪽이 막혀있으면 빗물을 담을 수 있도록 하는 문제
    자료 구조 : 특별한 자료 구조는 없고, List를 활용해서 탐색할 수 있는 범위를 최대한 한 번에 크게 할 수 있도록 함

    주안점
    1. 최대값 배열을 활용하여 1차원 배열 두 개로 값을 알아낼 수 있는 방법이 있는 것으로 보임
    2. 양쪽 끝은 항상 빗물이 안담기기 때문에 제외하면 조금이라도 더 시간을 줄일 수 있음
     */
    static int W, H, answer, map[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<W; i++) {
            int P = Integer.parseInt(st.nextToken());
            for(int j=H-1; j>H-1-P; j--) {
                map[j][i] = 1;
            }
        }

        answer = 0;
        for(int i=H-1; i>=0; i--) {
            for (int j=1; j<W-1; j++) {
                if(map[i][j] == 0) check(i, j);
            }
        }

        System.out.println(answer);
    }
    static void check(int x, int y) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[] {x, y});
        boolean Lflag = false;
        boolean Rflag = false;
        for(int i=y-1; i>=0; i--) {
            if(map[x][i] == 0) list.add(new int[] {x, i});
            else if(map[x][i] == 1) {
                Lflag = true;
                break;
            }
        }
        for(int i=y+1; i<W; i++) {
            if(map[x][i] == 0) list.add(new int[] {x, i});
            else if(map[x][i] == 1) {
                Rflag = true;
                break;
            }
        }

        if(Lflag && Rflag) {
            answer += list.size();
            for(int[] cur : list) {
                map[cur[0]][cur[1]] = 2;
            }
        } else {
            for(int[] cur : list) {
                map[cur[0]][cur[1]] = -1;
            }
        }

    }
}
