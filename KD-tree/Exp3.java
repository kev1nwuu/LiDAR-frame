// Header
// Yinhui Wu
// ID:300258115
// Experiment 3

import java.util.ArrayList;
import java.util.List;

public class Exp3 {

    public static void main(String[] args) throws Exception {

        double startTime = System.nanoTime();   // START TGE TIME HERE 
        ArrayList<Point3D> list_of_3DPoint = DBScan.read("C:\\Users\\Administrator\\Desktop\\P2\\Point_Cloud_3.csv");  // READING THE FILE 
        
        DBScan dbscan = new DBScan(list_of_3DPoint);   // USING DBSCAN TO 

        dbscan.setEps(1.2);   
        dbscan.setMinPts(10);
        
        dbscan.findClusters();    // FINDING THE Clusters

        

        double endTime = System.nanoTime();   
        double duration = (endTime - startTime) / 1000000;    // TIME USED

        System.out.println("compute time the DBScan(with KD-Tree search) find Clusters from Point_Cloud_3: " + duration +" ms.");


    }

}