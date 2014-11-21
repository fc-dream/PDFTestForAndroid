/*
 * $Id: Images.java 3373 2008-05-12 16:21:24Z xlv $
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
package com.lowagie.examples.objects.images;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pdftest.PdfTestRunner;
import com.example.pdftest.R;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * General Images example.
 */
public class Images {

	/**
	 * General Images example
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {

		System.out.println("Images");

		// step 1: creation of a document-object
		Document document = new Document();

		try {
			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "Images.pdf"));

			// step 3: we open the document
			document.open();

			// step 4:
			document.add(new Paragraph("A picture of my dog: otsoe.jpg"));
			
			//Can't use filename => use byte[] instead
//			Image jpg = Image.getInstance("otsoe.jpg");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.otsoe);
			bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */,
			                        100 /* Ratio */, stream);
			Image jpg = Image.getInstance(stream.toByteArray());
			
			document.add(jpg);
			document.add(new Paragraph("getacro.gif"));
			
			//Can't use filename => use byte[] instead
//			Image gif = Image.getInstance("getacro.gif");
			stream = new ByteArrayOutputStream();
			bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.getacro);
			bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */,
			                        100 /* Ratio */, stream);
			Image gif = Image.getInstance(stream.toByteArray());
			
			document.add(gif);
			document.add(new Paragraph("pngnow.png"));
			
			//Can't use filename => use byte[] instead
//			Image png = Image.getInstance("pngnow.png");
			stream = new ByteArrayOutputStream();
			bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.pngnow);
			bitmap.compress(Bitmap.CompressFormat.PNG /* FileType */,
			                        100 /* Ratio */, stream);
			Image png = Image.getInstance(stream.toByteArray());
			
			document.add(png);
			document.add(new Paragraph("iText.bmp"));
			
			//Can't use filename => use byte[] instead
//			Image bmp = Image.getInstance("iText.bmp");
			stream = new ByteArrayOutputStream();
			bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.itext_bmp);
			bitmap.compress(Bitmap.CompressFormat.PNG /* FileType */,
			                        100 /* Ratio */, stream);
			Image bmp = Image.getInstance(stream.toByteArray());
			
			document.add(bmp);
			document.add(new Paragraph("iText.wmf"));
			
			//Can't use filename => use byte[] instead
//			Image wmf = Image.getInstance("iText.wmf");
			InputStream inputStream = PdfTestRunner.getActivity().getResources().openRawResource(R.raw.itext_wmf);
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			Image wmf = Image.getInstance(buffer);
			
			document.add(wmf);
			document.add(new Paragraph("iText.tif"));
			
			//Can't use filename => use byte[] instead
//			Image tiff = Image.getInstance("iText.tif");
			inputStream = PdfTestRunner.getActivity().getResources().openRawResource(R.raw.itext_tif);
			buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			Image tiff = Image.getInstance(buffer);
			
			document.add(tiff);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

		// step 5: we close the document
		document.close();
	}
}