package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML private Button closeButton;
    @FXML private Button minimizeButton;

    @FXML private Button candidateButton1;
    @FXML private Button candidateButton2;
    @FXML private Button candidateButton3;
    @FXML private Button candidateButton4;

    @FXML private Button alertButton;

    private APIHandler apiHandler;
    private int candidateId;

    @FXML
    private void initialize() {
        apiHandler = new APIHandler();
        apiHandler.injectController(this);
        (new Thread(() -> {
            try {
                ArrayList<String> candidates = apiHandler.getCandidates();
                candidateButton1.setText(candidates.get(0));
                candidateButton2.setText(candidates.get(1));
                candidateButton3.setText(candidates.get(2));
                candidateButton4.setText(candidates.get(3));
            } catch (IOException e) {
                e.printStackTrace();
            }
        })).start();
    }

    @FXML
    private void onMinimize() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void onClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void vote1() {
        vote(0);
    }

    @FXML
    private void vote2() {
        vote(1);
    }

    @FXML
    private void vote3() {
        vote(2);
    }

    @FXML
    private void vote4() {
        vote(3);
    }

    private void vote(int candidateId) {
        this.candidateId = candidateId;
        try {
            apiHandler.postVote();
            createAlert();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getVote() {
        return candidateId;
    }

    private void createAlert() throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(minimizeButton.getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("alert.fxml"));
        dialog.setScene(new Scene(root));
        dialog.show();
    }

    @FXML
    private void dismissAlert() {
        Stage stage = (Stage) alertButton.getScene().getWindow();
        stage.close();
    }
}
