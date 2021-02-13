package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import model.Classroom;
import model.UserAccount;

import java.io.File;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class ClassroomGUI {

	//Login

	@FXML
	private BorderPane mainPanetext;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	// Account List
	@FXML
	private ImageView showProfileImage;


	@FXML
	private TableView<UserAccount> showAccounts;

	@FXML
	private TableColumn<UserAccount,String> usernameColumn;

	@FXML
	private TableColumn<UserAccount,String> genderColumn;

	@FXML
	private TableColumn<UserAccount,String> careerColumn;

	@FXML
	private TableColumn<UserAccount,String> birthdayColumn;

	@FXML
	private TableColumn<UserAccount,String> browseColumn;

	//Register

	@FXML
	private TextField registerUsername;

	@FXML
	private TextField registerPhoto;

	@FXML
	private RadioButton genderMale;

	@FXML
	private ToggleGroup genderGroup;

	@FXML
	private RadioButton genderFemale;

	@FXML
	private RadioButton genderOther;

	@FXML
	private CheckBox SystemCareer;

	@FXML
	private CheckBox TelematicCareer;

	@FXML
	private CheckBox IndustrialCareer;

	@FXML
	private DatePicker registerBirthday;

	@FXML
	private ChoiceBox<String> browserChoice;

	@FXML
	private PasswordField registerPassword; 

	@FXML
	public void initialize() {

	}

	private Classroom classroom;


	public ClassroomGUI(Classroom classroom) {
		this.classroom= classroom;
	}

	public void showLogin() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlloader.setController(this);
		Parent root = fxmlloader.load();
		mainPanetext.getChildren().clear();
		mainPanetext.setCenter(root);
	}

	@FXML
	public void SignUp (ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("register.fxml"));
		fxmlloader.setController(this);
		Parent root = fxmlloader.load();
		mainPanetext.getChildren().clear();
		mainPanetext.setCenter(root);
		browserChoice.getItems().addAll("Chrome","Moxilla","Safari","Opera","Explore","Thor");
	}

	public UserAccount loginVerification (String username, String password) {
		UserAccount exists = null;
		boolean found = false;
		for (int i=0; i<classroom.getAccounts().size() && !found;i++) {
			if (classroom.getAccounts().get(i).getUsername().equals(username) && classroom.getAccounts().get(i).getPassword().equals(password)) {
				exists = classroom.getAccounts().get(i);
				found = true;
			}
		}
		return exists;
	}

	public UserAccount userVerification (String username) {
		UserAccount exists = null;
		String user = username;	
		boolean found = false;
		for (int i=0; i<classroom.getAccounts().size() && !found;i++) {
			if (classroom.getAccounts().get(i).getUsername().equals(user)) {
				exists = classroom.getAccounts().get(i);
				found = true;
			}
		}
		return exists;
	}
	
	public void login() throws IOException {
		if (loginVerification(txtUsername.getText(),txtPassword.getText())!=null) {			
			UserAccount user = loginVerification(txtUsername.getText(),txtPassword.getText());
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("account-list.fxml"));
			fxmlloader.setController(this);
			Parent root = fxmlloader.load(); 			
			mainPanetext.getChildren().clear();
			mainPanetext.getChildren().setAll(root);
			txtUsername.setText("");
			txtPassword.setText("");
			File f = new File(user.getPhoto());
			Image img = new Image(f.toURI().toString());
			initializableTableView();			
			this.registerUsername.setText(user.getUsername());
			this.showProfileImage.setImage(img);

		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Error");
			alert.setContentText("Invalid Username or Password");
			alert.showAndWait();
		}
	}

	public void SignIn(ActionEvent event) throws IOException {  	
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlloader.setController(this);
		Parent root = fxmlloader.load();
		mainPanetext.getChildren().clear();
		mainPanetext.setCenter(root);
	}


	@FXML
	public void createAnAccount(ActionEvent event) throws IOException {
		String career="";
		String gender= "";
		if(SystemCareer.isSelected()) {
			career="Software Engineering";
		}else if(TelematicCareer.isSelected()) {
			career="Telematic Engineering";
		}else if(IndustrialCareer.isSelected()) {
			career="Industrial Engineering";
		}
		if(genderMale.isSelected()) {
			gender="Male";
		}else if(genderFemale.isSelected()) {
			gender="Female";
		}else if(genderOther.isSelected()) {
			gender="Other";
		}
		if (userVerification(registerUsername.getText())==null) { 

			this.classroom.addUserAccount(registerUsername.getText(),
					registerPassword.getText(),
					registerPhoto.getText(),
					gender,
					career,
					registerBirthday.getValue().toString(),
					browserChoice.getSelectionModel().getSelectedItem().toString());
			showLogin();
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Error");
			alert.setContentText("The user name is unvailable");
			alert.showAndWait();
		}
	}

	@FXML
	public void logOut(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlloader.setController(this);
		Parent root = fxmlloader.load();
		mainPanetext.getChildren().clear();
		mainPanetext.setCenter(root);
	}

	@FXML
	public void browsePhoto(ActionEvent event) {
		FileChooser filec = new FileChooser();		
		filec.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images","*.jpg","*.png","*.jpeg"));
		File selectedFile = filec.showOpenDialog(null);
		if (selectedFile != null) {
			registerPhoto.setText(selectedFile.getAbsolutePath());
		}else {
			registerPhoto.setText("Invalid File");
		}
	}



	public void initializableTableView() {
		ObservableList <UserAccount> accountsArray = FXCollections.observableArrayList(classroom.getAccounts());
		showAccounts.setItems(accountsArray); 
		usernameColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("username"));
		genderColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("gender"));
		careerColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("career"));
		birthdayColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("birthday"));
		browseColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("browse"));
		showAccounts.setItems(accountsArray);
	}

}


