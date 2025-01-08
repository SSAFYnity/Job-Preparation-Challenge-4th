import java.util.*;
import java.io.*;
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        Set<Node> iceMountainSet = new HashSet<>();
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int height = Integer.parseInt(st.nextToken());
                if (height != 0) {
                    iceMountainSet.add(new Node(row, col, height));
                }
                map[row][col] = height;
            }
        }
        IceMountainCalculator iceMountainCalculator = new IceMountainCalculator(iceMountainSet, map);
        int answer = iceMountainCalculator.calculateMeltingDuration();
        System.out.println(answer);
    }
}
class IceMountainCalculator {
    private final Set<Node> iceMountainSet;
    private final int[][] map;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public IceMountainCalculator(Set<Node> iceMountainSet, int[][] map) {
        this.iceMountainSet = iceMountainSet;
        this.map = map;
    }

    public int calculateMeltingDuration() {
        int year = 0;
        if (isSeparated()) {
            return 0;
        }

        while (!iceMountainSet.isEmpty()) {
            meltIceMountains();
            year++;
            if (isSeparated()) {
                return year;
            }
        }
        return 0;
    }

    private void meltIceMountains() {
        List<Node> removingIceMountains = new ArrayList<>();

        for (Node node : iceMountainSet) {
            int newHeight = calculateNewHeight(node);
            if (newHeight <= 0) {
                removingIceMountains.add(node);
            } else {
                node.setHeight(newHeight);
            }
        }

        for (Node node : removingIceMountains) {
            map[node.x][node.y] = 0;
            iceMountainSet.remove(node);
        }
    }

    private int calculateNewHeight(Node node) {
        int x = node.x;
        int y = node.y;
        int height = node.height;

        for (int[] direction : DIRECTIONS) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            if (isValid(nx, ny) && map[nx][ny] == 0) {
                height--;
            }
        }
        return height;
    }

    private boolean isSeparated() {
        if (iceMountainSet.isEmpty()) {
            return false;
        }

        boolean[][] visited = new boolean[map.length][map[0].length];
        Node startNode = iceMountainSet.iterator().next();
        int connectedCount = traverseConnectedComponents(startNode, visited);

        return connectedCount != iceMountainSet.size();
    }

    private int traverseConnectedComponents(Node startNode, boolean[][] visited) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(startNode);
        visited[startNode.x][startNode.y] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            count++;

            for (int[] direction : DIRECTIONS) {
                int nx = current.x + direction[0];
                int ny = current.y + direction[1];
                if (isValid(nx, ny) && map[nx][ny] > 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny, map[nx][ny]));
                }
            }
        }
        return count;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
    }
}

class Node {
    int x;
    int y;
    int height;
    public Node(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object another) {
        if (another == null || another.getClass() != this.getClass()) {
            return false;
        }
        Node node = (Node) another;
        return this.x == node.x && this.y == node.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    @Override
    public String toString() {
        return "x: " + x +", y: " + y;
    }
}