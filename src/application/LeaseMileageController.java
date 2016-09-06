package application;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LeaseMileageController implements Initializable {
	@FXML
	private TextField txtLeaseStart;
	@FXML
	private TextField txtMilesPerYear;
	@FXML
	private TextField txtCurrMileage;
	@FXML
	private Button btnCalculate;
	@FXML
	private Button btnClear;
	@FXML
	private Label lblDispRecTotMlg;
	@FXML
	private Label lblDispCurOvg;
	@FXML
	private Label lblDispRecDlyMlg;
	@FXML
	private Label lblDispCurDlyMlg;
	@FXML
	private Label lblDispRecDlyChg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		//Calculate button event handler
		btnCalculate.setOnAction((event) -> {
			try {
				String dtStr = txtLeaseStart.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				Date leaseStart = sdf.parse(dtStr);
				
				double milesPerYear = Double.parseDouble(txtMilesPerYear.getText());
				
				double currentMiles = Double.parseDouble(txtCurrMileage.getText());
				
				Date today = new Date();
				// days since lease started
				double daysElapsed = (double) (today.getTime() - leaseStart.getTime()) / 1000 / 60 / 60 / 24;
				
				double recMPD = milesPerYear / 365; // recommended miles per day
				double actMPD = currentMiles / daysElapsed; // actual average daily mileage
				double recCurMiles = recMPD * daysElapsed; // recommended current total mileage
				
				lblDispRecTotMlg.setText(String.format("%.2f", recCurMiles) + " miles");
				lblDispCurOvg.setText(String.format("%.2f", currentMiles - recCurMiles) + " miles");
				lblDispRecDlyMlg.setText(String.format("%.2f", recMPD) + " miles");
				lblDispCurDlyMlg.setText(String.format("%.2f", actMPD) + " miles");
				lblDispRecDlyChg.setText(String.format("%.2f", recMPD - actMPD) + " miles");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		btnClear.setOnAction((event) -> {
			txtLeaseStart.clear();
			txtMilesPerYear.clear();
			txtCurrMileage.clear();
			
			lblDispRecTotMlg.setText("");
			lblDispCurOvg.setText("");
			lblDispRecDlyMlg.setText("");
			lblDispCurDlyMlg.setText("");
			lblDispRecDlyChg.setText("");
		});
	}
	
//	public boolean validate() {
//		
//	}
}
