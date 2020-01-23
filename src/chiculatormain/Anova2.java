package chiculatormain;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Iterator;

public class Anova2 {
   TableView<Anova> table;
   TableColumn<Anova, Double> t1;
   TableColumn<Anova, Integer> t0;
   TableColumn<Anova, Double> t2;
   TableColumn<Anova, Double> t3;
   Button addRow;
   Button addColumn;
   Button deleteRow;
   Button deleteCol;
   Button finish;
   Button clear;
   Button back;

   public void addRows() {
      Anova anv = new Anova();
      this.table.getItems().add(anv);
   }

   public void removeRows() {
      ObservableList<Anova> selected = this.table.getSelectionModel().getSelectedItems();
      ObservableList<Anova> totalList = this.table.getItems();
      Iterator var3 = selected.iterator();

      while(var3.hasNext()) {
         Anova item = (Anova)var3.next();
         totalList.remove(item);
      }

   }

   public void addColumns() {
      TableColumn<Anova, Double> tcol = new TableColumn("New Item");
      tcol.setCellValueFactory(new PropertyValueFactory("FirstItem"));
      tcol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      tcol.setOnEditCommit((e) -> {
         this.onEditFirstItem(e);
      });
      this.table.getColumns().add(tcol);
   }

   public void onEditFirstItem(Event e) {
      Anova anv = null;

      try {
         CellEditEvent<Anova, Double> edit = (CellEditEvent)e;
         anv = (Anova)edit.getRowValue();
         anv.setFirstItem((Double)edit.getNewValue());
      } catch (InputMismatchException | NumberFormatException var4) {
         anv.setFirstItem(0.0D);
      }

   }

   public void onEditSecondItem(Event e) {
      Anova anv = null;

      try {
         CellEditEvent<Anova, Double> edit = (CellEditEvent)e;
         anv = (Anova)edit.getRowValue();
         anv.setSecondItem((Double)edit.getNewValue());
      } catch (InputMismatchException | NumberFormatException var4) {
         anv.setSecondItem(0.0D);
      }

   }

   public void onEditThirdItem(Event e) {
      Anova anv = null;

      try {
         CellEditEvent<Anova, Double> edit = (CellEditEvent)e;
         anv = (Anova)edit.getRowValue();
         anv.setThirdItem((Double)edit.getNewValue());
      } catch (InputMismatchException | NumberFormatException var4) {
         anv.setThirdItem(0.0D);
      }

   }

   public TableView<Anova> anovaTable() {
      this.table = new TableView();
      this.t1 = new TableColumn();
      this.t1.setCellValueFactory(new PropertyValueFactory("FirstItem"));
      this.t1.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      this.t1.setOnEditCommit((e) -> {
         this.onEditFirstItem(e);
      });
      this.t1.setText("Item 1");
      this.t1.setMaxWidth(50.0D);
      this.t2 = new TableColumn();
      this.t2.setCellValueFactory(new PropertyValueFactory("SecondItem"));
      this.t2.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      this.t2.setOnEditCommit((e) -> {
         this.onEditSecondItem(e);
      });
      this.t2.setText("Item 2");
      this.t2.setMaxWidth(50.0D);
      this.t3 = new TableColumn();
      this.t3.setCellValueFactory(new PropertyValueFactory("ThirdItem"));
      this.t3.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      this.t3.setOnEditCommit((e) -> {
         this.onEditThirdItem(e);
      });
      this.t3.setText("Item 3");
      this.t3.setMaxWidth(50.0D);
      this.t0 = new TableColumn();
      this.t0.setCellValueFactory(new PropertyValueFactory("Count"));
      this.t0.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      this.t0.setOnEditCommit((e) -> {
         this.onEditThirdItem(e);
      });
      this.t0.setText("S/N");
      this.t0.setMaxWidth(30.0D);
      this.t0.setPrefWidth(30.0D);
      this.table.getColumns().addAll(new TableColumn[]{this.t1, this.t2, this.t3});
      this.table.setEditable(true);
      return this.table;
   }

   public Scene anovaGraph(Stage stage, Scene scene) {
      TableView<Anova> tab = this.anovaTable();
      tab.getSelectionModel().setCellSelectionEnabled(true);
      this.addRow = new Button("Add Row");
      this.addColumn = new Button("Add Column");
      this.deleteRow = new Button("Delete Row");
      this.deleteCol = new Button("Delete Column");
      this.clear = new Button("Clear All");
      this.finish = new Button("Finish");
      this.back = new Button("Back");
      this.finish.setOnAction((e) -> {
         stage.setScene(this.anovaFinalGraph(stage, tab, scene));
      });
      this.addRow.setOnAction((e) -> {
         this.addRows();
      });
      this.addColumn.setOnAction((e) -> {
         this.addColumns();
      });
      this.deleteRow.setOnAction((e) -> {
         this.removeRows();
      });
      this.deleteCol.setOnAction((e) -> {
         tab.getColumns().clear();
      });
      this.back.setOnAction((e) -> {
         stage.setScene(scene);
      });
      this.clear.setOnAction((e) -> {
         tab.getItems().clear();
      });
      HBox hbox = new HBox(10.0D, new Node[]{this.addRow, this.deleteRow, this.addColumn, this.deleteCol, this.clear, this.back, this.finish});
      VBox.setMargin(hbox, new Insets(80.0D));
      VBox vbox = new VBox(10.0D, new Node[]{tab, hbox});
      vbox.setAlignment(Pos.TOP_CENTER);
      vbox.setPadding(new Insets(4.0D));
      Scene sc = new Scene(vbox, 700.0D, 600.0D);
      stage.setTitle("CalPro- A  n  o  v  a");
      return sc;
   }

   public Scene anovaFinalGraph(Stage stage, TableView<Anova> table, Scene scene) {
      TableView<Anova> tab = new TableView();
      TableColumn<Anova, Double> sv = new TableColumn();
      TableColumn<Anova, Double> df = new TableColumn();
      TableColumn<Anova, Double> ss = new TableColumn();
      TableColumn<Anova, Double> ms = new TableColumn();
      TableColumn<Anova, Double> fr = new TableColumn();
      sv.setCellValueFactory(new PropertyValueFactory("Sv"));
      sv.setText("Source of Variation");
      sv.setMaxWidth(200.0D);
      sv.setPrefWidth(150.0D);
      df.setCellValueFactory(new PropertyValueFactory("Df"));
      df.setText("DF");
      df.setMaxWidth(200.0D);
      df.setPrefWidth(90.0D);
      ss.setCellValueFactory(new PropertyValueFactory("Ss"));
      ss.setText("Sum of Squares");
      ss.setMaxWidth(200.0D);
      ss.setPrefWidth(150.0D);
      ms.setCellValueFactory(new PropertyValueFactory("Ms"));
      ms.setText("Mean Squares");
      ms.setMaxWidth(150.0D);
      fr.setCellValueFactory(new PropertyValueFactory("Fr"));
      fr.setText("F-ratio");
      fr.setMaxWidth(100.0D);
      fr.setPrefWidth(60.0D);
      tab.getColumns().addAll(new TableColumn[]{sv, df, ss, ms, fr});
      ObservableList<Anova> olist = FXCollections.observableArrayList();
      new Anova();
      String[] sov = new String[]{"Between", "Error", "Total"};
      int[] arr2 = this.AnovaDFCol(table);
      double[] arr1 = this.AnovaSSCol(table);
      double[] arr3 = this.AnovaMSCol(table);
      String[] fratio = this.AnovaFRCol(table);

      for(int i = 0; i < arr2.length; ++i) {
         tab.getItems().addAll(new Anova[]{new Anova(sov[i], arr2[i], arr1[i], arr3[i], fratio[i])});
      }

      tab.setFixedCellSize(25.0D);
      tab.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(tab.getFixedCellSize()).add(30));
      tab.setMaxWidth(600.0D);
      tab.setMaxHeight(120.0D);
      tab.prefWidthProperty().bind(Bindings.size(table.getItems()).multiply(tab.getFixedCellSize()));
      Label label = new Label("Inference");
      String msg = "";
      msg = msg + "From the table above, the calculated F-value is " + fratio[0] + ".\n";
      msg = msg + "If the tabulated F-value at degrees of freedom " + arr2[0] + ", " + arr2[1] + " and ";
      msg = msg + "a specific value of alpha (0.05, 0.01, 0.025, etc.) is greater \n";
      msg = msg + "than the calculated F-value (i.e " + fratio[0] + ") we fail to ";
      msg = msg + "reject the null hypothesis. \n";
      msg = msg + "Otherwise, we reject the null hypothesis.";
      Label labelinference = new Label(msg);
      VBox inf = new VBox(12.0D, new Node[]{label, labelinference});
      inf.setPadding(new Insets(0.0D, 0.0D, 80.0D, 0.0D));
      inf.setAlignment(Pos.BOTTOM_LEFT);
      this.back = new Button("Back");
      this.back.setOnAction((e) -> {
         stage.setScene(scene);
      });
      Button exit = new Button("Exit");
      stage.setOnCloseRequest((e) -> {
         e.consume();
         Boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         if (b) {
            stage.close();
         }

      });
      exit.setOnAction((e) -> {
         Boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         if (b) {
            stage.close();
         }

      });
      Region r1 = new Region();
      Region r2 = new Region();
      HBox hb = new HBox(new Node[]{this.back, r1, exit});
      HBox.setHgrow(r1, Priority.ALWAYS);
      hb.setAlignment(Pos.BASELINE_LEFT);
      VBox.setMargin(hb, new Insets(10.0D));
      VBox vbox = new VBox(50.0D, new Node[]{tab, r2, inf, hb});
      VBox.setVgrow(r2, Priority.ALWAYS);
      vbox.setPadding(new Insets(5.0D));
      Scene sc = new Scene(vbox, 700.0D, 600.0D);
      stage.setTitle("CalPro- A  n  o  v  a");
      stage.setResizable(false);
      return sc;
   }

   public int[] extractInt(TableView<?> table, TableColumn<?, Integer> column) {
      int[] array = new int[table.getItems().size()];

      for(int i = 0; i < array.length; ++i) {
         array[i] = (Integer)column.getCellData(i);
      }

      return array;
   }

   public double[] extractDouble(TableView<?> table, TableColumn<?, Double> column) {
      double[] array = new double[table.getItems().size()];

      for(int i = 0; i < array.length; ++i) {
         array[i] = (Double)column.getCellData(i);
      }

      return array;
   }

   public char[] extractChar(TableView<?> table, TableColumn<?, Character> column) {
      char[] array = new char[table.getItems().size()];
      ObservableValue<Character> list = null;

      for(int i = 0; i < array.length; ++i) {
         array[i] = (Character)column.getCellData(i);
      }

      return array;
   }

   public char[] takeChar(TableView<?> table, TableColumn<?, Character> column) {
      char[] array = new char[table.getItems().size()];

      for(int i = 0; i < array.length; ++i) {
      }

      return array;
   }

   public String[] extractString(TableView<?> table, TableColumn<?, String> column) {
      String[] array = new String[table.getItems().size()];

      for(int i = 0; i < array.length; ++i) {
         array[i] = (String)column.getCellData(i);
      }

      return array;
   }

   public double sumSolution(Double[] calculatedArray) {
      double sum = 0.0D;

      for(int i = 0; i < calculatedArray.length; ++i) {
         sum += calculatedArray[i];
      }

      return sum;
   }

   public int getDegF(TableView<Anova> tab) {
      double sumosq = 0.0D;
      int sum = 0;
      int coun = 0;
      double sst = 0.0D;
      ObservableList<TableColumn<Anova, ?>> list = tab.getColumns();
      TableColumn<Anova, Double> coll = (TableColumn)list.get(1);

      for(int i = 0; i < tab.getItems().size(); ++i) {
         coll.getCellData(i);
         ++coun;
      }

      ObservableList<Anova> list2 = tab.getItems();
      TableColumn<Anova, Double>[] oarray = new TableColumn[list.size()];

      for(int i = 0; i < oarray.length; ++i) {
         oarray[i] = (TableColumn)list.get(i);
      }

      double[] array = new double[tab.getItems().size()];

      int realsum;
      for(realsum = 0; realsum < oarray.length; ++realsum) {
         this.getArrays(tab, oarray[realsum]);
         ++sum;
      }

      realsum = sum * coun - 1;
      return realsum;
   }

   public int getTabDF(TableView<Anova> table) {
      ObservableList<TableColumn<Anova, ?>> list = table.getColumns();
      int con = 0;

      for(Iterator var4 = list.iterator(); var4.hasNext(); ++con) {
         TableColumn<Anova, ?> tc = (TableColumn)var4.next();
      }

      return con - 1;
   }

   public double sumArray(double[] array) {
      double sum = 0.0D;

      for(int i = 0; i < array.length; ++i) {
         sum += array[i];
      }

      return sum;
   }

   public double sumSquares(double[] col) {
      double sumSq = 0.0D;

      for(int i = 0; i < col.length; ++i) {
         sumSq += col[i] * col[i];
      }

      return sumSq;
   }

   public double[] getArrays(TableView<?> table, TableColumn<?, Double> col1) {
      double[] totalColumn1 = new double[table.getItems().size()];

      for(int i = 0; i < totalColumn1.length; ++i) {
         totalColumn1[i] = (Double)col1.getCellData(i);
      }

      return totalColumn1;
   }

   public double sumofsquarestotal(TableView<Anova> table) {
      double sumtotal = 0.0D;
      double squares = 0.0D;
      double counter = 0.0D;
      ObservableList<Anova> list1 = table.getItems();
      ObservableList<TableColumn<Anova, ?>> list2 = table.getColumns();

      for(int i = 0; i < list2.size(); ++i) {
         TableColumn<Anova, Double> tc = (TableColumn)list2.get(i);

         for(int j = 0; j < table.getItems().size(); ++j) {
            sumtotal += (Double)tc.getCellData(j);
            squares += (Double)tc.getCellData(j) * (Double)tc.getCellData(j);
         }
      }

      double sumofsquares = squares - sumtotal * sumtotal / (double)(this.getDegF(table) + 1);
      return sumofsquares;
   }

   public double sumofsquaresbetween(TableView<Anova> table) {
      double sumtotal = 0.0D;
      double squares = 0.0D;
      double columnsum = 0.0D;
      double counter = 0.0D;
      ObservableList<Anova> list1 = table.getItems();
      ObservableList<TableColumn<Anova, ?>> list2 = table.getColumns();
      TableColumn<Anova, Double> t = (TableColumn)list2.get(0);
      int n = 0;

      for(int i = 0; i < list2.size(); ++i) {
         TableColumn<Anova, Double> tc = (TableColumn)list2.get(i);

         for(int j = 0; j < n; ++j) {
            sumtotal += (Double)tc.getCellData(j);
         }

         columnsum += sumtotal * sumtotal / (double)n;
      }

      double cf = sumtotal * sumtotal / (double)table.getItems().size();
      double ssb = columnsum - cf;
      return ssb;
   }

   public double sumOfSquaresTotal(TableView<Anova> tab) {
      double sumosq = 0.0D;
      double sum = 0.0D;
      double sst = 0.0D;
      ObservableList<TableColumn<Anova, ?>> list = tab.getColumns();
      ObservableList<Anova> list2 = tab.getItems();
      TableColumn[] oarray = new TableColumn[list.size()];

      try {
         for(int i = 0; i < oarray.length; ++i) {
            oarray[i] = (TableColumn)list.get(i);
         }

         double[] array = new double[tab.getItems().size()];

         for(int i = 0; i < oarray.length; ++i) {
            array = this.getArrays(tab, oarray[i]);
            sum += this.sumArray(array);
            sumosq += this.sumSquares(array);
         }

         sst = sumosq - sum / (double)(this.getDegF(tab) + 1);
      } catch (ArrayIndexOutOfBoundsException var13) {
         var13.getMessage();
      }

      return sst;
   }

   public double sumOfSquaresBetween(TableView<Anova> tab) {
      double sumosq = 0.0D;
      double suma = 0.0D;
      double sst = 0.0D;
      ObservableList<TableColumn<Anova, ?>> list = tab.getColumns();
      ObservableList<Anova> list2 = tab.getItems();
      TableColumn<Anova, Double> t = (TableColumn)list.get(0);
      TableColumn<Anova, Double>[] oarray = new TableColumn[list.size()];
      double[] sum = new double[tab.getColumns().size()];
      int n = (this.getDegF(tab) + 1) / (this.getTabDF(tab) + 1);
      TableColumn<Anova, Double>[] tablearray = new TableColumn[tab.getColumns().size()];
      double[] array = new double[n];

      int i;
      for(i = 0; i < tab.getColumns().size(); ++i) {
         tablearray[i] = (TableColumn)list.get(i);
      }

      for(i = 0; i < tab.getColumns().size(); ++i) {
         TableColumn<Anova, Double> col = tablearray[i];

         for(int j = 0; j < n; ++j) {
            array[j] = (Double)col.getCellData(j);
            suma = this.sumArray(array);
         }

         sst += this.sumArray(array);
         sumosq += suma * suma / (double)n;
      }

      double cf = sst * sst / (double)(this.getDegF(tab) + 1);
      double ans = sumosq - cf;
      return ans;
   }

   public double[] AnovaSSCol(TableView<Anova> table) {
      double sumofsquaresbet = this.sumOfSquaresBetween(table);
      double sumofsquarestot = this.sumofsquarestotal(table);
      double sumofsquareserr = sumofsquarestot - sumofsquaresbet;
      double[] col = new double[]{sumofsquaresbet, sumofsquareserr, sumofsquarestot};
      return col;
   }

   public int[] AnovaDFCol(TableView<Anova> table) {
      int tabDeg = this.getTabDF(table);
      int totDeg = this.getDegF(table);
      int errDeg = totDeg - tabDeg;
      int[] degOfFreedomArray = new int[]{tabDeg, errDeg, totDeg};
      return degOfFreedomArray;
   }

   public double[] AnovaMSCol(TableView<Anova> table) {
      double[] arr = new double[3];
      double[] ano = this.AnovaSSCol(table);
      int[] ano2 = this.AnovaDFCol(table);
      arr[0] = ano[0] / (double)ano2[0];
      arr[1] = ano[1] / (double)ano2[1];
      arr[2] = ano[2] / (double)ano2[2];
      return arr;
   }

   public String[] AnovaFRCol(TableView<Anova> table) {
      double[] fcol = this.AnovaMSCol(table);
      double fratio = 0.0D;
      fratio = fcol[0] / fcol[1];
      NumberFormat nf = NumberFormat.getNumberInstance();
      nf.setMaximumFractionDigits(4);
      String[] finalfr = new String[]{nf.format(fratio), "", ""};
      return finalfr;
   }
}
