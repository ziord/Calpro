package chiculatormain;

public class GradePoint {
   private String course;
   private char grade;
   private int unit;
   private int count;
   private String gp;

   public GradePoint() {
   }

   public GradePoint(int count, String course, int unit, char grade) {
      this.count = count;
      this.course = course;
      this.unit = unit;
      this.grade = grade;
   }

   public GradePoint(int count, String course, int unit, char grade, String gp) {
      this.count = count;
      this.course = course;
      this.unit = unit;
      this.grade = grade;
      this.gp = gp;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public int getCount() {
      return this.count;
   }

   public void setCourse(String course) {
      this.course = course;
   }

   public String getCourse() {
      return this.course;
   }

   public void setUnit(int unit) {
      this.unit = unit;
   }

   public int getUnit() {
      return this.unit;
   }

   public void setGrade(char grade) {
      this.grade = grade;
   }

   public char getGrade() {
      return this.grade;
   }

   public void setGP(String gp) {
      this.gp = gp;
   }

   public String getGP() {
      return this.gp;
   }
}
