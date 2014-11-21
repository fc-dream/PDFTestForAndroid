/*
 * $Id: ImageSequence.java 3373 2008-05-12 16:21:24Z xlv $
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
 * Generates 2 documents: one that respects the order of Images added, another
 * that has the default behaviour: only show the images if they fit on the page,
 * if they don't fit, wait until the next page.
 */
public class ImageSequence {
	/**
	 * Generates 2 documents: one that respects the order of Images added,
	 * another that has the default behaviour: only show the images if they fit
	 * on the page, if they don't fit, wait until the next page.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {

		System.out.println("ImageSequence");

		// step 1: creation of a document-object
		Document document = new Document();

		try {
			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "notInSequence.pdf"));
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "inSequence.pdf"));
			writer.setStrictImageSequence(true);

			// step 3: we open the document
			document.open();

			// step 4:
			document.add(new Paragraph("1st image"));
			//Can't use filename => use byte[] instead
//			Image jpg = Image.getInstance("otsoe.jpg");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.otsoe);
			bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */,
			                        100 /* Ratio */, stream);
			Image jpg = Image.getInstance(stream.toByteArray());
			document.add(jpg);
			document.add(new Paragraph("2nd image"));
			//Can't use filename => use byte[] instead
//			Image gif = Image.getInstance("getacro.gif");
			stream = new ByteArrayOutputStream();
			bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.getacro);
			bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */,
			                        100 /* Ratio */, stream);
			Image gif = Image.getInstance(stream.toByteArray());
			document.add(gif);
			document.add(new Paragraph("3rd image"));
			document.add(jpg);
			document.add(new Paragraph("4th image"));
			document.add(gif);
			document.add(new Paragraph("5th image"));
			document.add(jpg);
			document.add(new Paragraph("6th image"));
			document.add(gif);
			document.add(new Paragraph("7th image"));
			document.add(jpg);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

		// step 5: we close the document
		document.close();
	}
}
