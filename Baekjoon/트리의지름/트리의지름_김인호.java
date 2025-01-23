import java.util.*;

public class Main {
    public static class Edge{
        int vertex,cost;
        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
    static int V,count;
    static int[]chk ,str;
    static ArrayList<ArrayList<Edge>> arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V=sc.nextInt();
        chk = new int[V+1];
        str = new int[V+1];
        Arrays.fill(chk,0);
        arr = new ArrayList<>();
        for (int i = 0; i < V+1; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            int start = sc.nextInt();
            while (true){
                int end = sc.nextInt();
                if (end == -1) {break;}
                int cost = sc.nextInt();
                arr.get(start).add(new Edge(end,cost));
            }
        }
        BFS(1);
        int max =Integer.MIN_VALUE;
        int maxIdx = 0;
        for (int i = 0; i < V+1; i++) {
            if(str[i]>max){
                max = str[i];
                maxIdx = i;
            }
        }
        Arrays.fill(str,0);
        Arrays.fill(chk,0);
        BFS(maxIdx);
        int z = Arrays.stream(str).max().getAsInt();
        System.out.println(z);
    }
    static void BFS(int x){
        Queue<Integer> q = new LinkedList<>();
        chk[x]=1;
        q.offer(x);
        while(!q.isEmpty()){
                int now = q.poll();
                for (int i = 0; i < arr.get(now).size(); i++) {
                    int nowVert = arr.get(now).get(i).vertex;
                    int nowCost = arr.get(now).get(i).cost;
                    if(chk[nowVert]==0){
                        chk[nowVert] =1;
                        q.add(nowVert);
                        str[nowVert] = str[now]+nowCost;
                    }
                }
        }
    }

}
