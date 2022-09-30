package imc;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application {
	
	private Label pesoLabel, kg, alturaLabel, cm, imcLabel, estado;
	private TextField pesoText, alturaText;
	private DoubleProperty peso, alturaCM, alturaM, imc;
	private HBox pesoBox, alturaBox;
	private VBox root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		peso = new SimpleDoubleProperty();
		alturaCM = new SimpleDoubleProperty();
		alturaM = new SimpleDoubleProperty();
		imc = new SimpleDoubleProperty();
		
		pesoLabel = new Label();
		pesoLabel.setText("Peso:");
		
		pesoText = new TextField();
		pesoText.setMaxWidth(50);
		
		kg = new Label();
		kg.setText("kg");
		
		pesoBox = new HBox();
		pesoBox.setAlignment(Pos.CENTER);
		pesoBox.getChildren().addAll(pesoLabel, pesoText, kg);
		pesoBox.setSpacing(5);
		
		alturaLabel = new Label();
		alturaLabel.setText("Altura:");
		
		alturaText = new TextField();
		alturaText.setMaxWidth(50);
		
		cm = new Label();
		cm.setText("cm");
		
		alturaBox = new HBox();
		alturaBox.setAlignment(Pos.CENTER);
		alturaBox.getChildren().addAll(alturaLabel, alturaText, cm);
		alturaBox.setSpacing(5);
		
		imcLabel = new Label();
		
		estado = new Label();
		
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(pesoBox, alturaBox, imcLabel, estado);
		root.setFillWidth(false);
		root.setSpacing(5);
		
		Scene scene = new Scene(root, 320, 200);
		primaryStage.setTitle("IMC.fxml");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		pesoText.textProperty().bindBidirectional(peso, new NumberStringConverter());
		alturaText.textProperty().bindBidirectional(alturaCM, new NumberStringConverter());
		alturaM.bind(alturaCM.divide(100));
		imc.bind(peso.divide(alturaM.multiply(alturaM)));
		imcLabel.textProperty().bind(Bindings.concat("IMC: ").concat(imc));
		imcLabel.textProperty().addListener(event -> cambiaEstado());
		
	}
	
	private void cambiaEstado() {
		if(imc.get()>=0 && imc.get()<18.5) {
			estado.setText("Bajo peso");
		} else if(imc.get()>=18.5 && imc.get()<25) {
			estado.setText("Normal");
		} else if(imc.get()>=25 && imc.get()<30) {
			estado.setText("Sobrepeso");
		} else if(imc.get()>=30 && Double.isFinite(imc.get())) {
			estado.setText("Obeso");
		} else {
			estado.setText("IMC no válido");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
