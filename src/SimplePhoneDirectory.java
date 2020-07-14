import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SimplePhoneDirectory extends Application{
	
	ArrayList<String> listOfPeopleNumbers = new ArrayList<String>();
		
	public static void main(String[] args) throws Exception {
		launch(args);		// Launch Application with arguments
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Initialize GridPane layouts
		GridPane mainPane = new GridPane();		
		GridPane top = new GridPane();
		GridPane middle = new GridPane();
		GridPane bottom = new GridPane(); 
		GridPane format = new GridPane();
		
		// Create and add title
		Text title = new Text(" Directory ");
		title.setFont(Font.font ("Verdana", FontWeight.BOLD, 24));
		title.setFill(Color.GREEN);
		Reflection r = new Reflection();
		r.setFraction(0.5f);		 
		title.setEffect(r);		 		
		top.add(title, 0, 0);
		mainPane.setVgap(15.0);
		mainPane.add(top,0,0);
				
		// Create and add new phone interface
		Text name = new Text(" Name: ");
		name.setFont(Font.font ("Verdana", FontWeight.BOLD, 18));
		Text number = new Text(" Number: ");
		number.setFont(Font.font ("Verdana", FontWeight.BOLD, 18));
		Text formatSuggestion = new Text(" Please include dashes. ");
		formatSuggestion.setFont(Font.font ("Verdana", FontWeight.BOLD, 12));
		TextField nameField = new TextField ();
		TextField numberField = new TextField ();
		Button submit = new Button("Submit");
		GridPane.setHalignment(submit, HPos.RIGHT);
		middle.add(name, 0, 0);
		middle.add(number, 0, 1);		
		middle.add(nameField, 1, 0);
		middle.add(numberField, 1, 1);
		middle.add(submit, 1, 2);
		
		TextField searchField = new TextField ();
		Button submitSearch = new Button("Search");
		middle.setHgap(5);
		middle.setVgap(5);		
		middle.add(searchField, 0, 3);
		middle.add(submitSearch, 1, 3);
		mainPane.add(middle,0,1);
		
		// Create and add 'phone number display interface'
		TextArea information = new TextArea();
		information.setEditable(false);
		information.setPadding(new Insets(2, 5, 2, 5));
		information.setMaxWidth(500);
		information.setFont( Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 12.0));
		bottom.add(information, 0, 0);
		mainPane.add(bottom, 0, 4);				
		mainPane.add(formatSuggestion, 0, 2);	
		
		// Create font format area
		ComboBox<String> cbFonts = new ComboBox<String>();
		ObservableList<String> fontItemsList = FXCollections.observableArrayList( Font.getFamilies() );
		cbFonts.getItems().addAll(fontItemsList);		
		cbFonts.setValue("Times New Roman");
	
		ComboBox<Double> cbFontsSize = new ComboBox<Double>();
		List<Double> fontsSizes = new ArrayList<Double>();
		fontsSizes.add(8.0);	fontsSizes.add(12.0);	fontsSizes.add(16.0);	fontsSizes.add(20.0);	fontsSizes.add(24.0);	fontsSizes.add(28.0);	fontsSizes.add(32.0);	fontsSizes.add(36.0); 	
		ObservableList<Double> fontsSizeItemsList = FXCollections.observableArrayList( fontsSizes );
		cbFontsSize.getItems().addAll(fontsSizeItemsList);		
		cbFontsSize.setValue(12.0);
							
		Label lblBold = new Label("B");
		lblBold.setFont( Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 12.0) );
		CheckBox bold = new CheckBox();
		Label lblItallic = new Label("I");
		lblItallic.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 12.0));
		CheckBox itallic = new CheckBox();
		
		final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
		
		format.add(cbFonts, 0, 0);	
		format.add(cbFontsSize, 1, 0);
		format.add(lblBold, 2, 0);
		format.add(bold, 3, 0);
		format.add(lblItallic, 4, 0);
		format.add(itallic, 5, 0);
		format.add(colorPicker, 6, 0);
		format.setHgap(2);
		mainPane.add(format, 0, 3);
		
		
		cbFonts.setOnAction(  e -> {			
			javafx.scene.text.FontWeight isBold = (bold.isSelected()? FontWeight.BOLD : FontWeight.NORMAL);
			javafx.scene.text.FontPosture isItallic = (itallic.isSelected()? FontPosture.ITALIC : FontPosture.REGULAR);	
			information.setFont( Font.font(cbFonts.getValue(), isBold, isItallic, cbFontsSize.getValue()));
		});
		
		cbFontsSize.setOnAction(  e -> {
			javafx.scene.text.FontWeight isBold = (bold.isSelected()? FontWeight.BOLD : FontWeight.NORMAL);
			javafx.scene.text.FontPosture isItallic = (itallic.isSelected()? FontPosture.ITALIC : FontPosture.REGULAR);	
			information.setFont( Font.font(cbFonts.getValue(), isBold, isItallic, cbFontsSize.getValue()));
		});
		
		bold.setOnAction(  e -> {
			javafx.scene.text.FontWeight isBold = (bold.isSelected()? FontWeight.BOLD : FontWeight.NORMAL);
			javafx.scene.text.FontPosture isItallic = (itallic.isSelected()? FontPosture.ITALIC : FontPosture.REGULAR);	
			information.setFont( Font.font(cbFonts.getValue(), isBold, isItallic, cbFontsSize.getValue()));
		});
		
		itallic.setOnAction(  e -> {
			javafx.scene.text.FontWeight isBold = (bold.isSelected()? FontWeight.BOLD : FontWeight.NORMAL);
			javafx.scene.text.FontPosture isItallic = (itallic.isSelected()? FontPosture.ITALIC : FontPosture.REGULAR);	
			information.setFont( Font.font(cbFonts.getValue(), isBold, isItallic, cbFontsSize.getValue()));
		});
		
		colorPicker.setOnAction( e -> {
			  String value = ""+colorPicker.getValue();
			  value = value.substring(value.length()-8);
              information.setStyle("-fx-text-fill:#"+value+";");               
        });
					
		// Display the scene.
		Scene newScene = new Scene(mainPane);	
		primaryStage.setScene(newScene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(540);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Simple Phone Directory version 3.0 -  by Vision Paudel");
		primaryStage.show();
		getPhoneNumbers(information); // Initially collect numbers from numbers.txt
		
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
				System.out.println("File Not Found!");
			}
			  catch (Exception e2) {
				e2.printStackTrace();
			}
			
		});
		
		// Event handler for submitSearch button
		submitSearch.setOnMouseClicked( e -> {   
			TextArea searchInformation = new TextArea();
			searchInformation.setPadding(new Insets(2, 5, 2, 5));
			
			for(int i = 0; i < listOfPeopleNumbers.size(); i++) {
				String current = listOfPeopleNumbers.get(i).toString();
				String searchText = searchField.getText();
				if ( current.contains(searchText) ) {
					searchInformation.setText(searchInformation.getText() + listOfPeopleNumbers.get(i).toString() +"\n" );
				}
			}
			javafx.scene.text.FontWeight isBold = (bold.isSelected()? FontWeight.BOLD : FontWeight.NORMAL);
			javafx.scene.text.FontPosture isItallic = (itallic.isSelected()? FontPosture.ITALIC : FontPosture.REGULAR);	
			searchInformation.setFont( Font.font(cbFonts.getValue(), isBold, isItallic, cbFontsSize.getValue()));
			String value = ""+colorPicker.getValue();
			value = value.substring(value.length()-8);
			searchInformation.setStyle("-fx-text-fill:#"+value+";"); 
			BorderPane searchPane = new BorderPane();
			searchPane.setCenter(searchInformation);
			Scene searchScene = new Scene(searchPane);
			Stage searchStage = new Stage();
			searchStage.setScene(searchScene);
			searchStage.setWidth(300);
			searchStage.setHeight(310);
			searchStage.setResizable(true);
			searchStage.setTitle("Search Result!");
			searchStage.show();					
		});
				
				
	}
	
	// Get phone numbers from file and process.
	void getPhoneNumbers(TextArea information) throws FileNotFoundException {
		
		
		File file = new File("numbers.txt");
		
		if(!file.exists()){
			System.out.println("File not found: numbers.txt");	
		}
		else {
			
			Scanner input = new Scanner(file);
			int i = 0;			
			while(input.hasNext()) {
				listOfPeopleNumbers.add(input.nextLine());
				i++;   
			}
			System.out.println("Total lines read: " + i);
			Collections.sort(listOfPeopleNumbers);
			
			for(int count = 0; count < listOfPeopleNumbers.size(); count++) {				
				information.setText(information.getText() + listOfPeopleNumbers.get(count) + "\n"    );				
			}
			
			input.close();
		}
	}
	
}