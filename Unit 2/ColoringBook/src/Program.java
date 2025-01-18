//package ColoringBook;
import java.awt.Color;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Stack;

import DrawingLib.drawing.DrawingFrame;
import DrawingLib.drawing.MouseInterceptor.MouseHook;
import DrawingLib.drawing.Drawing;


public class Program {
    public static ArrayList<SimpleEntry<Integer, Integer>> getNeighbors(int xSeed, int ySeed){
        ArrayList<SimpleEntry<Integer, Integer>> neighbors = new ArrayList<SimpleEntry<Integer, Integer>>();
        neighbors.add(new SimpleEntry<Integer, Integer>(xSeed + 1, ySeed));
        neighbors.add(new SimpleEntry<Integer, Integer>(xSeed - 1, ySeed));
        neighbors.add(new SimpleEntry<Integer, Integer>(xSeed, ySeed + 1));
        neighbors.add(new SimpleEntry<Integer, Integer>(xSeed, ySeed - 1));
        neighbors.add(new SimpleEntry<Integer, Integer>(xSeed + 1, ySeed + 1));
        neighbors.add(new SimpleEntry<Integer, Integer>(xSeed - 1, ySeed - 1));
        neighbors.add(new SimpleEntry<Integer, Integer>(xSeed + 1, ySeed - 1));
        neighbors.add(new SimpleEntry<Integer, Integer>(xSeed - 1, ySeed + 1));
        return neighbors;
    }
    public static void recursiveFlood(int xSeed, int ySeed, Color color){
        _drawing.setPixel(xSeed, ySeed, color);
        ArrayList<SimpleEntry<Integer, Integer>>neighbors = getNeighbors(xSeed, ySeed);
        for(SimpleEntry<Integer, Integer> neighbor : neighbors){
            if(_drawing.isValidPixel(neighbor.getKey(), neighbor.getValue()) && _drawing.isBrightPixel(neighbor.getKey(), neighbor.getValue())){
                recursiveFlood(neighbor.getKey(), neighbor.getValue(), color);
                System.out.println("(" + neighbor.getKey() + "," + neighbor.getValue() + ")");
            }
        }
    }

    public static void stackFlood(int xSeed, int ySeed, Color color) {
        _drawing.setPixel(xSeed, ySeed, color);
        Stack<WorkItem> stack = new Stack<WorkItem>();
        stack.push(new WorkItem(xSeed, ySeed));
        while(!stack.isEmpty()) {
            WorkItem w = stack.pop();
            ArrayList<SimpleEntry<Integer, Integer>> neighbors = getNeighbors(w.x, w.y);
            for(SimpleEntry<Integer, Integer> neighbor : neighbors) {
                if(_drawing.isValidPixel(neighbor.getKey(), neighbor.getValue()) && _drawing.isBrightPixel(neighbor.getKey(), neighbor.getValue())) {
                    _drawing.setPixel(neighbor.getKey(), neighbor.getValue(), color);
                    stack.push(new WorkItem(neighbor.getKey(), neighbor.getValue()));
                }
            }
        }
    }

    public static void queueFlood(int xSeed, int ySeed, Color color) {
        _drawing.setPixel(xSeed, ySeed, color);
        ArrayList<WorkItem> queue = new ArrayList<WorkItem>();
        queue.add(new WorkItem(xSeed, ySeed));
        while(!queue.isEmpty()) {
            WorkItem w = queue.remove(0);
            ArrayList<SimpleEntry<Integer, Integer>> neighbors = getNeighbors(w.x, w.y);
            for(SimpleEntry<Integer, Integer> neighbor : neighbors) {
                if(_drawing.isValidPixel(neighbor.getKey(), neighbor.getValue()) && _drawing.isBrightPixel(neighbor.getKey(), neighbor.getValue())) {
                    _drawing.setPixel(neighbor.getKey(), neighbor.getValue(), color);
                    queue.add(new WorkItem(neighbor.getKey(), neighbor.getValue()));
                }
            }
        }
    }
    
    
    
    /**
     * Global static fields for the Drawing object being worked on
     * and the DrawingFrame containing and displaying it.
     */
    private static Drawing _drawing;
    private static DrawingFrame _frame;
    
    /**
     * Demonstrates a simple alteration to the drawing:
     * On a square section of the image, from top-left: (40,30) to bottom-right (140, 130)
     * replace the dark pixels with yellow and the bright pixels with yellow.
     */
    public static void paint() throws InterruptedException {
        for(int x = 40; x < 140; x++) {
            if (x == 90) {
                _frame.stop();
            }
            for (int y = 30; y < 130; y++) {
                _frame.step(1);
                Color c = _drawing.getPixel(x, y);
                if (_drawing.isDarkPixel(x, y)) {
                    _drawing.setPixel(x, y, c.brighter());
                } else {
                    _drawing.setPixel(x, y, c.darker());
                }
            }
        }
    }

    /**
     * Demonstrates triggering an action on a mouse click
     */
    public static MouseHook _onMouseClick = (mouseEvent) -> {
        System.out.printf("(%d,%d)\n", _frame.getCanvasX(mouseEvent), _frame.getCanvasY(mouseEvent));
        queueFlood(_frame.getCanvasX(mouseEvent), _frame.getCanvasY(mouseEvent), Color.YELLOW);
    };
    
    /**
     * Main entry point in the program:
     * Initializes the static Drawing (_drawing) with an image of your choice,
     * then initializes the static DrawingFrame (_frame) loading into it the new drawing.
     * Subsequently the frame is opened on the screen then the drawing is painted upon
     * and displayed as it is being modified before the program terminates. 
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to the Coloring Festival!");
        
        // pick a drawing -- correct the path to point to the drawing of your choice
        _drawing = Drawing.read("drawings/bird.jpg");
        
        // put it in a frame
        _frame = new DrawingFrame(_drawing);
        
        // put the frame on display and stop to admire it.
        _frame.open();
        _frame.step();
        
        // make some change to the drawing, then stop for applause.
        //paint();
        
        _frame.stop();
        
        // setup a hook such that we know where we're clicking
        _frame.setMouseClickedHook(_onMouseClick);
        
        // the show is over.
        _frame.close();
        
        System.out.println("Well done, goodbye!");
    }
}
