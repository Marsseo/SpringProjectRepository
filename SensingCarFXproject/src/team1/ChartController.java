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
import org.json.JSONObject;


public class ChartController implements Initializable {
	
	ObservableList<XYChart.Data<String, Integer>> xyList1 = FXCollections.observableArrayList();
	ObservableList<XYChart.Data<String, Integer>> xyList2 = FXCollections.observableArrayList();
	ObservableList<XYChart.Data<String, Integer>> xyList3 = FXCollections.observableArrayList();
	ObservableList<XYChart.Data<String, Integer>> xyList4 = FXCollections.observableArrayList();

	ObservableList<String> myXaxisCategories = FXCollections.observableArrayList();
	
	private Task<Date> task;
	
	private XYChart.Series xySeries1;
	private XYChart.Series xySeries2;
	private XYChart.Series xySeries3;
	private XYChart.Series xySeries4;
	private CategoryAxis xAxis;
	private CoapClient4Car client = new CoapClient4Car();
	private String trackingColor;
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

	public ChartController() {
		xAxis = new CategoryAxis();
		xAxis.setLabel("Time");
		
		final NumberAxis yAxis = new NumberAxis();
		sensingChart = new LineChart<>(xAxis,yAxis);
	}
	
	public void initialize(URL url, ResourceBundle rb) {
		
		sensingChart.setTitle("Chart for SensingCar");
		sensingChart.setAnimated(false);
					
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		tracking.setOnAction(e->{
			if(tracking.isSelected()){
				trackingPane.setVisible(true);
			}else{
				trackingPane.setVisible(false);
			}
		});
		
//		if(curColor().equals("white")){
//			
//		}
		
		trackingPane.setStyle("StringProperty [bean: Pane[id=trackingPane], name: style, value: -fx-border-color: black; -fx-background-color: white;]");
		System.out.println(trackingPane.styleProperty());
		
		
		xyList1.addListener((ListChangeListener<XYChart.Data<String, Integer>>) change -> {
			if (change.getList().size() > 10) {
				xAxis.getCategories().remove(0, 1);
			}
		});
		

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
				
				if(xyList1.size()>=10){
					xyList1.remove(0, 1);
					xyList2.remove(0, 1);
					xyList3.remove(0, 1);
					xyList4.remove(0, 1);
				}
				xyList1.add(new XYChart.Data(strDate, random.nextInt(50)));
				xyList2.add(new XYChart.Data(strDate, random.nextInt(200)));
				xyList3.add(new XYChart.Data(strDate, random.nextInt(255)));
				xyList4.add(new XYChart.Data(strDate, random.nextInt(400)));
				
//				xyList1.add(new XYChart.Data(strDate, client.curObserveState("thermistorsensor")));
//				xyList2.add(new XYChart.Data(strDate, client.curObserveState("photoresistorsensor")));
//				xyList3.add(new XYChart.Data(strDate, client.curObserveState("gassensor")));
//				xyList4.add(new XYChart.Data(strDate, client.curObserveState("ultrasonicsensor")));

			}
		});
		
		executor.submit(task); 
		
		xAxis.setCategories(myXaxisCategories);
		xAxis.setAutoRanging(false);

		xySeries1 = new XYChart.Series(xyList1);
		xySeries1.setName("온도");

		xySeries2 = new XYChart.Series(xyList2);
		xySeries2.setName("조도");
		
		xySeries3 = new XYChart.Series(xyList3);
		xySeries3.setName("가스");

		xySeries4 = new XYChart.Series(xyList4);
		xySeries4.setName("거리(초음파)");

		sensingChart.getData().addAll(xySeries1, xySeries2, xySeries3, xySeries4);		      
	   
	}
	
	private String curColor(){
		String response = client.curState("trackingsensor");
		JSONObject jsonObject = new JSONObject(response);
		String color = jsonObject.getString("tracking");
		trackingColor = color;
		return color;
	}
		
}
