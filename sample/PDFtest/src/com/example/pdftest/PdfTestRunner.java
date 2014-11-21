package com.example.pdftest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.SortedMap;
import java.util.TreeMap;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Simple test class which runs all listed examples by calling their main method
 * via reflection
 * 
 * @author markus
 * 
 */
public class PdfTestRunner extends Activity implements OnClickListener {

    private SortedMap<String, String> resultingPdfs = new TreeMap<String, String>();

    private WebView about;
    private WebView results;

    private static PdfTestRunner activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        resultingPdfs.put("com.lowagie.examples.directcontent.Layers",
                "layers.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.TemplateImages",
                "templateImages.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.Templates",
                "templates.pdf");
        /*resultingPdfs.put("com.lowagie.examples.directcontent.colors.Groups",
                "groups.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.colors.Patterns",
                "patterns.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.colors.Shading",
                "shading.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.directcontent.colors.ShadingPattern",
                "shading_pattern.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.colors.SoftMask",
                "softmask.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.directcontent.colors.SpotColors",
                "spotcolor.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.directcontent.colors.Transparency",
                "transparency.pdf");
        resultingPdfs
                .put("com.lowagie.examples.directcontent.coordinates.AffineTransformation",
                        "affinetransformation.pdf");
        resultingPdfs
                .put("com.lowagie.examples.directcontent.coordinates.TransformImage",
                        "transformimage.pdf");
        resultingPdfs
                .put("com.lowagie.examples.directcontent.coordinates.Transformations",
                        "transformations.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.directcontent.coordinates.UpsideDown",
                "upsideDown.pdf");
        resultingPdfs
                .put("com.lowagie.examples.directcontent.coordinates.XandYcoordinates",
                        "XandY.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.directcontent.graphics.Circles",
                "circles.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.graphics.GState",
                "gstate.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.directcontent.graphics.Literal",
                "literal.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.graphics.Shapes",
                "shapes.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.directcontent.optionalcontent.Automatic",
                "automatic.pdf");
        resultingPdfs
                .put("com.lowagie.examples.directcontent.optionalcontent.ContentGroups",
                        "contentgroups.pdf");
        resultingPdfs
                .put("com.lowagie.examples.directcontent.optionalcontent.NestedLayers",
                        "nestedlayers.pdf");
        resultingPdfs
                .put("com.lowagie.examples.directcontent.optionalcontent.OptionalContent",
                        "optionalcontent.pdf");
        resultingPdfs
                .put("com.lowagie.examples.directcontent.optionalcontent.OrderedLayers",
                        "orderedlayers.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.directcontent.pageevents.Bookmarks",
                "bookmarks.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.directcontent.pageevents.EndPage",
                "endpage.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.directcontent.pageevents.Events",
                "RomeoJuliet.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.text.Logo",
                "logo.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.text.Text",
                "text.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.EncodingFont",
                "encodingfont.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.FontEncoding",
                "fontencoding.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.FontFactoryType1Fonts",
                "FontFactoryType1Fonts.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.StandardType1Fonts",
                "StandardType1Fonts.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.UnicodeExample",
                "unicode.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.fonts.getting.ChineseJapaneseKorean",
                "cjk.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.getting.OpenTypeFont",
                "opentypefont.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.getting.TrueType",
                "truetype_getting.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.fonts.getting.UsingFontFactory",
                "FontFactory.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.styles.ComplexText",
                "complextext.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.styles.ExtraStyles",
                "ExtraStyles.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.styles.FixedFontWidth",
                "fixedfontwidth.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.styles.FontColor",
                "FontColor.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.fonts.styles.FontStylePropagation",
                "FontStylePropagation.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.styles.RightToLeft",
                "righttoleft.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.styles.Vertical",
                "vertical.pdf");
        resultingPdfs.put("com.lowagie.examples.fonts.styles.WidthHeight",
                "widthheight.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.FormCheckbox",
                "checkbox.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.FormCombo", "combo.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.FormList", "list.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.FormPushButton",
                "pushbutton.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.FormRadioButton",
                "radiobutton.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.FormSignature",
                "signature.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.FormTextField",
                "textfield.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.ListFields", null);
        resultingPdfs.put("com.lowagie.examples.forms.SimpleRegistrationForm",
                "SimpleRegistrationForm.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.TextFields",
                "TextFields.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.create.StudentCard",
                "studentcard.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.create.StudentCardForm",
                "studentcardform.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.fill.FdfExample",
                "registered_fdf.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.fill.Register",
                "registered_flat.pdf");
        resultingPdfs.put("com.lowagie.examples.forms.fill.XfdfExample",
                "registered_xfdf.pdf");
        resultingPdfs.put("com.lowagie.examples.general.CustomPageSize",
                "CustomPageSize.pdf");
        resultingPdfs.put("com.lowagie.examples.general.DefaultPageSize",
                "DefaultPageSize.pdf");
        resultingPdfs.put("com.lowagie.examples.general.HelloEncrypted",
                "HelloEncrypted.pdf");
        resultingPdfs.put("com.lowagie.examples.general.HelloSystemOut", null);
        resultingPdfs.put("com.lowagie.examples.general.HelloWorld",
                "HelloWorld.pdf");
        resultingPdfs.put("com.lowagie.examples.general.HelloWorldMeta",
                "HelloWorldMeta.pdf");
        resultingPdfs.put("com.lowagie.examples.general.HelloWorldMultiple",
                "HelloWorldPdf.pdf");
        resultingPdfs.put("com.lowagie.examples.general.LandscapePortrait",
                "LandscapePortrait.pdf");
        resultingPdfs
                .put("com.lowagie.examples.general.Margins", "Margins.pdf");
        resultingPdfs
                .put("com.lowagie.examples.general.copystamp.AddWatermarkPageNumbers",
                        "watermark_pagenumbers.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.general.copystamp.ConcatenateForms",
                "concatenatedforms.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.general.copystamp.EncryptorExample",
                "encrypted.pdf");
        resultingPdfs.put("com.lowagie.examples.general.copystamp.TwoOnOne",
                "2on1.pdf");
        resultingPdfs.put("com.lowagie.examples.general.faq.Measurements",
                "Measurements.pdf");
        resultingPdfs.put("com.lowagie.examples.general.faq.NewPage",
                "NewPage.pdf");
        resultingPdfs.put("com.lowagie.examples.general.faq.PdfVersion",
                "pdfversion.pdf");
        resultingPdfs.put("com.lowagie.examples.general.faq.iTextVersion",
                "version.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.Chunks", "Chunks.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.DifferentFonts",
                "differentfonts.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.FancyLists",
                "fancylists.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.FontSelection",
                "fontselection.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.Lists", "lists.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.NegativeLeading",
                "NegativeLeading.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.ParagraphAttributes",
                "ParagraphAttributes.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.Paragraphs",
                "Paragraphs.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.Phrases", "Phrases.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.SpaceWordRatio",
                "spacewordratio.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.SymbolSubstitution",
                "SymbolSubstitution.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.anchors.AHref",
                "AHref.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.anchors.Actions",
                "Actions.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.anchors.Annotations",
                "Annotations.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.anchors.ChainedActions",
                "ChainedActions.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.anchors.LocalDestination",
                "LocalDestination.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.anchors.LocalGoto",
                "LocalGoto.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.anchors.NamedActions",
                "NamedActions.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.anchors.OpenApplication",
                "OpenApplication.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.anchors.RemoteGoto",
                "DocumentA.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.anchors.SimpleAnnotations",
                "SimpleAnnotations1.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.bookmarks.ChapterSection",
                "ChapterSection.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.bookmarks.Destinations",
                "Destinations.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.bookmarks.OutlineActions",
                "OutlineActions.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.bookmarks.PageLabels",
                "PageLabels.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.bookmarks.ViewerPreferences",
                "TwoColumnLeft.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.Background",
                "Background.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.ChunkColor",
                "ChunkColor.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.EndOfLine",
                "EndOfLine.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.Generic",
                "Generic.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.Glossary",
                "Glossary.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.Hyphenation",
                "Hyphenation.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.Lines",
                "Lines.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.Rendering",
                "Rendering.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.chunk.Skew", "Skew.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.SplitChar",
                "SplitChar.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.SubSupScript",
                "SubSupScript.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.chunk.Width",
                "Width.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.columns.Column",
                "column.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.columns.ColumnIrregular",
                "columnirregular.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.columns.ColumnObjects",
                "columnobjects.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.columns.ColumnSimple",
                "columnsimple.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.columns.MultiColumnIrregular",
                "multicolumnirregular.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.columns.MultiColumnR2L",
                "multicolumnR2L.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.columns.MultiColumnSimple",
                "multicolumnsimple.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.images.AbsolutePositions",
                "absolutepositions.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.Alignment",
                "alignment-images.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.AnnotatedImage",
                "annotated_images.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.DvdCover",
                "dvdcover.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.ImageChunks",
                "imageChunks.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.ImageMasks",
                "maskedImages.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.ImageSequence",
                "inSequence.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.Images",
                "Images.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.RawData",
                "rawdata.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.Rotating",
                "rotating.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.Scaling",
                "scaling.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.tiff.Barcodes",
                "barcodes.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.images.tiff.ExampleEAN128",
                "ean128.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.images.tiff.ExamplePDF417",
                "pdf417.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.images.tiff.OddEven",
                null);
        resultingPdfs.put("com.lowagie.examples.objects.images.tiff.Tiff2Pdf",
                "tif_12.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.tables.AddBigTable",
                "AddBigTable.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.tables.CellAlignment",
                "Alignment.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.tables.CellColors",
                "CellColors.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.tables.CellHeights",
                "CellHeights.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.CellPaddingLeading",
                "PaddingLeading.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.tables.CellWidths",
                "CellWidths.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.tables.DefaultCell",
                "DefaultCell.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.tables.ImageCell",
                "ImageCell.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.tables.SplitRows",
                "SplitRowsBetween.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.tables.TableBorders",
                "TableBorders.pdf");
        resultingPdfs.put("com.lowagie.examples.objects.tables.TableSpacing",
                "TableSpacing.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.TableWidthAlignment",
                "TableWidthAlignment.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.alternatives.LargeCell",
                "largecell.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.tables.alternatives.MyFirstTable",
                        "MyFirstTable.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.tables.alternatives.NestedTables",
                        "nestedtables.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.alternatives.OldTable",
                "oldtable.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.tables.alternatives.PaddingBorders",
                        "paddingborders.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.tables.alternatives.RepeatingTable",
                        "repeatingtable.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.tables.alternatives.SpecificCells",
                        "specificcells.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.tables.alternatives.TablePdfPTable",
                        "tableattributes.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.tables.alternatives.TableWithImage",
                        "imageTable.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.pdfptable.CellEvents",
                "CellEvents.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.pdfptable.Tables",
                "tables.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.pdfptable.FloatingBoxes",
                "FloatingBoxes.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.pdfptable.FragmentTable",
                "FragmentTable.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.pdfptable.SplitTable",
                "SplitTable.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.pdfptable.TableEvents1",
                "TableEvents1.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.pdfptable.TableEvents2",
                "TableEvents2.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.tables.pdfptable.VerticalTextInCells",
                        "VerticalText.pdf");
        resultingPdfs
                .put("com.lowagie.examples.objects.tables.pdfptable.WriteSelectedRows",
                        "WriteSelectedRows.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.colors.Pattern",
                "pattern.pdf");
        resultingPdfs.put("com.lowagie.examples.directcontent.graphics.State",
                "state.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.pdfptable.SplitTable",
                "SplitTable.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.pdfptable.SplitTable",
                "SplitTable.pdf");
        resultingPdfs.put(
                "com.lowagie.examples.objects.tables.pdfptable.SplitTable",
                "SplitTable.pdf");*/

        results = (WebView) findViewById(R.id.results);
        results.getSettings().setJavaScriptEnabled(true);
        results.addJavascriptInterface(this, "android");
        results.setHorizontalScrollBarEnabled(false);
        results.loadUrl("file:///android_asset/results.html");

        about = (WebView) findViewById(R.id.about);
        about.getSettings().setJavaScriptEnabled(true);
        about.addJavascriptInterface(this, "android");
        about.setHorizontalScrollBarEnabled(false);
        about.loadUrl("file:///android_asset/about.html");

        activity = this;
    }

    private void textAppend(final String string) {
        runOnUiThread(new Runnable() {

            public void run() {
                results.loadUrl("javascript:appendResult('"
                        + Base64.encodeToString(string.getBytes(),
                                Base64.NO_WRAP) + "')");
            }
        });
    }
    

    public void onClick(View v) {
        // TODO Auto-generated method stub
     // Create droidtext directory for storing results
        File file = new File(
                android.os.Environment.getExternalStorageDirectory()
                        + File.separator + "droidtext");
        if (!file.exists()) {
            file.mkdir();
        }

        Thread t = new Thread() {

            public void run() {
                int success = 0;
                int count = 1;
                for (final String className : resultingPdfs.keySet()) {
                    String displayName = className
                            .substring("com.lowagie.examples.".length());
                    textAppend("Trying to run example " + (count++) + " of "
                            + resultingPdfs.size() + " " + displayName
                            + "<br/>");

                    String result = null;
                    try {
                        // Set output streams to bytearray streams so we can
                        // display the output of examples
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        PrintStream errorStream = new PrintStream(bos, true);
                        System.setErr(errorStream);

                        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
                        PrintStream outStream = new PrintStream(bos2, true);
                        System.setOut(outStream);

                        // Find the main method
                        Class<?> c = Class.forName(className);
                        Method main = c.getDeclaredMethod("main",
                                String[].class);

                        // Emulate CLI parameters if necessary
                        String[] params = null;
                        if (className
                                .equals("com.lowagie.examples.objects.tables.pdfptable.FragmentTable")) {
                            params = new String[] { "3" };
                        } else if (className
                                .equals("com.lowagie.examples.objects.images.tiff.OddEven")) {
                            params = new String[] { "odd.tif", "even.tif",
                                    "odd_even.tiff" };
                        } else if (className
                                .equals("com.lowagie.examples.objects.images.tiff.Tiff2Pdf")) {
                            params = new String[] { "tif_12.tif" };
                        } else if (className
                                .equals("com.lowagie.examples.objects.images.DvdCover")) {
                            params = new String[] { "dvdcover.pdf", "Title",
                                    "0xff0000", "hitchcock.png" };
                        } else if (className
                                .equals("com.lowagie.examples.forms.ListFields")) {
                            params = new String[] {};
                        } else if (className
                                .equals("com.lowagie.examples.general.read.Info")) {
                            params = new String[] { "RomeoJuliet.pdf" };
                        } else if (className
                                .equals("com.lowagie.examples.objects.anchors.OpenApplication")) {
                            params = new String[] { "" };
                        }

                        main.invoke(null, (Object) params);

                        // Parse results
                        String string = new String(bos.toByteArray());
                        String string2 = new String(bos2.toByteArray());
                        if (string.length() > 0) {
                            result = "Failed: " + string;
                        } else if (string2.contains("Exception")) {
                            result = "Failed: " + string2;
                        } else if (resultingPdfs.get(className) != null) {
                            File pdf = new File(
                                    Environment.getExternalStorageDirectory()
                                            + File.separator + "droidtext"
                                            + File.separator
                                            + resultingPdfs.get(className));
                            if (!pdf.exists()) {
                                result = "Failed: Resulting pdf didn't get created";
                            } else if (pdf.length() <= 0) {
                                result = "Failed: Resulting pdf is empty";
                            } else {
                                success++;
                                result = "Successful";
                            }
                        } else {
                            success++;
                            result = "Successful";
                        }
                    } catch (Exception e) {
                        result = "Failed with exception: "
                                + e.getClass().getName() + ": "
                                + e.getMessage();
                    }
                    if (result.startsWith("Failed")) {
                        String string = "<p style=\"margin-left: 20px\"><font color=\"red\">Result: "
                                + result + "</font></p>";
                        textAppend(string);
                    } else {
                        String open = resultingPdfs.get(className) != null ? "<a href=\"#\" onclick=\"android.openPdf('"
                                + className + "')\">Open Pdf</a></p>"
                                : "";
                        textAppend("<p style=\"margin-left: 20px\"><font color=\"green\">Result: "
                                + result + "</font><br/>" + open);
                    }
                }
                textAppend("<p><b>" + success + "/" + resultingPdfs.size()
                        + " successful</b></p>");
            }

        };
        t.start();
    }

    /**
     * Static helper methods so that examples can use the context of the
     * activity to access resources
     * 
     * @return
     */
    public static PdfTestRunner getActivity() {
        return activity;
    }

    /**
     * Open URL in Browser
     * 
     * @param url
     */
    public void openURL(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(i);
    }

    /**
     * Open pdf in available viewer
     * 
     * @param url
     */
    public void openPdf(String className) {
        String path = resultingPdfs.get(className);
        if (path != null) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory()
                    + File.separator
                    + "droidtext" + File.separator + path)), "application/pdf");
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try {
                startActivity(i);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No Application Available to View PDF",
                        Toast.LENGTH_SHORT).show();
            }
            startActivity(i);
        } else {
            Toast.makeText(this, "No resulting pdf for example " + className,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
