package assignment9;

import java.util.*;

/**
 * Represents a graph structure (a set of vertices each with a set of edges).
 *
 * @author Paymon Saebi
 */
public class Graph {
    /**
     * The graph underlying structure is a HashMap
     * Holds a set of vertices (String name mapped to Vertex instance)
     */

    private HashMap<String, Vertex> vertices;
    private boolean isDirected, isWeighted;

    /**
     * Constructs an empty graph.
     * <p/>
     * Instantiates a new HashMap structure to hold the graph's vertices
     */
    public Graph() {
        vertices = new HashMap<String, Vertex>();
        isDirected = false;
        isWeighted = false;
    }

    /**
     * Set the graph's directed-ness indicator
     * <p/>
     * * @param directed - boolean indicating whether the graph will be directed
     */
    public void setDirected(boolean directed) {
        this.isDirected = directed;
    }

    /**
     * Get the graph's directed-ness indicator
     *
     * @return true if the graph is directed, false otherwise
     */
    public boolean getDirected() {
        return this.isDirected;
    }

    /**
     * Set the graph weighted-ness indicator
     *
     * @param weighted - boolean indicating whether the graph will be weighted
     */
    public void setWeighted(boolean weighted) {
        this.isWeighted = weighted;
    }

    /**
     * Get the graph weighted-ness indicator
     *
     * @return true if the graph is weighted, false otherwise
     */
    public boolean getWeighted() {
        return this.isWeighted;
    }

    /**
     * get the graphs list of vertices
     *
     * @return the HashMap structure holding the graph's vertices
     */
    public HashMap<String, Vertex> getVertices() {
        return this.vertices;
    }

    /**
     * Adds to the graph an edge from the vertex with name "name1" to the vertex with name "name2".
     * If the graph is not directed also adds an edge from vertex with name "name2" to vertex with name "name1".
     * If either vertex does not already exist in the graph, it is added.
     *
     * @param name1 - name of the first vertex
     * @param name2 - name of the other vertex
     */
    public void addEdge(String name1, String name2) {
        Vertex vertex1;
        if (vertices.containsKey(name1))
            vertex1 = vertices.get(name1);
        else {
            vertex1 = new Vertex(name1);
            vertices.put(name1, vertex1);
        }

        Vertex vertex2;
        if (vertices.containsKey(name2))
            vertex2 = vertices.get(name2);
        else {
            vertex2 = new Vertex(name2);
            vertices.put(name2, vertex2);
        }

        vertex1.addEdge(vertex2);
        if (!isDirected)
            vertex2.addEdge(vertex1);
    }

    /**
     * Adds to the graph an edge from the vertex with name "name1" to the vertex with name "name2".
     * If the graph is not directed also adds an edge from vertex with name "name2" to vertex with name "name1".
     * TIf either vertex does not already exist in the graph, it is added.
     * The edge is associated with the "weight".
     *
     * @param name1  - name of the first vertex
     * @param name2  - name of the other vertex
     * @param weight - the cost to traverse the edge
     */
    public void addEdgeWeighted(String name1, String name2, int weight) {
        Vertex vertex1;
        if (vertices.containsKey(name1))
            vertex1 = vertices.get(name1);
        else {
            vertex1 = new Vertex(name1);
            vertices.put(name1, vertex1);
        }

        Vertex vertex2;
        if (vertices.containsKey(name2))
            vertex2 = vertices.get(name2);
        else {
            vertex2 = new Vertex(name2);
            vertices.put(name2, vertex2);
        }

        vertex1.addEdgeWeighted(vertex2, weight);
        if (!isDirected)
            vertex2.addEdgeWeighted(vertex1, weight);
    }

    /**
     * Performs Dijkstra's routine on a weighted graph to determine the cheapest path from start vertex to a goal vertex.
     * Uses PriorityQueueHEAP class to find the "unvisited vertex with smallest distance from start".
     *
     * @param startName - Name of the starting vertex in the path
     * @param goalName  - Name of the ending vertex in the path
     * @return a list of the vertices that make up the cheapest path from the starting vertex (inclusive) to the
     * ending vertex (inclusive) based on weight associated with the edges between the graphs vertices
     * @throws UnsupportedOperationException if the graph is not weighted, or there are no vertices in the graph
     *                                       with the names startName or goalName
     */
    public List<String> dijkstrasShortestPath(String startName, String goalName) throws UnsupportedOperationException {
        // assign graph variable to this graph
        Graph graph = this;
        // make sure the graph given is a supported type (positive weighted)
        if (!graph.getWeighted())
            throw new UnsupportedOperationException("Graph needs to be weighted for Dijkstra's algorithm!");
        // implement the algorithm with a PriorityQueueHEAP
        HashMap<String, Vertex> vertices = graph.getVertices();
        if (!vertices.containsKey(startName))
            throw new UnsupportedOperationException("Dijkstra's: the start vertex " + vertices.get(startName) + " was not found!");
        if (!vertices.containsKey(goalName))
            throw new UnsupportedOperationException("Dijkstra's: the goal vertex " + vertices.get(goalName) + " was not found!");

        // create a collection of all the vertices in the graph, set all vertices costFromStart to MAX_VALUE
        Collection<Vertex> vertexCollection = vertices.values();
        for (Vertex vrtx : vertexCollection) {
            vrtx.setCostFromStart(Integer.MAX_VALUE);
            vrtx.setCameFrom(null);
            vrtx.setVisited(false);
        }

        PriorityQueueHEAP<Vertex> queue = new PriorityQueueHEAP<Vertex>();
        Vertex startVertex = vertices.get(startName);
        startVertex.setCostFromStart(0);
        queue.add(startVertex);
        while (queue.size() != 0) {
            Vertex currentVertex = queue.deleteMin();
            currentVertex.setVisited(true);

            // iterate through neighbor vertices and set fields appropriately
            Iterator<Edge> edgeIterator = currentVertex.edges();
            while (edgeIterator.hasNext()) {
                Edge neighborEdge = edgeIterator.next();
                Vertex neighborVertex = neighborEdge.getOtherVertex();

                // if the vertex has been visited, continue to next neighbor
                if (neighborVertex.getVisited())
                    continue;
                // determine the new cost from the current vertex to this neighbor vertex
                int newCost = currentVertex.getCostFromStart() + neighborEdge.getWeight();
                // if it has not been visited yet, update vertex and add to priority queue, else just update vertex
                if (neighborVertex.getCostFromStart() > newCost && neighborVertex.getCameFrom() == null) {
                    neighborVertex.setCostFromStart(newCost);
                    neighborVertex.setCameFrom(currentVertex);
                    queue.add(neighborVertex);
                } else if (neighborVertex.getCostFromStart() > newCost && neighborVertex.getCameFrom() != null) {
                    neighborVertex.setCostFromStart(newCost);
                    neighborVertex.setCameFrom(currentVertex);
                }
            }
        }
        // construct the paths from the goal vertex backwards, uses addFirst so the returned list is in correct order.
        LinkedList<String> path = new LinkedList<String>();
        path.add(goalName);
        Vertex currentVertex = vertices.get(goalName);
        while (currentVertex.getCameFrom() != null) {
            path.addFirst(currentVertex.getCameFrom().getName());
            currentVertex = currentVertex.getCameFrom();
        }
        return path;
    }
}
