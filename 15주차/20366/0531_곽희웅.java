import java.io.*;
import java.util.*;
public class Main_bj_20366 {

    /*
    접근 방식 : 처음엔 인덱스 쌍과 합한 값을 저장하는 pq만으로 풀려고 했는데, 같은 값인데 인덱스가 다른 수가 있는 경우가 exceptional case
              그래서 List로 수가 같으면 추가하도록 관리

    주안점
    1. 2중 for문으로 특정 눈사람을 만들고 사용하지 인덱스로 가장 가까운 값의 눈사람을 찾는 투 포인터로도 풀 수 있음
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) num[i] = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        List<int[]> list = new ArrayList<>();
        int count = 0;
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                pq.offer(new int[] {num[i] + num[j], count++});
                list.add(new int[] {i, j});
            }
        }

        int[] start = pq.poll();
        int before = start[0];
        List<Integer> idxList = new ArrayList<>();
        idxList.add(start[1]);
        int diff = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            for(int idx : idxList) {
                if(check(idx, cur[1], list)) {
                    diff = Math.min(diff, cur[0] - before);
                    break;
                }
            }
            if(before != cur[0]) {
                before = cur[0];
                idxList.clear();
            }
            idxList.add(cur[1]);
        }
        System.out.print(diff);
    }

    static boolean check(int idx1, int idx2, List<int[]> list) {
        int[] first = list.get(idx1);
        int[] second = list.get(idx2);
        return first[0] != second[0] && first[0] != second[1] && first[1] != second[0] && first[1] != second[1];
    }
}
