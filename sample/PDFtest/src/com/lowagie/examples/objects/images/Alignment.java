/*
 * $Id: Alignment.java 3373 2008-05-12 16:21:24Z xlv $
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
import com.lowagie.text.pdf.PdfWriter;

/**
 * Demonstrates the alignment method and parameters.
 */
public class Alignment {
	/**
	 * Demonstrates the alignment method.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {
		System.out.println("Alignment of images");
		// step 1: creation of a document-object
		Document document = new Document();
		try {
			// step 2: creation of a writer
			PdfWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "alignment-images.pdf"));

			// step 3: we open the document
			document.open();

			//Can't use filename => use byte[] instead
//			Image gif = Image.getInstance("vonnegut.gif");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.vonnegut);
			bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */,
			                        100 /* Ratio */, stream);
			Image gif = Image.getInstance(stream.toByteArray());
			gif.setAlignment(Image.RIGHT);
			
			//Can't use filename => use byte[] instead
//			Image jpeg = Image.getInstance("otsoe.jpg");
			stream = new ByteArrayOutputStream();
			bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.otsoe);
			bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */,
			                        100 /* Ratio */, stream);
			Image jpeg = Image.getInstance(stream.toByteArray());
			jpeg.setAlignment(Image.MIDDLE);
			
			//Can't use filename => use byte[] instead
//			Image png = Image.getInstance("hitchcock.png");
			stream = new ByteArrayOutputStream();
			bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.hitchcock);
			bitmap.compress(Bitmap.CompressFormat.PNG /* FileType */,
			                        100 /* Ratio */, stream);
			Image png = Image.getInstance(stream.toByteArray());
			png.setAlignment(Image.LEFT);

			document.add(gif);
			document.add(jpeg);
			document.add(png);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		// step 5: we close the document
		document.close();
	}
}
