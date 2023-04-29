//  Header //
// Name:Yinhui Wu
// ID：300258115

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.*;

public class DBScan{

    private ArrayList<Point3D> list_point3D;
    private NearestNeighbors n1;
    private NearestNeighbors n2;
    MyStack stack;

    
    private double Eps = 0.0;  // initialize
    private double MinPts = 0.0;    
    
    private int C = 0;
    
    
    
    public DBScan(ArrayList<Point3D> list){
        list_point3D = list;
        n1 = new NearestNeighbors(list_point3D);
        n2 = new NearestNeighbors(list_point3D);
        stack = new MyStack();
    }

    // getter and setter
    public void setEps(double eps){
        this.Eps = eps;
    }
    public void setMinPts(double minPts){
        this.MinPts = minPts;
    }
    public double get_Eps(){
        return this.Eps;
    }
    public double get_MinPts(){
        return this.MinPts;
    }

    
    public void findClusters(){

        for (Point3D p : list_point3D){
            
            if(p.get_cluster_label() != -1){ /* Already processed   undefined = -1 , Noise = 0 */
                continue;
            }
            
            ArrayList<Point3D> N = n1.rangeQuery(Eps, p);         /* Find neighbors */

            if (N.size() < MinPts){                             /* Density check,if smaller than Minpoints then i will mark as Noise*/
                p.set_cluster_label(0);             /* Label as Noise */
                continue;                               
            }
            
            C = C + 1;                                /* next cluster label */
            p.set_cluster_label(C);                       /* Label initial point */
            
            for(Point3D j:N){                                 /* Neighbors to expand */
                stack.push(j);
            }
            
            while (!stack.isEmpty()) {
                
                Point3D Q = stack.pop();
                
                if(Q.get_cluster_label() == 0 ){            
                    Q.set_cluster_label(C);             /* Noise becomes border pt */
                }
                if(Q.get_cluster_label() != -1){
                    continue;                               /* Previously processed */
                }

                Q.set_cluster_label(C);                      /* Label neighbor */
                
                
                ArrayList<Point3D> N2 = n2.rangeQuery(Eps, Q);   /* Find neighbors */
                if (N2.size() >= MinPts){                   /* Density check */
                    
                    for(Point3D z:N2){
                        stack.push(z);                    /* Add neighbors to stack */
                    }
                 
                }

            }
        
        }
    }

    public int getNumberOfClusters(){
        return C;
    }

    public ArrayList<Point3D> getPoints(){
        return this.list_point3D;
    }

    public static ArrayList<Point3D> read(String filename){
        
        ArrayList<Point3D> list_of_3DPoint = new ArrayList<>();

        // FileReader fr = new FileReader(filename);    
        
        try {
            FileReader fr = new FileReader(filename);  
            BufferedReader br = new BufferedReader(fr);
            
            String line = br.readLine();
            line = br.readLine();
            line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                
                Point3D point3D = createPoint(attributes);
                
                list_of_3DPoint.add(point3D);
            
                line = br.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
           

        return list_of_3DPoint;
    }

    //Helper method
    private static Point3D createPoint(String[] metadata){
        
        double x = Double.parseDouble(metadata[0]);      // convter string to double
        double y = Double.parseDouble(metadata[1]);
        double z = Double.parseDouble(metadata[2]);

        return new Point3D(x,y,z);   // use the x y z to create a point and return.
    }


    
    public void save(String filename){
        
        ArrayList<Point3D> save_list_of_3DPoint = new ArrayList<>();

        for(int i = 0; i <= getNumberOfClusters(); i++){   // for number of group of Clusters
            
            double R = Math.random();
            double G = Math.random();
            double B = Math.random();
            
            for(Point3D point : getPoints()){              // find the points has a same cluster_label, group them, 
                                                            //and also for points in a same Clusters has same RGB.

                if(point.get_cluster_label() == i){                         
                    
                    if(i == 0){                             // if cluster_label == 0 mean its a noise point, so set RGB to black
                        point.set_R(0.0);                                                 
                        point.set_G(0.0); 
                        point.set_B(0.0);
                        save_list_of_3DPoint.add(point);
                    }
                    else{
                        point.set_R(R);                                                 
                        point.set_G(G); 
                        point.set_B(B);
                        save_list_of_3DPoint.add(point);
                    }
                }
            }
        }

        try {
            File csvFile = new File(filename);
        
            PrintWriter out = new PrintWriter(csvFile);

            out.printf("%s, %s, %s, %s, %s, %s, %s\n","x","y","z","C","R","G","B");        // wirter the two fixed letters and numbers 0 
            out.printf("%d, %d, %d, %d, %d, %d, %d\n",0,0,0,0,0,0,0);


            for(Point3D p : save_list_of_3DPoint){                                                          // then wirter anything in the list to the csv file
                out.printf("%f, %f, %f, %d, %f, %f, %f\n", p.getX(), p.getY(), p.getZ(), p.get_cluster_label(), p.get_R(), p.get_G(), p.get_B());
            }

            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}