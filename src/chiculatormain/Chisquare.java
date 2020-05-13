package chiculatormain;

public class Chisquare {
   private double observed;
   private double expected;
   private int count;
   private double sol;
   private double obsExp;

   public Chisquare() {
   }

   public Chisquare(int count, double observed, double expected) {
      this.count = count;
      this.observed = observed;
      this.expected = expected;
   }

   public Chisquare(double observed, double expected) {
      this.observed = observed;
      this.expected = expected;
   }

   public Chisquare(int c, double obsExp) {
      this.count = c;
      this.obsExp = obsExp;
   }

   public Chisquare(double sol) {
      this.sol = sol;
   }

   public Chisquare(int count, double observed, double expected, double obsExp, double sol) {
      this.count = count;
      this.observed = observed;
      this.expected = expected;
      this.obsExp = obsExp;
      this.sol = sol;
   }

   public Chisquare(int count, double observed, double expected, double obsExp) {
      this.count = count;
      this.observed = observed;
      this.expected = expected;
      this.obsExp = obsExp;
   }

   public void setCount(int c) {
      this.count = c;
   }

   public int getCount() {
      return this.count;
   }

   public void setObserved(double obsv) {
      this.observed = obsv;
   }

   public double getObserved() {
      return this.observed;
   }

   public void setExpected(double expt) {
      this.expected = expt;
   }

   public double getExpected() {
      return this.expected;
   }

   public void setObsExp(double oe) {
      this.obsExp = oe;
   }

   public double getObsExp() {
      return this.obsExp;
   }

   public void setSol(double sol) {
      this.sol = sol;
   }

   public double getSol() {
      return this.sol;
   }

}
