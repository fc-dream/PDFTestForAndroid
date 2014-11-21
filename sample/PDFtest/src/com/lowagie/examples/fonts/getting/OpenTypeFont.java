/*
 * $Id: OpenTypeFont.java 3373 2008-05-12 16:21:24Z xlv $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://itextdocs.lowagie.com/tutorial/
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */
package com.lowagie.examples.fonts.getting;

import java.io.FileOutputStream;
import java.io.InputStream;

import com.example.pdftest.PdfTestRunner;
import com.example.pdftest.R;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Using an Open Type Font (CFF only).
 */
public class OpenTypeFont {
	/**
	 * Using oth
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {
		// step 1
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			// step 2
			PdfWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "opentypefont.pdf"));
			// step 3
			document.open();
			// step 4
			//Don't use path read ttf into byte[] instead
//			BaseFont bf = BaseFont.createFont("liz.otf", BaseFont.CP1252, true);
			InputStream inputStream = PdfTestRunner.getActivity().getResources().openRawResource(R.raw.liz);
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			BaseFont bf = BaseFont.createFont("freesans.ttf", BaseFont.CP1252, true, false, buffer, null);
			String text = "Some text with the otf font LIZ.";
			document.add(new Paragraph(text, new Font(bf, 14)));
		} catch (Exception de) {
			de.printStackTrace();
		}
		// step 5
		document.close();
	}
}
