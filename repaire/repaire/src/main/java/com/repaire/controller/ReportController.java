package com.repaire.controller;

import com.repaire.service.ReportService;
import com.repaire.util.Result;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Resource
    private ReportService reportService;
    @RequestMapping("/getItemReport")
    public Result getItemReport(){
        Result result= reportService.getItemReport();
        return result;
    }

    //导出报告数据
    @RequestMapping("/exportBusinessReport")
    public  String  exportBusinessReport(){
        try {
            File file = new File("E:\\upfile\\sdlg\\report_template.xlsx");
            InputStream inputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            //获取数据
            Result businessReportData = reportService.getBusinessReportData();
            Map<String,Object> map= (Map<String,Object>)businessReportData.getData();

            XSSFSheet sheetAt = workbook.getSheetAt(0);//第一页
            XSSFRow row = sheetAt.getRow(2);
            XSSFCell cell = row.getCell(5);
            String reportDate =(String) map.get("reportDate");
            cell.setCellValue(reportDate);




            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  "redirect:/pages/report_business.html";
    }
    //查询报告数据
    @RequestMapping("/getBusinessReportData")
    @ResponseBody
    public Result getBusinessReportData(){
        Result result = reportService.getBusinessReportData();
        return  result;
    }

}
