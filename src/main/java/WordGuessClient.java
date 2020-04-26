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
	TextField s1,s2,s3,s4, c1, c2, clientplay1, letter, word;
	Label port2, ip2, category, letterlabel, wordlabel;
	Button connect, refresh, food, game, countries, guessletter, guessword,playanothercat;
	TextField port, ip;
	VBox foods, games, country, categories;
	HBox categoriesboxes, labels, texts, buttons;
	Client clientConnection;
	boolean clientoneonserver = false;
	ListView<String> listItems, listItems2;
	Wordguess clientinfo;
	boolean captured = false;
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
							if(data instanceof Wordguess)
							{
								//recieve MorraInfo object from callback.accept();
								Wordguess clientinfo = (Wordguess) data;
								clientConnection.clientinfo.numberofletters = clientinfo.numberofletters;
								clientConnection.clientinfo.remaininguess = clientinfo.remaininguess;
								clientConnection.clientinfo.won = clientinfo.won;
								clientConnection.clientinfo.lost = clientinfo.lost;
								clientConnection.clientinfo.guessedfoods = clientinfo.guessedfoods;
								clientConnection.clientinfo.guessedgames = clientinfo.guessedgames;
								clientConnection.clientinfo.guessedcountries = clientinfo.guessedcountries;
								if(clientConnection.clientinfo.won==true || clientConnection.clientinfo.lost==true)
								{
									playagain(primaryStage);
									clientConnection.clientinfo = new Wordguess();
									listItems2.getItems().clear();
								}
								if(clientConnection.clientinfo.guessedfoods==true && clientConnection.clientinfo.guessedgames==true )
								{
								}
								else if(clientConnection.clientinfo.guessedgames==true && clientConnection.clientinfo.guessedcountries==true)
								{
								}
								else if(clientConnection.clientinfo.guessedfoods==true && clientConnection.clientinfo.guessedcountries==true)
								{
								}
								else if(clientConnection.clientinfo.guessedfoods==true)
								{
								}
								else if(clientConnection.clientinfo.guessedgames==true)
								{
								}
								else if(clientConnection.clientinfo.guessedcountries==true)
								{
								}
								else
								{
								}
							}
							else
							{
									listItems2.getItems().add(data.toString());
							}
					});
					}, Integer.parseInt(port.getText().trim()), ip.getText());
				clientConnection.start();
				refresh.setDisable(false);
				connect.setDisable(true);
		}});
		refresh.setOnAction(e->{
			categoryscene(primaryStage);
		});
		VBox clientgui = new VBox();
		clientgui.getChildren().addAll(ports, ipaddress, connect, refresh);
		clientgui.setAlignment(Pos.CENTER);
		BorderPane clientBox = new BorderPane();
		clientBox.setCenter(clientgui);
		Scene scene = new Scene(clientBox, 300, 200);
		primaryStage.setScene(scene);
	}
	public void categoryscene(Stage primaryStage)
	{
		primaryStage.setTitle("(Client) Please select a category");
		food = new Button("Foods");
		game = new Button("Games");
		categoriesboxes = new HBox();
		countries = new Button("Countries");
		categoriesboxes.getChildren().addAll(food, game, countries);
		categoriesboxes.setAlignment(Pos.CENTER);
		food.setOnAction(e->{
			Wordguess tempguess = new Wordguess();
			clientConnection.clientinfo.category = "foods";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempguess.category = clientConnection.clientinfo.category;
			tempguess.guess = clientConnection.clientinfo.guess;
			tempguess.wordguess = clientConnection.clientinfo.wordguess;
			clientConnection.send(tempguess);
			foodcategoryscene(primaryStage);
		});
		game.setOnAction(e->{
			Wordguess tempguess = new Wordguess();
			clientConnection.clientinfo.category = "games";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempguess.category = clientConnection.clientinfo.category;
			tempguess.guess = clientConnection.clientinfo.guess;
			tempguess.wordguess = clientConnection.clientinfo.wordguess;
			clientConnection.send(tempguess);
			gamecategoryscene(primaryStage);
		});
		countries.setOnAction(e->{
			Wordguess tempguess = new Wordguess();
			clientConnection.clientinfo.category = "countries";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempguess.category = clientConnection.clientinfo.category;
			tempguess.guess = clientConnection.clientinfo.guess;
			tempguess.wordguess = clientConnection.clientinfo.wordguess;
			clientConnection.send(tempguess);
			countrycategoryscene(primaryStage);
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(categoriesboxes);
		Scene scene = new Scene(bp, 200, 200);
		primaryStage.setScene(scene);
	}
	public void foodcategoryscene(Stage primaryStage)
	{
		listItems2.getItems().clear();
		primaryStage.setTitle("(Client) Guess the food");
		letterlabel = new Label("Please guess letter");
		wordlabel = new Label("Please guess word");
		letter = new TextField("");
		word = new TextField("");
		playanothercat = new Button("Play another Category");
		guessletter = new Button("Guess Letter");
		guessword = new Button("Guess Word");
		labels = new HBox(letterlabel, wordlabel);
		labels.setAlignment(Pos.CENTER);
		texts = new HBox(letter, word);
		texts.setAlignment(Pos.CENTER);
		buttons = new HBox(guessletter, guessword);
		buttons.setAlignment(Pos.CENTER);
		labels.setSpacing(40);
		buttons.setSpacing(40);
		buttons.getChildren().remove(playanothercat);
		foods = new VBox(labels, texts, buttons, listItems2);
		guessletter.setOnAction(e->{
			if((letter.getText().trim()=="") || (isInteger(letter.getText()) ==true) || (letter.getText().trim().length() != 1))
			{
				//do nothing
			}
			else
			{
				Wordguess tempinfo = new Wordguess();
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = letter.getText().trim();
    	    	tempinfo.wordguess = clientConnection.clientinfo.wordguess;
    	    	clientConnection.send(tempinfo);
			}
		});
		guessword.setOnAction(e->{
			if(word.getText().trim()==""||isInteger(word.getText()) ==true || word.getText().length() < 2)
			{
				//do nothing
			}
			else
			{
				Wordguess tempinfo = new Wordguess();
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = clientConnection.clientinfo.guess;
    	    	tempinfo.wordguess = word.getText().trim();
    	    	clientConnection.send(tempinfo);
			}
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(foods);
		Scene scene = new Scene(bp, 400, 300);
		primaryStage.setScene(scene);
	}
	public void gamecategoryscene(Stage primaryStage)
	{
		listItems2.getItems().clear();
		primaryStage.setTitle("(Client) Guess the game");
		letterlabel = new Label("Please guess letter");
		wordlabel = new Label("Please guess word");
		letter = new TextField("");
		word = new TextField("");
		guessletter = new Button("Guess Letter");
		guessword = new Button("Guess Word");
		labels = new HBox(letterlabel, wordlabel);
		labels.setAlignment(Pos.CENTER);
		texts = new HBox(letter, word);
		texts.setAlignment(Pos.CENTER);
		buttons = new HBox(guessletter, guessword);
		buttons.setAlignment(Pos.CENTER);
		labels.setSpacing(40);
		buttons.setSpacing(40);
		games = new VBox(labels, texts, buttons, listItems2);
		guessletter.setOnAction(e->{
			if((letter.getText().trim()=="") || (isInteger(letter.getText().toString()) ==true) || (letter.getText().trim().length() != 1))
			{
				//do nothing
			}
			else
			{
				Wordguess tempinfo = new Wordguess();
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = letter.getText().trim();
    	    	tempinfo.wordguess = clientConnection.clientinfo.wordguess;
    	    	clientConnection.send(tempinfo);
			}
		});
		guessword.setOnAction(e->{
			if(word.getText().trim()=="" || isInteger(word.getText()) ==true || word.getText().length() < 2)
			{
				//do nothing
			}
			else
			{
				Wordguess tempinfo = new Wordguess();
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = clientConnection.clientinfo.guess;
    	    	tempinfo.wordguess = word.getText().trim();
    	    	clientConnection.send(tempinfo);
			}
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(games);
		Scene scene = new Scene(bp, 400, 300);
		primaryStage.setScene(scene);
	}
	public void countrycategoryscene(Stage primaryStage)
	{
		listItems2.getItems().clear();
		primaryStage.setTitle("(Client) Guess the country");
		letterlabel = new Label("Please guess letter");
		wordlabel = new Label("Please guess word");
		letter = new TextField("");
		word = new TextField("");
		guessletter = new Button("Guess Letter");
		guessword = new Button("Guess Word");
		labels = new HBox(letterlabel, wordlabel);
		labels.setAlignment(Pos.CENTER);
		texts = new HBox(letter, word);
		texts.setAlignment(Pos.CENTER);
		buttons = new HBox(guessletter, guessword);
		buttons.setAlignment(Pos.CENTER);
		labels.setSpacing(40);
		buttons.setSpacing(40);
		buttons.getChildren().remove(playanothercat);
		country = new VBox(labels, texts, buttons, listItems2);
		guessletter.setOnAction(e->{
			if((letter.getText().trim()=="") || (isInteger(letter.getText().toString()) ==true) || (letter.getText().trim().length() != 1))
			{
				//do nothing
			}
			else
			{
				Wordguess tempinfo = new Wordguess();
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = letter.getText().trim();
    	    	tempinfo.wordguess = clientConnection.clientinfo.wordguess;
    	    	clientConnection.send(tempinfo);
			}
		});
		guessword.setOnAction(e->{
			if(word.getText().trim()=="" || isInteger(word.getText()) ==true || word.getText().length() < 2)
			{
				//do nothing
			}
			else
			{
				Wordguess tempinfo = new Wordguess();
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = clientConnection.clientinfo.guess;
    	    	tempinfo.wordguess =  word.getText().trim();
    	    	clientConnection.send(tempinfo);
			}
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(country);
		Scene scene = new Scene(bp, 400, 300);
		primaryStage.setScene(scene);
	}
	public void playagain(Stage primaryStage)
	{
	}
	public boolean isInteger(String name)
	{
		//check if the name is integer
		try 
        { 
            // checking valid integer using parseInt() method 
            Integer.parseInt(name); 
            return true;
        }  
        catch (NumberFormatException e)  
        { 
            return false;
        } 
	}

}
