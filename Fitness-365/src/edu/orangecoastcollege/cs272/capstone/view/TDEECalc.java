package edu.orangecoastcollege.cs272.capstone.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

public class TDEECalc implements SceneNavigation{
	@FXML
	private RadioButton maleCB;
	@FXML
	private RadioButton femaleCB;
	@FXML
	private TextField feetTF;
	@FXML
	private TextField inchesTF;
	@FXML
	private TextField bmrTF;
	@FXML
	private TextField tdeeTF;
	@FXML
	private ComboBox<String> activityCB;

	private static final String FXML_FILE_NAME = "TDEECalc.fxml";
	private Controller mController = Controller.getInstance();


	@FXML
	private TextField weightTF;
	@FXML
	private TextField ageTF;




	// Event Listener on ComboBox[#activityCB].onAction
	@FXML
	public void calculateCB()
	{
		calculate();
	}
	// Event Listener on Button[#updateButton].onAction
	@FXML
	public void updateProfile() 
	{
	    
		//mController.getCurrentUser().setTDEE(Integer.parseInt(tdeeTF.getText()));
		//mController.updateUser(mController.getCurrentUser(), , values)
	}
	// Event Listener on Button[#cancelButton].onAction
	@FXML
	public void cancel()
	{
		feetTF.clear();
		inchesTF.clear();
		weightTF.clear();
		bmrTF.clear();
		tdeeTF.clear();

		CalcHomePage home = new CalcHomePage();
		mController.changeScene(home.getView(), false);
	}
	// Event Listener on Button[#calcButton].onAction
	@FXML
	public void calculate()
	{
		if(!feetTF.getText().isEmpty() && !inchesTF.getText().isEmpty() && !weightTF.getText().isEmpty()
		        && !ageTF.getText().isEmpty()
				&& (maleCB.isSelected() || femaleCB.isSelected()))
		{
    		NumberFormat num = new DecimalFormat("#0.0");


	    	if(maleCB.isSelected())
	    	{
	    		double w = Double.parseDouble(weightTF.getText()) * 6.23;
	    		double h = ((Double.parseDouble(feetTF.getText()) * 12) +
	    				Double.parseDouble(inchesTF.getText())) * 12.7;
	    		double calc = 66 + w + h - (Double.parseDouble(ageTF.getText()) * 6.8);
	    		bmrTF.setText(num.format(calc).toString());
	    	}
	    	else if(femaleCB.isSelected())
	    	{
	    		double w = Double.parseDouble(weightTF.getText()) * 4.35;
	    		double h = ((Double.parseDouble(feetTF.getText()) * 12) +
	    				Double.parseDouble(inchesTF.getText())) * 4.7;
	    		double calc = 655 + w + h - (Double.parseDouble(ageTF.getText()) * 4.7);
	    		bmrTF.setText(num.format(calc).toString());
	    	}

	    	if(!activityCB.getSelectionModel().isEmpty())
	    	{
	    		Integer index = activityCB.getSelectionModel().getSelectedIndex();

	    		switch(index)
	    		{
	    		case 1:
	    			Double tdee1 = Double.parseDouble(bmrTF.getText()) * 1.2;
	    			tdeeTF.setText((num.format(tdee1).toString()));
	    			break;
	    		case 2:
	    			double tdee2 = Double.parseDouble(bmrTF.getText()) * 1.375;
	    			tdeeTF.setText(num.format(tdee2).toString());
	    			break;
	    		case 3:
	    			double tdee3 = Double.parseDouble(bmrTF.getText()) * 1.55;
	    			tdeeTF.setText(num.format(tdee3).toString());
	    			break;
	    		case 4:
	    			double tdee4 = Double.parseDouble(bmrTF.getText()) * 1.725;
	    			tdeeTF.setText(num.format(tdee4).toString());
	    			break;
	    		case 5:
	    			double tdee = Double.parseDouble(bmrTF.getText()) * 1.9;
	    			tdeeTF.setText(num.format(tdee).toString());
	    			break;
	    		default:
	    			break;

	    		}
	    	}
		}
    	else
    	{
    		bmrTF.setText("*Incorrect. Try again.");
    	}

	}
	@Override
	public Scene getView()
	{
		try {
			BorderPane ap = (BorderPane) FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
			return new Scene(ap);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

    public void initialize()
    {

        ObservableList<String> activities = FXCollections.observableArrayList();

        activities.add("");
        activities.add("Sedentary (Little to no exercise)");
        activities.add("Lightly Active (1-3 days/week)");
        activities.add("Moderately Active (3-5 days/week)");
        activities.add("Very Active (6-7 days/week)");
        activities.add("Extremely Active (Exercise/training 2x/day)");

        activityCB.setItems(activities);



    }
}