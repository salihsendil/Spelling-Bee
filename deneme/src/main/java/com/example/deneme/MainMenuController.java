package com.example.deneme;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {


    public void clickHizliOyun() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hizli-oyun.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("SpellingBeeGame");
        stage.setScene(scene);
        stage.show();
    }

    public void clickCikis() {
        Alert logoutalert = new Alert(Alert.AlertType.CONFIRMATION);
        logoutalert.setTitle("Çıkış");
        logoutalert.setHeaderText("Çıkış Yap?");
        logoutalert.setContentText("Gerçekten çıkmak istiyor musunuz?");
        if (logoutalert.showAndWait().get() == ButtonType.OK)
            Platform.exit();
    }

    public void clickOzelOyun() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ozel-oyun-starter.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("SpellingBeeGame");
        stage.setScene(scene);
        stage.show();
    }
}
