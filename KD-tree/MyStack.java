


import java.io.*;
import java.util.*;


public class MyStack{

    Stack<Point3D> stack = new Stack<>();  // use the java built in stack
    
    
    
    public void push(Point3D point3D)
    {
        stack.push(point3D);
    }
    public Point3D pop()
    {
        return stack.pop();
    }

    public boolean isEmpty(){
        return stack.empty();
    }

}