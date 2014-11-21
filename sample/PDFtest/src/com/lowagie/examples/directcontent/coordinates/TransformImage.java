/*
 * $Id: TransformImage.java 3838 2009-04-07 18:34:15Z mstorer $
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
package com.lowagie.examples.directcontent.coordinates;

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
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Add an image using different transformation matrices.
 */
public class TransformImage {

	/**
	 * Add an image using different transformation matrices.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {
		Document.compress = false;
		System.out.println("Transformating an Image");
		// step 1: creation of a document-object
		Document document = new Document(PageSize.A4);

		try {
			// step 2: creation of the writer
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "transformimage.pdf"));

			// step 3: we open the document
			document.open();

			// step 4:
			PdfContentByte cb = writer.getDirectContent();
			//Can't use filename => use byte[] instead
//			Image img = Image.getInstance("hitchcock.png");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.hitchcock);
			bitmap.compress(Bitmap.CompressFormat.PNG /* FileType */,
			                        100 /* Ratio */, stream);
			Image img = Image.getInstance(stream.toByteArray());
			cb.addImage(img, 271, -50, -30, 550, 100, 100);
			cb.sanityCheck();

		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

		// step 5: we close the document
		document.close();
	}
}
