package com.hotelbooking.controller;

import java.io.File;
import java.io.FileOutputStream;

import com.hotelbooking.models.Invoice;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BillingController {

    @FXML private Label invoiceIdLabel;
    @FXML private Label bookingIdLabel;
    @FXML private Label guestNameLabel;
    @FXML private Label roomLabel;
    @FXML private Label checkInLabel;
    @FXML private Label checkOutLabel;
    @FXML private Label nightsLabel;
    @FXML private Label roomChargesLabel;
    @FXML private Label taxLabel;
    @FXML private Label grandTotalLabel;

    private Invoice currentInvoice;
    private String currentGuestName;
    private String currentRoomInfo;
    private String currentCheckIn;
    private String currentCheckOut;
    private long currentNights;

    public void setInvoiceData(Invoice invoice, String guestName, String roomInfo,
                                String checkIn, String checkOut, long nights) {
        this.currentInvoice = invoice;
        this.currentGuestName = guestName;
        this.currentRoomInfo = roomInfo;
        this.currentCheckIn = checkIn;
        this.currentCheckOut = checkOut;
        this.currentNights = nights;

        invoiceIdLabel.setText(String.valueOf(invoice.getId()));
        bookingIdLabel.setText(String.valueOf(invoice.getBookingId()));
        guestNameLabel.setText(guestName);
        roomLabel.setText(roomInfo);
        checkInLabel.setText(checkIn);
        checkOutLabel.setText(checkOut);
        nightsLabel.setText(nights + " night" + (nights > 1 ? "s" : ""));
        roomChargesLabel.setText("Rs." + String.format("%.2f", invoice.getTotalAmount()));
        taxLabel.setText("Rs." + String.format("%.2f", invoice.getTax()));
        grandTotalLabel.setText("Rs." + String.format("%.2f", invoice.getGrandTotal()));
    }

    @FXML
    private void handleDownloadPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Invoice PDF");
        fileChooser.setInitialFileName("Invoice_" + currentInvoice.getId() + "_" + currentGuestName.replaceAll("\\s+", "_") + ".pdf");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        Stage stage = (Stage) invoiceIdLabel.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            generatePDF(file);
        }
    }

    private void generatePDF(File file) {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // Colors
            BaseColor darkBrown  = new BaseColor(59, 42, 26);
            BaseColor orange     = new BaseColor(232, 127, 36);
            BaseColor cream      = new BaseColor(254, 253, 223);
            BaseColor lightGray  = new BaseColor(232, 213, 184);
            BaseColor green      = new BaseColor(39, 174, 96);
            BaseColor mutedBrown = new BaseColor(122, 102, 82);

            // Fonts
            Font titleFont    = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD, darkBrown);
            Font subtitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, orange);
            Font labelFont    = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, mutedBrown);
            Font valueFont    = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, darkBrown);
            Font totalFont    = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, darkBrown);
            Font grandFont    = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, green);
            Font footerFont   = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC, mutedBrown);

            // Header background
            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(100);
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(darkBrown);
            headerCell.setPadding(20);
            headerCell.setBorder(Rectangle.NO_BORDER);

            Paragraph hotelName = new Paragraph("HOTEL BOOKING SYSTEM", 
                new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, cream));
            hotelName.setAlignment(Element.ALIGN_CENTER);
            headerCell.addElement(hotelName);

            Paragraph invoiceTag = new Paragraph("INVOICE", 
                new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, new BaseColor(255, 200, 30)));
            invoiceTag.setAlignment(Element.ALIGN_CENTER);
            headerCell.addElement(invoiceTag);
            headerTable.addCell(headerCell);
            document.add(headerTable);

            document.add(Chunk.NEWLINE);

            // Info table
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setWidths(new float[]{2f, 3f});
            infoTable.setSpacingBefore(10);

            String[][] rows = {
                {"Invoice ID",   String.valueOf(currentInvoice.getId())},
                {"Booking ID",   String.valueOf(currentInvoice.getBookingId())},
                {"Guest Name",   currentGuestName},
                {"Room",         currentRoomInfo},
                {"Check-In",     currentCheckIn},
                {"Check-Out",    currentCheckOut},
                {"Nights",       currentNights + " night" + (currentNights > 1 ? "s" : "")},
            };

            for (String[] row : rows) {
                PdfPCell labelCell = new PdfPCell(new Phrase(row[0], labelFont));
                labelCell.setBorderColor(lightGray);
                labelCell.setPadding(8);
                labelCell.setBackgroundColor(new BaseColor(253, 248, 238));
                infoTable.addCell(labelCell);

                PdfPCell valueCell = new PdfPCell(new Phrase(row[1], valueFont));
                valueCell.setBorderColor(lightGray);
                valueCell.setPadding(8);
                infoTable.addCell(valueCell);
            }
            document.add(infoTable);

            document.add(Chunk.NEWLINE);

            // Billing breakdown table
            PdfPTable billingTable = new PdfPTable(2);
            billingTable.setWidthPercentage(100);
            billingTable.setWidths(new float[]{2f, 3f});

            String[][] billing = {
                {"Room Charges", "Rs." + String.format("%.2f", currentInvoice.getTotalAmount())},
                {"Tax (18% GST)", "Rs." + String.format("%.2f", currentInvoice.getTax())},
            };

            for (String[] row : billing) {
                PdfPCell lc = new PdfPCell(new Phrase(row[0], labelFont));
                lc.setBorderColor(lightGray);
                lc.setPadding(8);
                lc.setBackgroundColor(new BaseColor(253, 248, 238));
                billingTable.addCell(lc);

                PdfPCell vc = new PdfPCell(new Phrase(row[1], valueFont));
                vc.setBorderColor(lightGray);
                vc.setPadding(8);
                billingTable.addCell(vc);
            }

            // Grand total row
            PdfPCell gtLabel = new PdfPCell(new Phrase("GRAND TOTAL", totalFont));
            gtLabel.setBorderColor(lightGray);
            gtLabel.setPadding(12);
            gtLabel.setBackgroundColor(new BaseColor(255, 200, 30, 60));
            billingTable.addCell(gtLabel);

            PdfPCell gtValue = new PdfPCell(new Phrase(
                "Rs." + String.format("%.2f", currentInvoice.getGrandTotal()), grandFont));
            gtValue.setBorderColor(lightGray);
            gtValue.setPadding(12);
            gtValue.setBackgroundColor(new BaseColor(255, 200, 30, 60));
            billingTable.addCell(gtValue);

            document.add(billingTable);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            // Footer
            Paragraph footer = new Paragraph("Thank you for choosing Hotel Booking System!", footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            Paragraph generated = new Paragraph(
                "Generated on: " + java.time.LocalDate.now().toString(), footerFont);
            generated.setAlignment(Element.ALIGN_CENTER);
            document.add(generated);

            document.close();

            // Show success alert
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("PDF Saved");
            alert.setHeaderText(null);
            alert.setContentText("Invoice saved to:\n" + file.getAbsolutePath());
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to generate PDF: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleClose() {
        ((Stage) invoiceIdLabel.getScene().getWindow()).close();
    }
}