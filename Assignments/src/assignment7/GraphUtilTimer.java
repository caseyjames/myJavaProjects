package assignment7;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class GraphUtilTimer {
    public static void main(String[] args) {
        new GraphUtilTimer();
    }

    GraphUtilTimer() {
//        BFStimer();
        DFStimer();
//        dijkstrasTimer();
//        topologicalSortTimer();
    }

    void BFStimer() {
        int timesToLoop = 100;  // higher number causes more accurate average time
        int maxSize = 10000;   // determines right boundary of plot
        boolean isdone = false;
        Random rand = new Random(656794684984L); // used to create random lists
        List<String> names;
        String name1,name2;
        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime\tavg");

        // testing loop
        for (int i = 1000; i <= maxSize; i += 1000) {   // each of these loops accounts for a different input size 'N'

//            BinarySearchTree<Integer> randListBST;
            long startTime, midTime, endTime;
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here.
            startTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                isdone = GraphUtil.generateGraphInDotFile("testGraph.dot", i, 3, false, true, false);
                while (! isdone){}
                Graph testGraph = GraphUtil.buildGraphFromDotFile("testGraph.dot");
                HashMap<String,Vertex> keys = testGraph.getVertices();
                for (int k = 0; k < 200; k++) {
                    name1 = "v"; name2 = "v";
                    while (! keys.containsKey(name1))
                        name1 = "v" + (i - rand.nextInt(i));
                    while (! keys.containsKey(name2))
                        name2 = "v" + (i - rand.nextInt(i));
                    names = GraphUtil.breadthFirstSearch(testGraph, name1, name2);
                }
                isdone = false;
            }

            midTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                isdone = GraphUtil.generateGraphInDotFile("testGraph.dot", i, 3, false, true, false);
                while (! isdone){}
                Graph testGraph = GraphUtil.buildGraphFromDotFile("testGraph.dot");
                HashMap<String,Vertex> keys = testGraph.getVertices();
                for (int k = 0; k < 200; k++) {
                    name1 = "v"; name2 = "v";
                    while (! keys.containsKey(name1))
                        name1 = "v" + (i - rand.nextInt(i));
                    while (! keys.containsKey(name2))
                        name2 = "v" + (i - rand.nextInt(i));
                }
                isdone = false;
            }
            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results
        }
    }

    void DFStimer(){
        int timesToLoop = 10;  // higher number causes more accurate average time, but takes much longer
        int maxSize = 10000;   // determines right boundary of plot
        Random rand = new Random(); // used to create random lists

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime");

        // testing loops,each of these loops accounts for a different input size 'N'
        for (int i = 0; i <= maxSize; i += 5000) {
            if (i == 0) i = 1000; // allows i to equal 1000 then 5000 and then even 5000 increments after.

            // declare necessary variables, or clear existing ones to start fresh
            String name1, name2;
            long startTime, midTime, endTime;
            long seed = System.currentTimeMillis();  //use clock as initial starting point for the seed.
            rand.setSeed(seed);


            // this while loop runs for a full second to get things warmed up and running before timing starts
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) {/*empty*/}

            // startTime and testing start here
            startTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                GraphUtil.generateGraphInDotFile("testGraph.dot", i, 2, false, true, false);
                Graph testGraph = GraphUtil.buildGraphFromDotFile("testGraph.dot");
                HashMap<String,Vertex> keys = testGraph.getVertices();
                for (int k = 0; k < 100; k++) {
                    name1 = "v"; name2 = "v";
                    while (! keys.containsKey(name1))
                        name1 = "v" + (i - rand.nextInt(i));
                    while (! keys.containsKey(name2))
                        name2 = "v" + (i - rand.nextInt(i));
                    GraphUtil.depthFirstSearch(testGraph, name1, name2);
                }
            }

            midTime = System.nanoTime();   // midTime is set to aid in subtracting overhead
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {     // same loops run again without .get() to determine overhead
                GraphUtil.generateGraphInDotFile("testGraph.dot", i, 2, false, true, false);
                Graph testGraph = GraphUtil.buildGraphFromDotFile("testGraph.dot");
                HashMap<String,Vertex> keys = testGraph.getVertices();
                for (int k = 0; k < 100; k++) {
                    name1 = "v"; name2 = "v";
                    while (! keys.containsKey(name1))
                        name1 = "v" + (i - rand.nextInt(i));
                    while (! keys.containsKey(name2))
                        name2 = "v" + (i - rand.nextInt(i));
                }
            }
            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + avgTime);     // print results

            if (i == 1000) i = 0;  // used to allows 1000 to 5000 then even increments of 5000
        }
    }
}

