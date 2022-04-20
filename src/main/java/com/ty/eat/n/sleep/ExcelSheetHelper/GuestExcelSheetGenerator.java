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

import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.dto.User;

public class GuestExcelSheetGenerator {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Id", "Name", "Email-ID", "Password", "Phone Number", "Joining Date", "Checkout Date",
			"Total Amount", "Paid Amount", "Pending Amount", "Payment Status", "Govt ID" };
	static String SHEET = "guests";

	public static ByteArrayInputStream guestsToExcel(List<Guest> guests) {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);
			// Header
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}
			int rowIdx = 1;
			for (Guest guest : guests) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(guest.getId());
				row.createCell(1).setCellValue(guest.getName());
				row.createCell(2).setCellValue(guest.getEmail());
				row.createCell(3).setCellValue(guest.getPhoneNo());
				row.createCell(4).setCellValue(guest.getJoiningDate());
				row.createCell(5).setCellValue(guest.getCheckoutDate());
				row.createCell(6).setCellValue(guest.getTotalAmount());
				row.createCell(7).setCellValue(guest.getPaidAmount());
				row.createCell(8).setCellValue(guest.getPendingAmount());
				row.createCell(9).setCellValue(guest.getPaymentStatus());
				row.createCell(10).setCellValue(guest.getGovtID());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}

	}
}
