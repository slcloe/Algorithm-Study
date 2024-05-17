package club12;

import java.io.*;
import java.util.*;

class Node {
    int p;
    int q;
    int room;
    public Node(int p, int q) {
        this.p = p;
        this.q = q;
    }
}

public class BOJ12764 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Node> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            arr.add(new Node(p, q));
        }

        Collections.sort(arr, Comparator.comparingInt(o -> o.p));

        PriorityQueue<Node> rooms = new PriorityQueue<>(Comparator.comparingInt(o -> o.q));
        PriorityQueue<Node> candidates = new PriorityQueue<>(Comparator.comparingInt(o -> o.room));
        int[] roomCnt = new int[n];

        int roomNo = 0;
        for (Node cur : arr) {
            while (!rooms.isEmpty() && rooms.peek().q < cur.p)
                candidates.add(rooms.poll());
            int selectedRoomNo = candidates.isEmpty() ? roomNo++ : candidates.poll().room;
            roomCnt[selectedRoomNo]++;
            cur.room = selectedRoomNo;
            rooms.add(cur);
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < n && roomCnt[i] != 0; i++, cnt++) {
            sb.append(roomCnt[i]).append(' ');
        }
        System.out.println(cnt);
        System.out.println(sb);
    }
}