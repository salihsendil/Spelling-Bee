package com.example.deneme;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class OzelOyunStarterController {

    public static ArrayList<Character> wordinputarraylist;
    public static ArrayList<String> library;
    public static ArrayList<String> pangram;
    public static ArrayList<String> pangramWithoutDuplication;

    @FXML
    private TextField wordinput;
    @FXML
    private Label warninglabel;

    @FXML
    private Button wordinputenter;

    @FXML
    void initialize() {
        pangramWithoutDuplication = new ArrayList<>();
        library = new ArrayList<>();
        pangram = new ArrayList<>();
        String path = "./sozluk_v2.txt";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                library.add(line);
                if (line.length() >= 7) {
                    char[] chars = line.toCharArray();
                    String removedDuplication = removeDuplicateCharsFromString(chars, line.length());
                    if (removedDuplication.length() == 7) {
                        pangram.add(line);
                        pangramWithoutDuplication.add(removedDuplication);
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
        }

    }

    public void clickEnter() throws IOException {
        wordinputarraylist = new ArrayList<>();
        for (int i = 0; i < wordinput.getLength(); i++)
            wordinputarraylist.add(wordinput.getText().toUpperCase().charAt(i));
        try {
            wordInputLengthController();
            wordInputDifferentLetterController();
            wordInputPangramLetterController();
            gamePageLoader();
        } catch (Exception e) {

        }
        wordinputarraylist.clear();

    }

    public void wordInputLengthController() throws Exception {
        if (wordinput.getLength() != 7) {
            warninglabel.setText("Lütfen 7 farklı harf giriniz!");
            throw new Exception();
        }
    }

    public void wordInputDifferentLetterController() throws Exception {
        for (int i = 0; i < wordinputarraylist.size(); i++) {
            for (int j = i + 1; j < wordinputarraylist.size(); j++)
                if (wordinputarraylist.get(i) == wordinputarraylist.get(j)) {
                    warninglabel.setText("Aynı harften iki veya daha fazla kez girdiniz!");
                    throw new Exception();
                }
        }

    }

    private void wordInputPangramLetterController() throws Exception {

        boolean check = false;
        for (String pWOD : pangramWithoutDuplication) {
            boolean[] wordChecker = {true, true, true, true, true, true, true};
            for (int i = 0; i < wordinputarraylist.size(); i++) {
                if (!pWOD.toUpperCase().contains(String.valueOf(wordinputarraylist.get(i)))) {
                    wordChecker[i] = false;
                }
            }
            if (wordChecker[0] && wordChecker[1] && wordChecker[2] && wordChecker[3] && wordChecker[4] && wordChecker[5] && wordChecker[6]) {
                check = true;
            }
        }

        if (check != true) {
            warninglabel.setText("Girilen Kelime Sözlükte Bulunamadı!");
            throw new Exception();
        }
    }

    private void gamePageLoader() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ozel-oyun.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("SpellingBeeGame");
        stage.setScene(scene);
        stage.show();
    }


    private static String removeDuplicateCharsFromString(char str[], int n) {
        int index = 0;
        for (int i = 0; i < n; i++) {
            int j;
            for (j = 0; j < i; j++) {
                if (str[i] == str[j]) {
                    break;
                }
            }
            if (j == i) {
                str[index++] = str[i];
            }
        }
        return String.valueOf(Arrays.copyOf(str, index));
    }
}
