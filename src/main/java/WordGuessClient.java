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
	Button connect, refresh, food, game, countries, guessletter, guessword, playagain, quit;
	TextField port, ip;
	VBox foods, games, country, categories;
	HBox categoriesboxes, labels, texts, buttons, end;
	Client clientConnection;
	boolean clientoneonserver = false;
	ListView<String> listItems, listItems2;
	Wordguess clientinfo;
	boolean foodguessed = false;
	boolean gamesguessed = false;
	boolean countriesguessed = false;
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
								clientConnection.clientinfo.playagain = clientinfo.playagain;
								//System.out.println("Food: "+foodguessed);
								//System.out.println("Games: "+gamesguessed);
								//System.out.println("Countries: "+countriesguessed);
								//System.out.println("server Food: "+clientConnection.clientinfo.guessedfoods);
								//System.out.println("server Games: "+clientConnection.clientinfo.guessedgames);
								//System.out.println("server Countries: "+clientConnection.clientinfo.guessedcountries);
								if(clientConnection.clientinfo.won==true || clientConnection.clientinfo.lost==true)
								{
									clientConnection.clientinfo = new Wordguess();
									foodguessed = false;
									gamesguessed = false;
									countriesguessed = false;
									Wordguess tempinfo = new Wordguess();
									clientConnection.clientinfo.won=false;
									clientConnection.clientinfo.lost=false;
									clientConnection.clientinfo.category = "";
									clientConnection.clientinfo.guess = "";
									clientConnection.clientinfo.wordguess = "";
									clientConnection.clientinfo.guessedfoods = false;
									clientConnection.clientinfo.guessedgames = false;
									clientConnection.clientinfo.guessedcountries = false;
									tempinfo.won = clientConnection.clientinfo.won;
									tempinfo.lost = clientConnection.clientinfo.lost;
									tempinfo.category = clientConnection.clientinfo.category;
									tempinfo.guess = clientConnection.clientinfo.guess;
									tempinfo.wordguess = clientConnection.clientinfo.wordguess;
									tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
									tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
									tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
									clientConnection.send(tempinfo);
									playagain(primaryStage);
									listItems2.getItems().clear();
								}
								if((clientConnection.clientinfo.guessedfoods==true && gamesguessed ==true) || (foodguessed==true && clientConnection.clientinfo.guessedgames==true))
								{
									//client guessed food and game category
									Wordguess tempinfo = new Wordguess();
									clientConnection.clientinfo.category = "";
									clientConnection.clientinfo.guess = "";
									clientConnection.clientinfo.wordguess = "";
									clientConnection.clientinfo.guessedfoods = false;
									clientConnection.clientinfo.guessedgames = false;
									clientConnection.clientinfo.guessedcountries = false;
									foodguessed=true;
									gamesguessed =true;
									tempinfo.won = clientConnection.clientinfo.won;
									tempinfo.lost = clientConnection.clientinfo.lost;
									tempinfo.category = clientConnection.clientinfo.category;
									tempinfo.guess = clientConnection.clientinfo.guess;
									tempinfo.wordguess = clientConnection.clientinfo.wordguess;
									tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
									tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
									tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
									clientConnection.send(tempinfo);
									categorynofoodorgamesscene(primaryStage);
								}
								else if((clientConnection.clientinfo.guessedgames==true && countriesguessed ==true) || (clientConnection.clientinfo.guessedcountries==true && gamesguessed ==true))
								{
									//client guessed games and countries category
									Wordguess tempinfo = new Wordguess();
									clientConnection.clientinfo.category = "";
									clientConnection.clientinfo.guess = "";
									clientConnection.clientinfo.wordguess = "";
									clientConnection.clientinfo.guessedfoods = false;
									clientConnection.clientinfo.guessedgames = false;
									clientConnection.clientinfo.guessedcountries = false;
									gamesguessed =true;
									countriesguessed =true;
									tempinfo.won = clientConnection.clientinfo.won;
									tempinfo.lost = clientConnection.clientinfo.lost;
									tempinfo.category = clientConnection.clientinfo.category;
									tempinfo.guess = clientConnection.clientinfo.guess;
									tempinfo.wordguess = clientConnection.clientinfo.wordguess;
									tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
									tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
									tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
									clientConnection.send(tempinfo);
									categorynogamesorcountriesscene(primaryStage);
								}
								else if((clientConnection.clientinfo.guessedfoods==true && countriesguessed == true) || (foodguessed == true && clientConnection.clientinfo.guessedcountries==true))
								{
									//client guessed foods and countries category
									Wordguess tempinfo = new Wordguess();
									clientConnection.clientinfo.category = "";
									clientConnection.clientinfo.guess = "";
									clientConnection.clientinfo.wordguess = "";
									clientConnection.clientinfo.guessedfoods = false;
									clientConnection.clientinfo.guessedgames = false;
									clientConnection.clientinfo.guessedcountries = false;
									foodguessed = true;
									countriesguessed =true;
									tempinfo.won = clientConnection.clientinfo.won;
									tempinfo.lost = clientConnection.clientinfo.lost;
									tempinfo.category = clientConnection.clientinfo.category;
									tempinfo.guess = clientConnection.clientinfo.guess;
									tempinfo.wordguess = clientConnection.clientinfo.wordguess;
									tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
									tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
									tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
									clientConnection.send(tempinfo);
									categorynofoodsorcountriesscene(primaryStage);
								}
								else if(clientConnection.clientinfo.guessedfoods==true)
								{
									//client guessed foods
									Wordguess tempinfo = new Wordguess();
									foodguessed = true;
									clientConnection.clientinfo.category = "";
									clientConnection.clientinfo.guess = "";
									clientConnection.clientinfo.wordguess = "";
									clientConnection.clientinfo.guessedfoods = false;
									clientConnection.clientinfo.guessedgames = false;
									clientConnection.clientinfo.guessedcountries = false;
									tempinfo.won = clientConnection.clientinfo.won;
									tempinfo.lost = clientConnection.clientinfo.lost;
									tempinfo.category = clientConnection.clientinfo.category;
									tempinfo.guess = clientConnection.clientinfo.guess;
									tempinfo.wordguess = clientConnection.clientinfo.wordguess;
									tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
									tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
									tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
									clientConnection.send(tempinfo);
									categorynofoodscene(primaryStage);
								}
								else if(clientConnection.clientinfo.guessedgames==true)
								{
									//client guessed games
									Wordguess tempinfo = new Wordguess();
									gamesguessed = true;
									clientConnection.clientinfo.category = "";
									clientConnection.clientinfo.guess = "";
									clientConnection.clientinfo.wordguess = "";
									clientConnection.clientinfo.guessedfoods = false;
									clientConnection.clientinfo.guessedgames = false;
									clientConnection.clientinfo.guessedcountries = false;
									tempinfo.won = clientConnection.clientinfo.won;
									tempinfo.lost = clientConnection.clientinfo.lost;
									tempinfo.category = clientConnection.clientinfo.category;
									tempinfo.guess = clientConnection.clientinfo.guess;
									tempinfo.wordguess = clientConnection.clientinfo.wordguess;
									tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
									tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
									tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
									clientConnection.send(tempinfo);
									categorynogamesscene(primaryStage);
								}
								else if(clientConnection.clientinfo.guessedcountries==true)
								{
									//client guessed countries
									Wordguess tempinfo = new Wordguess();
									countriesguessed = true;
									clientConnection.clientinfo.category = "";
									clientConnection.clientinfo.guess = "";
									clientConnection.clientinfo.wordguess = "";
									clientConnection.clientinfo.guessedfoods = false;
									clientConnection.clientinfo.guessedgames = false;
									clientConnection.clientinfo.guessedcountries = false;
									tempinfo.won = clientConnection.clientinfo.won;
									tempinfo.lost = clientConnection.clientinfo.lost;
									tempinfo.category = clientConnection.clientinfo.category;
									tempinfo.guess = clientConnection.clientinfo.guess;
									tempinfo.wordguess = clientConnection.clientinfo.wordguess;
									tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
									tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
									tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
									clientConnection.send(tempinfo);
									categorynocountriesscene(primaryStage);
								}
								else if(clientConnection.clientinfo.playagain == true)
								{
									Wordguess tempinfo = new Wordguess();
									clientConnection.clientinfo.category = "";
									clientConnection.clientinfo.guess = "";
									clientConnection.clientinfo.wordguess = "";
									clientConnection.clientinfo.guessedfoods = false;
									clientConnection.clientinfo.guessedgames = false;
									clientConnection.clientinfo.guessedcountries = false;
									clientConnection.clientinfo.playagain = false;
									tempinfo.won = clientConnection.clientinfo.won;
									tempinfo.lost = clientConnection.clientinfo.lost;
									tempinfo.category = clientConnection.clientinfo.category;
									tempinfo.guess = clientConnection.clientinfo.guess;
									tempinfo.wordguess = clientConnection.clientinfo.wordguess;
									tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
									tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
									tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
									tempinfo.playagain = clientConnection.clientinfo.playagain;
									clientConnection.send(tempinfo);
									categoryscene(primaryStage);
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
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "foods";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			foodcategoryscene(primaryStage);
		});
		game.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "games";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			gamecategoryscene(primaryStage);
		});
		countries.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "countries";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			countrycategoryscene(primaryStage);
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(categoriesboxes);
		Scene scene = new Scene(bp, 350, 120);
		primaryStage.setScene(scene);
	}
	public void categorynofoodorgamesscene(Stage primaryStage)
	{
		primaryStage.setTitle("(Client) Please select a category");
		categoriesboxes = new HBox();
		countries = new Button("Countries");
		categoriesboxes.getChildren().addAll(countries);
		categoriesboxes.setAlignment(Pos.CENTER);
		countries.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "countries";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			countrycategoryscene(primaryStage);
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(categoriesboxes);
		Scene scene = new Scene(bp, 350, 120);
		primaryStage.setScene(scene);
	}
	public void categorynogamesorcountriesscene(Stage primaryStage)
	{
		primaryStage.setTitle("(Client) Please select a category");
		food = new Button("Foods");
		categoriesboxes = new HBox();
		categoriesboxes.getChildren().addAll(food);
		categoriesboxes.setAlignment(Pos.CENTER);
		food.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "foods";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			foodcategoryscene(primaryStage);
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(categoriesboxes);
		Scene scene = new Scene(bp, 350, 120);
		primaryStage.setScene(scene);
	}
	public void categorynofoodsorcountriesscene(Stage primaryStage)
	{
		primaryStage.setTitle("(Client) Please select a category");
		game = new Button("Games");
		categoriesboxes = new HBox();
		categoriesboxes.getChildren().addAll(game);
		categoriesboxes.setAlignment(Pos.CENTER);
		game.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "games";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			gamecategoryscene(primaryStage);
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(categoriesboxes);
		Scene scene = new Scene(bp, 350, 120);
		primaryStage.setScene(scene);
	}
	public void categorynofoodscene(Stage primaryStage)
	{
		primaryStage.setTitle("(Client) Please select a category");
		game = new Button("Games");
		categoriesboxes = new HBox();
		countries = new Button("Countries");
		categoriesboxes.getChildren().addAll(game, countries);
		categoriesboxes.setAlignment(Pos.CENTER);
		game.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "games";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			gamecategoryscene(primaryStage);
		});
		countries.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "countries";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			countrycategoryscene(primaryStage);
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(categoriesboxes);
		Scene scene = new Scene(bp, 350, 120);
		primaryStage.setScene(scene);
	}
	public void categorynogamesscene(Stage primaryStage)
	{
		primaryStage.setTitle("(Client) Please select a category");
		food = new Button("Foods");
		categoriesboxes = new HBox();
		countries = new Button("Countries");
		categoriesboxes.getChildren().addAll(food, countries);
		categoriesboxes.setAlignment(Pos.CENTER);
		food.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "foods";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			foodcategoryscene(primaryStage);
		});
		countries.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "countries";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			countrycategoryscene(primaryStage);
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(categoriesboxes);
		Scene scene = new Scene(bp, 350, 120);
		primaryStage.setScene(scene);
	}
	public void categorynocountriesscene(Stage primaryStage)
	{
		primaryStage.setTitle("(Client) Please select a category");
		food = new Button("Foods");
		game = new Button("Games");
		categoriesboxes = new HBox();
		categoriesboxes.getChildren().addAll(food, game);
		categoriesboxes.setAlignment(Pos.CENTER);
		food.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "foods";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			foodcategoryscene(primaryStage);
		});
		game.setOnAction(e->{
			Wordguess tempinfo = new Wordguess();
			clientConnection.clientinfo.category = "games";
			clientConnection.clientinfo.guess = "";
			clientConnection.clientinfo.wordguess="";
			tempinfo.won = clientConnection.clientinfo.won;
			tempinfo.lost = clientConnection.clientinfo.lost;
			tempinfo.category = clientConnection.clientinfo.category;
			tempinfo.guess = clientConnection.clientinfo.guess;
			tempinfo.wordguess = clientConnection.clientinfo.wordguess;
			tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
			tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
			tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
			clientConnection.send(tempinfo);
			gamecategoryscene(primaryStage);
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(categoriesboxes);
		Scene scene = new Scene(bp, 350, 120);
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
		foods = new VBox(labels, texts, buttons, listItems2);
		guessletter.setOnAction(e->{
			if((letter.getText().trim()=="") || (isInteger(letter.getText()) ==true) || (letter.getText().trim().length() != 1))
			{
				//do nothing
			}
			else
			{
				Wordguess tempinfo = new Wordguess();
				tempinfo.won = clientConnection.clientinfo.won;
				tempinfo.lost = clientConnection.clientinfo.lost;
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = letter.getText().trim();
    	    	tempinfo.wordguess = clientConnection.clientinfo.wordguess;
    	    	tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
				tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
				tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
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
				tempinfo.won = clientConnection.clientinfo.won;
				tempinfo.lost = clientConnection.clientinfo.lost;
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = clientConnection.clientinfo.guess;
    	    	tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
				tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
				tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
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
				tempinfo.won = clientConnection.clientinfo.won;
				tempinfo.lost = clientConnection.clientinfo.lost;
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = letter.getText().trim();
    	    	tempinfo.wordguess = clientConnection.clientinfo.wordguess;
    	    	tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
				tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
				tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
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
				tempinfo.won = clientConnection.clientinfo.won;
				tempinfo.lost = clientConnection.clientinfo.lost;
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = clientConnection.clientinfo.guess;
    	    	tempinfo.wordguess = word.getText().trim();
    	    	tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
				tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
				tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
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
		country = new VBox(labels, texts, buttons, listItems2);
		guessletter.setOnAction(e->{
			if((letter.getText().trim()=="") || (isInteger(letter.getText().toString()) ==true) || (letter.getText().trim().length() != 1))
			{
				//do nothing
			}
			else
			{
				Wordguess tempinfo = new Wordguess();
				tempinfo.won = clientConnection.clientinfo.won;
				tempinfo.lost = clientConnection.clientinfo.lost;
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = letter.getText().trim();
    	    	tempinfo.wordguess = clientConnection.clientinfo.wordguess;
    	    	tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
				tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
				tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
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
				tempinfo.won = clientConnection.clientinfo.won;
				tempinfo.lost = clientConnection.clientinfo.lost;
				tempinfo.category = clientConnection.clientinfo.category;
    	    	tempinfo.guess = clientConnection.clientinfo.guess;
    	    	tempinfo.wordguess =  word.getText().trim();
    	    	tempinfo.guessedfoods = clientConnection.clientinfo.guessedfoods;
				tempinfo.guessedgames = clientConnection.clientinfo.guessedgames;
				tempinfo.guessedcountries = clientConnection.clientinfo.guessedcountries;
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
		primaryStage.setTitle("(Client) Play again or quit");
		playagain = new Button("Play again");
		quit = new Button("Quit");
		end = new HBox(playagain, quit);
		end.setAlignment(Pos.CENTER);
		end.setSpacing(40);
		playagain.setOnAction(e->{
			categoryscene(primaryStage);
		});
		quit.setOnAction(e->{
			Platform.exit();
			System.exit(0);
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(end);
		Scene scene = new Scene(bp, 350, 100);
		primaryStage.setScene(scene);
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
