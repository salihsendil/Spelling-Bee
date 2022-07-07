package com.example.deneme;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class HizliOyunController {
    int sayac;
    int kontrolsayac;
    int puan;
    ArrayList<String> foundwordsarraylist = new ArrayList<>();
    ArrayList<String> library = new ArrayList<>();
    ArrayList<String> pangram = new ArrayList<>();
    ArrayList<String> pangramWithoutDuplication = new ArrayList<>();
    ArrayList<Character> chars = new ArrayList<>();


    @FXML
    private Text input1;
    @FXML
    private Text input2;
    @FXML
    private Text input3;
    @FXML
    private Text input4;
    @FXML
    private Text input5;
    @FXML
    private Text input6;
    @FXML
    private Text input7;

    @FXML
    private TextField output;
    @FXML
    private TextArea foundwords;
    @FXML
    private TextArea points;
    @FXML
    private Label alertlabel;
    @FXML
    private Text toplampuan;

    @FXML
    void initialize() {
        baslangic();
    }

    @FXML
    public void clickOnWord() {
        output.insertText(sayac, input1.getText());
        sayac++;
    }

    @FXML
    public void clickOnWord2() {
        output.insertText(sayac, input2.getText());
        sayac++;
    }

    @FXML
    public void clickOnWord3() {
        output.insertText(sayac, input3.getText());
        sayac++;
    }

    @FXML
    public void clickOnWord4() {
        output.insertText(sayac, input4.getText());
        sayac++;
    }

    @FXML
    public void clickOnWord5() {
        output.insertText(sayac, input5.getText());
        sayac++;
    }

    @FXML
    public void clickOnWord6() {
        output.insertText(sayac, input6.getText());
        sayac++;
    }

    @FXML
    public void clickOnWord7() {
        output.insertText(sayac, input7.getText());
        sayac++;
    }

    @FXML
    public void clickDelete() {
        for (int i = 0; i <= 0; i++) {
            output.deleteText(sayac - 1, sayac);
            sayac--;
        }
    }

    @FXML
    public void clickEnter() {

        if (library.stream().anyMatch(a -> output.getText().equals(a.toUpperCase())) &&
                output.getLength() != 0 &&
                output.getLength() > 3 &&
                output.getText().contains(input7.getText())) {
            if (foundwordsarraylist.stream().anyMatch(a -> output.getText().equals(a.toUpperCase()))) {
                alertlabel.setText("Kelime daha önce girildi!");
                output.deleteText(0, sayac);
                sayac = 0;
            } else {
                foundwordsarraylist.add(output.getText());
                foundwords.appendText(output.getText() + System.lineSeparator());
                output.deleteText(0, sayac);
                sayac = 0;
                if (foundwordsarraylist.get(kontrolsayac).length() == 4)
                    points.appendText("1" + System.lineSeparator());
                else
                    points.appendText(foundwordsarraylist.get(kontrolsayac).length() - 3 + System.lineSeparator());
                if (foundwordsarraylist.get(kontrolsayac).length() == 4)
                    toplampuan.setText(Integer.toString(puan = puan + 1));
                else
                    toplampuan.setText(Integer.toString((puan = puan + foundwordsarraylist.get(kontrolsayac).length() - 3)));
                kontrolsayac++;
            }

        } else if (output.getLength() <= 3 && output.getLength() != 0) {
            alertlabel.setText("Girilen kelime çok kısa!");
            output.deleteText(0, sayac);
            sayac = 0;
        } else if (!(output.getText().contains(input7.getText()))) {
            alertlabel.setText("Zorunlu harf kullanılmadı!");
            output.deleteText(0, sayac);
            sayac = 0;
        } else if (!(library.stream().anyMatch(a -> output.getText().equals(a.toUpperCase())))) {
            alertlabel.setText("Girilen kelime sözlükte mevcut değil!");
            output.deleteText(0, sayac);
            sayac = 0;
        } else
            alertlabel.setText("Lütfen kelime giriniz!");


    }

    @FXML
    public void baslangic() {
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
            startingLetterCorrector();
        } catch (Exception e) {
        }

    }

    public void startingLetterCorrector() {
        char[] ch = pangramWithoutDuplication.get(ThreadLocalRandom.current().nextInt(0, pangramWithoutDuplication.size() - 1)).toCharArray();

        for (char char1 : ch
        ) {
            this.chars.add(char1);
        }

        inputSetterWithOutCenterLetter();
        input7.setText(String.valueOf(chars.get(6)).toUpperCase());
        chars.remove(6);
    }

    public static String removeDuplicateCharsFromString(char str[], int n) {
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

    @FXML
    public void clickShuffle() {
        Collections.shuffle(chars);
        inputSetterWithOutCenterLetter();

    }

    private void inputSetterWithOutCenterLetter() {
        input1.setText(String.valueOf(chars.get(0)).toUpperCase());
        input2.setText(String.valueOf(chars.get(1)).toUpperCase());
        input3.setText(String.valueOf(chars.get(2)).toUpperCase());
        input4.setText(String.valueOf(chars.get(3)).toUpperCase());
        input5.setText(String.valueOf(chars.get(4)).toUpperCase());
        input6.setText(String.valueOf(chars.get(5)).toUpperCase());
    }

}








