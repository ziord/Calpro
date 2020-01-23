package chiculatormain;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Iterator;

public class GradePoint2 {
   TableView<GradePoint> table;
   ChoiceBox<Integer> unitChoiceBox;
   ChoiceBox<Character> gradeChoiceBox;
   Button cancel;
   Button back;
   Button addrow;
   Button removeRow;
   Button clearColumns;
   Button finish;
   TableColumn<GradePoint, Integer> countCol;
   TableColumn<GradePoint, String> courseCol;
   TableColumn<GradePoint, Integer> unitCol;
   TableColumn<GradePoint, Character> gradeCol;
   TableColumn<GradePoint, String> gpCol;
   int c = 0;

   public void removeRowCells(Event e) {
      ObservableList<GradePoint> list = this.table.getItems();
      ObservableList<GradePoint> selectedItems = this.table.getSelectionModel().getSelectedItems();
      Iterator var4 = selectedItems.iterator();

      while(var4.hasNext()) {
         GradePoint grp = (GradePoint)var4.next();
         list.remove(grp);
      }

   }

   public void addRowCells(Event e) {
      GradePoint gp = new GradePoint();
      ++this.c;
      gp.setCount(this.c);
      gp.setCourse("");
      this.table.getItems().addAll(new GradePoint[]{gp});
   }

   public void onEditCount(Event e) {
      GradePoint gp = null;

      try {
         CellEditEvent<GradePoint, Integer> edit = (CellEditEvent)e;
         new GradePoint();
         gp = (GradePoint)edit.getRowValue();
         gp.setCount((Integer)edit.getNewValue());
      } catch (NumberFormatException | InputMismatchException var4) {
         gp.setCount(0);
      }

   }

   public void onEditCourse(Event e) {
      GradePoint gp = null;

      try {
         CellEditEvent<GradePoint, String> edit = (CellEditEvent)e;
         gp = (GradePoint)edit.getRowValue();
         gp.setCourse((String)edit.getNewValue());
      } catch (InputMismatchException | NumberFormatException var4) {
         gp.setUnit(0);
      }

   }

   public void onEditUnit(Event e) {
      GradePoint gp = null;

      try {
         CellEditEvent<GradePoint, Integer> edit = (CellEditEvent)e;
         gp = (GradePoint)edit.getRowValue();
         gp.setUnit((Integer)edit.getNewValue());
      } catch (InputMismatchException | NumberFormatException var4) {
         gp.setUnit(0);
      }

   }

   public void onEditGrade(Event e) {
      GradePoint gp = null;

      try {
         CellEditEvent<GradePoint, Character> edit = (CellEditEvent)e;
         gp = (GradePoint)edit.getRowValue();
         gp.setGrade((Character)edit.getNewValue());
      } catch (InputMismatchException | NumberFormatException var4) {
         gp.setUnit(0);
      }

   }

   public TableView<GradePoint> Table(Stage stage, Scene scene) {
      this.unitChoiceBox = new ChoiceBox();
      this.unitChoiceBox.getItems().addAll(new Integer[]{1, 2, 3, 4});
      this.unitChoiceBox.setValue(1);
      this.gradeChoiceBox = new ChoiceBox();
      this.gradeChoiceBox.getItems().addAll(new Character[]{'A', 'B', 'C', 'D', 'E', 'F'});
      this.table = new TableView();
      this.table.getSelectionModel().setCellSelectionEnabled(true);
      this.countCol = new TableColumn();
      this.countCol.setCellValueFactory(new PropertyValueFactory("Count"));
      this.countCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      this.countCol.setOnEditCommit((e) -> {
         this.onEditCount(e);
      });
      this.countCol.setText("S/N");
      this.countCol.setMaxWidth(50.0D);
      this.countCol.setPrefWidth(30.0D);
      this.courseCol = new TableColumn();
      this.courseCol.setCellValueFactory(new PropertyValueFactory("Course"));
      this.courseCol.setCellFactory(TextFieldTableCell.forTableColumn());
      this.courseCol.setOnEditCommit((e) -> {
         this.onEditCourse(e);
      });
      this.courseCol.setText("Courses");
      this.countCol.setPrefWidth(80.0D);
      this.countCol.setMaxWidth(120.0D);
      this.unitCol = new TableColumn();
      this.unitCol.setCellValueFactory(new PropertyValueFactory("Unit"));
      this.unitCol.setCellFactory(ChoiceBoxTableCell.forTableColumn(new IntegerStringConverter(), this.unitChoiceBox.getItems()));
      this.unitCol.setOnEditCommit((e) -> {
         this.onEditUnit(e);
      });
      this.unitCol.setText("Units");
      this.unitCol.setMaxWidth(120.0D);
      this.unitCol.setPrefWidth(80.0D);
      this.gradeCol = new TableColumn();
      this.gradeCol.setCellValueFactory(new PropertyValueFactory("Grade"));
      this.gradeCol.setCellFactory(ChoiceBoxTableCell.forTableColumn(new CharacterStringConverter(), this.gradeChoiceBox.getItems()));
      this.gradeCol.setOnEditCommit((e) -> {
         this.onEditGrade(e);
      });
      this.gradeCol.setText("Grade");
      this.gradeCol.setMaxWidth(100.0D);
      this.countCol.setPrefWidth(80.0D);
      this.table.getColumns().addAll(new TableColumn[]{this.countCol, this.courseCol, this.unitCol, this.gradeCol});
      this.table.setEditable(true);
      return this.table;
   }

   public double sumArrays(double[] array) {
      double sum = 0.0D;

      for(int i = 0; i < array.length; ++i) {
         sum += array[i];
      }

      return sum;
   }

   public int sumArrays(int[] array) {
      int sum = 0;

      for(int i = 0; i < array.length; ++i) {
         sum += array[i];
      }

      return sum;
   }

   public String getGP(int[] units, char[] grade) {
      double unitSum = (double)this.sumArrays(units);
      double points = 0.0D;
      double gp = 0.0D;

      for(int i = 0; i < grade.length; ++i) {
         switch(grade[i]) {
         case 'A':
         case 'a':
            points += (double)(5 * units[i]);
            break;
         case 'B':
         case 'b':
            points += (double)(4 * units[i]);
            break;
         case 'C':
         case 'c':
            points += (double)(3 * units[i]);
            break;
         case 'D':
         case 'd':
            points += (double)(2 * units[i]);
            break;
         case 'E':
         case 'e':
            points += (double)(1 * units[i]);
            break;
         case 'F':
         case 'f':
            points += (double)(0 * units[i]);
         case 'G':
         case 'H':
         case 'I':
         case 'J':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'S':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         case 'Z':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case '`':
         }
      }

      gp = points / unitSum;
      NumberFormat nf = NumberFormat.getNumberInstance();
      nf.setMaximumFractionDigits(2);
      String rgp = nf.format(gp);
      return rgp;
   }

   public String getMessage(String gpvalue) {
      String msg = "";
      double value = 0.0D;

      try {
         value = Double.parseDouble(gpvalue);
      } catch (NumberFormatException var6) {
      }

      if (value > 4.4999D) {
         msg = msg + "Excellent work done! You are a First Class student.\n";
         msg = msg + "Keep up the good work!";
      } else if (value > 3.4999D && value <= 4.4999D) {
         msg = msg + "Great work done! You are on a Second Class Upper. \n";
         msg = msg + "You can still do better than this. Don't stop the good work!";
      } else if (value > 1.4999D && value <= 3.4999D) {
         msg = msg + "Good work. You are on a Second Class Lower. \n";
         msg = msg + "You can still do much more better than you have done. You have a great journey ahead.\n";
         msg = msg + "Start working hard today! No more postponements and laziness! Keep your head up high.";
      } else if (value > 0.9999D && value <= 1.4999D) {
         msg = msg + "You're on a Pass.\n";
         msg = msg + "I believe i don't need to tell you to tighten your seatbelts and work darn hard!!!!";
      } else if (value < 1.0D) {
         msg = msg + "Prepare to be kicked out!\n";
         msg = msg + "You freaking failed!!";
      }

      return msg;
   }

   public Scene firstScene(Stage stage, Scene scene) {
      this.table = this.Table(stage, scene);
      this.back = new Button("Back");
      this.back.setOnAction((e) -> {
         stage.setScene(scene);
      });
      this.cancel = new Button("Cancel");
      this.cancel.setOnAction((e) -> {
         boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         b = true;
         if (true) {
            stage.close();
         }

      });
      stage.setOnCloseRequest((e) -> {
         e.consume();
         boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         b = true;
         if (true) {
            stage.close();
         }

      });
      this.addrow = new Button("Add Row");
      this.removeRow = new Button("Delete Row");
      this.finish = new Button("Finish");
      this.addrow.setOnAction((e) -> {
         this.addRowCells(e);
      });
      this.removeRow.setOnAction((e) -> {
         this.removeRowCells(e);
      });
      this.finish.setOnAction((e) -> {
         stage.setScene(this.secondScene(stage, scene, this.table));
      });
      Region space = new Region();
      HBox hbox = new HBox(35.0D, new Node[]{this.addrow, this.removeRow, this.back, this.finish});
      hbox.setPadding(new Insets(15.0D));
      hbox.setAlignment(Pos.TOP_LEFT);
      VBox vb = new VBox(20.0D, new Node[]{this.table, space, hbox});
      VBox.setVgrow(space, Priority.ALWAYS);
      vb.setAlignment(Pos.TOP_LEFT);
      stage.setTitle("CalPro- G P  C a l c u l a t o r");
      Scene sceneGP = new Scene(vb, 750.0D, 600.0D);
      return sceneGP;
   }

   public Scene secondScene(Stage stage, Scene scene, TableView<GradePoint> table) {
      TableColumn<GradePoint, String> thegp = new TableColumn("GP");
      thegp.setCellValueFactory(new PropertyValueFactory("GP"));
      thegp.setPrefWidth(60.0D);
      this.back = new Button("Back");
      this.cancel = new Button("Cancel");
      this.back.setOnAction((e) -> {
         stage.setScene(scene);
      });
      this.cancel = new Button("Cancel");
      this.cancel.setOnAction((e) -> {
         boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         if (b) {
            stage.close();
         }

      });
      stage.setOnCloseRequest((e) -> {
         e.consume();
         boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         if (b) {
            stage.close();
         }

      });
      int[] arraycount = new int[table.getItems().size()];
      String[] arraycourse = new String[table.getItems().size()];
      int[] arrayunit = new int[arraycourse.length];
      char[] arraygrade = new char[arraycourse.length];
      ObservableList<TableColumn<GradePoint, ?>> list = table.getColumns();
      TableColumn<GradePoint, Integer> ccol = (TableColumn)list.get(0);
      TableColumn<GradePoint, String> coursecol = (TableColumn)list.get(1);
      TableColumn<GradePoint, Integer> unitcol = (TableColumn)list.get(2);
      TableColumn<GradePoint, Character> gradecol = (TableColumn)list.get(3);
      arraycount = (new Anova2()).extractInt(table, ccol);
      arraycourse = (new Anova2()).extractString(table, coursecol);
      arrayunit = (new Anova2()).extractInt(table, unitcol);
      arraygrade = (new Anova2()).extractChar(table, gradecol);
      String[] gparray = new String[arraygrade.length];
      table.getColumns().add(thegp);
      table.setEditable(false);
      String gpvalue = this.getGP(arrayunit, arraygrade);
      String msg = this.getMessage(gpvalue);

      try {
         gparray[0] = gpvalue;
      } catch (ArrayIndexOutOfBoundsException var35) {
      }

      for(int i = 0; i < gparray.length - 1; ++i) {
         gparray[i + 1] = "";
      }

      Label l1 = new Label("Number of courses: ");
      Label l2 = new Label("Total number of units: ");
      Label l3 = new Label("Grade Point: ");
      Label lv1 = new Label(arraycourse.length + "");
      double sum = (double)this.sumArrays(arrayunit);
      Label lv2 = new Label(sum + "");
      Label lv3 = new Label(gpvalue);
      HBox hb1 = new HBox(10.0D, new Node[]{l1, lv1});
      HBox hb2 = new HBox(10.0D, new Node[]{l2, lv2});
      HBox hb3 = new HBox(10.0D, new Node[]{l3, lv3});
      VBox vball = new VBox(10.0D, new Node[]{hb1, hb2, hb3});
      vball.setPadding(new Insets(5.0D, 0.0D, 0.0D, 10.0D));
      table.getItems().clear();

      for(int i = 0; i < arraycourse.length; ++i) {
         table.getItems().add(new GradePoint(arraycount[i], arraycourse[i], arrayunit[i], arraygrade[i], gparray[i]));
      }

      Label label = new Label(msg);
      label.setPadding(new Insets(25.0D, 5.0D, 0.0D, 10.0D));
      Region space = new Region();
      HBox butPane = new HBox(15.0D, new Node[]{this.back, space, this.cancel});
      butPane.setPadding(new Insets(10.0D));
      HBox.setHgrow(space, Priority.ALWAYS);
      Region spacer = new Region();
      VBox finalPane = new VBox(15.0D, new Node[]{table, vball, label, spacer, butPane});
      VBox.setVgrow(spacer, Priority.ALWAYS);
      stage.setTitle("CalPro- G P  C a l c u l a t o r");
      Scene lastscene = new Scene(finalPane, 850.0D, 650.0D);
      return lastscene;
   }
}
