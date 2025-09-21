package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class excelUtility {
    public FileInputStream file;
    public FileOutputStream fileOut;
    public XSSFWorkbook book;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public XSSFCellStyle cellStyle;
    String path;

    public excelUtility(String path)
    {
        this.path=path;
    }
    public int getRowCount(String sheetName) throws IOException
    {
        file=new FileInputStream(path);
        book=new XSSFWorkbook(file);
        sheet=book.getSheet(sheetName);
        int rowCount=sheet.getLastRowNum();
        book.close();
        file.close();
        return rowCount;
    }
    public int getCellCount(String sheetName,int rowNum) throws IOException {
        file=new FileInputStream(path);
        book=new XSSFWorkbook(file);
        sheet=book.getSheet(sheetName);
        row=sheet.getRow(rowNum);
      int  cellCount = row.getLastCellNum();
      book.close();
      file.close();
      return cellCount;
    }
    public String getCellData(String sheetNum,int rowNum,int column) throws IOException
    {
        file=new FileInputStream(path);
        book=new XSSFWorkbook(file);
        sheet=book.getSheet(sheetNum);
        row=sheet.getRow(rowNum);
        cell=row.getCell(column);

        DataFormatter formatter=new DataFormatter();
        String data;
        try {
           data = formatter.formatCellValue(cell);
        }catch (Exception e)
        {
            data="";
        }
        book.close();
        file.close();
        return data;
    }
    public void setCellData(String sheetNum, int rowNum, int column,String data) throws IOException {
        File xFile=new File(path);
        if(!xFile.exists())
        {
            book=new XSSFWorkbook();
            fileOut=new FileOutputStream(path);
            book.write(fileOut);
        }
        file=new FileInputStream(path);
        book=new XSSFWorkbook(file);
        if(book.getSheetIndex(sheetNum)==-1)
        {
            book.createSheet(sheetNum);
            sheet=book.getSheet(sheetNum);
        }
        if(sheet.getRow(rowNum)==null)
        {
            sheet.createRow(rowNum);
            row=sheet.getRow(rowNum);
        }
        cell=row.createCell(column);
        cell.setCellValue(data);
        fileOut=new FileOutputStream(path);
        book.write(fileOut);
        book.close();
        file.close();
        fileOut.close();
    }
    public void fillGreenColor(String sheetName, int rowNum, int column) throws IOException {
        file=new FileInputStream(path);
        book=new XSSFWorkbook(file);
        sheet=book.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell=row.getCell(column);
        cellStyle=book.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(cellStyle);
        book.write(fileOut);
        book.close();
        file.close();
        fileOut.close();

    }
    public void fillRedColor(String sheetName, int rowNum, int column) throws IOException {
        file=new FileInputStream(path);
        book=new XSSFWorkbook(file);
        sheet=book.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell=row.getCell(column);
        cellStyle=book.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.RED  .getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(cellStyle);
        book.write(fileOut);
        book.close();
        file.close();
        fileOut.close();

    }
}
