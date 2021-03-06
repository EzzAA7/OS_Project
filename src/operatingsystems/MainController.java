/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystems;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author amjad
 */
public class MainController implements Initializable {

    @FXML
    private TextField procNum;
    @FXML
    private TextField timeQuantum;
    @FXML
    private Label generateLabel;
    @FXML
    private JFXComboBox selectAlgo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> options = FXCollections.observableArrayList("FCFS","RR");
        selectAlgo.getItems().addAll(options);
    }    

    @FXML
    private void Generate(ActionEvent event) {
        int procNumINT;
        int timeQuantumINT;
        Scheduler.clear();
        try
        {
            if( procNum.getText() != null && timeQuantum.getText() != null)
            {
                procNumINT = Integer.parseInt(procNum.getText());
                timeQuantumINT = Integer.parseInt(timeQuantum.getText());
                generateLabel.setText("OOP" + procNumINT);
                for (int i = 0; i < procNumINT; i++) {
                    Process process = new Process(timeQuantumINT);
                    System.out.println(process.toString());
                    Scheduler.Processes.add(process);
                }
                generateLabel.setText("Generated: " + procNumINT);
            }
        else
            generateLabel.setText("Please enter provide input!");
        }
        catch(NumberFormatException e)
        {
            generateLabel.setText("Please enter a valid number/s");
        }
       

    }


    @FXML
    public void ShowProcs(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("procList.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(tableViewScene);
        stage.show();
    }
    @FXML
    private void ExitApp(ActionEvent event) throws IOException {
        System.exit(0);
    }
    
    @FXML
    private void simulateData() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("procList.fxml"));
        loader.load();
        ProcListController cont = loader.getController();
        String option = selectAlgo.getValue().toString();
        if(option == "FCFS")
        {
            System.out.println("Initiating First come first server!");
            Scheduler.FCFS();  
        }
        else if(option == "RR")
        {
            System.out.println("Initiating Round Robin!");
            Scheduler.RR();
        }
        cont.refreshTable();
    }
    
    
}
