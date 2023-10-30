import java.util.*;

public class DirectedGraph {
    private Map<Integer, List<Edge>> graph = new HashMap<>();

    class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public void addEdge(int source, int target, int weight) {
        if (!graph.containsKey(source)) {
            graph.put(source, new ArrayList<>());
        }
        graph.get(source).add(new Edge(target, weight));
    }

    public void dijkstra(int source, int target) {
        Map<Integer, Integer> distance = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));
        distance.put(source, 0);

        for (int user : graph.keySet()) {
            if (user != source) {
                distance.put(user, Integer.MAX_VALUE);
            }
            previous.put(user, null);
            queue.offer(user);
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == target) {
                printShortestPath(source, target, previous, distance.get(target));
                return;
            }
            if (distance.get(current) == Integer.MAX_VALUE) {
                break;
            }

            for (Edge edge : graph.get(current)) {
                int alt = distance.get(current) + edge.weight;
                if (alt < distance.get(edge.target)) {
                    distance.put(edge.target, alt);
                    previous.put(edge.target, current);
                    queue.remove(edge.target);
                    queue.offer(edge.target);
                }
            }
        }

        System.out.println("The user " + source + " does not trust " + target + ".");
    }

    public void printShortestPath(int source, int target, Map<Integer, Integer> previous, int distance) {
        System.out.println("Total weight of the shortest trust path: " + distance);
        List<Integer> path = new ArrayList<>();
        int current = target;
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);
        System.out.print("Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TrustGraph trustGraph = new TrustGraph();

        // Add directed edges with weights from your dataset
        trustGraph.addEdge(1, 2, 3);
        trustGraph.addEdge(1, 3, 2);
        trustGraph.addEdge(2, 4, 5);
        trustGraph.addEdge(3, 4, 2);
        trustGraph.addEdge(4, 5, 1);

        int sourceID = 1;
        int targetID = 5;
        trustGraph.dijkstra(sourceID, targetID);
    }
}

