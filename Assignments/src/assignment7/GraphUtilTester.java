package assignment7;

import junit.framework.TestCase;

import java.util.List;

public class GraphUtilTester extends TestCase {


    public void testDepthFirstSearch() throws Exception {
        //graph object to hold generated graph using BuildGraphFromDotFile
        Graph testGraph = GraphUtil.buildGraphFromDotFile("examplegraph6.dot");
        System.out.println(GraphUtil.isCyclic(testGraph));
//        List<String> path = GraphUtil.depthFirstSearch(testGraph, "1", "3");
//        System.out.println("--------------------------------------------------------------------------");
//        System.out.println("Using depthFirstSearch for examplegraph6, path from vertex \"1\" to \"3\"" +
//                           "\n - The path must be either [1,2,3],  [1,2,7,5,3],  or  [1,6,7,5,3]");
//        System.out.println("\tActual path found is: " + path);
//        System.out.println("--------------------------------------------------------------------------");
//
//        //graph object to hold generated graph using BuildGraphFromDotFile
//        testGraph = GraphUtil.buildGraphFromDotFile("examplegraph6.dot");
//        List<String> path2 = GraphUtil.depthFirstSearch(testGraph, "1", "3");
//        System.out.println("--------------------------------------------------------------------------");
//        System.out.println("Using depthFirstSearch for examplegraph6, path from vertex \"1\" to \"3\"" +
//                           "\n - The path must be either [1,2,3],  [1,2,7,5,3],  or  [1,6,7,5,3]");
//        System.out.println("\tActual path found is: " + path2);
//        System.out.println("--------------------------------------------------------------------------");
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

    }

    public void testTopologicalSort() throws Exception {

    }

    public void testGenerateGraphInDotFile() throws Exception {

    }

    public void testBuildGraphFromDotFile() throws Exception {

    }
}