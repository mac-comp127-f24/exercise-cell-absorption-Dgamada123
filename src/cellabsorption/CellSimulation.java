package cellabsorption;

import edu.macalester.graphics.CanvasWindow;


import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("SameParameterValue")


public class CellSimulation {
    
    private CanvasWindow canvas;
    private Random rand = new Random();
  
    private List<Cell> cells;
    

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();

        //noinspection InfiniteLoopStatement
        while (true) {

            for (Cell cell:cells) {
                Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
                cell.moveAround(canvasCenter);
                cell.grow(0.02);

            }
            

            canvas.draw();
            canvas.pause(10);
            
        }
    }



    private void populateCells() {
        cells = new ArrayList<>();


        for (int n = 1; n <= 200; n++) {
            
            double size = rand.nextInt(5) + 2;
            
            Cell cell = new Cell(rand.nextDouble() * (canvas.getWidth() - size),
            rand.nextDouble() * (canvas.getWidth() - size),
            size,
            Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
            canvas.add(cell.getShape());
            cells.add(cell);

            

        } 

    }



    
    private static double sqr(double x) {
        return x * x;
    }

    // private static double normalizeRadians(double theta) {
    //     double pi2 = Math.PI * 2;
    //     return ((theta + Math.PI) % pi2 + pi2) % pi2 - Math.PI;
    // }
}
