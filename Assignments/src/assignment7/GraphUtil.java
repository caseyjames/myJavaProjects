package assignment7;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Utility class containing methods for operating on graphs.
 * <p/>
 * Depth-first-search routine - to find a path between two vertices in a graph
 * Breadth-first-search routine - to find the shortest path between two vertices in a graph
 * Dijkstra's cheapest path routine - to find the cheapest path between two vertices in a graph
 * Topological sort - to produce a topologically sorted list of all vertices in a graph
 * Generating random graphs routine - to generate parameterized random graph for testing
 * Building graphs from file routine - to create and build a graph object from a valid dot file
 *
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class GraphUtil {
    /**
     * Performs a depth-first search of a graph to determine a path from a start vertex to an goal vertex.
     * (See Lecture 18 for the algorithm.)
     *
     * @param graph     - The graph object to be traversed
     * @param startName - Name of the starting vertex in the path
     * @param goalName  - Name of the ending vertex in the path
     * @return a list of the vertices that make up a path path from the vertex with the name startName (inclusive)
     * to the ending vertex with the name goalName (inclusive)
     * @throws UnsupportedOperationException if there are no vertices in the graph with the names startName or goalName
     */
    public static List<String> depthFirstSearch(Graph graph, String startName, String goalName) {
        //ArrayList to hold vertices in path in the correct order from startVertex to goalVertex
        ArrayList<String> path = new ArrayList<String>();

        // copy of this graphs HashMap so the original field values of the vertices are not changed.
        HashMap<String,Vertex> map = new HashMap<String,Vertex>(graph.getVertices());

        // throw exception of startName and goalName are not associated with any vertices the HashMap
        if (!(map.containsKey(startName)) || !(map.containsKey(goalName)))
            throw new UnsupportedOperationException("The startName or goalName do not exist!");

        // store vertex at startName & goalName to pass to depthFirstSearchRecursive
        Vertex start = map.get(startName);
        Vertex goal = map.get(goalName);

        // set start vertex to visited to avoid cycles
        start.setVisited(true);

        // call recursive method
        depthFirstSearchRecursive(start, goal);

        //after recursive call, if goal vertex has not been visited, state no path found and return empty list
        if (! goal.getVisited()) {
//            System.out.println("There was no path found from the vertex " + startName + " to the vertex " + goalName + "!");
            return path;
        }

        //LinkedList to hold the vertex names of the found path in reverse
        LinkedList<String> reversePath = new LinkedList<String>();

        // first add the goal before looping
        reversePath.add(goal.getName());
        // continuous loop until goal equal startVertex
        while (! goal.getName().equals(startName)) {
            reversePath.addLast(goal.getCameFrom().getName());
            goal = goal.getCameFrom();
        }

        // remove last items adding to path
        while (!reversePath.isEmpty())
            path.add(reversePath.removeLast());

        //return completed array list
        return path;
    }

    /**
     * Recursive method that is called by the depthFirstSearch driver method above.
     *
     * @param currentVertex start vertex or current vertex to traverse from to reach the goal vertex.
     * @param goalVertex    the vertex trying to be reach by a graph path from current or start vertex.
     */
    private static void depthFirstSearchRecursive(Vertex currentVertex, Vertex goalVertex) {
        // check if currVertex is goal
        if (currentVertex.equals(goalVertex))
            return;

        //get an iterator for the adjacent edges
        Iterator<Edge> currentEdges = currentVertex.edges();

        // edge to hold the current edge that the iterator returned
        Edge nextEdge;
        // next vertex to visit
        Vertex nextVertex;

        //while there are more edges to iterate through & the goal vertex hasn't been visited, call recursive method
        while (currentEdges.hasNext()/* && goalVertex.getVisited() == false*/) {
            //set this edge to next edge that the iterator returns
            nextEdge = currentEdges.next();
            if (nextEdge.getOtherVertex().getVisited() == false) {     //if the edge points to an unvisited vertex,
                nextVertex = nextEdge.getOtherVertex();             //visit vertex with recursive method
                nextVertex.setVisited(true);
                nextVertex.setCameFrom(currentVertex);
                // call recursive method
                depthFirstSearchRecursive(nextVertex, goalVertex);
            }
        }
        //return if goal is reached or there are no other vertices to visit.
        return;
    }

    /**
     * Performs a breadth-first search on a graph to determine the shortest path from a start vertex to an goal vertex.
     * (See Lecture 18 for the algorithm.)
     *
     * @param graph     - The graph object to be traversed
     * @param startName - Name of the starting vertex in the path
     * @param goalName  - Name of the ending vertex in the path
     * @return a list of the vertices that make up the shortest path from the vertex with the name startName (inclusive)
     * to the ending vertex with the name goalName (inclusive)
     * @throws UnsupportedOperationException if there are no vertices in the graph with the names startName or goalName
     */
    public static List<String> breadthFirstSearch(Graph graph, String startName, String goalName) {
        //ArrayList to hold vertices in path in the correct order from startVertex to goalVertex
        ArrayList<String> path = new ArrayList<String>();

        // get graph HashMap to access the vertices by name
        HashMap<String,Vertex> map = new HashMap<String,Vertex>(graph.getVertices());
        // throw exception of startName and goalName are not associated with any vertices the HashMap
        if (!(map.containsKey(startName)) || !(map.containsKey(goalName)))
            throw new UnsupportedOperationException("The startName or goalName do not exist!");

        // queue to traverse through and visit neighbors breadth first,implemented with LinkedList
        LinkedList<Vertex> Q = new LinkedList<Vertex>() {
        };

        // current vertex, most recently dequeued from list, beginning vertex obtained from map, and neighbor vertex
        Vertex current, neighbor, start = map.get(startName), goal = map.get(goalName), finalVertex = map.get(startName);

        // set start vertex as visited and enque on to queue
        start.setVisited(true);
        Q.add(start);
        // set current to start node at first to avoid
        current = start;

        // keep visiting neighbors while the queue is not empty
        while (!Q.isEmpty()) {
            current = Q.removeFirst();
            //check if current is equal to goal, if so break from while loop, if not iterate through neighbors
            if (current.equals(goal))
                finalVertex = current;
            // get iterator to traverse neighboring edges
            Iterator<Edge> itr = current.edges();

            // iterate each neighbor, through the edges, if unvisited, then visit it and enque it
            while (itr.hasNext()) {
                // set neighbor to next pointed to vertex
                neighbor = itr.next().getOtherVertex();
                // if pointed to vertex is unvisited, visit it update cameFrom, and enque to queue
                if (!neighbor.getVisited()) {
                    neighbor.setCameFrom(current);
                    neighbor.setVisited(true);
                    Q.addLast(neighbor);
                }
            }
        }

        /*check if Q emptied and goal was never reached, meaning there was no path from start to goal
        state this and return the empty list.*/
        if (! finalVertex.equals(goal)) {
//            System.out.println("There was no path found from the vertex " + startName + " to the vertex " + goalName + "!");
            return path;
        }

        //LinkedList to hold the vertex names of the found path in reverse
        LinkedList<String> reversePath = new LinkedList<String>();

        // first add the goal before looping
        reversePath.addFirst(finalVertex.getName());
        // continuous loop until finalVertex equals startVertex
        while (! finalVertex.equals(start)) {
            reversePath.addLast(finalVertex.getCameFrom().getName());
            finalVertex = finalVertex.getCameFrom();
        }

        // remove last items adding to path
        while (!reversePath.isEmpty())
            path.add(reversePath.removeLast());

        //return completed array list
        return path;
    }

    /**
     * Performs Dijkstra's routine on a weighted graph to determine the cheapest path from start vertex to a goal vertex.
     * (See Lecture 19 for the algorithm.)
     * <p/>
     * Uses Java's PriorityQueue class to find the "unvisited vertex with smallest distance from start".
     * See the API for PriorityQueue, and ask the course staff if you need help.
     *
     * @param graph     - The graph object to be traversed
     * @param startName - Name of the starting vertex in the path
     * @param goalName  - Name of the ending vertex in the path
     * @return a list of the vertices that make up the cheapest path from the starting vertex (inclusive) to the
     * ending vertex (inclusive) based on weight associated with the edges between the graphs vertices
     * @throws UnsupportedOperationException if the graph is not weighted, or there are no vertices in the graph
     *                                       with the names startName or goalName
     */
    public static List<String> dijkstrasShortestPath(Graph graph, String startName, String goalName) throws UnsupportedOperationException {
        // make sure the graph given is a supported type (positive weighted)
        if (!graph.getWeighted())
            throw new UnsupportedOperationException("Graph needs to be weighted for Dijkstra's algorithm!");
        // implement the algorithm with a priority queue
        HashMap<String,Vertex> vertices = graph.getVertices();
        if (!vertices.containsKey(startName))
            throw new UnsupportedOperationException("Dijkstra's: the start vertex " + vertices.get(startName) + " was not found!");
        if (!vertices.containsKey(goalName))
            throw new UnsupportedOperationException("Dijkstra's: the goal vertex " + vertices.get(goalName) + " was not found!");
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        Vertex startVertex = vertices.get(startName);
        startVertex.setCostFromStart(0);
        queue.add(startVertex);
        while (queue.size() != 0) {
            Vertex currentVertex = queue.poll();
            currentVertex.setVisited(true);

            // iterate through neighbor vertices and set fields appropriately
            Iterator<Edge> edgeIterator = currentVertex.edges();
            while (edgeIterator.hasNext()) {
                Edge neighborEdge = edgeIterator.next();
                Vertex neighborVertex = neighborEdge.getOtherVertex();

                // set the running cost and cameFrom iff the vertex hasn't been visited or the new path to that vertex is shorter
                int newCost = currentVertex.getCostFromStart() + neighborEdge.getWeight();
                if (!neighborVertex.getVisited() || neighborVertex.getCostFromStart() > newCost) {
                    neighborVertex.setCostFromStart(newCost);
                    neighborVertex.setVisited(true);
                    neighborVertex.setCameFrom(currentVertex);
                    if (queue.contains(neighborVertex)) // update the vertex if it's in the queue
                        queue.remove(neighborVertex);
                    queue.add(neighborVertex);
                }
            }
        }
        // construct the paths from the goal vertex backwards
        LinkedList<String> path = new LinkedList<String>();
        path.add(goalName);
        Vertex currentVertex = vertices.get(goalName);
        while (currentVertex.getCameFrom() != null) {
            path.addFirst(currentVertex.getCameFrom().getName());
            currentVertex = currentVertex.getCameFrom();
        }
        return path;
    }

    /**
     * Performs a topological sort of the vertices in a directed acyclic graph.
     * (See Lecture 19 for the algorithm.)
     *
     * @param graph - The graph object to be traversed
     * @return a list of the vertex names in topologically sorted order
     * @throws UnsupportedOperationException if the graph is undirected, or it is cyclic.
     */
    public static List<String> topologicalSort(Graph graph) {
        //first check that the specified graph is directed & acyclic, if not throw exception.
        if (!graph.getDirected() || isCyclic(graph))
            throw new UnsupportedOperationException("You cannot perform topological sort on an undirected or cyclic graph!");

        // get copy of HashMap to perform topologicalSort, this does not change original graphs HashMap values.
        HashMap<String,Vertex> map = new HashMap<String,Vertex>(graph.getVertices());
        // Collection of vertices to sort through
        Collection<Vertex> vertices = map.values();

        // queue to traverse through vertices with different levels of inDegree value
        Queue<Vertex> Q = new LinkedList<Vertex>();
        // ArrayList to hold sorted vertex names, size is initialized to amount of vertices.
        ArrayList<String> sortedNames = new ArrayList<String>(vertices.size());

        // enque the first vertices with inDegree of 0 to Queue.
        for (Vertex vertex : vertices)
            if (vertex.getInDegree() == 0)
                Q.add(vertex);

        //most recent vertex removed from the Q
        Vertex currentVertex;
        // perform topological sort algorithm until the queue is empty, as shown in lecture 19
        while (!Q.isEmpty()) {
            currentVertex = Q.remove();
            sortedNames.add(currentVertex.getName());
            LinkedList<Edge> currentEdges = currentVertex.getEdges();
            // decrement all neighbors inDegree by 1, if any neighbor now has inDegree of 0, enqueue
            for (Edge e : currentEdges) {
                e.getOtherVertex().decInDegree();
                if (e.getOtherVertex().getInDegree() == 0)
                    Q.add(e.getOtherVertex());
            }
        }
        // topological sort is done and return sortedNames list.
        return sortedNames;
    }

    /**
     * Builds a graph according to the edges specified in the given DOT file (e.g., "a -- b" or "a -> b").
     * Accepts directed ("digraph") or undirected ("graph") graphs.
     * <p/>
     * Accepts many valid DOT files (see examples posted with assignment).
     * --accepts \\-style comments
     * --accepts one edge per line or edges terminated with ;\
     * --accepts label attributes (e.g., [label = "a label"]) for weights
     *
     * @param filename - name of the DOT file
     */
    public static void generateGraphInDotFile(String filename, int vertexCount, int edgeDensity, boolean directed, boolean acyclic, boolean weighted) {
        PrintWriter out = null;

        try {
            out = new PrintWriter(filename);
        } catch (Exception e) {
            System.out.print("Unable to utilize the graph .dot file name: ");
            System.err.println(e.getMessage());
        }

        Vertex[] vertex = new Vertex[vertexCount];
        Random rng = new Random();

        String edgeOp = "--";

        if (directed) {
            out.print("di");
            edgeOp = "->";
        }

        out.println("graph G {");
        out.println("dpi=300");

        for (int i = 0; i < vertexCount; i++)
            vertex[i] = new Vertex("v" + i);

        int rand1 = rng.nextInt(vertexCount);
        int rand2 = rng.nextInt(vertexCount);

        if (acyclic)
            for (int i = 0; i < edgeDensity * vertexCount; i++) {
                rand1 = rng.nextInt(vertexCount);
                rand2 = rng.nextInt(vertexCount);

                for (Edge e : vertex[rand1].getEdges())
                    while (e.getOtherVertex().equals(vertex[rand2]))
                        rand2 = rng.nextInt(vertexCount);

                while (rand2 <= rand1) {
                    rand1 = rng.nextInt(vertexCount);
                    rand2 = rng.nextInt(vertexCount);

                    for (Edge e : vertex[rand1].getEdges())
                        while (e.getOtherVertex().equals(vertex[rand2]))
                            rand2 = rng.nextInt(vertexCount);
                    if (rand2 <= rand1)
                        rand2 = rng.nextInt(vertexCount);
                }

                vertex[rand1].addEdge(vertex[rand2]);

                out.print("\t" + vertex[rand1].getName() + edgeOp + vertex[rand2].getName());

                if (weighted)
                    out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");

                out.print("\n");
            }
        else
            for (int i = 0; i < edgeDensity * vertexCount; i++) {
                rand1 = rng.nextInt(vertexCount);
                rand2 = rng.nextInt(vertexCount);

                while (rand2 == rand1) {
                    rand2 = rng.nextInt(vertexCount);
                }
                out.print("\t" + vertex[rand1].getName() + edgeOp + vertex[rand2].getName());

                if (weighted)
                    out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");

                out.print("\n");
            }

        out.println("}");
        out.close();
    }

    /**
     * Builds a graph according to the edges specified in the given DOT file (e.g., "a -- b" or "a -> b").
     * Accepts directed ("digraph") or undirected ("graph") graphs.
     * <p/>
     * Accepts many valid DOT files (see examples posted with assignment).
     * --accepts \\-style comments
     * --accepts one edge per line or edges terminated with ;
     * --does not accept attributes in [] (e.g., [label = "a label"])
     *
     * @param filename - name of the DOT file
     */
    public static Graph buildGraphFromDotFile(String filename) {
        Graph g = new Graph();

        Scanner s = null;
        try {
            s = new Scanner(new File(filename)).useDelimiter(";|\n");
        } catch (Exception e) {
            System.out.print("Unable to utilize the graph .dot file: ");
            System.err.println(e.getMessage());
        }

        // Determine if graph is directed or not (i.e., look for "digraph id {" or "graph id {")
        String line = "", edgeOp = "";

        while (s.hasNext()) {
            line = s.next();

            // Skip //-style comments.
            line = line.replaceFirst("//.*", "");

            if (line.indexOf("digraph") >= 0) {
                g.setDirected(true);
                edgeOp = "->";
                line = line.replaceFirst(".*\\{", "");
                break;
            }
            if (line.indexOf("graph") >= 0) {
                g.setDirected(false);
                edgeOp = "--";
                line = line.replaceFirst(".*\\{", "");
                break;
            }
        }

        line = s.next();
        boolean weighted = line.contains("label");

        if (weighted)
            g.setWeighted(true);

        // Look for edge operators -- (or ->) and determine the left and right vertices for each edge.
        while (s.hasNext()) {
            String[] substring2 = null;
            String[] substring = line.split(edgeOp);

            if (weighted) {
                substring2 = line.split(" ");
                substring = substring2[0].split(edgeOp);
            }

            for (int i = 0; i < substring.length - 1; i += 2) {
                // remove " and trim whitespace from node string on the left
                String vertex1 = substring[0].replace("\"", "").trim();
                if (vertex1.equals(""))
                    continue;

                String vertex2 = substring[1].replace("\"", "").trim();
                if (vertex2.equals(""))
                    continue;

                if (weighted) {
                    String[] substring3 = substring2[1].split("=");
                    int weight = Integer.parseInt(substring3[1].replace("]", "").trim());
                    g.addEdgeWeighted(vertex1, vertex2, weight);
                } else
                    g.addEdge(vertex1, vertex2);
            }

            if (substring[substring.length - 1].indexOf("}") >= 0)
                break;

            line = s.next();

            // Skip //-style comments.
            line = line.replaceFirst("//.*", "");
        }

        return g;
    }

    /**
     * Determines if the specified graph is cyclic or not.
     *
     * @return true if the specified graph is cyclic, otherwise false.
     */
    public static boolean isCyclic(Graph graph) {

        // create copy of this graphs HashMap
        HashMap<String,Vertex> map = new HashMap<String,Vertex>(graph.getVertices());

        // array list to hold all the vertices in this graph, used as starting vertices.
        Collection<Vertex> allVertices = map.values();
        // amount of vertices in this graph.
        int vAmount = allVertices.size();

        // array list matrix to hold lists of neighbors for each starting vertex
        ArrayList<Vertex>[] neighbors = new ArrayList[vAmount];
        // initiate each column to contain an empty ArrayList so that no columns are null.
        for (int i = 0; i < vAmount; i++)
            neighbors[i] = new ArrayList<Vertex>();

        int index = 0;
        // fill neighbors matrix
        for (Vertex vertex : allVertices) {
            LinkedList<Edge> neighborItr = vertex.getEdges();
            for (Edge edge : neighborItr)
                neighbors[index].add(edge.getOtherVertex());
            index++;
        }

        // check if there are any paths from each vertexes neighbor back to that vertex.
        index = 0;
        for (Vertex vertex : allVertices) {
            for (Vertex neighbor : neighbors[index])
                if (hasPath(graph, vertex.getName(), neighbor.getName()))
                    return true;
            index++;
        }
        // no paths found, return cyclic value (default false)
        return false;
    }

    /**
     * Determines whether there is a path between the specified start and goal vertices in the specified graph.
     *
     * @return true if there is a path between the specified start and goal vertices in the specified graph, otherwise
     * false.
     */
    public static boolean hasPath(Graph graph, String startName, String goalName) {
        // get graph HashMap to access the vertices by name
        HashMap<String,Vertex> map = new HashMap<String,Vertex>(graph.getVertices());
        // throw exception of startName and goalName are not associated with any vertices the HashMap
        if (!(map.containsKey(startName)) || !(map.containsKey(goalName)))
            throw new UnsupportedOperationException("The startName or goalName do not exist!");

        // queue to traverse through and visit neighbors breadth first,implemented with LinkedList
        LinkedList<Vertex> Q = new LinkedList<Vertex>() {
        };

        // current vertex, most recently dequeued from list, beginning vertex obtained from map, and neighbor vertex
        Vertex current, neighbor, start = map.get(startName), goal = map.get(goalName);

        // set start vertex as visited and enque on to queue
        start.setVisited(true);
        Q.add(start);
        // set current to start node at first to avoid
        current = start;

        // keep visiting neighbors while the queue is not empty
        while (!Q.isEmpty()) {
            current = Q.removeFirst();
            //check if current is equal to goal, if so break from while loop, if not iterate through neighbors
            if (current.equals(goal))
                break;
            // get iterator to traverse neighboring edges
            Iterator<Edge> itr = current.edges();

            // iterate each neighbor, through the edges, if unvisited, then visit it and enque it
            while (itr.hasNext()) {
                // set neighbor to next pointed to vertex
                neighbor = itr.next().getOtherVertex();
                // if pointed to vertex is unvisited, visit it update cameFrom, and enque to queue
                if (!neighbor.getVisited()) {
                    neighbor.setCameFrom(current);
                    neighbor.setVisited(true);
                    Q.addLast(neighbor);
                }
            }
        }

        //check if Q emptied and goal was never reached, meaning there was no path from start to goal and return false;
        if (!current.equals(goal))
            return false;

        // if current vertex equals the goal vertex than there is a path.
        return true;
    }
}