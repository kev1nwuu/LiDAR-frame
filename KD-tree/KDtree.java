// Header
// Yinhui Wu
// ID:300258115
// kd-tree

import java.util.List;

public class KDtree {
    
    
    public class KDnode {
   
        public Point3D point;
        public int axis;
        public double value;
        public KDnode left;
        public KDnode right;
        public KDnode(Point3D pt, int axis) {
            this.point= pt;
            this.axis= axis;
            this.value= pt.get(axis);
            left= right= null;
        }
    }
    
    private KDnode root;
    // construct empty tree
    public KDtree() {

        root= null;
    }

    public KDnode root(){
        return this.root;
    }  // return the tree root

    public void add(Point3D point){    // add method that implement insert method given in pseudocode

        root = insert(point, root, 0);

    }

    // 后面加的
    private KDtree.KDnode insert(Point3D point, KDnode node, Integer axis){
        
        Integer DIM = 3;   // set the dim to 3 because we are working on 3D environment
        
        if (node == null){
            node = new KDnode(point, axis);
        }
        
        else if(point.get(axis) <= node.value){
            node.left = insert(point, node.left, (axis+1) % DIM);
        }
        
        else{
            node.right = insert(point, node.right, (axis+1) % DIM);
            
        }
        
        return node;
    
    }

}