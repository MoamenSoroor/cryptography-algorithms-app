package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.algorithms.DES_Algorithm;
import app.algorithms.RSA_Algorithm;
import app.core.Algorithm;
import app.core.Reporting;
import app.coreUI.Controller;
import app.coreUI.Model;
import app.coreUI.StageWindow;
import app.utils.UI.UtilsViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewController extends Controller<VBox> implements Reporting {



    @FXML
    private VBox vboxRoot;

    @FXML
    private ComboBox<String> comboboxSelectAlgorithm;

    @FXML
    private ComboBox<String> comboboxSelectKey;
    
    @FXML
    private Button buttonResetAll;

    @FXML
    private Button buttonGenerateRSA_Key;

    @FXML
    private Button buttonOptions;

    @FXML
    private Button buttonPrintKeys;
    
    @FXML
    private TextArea boxPlainTextKey;

    @FXML
    private TextArea boxPlainText;

    @FXML
    private Button buttonEncrypt;

    @FXML
    private Button buttonClearPlainKey;

    @FXML
    private Button buttonClearPlain;

    @FXML
    private Button buttonPlainKeyToCipherKey;

    @FXML
    private TextArea boxCipherTextKey;

    @FXML
    private TextArea boxCipherText;

    @FXML
    private Button buttonCipherKeyToPlainKey;

    @FXML
    private Button buttonClearCipher;

    @FXML
    private Button buttonClearCipherKey;

    @FXML
    private Button buttonDecrypt;

    @FXML
    private TabPane tabpaneLogsErrors;

    @FXML
    private Tab tabLogs;

    @FXML
    private TextArea boxLogs;
    

	private ViewModel viewModel;
		
	@Override
	public void linkModel() {
		viewModel = (ViewModel) stageWindow.getModel();
		comboboxSelectAlgorithm.getItems().addAll(viewModel.getNames());
		selectAlgorithm(DES_Algorithm.DES_ALGORITHM);
		buttonGenerateRSA_Key.setDisable(true);
		comboboxSelectKey.setDisable(true);
		buttonPrintKeys.setDisable(true);

		comboboxSelectAlgorithm.getSelectionModel().selectedItemProperty().addListener(obj -> {
			viewModel.selectAlgorithm(comboboxSelectAlgorithm.getSelectionModel().getSelectedItem());
			if(viewModel.getSelectedAlgorithm().getAlgorithmName().equals(RSA_Algorithm.RSA_NAME)) {
				buttonGenerateRSA_Key.setDisable(false);
				comboboxSelectKey.setDisable(false);
				buttonPrintKeys.setDisable(false);

				
			}
			else
			{
				buttonGenerateRSA_Key.setDisable(true);
				comboboxSelectKey.setDisable(true);
				buttonPrintKeys.setDisable(true);

			}
		});
		
		comboboxSelectKey.getSelectionModel().selectedItemProperty().addListener(e->{
			RSA_Algorithm rsa = (RSA_Algorithm) viewModel.getSelectedAlgorithm();
			rsa.selectKey(comboboxSelectKey.getSelectionModel().getSelectedItem());			
			writeLog(rsa.getSelectedKey().toString());
		});
		
		comboboxSelectKey.valueProperty().addListener(e->{
			RSA_Algorithm rsa = (RSA_Algorithm) viewModel.getSelectedAlgorithm();
			rsa.selectKey(comboboxSelectKey.getSelectionModel().getSelectedItem());			
			writeLog(rsa.getSelectedKey().toString());
		});
		
		

		buttonEncrypt.setOnAction(e -> {
			clearLogs();
			boxCipherText.setText(
					viewModel.getSelectedAlgorithm().encrypt(boxPlainTextKey.getText(), boxPlainText.getText()));
		});

		buttonDecrypt.setOnAction(e -> {
			clearLogs();
			boxPlainText.setText(
					viewModel.getSelectedAlgorithm().decrypt(boxCipherTextKey.getText(), boxCipherText.getText()));
		});

		buttonClearPlain.setOnAction(e -> {
			boxPlainText.clear();
		});

		buttonClearCipher.setOnAction(e -> {
			boxCipherText.clear();
		});
		
		buttonClearPlainKey.setOnAction(e -> {
			boxPlainTextKey.clear();
		});

		buttonClearCipherKey.setOnAction(e -> {
			boxCipherTextKey.clear();
		});
		
		
		buttonPlainKeyToCipherKey.setOnAction(e->{
			boxCipherTextKey.setText(boxPlainTextKey.getText());
		});
		
		buttonCipherKeyToPlainKey.setOnAction(e->{
			boxPlainTextKey.setText(boxCipherTextKey.getText());
		});
		
		buttonGenerateRSA_Key.setOnAction(e->{
			RSA_Algorithm rsa = (RSA_Algorithm) viewModel.getSelectedAlgorithm();
			rsa.generateRSA_List();
			comboboxSelectKey.getItems().addAll(rsa.getKeyNames());
			comboboxSelectKey.getSelectionModel().select(rsa.getSelectedKey().getKeyName());
			writeLog(rsa.getRSA_KeyList());
			buttonPrintKeys.setDisable(false);
		});
		
		buttonPrintKeys.setOnAction(e->{
			RSA_Algorithm rsa = (RSA_Algorithm) viewModel.getSelectedAlgorithm();
			writeLog(rsa.getRSA_KeyList());
		});
		
		buttonOptions.setOnAction(e->{
			loadOptionPane();
		});
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttonResetAll.setOnAction(event -> {
			boxCipherText.clear();
			boxCipherTextKey.clear();
			boxPlainText.clear();
			boxPlainTextKey.clear();
			boxLogs.clear();
		});

	}
	
	public void selectAlgorithm(String name) {
		
		viewModel.selectAlgorithm(name);
		comboboxSelectAlgorithm.getSelectionModel().select(viewModel.getSelectedAlgorithm().getAlgorithmName());
	}

	@Override
	public void writeLog(String line) {
		boxLogs.appendText(line + System.lineSeparator());
	}

	@Override
	public void writeError(String line) {
		boxLogs.appendText(line + System.lineSeparator());

	}

	@Override
	public void clearLogs() {
		boxLogs.clear();
	}
	
	public void loadOptionPane() {
		
		try {
			String location = "app/utils/UI/UtilsView.fxml";
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource(location));
			AnchorPane root = loader.load();
			Controller<AnchorPane> controller = loader.getController();
			Model<AnchorPane> model = new UtilsViewModel();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("view.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.sizeToScene();
			//stage.initOwner(stageWindow.getStage());
			stage.initModality(Modality.NONE);
			stage.initStyle(StageStyle.DECORATED);
			StageWindow<AnchorPane> stageWindow2 = new StageWindow<AnchorPane>(location, root, controller, model, scene, stage);
			controller.setStageWindow(stageWindow2);
			model.setStageWindow(stageWindow2);
			controller.linkModel();
			stage.setTitle("Options");
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

	// vboxRoot = con.getVboxRoot();
	// comboboxSelectAlgorithm = con.getComboboxSelectAlgorithm();
	// labelAlgorithmName = con.getLabelAlgorithmName();
	// boxPlainTextKey = con.getBoxPlainTextKey();
	// buttonEncrypt = con.getButtonEncrypt();
	// buttonPlainCopy = con.getButtonPlainCopy();
	// buttonPlainClear = con.getButtonPlainClear();
	// buttonCipherCopy = con.getButtonCipherCopy();
	// buttonCipherClear = con.getButtonCipherClear();
	// boxPlainText = con.getBoxPlainText();
	// boxCipherTextKey = con.getBoxCipherTextKey();
	// buttonDecrypt = con.getButtonDecrypt();
	// boxCipherText = con.getBoxCipherText();
	// tabpaneLogsErrors = con.getTabpaneLogsErrors();
	// tabLogs = con.getTabLogs();
	// boxLogs = con.getBoxErrors();
	// tabErrors = con.getTabErrors();
	// boxErrors = con.getBoxErrors();

}
