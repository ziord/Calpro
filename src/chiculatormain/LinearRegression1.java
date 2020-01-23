package chiculatormain;

public class LinearRegression1 {
   int count = 0;
   double x;
   double y;
   double xsquare;
   double ysquare;
   double xy;

   public LinearRegression1() {
   }

   public LinearRegression1(double x, double y) {
      this.x = x;
      this.y = y;
   }

   public LinearRegression1(int count, double x, double y) {
      this.count = count;
      this.x = x;
      this.y = y;
   }

   public LinearRegression1(double xsquare, double ysquare, double xy) {
      this.xsquare = xsquare;
      this.ysquare = ysquare;
      this.xy = xy;
   }

   public LinearRegression1(int count, double x, double y, double xsquare, double ysquare, double xy) {
      this.count = count;
      this.x = x;
      this.y = y;
      this.xsquare = xsquare;
      this.ysquare = ysquare;
      this.xy = xy;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public int getCount() {
      return this.count;
   }

   public void setX(double x) {
      this.x = x;
   }

   public double getX() {
      return this.x;
   }

   public void setY(double y) {
      this.y = y;
   }

   public double getY() {
      return this.y;
   }

   public void setXY(double xy) {
      this.xy = xy;
   }

   public double getXY() {
      return this.xy;
   }

   public void setXSquare(double xsquare) {
      this.xsquare = xsquare;
   }

   public double getXSquare() {
      return this.xsquare;
   }

   public void setYSquare(double ysquare) {
      this.ysquare = ysquare;
   }

   public double getYSquare() {
      return this.ysquare;
   }
}
