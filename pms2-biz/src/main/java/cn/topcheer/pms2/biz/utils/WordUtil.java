package cn.topcheer.pms2.biz.utils;

import cn.hutool.core.io.FileUtil;
import cn.topcheer.common.wordanalyze.replaceprocess.ReplaceProcess;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.pdf.PdfReader;
import com.spire.doc.FileFormat;
import com.spire.doc.Table;
import com.spire.doc.documents.TextSelection;
import com.spire.doc.documents.TextWrappingStyle;
import com.spire.doc.documents.TextWrappingType;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextRange;
import freemarker.core.ParseException;
import freemarker.template.*;
import net.sf.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.*;
import java.sql.Blob;
import java.util.*;
@Slf4j
public class WordUtil {
//    private static Map<String,String> planStatusMap = ProgressBarController.planStatusMap;

	static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。
	static final int wdFormatPDF = 17;// PDF 格式

	@SuppressWarnings({ "deprecation" })
	public static Template getTemplate(String templatePath, String templateName)
			throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException {

		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		File file=new File(templatePath);
		configuration.setDirectoryForTemplateLoading(file);
//		configuration.setClassForTemplateLoading(WordUtil.class, templatePath);
//		configuration.setClassForTemplateLoading(WordUtil.class, templatePath);
		Template template = configuration.getTemplate(templateName);
		template.setEncoding("UTF-8");
		return template;
	}

	@SuppressWarnings("unused")
	public static void write(String templatePath, String templateName,
							 Map<String, Object> dataMap, Writer out)
			throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		Template t = getTemplate(templatePath, templateName);
		t.process(dataMap, out);
		out.flush();
		out.close();
	}

//	/**
//	 * 将word文件转pdf文件
//	 *
//	 * @param inputFilePath
//	 *            (输入word文件路径)
//	 * @param outFilePath
//	 *            (输出pdf文件路径)
//	 */
//	public static void wordToPdf(String inputFilePath, String outFilePath) {
//		System.out.println("启动Word...");
//		long start = System.currentTimeMillis();
//		ActiveXComponent app = null;
//		try {
//			app = new ActiveXComponent("Word.Application");
//			app.setProperty("Visible", false);
//
//			Dispatch docs = app.getProperty("Documents").toDispatch();
//			System.out.println("打开文档..." + inputFilePath);
//			File tofile = new File(outFilePath);
//			if (tofile.exists()) {
//				tofile.delete();
//			}
//
//			Dispatch doc = Dispatch.call(docs,//
//					"Open", //
//					inputFilePath,// FileName
//					false,// ConfirmConversions
//					true // ReadOnly
//			).toDispatch();
//			System.out.println("转换文档到PDF..." + outFilePath);
//			Dispatch.call(doc,//
//					"SaveAs", //
//					outFilePath, // FileName
//					wdFormatPDF);
//			Dispatch.call(doc, "Close", false);
//			long end = System.currentTimeMillis();
//			System.out.println("转换完成..用时：" + (end - start) + "ms.");
//		} catch (Exception e) {
//			System.out.println("========Error:文档转换失败：" + e.getMessage());
//			convertFailFile(inputFilePath);
//
//		} finally {
//			if (app != null)
//				app.invoke("Quit", wdDoNotSaveChanges);
//		}
//		OfficeUtilForAspnetWords.wordToPdf(inputFilePath,outFilePath);
//	}
//
	/**
	 * @param pdfFile 源PDF文件
	 * @param imgFile	图片文件
	 */
	public static void imgToPdf(File pdfFile,File imgFile)  throws Exception {
		//文件转img
		InputStream is = new FileInputStream(imgFile);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for(int i;(i=is.read())!=-1;)
		{
			baos.write(i);
		}
		baos.flush();

		//取得图像的宽和高。
		Image img = Image.getInstance(baos.toByteArray());
		float width = img.getWidth();
		float height = img.getHeight();
		img.setAbsolutePosition(0.0F, 0.0F);//取消偏移
		System.out.println("width = "+width+"\theight"+height);

		//img转pdf
		Document doc = new Document(new Rectangle(width,height));
		PdfWriter pw = PdfWriter.getInstance(doc,new FileOutputStream(pdfFile));
		doc.open();
		doc.add(img);

		//释放资源
		System.out.println(doc.newPage());
		pw.flush();
		baos.close();
		doc.close();
		pw.close();
	}
//
	/*20210607word转pdf改成新的先*/
	public static void wordToPdfNew(String inputFilePath, String outFilePath) {
		long start = System.currentTimeMillis();
		com.spire.doc.Document  document = new com.spire.doc.Document();
		document.loadFromFile(inputFilePath);
		log.warn("word转换pdf获取进入文件\r\n");
		document.saveToFile(outFilePath, FileFormat.PDF);
		log.warn("word转换pdf转换完成pdf文件\r\n");
		long end = System.currentTimeMillis();
		document.close();
		System.out.println("转换完成..用时：" + (end - start) + "ms.");
		log.warn("pdfbook转换完成..用时：" + (end - start) + "ms.");
		/*Document document = new Document();
		document("Sample.docx");
		document.saveToFile("out/toPDF.pdf", FileFormat.PDF);*/

	}
//
//	/*20210609word转pdf改成新的先*/
//	public static void wordToPdfSpire(String inputFilePath, String outFilePath) {
//		long start = System.currentTimeMillis();
//
//		log.warn("word转换pdf获取进入文件\r\n");
//		log.warn("word转换pdf转换完成pdf文件\r\n");
//		long end = System.currentTimeMillis();
//		System.out.println("转换完成..用时：" + (end - start) + "ms.");
//		log.warn("pdfbook转换完成..用时：" + (end - start) + "ms.");
//		/*Document document = new Document();
//		document("Sample.docx");
//		document.saveToFile("out/toPDF.pdf", FileFormat.PDF);*/
//
//	}
//
//
//
//
//	/**
//	 * 通过word模板生成word到指定的word文件,并且生成pdf到指定pdf文件
//	 * @param modelFilePath（word模板在项目中的路径）
//	 * @param modelName（word模板名称）
//	 * @param tmpWordFile（生成的word文件）
//	 * @param tmpPDFFile（生成的pdf文件）
//	 * @param hashmap（生成word文件所需要的map数据源）
//	 * @throws MalformedTemplateNameException
//	 * @throws ParseException
//	 * @throws IOException
//	 * @throws TemplateException
//	 */
//	public static void createPdf(String modelFilePath, String modelName,
//								 String tmpWordFile, String tmpPDFFile, Map<String, Object> hashmap)
//			throws MalformedTemplateNameException, ParseException, IOException,
//			TemplateException {
//		log.warn("pdffree----生成模板开始\r\n");
//		createWord(modelFilePath, modelName, tmpWordFile, hashmap);
//		log.warn("pdffree----生成模板结束\r\n");
//		// String modelPath = "rmkkx.xml";
//		// 将word转pdf
//		//WordUtil.wordToPdf(tmpWordFile, tmpPDFFile);
//		Word2PdfSuwellUtil.doc2pdf(tmpWordFile,tmpPDFFile);
//		log.warn("pdffree----转化pdf结束\r\n");
//	}
//
//
//
//
	/**
	 * 通过word模板生成word到指定的word文件,并且生成pdf到指定pdf文件
	 * @param modelFilePath（word模板在项目中的路径）
	 * @param modelName（word模板名称）
	 * @param tmpWordFile（生成的word文件）
	 * @param tmpPDFFile（生成的pdf文件）
	 * @param hashmap（生成word文件所需要的map数据源）
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void createPdfNewToPdf(String modelFilePath, String modelName,
								 String tmpWordFile, String tmpPDFFile, Map<String, Object> hashmap,String toPdfType)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		log.warn("pdffree----生成模板开始\r\n");
		createWord(modelFilePath, modelName, tmpWordFile, hashmap);
		log.warn("pdffree----生成模板结束\r\n");
		// String modelPath = "rmkkx.xml";
		// 将word转pdf
		wordToPdfByType(tmpWordFile, tmpPDFFile, toPdfType);
		log.warn("pdffree----转化pdf结束\r\n");
	}
//
	/**
	 * 通过word模板生成word到指定的word文件
	 * @param modelFilePath（word模板在项目中的路径）
	 * @param modelName（word模板名称）
	 * @param tmpWordFile（生成的word文件）
	 * @param hashmap（生成word文件所需要的map数据源）
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void createWord(String modelFilePath, String modelName,
								  String tmpWordFile, Map<String, Object> hashmap)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		File filepath = new File(tmpWordFile);
		// 将模板和数据模型合并生成文件
		FileOutputStream stream = new FileOutputStream(filepath);
		Writer out = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
		// 生成word文件
		// WordUtil.write("/pdfmodel", modelName, hashmap, out);
		WordUtil.write(modelFilePath, modelName, hashmap, out);
		JoinPhoto(tmpWordFile, hashmap);
	}
//
//	/**
//	 * 将JSONObject转Map
//	 * @param obj（提供数据的JSONObject对象）
//	 * @param keys（Map需要添加的key）
//	 * @param hashmap（需要添加key的Map对象）
//	 * @return
//	 */
//	public static Map<String, Object> jsonToMap(JSONObject obj, String[] keys,
//												Map<String, Object> hashmap) {
//		//如果obj对象为null，把需要添加的key的值都设置为空字符串
//		if (obj == null) {
//			for (int i = 0; i < keys.length; i++) {
//				Object value = "";
//				String key = keys[i];
//				hashmap.put(key, value);
//			}
//		} else {
//			for (int i = 0; i < keys.length; i++) {
//				Object value = "";
//				String key = keys[i];
//				//如果obj对象中没有该key，则添加的该key，并把值设置为空字符串
//				if (obj.containsKey(key)) {
//					Object value1 = obj.get(key);
//					value = value1;
//				}
//				hashmap.put(key, value);
//			}
//		}
//		return hashmap;
//	}
//	/**
//	 * 将JSONObject转Map
//	 * @param obj（提供数据的JSONObject对象）
//	 * @param keys（Map需要添加的key）
//	 * @param hashmap（需要添加key的Map对象）
//	 * 指定 String String
//	 * @return
//	 */
//	public static Map<String, String> jsonToMap(JSONObject obj, String[] keys,
//												Map<String, String> hashmap,boolean isString) {
//		//如果obj对象为null，把需要添加的key的值都设置为空字符串
//		if (obj == null) {
//			for (int i = 0; i < keys.length; i++) {
//				String value = "";
//				String key = keys[i];
//				hashmap.put(key, value);
//			}
//		} else {
//			for (int i = 0; i < keys.length; i++) {
//				String value = "";
//				String key = keys[i];
//				//如果obj对象中没有该key，则添加的该key，并把值设置为空字符串
//				if (obj.containsKey(key)) {
//					String value1 = obj.get(key)+"";
//					value = value1;
//				}
//				hashmap.put(key, value);
//			}
//		}
//		return hashmap;
//	}
	// 记录转换失败的文件 目录 默认存在D盘，errorconverts ,文件大小1.5M
	// 超过1.5M 重新建一个
//	public static void  convertFailFile(String errorFile){
//		Date d = new Date();
//		errorFile = Util.formatDate(d,"yyyy-mm-dd HH:mm:ss")+"==》"+errorFile;
//		String errorfiles = "d:\\errorconverts";
//		File ef = new File(errorfiles);
//		if(!ef.exists()){
//			ef.mkdir();
//		}else{
//			File[] listFiles = ef.listFiles();
//			if(listFiles.length>0){
//				listFiles = sortFilesByModifyDate(listFiles);
//				File crrentFile = listFiles[0];
//				if(crrentFile.length()<10*1024*1024){
//					FileWriter writer = null;
//					try {
//						writer = new FileWriter(crrentFile.getAbsoluteFile(), true);
//						writer.write(errorFile+"\r\n");
//						writer.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}else{
//					File currentFile = new File(errorfiles+"\\errorfile_"+(listFiles.length+1)+".txt");
//					FileWriter writer = null;
//					try {
//						writer = new FileWriter(currentFile.getAbsoluteFile(), true);
//						writer.write(errorFile+"\r\n");
//						writer.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}else{
//				File currentFile = new File(errorfiles+"\\errorfile_1.txt");
//				FileWriter writer = null;
//				try {
//					writer = new FileWriter(currentFile.getAbsoluteFile(), true);
//					writer.write(errorFile+"\r\n");
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

//	public static void  convertFailFile2(String errorFile){
//
//		String errorfiles = "d:\\errorconverts2";
//		File ef = new File(errorfiles);
//		if(!ef.exists()){
//			ef.mkdir();
//		}else{
//			File[] listFiles = ef.listFiles();
//			if(listFiles.length>0){
//				listFiles = sortFilesByModifyDate(listFiles);
//				File crrentFile = listFiles[0];
//				if(crrentFile.length()<100*1024*1024){
//					FileWriter writer = null;
//					try {
//						writer = new FileWriter(crrentFile.getAbsoluteFile(), true);
//						writer.write(errorFile+"\r\n");
//						writer.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}else{
//					File currentFile = new File(errorfiles+"\\errorfile_"+(listFiles.length+1)+".txt");
//					FileWriter writer = null;
//					try {
//						writer = new FileWriter(currentFile.getAbsoluteFile(), true);
//						writer.write(errorFile+"\r\n");
//						writer.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}else{
//				File currentFile = new File(errorfiles+"\\errorfile_1.txt");
//				FileWriter writer = null;
//				try {
//					writer = new FileWriter(currentFile.getAbsoluteFile(), true);
//					writer.write(errorFile+"\r\n");
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

//	// 文件按照时间排序 baboo
//	private static File[] sortFilesByModifyDate(File[] lists){
//		Arrays.sort(lists, new Comparator<File>() {
//			public int compare(File f1, File f2) {
//				long diff = f1.lastModified() - f2.lastModified();
//				if (diff < 0)
//					return 1;
//				else if (diff == 0)
//					return 0;
//				else
//					return -1;//如果 if 中修改为 返回-1 同时此处修改为返回 1  排序就会是递减
//			}
//		});
//		return lists;
//	}

	public static void main(String[] arms){
//		for(int i=10000;i>0;i--) {
//			convertFailFile("根据《中华人民共和国保守国家秘密法》和相关法律法规规定，乙方就 云服务平台的相关工作 向甲方郑重承诺");
//		}
		//测试 rtfToWord
//		String wordPath="D:\\tmp\\sample1.doc";
//		String rtfPath="D:\\tmp\\sample1.rtf";
//		OfficeUtilForAspnetWords.WordToRtf(wordPath,rtfPath);
//
//		String rtfPath2="D:\\tmp\\sample2.rtf";
//		String wordPath2="D:\\tmp\\sample2.docx";
//		OfficeUtilForAspnetWords.RtfToWord(rtfPath2,wordPath2);


		String pdfPath="D:\\tmp\\sample3333.pdf";
//		OfficeUtilForAspnetWords.wordToPdf(wordPath,pdfPath);

		try {
			byte[] data= OperationPdfUtil.fileToByteArray(pdfPath);
			//PdfReader pr = new PdfReader(pdfPath);
			FileOutputStream outputStream=new FileOutputStream("D:\\tmp\\sample100.pdf");
			OperationPdfUtil.setWatermark(data,outputStream,"不能外传",0.8f);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}  catch (IOException e) {
			throw new RuntimeException(e);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}

	}

//	public static void  convertFailFile3(String errorFile){
//
//		String errorfiles = "d:\\errorconverts2";
//		File ef = new File(errorfiles);
//		if(!ef.exists()){
//			ef.mkdir();
//		}else{
//			File[] listFiles = ef.listFiles();
//			if(listFiles.length>0){
//				listFiles = sortFilesByModifyDate(listFiles);
//				File crrentFile = listFiles[0];
//				if(crrentFile.length()<100*1024*1024){
//					FileWriter writer = null;
//					try {
//						writer = new FileWriter(crrentFile.getAbsoluteFile(), true);
//						writer.write(errorFile+"\r\n");
//						writer.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}else{
//					File currentFile = new File(errorfiles+"\\errorfile_"+(listFiles.length+1)+".txt");
//					FileWriter writer = null;
//					try {
//						writer = new FileWriter(currentFile.getAbsoluteFile(), true);
//						writer.write(errorFile+"\r\n");
//						writer.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}else{
//				File currentFile = new File(errorfiles+"\\errorfile_3.txt");
//				FileWriter writer = null;
//				try {
//					writer = new FileWriter(currentFile.getAbsoluteFile(), true);
//					writer.write(errorFile+"\r\n");
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}




    /**
     * 通过xml模板生成xml到指定的xml文件,并且生成pdf到指定pdf文件
     * @param modelFilePath（xml模板在项目中的路径）
     * @param modelName（xml模板名称）
     * @param tmpWordFile（生成的xml文件）
     * @param tmpPDFFile（生成的pdf文件）
     * @param hashmap（生成xml文件所需要的map数据源）
     * @throws MalformedTemplateNameException
     * @throws ParseException
     * @throws IOException
     * @throws TemplateException
     */
//    public static void createPdfByordanalyze(String modelFilePath, String modelName,
//                                 String tmpWordFile, String tmpPDFFile, Map<String, Object> hashmap)
//            throws MalformedTemplateNameException, ParseException, IOException,
//            TemplateException {
//		log.warn("pdfbook----书签替换开始\r\n");
//        createWordByordanalyze(modelFilePath, modelName, tmpWordFile, hashmap);
//		log.warn("pdfbook----书签替换结束\r\n");
//        // String modelPath = "rmkkx.xml";
//        // 将word转pdf
//		log.warn("pdfbook----word转pdf开始\r\n");
//        //WordUtil.wordToPdf(tmpWordFile, tmpPDFFile);
//		Word2PdfSuwellUtil.doc2pdf(tmpWordFile,tmpPDFFile);
//		log.warn("pdfbook----word转pdf结束\r\n");
//    }




	/**
	 * 通过xml模板生成xml到指定的xml文件,并且生成pdf到指定pdf文件
	 * @param modelFilePath（xml模板在项目中的路径）
	 * @param modelName（xml模板名称）
	 * @param tmpWordFile（生成的xml文件）
	 * @param tmpPDFFile（生成的pdf文件）
	 * @param hashmap（生成xml文件所需要的map数据源）
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void createPdfByordanalyzeNewToPdf(String modelFilePath, String modelName,
											 String tmpWordFile, String tmpPDFFile, Map<String, Object> hashmap,String toPdfType)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
//		log.warn("pdfbook----书签替换开始\r\n");
		createWordByordanalyze(modelFilePath, modelName, tmpWordFile, hashmap);
//		log.warn("pdfbook----书签替换结束\r\n");
		// String modelPath = "rmkkx.xml";
		// 将word转pdf
//		log.warn("pdfbook----word转pdf开始\r\n");
		wordToPdfByType(tmpWordFile, tmpPDFFile, toPdfType);
//		log.warn("pdfbook----word转pdf结束\r\n");
	}
//
	public static void wordToPdfByType(String tmpWordFile, String tmpPDFFile, String toPdfType) throws IOException {
		switch (toPdfType){
			case "wordToPdfNew":
				WordUtil.wordToPdfNew(tmpWordFile, tmpPDFFile);
				break;
			case "wordConverterToPdf":WordUtil.wordConverterToPdf(tmpWordFile, tmpPDFFile);
				break;
			case "word2PdfAsposeUtil":
				Word2PdfAsposeUtil.doc2pdf(tmpWordFile, tmpPDFFile);
				break;
			case "word2PdfSuwell":
				Word2PdfSuwellUtil.doc2pdf(tmpWordFile, tmpPDFFile);
				break;
			case "libconvertOffice2PDF":
				File file = new File(tmpWordFile);
				/*com.spire.doc.Document doc=new com.spire.doc.Document();
				doc.loadFromFile(tmpWordFile);
				String doc2=tmpWordFile.replace(".doc","2.doc");
				doc.saveToFile(doc2, FileFormat.Docx);*/
				String path = file.getParent();
				CommandExecute.convertOffice2PDF(tmpWordFile, path);
				break;
			default:
				Word2PdfSuwellUtil.doc2pdf(tmpWordFile, tmpPDFFile);
				break;
		}
	}
//
	/**
     * 通过word模板生成word到指定的word文件----书签方式
     * @param modelFilePath（word模板在项目中的路径）
     * @param modelName（word模板名称）
     * @param tmpWordFile（生成的word文件）
     * @param bookmarks（生成word文件所需要的map数据源）
     * @throws MalformedTemplateNameException
     * @throws ParseException
     * @throws IOException
     * @throws TemplateException
     */
    public static void createWordByordanalyze(String modelFilePath, String modelName,
                                  String tmpWordFile, Map<String, Object> bookmarks)
            throws MalformedTemplateNameException, ParseException, IOException,
            TemplateException {
       /* File filepath = new File(tmpWordFile);
        // 将模板和数据模型合并生成文件
        FileOutputStream stream = new FileOutputStream(filepath);
        Writer out = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
        // 生成word文件
        // WordUtil.write("/pdfmodel", modelName, hashmap, out);
        WordUtil.write(modelFilePath, modelName, bookmarks, out);*/
        try {

            ReplaceProcess replaceProcess = new ReplaceProcess(bookmarks);
			System.out.println(modelFilePath+"/"+modelName);
            boolean result = replaceProcess.replaceProcess(modelFilePath+"/"+modelName, tmpWordFile);
			JoinPhoto(tmpWordFile, bookmarks);

			if(result){
                System.out.println("替换成功！");
            } else {
                System.out.println("替换失败！");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
//
	private static void JoinPhoto(String tmpWordFile, Map<String, Object> bookmarks) {
    /*
     * 插入图片格式
     * bookmarks key值imagedata
     * imagedata对应一个ArrayList<Map>
     *
     * Map<String,Object>
     * map对应key和value
     * blob:对应Blob数据类型（从数据库中获取到的Blob） 类型为java.sql的Blob
     * position:对应模板图片应处位置的标识文字（标识文字需要在模板中唯一）类型为String
     * width:图片需要设置的宽度 传参类型为float 单位为磅 换算1cm=28.346f
     * height:图片需要设置的高度
     * */
    /*
     * 报错请检查一下WEB-INF中的lib中的Spire.Doc.jar是否添加依赖*/
		//测试数据结束
		List<Map> imagedata = new ArrayList<>();
		if (bookmarks.get("imagedata") != null) {
            imagedata = (List<Map>) bookmarks.get("imagedata");
            bookmarks.remove("imagedata");
        }
		for (Map imageMap : imagedata) {
            String imageUrl = tmpWordFile.substring(0, tmpWordFile.length() - 11) + "temp.jpg";
            saveImage(imageUrl,(Blob) imageMap.get("blob"));
            com.spire.doc.Document document = new com.spire.doc.Document();
            document.loadFromFile(tmpWordFile);
            TextSelection[] selections = document.findAllString(imageMap.get("position").toString(), true, true);
            int index = 0;
            TextRange range = null;
            Table table = document.getSections().get(0).getTables().get(0);
            if (selections != null) {
                for (TextSelection selection : selections) {
                    DocPicture pic = new DocPicture(document);
                    pic.loadImage(imageUrl);
                    pic.setTextWrappingStyle(TextWrappingStyle.Square);
                    pic.setTextWrappingType(TextWrappingType.Both);

                    pic.setWidth((float) imageMap.get("width"));
                    pic.setHeight((float) imageMap.get("height"));
                    range = selection.getAsOneRange();
                    index = range.getOwnerParagraph().getChildObjects().indexOf(range);
                    range.getOwnerParagraph().getChildObjects().insert(index, pic);
                    range.getOwnerParagraph().getChildObjects().remove(range);
                }
                document.saveToFile(tmpWordFile);
            }
            document.dispose();
        }
	}
//
	//将传递过来的Blob数据保存为图片格式
	public static void saveImage(String imageUrl, Blob blob) {

		File imageFile = new File(imageUrl);
		if (imageFile.exists()) {
			if (imageFile.delete()) {
				System.out.println("替换图片删除成功");
			}
		}
		try {
			InputStream input = blob.getBinaryStream();
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imageUrl));
			byte[] b = new byte[(int) blob.length()];
			int count = input.read(b);
			if (count != -1) {
				out.write(b, 0, count);
			}
			out.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//
	public static void wordConverterToPdf(String docxPath,String pdfpath) throws IOException {
		File file = new File(docxPath);
		String path = file.getParent();
		try {
			String osName = System.getProperty("os.name");
			String command = "";
			if (osName.contains("Windows")) {
				//soffice --convert-to pdf  -outdir E:/test.docx
				command = "soffice --convert-to pdf  -outdir " + path + " " + docxPath;
			} else {
				command = "doc2pdf --output=" + pdfpath+ " " + docxPath;
			}
//			log.warn("生成pdf的result==" + command);
			String result = CommandExecute.executeCommand(command);
//			log.warn("生成pdf的result==" + result);
			if (result.equals("") || result.contains("writer_pdf_Export")) {
				log.warn("\r\n生成pdf成功了啊啊啊");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
//
//	public static String readWord(String path) {
//		StringBuilder buffer = new StringBuilder();
//		try {
//			if (path.endsWith(".doc")) {
//				InputStream is = null;
//				try {
//					is = new FileInputStream(new File(path));
//					WordExtractor ex = new WordExtractor(is);
//					buffer.append(ex.getText());
//				}catch (Exception e){
//					log.error("readWorddoc:{}",e);
//					convertFailFile2(path+e.getMessage());
//				}finally {
//					if(is != null){
//						is.close();
//					}
//				}
//			} else if (path.endsWith("docx")) {
//				OPCPackage opcPackage = null;
//				try{
//					opcPackage = POIXMLDocument.openPackage(path);
//					POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
//					buffer.append(extractor.getText());
//				}catch (Exception e){
//					log.error("readWorddocx:{}",e);
//					convertFailFile2(path+e.getMessage());
//				}finally {
//					if(opcPackage != null){
//						opcPackage.close();
//					}
//				}
//			} else {
//				System.out.println("此文件不是word文件！");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(buffer);
//		return buffer.toString();
//	}


	/*通用复制方法*/
	public static void copy(String url1, String url2) {
		try {
			FileInputStream in = new FileInputStream(new File(url1));
			FileOutputStream out = new FileOutputStream(new File(url2));
			byte[] buff = new byte[512];
			int n = 0;
			System.out.println("复制文件：" + "\n" + "源路径：" + url1 + "\n" + "目标路径："
					+ url2);
			while ((n = in.read(buff)) != -1) {
				out.write(buff, 0, n);
			}
			out.flush();
			in.close();
			out.close();
			System.out.println("复制完成");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
