package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class dataProvider {
    @DataProvider(name = "LoginData")
    public Object [][] getData() throws IOException
    {
        String path="./testData/testData.xlsx";
                //"./testData/testData.xlsx";

        excelUtility excelUtil=new excelUtility(path);
        int totalRow=excelUtil.getRowCount("Sheet1");
        //System.out.println("totalRow = " + totalRow);
        int totalColumn=excelUtil.getCellCount("Sheet1",1);
        //System.out.println("totalColumn = " + totalColumn);
        Object[][] loginData =new String[totalRow][totalColumn];
        for(int i = 1;i<=totalRow;i++){
            for (int j = 0; j < totalColumn; j++) {
                loginData[i-1][j]=excelUtil.getCellData("Sheet1",i,j);
            }
        }
        return loginData;
    }

}
