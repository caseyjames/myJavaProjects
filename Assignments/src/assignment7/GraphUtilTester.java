package assignment7;

import junit.framework.TestCase;

import java.util.List;

public class GraphUtilTester extends TestCase {


    public void testDepthFirstSearch() throws Exception {
        //graph object to hold generated graph using BuildGraphFromDotFile
        Graph testGraph = GraphUtil.buildGraphFromDotFile("examplegraph6.dot");
//        Collection<Vertex> theseVertices = testGraph.getVertices().values();
//        for (Vertex vertex : theseVertices)
//            System.out.println(vertex.getName());
        List<String> path = GraphUtil.depthFirstSearch(testGraph, "1", "3");
        System.out.println(path);
    }

    public void testBreadthFirstSearch() throws Exception {

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