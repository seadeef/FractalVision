import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runner class for the Fractal App
 */
public class Main {

    /**
     * Executes on runtime and starts the app
     * @param args cmd line args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Fractal[] fractals = {new Mandelbrot(),
                              new Newton(new Sine()),
                              new Newton(new Sinh()),
                              new Newton(new Cubed())};

        int count = 1;
        for (Fractal fractal : fractals) {
            System.out.println("[" + count + "] " + fractal);
            count += 1;
        }
        System.out.print("Which fractal would you like to render? ");
        int option = input.nextInt();
        input.nextLine();
        Fractal selected = fractals[option-1];

        System.out.print("Enter a zoom percentage: ");
        double zoom = input.nextDouble()/100;
        input.nextLine();

        //Having to type this every time annoys me -- can uncomment
//        System.out.print("Enter the range for the fractal (zero for default): ");
//        double range = input.nextDouble();
//        input.nextLine();
//        if (range != 0.0) {
//            selected.setRange(range);
//        }

        System.out.print("Enter the width of the image (in pixels): ");
        int width = input.nextInt();

        System.out.print("Enter the height of the image (in pixels): ");
        int height = input.nextInt();
        input.nextLine();

        System.out.print("Specify a center point (blank for default): ");
        String center = input.nextLine();
        Scanner parse = new Scanner(center);
        parse.useDelimiter(",");

        Render renderer = null;
        if (parse.hasNextDouble()) {
            double centerx = parse.nextDouble();
            double centery = parse.nextDouble();
            System.out.println(centerx);
            System.out.println(centery);
            renderer = new Render(width, height, zoom, selected, centerx, centery);
        } else {
            renderer = new Render(width, height, zoom, selected);
        }

        RenderFrame frame = new RenderFrame(renderer);
        frame.start();
    }
}