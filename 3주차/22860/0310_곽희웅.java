import java.io.*;
import java.util.*;
public class 0310_곽희웅 {

    /*
    접근 방식 : 메모리 제한이 1024MB, N, M, Q의 최대값이 1초인 것으로 보아 새로운 Set과 List를 선언하고 값을 할당해도 괜찮을 것이라고 고려
    자료 구조 : List<Integer>를 사용해서 Map에 저장한 서로 다른 폴더의 인덱스를 저장하고, List<String>, Set<String>을 통해 전체 파일의 개수와
               중복을 제거한 파일의 개수를 산정

    주안점
    1. 폴더와 파일은 같은 트리 내에서 다르게 관리해야하며 폴더도 경로는 다르지만 이름은 같은 폴더가 존재할 수 있음
    2. 입력이 주어질 때 main부터 순서대로 주어지지 않는 경우가 있음, 일반적인 경우에는 하위 폴더나 파일의 입력이 먼저 오는 경우 NullPointer가 발생
     */

    static class Node {
        String name;
        ArrayList<Integer> folders;
        ArrayList<String> files;

        Node(String name) {
            this.name = name;
            folders = new ArrayList<>();
            files = new ArrayList<>();
        }
    }

    static Node[] folders;
    static Map<String, Integer> map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node main = new Node("main");

        // 폴더 저장
        int idx = 0;
        folders = new Node[N+1];
        folders[idx] = main;
        // 폴더의 인덱스 저장
        map = new HashMap<>();
        map.put("main", idx);
        idx++;

        // 명령 저장
        String[][] orders = new String[N+M][3];

        for(int i=0; i<N+M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            orders[i][0] = st.nextToken();
            orders[i][1] = st.nextToken();
            orders[i][2] = st.nextToken();
        }

        for(int i=0; i<N+M; i++) {
            if(orders[i][2].equals("1")) {
                int parentIdx = map.getOrDefault(orders[i][0], -1);
                Node parent = null;
                if(parentIdx == -1) {
                    parent = new Node(orders[i][0]);
                    folders[idx] = parent;
                    map.put(orders[i][0], idx);
                    idx++;
                } else parent = folders[parentIdx];

                int childIdx = map.getOrDefault(orders[i][1], -1);
                if(childIdx == -1) {
                    Node child = new Node(orders[i][1]);
                    parent.folders.add(idx);
                    folders[idx] = child;
                    map.put(orders[i][1], idx);
                    idx++;
                } else parent.folders.add(childIdx);
            }
        }

        for(int i=0; i<N+M; i++) {
            if(orders[i][2].equals("0")) {
                Node parent = folders[map.get(orders[i][0])];
                parent.files.add(orders[i][1]);
            }
        }

        int Q = Integer.parseInt(br.readLine());
        for(int i=0; i<Q; i++) {
            String[] query = br.readLine().split("/");
            Node cur = null;

            for (String temp : query) {
                if (cur == null) {
                    cur = folders[0];
                } else {
                    for (int index : cur.folders) {
                        if (folders[index].name.equals(temp)) {
                            cur = folders[index];
                            break;
                        }
                    }
                }
            }

            ArrayList<String> resultFiles = new ArrayList<>();
            Set<String> resultSet = new HashSet<>();
            find(cur, resultFiles, resultSet);
            sb.append(resultSet.size()).append(" ").append(resultFiles.size()).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void find(Node cur, ArrayList<String> resultFiles, Set<String> resultSet) {
        for(int i : cur.folders) {
            find(folders[i], resultFiles, resultSet);
        }
        for(String file : cur.files) {
            resultFiles.add(file);
            resultSet.add(file);
        }
    }
}
