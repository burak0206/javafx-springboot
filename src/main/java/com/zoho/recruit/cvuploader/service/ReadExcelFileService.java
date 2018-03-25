package com.zoho.recruit.cvuploader.service;

import com.zoho.recruit.cvuploader.model.Candidate;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ReadExcelFileService {



    public List<Candidate> getCandidates(File file) {
        List<Candidate> candidateList = new ArrayList<>();
        try {

            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                Candidate candidate = new Candidate();
                int count = 0;

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    String cellStr = currentCell.getStringCellValue();

                    if (count==0){
                        candidate.setName(cellStr);
                    } else if (count==1){
                        candidate.setEmail(cellStr);
                    } else if (count==2){
                        candidate.setCvTitle(cellStr);
                    }
                    count++;
                }
                if(candidate.isValid())
                    candidateList.add(candidate);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(candidateList.size());
        return candidateList;
    }

}
