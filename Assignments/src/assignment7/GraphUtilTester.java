package assignment7;

import junit.framework.TestCase;

import java.util.List;

public class GraphUtilTester extends TestCase {


    public void testDepthFirstSearch() throws Exception {
        //graph object to hold generated graph using BuildGraphFromDotFile
        Graph testGraph = GraphUtil.buildGraphFromDotFile("examplegraph6.dot");
        List<String> path = GraphUtil.depthFirstSearch(testGraph, "1", "3");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Using depthFirstSearch for examplegraph6, path from vertex \"1\" to \"3\"" +
                           "\n - The path must be either [1,2,3],  [1,2,7,5,3],  or  [1,6,7,5,3]");
        System.out.println("\tActual path found is: " + path);
        System.out.println("--------------------------------------------------------------------------");
    }

    public void testBreadthFirstSearch() throws Exception {
        //graph object to hold generated graph using BuildGraphFromDotFile
        Graph testGraph = GraphUtil.buildGraphFromDotFile("examplegraph8.dot");
        List<String> path = GraphUtil.breadthFirstSearch(testGraph, "Salt Lake City", "San Diego");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Using breadthFirstSearch for examplegraph8, shortest path from vertex \n" +
                           "\t\t\tSalt Lake City - to - San Diego  " +
                           "\n - The path must be [Salt Lake City, Atlanta, San Diego], which is the shortest path");
        System.out.println("\tActual path found is: " + path);
        System.out.println("--------------------------------------------------------------------------");
    }

    public void testDijkstrasShortestPath() throws Exception {
        //graph object to hold generated graph using BuildGraphFromDotFile
        Graph testGraph = GraphUtil.buildGraphFromDotFile("hugeDirectedAcyclicWeighted.dot");
        List<String> path = GraphUtil.dijkstrasShortestPath(testGraph, "v0", "v199");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Using dijkstrasShortestPath for exampleGraph9, shortest path from vertex \n" +
                           "\t\t\tv0 - to - v199  " +
                           "\n - The path must be [v0, v13, v126, v134, v152, v164, v199], which is the shortest path");
        System.out.println("\tActual path found is: " + path);
        System.out.println("--------------------------------------------------------------------------");
    }

    public void testTopologicalSort() throws Exception {
        // first try it on the school course graph provided on moodle
        Graph examplegraph7 = GraphUtil.buildGraphFromDotFile("examplegraph7.dot");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Using topologicalSort on examplegraph6 to produce a sorted list of the it's vertices");
        System.out.println("\tThe sorted List is: " + GraphUtil.topologicalSort(examplegraph7));
        System.out.println("--------------------------------------------------------------------------");

        Graph topoTestGraph = GraphUtil.buildGraphFromDotFile("topoTest.dot");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Using topologicalSort on topoTest graph to produce a sorted list of the it's vertices");
        System.out.println("\tThe sorted List is: " + GraphUtil.topologicalSort(topoTestGraph));
        System.out.println("--------------------------------------------------------------------------");
    }
}