package cn.topcheer.pms2.biz.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
public class OperationPdfUtil {
	/**
	 * 为pdf文件加水印(根据水印文字)
	 * 
	 * @param bytes
	 *            （pdf文件的byte数组）
	 * @param outPutStream
	 *            （ 以字节流的方式保存到所指定文件）
	 * @param waterMarkName
	 *            （水印的文字）
	 * @throws IOException
	 * @throws DocumentException
	 */
    // 所有者密码
    private static final String ownerPassWord = "zjD_1D+S8_98.df*lsfls_+&^!fsa";
	public static void setWatermark(byte[] bytes,
			FileOutputStream outPutStream, String waterMarkName,float opacity)
			throws IOException, DocumentException {
		PdfReader pr = new PdfReader(bytes, ownerPassWord.getBytes());
		// 获取文件页数
		int pageSize = pr.getNumberOfPages();
		PdfStamper stam = new PdfStamper(pr, outPutStream);
		PdfContentByte content = null;
		// 设置字体
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
				BaseFont.EMBEDDED);
        // 设置密码和权限 只允许打印，不允许复制和修改，另存为
        stam.setEncryption(null,
                ownerPassWord.getBytes(), PdfWriter.ALLOW_PRINTING, false);
		PdfGState gs = new PdfGState();
		// 设置透明度(填充不透明度)
		//gs.setFillOpacity(0.4f);
		gs.setFillOpacity(opacity);
		// 中风不透明度
		// gs.setStrokeOpacity(0.4f);
		for (int i = 1; i <= pageSize; i++) {
			content = stam.getOverContent(i);// 在内容上方加水印
			// content = stam.getUnderContent(i);//在内容下方加水印
			content.setGState(gs);
			content.beginText();
			content.setColorFill(BaseColor.LIGHT_GRAY);
			content.setFontAndSize(base, 50);// 设置字体的大小
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 100,
					750, 45);// 宽，高，斜度
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 250,
					650, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 350,
					550, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 200,
					250, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 350,
					150, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 500,
					350, 45);
			content.endText();
		}
		stam.close();
	}
    public static void setWatermarkNotEncry(byte[] bytes,
                                    FileOutputStream outPutStream, String waterMarkName,float opacity,boolean isEncry)
            throws IOException, DocumentException {
        PdfReader pr = null;
        if(isEncry) {
            pr =  new PdfReader(bytes, ownerPassWord.getBytes());
        }else{
            pr =  new PdfReader(bytes, "PDF".getBytes());
        }
        // 获取文件页数
        int pageSize = pr.getNumberOfPages();
        PdfStamper stam = new PdfStamper(pr, outPutStream);
        PdfContentByte content = null;
        // 设置字体
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                BaseFont.EMBEDDED);

        if(isEncry) {
            // 设置密码和权限 只允许打印，不允许复制和修改，另存为
            stam.setEncryption(null, ownerPassWord.getBytes(), PdfWriter.ALLOW_PRINTING, false);
        }
        PdfGState gs = new PdfGState();
        // 设置透明度(填充不透明度)
        //gs.setFillOpacity(0.4f);
        gs.setFillOpacity(opacity);
        // 中风不透明度
        // gs.setStrokeOpacity(0.4f);
        for (int i = 1; i <= pageSize; i++) {
            content = stam.getOverContent(i);// 在内容上方加水印
            // content = stam.getUnderContent(i);//在内容下方加水印
            content.setGState(gs);
            content.beginText();
            content.setColorFill(BaseColor.LIGHT_GRAY);
            content.setFontAndSize(base, 50);// 设置字体的大小
            content.setTextMatrix(70, 200);
            content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 100,
                    750, 45);// 宽，高，斜度
            content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 250,
                    650, 45);
            content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 350,
                    550, 45);
            content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 200,
                    250, 45);
            content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 350,
                    150, 45);
            content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 500,
                    350, 45);
            content.endText();
        }
        stam.close();
    }
	/**
	 * 为pdf文件加水印(根据水印文字)
	 * 
	 * @param bytes
	 *            （pdf文件的byte数组）
	 * @param outPutStream
	 *            （ 以字节流的方式保存到所指定文件）
	 * @param waterMarkName
	 *            （水印的文字）
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void setWatermark01(byte[] bytes,
			FileOutputStream outPutStream, String waterMarkName,float opacity)
					throws IOException, DocumentException {
		PdfReader pr = new PdfReader(bytes, ownerPassWord.getBytes());
		// 获取文件页数
		int pageSize = pr.getNumberOfPages();
		PdfStamper stam = new PdfStamper(pr, outPutStream);
		PdfContentByte content = null;
		// 设置字体
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
				BaseFont.EMBEDDED);
        // 设置密码和权限 只允许打印，不允许复制和修改，另存为
        stam.setEncryption(null,
                ownerPassWord.getBytes(), PdfWriter.ALLOW_PRINTING, false);
		PdfGState gs = new PdfGState();
		// 设置透明度(填充不透明度)
		//gs.setFillOpacity(0.4f);
		gs.setFillOpacity(opacity);
		// 中风不透明度
		// gs.setStrokeOpacity(0.4f);
		for (int i = 1; i <= pageSize; i++) {
			content = stam.getOverContent(i);// 在内容上方加水印
			// content = stam.getUnderContent(i);//在内容下方加水印
			content.setGState(gs);
			content.beginText();
			content.setColorFill(BaseColor.LIGHT_GRAY);
			content.setFontAndSize(base, 50);// 设置字体的大小
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 100,
					250, 45);// 宽，高，斜度
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 250,
					150, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 350,
					350, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 450,
					250, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 550,
					150, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 650,
					350, 45);
			content.endText();
		}
		stam.close();
	}
	public static void setWatermarkhx(byte[] bytes,
									  FileOutputStream outPutStream, String waterMarkName,float opacity)
			throws IOException, DocumentException {
		PdfReader pr =null;
		PdfStamper stam =null;
		try {
			pr = new PdfReader(bytes, ownerPassWord.getBytes());
			// 获取文件页数
			int pageSize = pr.getNumberOfPages();
			stam = new PdfStamper(pr, outPutStream);
			PdfContentByte content = null;
			// 设置字体
			BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.EMBEDDED);
			// 设置密码和权限 只允许打印，不允许复制和修改，另存为
			stam.setEncryption(null,
					ownerPassWord.getBytes(), PdfWriter.ALLOW_PRINTING, false);
			PdfGState gs = new PdfGState();
			// 设置透明度(填充不透明度)
			//gs.setFillOpacity(0.4f);
			gs.setFillOpacity(opacity);
			// 中风不透明度
			// gs.setStrokeOpacity(0.4f);
			for (int i = 1; i <= pageSize; i++) {
				content = stam.getOverContent(i);// 在内容上方加水印
				// content = stam.getUnderContent(i);//在内容下方加水印
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.LIGHT_GRAY);
				content.setFontAndSize(base, 50);// 设置字体的大小
				content.setTextMatrix(70, 200);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 100,
						550, 45);// 宽，高，斜度
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 200,
						450, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 300,
						350, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 400,
						250, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 500,
						150, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 600,
						50, 45);
				content.endText();
			}
			if (pr!=null) {
				pr.close();
			}
			if (stam!=null) {
				stam.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (pr!=null) {
				pr.close();
			}
			if (stam!=null) {
				stam.close();
			}
		}
	}
	public static void setWatermark02(byte[] bytes,
									  FileOutputStream outPutStream, String waterMarkName,float opacity,String passornot)
			throws IOException, DocumentException {
		PdfReader pr = new PdfReader(bytes, ownerPassWord.getBytes());
		// 获取文件页数
		int pageSize = pr.getNumberOfPages();
		PdfStamper stam = new PdfStamper(pr, outPutStream);
		PdfContentByte content = null;

		// 设置字体
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
				BaseFont.EMBEDDED);
        // 设置密码和权限 只允许打印，不允许复制和修改，另存为
        stam.setEncryption(null,
                ownerPassWord.getBytes(), PdfWriter.ALLOW_PRINTING, false);
		PdfGState gs = new PdfGState();
		// 设置透明度(填充不透明度)
		//gs.setFillOpacity(0.4f);
		gs.setFillOpacity(opacity);
		// 中风不透明度
		// gs.setStrokeOpacity(0.4f);
		for (int i = 1; i <= pageSize; i++) {
			Rectangle pageRect = stam.getReader().getPageSizeWithRotation(i);
			// 计算水印X,Y坐标
			float x = pageRect.getWidth() / 2;
			float y = pageRect.getHeight() / 2;
			content = stam.getOverContent(i);// 在内容上方加水印
			// content = stam.getUnderContent(i);//在内容下方加水印
			content.setGState(gs);
			content.beginText();
			content.setColorFill(BaseColor.LIGHT_GRAY);
			content.setFontAndSize(base, 50);// 设置字体的大小
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x,
					y, 45);// 宽，高，斜度
			if(passornot=="审核通过") {
				content.showTextAligned(Element.ALIGN_CENTER, "审核通过", x+80,
						y-10, 45);
			}else if(passornot=="未审核") {
                content.showTextAligned(Element.ALIGN_CENTER, "未审核", x+80,
                        y-10, 45);
            }else{
                content.showTextAligned(Element.ALIGN_CENTER, "", x+80,
                        y-10, 45);
            }
			content.endText();
		}
		stam.close();
	}

	public static void addPageNumAndWatermark02(byte[] bytes,
									  FileOutputStream outPutStream, String waterMarkName,float opacity,String passornot)
			throws IOException, DocumentException {
		PdfReader pr = new PdfReader(bytes, ownerPassWord.getBytes());
		// 获取文件页数
		int pageSize = pr.getNumberOfPages();
		PdfStamper stam = new PdfStamper(pr, outPutStream);
		PdfContentByte content = null;

		// 设置字体
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
				BaseFont.EMBEDDED);
		PdfGState gs = new PdfGState();
		// 设置透明度(填充不透明度)
		//gs.setFillOpacity(0.4f);
		gs.setFillOpacity(opacity);
		// 中风不透明度
		// gs.setStrokeOpacity(0.4f);
		for (int i = 1; i <= pageSize; i++) {
			Rectangle pageRect = stam.getReader().getPageSizeWithRotation(i);
			// 计算水印X,Y坐标
			float x = pageRect.getWidth() / 2;
			float y = pageRect.getHeight() / 2;
			content = stam.getOverContent(i);// 在内容上方加水印
			// content = stam.getUnderContent(i);//在内容下方加水印
			//content.setGState(gs);
			content.beginText();
			content.setColorFill(BaseColor.DARK_GRAY);
			content.setFontAndSize(base, 14);// 设置字体的大小
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, i+" / "+pageSize, x,
					y/10, 0);
			content.endText();
			/*水印*/
			content = null;
			content = stam.getOverContent(i);// 在内容上方加水印
			// content = stam.getUnderContent(i);//在内容下方加水印
			content.setGState(gs);
			content.beginText();
			content.setColorFill(BaseColor.LIGHT_GRAY);
			content.setFontAndSize(base, 50);// 设置字体的大小
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x,
					y, 45);// 宽，高，斜度
			if(passornot=="审核通过") {
				content.showTextAligned(Element.ALIGN_CENTER, "审核通过", x+80,
						y-10, 45);
			}else if(passornot=="未审核") {
				content.showTextAligned(Element.ALIGN_CENTER, "未审核", x+80,
						y-10, 45);
			}else{
				content.showTextAligned(Element.ALIGN_CENTER, "", x+80,
						y-10, 45);
			}
			content.endText();
		}
		stam.close();
	}

	public static void addPageNumAndWatermark(byte[] bytes,
												FileOutputStream outPutStream, String waterMarkName,float opacity,String passornot)
			throws IOException, DocumentException {
		PdfReader pr = new PdfReader(bytes, ownerPassWord.getBytes());
		// 获取文件页数
		int pageSize = pr.getNumberOfPages();
		PdfStamper stam = new PdfStamper(pr, outPutStream);
		PdfContentByte content = null;

		// 设置字体
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
				BaseFont.EMBEDDED);
		PdfGState gs = new PdfGState();
		// 设置透明度(填充不透明度)
		//gs.setFillOpacity(0.4f);
		gs.setFillOpacity(opacity);
		// 中风不透明度
		// gs.setStrokeOpacity(0.4f);
		for (int i = 1; i <= pageSize; i++) {
			Rectangle pageRect = stam.getReader().getPageSizeWithRotation(i);
			// 计算水印X,Y坐标
			float x = pageRect.getWidth() / 2;
			float y = pageRect.getHeight() / 2;
			content = stam.getOverContent(i);// 在内容上方加水印
			// content = stam.getUnderContent(i);//在内容下方加水印
			content.beginText();
			content.setColorFill(BaseColor.DARK_GRAY);
			content.setFontAndSize(base, 14);// 设置字体的大小
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, i+" / "+pageSize, x,
					y/10, 0);
			content.endText();
			/*水印*/
			content.setGState(gs);
			content.beginText();
			content.setColorFill(BaseColor.LIGHT_GRAY);
			content.setFontAndSize(base, 50);// 设置字体的大小
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 100,
					750, 45);// 宽，高，斜度
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 250,
					650, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 350,
					550, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 200,
					250, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 350,
					150, 45);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 500,
					350, 45);
			content.endText();
		}
		stam.close();
	}

	public static void setWatermark02zl(byte[] bytes,
									  FileOutputStream outPutStream, String waterMarkName,float opacity,String passornot)
			throws IOException, DocumentException {
		PdfReader pr = new PdfReader(bytes, ownerPassWord.getBytes());
		// 获取文件页数
		int pageSize = pr.getNumberOfPages();
		PdfStamper stam = new PdfStamper(pr, outPutStream);
		PdfContentByte content = null;
       // 设置密码和权限 只允许打印，不允许复制和修改，另存为
        stam.setEncryption(null,ownerPassWord.getBytes(), PdfWriter.ALLOW_PRINTING, false);
		// 设置字体
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
				BaseFont.EMBEDDED);
		PdfGState gs = new PdfGState();
		// 设置透明度(填充不透明度)
		//gs.setFillOpacity(0.4f);
		gs.setFillOpacity(opacity);
		// 中风不透明度
		// gs.setStrokeOpacity(0.4f);
		for (int i = 1; i <= pageSize; i++) {
			Rectangle pageRect = stam.getReader().getPageSizeWithRotation(i);
			// 计算水印X,Y坐标
			float x = pageRect.getWidth() / 2;
			float y = pageRect.getHeight() / 2;
			content = stam.getOverContent(i);// 在内容上方加水印
			// content = stam.getUnderContent(i);//在内容下方加水印
			content.setGState(gs);
			content.beginText();
			content.setColorFill(BaseColor.LIGHT_GRAY);
			content.setFontAndSize(base, 50);// 设置字体的大小
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x,
					y, 45);// 宽，高，斜度
			if(passornot!="") {
				content.showTextAligned(Element.ALIGN_CENTER, "审核通过", x,
						y, 45);
			}
			content.endText();
		}
		stam.close();
	}

	public static void setWatermarkWithSize(byte[] bytes,
											FileOutputStream outPutStream, String waterMarkName,float opacity,int fontsize,
											String waterstyle,String passornot,Boolean isEncry)
			throws IOException, DocumentException {
		PdfReader pr = null;
		if(isEncry) {
			pr =  new PdfReader(bytes, ownerPassWord.getBytes());
		}else{
			pr =  new PdfReader(bytes, "PDF".getBytes());
		}
		// 获取文件页数
		int pageSize = pr.getNumberOfPages();
		PdfStamper stam = new PdfStamper(pr, outPutStream);
		PdfContentByte content = null;

		// 设置字体
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
				BaseFont.EMBEDDED);
		// 设置密码和权限 只允许打印，不允许复制和修改，另存为
		stam.setEncryption(null,
				ownerPassWord.getBytes(), PdfWriter.ALLOW_PRINTING, false);
		PdfGState gs = new PdfGState();
		// 设置透明度(填充不透明度)
		//gs.setFillOpacity(0.4f);
		gs.setFillOpacity(opacity);
		// 中风不透明度
		// gs.setStrokeOpacity(0.4f);

		if("Watermark02".equals(waterstyle)){
			for (int i = 1; i <= pageSize; i++) {
                Rectangle pageRect = stam.getReader().getPageSizeWithRotation(i);
                // 计算水印X,Y坐标
                float x = pageRect.getWidth() / 2;
                float y = pageRect.getHeight() / 2;
                content = stam.getOverContent(i);// 在内容上方加水印
                // content = stam.getUnderContent(i);//在内容下方加水印
                content.setGState(gs);
                content.beginText();
                content.setColorFill(BaseColor.LIGHT_GRAY);
                content.setFontAndSize(base, fontsize);// 设置字体的大小
                content.setTextMatrix(70, 200);
                content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x,
                        y, 45);// 宽，高，斜度
                if(passornot=="审核通过") {
                    content.showTextAligned(Element.ALIGN_CENTER, "审核通过", x+80,
                            y-10, 45);
                }else if(passornot=="未审核") {
                    content.showTextAligned(Element.ALIGN_CENTER, "未审核", x+80,
                            y-10, 45);
                }else{
                    content.showTextAligned(Element.ALIGN_CENTER, "", x+80,
                            y-10, 45);
                }
                content.endText();
            }
		}
		if("Watermark".equals(waterstyle)){

			for (int i = 1; i <= pageSize; i++) {
				content = stam.getOverContent(i);// 在内容上方加水印
				// content = stam.getUnderContent(i);//在内容下方加水印
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.LIGHT_GRAY);
				content.setFontAndSize(base, fontsize);// 设置字体的大小
				content.setTextMatrix(70, 200);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 100,
						750, 45);// 宽，高，斜度
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 250,
						650, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 350,
						550, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 200,
						250, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 350,
						150, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 500,
						350, 45);
				content.endText();
			}
		}
		if("WatermarkRL".equals(waterstyle)){//左右均匀分布

			for (int i = 1; i <= pageSize; i++) {
				content = stam.getOverContent(i);// 在内容上方加水印
				// content = stam.getUnderContent(i);//在内容下方加水印
				// 计算水印X,Y坐标
				Rectangle pageRect = stam.getReader().getPageSizeWithRotation(i);
				float x = pageRect.getWidth() / 2;
				float y = pageRect.getHeight() / 2;
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.LIGHT_GRAY);
				content.setFontAndSize(base, fontsize);// 设置字体的大小
				content.setTextMatrix(70, 200);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x-50,
						y-200, 45);// 宽，高，斜度
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x-100,
						y+100, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x-150,
						y+150, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x-200,
						y-150, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x-250,
						y+50, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x-300,
						y-250, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x+50,
						y+200, 45);// 宽，高，斜度
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x+100,
						y-100, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x+150,
						y-150, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x+200,
						y+150, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x+250,
						y-50, 45);
				content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x+300,
						y+250, 45);
				content.endText();
			}
		}
		stam.close();
	}

	/**
	 * 通过添加背景图片为pdf添加水印
	 * @param stream(FileInputStream)
	 * @param outStream(FileOutputStream)
	 * @param imagePath(图片路径)
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void addPdfMarkByImage(FileInputStream stream,
			FileOutputStream outStream, String imagePath)
			throws DocumentException, IOException {
		byte[] bytes = streamToByteArray(stream);
		addPdfMarkByImage(bytes, outStream, imagePath);
	}
	
	/**
	 * 
	 * @param bytes(pdf的字节数组)
	 * @param outStream
	 * @param imagePath
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void addPdfMarkByImage(byte[] bytes,
			FileOutputStream outStream, String imagePath) throws IOException, DocumentException{
		PdfReader pr = new PdfReader(bytes, "PDF".getBytes());
		int pagesize = pr.getNumberOfPages();
		PdfStamper stam = new PdfStamper(pr, outStream);
		Image image = Image.getInstance(imagePath);
		image.setAbsolutePosition(80, 80);

		for (int i = 1; i <= pagesize; i++) {
			PdfContentByte pcb = stam.getUnderContent(i);
			pcb.addImage(image);
		}
		stam.close();
	}

	/**
	 * 将文件转字节数组
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] fileToByteArray(String filePath) throws IOException {
		FileInputStream in = null;
		File file = new File(filePath);
		in = new FileInputStream(file);
		byte[] bytes = streamToByteArray(in);
		if(in!=null){
			in.close();
		}
		return bytes;	
	}

	/**
	 * 将输入流转成Byte数组
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 */
	public static byte[] streamToByteArray(FileInputStream stream)
			throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
		byte[] b = new byte[1000];
		int n;
		while ((n = stream.read(b)) != -1) {
			bos.write(b, 0, n);
		}
		byte[] bytes = bos.toByteArray();
		if (bos != null) {
			bos.close();
		}
		return bytes;
	}

	/**
	 * 
	 * @param i
	 * @param bytes
	 * @param projectbaseName
	 * @param tmpFilePath
	 * @param response
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void outPut(int i, String projectbaseName, byte[] bytes,
			String tmpFilePath, float opacity,HttpServletResponse response)
			throws IOException, DocumentException {

		FileOutputStream out = null;
		/*
		 * 如果i为0，传过来的byte[] bytes 为已经将模板转为pdf的字节数组，说明要从模板中生成pdf,并且生成临时文件到本地
		 * 如果i为1，说明直接从本地下载
		 */
		if (i == 0) {
			/*
			 * File outFile = new File(tmpFilePath); out = new
			 * FileOutputStream(outFile); out.write(bytes); // 写到本地临时文件
			 */
			File outFile = new File(tmpFilePath);
			out = new FileOutputStream(outFile);
			String waterMarkName = "审核通过";
			// 写到本地临时文件
			OperationPdfUtil.setWatermark(bytes, out, waterMarkName,opacity);
			// 再从本地临时文件获取该pdf文件
			bytes = OperationPdfUtil.fileToByteArray(tmpFilePath);
		} else {
			bytes = OperationPdfUtil.fileToByteArray(tmpFilePath);
		}
		outResponse(response, projectbaseName, bytes);
        if (out != null) {
			out.close();
		}
	}

	/**
	 * 向客户提供下载
	 * @param response
	 * @param projectbaseName
	 * @param bytes
	 * @throws IOException
	 */
	public static void outResponse(HttpServletResponse response,
			String projectbaseName, byte[] bytes) throws IOException {
		// 将pdf流通过outputStream输出到浏览器
		response.reset(); // 清除首部的空白行
		response.setContentType("application/pdf");
		String fileName = projectbaseName + ".pdf";
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes(), "ISO8859-1"));
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);

		// 执行完后，关闭相应连接
		if (ouputStream != null) {
			ouputStream.close();
		}
	}


	/**
	 * 向客户提供下载doc类型
	 * @param response
	 * @param projectbaseName
	 * @param bytes
	 * @throws IOException
	 */
	public static void outResponseword(HttpServletResponse response,
								   String projectbaseName, byte[] bytes) throws IOException {
		// 将pdf流通过outputStream输出到浏览器
		response.reset(); // 清除首部的空白行
		response.setContentType("application/doc");
		String fileName = projectbaseName + ".doc";
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes(), "ISO8859-1"));
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);

		// 执行完后，关闭相应连接
		if (ouputStream != null) {
			ouputStream.close();
		}
	}

	/**
	 * 向客户提供下载--excel
	 * @param response
	 * @param projectbaseName
	 * @param bytes
	 * @throws IOException
	 */
	public static void outResponseexcel(HttpServletResponse response,
										String projectbaseName, byte[] bytes) throws IOException {
		// 将pdf流通过outputStream输出到浏览器
		response.reset(); // 清除首部的空白行
		response.setContentType("application/msexcel");
		String fileName = projectbaseName + ".xls";
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes(), "ISO8859-1"));
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);

		// 执行完后，关闭相应连接
		if (ouputStream != null) {
			ouputStream.close();
		}
	}
	
	
	
	
	 /** 
	  * @author lhl
     * 图片添加水印 
     * @param srcImgPath 需要添加水印的图片的路径 
     * @param outImgPath 添加水印后图片输出路径 
     * @param markContentColor 水印文字的颜色 
     * @param waterMarkContent 水印的文字 
     */  
    public static void mark(String srcImgPath, String outImgPath, Color markContentColor, String waterMarkContent) {  
        try {  
            // 读取原图片信息  
            File srcImgFile = new File(srcImgPath);  
            BufferedImage srcImg = ImageIO.read(srcImgFile);  
            int srcImgWidth = srcImg.getWidth(null);  
            int srcImgHeight = srcImg.getHeight(null);  
            // 加水印  
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);  
            Graphics2D g = bufImg.createGraphics();  
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);  
            //Font font = new Font("Courier New", Font.PLAIN, 12);  
            Font font = new Font("宋体", Font.PLAIN, 250);    
            g.setColor(markContentColor); //根据图片的背景设置水印颜色  
              
            g.setFont(font);  
            int x = (srcImgWidth - getWatermarkLength(waterMarkContent, g))/2;  
            int y = (srcImgHeight - 3)/2;  
            //int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;  
            //int y = srcImgHeight / 2;  
            g.drawString(waterMarkContent, x, y);  
            g.dispose();  
            // 输出图片  
            FileOutputStream outImgStream = new FileOutputStream(outImgPath);  
            ImageIO.write(bufImg, "jpg", outImgStream);  
            outImgStream.flush();  
            outImgStream.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    
    
    /** 
     * 获取水印文字总长度 
     * @param waterMarkContent 水印的文字 
     * @param g 
     * @return 水印文字总长度 
     */  
    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {  
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());  
    }  
	

	/**
	 * 
	 * @param modelPath
	 * @param projectbaseName
	 * @param tmpFilePath
	 * @param projectbaseid
	 * @param conn
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void loadPdf(String modelPath, String projectbaseName,
			String tmpFilePath, String projectbaseid, Connection conn,float opacity,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 创建报表参数Map对象，需要传入报表的参数，均通过这个map传递
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", projectbaseid);
		// 读取报表模板文件（可以从本地磁盘读取，也可以从项目文件中获取）
		File reportFile = new File(modelPath);

		// 通过jasperreport提供的类和方法，将模板转为pdf流
		byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),
				parameters, conn);
		outPut(0, projectbaseName, bytes, tmpFilePath, opacity, response);
		// 关闭数据库连接
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * 根据批次id，选择不同批次对应的模板路径
	 * 
	 *  projectbatchName
	 * @return
	 */
	public static String selectTemplatePath(String projectbatchid) {
		String path = "PdfModel/rkxxm/rkxxmone.jasper";
		// switch (projectbatchid) {
		// case "5FDCE3D2-BD22-4C9E-B7E1-4B4DF73D6B39"://重点研发计划
		// path = "";
		// break;
		// case "727CF453-1C31-44C9-9671-91EF2523936F"://新产品试制计划
		// path = "";
		// break;
		// case "2516DB1A-A03A-4786-9361-312CB14E150D"://软科学研究计划
		// path = "PdfModel/rkxxm/rkxxmone.jasper";
		// break;
		// case "93557D99-D413-40B6-B5E3-A1FCE997B42E"://公益技术应用研究（实验动物）
		// path = "";
		// break;
		// case "F8442549-DEE6-42DD-AF58-11FF610A1CB8"://公益技术应用研究（分析测试）
		// path = "";
		// break;
		// case "133ED10A-28CB-4980-95B0-CE6061C3AABD"://公益技术应用研究
		// path = "";
		// break;
		// case "542A2FFC-4369-49A6-ADEB-B6307782F029"://长兴县科技计划项目
		// path = "";
		// break;
		// case "AF3BAB8B-564E-4118-9C67-4AB48918E88C"://长兴县企业研发项目
		// path = "";
		// break;
		// default:
		// path = "";
		// break;
		// }
		return path;
	}
	/**
	 * @author lhl
	 * 将多个的pdf转换为一个pdf
	 * @param folder
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	public static void mergePDF(String folder) throws COSVisitorException, IOException{

		PDFMergerUtility mergePdf = new PDFMergerUtility();

		String destinationFileName = "浙江省科技计划项目验收材料.pdf";

		String[] filesInFolder = {"目录.pdf","项目合同书.pdf","项目总结报告.pdf","项目技术报告.pdf","项目审计报告.pdf","项目论文发表情况.pdf",
				"团队成员或项目支持情况.pdf","团队成员职称晋升情况.pdf","团队成员学习情况.pdf","获软件注册权和发明专利情况.pdf"};
		for (int i = 0; i < filesInFolder.length; i++)
			mergePdf.addSource(folder + File.separator + filesInFolder[i]);

		mergePdf.setDestinationFileName(folder + File.separator
				+ destinationFileName);
		mergePdf.mergeDocuments();
	}
	
	/**
	 * @author lhl
	 * 读取一个文件夹里的多个pdf
	 * @param folder
	 * @return
	 * @throws IOException
	 */
	private static String[] getFiles(String folder) throws IOException {
		File _folder = new File(folder);
		String[] filesInFolder;

		if (_folder.isDirectory()) {
			filesInFolder = _folder.list();
			return filesInFolder;
		} else {
			throw new IOException("Path is not a directory");
		}
	}


    public static void setQrcode(byte[] bytes,
                                    FileOutputStream outPutStream, float opacity)
            throws IOException, DocumentException {
        PdfReader pr = new PdfReader(bytes, ownerPassWord.getBytes());
        // 获取文件页数
        int pageSize = pr.getNumberOfPages();
        PdfStamper stam = new PdfStamper(pr, outPutStream);
        PdfContentByte content = null;
        // 设置字体
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                BaseFont.EMBEDDED);
        PdfGState gs = new PdfGState();
        // 设置透明度(填充不透明度)
        //gs.setFillOpacity(0.4f);
        gs.setFillOpacity(opacity);
        // 中风不透明度
        // gs.setStrokeOpacity(0.4f);
        for (int i = 1; i <= pageSize; i++) {
            content = stam.getOverContent(i);// 在内容上方加图片
            content.setGState(gs);
            content.beginText();
            content.setColorFill(BaseColor.LIGHT_GRAY);
            content.setFontAndSize(base, 50);// 设置字体的大小
            content.setTextMatrix(70, 200);
            content.showTextAligned(Element.ALIGN_CENTER, "cscs", 100,
                    250, 45);// 宽，高，斜度
            content.showTextAligned(Element.ALIGN_CENTER,  "cscs",250,
                    150, 45);
            content.showTextAligned(Element.ALIGN_CENTER, "cscs", 350,
                    350, 45);
            content.showTextAligned(Element.ALIGN_CENTER, "cscs", 450,
                    250, 45);
            content.showTextAligned(Element.ALIGN_CENTER,  "cscs", 550,
                    150, 45);
            content.showTextAligned(Element.ALIGN_CENTER,  "cscs", 650,
                    350, 45);
            content.endText();

            PdfDocument document=content.getPdfDocument();
            Image jpg = Image.getInstance("d:/baidu/ewm.jpg");
            jpg.setAlignment(Image.ALIGN_CENTER);
            float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
            float documentHeight = documentWidth / 580 * 320;//重新设置宽高
            jpg.scaleAbsolute(documentWidth, documentHeight);//重新设置宽高
            document.add(jpg);
        }
        stam.close();
    }

    public static void main11(byte[] bytes) throws Exception {
         // 模板文件路径
         String templatePath = "d:/baidu/cscs11.pdf";
         // 生成的文件路径
         String targetPath = "d:/baidu/cscs12.pdf";
         // 关键字名
         String fieldName = "SignatureField1";
         // 图片路径
         String imagePath = "d:/baidu/ewm.jpg";


         FileOutputStream fos = new FileOutputStream(targetPath);
         // 读取模板文件
         InputStream input = new FileInputStream(new File(templatePath));
         PdfReader reader = new PdfReader(input);
         PdfStamper stamper = new PdfStamper(reader, fos);
         // 提取pdf中的表单
         AcroFields form = stamper.getAcroFields();
         form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));

        // 获取文件页数
        int pageSize = reader.getNumberOfPages();
         // 通过域名获取所在页和坐标，左下角为起点
         /*nt pageNo = form.getFieldPositions(fieldName).get(0).page;
         Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
         float x = signRect.getLeft();
         float y = signRect.getBottom();*/
         //x = 20f;
         //y = 40f;
         // 读图片
         Image image = Image.getInstance(imagePath);
        for (int i = 1; i < pageSize; i++) {
            // 获取操作的页面
            PdfContentByte under = stamper.getOverContent(i);
            // 根据域的大小缩放图片
            //image.scaleToFit(signRect.getWidth(), signRect.getHeight());
            image.scaleToFit(25, 25);
            // 添加图片
            //image.setAbsolutePosition(x, y);
            image.setAbsolutePosition(11,22);
            under.addImage(image);
        }
         fos.flush();
         fos.close();
         /*stamper.close();*/
         reader.close();



         }

	/**
	 * 从输入流中获取字节数组
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static  byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	//加密码
	public static void setPassWorkmark(byte[] bytes,
									   FileOutputStream outPutStream)
			throws IOException, DocumentException {

		PdfReader pr = new PdfReader(bytes, ownerPassWord.getBytes());
		// 获取文件页数
		int pageSize = pr.getNumberOfPages();
		PdfStamper stam = new PdfStamper(pr, outPutStream);
		// 设置密码和权限 只允许打印，不允许复制和修改，另存为
		stam.setEncryption(null,
				ownerPassWord.getBytes(), PdfWriter.ALLOW_PRINTING, false);
		stam.close();
		pr.close();
	}

	public static void zipOut(HttpServletResponse response,String folder) throws IOException {
		int i = folder.lastIndexOf('/');
		String zipname = folder.substring(i+1)+".zip";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ZipUtils.toZip(folder,out,true);
		byte[] bytes = out.toByteArray();

		response.reset(); // 清除首部的空白行
		response.setContentType("application/zip");
		// TODO: 2022/7/12 这个返回前台的名称怎么弄，好像只能给4个字符长度
        /*response.setHeader("Content-Disposition", "attachment;filename="
                + new String(zipname.getBytes("gb2312"),"ISO8859-1"));*/
		response.setHeader("Content-Disposition", "attachment;filename=压缩"
				+".zip");
		response.setContentLength(bytes.length);
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(bytes, 0, bytes.length);

		// 执行完后，关闭相应连接
		if (outputStream != null) {
			outputStream.close();
		}
//		PmsAffixController.deleteFileOrDictionary(Util.GetFileRealPath(folder));//弃用因为windows可以识别file.isfile()，而linux环境未识别出
		FileUtils.deleteDirectory(new File(folder));
	}
}
