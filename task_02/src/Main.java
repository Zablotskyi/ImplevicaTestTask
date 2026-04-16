import java.util.*;

public class Main {

    //Method for finding the minimum path cost from start to end
    static int minCost(List<Edge>[] graph, int start, int end) {
        int[] dist = new int[graph.length];

        //Fill the distance array with a very large value
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        //Priority queue: always processes the city with the lowest current cost
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            int currentCity = current.city;
            int currentCost = current.cost;

            //If a shorter path to this city was already found, skip this one
            if (currentCost > dist[currentCity]) {
                continue;
            }

            //If we reached the destination city, return the result immediately
            if (currentCity == end) {
                return currentCost;
            }

            //Check all neighboring cities
            for (Edge edge : graph[currentCity]) {
                int nextCity = edge.to;
                int newCost = currentCost + edge.cost;

                //If a cheaper path is found, update the distance
                if (newCost < dist[nextCity]) {
                    dist[nextCity] = newCost;
                    pq.add(new Node(nextCity, newCost));
                }
            }
        }

        //Return the minimum cost to the destination city
        return dist[end];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int s = scanner.nextInt(); //number of test cases

        for (int test = 0; test < s; test++) {
            int n = scanner.nextInt(); //number of cities

            //Map for storing: city name -> city index
            Map<String, Integer> cityIndexMap = new HashMap<>();

            //Graph represented as an adjacency list
            List<Edge>[] graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            //Read all cities and their neighbors
            for (int i = 1; i <= n; i++) {
                String cityName = scanner.next();
                cityIndexMap.put(cityName, i);

                int p = scanner.nextInt(); //number of neighbors

                for (int j = 0; j < p; j++) {
                    int neighborIndex = scanner.nextInt();
                    int cost = scanner.nextInt();

                    graph[i].add(new Edge(neighborIndex, cost));
                }
            }

            int r = scanner.nextInt(); //number of path queries

            //Process all queries
            for (int i = 0; i < r; i++) {
                String sourceName = scanner.next();
                String destinationName = scanner.next();

                int source = cityIndexMap.get(sourceName);
                int destination = cityIndexMap.get(destinationName);

                int result = minCost(graph, source, destination);
                System.out.println(result);
            }
        }

        scanner.close();
    }
}