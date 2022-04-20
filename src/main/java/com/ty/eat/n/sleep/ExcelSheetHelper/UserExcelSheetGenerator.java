package com.ty.eat.n.sleep.ExcelSheetHelper;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ty.eat.n.sleep.dto.User;

public class UserExcelSheetGenerator {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Id", "Name", "Email-ID", "Phone Number", "Role" };
	static String SHEET = "users";

	public static ByteArrayInputStream usersToExcel(List<User> users) {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);
			// Header
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}
			int rowIdx = 1;
			for (User user : users) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(user.getId());
				row.createCell(1).setCellValue(user.getName());
				row.createCell(2).setCellValue(user.getEmail());
				row.createCell(3).setCellValue(user.getPhoneNo());
				row.createCell(4).setCellValue(user.getRole());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}

	}
}