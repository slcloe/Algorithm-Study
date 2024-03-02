import java.util.*;
public class 0302_곽희웅 {

    /*
    접근 방식 : 최대 10000 * 10000의 배열로 구해지는데, 계속 가장 큰 배열을 구하고 슬라이싱하면 메모리 초과가 발생
               제한 조건에 2차원 배열의 크기에 대해서 알려주기 때문에 이용해서 map 크기 지정
    자료 구조 : 2차원 배열을 사용해서 숫자를 저장 후 출력

    주안점
    1. 배열 크기를 주어진 숫자를 사용하여 최소로 지정하지 않으면 메모리 초과 발생
    2. remain이라는 변수를 사용해서 현재 필요한 숫자를 구했는지 확인하는데, 0 0 0 0일 경우가 있기 때문에 dfs 호출 코드 앞에 if문 배치
    3. 모든 숫자의 행은 길이가 같아야 하기 때문에 제일 마지막에 구한 숫자의 길이와 비교하여 빈 칸을 채워줌
    */

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static int idx, limit, order, remain, map[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R1 = sc.nextInt();
        int C1 = sc.nextInt();
        int R2 = sc.nextInt();
        int C2 = sc.nextInt();
        int xSide = R2-R1+1;
        int ySide = C2-C1+1;
        map = new int[xSide][ySide];
        // 채워야하는 배열의 값 개수
        remain = xSide * ySide;
        // 대입하는 값
        order = 1;
        // 방향값
        idx = 0;
        // 현재 이동거리
        limit = 1;
        // 첫 좌표 입력
        if(R1 <= 0 && C1 <= 0 && R1 * -1 < xSide && C1 * -1 < ySide) {
            map[R1 * -1][C1 * -1] = order++;
            remain--;
        }
        else order++;

        // 현재 x좌표, 현재 y좌표, 이동거리 충족 횟수, x 변의 크기, y 변의 크기
        if(remain > 0) dfs(R1 * -1, C1 * -1, 0, xSide, ySide);

        int size = String.valueOf(order).length();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<(R2-R1+1); i++) {
            for(int j=0; j<(C2-C1+1); j++) {
                String num = String.valueOf(map[i][j]);
                if(num.length() < size) for(int k=0; k<size - num.length(); k++) sb.append(" ");
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static void dfs(int x, int y, int count, int xSide, int ySide) {
        if(count == 2) {
            count = 0;
            limit++;
        }
        for(int i=0; i<limit; i++) {
            x += dx[idx];
            y += dy[idx];
            if(x >= 0 && y >= 0 && x < xSide && y < ySide) {
                map[x][y] = order++;
                if(--remain == 0) return;
            } else order++;
        }
        count++;
        idx = (idx+1)%4;
        dfs(x, y, count, xSide, ySide);
    }
}
