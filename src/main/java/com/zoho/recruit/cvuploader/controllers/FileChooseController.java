package com.zoho.recruit.cvuploader.controllers;


import com.zoho.recruit.cvuploader.config.StageManager;
import com.zoho.recruit.cvuploader.model.Candidate;
import com.zoho.recruit.cvuploader.service.ReadExcelFileService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class FileChooseController implements Initializable {

    private File file;

    @FXML
    private Label lblPage;

    @FXML
    private Text txtSelectedFile;

    @FXML
    private Button btnCVsProcess;

    @Autowired
    private ReadExcelFileService readExcelFileService;


    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    private void dosyaSec(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterXlsx = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        FileChooser.ExtensionFilter extFilterXls = new FileChooser.ExtensionFilter("Excel files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extFilterXlsx);
        fileChooser.getExtensionFilters().add(extFilterXls);

        file = fileChooser.showOpenDialog(stageManager.getPrimaryStage());
        if(file !=null){
            System.out.println(file);
            txtSelectedFile.setText(file.getPath());
            btnCVsProcess.setDisable(false);
        } else {
            btnCVsProcess.setDisable(true);
            txtSelectedFile.setText("Seçilen Dosya Yolu");
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void candidatesProcess(ActionEvent event) throws IOException {
        List<Candidate> candidates = readExcelFileService.getCandidates(file);
        candidates.stream().forEach(System.out::println);
    }
}
