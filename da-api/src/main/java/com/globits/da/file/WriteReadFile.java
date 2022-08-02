package com.globits.da.file;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.ProvinceDto;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static com.globits.da.Constants.SHEET_NAME;

public class WriteReadFile {
    // ghi danh sach employee ra file excel
     public  static Boolean writeFile(List<EmployeeDto> list, String path){
        HSSFWorkbook workbook = new HSSFWorkbook();
        // tao mot sheet excel
        HSSFSheet sheet = workbook.createSheet(SHEET_NAME);

        int rowNum = 0;
        Cell cell;
        Row row;
        row = sheet.createRow(rowNum);
        // write tieu de cho bang excel
        String[] tile = {"STT","Code","Name","Email","Phone","Age"};
        for (int i = 0; i < tile.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(tile[i]);
        }
        // ghi du lieu theo cot
        for (EmployeeDto item: list) {
            rowNum++;
            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(rowNum);
            cell = row.createCell(1);
            cell.setCellValue(item.getCode());
            cell = row.createCell(2);
            cell.setCellValue(item.getName());
            cell = row.createCell(3);
            cell.setCellValue(item.getEmail());
            cell = row.createCell(4);
            cell.setCellValue(item.getPhone());
            cell = row.createCell(5);
            cell.setCellValue(item.getAge());
        }
        File file = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
         return false;
    }
    // doc file excel tra lai 1 list employee
     public static List<EmployeeDto> readFile(String path){
        List<EmployeeDto> dtoList = new ArrayList<>();
        File file = new File(path);
        try {
            FileInputStream fis = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet(SHEET_NAME);

            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
            DataFormatter fm = new DataFormatter();
            while (iterator.hasNext()){
                Row row = iterator.next();
                EmployeeDto dto = new EmployeeDto();
                dto.setCode(row.getCell(1).getStringCellValue());
                dto.setName(row.getCell(2).getStringCellValue());
                dto.setEmail(row.getCell(3).getStringCellValue());
                dto.setPhone(row.getCell(4).getStringCellValue());
                dto.setAge(Integer.parseInt(fm.formatCellValue(row.getCell(5))));
                dtoList.add(dto);
            }
            fis.close();
            return  dtoList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

         return null;
    }
}
