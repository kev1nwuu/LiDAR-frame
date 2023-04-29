 //  Header //
// Name:Yinhui Wu
// ID：300258115

import java.util.*;
import java.io.File;       
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{       
        
    public static void InsertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
 
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    } 
     
    
    public static void main(String[] args){
    
        ArrayList<Point3D> list_of_3DPoint = DBScan.read("C:\\Users\\Administrator\\Desktop\\A1\\Point_Cloud_1.csv");
        
        DBScan dbscan = new DBScan(list_of_3DPoint);

        dbscan.setEps(1.2);
        dbscan.setMinPts(10);
        
        dbscan.findClusters();

        
        dbscan.save("C:\\Users\\Administrator\\Desktop\\A1\\Point_Cloud_1_clusters_"+ dbscan.get_Eps()+"_" + dbscan.get_MinPts() + "_"+dbscan.getNumberOfClusters() + ".csv");

    //     System.out.print("size of all clusters found, from the largest one to the smallest one\n\n");

    }
        
        


    //     HashMap<Integer, Integer> size_of_all_clusters_found = new HashMap<>();      
    //     int count_for_points_in_a_cluster = 0;

            
    //     for(int i = 1; i<= dbscan.getNumberOfClusters();i++){
            
    //         for(Point3D points : dbscan.getPoints()){
    //             if(points.get_cluster_label() == i){
    //                 count_for_points_in_a_cluster++;
    //             }
    //         }
        
    //         size_of_all_clusters_found.put(i, count_for_points_in_a_cluster);
            
        
    //         count_for_points_in_a_cluster = 0;
        
    //     }
        
        
    //     /////////////////////////////////////////////////////////////////////////////////////// 
    //     // print out the clusters with labels and points.
    //     ArrayList<Integer> list_Integers = new ArrayList<>();
    //     LinkedHashMap<Integer, Integer> final_Map = new LinkedHashMap<>();
        
    //     for (Map.Entry<Integer, Integer> entry : size_of_all_clusters_found.entrySet()) {
    //         list_Integers.add(entry.getValue());
    //     }

    //     Collections.sort(list_Integers);
    //     Collections.reverse(list_Integers);

        
    //     for (Integer j : list_Integers) {
    //         for (Map.Entry<Integer, Integer> entry : size_of_all_clusters_found.entrySet()) {
    //             if (entry.getValue().equals(j)) {
    //                 final_Map.put(entry.getKey(), j);
    //             }
    //         }
    //     }
        
    //     for (Map.Entry<Integer, Integer> entry : final_Map.entrySet()) {
    //         System.out.println("The cluster label "+ entry.getKey() + " has " + entry.getValue() + " points.\n");
    //     }
        
        
    //     ///////noise point
    //     int count_for_noise_points = 1; // start at 1 becasue i took off 000 from the input file.
        
    //     for(Point3D points : dbscan.getPoints()){
    //         if(points.get_cluster_label() == 0){
    //             count_for_noise_points++;
    //         }
    //     }
        
    //     System.out.print("The number of noise point (cluster_label = 0 ):" + count_for_noise_points+"\n\n");



    // }


}    