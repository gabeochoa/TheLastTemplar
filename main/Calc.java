package main;

import java.awt.Point;

public class Calc
{
   public static Point Left = new Point(0,400); 
   public static Point Right = new Point(800,400); 
   public static Point Up = new Point(300,0); 
   public static Point Down = new Point(300,800); 

   public static double dist(Point A,Point B)
   {
       double xDif,yDif,sum,d;     
       double Ax,Ay,Bx,By;
                
                 Ax = A.getX();
                 Bx = B.getX();
                 Ay = A.getY();
                 By = B.getY();
                       
       xDif = (Bx-Ax) * (Bx-Ax);
       yDif = (By-Ay) * (By-Ay);
       sum = xDif + yDif;
       d = Math.sqrt(sum);
       return d;
   }
   public static boolean checkLeft(Point A){return (dist(Left,A) > 10);}
   public static boolean checkRight(Point A){return (dist(Right,A) > 10);}
   public static boolean checkUp(Point A){return (dist(Up,A)> 10);}
   public static boolean checkDown(Point A){return (dist(A,Down) > 10);}
   public static boolean ifInBox(int mx,int my,int x,int y, int w, int h)
   {
       if(( (mx>x) && (mx<w) ) && ((my > y)&&(my<h)))
            return true;
       return false;
    }
}
