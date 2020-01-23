package chiculatormain;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Iterator;

public class Chisquare3 {
   TableView<Chisquare> table;
   TableColumn<Chisquare, Double> obs;
   TableColumn<Chisquare, Integer> co;
   TableColumn<Chisquare, Double> exp;
   TableColumn<Chisquare, Double> oe;
   TableColumn<Chisquare, Double> oE;
   TableColumn<Chisquare, Double> fin;
   MenuBar bar;
   Menu menu;
   Button ok;
   Button go;
   Button finish;
   Button back;
   Button add;
   Button delete;
   int count = 0;
   Label lab1;
   Label lab2;

   public void adder(TableView<Chisquare> table) {
      Chisquare cs = new Chisquare();
      ++this.count;
      cs.setCount(this.count);
      cs.setExpected(0.0D);
      cs.setObserved(0.0D);
      table.getItems().add(cs);
   }

   public void remover(TableView<Chisquare> table) {
      ObservableList<Chisquare> list = table.getItems();
      ObservableList<Chisquare> dList = table.getSelectionModel().getSelectedItems();
      Iterator var4 = dList.iterator();

      while(var4.hasNext()) {
         Chisquare it = (Chisquare)var4.next();
         list.remove(it);
      }

   }

   public void onEditObs(Event e) {
      Chisquare cs = null;

      try {
         CellEditEvent<Chisquare, Double> tt = (CellEditEvent)e;
         cs = (Chisquare)tt.getRowValue();
         cs.setObserved((Double)tt.getNewValue());
      } catch (NumberFormatException | InputMismatchException var4) {
         cs.setObserved(0.0D);
      }

   }

   public void onEditExpected(Event e) {
      Chisquare c = null;

      try {
         CellEditEvent<Chisquare, Double> tc = (CellEditEvent)e;
         c = (Chisquare)tc.getRowValue();
         c.setExpected((Double)tc.getNewValue());
      } catch (NumberFormatException | InputMismatchException var4) {
         c.setExpected(0.0D);
      }

   }

   public void onEditCount(Event e) {
      Chisquare k = null;

      try {
         CellEditEvent<Chisquare, Integer> tc = (CellEditEvent)e;
         k = (Chisquare)tc.getRowValue();
         k.setCount((Integer)tc.getNewValue());
      } catch (NumberFormatException | InputMismatchException var4) {
         k.setCount(0);
      }

   }

   public TableView<Chisquare> Table() {
      this.table = new TableView();
      this.table.setEditable(true);
      this.obs = new TableColumn();
      this.obs.setCellValueFactory(new PropertyValueFactory("Observed"));
      this.obs.setText("Observed Value");
      this.obs.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      this.obs.setOnEditCommit((e) -> {
         this.onEditObs(e);
      });
      this.obs.setPrefWidth(120.0D);
      this.obs.setMaxWidth(150.0D);
      this.exp = new TableColumn();
      this.exp.setCellValueFactory(new PropertyValueFactory("Expected"));
      this.exp.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      this.exp.setText("Expected Value");
      this.exp.setOnEditCommit((e) -> {
         this.onEditExpected(e);
      });
      this.exp.setPrefWidth(120.0D);
      this.exp.setMaxWidth(150.0D);
      this.co = new TableColumn();
      this.co.setCellValueFactory(new PropertyValueFactory("Count"));
      this.co.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      this.co.setOnEditCommit((e) -> {
         this.onEditCount(e);
      });
      this.co.setText("S/N");
      this.co.setMaxWidth(50.0D);
      this.table.getColumns().addAll(new TableColumn[]{this.co, this.obs, this.exp});
      this.table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      this.table.getSelectionModel().setCellSelectionEnabled(true);
      this.obs.setMaxWidth(150.0D);
      this.exp.setMaxWidth(150.0D);
      this.co.setMaxWidth(30.0D);
      return this.table;
   }

   public Scene chiGraph(Stage stage, Scene sc) {
      TableView<Chisquare> tab = this.Table();
      this.add = new Button("Add Row");
      this.back = new Button("Back");
      this.delete = new Button("Delete");
      this.go = new Button("Go");
      Button clear = new Button("Clear");
      clear.setOnAction((e) -> {
         tab.getItems().clear();
      });
      this.add.setOnAction((e) -> {
         this.adder(tab);
      });
      this.delete.setOnAction((e) -> {
         this.remover(tab);
      });
      this.back.setOnAction((e) -> {
         stage.setScene(sc);
      });
      this.go.setOnAction((e) -> {
         try {
            this.autogenerate(tab);
         } catch (NullPointerException var4) {
         }

      });
      this.finish = new Button("Finish");
      this.finish.setOnAction((e) -> {
         stage.setScene(this.chiGraphFinal(stage, sc, this.table));
      });
      this.add.setTooltip(new Tooltip("Click to add row cells to the table"));
      this.delete.setTooltip(new Tooltip("Click to delete selected row cell(s) "));
      this.go.setTooltip(new Tooltip("Click to automate expected values "));
      this.finish.setTooltip(new Tooltip("Click to finish solution"));
      Tooltip tip = new Tooltip("clears the table to generate a fresh table");
      Tooltip.install(clear, tip);
      this.bar = new MenuBar();
      HBox top = new HBox(new Node[]{this.bar});
      top.setAlignment(Pos.TOP_LEFT);
      Region r = new Region();
      new Region();
      HBox hbox = new HBox(20.0D);
      hbox.getChildren().addAll(new Node[]{this.back, this.add, this.delete, clear, this.go, this.finish});
      hbox.setAlignment(Pos.BOTTOM_CENTER);
      HBox.setMargin(hbox, new Insets(0.0D, 0.0D, 0.0D, 0.0D));
      VBox vbox = new VBox(30.0D);
      vbox.getChildren().addAll(new Node[]{tab, r, hbox});
      VBox.setVgrow(r, Priority.ALWAYS);
      vbox.setPadding(new Insets(0.0D, 10.0D, 10.0D, 10.0D));
      VBox vt = new VBox(5.0D, new Node[]{vbox});
      Scene scene = new Scene(vt, 525.0D, 500.0D);
      stage.setTitle("CalPro-Chisquare Analysis");
      return scene;
   }

   public Scene chiGraph2(Stage stage, Scene s) {
      TableView<Chisquare> tab = this.Table();
      this.add = new Button("Add Row");
      this.back = new Button("Back");
      this.bar = new MenuBar();
      this.delete = new Button("Delete");
      this.go = new Button("Go");
      this.add.setOnAction((e) -> {
         this.adder(tab);
      });
      this.delete.setOnAction((e) -> {
         this.remover(tab);
      });
      this.back.setOnAction((e) -> {
         stage.setScene(s);
      });
      this.go.setDisable(true);
      Button clear = new Button("Clear");
      clear.setOnAction((e) -> {
         tab.getItems().clear();
      });
      this.finish = new Button("Finish");
      this.finish.setOnAction((e) -> {
         stage.setScene(this.chiGraphFinal2(stage, s, tab));
      });
      this.add.setTooltip(new Tooltip("Click to add row cells to the table"));
      this.delete.setTooltip(new Tooltip("Click to delete selected row cell(s) "));
      this.go.setTooltip(new Tooltip("Click to automate expected values "));
      this.finish.setTooltip(new Tooltip("Click to finish solution"));
      MenuBar mb = new MenuBar();
      HBox up = new HBox(new Node[]{mb});
      up.setAlignment(Pos.TOP_LEFT);
      HBox hbox = new HBox(20.0D);
      hbox.getChildren().addAll(new Node[]{this.back, this.add, this.delete, clear, this.go, this.finish});
      hbox.setAlignment(Pos.BOTTOM_LEFT);
      Region r = new Region();
      HBox.setMargin(hbox, new Insets(5.0D, 0.0D, 0.0D, 0.0D));
      VBox vbox = new VBox(30.0D);
      vbox.getChildren().addAll(new Node[]{tab, r, hbox});
      VBox.setVgrow(r, Priority.ALWAYS);
      VBox vbo = new VBox(0.0D, new Node[]{up, vbox});
      vbox.setPadding(new Insets(5.0D, 10.0D, 10.0D, 10.0D));
      stage.setTitle("CalPro-Chisquare Analysis");
      Scene scene = new Scene(vbo, 525.0D, 525.0D);
      return scene;
   }

   public Scene chiGraphFinal(Stage stage, Scene sce, TableView<Chisquare> table) {
      Scene scene = null;
      TableView<Chisquare> table1 = this.autogenerate(table);
      ObservableList<TableColumn<Chisquare, ?>> cols = table1.getColumns();
      TableColumn<Chisquare, Double> col3 = (TableColumn)cols.get(1);
      TableColumn<Chisquare, Double> col4 = (TableColumn)cols.get(2);
      Double[] obsv = new Double[table1.getItems().size()];
      Double[] expe = new Double[table1.getItems().size()];
      Double[] sol = new Double[table1.getItems().size()];
      obsv = this.getArrays(table1, col3);
      expe = this.getArrays(table1, col4);
      sol = this.calculateChi(obsv, expe);
      double sumtotal = this.sumSolution(sol);
      this.oE = new TableColumn("Chisquare");
      this.oE.setCellValueFactory(new PropertyValueFactory("obsExp"));
      this.oE.setText("Observed-Expected");
      this.oE.setPrefWidth(130.0D);
      this.oE.setMaxWidth(140.0D);
      this.fin = new TableColumn("Solution");
      this.fin.setCellValueFactory(new PropertyValueFactory("Sol"));
      this.fin.setMaxWidth(100.0D);
      table1.getColumns().addAll(new TableColumn[]{this.oE, this.fin});
      table1.getItems().clear();
      (new Chisquare()).setSol(sumtotal);

      for(int i = 0; i < expe.length; ++i) {
         table1.getItems().add(new Chisquare(i + 1, obsv[i], expe[i], sol[i], sumtotal));
      }

      table1.prefWidthProperty().bind(stage.widthProperty());
      table.setFixedCellSize(25.0D);
      table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(30));
      table.setEditable(false);
      this.lab1 = new Label("Inference: ");
      this.lab2 = new Label();
      Button b1 = new Button("Back");
      Button b2 = new Button("Exit");
      b1.setOnAction((e) -> {
         stage.setScene(this.chiGraph(stage, sce));
      });
      stage.setOnCloseRequest((e) -> {
         e.consume();
         Boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         if (b) {
            stage.close();
         }

      });
      b2.setOnAction((e) -> {
         Boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         if (b) {
            stage.close();
         }

      });
      HBox hbox = new HBox(20.0D);
      Region r = new Region();
      hbox.getChildren().addAll(new Node[]{this.lab1, this.setInference(sumtotal, new Label(), table), table});
      VBox vbox = new VBox(25.0D);
      VBox.setVgrow(r, Priority.ALWAYS);
      vbox.getChildren().addAll(new Node[]{table1, r, hbox, new HBox(12.0D, new Node[]{r, b1, b2})});
      vbox.setPadding(new Insets(5.0D, 10.0D, 10.0D, 10.0D));
      scene = new Scene(vbox, 600.0D, 570.0D);
      stage.setTitle("Calpro-Chisquare Analysis");
      return scene;
   }

   public Scene chiGraphFinal2(Stage stage, Scene sce, TableView<Chisquare> table) {
      Scene scene = null;
      ObservableList<TableColumn<Chisquare, ?>> cols = table.getColumns();
      TableColumn<Chisquare, Double> col1 = (TableColumn)cols.get(1);
      TableColumn<Chisquare, Double> col2 = (TableColumn)cols.get(2);
      Double[] arr1 = new Double[table.getItems().size()];
      Double[] arr2 = new Double[table.getItems().size()];
      Double[] arr3 = new Double[table.getItems().size()];
      arr1 = this.getArrays(table, col1);
      arr2 = this.getArrays(table, col2);
      arr3 = this.calculateChi(arr1, arr2);
      double arr4 = this.sumSolution(arr3);
      this.oE = new TableColumn("Chisquare");
      this.oE.setCellValueFactory(new PropertyValueFactory("obsExp"));
      this.oE.setText("Observed-Expected");
      this.oE.setPrefWidth(130.0D);
      this.oE.setMaxWidth(140.0D);
      this.fin = new TableColumn("Solution");
      this.fin.setCellValueFactory(new PropertyValueFactory("Sol"));
      this.fin.setMaxWidth(100.0D);
      table.getColumns().addAll(new TableColumn[]{this.oE, this.fin});
      table.getItems().clear();

      for(int i = 0; i < arr2.length; ++i) {
         table.getItems().addAll(new Chisquare[]{new Chisquare(i + 1, arr1[i], arr2[i], arr3[i], arr4)});
      }

      table.prefWidthProperty().bind(stage.widthProperty());
      table.setFixedCellSize(25.0D);
      table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(30));
      table.setEditable(false);
      this.lab1 = new Label("Inference: ");
      this.lab1.setFont(new Font("Sans serrif", 13.0D));
      this.lab2 = new Label();
      this.lab2.setText("");
      Button b1 = new Button("Back");
      Button b2 = new Button("Exit");
      b1.setOnAction((e) -> {
         stage.setScene(this.chiGraph2(stage, sce));
      });
      stage.setOnCloseRequest((e) -> {
         e.consume();
         Boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         if (b) {
            stage.close();
         }

      });
      b2.setOnAction((e) -> {
         Boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Program");
         if (b) {
            stage.close();
         }

      });
      Region r = new Region();
      HBox hbox = new HBox(20.0D);
      hbox.getChildren().addAll(new Node[]{this.lab1, this.setInference(arr4, new Label(), table)});
      VBox vbox = new VBox(20.0D);
      VBox.setVgrow(r, Priority.ALWAYS);
      vbox.getChildren().addAll(new Node[]{table, hbox, r, new HBox(10.0D, new Node[]{b1, b2})});
      vbox.setPadding(new Insets(5.0D, 10.0D, 5.0D, 10.0D));
      scene = new Scene(vbox, 600.0D, 570.0D);
      stage.setTitle("CalPro-Chisquare Analysis");
      return scene;
   }

   public double sumArray(Double[] array) {
      double sum = 0.0D;

      for(int i = 0; i < array.length; ++i) {
         sum += array[i];
      }

      return sum / (double)array.length;
   }

   public Double[] getArrays(TableView<Chisquare> table, TableColumn<Chisquare, Double> col1) {
      Double[] totalColumn1 = new Double[table.getItems().size()];

      for(int i = 0; i < totalColumn1.length; ++i) {
         totalColumn1[i] = (Double)col1.getCellData(i);
      }

      return totalColumn1;
   }

   public TableView<Chisquare> autogenerate(TableView<Chisquare> table) {
      new Chisquare();
      double sum = 0.0D;
      double div = 0.0D;
      Double[] former = new Double[table.getColumns().size()];
      ObservableList<TableColumn<Chisquare, ?>> columns = table.getColumns();
      TableColumn<Chisquare, Double> col1 = (TableColumn)columns.get(1);
      TableColumn<Chisquare, Double> col2 = (TableColumn)columns.get(1);
      former = this.getArrays(table, col1);
      Double[] aut = new Double[former.length];
      Double ans = this.sumArray(former);
      table.getItems().clear();

      for(int i = 0; i < former.length; ++i) {
         aut[i] = ans;
         table.getItems().add(new Chisquare(i + 1, former[i], aut[i]));
      }

      return table;
   }

   public Double[] calculateChi(Double[] array1, Double[] array2) {
      Double[] newArray = new Double[array1.length];

      int i;
      for(i = 0; i < array1.length; ++i) {
         newArray[i] = array1[i] - array2[i];
      }

      for(i = 0; i < newArray.length; ++i) {
         newArray[i] = newArray[i] * newArray[i];
      }

      for(i = 0; i < newArray.length; ++i) {
         newArray[i] = newArray[i] / array2[i];
      }

      return newArray;
   }

   public double sumSolution(Double[] calculatedArray) {
      double sum = 0.0D;

      for(int i = 0; i < calculatedArray.length; ++i) {
         sum += calculatedArray[i];
      }

      return sum;
   }

   public Label setInference(double ans, Label label, TableView<Chisquare> finalTable) {
      String msg = "";
      NumberFormat nf = NumberFormat.getNumberInstance();
      nf.setMaximumFractionDigits(3);
      if (ans > 0.0D) {
         msg = msg + "The Chisquare value is ";
         msg = msg + nf.format(ans) + "\n";
         msg = msg + "This implies that at specific value of alpha and at ";
         msg = msg + (finalTable.getItems().size() - 1) + " degrees of freedom \n";
         msg = msg + "if " + nf.format(ans) + " is greater than the value from Chisquare table ";
         msg = msg + "we reject the null hypothesis\n";
         msg = msg + "otherwise, we fail to reject the null hypothesis";
         label.setText(msg);
      }

      return label;
   }
}
