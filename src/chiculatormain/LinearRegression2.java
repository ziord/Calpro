package chiculatormain;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
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

public class LinearRegression2 {
   TableView<LinearRegression1> table;
   Button cancel;
   Button back;
   Button addrow;
   Button removeRow;
   Button clearColumns;
   Button finish;
   TableColumn<LinearRegression1, Integer> count;
   TableColumn<LinearRegression1, Double> x;
   TableColumn<LinearRegression1, Double> y;
   int c = 0;

   public void removeRowCells(Event e) {
      ObservableList<LinearRegression1> list = this.table.getItems();
      ObservableList<LinearRegression1> selectedItems = this.table.getSelectionModel().getSelectedItems();
      Iterator var4 = selectedItems.iterator();

      while(var4.hasNext()) {
         LinearRegression1 lr = (LinearRegression1)var4.next();
         list.remove(lr);
      }

   }

   public void addRowCells(Event e) {
      LinearRegression1 lr = new LinearRegression1();
      ++this.c;
      lr.setCount(this.c);
      this.table.getItems().addAll(new LinearRegression1[]{lr});
   }

   public void onEditCount(Event e) {
      LinearRegression1 lr = null;

      try {
         CellEditEvent<LinearRegression1, Integer> edit = (CellEditEvent)e;
         new LinearRegression1();
         lr = (LinearRegression1)edit.getRowValue();
         lr.setCount((Integer)edit.getNewValue());
      } catch (NumberFormatException | InputMismatchException var4) {
         lr.setCount(0);
      }

   }

   public void onEditX(Event e) {
      LinearRegression1 lr = null;

      try {
         new LinearRegression1();
         CellEditEvent<LinearRegression1, Double> edit = (CellEditEvent)e;
         lr = (LinearRegression1)edit.getRowValue();
         lr.setX((Double)edit.getNewValue());
      } catch (NumberFormatException | InputMismatchException var4) {
         lr.setX(0.0D);
      }

   }

   public void onEditY(Event e) {
      LinearRegression1 lr = null;

      try {
         new LinearRegression1();
         CellEditEvent<LinearRegression1, Double> edit = (CellEditEvent)e;
         lr = (LinearRegression1)edit.getRowValue();
         lr.setY((Double)edit.getNewValue());
      } catch (NumberFormatException | InputMismatchException var4) {
         lr.setY(0.0D);
      }

   }

   public TableView<LinearRegression1> Table(Stage stage, Scene scene) {
      this.count = new TableColumn("S/N");
      this.count.setPrefWidth(50.0D);
      this.count.setMaxWidth(100.0D);
      this.x = new TableColumn("X - dependent");
      this.x.setPrefWidth(50.0D);
      this.x.setMaxWidth(100.0D);
      this.y = new TableColumn("Y - independent");
      this.y.setPrefWidth(50.0D);
      this.y.setMaxWidth(100.0D);
      this.x.setCellValueFactory(new PropertyValueFactory("X"));
      this.x.setOnEditCommit((e) -> {
         this.onEditX(e);
      });
      this.y.setCellValueFactory(new PropertyValueFactory("Y"));
      this.count.setCellValueFactory(new PropertyValueFactory("Count"));
      this.y.setOnEditCommit((e) -> {
         this.onEditY(e);
      });
      this.count.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      this.count.setOnEditCommit((e) -> {
         this.onEditCount(e);
      });
      this.x.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      this.y.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      this.x.setPrefWidth(100.0D);
      this.x.setMaxWidth(150.0D);
      this.y.setPrefWidth(100.0D);
      this.y.setMaxWidth(150.0D);
      this.table = new TableView();
      this.table.getColumns().addAll(new TableColumn[]{this.count, this.x, this.y});
      this.table.setEditable(true);
      this.table.getSelectionModel().setCellSelectionEnabled(true);
      return this.table;
   }

   public double[] getXArray(TableView<LinearRegression1> tab) {
      Anova2 ano = new Anova2();
      ObservableList<TableColumn<LinearRegression1, ?>> list = tab.getColumns();
      TableColumn<LinearRegression1, Double> xcol = (TableColumn)list.get(1);
      double[] xarray = new double[tab.getItems().size()];
      xarray = ano.extractDouble(tab, xcol);
      return xarray;
   }

   public double[] getYArray(TableView<LinearRegression1> tab) {
      Anova2 ano = new Anova2();
      ObservableList<TableColumn<LinearRegression1, ?>> list = tab.getColumns();
      TableColumn<LinearRegression1, Double> ycol = (TableColumn)list.get(2);
      double[] yarray = new double[tab.getItems().size()];
      yarray = ano.extractDouble(tab, ycol);
      return yarray;
   }

   public double sumArrays(double[] array) {
      double sum = 0.0D;

      for(int i = 0; i < array.length; ++i) {
         sum += array[i];
      }

      return sum;
   }

   public double[] getxy(TableView<LinearRegression1> tab) {
      Anova2 ano = new Anova2();
      ObservableList<TableColumn<LinearRegression1, ?>> list = tab.getColumns();
      TableColumn<LinearRegression1, Double> xcol = (TableColumn)list.get(1);
      TableColumn<LinearRegression1, Double> ycol = (TableColumn)list.get(2);
      double[] xarray = new double[tab.getItems().size()];
      xarray = ano.extractDouble(tab, xcol);
      double[] yarray = new double[tab.getItems().size()];
      yarray = ano.extractDouble(tab, ycol);
      double[] xyarray = new double[yarray.length];

      for(int i = 0; i < xyarray.length; ++i) {
         xyarray[i] = xarray[i] * yarray[i];
      }

      return xyarray;
   }

   public double[] getYSquareArray(TableView<LinearRegression1> tab) {
      Anova2 ano = new Anova2();
      ObservableList<TableColumn<LinearRegression1, ?>> list = tab.getColumns();
      TableColumn<LinearRegression1, Double> ycol = (TableColumn)list.get(2);
      double[] yarray = new double[tab.getItems().size()];
      yarray = ano.extractDouble(tab, ycol);
      double[] ysquarearray = new double[yarray.length];

      for(int i = 0; i < ysquarearray.length; ++i) {
         ysquarearray[i] = yarray[i] * yarray[i];
      }

      return ysquarearray;
   }

   public double[] getXSquareArray(TableView<LinearRegression1> tab) {
      Anova2 ano = new Anova2();
      ObservableList<TableColumn<LinearRegression1, ?>> list = tab.getColumns();
      TableColumn<LinearRegression1, Double> xcol = (TableColumn)list.get(1);
      double[] xarray = new double[tab.getItems().size()];
      xarray = ano.extractDouble(tab, xcol);
      double[] xsquarearray = new double[xarray.length];

      for(int i = 0; i < xsquarearray.length; ++i) {
         xsquarearray[i] = xarray[i] * xarray[i];
      }

      return xsquarearray;
   }

   public double getXSum(TableView<LinearRegression1> tab) {
      double[] array = new double[tab.getItems().size()];
      double sum = 0.0D;
      array = this.getXArray(tab);

      for(int i = 0; i < array.length; ++i) {
         sum += array[i];
      }

      return sum;
   }

   public int getN(TableView<LinearRegression1> tab) {
      double sumosq = 0.0D;
      int sum = 0;
      int coun = 0;
      double sst = 0.0D;
      ObservableList<TableColumn<LinearRegression1, ?>> list = tab.getColumns();
      TableColumn<LinearRegression1, Double> coll = (TableColumn)list.get(1);

      for(int i = 0; i < tab.getItems().size(); ++i) {
         coll.getCellData(i);
         ++coun;
      }

      return coun;
   }

   public double getYSum(TableView<LinearRegression1> tab) {
      double[] array = new double[tab.getItems().size()];
      double sum = 0.0D;
      array = this.getYArray(tab);

      for(int i = 0; i < array.length; ++i) {
         sum += array[i];
      }

      return sum;
   }

   public double getAlpha(TableView<LinearRegression1> tab) {
      double firstside = this.getYSum(tab) / (double)this.getN(tab);
      double secondside = this.getXSum(tab) / (double)this.getN(tab);
      double beta = this.getBeta(tab);
      double alpha = firstside - beta * secondside;
      return alpha;
   }

   public double getBeta(TableView<LinearRegression1> tab) {
      double numerator = (double)this.getN(tab) * this.sumArrays(this.getxy(tab)) - this.getXSum(tab) * this.getYSum(tab);
      double denominator = (double)this.getN(tab) * this.sumArrays(this.getXSquareArray(tab)) - this.getXSum(tab) * this.getXSum(tab);
      double beta = numerator / denominator;
      return beta;
   }

   public LineChart regressionGraph(TableView<LinearRegression1> table) {
      double[] xarr = new double[]{0.0D, 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 11.0D, 12.0D, 13.0D, 14.0D, 15.0D};
      double[] yarr = new double[xarr.length];
      NumberAxis xAxis = new NumberAxis(1.0D, 15.0D, 1.0D);
      xAxis.setLabel("X Axis");
      NumberAxis yAxis = new NumberAxis();
      yAxis.setLabel("Y-Axis");
      Series series = new Series();
      series.setName("Fitted Regression Model");

      int i;
      for(i = 0; i < xarr.length; ++i) {
         yarr[i] = this.getAlpha(table) + this.getBeta(table) * xarr[i];
      }

      for(i = 0; i < xarr.length; ++i) {
         series.getData().add(new Data(xarr[i], yarr[i]));
      }

      LineChart lineChart = new LineChart(xAxis, yAxis);
      lineChart.getData().add(series);
      return lineChart;
   }

   public ScatterChart scatteredGraph(TableView<LinearRegression1> tab) {
      ObservableList<TableColumn<LinearRegression1, ?>> list = tab.getColumns();
      TableColumn<LinearRegression1, Double> xcol = (TableColumn)list.get(1);
      TableColumn<LinearRegression1, Double> ycol = (TableColumn)list.get(2);
      double[] xarr = (new Anova2()).getArrays(tab, xcol);
      double[] yarr = (new Anova2()).getArrays(tab, ycol);
      double b = 0.0D;
      NumberAxis xAxis = new NumberAxis();
      xAxis.setLabel("X-Axis");
      NumberAxis yAxis = new NumberAxis();
      yAxis.setLabel("Y-Axis");
      Series series = new Series();
      series.setName("Scattered Regression Plot");

      for(int i = 0; i < xarr.length; ++i) {
         series.getData().add(new Data(xarr[i], yarr[i]));
      }

      ScatterChart scatterChart = new ScatterChart(xAxis, yAxis);
      scatterChart.getData().add(series);
      return scatterChart;
   }

   public Scene RegressionScene(Stage stage, Scene scene) {
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
         stage.setScene(this.finalRegressionScene(stage, scene, this.table));
      });
      Region space = new Region();
      HBox hbox = new HBox(35.0D, new Node[]{this.addrow, this.removeRow, this.back, this.finish});
      hbox.setPadding(new Insets(15.0D));
      hbox.setAlignment(Pos.TOP_LEFT);
      VBox vb = new VBox(20.0D, new Node[]{this.table, space, hbox});
      VBox.setVgrow(space, Priority.ALWAYS);
      vb.setAlignment(Pos.TOP_LEFT);
      Scene sceneLR = new Scene(vb, 750.0D, 600.0D);
      stage.setTitle("CalPro- L i n e a r  R e g r e s s i o n");
      return sceneLR;
   }

   public Scene finalRegressionScene(Stage stage, Scene scene, TableView<LinearRegression1> table) {
      TableColumn<LinearRegression1, Double> xsquare = new TableColumn("X^2");
      TableColumn<LinearRegression1, Double> ysquare = new TableColumn("Y^2");
      TableColumn<LinearRegression1, Double> xy = new TableColumn("XY");
      xsquare.setPrefWidth(60.0D);
      ysquare.setPrefWidth(60.0D);
      xy.setPrefWidth(60.0D);
      xsquare.setMaxWidth(120.0D);
      ysquare.setMaxWidth(120.0D);
      xy.setMaxWidth(120.0D);
      xsquare.setCellValueFactory(new PropertyValueFactory("XSquare"));
      ysquare.setCellValueFactory(new PropertyValueFactory("YSquare"));
      xy.setCellValueFactory(new PropertyValueFactory("XY"));
      int[] arraycount = new int[table.getItems().size()];
      double[] arrayx = new double[table.getItems().size()];
      double[] arrayy = new double[table.getItems().size()];
      ObservableList<TableColumn<LinearRegression1, ?>> list = table.getColumns();
      TableColumn<LinearRegression1, Integer> ccol = (TableColumn)list.get(0);
      TableColumn<LinearRegression1, Double> xcol = (TableColumn)list.get(1);
      TableColumn<LinearRegression1, Double> ycol = (TableColumn)list.get(2);
      arraycount = (new Anova2()).extractInt(table, ccol);
      arrayx = (new Anova2()).getArrays(table, xcol);
      arrayy = (new Anova2()).getArrays(table, ycol);
      double[] array1 = this.getXSquareArray(table);
      double[] array2 = this.getYSquareArray(table);
      double[] array3 = this.getxy(table);
      table.getColumns().addAll(new TableColumn[]{xsquare, ysquare, xy});
      table.setEditable(false);
      Label label = new Label("Fitted Regression Model:");
      Label model = new Label();
      NumberFormat nf = NumberFormat.getNumberInstance();
      nf.setMaximumFractionDigits(3);
      String alph = nf.format(this.getAlpha(table));
      String bet = nf.format(this.getBeta(table));
      String text = "Y = " + alph + " + " + bet + "X";
      model.setText(text);
      Label xysum = new Label("∑XY: ");
      Label xsumysum = new Label("∑X∑Y: ");
      Label xsquaresum = new Label("∑(X^2): ");
      Label xsumsquare = new Label("((∑X)^2): ");
      Label ysum = new Label("∑Y: ");
      Label xsum = new Label("∑X: ");
      Label one = new Label("α: ");
      Label two = new Label("β: ");
      Label xysumVal = new Label();
      double[] xyarray = this.getxy(table);
      double xyarraysum = this.sumArrays(xyarray);
      xysumVal.setText(xyarraysum + "");
      Label xsumysumVal = new Label();
      double sumxVal = this.getXSum(table);
      double sumyVal = this.getYSum(table);
      double sumxyVal = sumxVal * sumyVal;
      xsumysumVal.setText(sumxyVal + "");
      Label xsquaresumVal = new Label();
      double[] xsquaresumarray = this.getXSquareArray(table);
      double xsquaresumarrayVal = this.sumArrays(xsquaresumarray);
      xsquaresumVal.setText(xsquaresumarrayVal + "");
      Label xsumsquareVal = new Label();
      double valuex = this.getXSum(table);
      double valuesq = valuex * valuex;
      xsumsquareVal.setText(valuesq + "");
      Label ysumVal = new Label();
      double valuey = this.getYSum(table);
      ysumVal.setText(valuey + "");
      Label xsumVal = new Label();
      double valuexsum = this.getXSum(table);
      xsumVal.setText(valuexsum + "");
      Label aVal = new Label();
      double alphaValue = this.getAlpha(table);
      aVal.setText(alphaValue + "");
      Label bVal = new Label();
      double betaValue = this.getBeta(table);
      bVal.setText(betaValue + "");
      HBox h1 = new HBox(5.0D, new Node[]{xysum, xysumVal});
      HBox h2 = new HBox(5.0D, new Node[]{xsumysum, xsumysumVal});
      HBox h3 = new HBox(5.0D, new Node[]{xsquaresum, xsquaresumVal});
      HBox h4 = new HBox(5.0D, new Node[]{xsumsquare, xsumsquareVal});
      HBox h5 = new HBox(5.0D, new Node[]{ysum, ysumVal});
      HBox h6 = new HBox(5.0D, new Node[]{xsum, xsumVal});
      HBox h7 = new HBox(5.0D, new Node[]{one, aVal});
      HBox h8 = new HBox(5.0D, new Node[]{two, bVal});
      HBox b1 = new HBox(15.0D, new Node[]{h1, h2, h3, h4});
      HBox b2 = new HBox(15.0D, new Node[]{h5, h6, h7, h8});
      VBox vlabels = new VBox(5.0D, new Node[]{b1, b2});
      table.getItems().clear();

      for(int i = 0; i < arrayx.length; ++i) {
         table.getItems().add(new LinearRegression1(arraycount[i], arrayx[i], arrayy[i], array1[i], array2[i], array3[i]));
      }

      HBox hbox = new HBox(10.0D, new Node[]{label, model});
      hbox.setAlignment(Pos.CENTER_LEFT);
      vlabels.getChildren().add(hbox);
      vlabels.setPadding(new Insets(10.0D));
      vlabels.setAlignment(Pos.CENTER_LEFT);
      this.back = new Button("Back");
      this.back.setOnAction((e) -> {
         stage.setScene(scene);
      });
      this.cancel = new Button("Cancel");
      stage.setOnCloseRequest((e) -> {
         e.consume();
         boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Application");
         if (b) {
            stage.close();
         }

      });
      this.cancel.setOnAction((e) -> {
         boolean b = Reconfirmation.closeBox("Are you sure you want to exit?", "Exit Application");
         if (b) {
            stage.close();
         }

      });
      Region space = new Region();
      table.setFixedCellSize(25.0D);
      HBox butPane = new HBox(15.0D, new Node[]{this.back, space, this.cancel});
      butPane.setPadding(new Insets(10.0D));
      HBox.setHgrow(space, Priority.ALWAYS);
      VBox rb = new VBox(5.0D, new Node[]{new Label(text), this.regressionGraph(table)});
      HBox graphPane = new HBox(15.0D, new Node[]{this.scatteredGraph(table), rb});
      graphPane.setAlignment(Pos.BOTTOM_CENTER);
      VBox finalPane = new VBox(10.0D);
      Region spacer = new Region();
      stage.setTitle("CalPro- L i n e a r  R e g r e s s i o n");
      finalPane.getChildren().addAll(new Node[]{table, vlabels, spacer, graphPane, butPane});
      VBox.setVgrow(spacer, Priority.ALWAYS);
      Scene lastscene = new Scene(finalPane, 850.0D, 650.0D);
      return lastscene;
   }
}
