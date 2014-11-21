/*
 * $Id: AnnotatedImage.java 3373 2008-05-12 16:21:24Z xlv $
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
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pdftest.PdfTestRunner;
import com.example.pdftest.R;
import com.lowagie.text.Annotation;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Images with an annotation.
 */
public class AnnotatedImage {
	/**
	 * Adds some annotated images to a PDF file.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {

		System.out.println("images and annotations");

		// step 1: creation of a document-object
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			// step 2:
			// we create a writer that listens to the document
			PdfWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "annotated_images.pdf"));
			// step 3: we open the document
			document.open();
			// step 4: we add some content
			//Can't use filename => use byte[] instead
//			Image jpeg = Image.getInstance("otsoe.jpg");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeResource(PdfTestRunner.getActivity().getResources(), R.drawable.otsoe);
			bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */,
			                        100 /* Ratio */, stream);
			Image jpeg = Image.getInstance(stream.toByteArray());
			jpeg.setAnnotation(new Annotation("picture", "This is my dog", 0, 0, 0, 0));
			jpeg.setAbsolutePosition(100f, 550f);
			document.add(jpeg);
			//Can't use filename => use byte[] instead
//			Image wmf = Image.getInstance("iText.wmf");
			InputStream inputStream = PdfTestRunner.getActivity().getResources().openRawResource(R.raw.itext_wmf);
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			Image wmf = Image.getInstance(buffer);
			wmf.setAnnotation(new Annotation(0, 0, 0, 0, "http://www.lowagie.com/iText"));
			wmf.setAbsolutePosition(100f, 200f);
			document.add(wmf);
		} catch (Exception de) {
			de.printStackTrace();
		}

		// step 5: we close the document
		document.close();
	}

}
