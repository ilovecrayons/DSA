import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import edu.ftdev.KeyInterceptor.KeyHook;
import edu.ftdev.Map.MapCanvas;

public class Program {

    static ArrayList<Set<Node>> coloredSets = new ArrayList<Set<Node>>();
    static Graph routes;

    /**
     * Lambda method which will be called each time the user
     * is pressing the key 'T'.
     * 
     * @param keyEvent - details about the key which was pressed.
     */
    private static KeyHook _onKeyT = (KeyEvent keyEvent, Object[] args) -> {
        MapCanvas mc = (MapCanvas) args[0];
        String statusText = "Key: '" + keyEvent.getKeyChar() + "'; ";
        statusText += "Routes: " + mc.getRoutes();
        mc.setStatusMessage(statusText);
    };

    private static KeyHook _onABCDE = (KeyEvent keyEvent, Object[] args) -> {
        MapCanvas mc = (MapCanvas) args[0];
        Set<String> allRoutes = mc.getRoutes();
        Set<String> ans = getRoutes(keyEvent.getKeyChar(), allRoutes);
        mc.setStatusMessage(ans.toString());
        mc.setOverlays(ans);
    };

    static int incrementor = 0;
    private static KeyHook _onW = (keyEvent, args) -> {
        MapCanvas mc = (MapCanvas) args[0];
        Set<Node> currentSet = coloredSets.get(incrementor);
        Set<String> currentRoutes = new TreeSet<String>();
        for (Node node : currentSet) {
            currentRoutes.add(node.name);
        }
        mc.setOverlays(currentRoutes);
        incrementor++;
        if (incrementor >= coloredSets.size()) {
            incrementor = 0;
        }
    };

    public static Set<String> getRoutes(char start, Set<String> allRoutes) {
        Set<String> ans = new TreeSet<String>();
        for (String route : allRoutes) {
            if (route.charAt(0) == start)
                ans.add(route);
        }
        return ans;
    }

    static String originalRoute;

    private static KeyHook _onKeyX = (KeyEvent keyEvent, Object[] args) -> {
        MapCanvas mc = (MapCanvas) args[0];
        Set<String> overlays = mc.getOverlays();
        Set<String> allRoutes = mc.getRoutes();
        Set<String> ans = new TreeSet<String>();
        if (overlays.size() == 1) {
            String overlayRoute = overlays.iterator().next();
            originalRoute = overlayRoute;
            ans.add(originalRoute);
            for (String route : allRoutes) {
                if (mc.collide(overlayRoute, route)) {
                    ans.add(route);
                }
            }
            mc.setOverlays(ans);
        } else {
            // remove the colliding routes
            mc.setOverlays(originalRoute);
        }
    };

    static Set<Node> greedy(int color, Queue<Node> unlabeledQueue) {
        Set<Node> ans = new HashSet<Node>();
        Set<Node> labeledSet = new HashSet<Node>();

        // Create a copy of nodes to process so we don't modify while iterating
        ArrayList<Node> nodesToProcess = new ArrayList<>(unlabeledQueue);

        for (Node node : nodesToProcess) {
            boolean connected = false;
            for (Node n : labeledSet) {
                if (node.neighbors.contains(n)) {
                    connected = true;
                    break;
                }
            }
            if (!connected) {
                ans.add(node);
                labeledSet.add(node);
                unlabeledQueue.remove(node);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // create a MapCanvas object and load it with an intersection image
        MapCanvas mapCanvas = new MapCanvas("Woodlawn.jpg");

        // registers the key T with the method _onKeyT
        mapCanvas.setKeyHook('T', _onKeyT, mapCanvas);

        // opens the GUI window
        mapCanvas.open();

        // break;step-level execution
        mapCanvas.breakStep();

        // register the 'A', 'B', 'C', .. key strokes for demo route highlights
        // mapCanvas.setDemoKeyHooks(true);
        mapCanvas.setKeyHook('A', _onABCDE, mapCanvas);
        mapCanvas.setKeyHook('B', _onABCDE, mapCanvas);
        mapCanvas.setKeyHook('C', _onABCDE, mapCanvas);
        mapCanvas.setKeyHook('D', _onABCDE, mapCanvas);
        mapCanvas.setKeyHook('E', _onABCDE, mapCanvas);
        mapCanvas.setKeyHook('X', _onKeyX, mapCanvas);
        mapCanvas.setKeyHook('W', _onW, mapCanvas);

        // for testing
        mapCanvas.setOverlays("AE");

        // init graph
        routes = new Graph();
        for (String route : mapCanvas.getRoutes()) {
            Node node = new Node(route);
            routes.addNode(node);
        }
        for (Node node : routes.getNodes()) {
            Set<Node> potentialNeighbors = routes.getNodes();
            for (Node route : potentialNeighbors) {
                if (mapCanvas.collide(node.name, route.name)) {
                    if (!node.name.equals(route.name)) {
                        routes.addEdge(node, route);
                    }
                }
            }
        }

        // graph coloring!
        int color = 0;
        Queue<Node> unlabeledQueue = new LinkedList<Node>();
        for (Node node : routes.getNodes()) {
            unlabeledQueue.add(node);
        }

        while (!unlabeledQueue.isEmpty()) {
            Set<Node> greedySet = new HashSet<Node>();
            greedySet.addAll(greedy(color, unlabeledQueue));
            if (greedySet.isEmpty()) {
                break;
            }
            coloredSets.add(greedySet);
            color++;
        }

        // break jump-level execution
        mapCanvas.breakJump();

        // close the window and terminate the program
        mapCanvas.close();
    }
}
