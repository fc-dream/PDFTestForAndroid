/*
 * $Id: XfdfExample.java 3373 2008-05-12 16:21:24Z xlv $
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
package com.lowagie.examples.forms.fill;

import java.io.FileOutputStream;
import java.io.InputStream;

import com.example.pdftest.PdfTestRunner;
import com.example.pdftest.R;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.XfdfReader;

/**
 * How to merge an XFDF file with a PDF form.
 */
public class XfdfExample {
	/**
	 * Merges an XFDF file with a PDF form.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {
		try {
			// merging the FDF file
			PdfReader pdfreader = new PdfReader(PdfTestRunner.getActivity().getResources().openRawResource(R.raw.simpleregistrationform));
			String path = android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "registered_xfdf.pdf";
            PdfStamper stamp = new PdfStamper(pdfreader, new FileOutputStream(path));
            InputStream inputStream = PdfTestRunner.getActivity().getResources().openRawResource(R.raw.register);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
			XfdfReader fdfreader = new XfdfReader(buffer);
			AcroFields form = stamp.getAcroFields();
			form.setFields(fdfreader);
			stamp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
