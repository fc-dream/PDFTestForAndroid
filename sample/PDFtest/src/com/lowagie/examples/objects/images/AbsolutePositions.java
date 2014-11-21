/*
 * $Id: AbsolutePositions.java 3373 2008-05-12 16:21:24Z xlv $
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
 * Add the image at an absolute position.
 */
public class AbsolutePositions {
	/**
	 * Adds an Image at an absolute position.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {

		System.out.println("Absolute Positioning of an Image");

		// step 1: creation of a document-object
		Document document = new Document();

		try {

			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file

			PdfWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "absolutepositions.pdf"));

			// step 3: we open the document
			document.open();

			// step 4: we add content
			//Can't use filename => use byte[] instead
//			Image img = Image.getInstance("hitchcock.png");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.hitchcock);
			bitmap.compress(Bitmap.CompressFormat.PNG /* FileType */,
			                        100 /* Ratio */, stream);
			Image png = Image.getInstance(stream.toByteArray());
			png.setAbsolutePosition(171, 250);
			document.add(png);
			png.setAbsolutePosition(342, 500);
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
