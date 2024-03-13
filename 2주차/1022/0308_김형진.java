import java.io.*;
import java.util.StringTokenizer;

// 문자열 폭발
public class BOJ_1022 {
    static int digit =0;
    static int r1, c1, r2, c2, startR, startC,rSize, cSize;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] map;

    static void tornado(int r, int c, int d, int maxCnt, int cnt,int step, int toGo, int toGoCnt){
        if(cnt == maxCnt) return;
        while(cnt < maxCnt){

            if(toGoCnt == toGo/2 || toGoCnt == toGo) d = (d+1)%4;

            int nr = r + dr[d];
            int nc = c + dc[d];

            if(r>= 0 && r<rSize && c>=0 && c< cSize){
                map[r][c] = step;
                cnt++;
            }
            if(toGoCnt == toGo){
                toGo = toGo+2;
                toGoCnt = 0;
            }
            toGoCnt++;
            r = nr;
            c = nc;
            step++;
        }
        digit = (int)Math.log10(step)+1;

    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb= new StringBuilder();
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        rSize = Math.abs(r1-r2)+1;
        cSize = Math.abs(c1-c2)+1;
        int maxCnt = rSize * cSize;
        map = new int[rSize][cSize];

        int rPlus = - r1;
        int cPlus = - c1;
        startR = rPlus;
        startC = cPlus;
        r1+= rPlus;
        c1+= cPlus;
        r2+= rPlus;
        c2+= cPlus;
        tornado(startR,startC, 0, maxCnt, 0, 1, 2, 0 );

        sb.append('%').append(digit).append('d');
        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < cSize; j++) {
                System.out.printf(sb.toString(),map[i][j]);
                if(j < cSize-1) System.out.print(" ");
            }System.out.println();
        }
    }
}