package com.ty.eat.n.sleep.ExcelSheetHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.User;

public class BranchExcelSheetGenerator {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Id", "Name", "Email-ID", "Phone Number", "PG Name","Address","Wifi_Passcode" };
	static String SHEET = "branches";

	public static ByteArrayInputStream branchesToExcel(List<Branch> branches) {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);
			// Header
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}
			int rowIdx = 1;
			for (Branch branch : branches) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(branch.getId());
				row.createCell(1).setCellValue(branch.getName());
				row.createCell(2).setCellValue(branch.getEmail());
				row.createCell(3).setCellValue(branch.getPhoneNo());
				row.createCell(4).setCellValue(branch.getPgName());
				row.createCell(5).setCellValue(branch.getAddress());
				row.createCell(6).setCellValue(branch.getWifiPassword());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}

	}
}
