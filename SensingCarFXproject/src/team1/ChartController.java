package team1;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;


public class ChartController implements Initializable {
	
	ObservableList<XYChart.Data<String, Integer>> xyList1 = FXCollections.observableArrayList();
	ObservableList<XYChart.Data<String, Integer>> xyList2 = FXCollections.observableArrayList();

	ObservableList<String> myXaxisCategories = FXCollections.observableArrayList();
	
	int i;
	private Task<Date> task;
	
	private XYChart.Series xySeries1;
	private XYChart.Series xySeries2;
	private CategoryAxis xAxis;
	private int lastObservedSize;
	
	@FXML
	private LineChart<String, Number> sensingChart;
	@FXML
	private CheckBox thermistor;
	@FXML
	private CheckBox photoresistor;
	@FXML
	private CheckBox gas;
	@FXML
	private CheckBox ultrasonic;
	@FXML
	private CheckBox tracking;
	@FXML
	private Pane trackingPane;
	
	
	public void initialize(URL url, ResourceBundle rb) {
		
		xyList1.addListener((ListChangeListener<XYChart.Data<String, Integer>>) change -> {
			if (change.getList().size() > 10) {
				xAxis.getCategories().remove(0, 1);
			}
		});

		//stage.setTitle("Line Chart Sample");
		xAxis = new CategoryAxis();
		xAxis.setLabel("Time");

		final NumberAxis yAxis = new NumberAxis();
		sensingChart = new LineChart<>(xAxis,yAxis);

		sensingChart.setTitle("Chart for SensingCar");
		sensingChart.setAnimated(false);

		task = new Task<Date>() {
			@Override
			protected Date call() throws Exception {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException iex) {
						Thread.currentThread().interrupt();
					}

					if (isCancelled()) {
						break;
					}

					updateValue(new Date());
				}
				return new Date();
			}
		};

		task.valueProperty().addListener(new ChangeListener<Date>() {
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Random random = new Random();

			@Override
			public void changed(ObservableValue<? extends Date> observableValue, Date oldDate, Date newDate) {

				String strDate = dateFormat.format(newDate);
				myXaxisCategories.add(strDate);

				xyList1.add(new XYChart.Data(strDate, random.nextInt(100500)));
				xyList2.add(new XYChart.Data(strDate, random.nextInt(10050)));

			}
		});

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(task);

		//Scene scene  = new Scene(lineChart,800,600);

		xAxis.setCategories(myXaxisCategories);
		xAxis.setAutoRanging(false);

		xySeries1 = new XYChart.Series(xyList1);
		xySeries1.setName("Series 1");

		xySeries2 = new XYChart.Series(xyList2);
		xySeries2.setName("Series 2");

		sensingChart.getData().addAll(xySeries1, xySeries2);

		i = 0;

              
	   
	}
}
