package ua.nure.petrov.SummaryTask4.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.db.entity.Tariff;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Class that create pdf document from the table of tariffs
 * 
 * @author Petrov Vladyslav
 * 
 */
public class PdfCreator {

	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);

	private static final Logger LOG = Logger.getLogger(PdfCreator.class);

	public static void createPDF(List<Tariff> tariff, String path) {

		try {
			Document document = new Document();

			FileOutputStream fos = new FileOutputStream(path);

			PdfWriter.getInstance(document, fos);

			document.open();

			Paragraph paragraph = new Paragraph("Inforation about tariffs",
					titleFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(3);

			PdfPCell cell = null;

			cell = new PdfPCell(new Phrase("Name"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Description"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Price"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			table.setHeaderRows(1);

			for (Tariff t : tariff) {
				table.addCell(t.getName());
				table.addCell(t.getDescription());
				table.addCell(String.valueOf(t.getPrice()));
			}

			LOG.error("Document has been created");

			document.add(table);
			document.close();

		} catch (FileNotFoundException | DocumentException e) {
			LOG.error("Error --> " + e.getMessage());
		}
	}
}