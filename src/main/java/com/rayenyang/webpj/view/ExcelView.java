package com.rayenyang.webpj.view;

import com.rayenyang.webpj.entity.user.User;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * description:
 * Created by rayenyang on 2017/6/19.
 */

public class ExcelView extends AbstractXlsView {
    
    
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        final List<User> userList = (List<User>) map.get("userList");
    
        //创建工作表
        final Sheet sheet = workbook.createSheet("用户列表");
        sheet.setDefaultColumnWidth(50);
    
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        
        //设置表头
        final Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("用户名");
        header.getCell(0).setCellStyle(cellStyle);
        header.createCell(1).setCellValue("密码");
        sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));
        
        int rowCount = 1;
    
        for (User user : userList) {
            final Row row = sheet.createRow(rowCount);
//            row.
            row.createCell(0).setCellValue(user.getUserName());
            row.createCell(1).setCellValue(user.getPassword());
            rowCount++;
        }
        
    
    
    }
}
