// Header
// Yinhui Wu
// ID:300258115
// Experiment 2
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exp2 {

    public static List<Point3D> read(String filename) throws Exception {

        List<Point3D> points = new ArrayList<Point3D>();
        double x, y, z;

        Scanner sc = new Scanner(new File(filename));
        // sets the delimiter pattern to be , or \n \r
        sc.useDelimiter(",|\n|\r");

        // skipping the first line x y z
        sc.next();
        sc.next();
        sc.next();

        // read points
        while (sc.hasNext()) {
            x = Double.parseDouble(sc.next());
            y = Double.parseDouble(sc.next());
            z = Double.parseDouble(sc.next());
            points.add(new Point3D(x, y, z));
        }

        sc.close();  //closes the scanner

        return points;
    }



    public static void main(String[] args) throws Exception {



        final DecimalFormat df = new DecimalFormat("0.0000");  // round Decimal

        double eps = Double.parseDouble(args[1]);;

        List<Point3D> points = Exp2.read(args[2]); //EX: Point_Cloud_1.csv"//
        int step = Integer.parseInt(args[3]);

        if(args[0].equals("lin")) {  // check if it's using linear search

            double runtimes =0;            // save the total runtime
            int total = 0;                 // total points used
            for (int i = 0; i < points.size(); i += step) {
                NearestNeighbors nn = new NearestNeighbors(points);
                double startTime = System.nanoTime();
                nn.rangeQuery(points.get(i), eps);
                double endTime = System.nanoTime();
                double duration = (endTime - startTime) / 1000000;
                runtimes = runtimes+duration;
                total++;
            }
            double average_runtimes = runtimes/total;
            System.out.println("(linear search) Average compute time to find neighbors from the "+args[2]+" is "+df.format(average_runtimes)+" ms.");


        }
        else{
            double runtimes =0;
            int total = 0;

            for (int i = 0; i < points.size(); i += step) {
                NearestNeighborsKD nn = new NearestNeighborsKD(points);
                double startTime = System.nanoTime();
                nn.rangeQuery(points.get(i), eps);
                double endTime = System.nanoTime();
                double duration = (endTime - startTime) / 1000000;
                runtimes = runtimes+duration;
                total++;
            }

            double average_runtimes = runtimes/total;
            System.out.println("(KD-tree) Average compute time to find neighbors from the "+args[2]+" is "+ df.format(average_runtimes)+" ms.");
        }







    }












}