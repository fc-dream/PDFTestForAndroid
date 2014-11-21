package com.example;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	
	private Button b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b= (Button)findViewById(R.id.button1);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createPDF();

			}
		});
		
	}

	
	public void createPDF()
	{
	 	Document doc = new Document();
        
		
		 try {
			 	String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/droidText";
			 	 
			 	File dir = new File(path);
			        if(!dir.exists())
			        	dir.mkdirs();

			    Log.d("PDFCreator", "PDF Path: " + path);
			    
			        
			    File file = new File(dir, "sample.pdf");
			    FileOutputStream fOut = new FileOutputStream(file);
     
        	 	PdfWriter.getInstance(doc, fOut);
                 
                //open the document
                doc.open();
                
                
                Paragraph p1 = new Paragraph("Hi! I am generating my first PDF using DroidText");
                Font paraFont= new Font(Font.COURIER);
                p1.setAlignment(Paragraph.ALIGN_CENTER);
                p1.setFont(paraFont);
                
                 //add paragraph to document    
                 doc.add(p1);
                
                 Paragraph p2 = new Paragraph("This is an example of a simple paragraph");
                 Font paraFont2= new Font(Font.COURIER,14.0f,Color.GREEN);
                 p2.setAlignment(Paragraph.ALIGN_CENTER);
                 p2.setFont(paraFont2);
                 
                 doc.add(p2);
                 
                 ByteArrayOutputStream stream = new ByteArrayOutputStream();
                 Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.android);
                 bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
                 Image myImg = Image.getInstance(stream.toByteArray());
                 myImg.setAlignment(Image.MIDDLE);
                
                 //add image to document
                 doc.add(myImg);
                
                 //set footer
                 Phrase footerText = new Phrase("This is an example of a footer");
                 HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
                 doc.setFooter(pdfFooter);
                

                
         } catch (DocumentException de) {
                 Log.e("PDFCreator", "DocumentException:" + de);
         } catch (IOException e) {
                 Log.e("PDFCreator", "ioException:" + e);
         } 
		 finally
         {
                 doc.close();
         }
       
	}      
}


