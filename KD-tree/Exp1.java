
// Header
// Yinhui Wu
// ID:300258115
// Experiment 1

import java.util.List;
import java.util.ArrayList;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Exp1 {

	// reads a csv file of 3D points (rethrow exceptions!)
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

	// helper for test if they have the same list of neighbors.
	public static boolean test(List<Point3D> n_lin, List<Point3D> n_KD){
		if(n_lin.size() != n_KD.size()){      // if the size cant match its already wrong
			return false;
		}

		int size = n_lin.size();
		int all_passed = 0;    // find match

		for (Point3D d : n_lin) {
			for (Point3D point3D : n_KD)
				if (d.getX() == point3D.getX() && d.getY() == point3D.getY() && d.getZ() == point3D.getZ()) {
					all_passed++;
				}
		}

		return all_passed == size;      // if the match number match the size of the list then it's true;

	}



	public static void main(String[] args) throws Exception {


		double eps = Double.parseDouble(args[1]);;

		// reads the csv file
		List<Point3D> points = Exp1.read(args[2]); // "E:\\intellij project\\P2\\src\\Point_Cloud_1.csv"

		Point3D query = new Point3D(Double.parseDouble(args[3]),
				Double.parseDouble(args[4]),
				Double.parseDouble(args[5]));

		// query points used for output text file
		// Point3D query1 = new Point3D(-5.42985,  0.80756, -0.39821);
		// Point3D query2 = new Point3D(-12.97637, 5.09061,  0.76223);
		// Point3D query3 = new Point3D(-36.10818, 14.24161, 4.29347);
		// Point3D query4 = new Point3D(3.10743,   0.03286,  0.42839);
		// Point3D query5 = new Point3D(11.58047,  2.99060, 1.86546);
		// Point3D query6 = new Point3D(14.15982,  4.68070, -0.13379);


		// creates the NearestNeighbor instance

		// linear

		if (args[0].equals("lin")) {

			NearestNeighbors nnn = new NearestNeighbors(points);
			List<Point3D> neighbors = nnn.rangeQuery(query, eps);

			// save to text file

			// if(query.equals(query1)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt1_lin.txt");
			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}

			// }

			// else if(query.equals(query2)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt2_lin.txt");
			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }
			// else if(query.equals(query3)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt3_lin.txt");
			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }

			// else if(query.equals(query4)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt4_lin.txt");
			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }

			// else if(query.equals(query5)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt5_lin.txt");
			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }

			// else if(query.equals(query6)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt6_lin.txt");
			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }

			System.out.println("number of neighbors = " + neighbors.size());

			for (Point3D p : neighbors) {

				System.out.println(p + "\n");
			}


		}

		else {
			// KD-tree
			NearestNeighborsKD kd = new NearestNeighborsKD(points);
			List<Point3D> neighbors = kd.rangeQuery(query, eps);

			// save to text file
			// if (query.equals(query1)) {
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt1_kd.txt");

			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }

			// else if (query.equals(query2)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt2_kd.txt");

			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }

			// else if (query.equals(query3)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt3_kd.txt");

			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }

			// else if (query.equals(query4)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt4_kd.txt");

			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }

			// else if (query.equals(query5)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt5_kd.txt");

			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }

			// else if (query.equals(query6)){
			// 	try {
			// 		File textFile = new File("C:\\Users\\Administrator\\Desktop\\P2\\exp1\\pt6_kd.txt");

			// 		PrintWriter out = new PrintWriter(textFile);

			// 		out.printf("%s, %s, %s\n", "x", "y", "z");        // wirter the two fixed letters and numbers 0

			// 		for (Point3D p : neighbors) {                                        // then wirter anything in the list to the text file
			// 			out.printf("%f, %f, %f\n", p.getX(), p.getY(), p.getZ());
			// 		}

			// 		out.close();
			// 	} catch (IOException e) {
			// 		e.printStackTrace();
			// 	}
			// }
			System.out.println("number of neighbors = " + neighbors.size());

			for (Point3D p : neighbors) {

				System.out.println(p + "\n");                // (one point per line)
			}
		}



		//Test to see if the list of neighbors for both search methods are the same.
//		NearestNeighbors nn = new NearestNeighbors(points);
//	 	List<Point3D> neighbors_lin = nn.rangeQuery(query,eps);
//
//		NearestNeighborsKD mm = new NearestNeighborsKD(points);
//		List<Point3D> neighbors_kd = mm.rangeQuery(query,eps);
//
//		System.out.println("finds the nearest neighbors of a point though a simple linear search\n");
//		for (Point3D p : neighbors_lin) {
//
//			System.out.println(p + "\n");
//		}
//
//		System.out.println("finds the nearest neighbors of a point using k-d trees \n");
//		for (Point3D p : neighbors_kd) {
//
//			System.out.println(p + "\n");
//		}
//
//
//		boolean t = test(neighbors_lin,neighbors_kd);             // It will return true if they are same, return false if not
//		if (t){
//			System.out.println("Both methods give the same results");
//		}
//		else{
//			System.out.println("Both methods give the diff results");
//		}


	}
}