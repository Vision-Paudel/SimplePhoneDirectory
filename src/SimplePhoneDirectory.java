import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SimplePhoneDirectory extends Application{
	
	ArrayList<String> listOfPeopleNumbers = new ArrayList<String>();
		
	public static void main(String[] args) throws Exception {
		launch(args);		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		GridPane mainPane = new GridPane();		
		GridPane top = new GridPane();
		GridPane middle = new GridPane();
		GridPane bottom = new GridPane(); // Not used yet, maybe will be in the future
		
		// Create and Add Title
		Text title = new Text(" Directory ");
		title.setFont(Font.font ("Verdana", FontWeight.BOLD, 24));
		title.setFill(Color.GREEN);
		Reflection r = new Reflection();
		r.setFraction(0.5f);		 
		title.setEffect(r);		 		
		top.add(title, 0, 0);
		mainPane.setVgap(15.0);
		mainPane.add(top,0,0);
		
		// Create and Add New Phone Interface
		Text name = new Text(" Name: ");
		name.setFont(Font.font ("Verdana", FontWeight.BOLD, 18));
		Text number = new Text(" Number: ");
		number.setFont(Font.font ("Verdana", FontWeight.BOLD, 18));
		Text formatSuggestion = new Text(" Please include dashes. ");
		formatSuggestion.setFont(Font.font ("Verdana", FontWeight.BOLD, 12));
		TextField nameField = new TextField ();
		TextField numberField = new TextField ();
		Button submit = new Button("Submit");
		middle.add(name, 0, 0);
		middle.add(number, 0, 1);		
		middle.add(nameField, 1, 0);
		middle.add(numberField, 1, 1);
		middle.add(submit, 1, 2);
		mainPane.add(middle,0,1);
		mainPane.add(formatSuggestion, 0, 2);
				
		// Create and Add Display Interface
		TextArea information = new TextArea();
		information.setPadding(new Insets(2, 5, 2, 5));
		bottom.add(information, 0, 0);
		mainPane.add(bottom, 0, 3);
		
		// Display the scene.
		Scene newScene = new Scene(mainPane);	
		primaryStage.setScene(newScene);
		primaryStage.setWidth(460);
		primaryStage.setHeight(540);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Simple Phone Directory version 1.2 -  by Vision Paudel");
		primaryStage.show();
		getPhoneNumbers(information);
		
		// Event handler for submit button
		submit.setOnMouseClicked(   e -> {
			
			listOfPeopleNumbers.add( nameField.getText() + " " + numberField.getText()     );
			Collections.sort(listOfPeopleNumbers);
						
			try {
				PrintWriter fos = new PrintWriter(new File("numbers.txt"));
				
				for(int count = 0; count < listOfPeopleNumbers.size(); count++) {					
					fos.write(listOfPeopleNumbers.get(count) + "\n");						
				}
				fos.close();
				
				information.setText("");
				listOfPeopleNumbers = new ArrayList<String>();
				getPhoneNumbers(information);
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		    			
		});
				
		
	}
	
	// Get phone numbers from file and process.
	void getPhoneNumbers(TextArea information) throws Exception {
		
		File file = new File("numbers.txt");
		
		if(!file.exists()){
			throw new Exception("File not found: numbers.txt");	
		}
		else {
			
			Scanner input = new Scanner(file);
			int i = 0;			
			while(input.hasNext()) {
				listOfPeopleNumbers.add(input.nextLine());
				i++;   
			}
			Collections.sort(listOfPeopleNumbers);
			
			for(int count = 0; count < listOfPeopleNumbers.size(); count++) {				
				information.setText(information.getText() + listOfPeopleNumbers.get(count) + "\n"    );				
			}
			
			input.close();
		}
	}
	
}
