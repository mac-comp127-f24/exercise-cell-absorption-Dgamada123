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
        while (true) {
            for (Cell cell:cells) {
                Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
                cell.moveAround(canvasCenter);
            }
            canvas.draw();
            canvas.pause(10);
            handleCellInteraction();
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

    private void handleCellInteraction() {
        for (int i = 0; i < cells.size(); i++) {
            Cell cell0 = cells.get(i);
            for (int j = i + 1; j < cells.size(); j++) {
                Cell cell1 = cells.get(j);
                cell1.interactWith(cell0);
            }
        }
    }

    private static double sqr(double x) {
        return x * x;
    }
}
