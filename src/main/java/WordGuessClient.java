import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WordGuessClient extends Application {
	TextField s1,s2,s3,s4, c1, c2, clientplay1;
	Label port2, ip2, clientplay, player1points, player2points, p1plays, p2plays, p1guesses, p2guesses, hand, gues;
	Button b1, b3,b4, connect, refresh, update, playagain, quit;
	Button play0, play1, play2, play3, play4, play5, play6, play7, play8, play9, play10;
	Button guess0, guess1, guess2, guess3, guess4, guess5, guess6, guess7, guess8, guess9, guess10;
	TextField port, ip, p1points, p2points;
	VBox clientBox;
	Client clientConnection;
	boolean clientoneonserver = false;
	ListView<String> listItems, listItems2;
	ArrayList<Button> play = new ArrayList<Button>();
	ArrayList<Button> guess = new ArrayList<Button>();
	Wordguess clientinfo;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Client) Word Guess!!!");
		port = new TextField("");
		port2 = new Label("Port ");
		ip = new TextField("");
		ip2 = new Label("Ip    	");
		connect = new Button("Connect");
		refresh = new Button("Start game");
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
        	public void handle(WindowEvent t) {
				//clientConnection.clientinfo = new MorraInfo();
				Platform.exit();
				System.exit(0);
        	}
		});
		//start first scene
		startscene(primaryStage);
		refresh.setDisable(true);
		primaryStage.show();
	}
	//intro screen that lets client connect to server
	public void startscene(Stage primaryStage)
	{
		HBox ports = new HBox();
		ports.getChildren().addAll(port2, port);
		ports.setAlignment(Pos.CENTER);
		HBox ipaddress = new HBox();
		clientinfo = new Wordguess();
		listItems2 = new ListView<String>();
		ipaddress.setAlignment(Pos.CENTER);
		ipaddress.getChildren().addAll(ip2, ip);
		connect.setOnAction(e->{
			//check if port and ip address are input before connecting
			if(port.getText() == "")
			{
				
			}
			else if(ip.getText() == "")
			{
				
			}
			else
			{
				//create client 
				clientConnection = new Client(data->{
					Platform.runLater(()->{
							//recieve string object from callback.accept() and add it to listItems2;
							listItems2.getItems().add(data.toString());
					});
					}, Integer.parseInt(port.getText().trim()), ip.getText());
				clientConnection.start();
				refresh.setDisable(false);
				connect.setDisable(true);
		}});
		refresh.setOnAction(e->{
			//newScene(primaryStage);
		});
		VBox clientgui = new VBox();
		clientgui.getChildren().addAll(ports, ipaddress, connect, refresh);
		clientgui.setAlignment(Pos.CENTER);
		BorderPane clientBox = new BorderPane();
		clientBox.setCenter(clientgui);
		Scene scene = new Scene(clientBox, 300, 200);
		primaryStage.setScene(scene);
	}

}
