package org.duang.test;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class PdfCreating {

	public void init() throws Exception{
		//①建立com.lowagie.text.Document对象的实例。
		Document document = new Document(PageSize.A4, 0, 0, 0, 0); 

		//②建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		PdfWriter.getInstance(document, new FileOutputStream("Helloworld.PDF")); 

		//③打开文档。
		document.open(); 

		//④向文档中添加内容。
		document.add(new Paragraph("Hello World")); 

		//⑤关闭文档。
		document.close(); 
	}

	public void init2() throws Exception{
		//①建立com.lowagie.text.Document对象的实例。
		Document document = new Document(PageSize.A4, 0, 0, 0, 0); 
		/* public boolean addTitle(String title)
		public boolean addSubject(String subject)
		public boolean addKeywords(String keywords)
		public boolean addAuthor(String author)
		public boolean addCreator(String creator)
		public boolean addProducer()
		public boolean addCreationDate()
		public boolean addHeader(String name, String content) */

		//②建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		PdfWriter.getInstance(document, new FileOutputStream("Helloworld.PDF")); 

		//③打开文档。
		document.open(); 

		//④向文档中添加内容。
		document.add(new Paragraph("Hello World")); 

		//⑤关闭文档。
		document.close(); 
	}

	public static void main(String[] args) throws Exception{
		PdfCreating obj = new PdfCreating();
		obj.init();
		System.out.println("完成了");
	}

}
