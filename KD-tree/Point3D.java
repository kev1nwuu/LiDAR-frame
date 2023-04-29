// Header
// Yinhui Wu
// ID:300258115


/*
 * Point3D (x,y,z)
 *
 * CSI2510 Algorithmes et Structures de Donnees
 * www.uottawa.ca
 *
 * Robert Laganiere, 2022
 *
*/ 

public class Point3D {
  
  
  // 后面加的
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
  
  
  
  private double x;
  private double y;
  private double z;
  public int label = -1; // not used here

  // constructs point (x,y,z)
  public Point3D(double x, double y, double z) {
       
    this.x= x;
    this.y= y;
    this.z= z;
  }
  
  // gets x-coordinate
  public double getX() {
	  return x;
  }

  // gets y-coordinate
  public double getY() {
	  return y;
  }	

  // gets z-coordinate
  public double getZ() {
	  return z;
  }

  // FOR EXP3
  public int get_cluster_label(){
    return this.label;
  }
  public void set_cluster_label(int i){
     this.label =i;
  }

  // gets coordinate x, y or z if axis 0, 1, or 2
  public double get(int axis) {
	  
	  switch(axis) {
		  case 0: return x;
		  case 1: return y;
		  case 2: return z;
		  default: return 0.0;
	  }		  
  }

  // gets the euclidean distance between two points
  public double distance(Point3D pt) {
     return Math.sqrt((this.getX()-pt.getX())*(this.getX()-pt.getX()) +  
	                        (this.getY()-pt.getY())*(this.getY()-pt.getY()) +
	                        (this.getZ()-pt.getZ())*(this.getZ()-pt.getZ()) );
  }
  
  // gets String representation
  public String toString() {
	  
	  return "("+x+","+y+","+z+")";
  }
}