package com.company;

import java.util.*;

/**
 * Date: 11/26/20
 * Time: 3:18 PM
 */
public class GraphTraversal {

    // 5 -> 6 -> 7


    static Set<String> depthFirstTraversal(Graph graph, String root) {
        // Stack


        Stack<String> stack = new Stack<>();
        LinkedHashSet<String> visited = new LinkedHashSet<>(); // preserves insertion order
        stack.push(root);

        while(!stack.isEmpty()) {
            String nodeLabel = stack.pop();
            if(!visited.contains(nodeLabel)) {
                visited.add(nodeLabel);
                List<GNode> nodesToTravel = graph.getAdjNodes(nodeLabel);
                nodesToTravel.forEach(node -> stack.push(node.label));
            }
        }

        return visited;
    }

    static Set<String> breathFirstTraversal(Graph graph, String root) {
        // Queue
        Queue<String> queue = new LinkedList<>();
        LinkedHashSet<String> visited = new LinkedHashSet<>(); // preserves insertion order

        queue.add(root);
        while(!queue.isEmpty()) {
            String nodeLabel = queue.poll();
            if(!visited.contains(nodeLabel)) {
                visited.add(nodeLabel);
                List<GNode> nodesToTravel = graph.getAdjNodes(nodeLabel);
                nodesToTravel.forEach(node -> queue.add(node.label));
            }
        }

        return visited;
    }


}


// 5 -> 6 -> 8

class Graph {
    private final Map<GNode, List<GNode>> adjVertices = new HashMap<GNode, List<GNode>>();

    String printGraph() {
        StringBuilder sb = new StringBuilder();

        adjVertices.keySet().forEach(key -> {
            sb.append(key.label);
            sb.append(adjVertices.get(key));
        });
        return sb.toString();
    }


    public void addNode(String label) {
        adjVertices.putIfAbsent(new GNode(label), new ArrayList<>());
    }

    public void removeNode(String label) {
        // remove
        // remove all the reference first
        adjVertices.values().stream().forEach(node -> node.remove(label));
        adjVertices.remove(new GNode(label));
    }

    public void addEdge(String l1, String l2) {
        GNode g1 = new GNode(l1);
        GNode g2 = new GNode(l2);
        adjVertices.get(g1).add(g2);
        adjVertices.get(g2).add(g1);
    }

    public void removeEdge(String l1, String l2) {
        GNode g1 = new GNode(l1);
        GNode g2 = new GNode(l2);
        adjVertices.get(g1).remove(g2);
        adjVertices.get(g2).remove(g1);
    }

    public List<GNode> getAdjNodes(String label) {
        return adjVertices.get(new GNode(label));
    }


}

class GNode {
    String label;
    GNode(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "GNode{" +
                "label='" + label + '\'' +
                '}';
    }

    /* Imp */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GNode gNode = (GNode) o;
        return Objects.equals(label, gNode.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}

