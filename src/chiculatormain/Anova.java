package chiculatormain;

public class Anova {
   private double firstItem;
   private double secondItem;
   private double thirdItem;
   private String sv;
   private int df;
   private double ss;
   private double ms;
   private String fr;

   public Anova() {
   }

   public Anova(int df, String sv) {
      this.df = df;
      this.sv = sv;
   }

   public Anova(int df) {
      this.df = df;
   }

   public Anova(int df, double ss, double ms) {
      this.df = df;
      this.ss = ss;
      this.ms = ms;
   }

   public Anova(String fr) {
      this.fr = fr;
   }

   public Anova(double firstItem, double secondItem) {
      this.firstItem = firstItem;
      this.secondItem = secondItem;
   }

   public Anova(String sv, int df, double ss, double ms) {
      this.sv = sv;
      this.df = df;
      this.ss = ss;
      this.ms = ms;
   }

   public Anova(String sv, int df, double ss, double ms, String fr) {
      this.sv = sv;
      this.df = df;
      this.ss = ss;
      this.ms = ms;
      this.fr = fr;
   }

   public Anova(double firstItem, double secondItem, double thirdItem) {
      this.firstItem = firstItem;
      this.secondItem = secondItem;
      this.thirdItem = thirdItem;
   }

   public void setFirstItem(double item) {
      this.firstItem = item;
   }

   public double getFirstItem() {
      return this.firstItem;
   }

   public void setSecondItem(double item) {
      this.secondItem = item;
   }

   public double getSecondItem() {
      return this.secondItem;
   }

   public void setThirdItem(double item) {
      this.thirdItem = item;
   }

   public double getThirdItem() {
      return this.thirdItem;
   }

   public void setSv(String sv) {
      this.sv = sv;
   }

   public String getSv() {
      return this.sv;
   }

   public void setDf(int df) {
      this.df = df;
   }

   public int getDf() {
      return this.df;
   }

   public void setSs(double ss) {
      this.ss = ss;
   }

   public double getSs() {
      return this.ss;
   }

   public void setMs(double ms) {
      this.ms = ms;
   }

   public double getMs() {
      return this.ms;
   }

   public void setFr(String fr) {
      this.fr = fr;
   }

   public String getFr() {
      return this.fr;
   }
}
