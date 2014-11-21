/*
 * $Id: AddWatermarkPageNumbers.java 3373 2008-05-12 16:21:24Z xlv $
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
package com.lowagie.examples.general.copystamp;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pdftest.PdfTestRunner;
import com.example.pdftest.R;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * Reads the pages of an existing PDF file, adds pagenumbers and a watermark.
 */
public class AddWatermarkPageNumbers {
	/**
	 * Reads the pages of an existing PDF file, adds pagenumbers and a
	 * watermark.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {
		System.out.println("Add watermarks and pagenumbers");
		try {
			// we create a reader for a certain document
			//Can't use filename directly
			//PdfReader reader = new PdfReader("chaptersection.pdf");
			PdfReader reader = new PdfReader(PdfTestRunner.getActivity().getResources().openRawResource(R.raw.chaptersection));
			int n = reader.getNumberOfPages();
			// we create a stamper that will copy the document to a new file
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "watermark_pagenumbers.pdf"));
			// adding some metadata
			HashMap moreInfo = new HashMap();
			moreInfo.put("Author", "Bruno Lowagie");
			stamp.setMoreInfo(moreInfo);
			// adding content to each page
			int i = 0;
			PdfContentByte under;
			PdfContentByte over;
			//Can't use filename => use byte[] instead
//			Image img = Image.getInstance("watermark.jpg");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.watermark);
			bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */,
			                        100 /* Ratio */, stream);
			Image img = Image.getInstance(stream.toByteArray());
			
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
			img.setAbsolutePosition(200, 400);
			while (i < n) {
				i++;
				// watermark under the existing page
				under = stamp.getUnderContent(i);
				under.addImage(img);
				// text over the existing page
				over = stamp.getOverContent(i);
				over.beginText();
				over.setFontAndSize(bf, 18);
				over.setTextMatrix(30, 30);
				over.showText("page " + i);
				over.setFontAndSize(bf, 32);
				over.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE", 230, 430, 45);
				over.endText();
			}
			// adding an extra page
			stamp.insertPage(1, PageSize.A4);
			over = stamp.getOverContent(1);
			over.beginText();
			over.setFontAndSize(bf, 18);
			over.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE OF AN EXISTING PDF DOCUMENT", 30, 600, 0);
			over.endText();
			// adding a page from another document
			//Can't use filename directly
//			PdfReader reader2 = new PdfReader("SimpleAnnotations1.pdf");
			PdfReader reader2 = new PdfReader(PdfTestRunner.getActivity().getResources().openRawResource(R.raw.simpleannotations1));
			under = stamp.getUnderContent(1);
			under.addTemplate(stamp.getImportedPage(reader2, 3), 1, 0, 0, 1, 0, 0);
			// closing PdfStamper will generate the new PDF file
			stamp.close();
		} catch (Exception de) {
			de.printStackTrace();
		}
	}
}