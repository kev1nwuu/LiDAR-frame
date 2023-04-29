//  Header //
// Name:Yinhui Wu
// ID：300258115


import java.util.ArrayList;
import java.util.List;

public class NearestNeighbors{

    ArrayList<Point3D> list_point3D;
    
    public NearestNeighbors(ArrayList<Point3D> list){
        list_point3D = list;
    }

    public ArrayList<Point3D> rangeQuery(double eps, Point3D P){  // finds the nearest neighbors of a 3D point
        
        ArrayList<Point3D> N = new ArrayList<>();

        for(Point3D point : list_point3D){       /* Scan all points in DB */
            if(point.distance(P) <= eps){        /* Compute distance */
                N.add(point);                    // add to empty list(nearest neighbors of a 3D point)
            }
        } 
        return N;
    }
}