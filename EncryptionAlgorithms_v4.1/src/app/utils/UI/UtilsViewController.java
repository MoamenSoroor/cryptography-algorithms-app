package app.utils.UI;

import java.net.URL;
import java.util.ResourceBundle;

import app.core.BinaryFormatter;
import app.coreUI.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UtilsViewController extends Controller<AnchorPane>{

    @FXML
    private AnchorPane root;

    @FXML
    private Tab tabMatcher;

    @FXML
    private TextArea boxText1;

    @FXML
    private TextArea boxText2;

    @FXML
    private Button buttonMatch;

    @FXML
    private TextArea boxResult;

    @FXML
    private Tab tabFormatter;

    @FXML
    private TextArea boxText11;

    @FXML
    private TextArea boxResult1;

    @FXML
    private Button buttonMatch11;

    @FXML
    private Button buttonMatch1;

    @FXML
    private Tab tabFormatOptions;

    @FXML
    private VBox boxFormatOptions;

    @FXML
    private ComboBox<Character> selectSeparator;

    @FXML
    private ComboBox<?> selectBlockLength;

    @FXML
    private RadioButton radioBits4_;

    @FXML
    private ToggleGroup fomratGroup;

    @FXML
    private RadioButton radioBits6_;

    @FXML
    private RadioButton radioBits8_;

    @FXML
    private RadioButton radioBits16_;

    @FXML
    private RadioButton radioBits4s;

    @FXML
    private RadioButton radioBits6s;

    @FXML
    private RadioButton radioBits8s;

    @FXML
    private RadioButton radioBits16s;

    @FXML
    private RadioButton radioBitsOther;

    @FXML
    private TextArea boxformat;
    
	public UtilsViewController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void linkModel() {
		buttonMatch.setOnAction(e->{
			if(BinaryFormatter.toOrdinaryString(boxText1.getText())
					.equals(BinaryFormatter.toOrdinaryString(
							boxText2.getText()))){
						boxResult.setText("Matches");
					}
			else
				boxResult.setText("Not Matches");
		});
		
	}

}
