import com.sun.javafx.beans.event.AbstractNotifyListener;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.Observable;

public class LinechartTest extends Application {

    ObservableList<XYChart.Data<String, Integer>> xyList1 = FXCollections.observableArrayList();
    ObservableList<XYChart.Data<String, Integer>> xyList2 = FXCollections.observableArrayList();

    ObservableList<String> myXaxisCategories = FXCollections.observableArrayList();

    int i;
    private Task<Date> task;
    private LineChart<String,Number> lineChart;
    private XYChart.Series xySeries1;
    private XYChart.Series xySeries2;
    private CategoryAxis xAxis;

    @Override public void start(Stage stage) {

        xyList1.addListener((ListChangeListener<XYChart.Data<String, Integer>>) change -> {
           if (change.getList().size() >= 10) {
                
		xAxis.getCategories().remove(0, 1);
//              xAxis.getCategories().remove(lastObservedSize, lastObservedSize+10);
//		lineChart.getData().remove(lastObservedSize, lastObservedSize+10);
		
            }
        });

        stage.setTitle("Line Chart Sample");
        xAxis = new CategoryAxis();
        xAxis.setLabel("Time");

        final NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Sensing Car");
        lineChart.setAnimated(true);

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
		}
                xyList1.add(new XYChart.Data(strDate, Integer.valueOf(random.nextInt(50))));
                xyList2.add(new XYChart.Data(strDate, Integer.valueOf(random.nextInt(500) - random.nextInt(100))));

            }
        });

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(task);

        Scene scene  = new Scene(lineChart,800,600);

        xAxis.setCategories(myXaxisCategories);
	xAxis.setAutoRanging(false);

        xySeries1 = new XYChart.Series(xyList1);
        xySeries1.setName("Temperature");

        xySeries2 = new XYChart.Series(xyList2);
        xySeries2.setName("Gas");
	
        lineChart.getData().addAll(xySeries1, xySeries2);
	
        i = 0;

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            task.cancel();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}