package sample.Client;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Server.DataObject;


public class riskDetail extends Connection{
    public riskDetail() {
        d = new DataObject();
        d.setCompanyId(companyId);
        d.setCommand("confirmRisk");
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeObject(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            d = (DataObject) in.readObject();
            System.out.println(d.getMultiply());
            closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private DataObject d;
    @FXML
    private Button exit;

    @FXML
    private Text text;

    @FXML
    private Button diagCir;

    @FXML
    private Button risk;

    @FXML
    private Button margin;

    @FXML
    private Button bisnes;

    @FXML
    private Button report;

    @FXML
    private Button diffSum;

    @FXML
    private Button percent;

    @FXML
    void initialize() {
        exit.setOnAction(event -> {
            exit.getScene().getWindow().hide();
            newScene("main");
        });
        diagCir.setOnAction(event -> {
            diagCir.getScene().getWindow().hide();
            newScene("diagCircle");
        });
        percent.setOnAction(event -> {
            percent.getScene().getWindow().hide();
            newScene("percentDiff");
        });
        risk.setOnAction(event -> {
            risk.getScene().getWindow().hide();
            newScene("riskGetInfo");
        });
        margin.setOnAction(event -> {
            margin.getScene().getWindow().hide();
            newScene("margin");
        });
        bisnes.setOnAction(event -> {
            bisnes.getScene().getWindow().hide();
            newScene("bisnes");
        });
        diffSum.setOnAction(event -> {
            diffSum.getScene().getWindow().hide();
            newScene("diffSum");
        });
        report.setOnAction(event -> {
            report.getScene().getWindow().hide();
            try(
                    FileWriter writer = new FileWriter(d.getCompanyName() + "report.txt", false)
            )
            {
                writer.write("Название компании " + d.getCompanyName());
                writer.append('\n');
                writer.write( "ЗК " + d.getCredit());
                writer.append('\n');
                writer.write("СК " + d.getPercent());
                writer.append('\n');
                writer.write("Коэффицент " + d.getMultiply());
                writer.append('\n');
                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
            newScene("accept3");
        });
    }
}