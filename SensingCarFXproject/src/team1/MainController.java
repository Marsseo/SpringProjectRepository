package team1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class MainController implements Initializable {

	@FXML
	private AnchorPane holderPane;

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			setNode(FXMLLoader.load(getClass().getResource("Home.fxml")));
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}	
	
	private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
	
	}
	private void cleanPane(){
		holderPane.getChildren().clear();
		holderPane.getChildren().removeAll();
	}

	@FXML
	private void switchHome(ActionEvent event) {
		try {
			cleanPane();
			setNode(FXMLLoader.load(getClass().getResource("Home.fxml")));
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void switchController(ActionEvent event) {
		try {
			cleanPane();
			setNode(FXMLLoader.load(getClass().getResource("Controller.fxml")));
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void switchChart(ActionEvent event) {
		try {
			cleanPane();
			setNode(FXMLLoader.load(getClass().getResource("Chart.fxml")));
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void switchCamera(ActionEvent event) {
		try {
			cleanPane();
			setNode(FXMLLoader.load(getClass().getResource("Camera.fxml")));
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void switchRGBnNF(ActionEvent event) {
		try {
			cleanPane();
			setNode(FXMLLoader.load(getClass().getResource("RGBnNF.fxml")));
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	
}
