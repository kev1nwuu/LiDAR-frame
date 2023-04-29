// Header
// Yinhui Wu
// ID:300258115
// KD-tree search


import java.util.List;



import java.util.ArrayList;

public class NearestNeighborsKD{

    protected java.util.List<Point3D> points; 
    
    KDtree kdTree;

    KDtree.KDnode kdnode;
    
    // construct with list of points
    public NearestNeighborsKD(java.util.List<Point3D> points) {
         
        this.points = points; 

        kdTree = new KDtree();  // initializations tree
        
        // List<Point3D> neighbors;
        
        for (Point3D p : points) {  // add points to kd tree
            kdTree.add(p);
        }
    }
  
    public List<Point3D> rangeQuery(Point3D p, double eps) { 
        
        List<Point3D> neighbors = new ArrayList<Point3D>();

        rangeQuery(p, eps, neighbors, kdTree.root()); // call private rangeQuery
        
        return neighbors;
    }

    private void rangeQuery(Point3D p, double eps, List<Point3D> neighbors, KDtree.KDnode node){  // find the neighbors(kd tree)



        if (node == null){
            return;
        }
        if (p.distance(node.point) < eps){
            neighbors.add(node.point);
        }

        if (p.get(node.axis) - eps <= node.value){
            rangeQuery(p, eps, neighbors, node.left);
        }

        if (p.get(node.axis) + eps > node.value){
            rangeQuery(p, eps, neighbors, node.right);
        }

        return;
    }










}