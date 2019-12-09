package sample.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Server.DataObject;

public class addCapital extends Connection{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField gratInvest;


    @FXML
    private TextField availableSum;


    @FXML
    private Button next;


    @FXML
    void initialize() throws NumberFormatException{
        next.setOnAction(event -> {
            DataObject d = new DataObject();
            d.setCommand("addCapital");
            try {
                if (Double.parseDouble(gratInvest.getText()) <0 || Double.parseDouble(availableSum.getText()) < 0) {
                    throw new Exception("bad");
                }
                d.setAvailableSum(availableSum.getText());
                d.setGratInvest(gratInvest.getText());
                d.setCompanyId(companyId);
                if (socket == null || socket.isClosed()) {
                    connect();
                }
                try {
                    out.writeObject(d);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                closeConnect();
                next.getScene().getWindow().hide();
                newScene("addTotalRisk");
            }
                catch (Exception e){
                next.getScene().getWindow().hide();
                newScene("error2");
                }
        });
    }

}
