

import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        int startX = 0;
        int startY = 0;
        Set<Node> waterSet = new HashSet<>();
        for (int r = 0; r < R; r++) {
            String line = br.readLine();
            for (int c = 0; c < C; c++) {
                char k = line.charAt(c);
                map[r][c] = k;
                if (k == 'S') {
                    startX = r;
                    startY = c;
                } else if (k == '*') {
                    waterSet.add(new Node(r, c));
                }
            }
        }
        Node startPoint = new Node(startX, startY);
        Forest forest = new Forest(map, waterSet);
        System.out.println(forest.execute(startPoint));

    }
}
class Forest {
    char[][] map;
    Set<Node> waterSet;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public Forest(char[][] map, Set<Node> waterSet) {
        this.map = map;
        this.waterSet = waterSet;
    }

    public String execute(Node startingPoint) {
        List<Node> candidates = new ArrayList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        candidates.add(startingPoint);
        visited[startingPoint.x][startingPoint.y] = true;
        int count = 1;
        while(!candidates.isEmpty()) {
            Queue<Node> dq = new ArrayDeque<>(candidates);
            candidates.clear();
            setWaterPoint();
            while(!dq.isEmpty()) {
                Node node = dq.poll();
                for (int[] direction : directions) {
                    int nx = node.x + direction[0];
                    int ny = node.y + direction[1];
                    if (isValid(nx, ny) && !visited[nx][ny]) {
                        if (map[nx][ny] == '.') {
                            candidates.add(new Node(nx, ny));
                            visited[nx][ny] = true;
                        } else if (map[nx][ny] == 'D') {
                            return String.valueOf(count);
                        }
                    }
                }
            }
            count++;
        }
        return "KAKTUS";
    }
    private void setWaterPoint() {
        Set<Node> waterAddSet = new HashSet<>();
        for (Node water : waterSet) {
            for (int[] direction : directions) {
                int nx = water.x + direction[0];
                int ny = water.y + direction[1];
                Node addedWater = new Node(nx, ny);
                if (isValid(nx, ny) && map[nx][ny] != 'X' && map[nx][ny] != 'D' && map[nx][ny] != '*') {
                    waterAddSet.add(addedWater);
                    map[nx][ny] = '*';
                }
            }
        }
        this.waterSet = waterAddSet;
    }

    private boolean isValid(int x, int y) {
        int R = map.length;
        int C = map[0].length;
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
class Node {
    int x;
    int y;
    public Node (int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Node node = (Node) obj;
        return this.x == node.x && this.y == node.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}