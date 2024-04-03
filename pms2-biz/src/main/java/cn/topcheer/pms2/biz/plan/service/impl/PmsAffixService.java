package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.common3.filestorage.FileInfo;
import cn.topcheer.common3.filestorage.FileStorage;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.SysParamvalue;
import cn.topcheer.pms2.api.plan.entity.PmsAffix;
import cn.topcheer.pms2.api.plan.entity.PmsAttachment;
import cn.topcheer.pms2.biz.sys.SysParamvalueService;
import cn.topcheer.pms2.biz.utils.DBHelperAffix;
import cn.topcheer.pms2.biz.utils.DBHelperAffixNew;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.biz.utils.WordUtil;
import cn.topcheer.pms2.dao.plan.PmsAffixDao;
import dm.jdbc.driver.DmdbBlob;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "PmsAffixService")
public class PmsAffixService extends GenericService<PmsAffix> {

    public PmsAffixDao getPmsAffixDao() {
        return (PmsAffixDao) this.getGenericDao();
    }
//	public final static String localFilePath = "PMSFILES/pmsAffix/";

    @Autowired
	DBHelper dbHelper;
	@Autowired(required = false)
	DBHelperAffix dbHelperAffix;
	@Autowired(required = false)
	DBHelperAffixNew dbHelperAffixNew;
//	@Autowired
//	PmsProjectbaseService pmsProjectbaseService;
	@Autowired
	SysParamvalueService sysParamvalueService;
//	@Autowired
//	private CreatePdfOrWord createPdfOrWord;
//	@Autowired
//	private PmsAttachmentService pmsAttachmentService;
	@Autowired(required = false)
	private FileStorage fileStorage;
//	@Autowired
//	private PmsProjectbaseFzrqkService pmsProjectbaseFzrqkService;
//	@Autowired
//	private PmsProjectbaseLeaderunitService pmsProjectbaseLeaderunitService;
//	@Autowired
//	private PmsProjectbaseAwardsService pmsProjectbaseAwardsService;
//	@Autowired
//	private PmsProjectbaseThesisService pmsProjectbaseThesisService;
//	@Autowired
//	private PmsProjectbasePatentService pmsProjectbasePatentService;
//	@Autowired
//	private DiskFileStorageProperties diskFileStorageProperties;
//	@Autowired
//	private PmsClobService pmsClobService;
//
//	//附件存储路径
//	private static String defaultFilePath = "D:/";
//
//	@Value("${server.defaultFilePath}")
//	public void setDefaultFilePath(String defaultFilePath){
//		PmsAffixService.defaultFilePath = defaultFilePath;
//	}
//
    @Autowired
    public void setPmsAffixDao(PmsAffixDao pmsAffixDao) {
        this.setGenericDao(pmsAffixDao);
    }
//
//	public PmsAffix downloadfileByName(String filename){
//		List<PmsAffix> list = this.getPmsAffixDao().findByProperty("name", filename);
//		if(list.size()>0){
//			return  list.get(0);
//		}else{
//			return null;
//		}
//	}
//
//	public List<PmsAffix> findAffixByName(String affixName) {
//		// TODO Auto-generated method stub
//		List<PmsAffix> listAffix = findByHql("select new map(aff.id as id,aff.sourceid as sourceid,aff.createuserid as createuserid,aff.name as name,aff.filesize as filesize,aff.type as type) from cn.topcheer.pms.pojo.PmsAffix as aff where "
//				+ "aff.name =? " ,affixName);
//		if(listAffix.size()>0)
//			return listAffix;
//		else
//			return null;
//	}
//	public boolean checkAffixByName(String sourceId,String affixType) {
//		// TODO Auto-generated method stub
//		List<PmsAffix> listAffix = findByHql("select new map(aff.id as id,aff.sourceid as sourceid,aff.createuserid as createuserid,aff.name as name,aff.filesize as filesize,aff.type as type) from cn.topcheer.pms.pojo.PmsAffix as aff where "
//				+ "aff.sourceid =? and aff.type = ?" ,sourceId,affixType);
//		if(listAffix.size()>0)
//			return true;
//		else
//			return false;
//	}
//
//	public String fromBaseGetDocid(String baseid) {
//		// TODO Auto-generated method stub
//		String sql = "select affix.id as docid from pms_projectbase base "
//				+ " left join pms_affix affix on affix.sourceid = base.id"
//				+ " where base.id = ? and affix.type='可行性报告'";
//		List<Map> listMap = dbHelper.getRows(sql, new Object[]{baseid});
//		if(listMap!=null&&listMap.size()>0)
//			return listMap.get(0).get("docid").toString();
//		else
//			return "";
//	}
//
//	/**
//	 * 根据type下载附件
//	 *
//	 * @param type
//	 * @throws Exception
//	 */
//	public List<String> getuploadFile( List<String> list,String projectbaseid,String tmpPDFFile,String type) throws Exception {
//		String hql = "select t from cn.topcheer.pms.pojo.PmsAffix t where t.sourceid=? and t.type=? order by createdate ";
//		List<PmsAffix> lis = this.findByHql(hql, projectbaseid,type);
//		String folder = tmpPDFFile + "/" + type;
//		File tmpFolderPathFile = new File(folder);
//		if (!tmpFolderPathFile.exists()) {
//			tmpFolderPathFile.mkdirs();
//		} else {
//			// 如果已经有文件，先删除所有文件
//			String[] filesInFolder = tmpFolderPathFile.list();
//			if (filesInFolder.length > 0) {
//				for (int i = 0; i < filesInFolder.length; i++) {
//					File file = new File(folder + "/" + filesInFolder[i]);
//					file.delete();
//				}
//			}
//		}
//		if (lis.size() > 0) {
//			for (int i = 0; i < lis.size(); i++) {
//				String extendname = lis.get(i).getExtendname();
//				byte[] modual = lis.get(i).getModual();
//				String filename = folder + "/" + type + i + "." + extendname;
//				File file = new File(filename);
//				// 建立输出字节流
//				FileOutputStream fos = new FileOutputStream(file);
//				// 用FileOutputStream 的write方法写入字节数组
//				fos.write(modual);
//				// 为了节省IO流的开销，需要关闭
//				fos.close();
//				if (extendname.equals("pdf")) {
//					list.add(filename);
//				} else if (extendname.equals("jpg") || extendname.equals("png")) {
//					jpgToPdf(new File(folder + "/" + type + i + ".pdf"), file);
//					list.add(folder + "/" + type + i + ".pdf");
//				} else if (extendname.equals("doc")
//						|| extendname.equals("docx")) {
//					// word再转pdf
//					WordUtil.wordToPdf(filename, folder + "/" + type + i
//							+ ".pdf");
//					list.add(folder + "/" + type + i + ".pdf");
//				}
//			}
//		}
//		return list;
//	}
//
	/**
	 * 根据type下载附件----PmsAttachment表
	 *
	 * @param type
	 * @throws Exception
	 */
	public List<String> getuploadFileAttachment( List<String> list,String projectbaseid,String tmpPDFFile,String type) throws Exception {
		String hql = "select t from PmsAttachment t where t.sourceid=?0 and t.category=?1 order by createtime ";
		List<PmsAttachment> lis = this.findByHql(hql, projectbaseid,type);
		String folder = tmpPDFFile + "/" + type.replace("/","-").replace("\\","-");
		File tmpFolderPathFile = new File(folder);
		if (!tmpFolderPathFile.exists()) {
			tmpFolderPathFile.mkdirs();
		} else {
			// 如果已经有文件，先删除所有文件
			String[] filesInFolder = tmpFolderPathFile.list();
			if (filesInFolder.length > 0) {
				for (int i = 0; i < filesInFolder.length; i++) {
					File file = new File(folder + "/" + filesInFolder[i]);
					file.delete();
				}
			}
		}
		//如果是true，会报io的错误，导致附件拼不上，奇怪
		dbHelper.setAutoCloseConneciton(false);
		if (lis.size() > 0) {
			for (int i = 0; i < lis.size(); i++) {
				String name = lis.get(i).getName();
				String extendname=lis.get(i).getExtensions().toLowerCase();
                String localfilepath=lis.get(i).getLocalfilepath();
				String filename = folder + "/" + "affix"+i+"."+extendname;
				File file = new File(filename);
                if(Util.isEoN(localfilepath)){
					DmdbBlob b=null;
                    b = getAffixContent(lis.get(i).getId().toString());
					int len = 0;
					BufferedInputStream sbs = new BufferedInputStream(b.getBinaryStream());
					byte[] buffer = new byte[1024];
					// 建立输出字节流
					FileOutputStream fos = new FileOutputStream(file);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					// 用FileOutputStream 的write方法写入字节数组
					long crr1 = System.currentTimeMillis();
					while((len = sbs.read(buffer))!=-1) {
						bos.write(buffer,0,len);
					}
					System.out.println("--->"+(System.currentTimeMillis()-crr1));
					// 为了节省IO流的开销，需要关闭
					bos.close();
					fos.close();
					sbs.close();
                }else{
					// 建立输出字节流
					FileOutputStream fos = new FileOutputStream(file);
					BufferedOutputStream byteOutputStream = new BufferedOutputStream(fos);
                    try {
                        FileInfo fileInfo = fileStorage.read(localfilepath,byteOutputStream);
						FileUtils.copyFile(new File(localfilepath),byteOutputStream);
                    } catch (IOException e) {
                    	//因为这里被抓住了，所以就算出错了也只是缺失该附件，而不是直接前台报错
						//本地环境下，上传路径基本为D://  因为存在 :  导致后面的正则表达式匹配后通不过，所以抛出文件找不到的错误
                        e.printStackTrace();
                    }
                    //b = org.hibernate.Hibernate.createBlob(byteOutputStream.getBytes());
					// 为了节省IO流的开销，需要关闭
					byteOutputStream.close();
					fos.close();
                }
				if (extendname.endsWith("pdf")) {
					list.add(filename);
				}if (extendname.endsWith("doc")
						|| extendname.endsWith("docx")) {
					String pdfName=folder + "/" +"affix"+i+ ".pdf";
					// word再转pdf
					//WordUtil.wordToPdf(filename, pdfName);
					//Word2PdfSuwellUtil.doc2pdf(filename,pdfName);
					SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
					String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
					WordUtil.wordToPdfByType(filename,pdfName,toPdfType);
					list.add(pdfName);
				}if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
					String pdfName=folder + "/" +"affix"+i+ ".pdf";
					File pdf = new File(pdfName);
					WordUtil.imgToPdf(pdf,file);
                    list.add(pdfName);
				}
			}
		}
		dbHelper.setAutoCloseConneciton(true);
		dbHelper.closeConn();
		return list;
	}
//
//	/**
//	 * 根据type下载附件----PmsAttachment表--及memo，用于验收其他附件
//	 *
//	 * @param type
//	 * @throws Exception
//	 */
//	public List<String> getuploadFileAttachmentAndmemo( List<String> list,String projectbaseid,String tmpPDFFile,String type,String memo) throws Exception {
//		String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=? and t.memo=? order by createtime ";
//		List<PmsAttachment> lis = this.findByHql(hql, projectbaseid,type,memo);
//		String folder = tmpPDFFile + "/" + type;
//		File tmpFolderPathFile = new File(folder);
//		if (!tmpFolderPathFile.exists()) {
//			tmpFolderPathFile.mkdirs();
//		} else {
//			// 如果已经有文件，先删除所有文件
//			String[] filesInFolder = tmpFolderPathFile.list();
//			if (filesInFolder.length > 0) {
//				for (int i = 0; i < filesInFolder.length; i++) {
//					File file = new File(folder + "/" + filesInFolder[i]);
//					file.delete();
//				}
//			}
//		}
//		if (lis.size() > 0) {
//			for (int i = 0; i < lis.size(); i++) {
//				String name = lis.get(i).getName();
//				String extendname=lis.get(i).getExtensions().toLowerCase();;
//				Blob b = getAffixEnclosureBlob(lis.get(i).getId().toString());
//				//byte[] modual = b.getBytes(1L, (int) b.length());
//				int len = 0;
//				BufferedInputStream sbs = new BufferedInputStream(b.getBinaryStream());
//				byte[] buffer = new byte[1024];
//				String filename = folder + "/" + "affix"+i;
//				File file = new File(filename);
//				// 建立输出字节流
//				FileOutputStream fos = new FileOutputStream(file);
//				BufferedOutputStream bos = new BufferedOutputStream(fos);
//				// 用FileOutputStream 的write方法写入字节数组
//				long crr1 = System.currentTimeMillis();
//				while((len = sbs.read(buffer))!=-1) {
//					bos.write(buffer,0,len);
//				}
//				System.out.println("--->"+(System.currentTimeMillis()-crr1));
//				// 为了节省IO流的开销，需要关闭
//				bos.close();
//				fos.close();
//				sbs.close();
//				if (extendname.endsWith("pdf")) {
//					list.add(filename);
//				}if (extendname.endsWith("doc")
//						|| extendname.endsWith("docx")) {
//					String pdfName=folder + "/" +"affix"+i+ ".pdf";
//					// word再转pdf
//					WordUtil.wordToPdf(filename, pdfName);
//					list.add(pdfName);
//				}if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
//					String pdfName=folder + "/" +"affix"+i+ ".pdf";
//					File pdf = new File(pdfName);
//					WordUtil.imgToPdf(pdf,file);
//					list.add(pdfName);
//				}
//			}
//		}
//		return list;
//	}
//
//
//    /**
//     * 根据type下载附件----PmsAttachment表--批量和上面那个有点区别，上传是pdf的不能直接打开
//     *
//     * @param type
//     * @throws Exception
//     */
//    public List<String> getuploadFileAttachmentForpl( List<String> list,String projectbaseid,String tmpPDFFile,String type) throws Exception {
//        String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=? order by createtime ";
//        List<PmsAttachment> lis = this.findByHql(hql, projectbaseid,type);
//        String folder = tmpPDFFile + "/" + type;
//        File tmpFolderPathFile = new File(folder);
//        if (!tmpFolderPathFile.exists()) {
//            tmpFolderPathFile.mkdirs();
//        } else {
//            // 如果已经有文件，先删除所有文件
//            String[] filesInFolder = tmpFolderPathFile.list();
//            if (filesInFolder.length > 0) {
//                for (int i = 0; i < filesInFolder.length; i++) {
//                    File file = new File(folder + "/" + filesInFolder[i]);
//                    file.delete();
//                }
//            }
//        }
//        if (lis.size() > 0) {
//            for (int i = 0; i < lis.size(); i++) {
//                String name = lis.get(i).getName();
//                String extendname=lis.get(i).getExtensions().toLowerCase();;
//				DmdbBlob b = getAffixContent(lis.get(i).getId().toString());
//                byte[] modual = b.getBytes(1L, (int) b.length());
//                String filename = folder + "/" + "affix"+i+ "." + extendname;
//                File file = new File(filename);
//                // 建立输出字节流
//                FileOutputStream fos = new FileOutputStream(file);
//                // 用FileOutputStream 的write方法写入字节数组
//                fos.write(modual);
//                // 为了节省IO流的开销，需要关闭
//                fos.close();
//                if (extendname.endsWith("pdf")) {
//                    list.add(filename);
//                }if (extendname.endsWith("doc")
//                        || extendname.endsWith("docx")) {
//                    String pdfName=folder + "/" +"affix"+i+ ".pdf";
//                    // word再转pdf
//                    WordUtil.wordToPdf(filename, pdfName);
//                    list.add(pdfName);
//                }if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
//                    String pdfName=folder + "/" +"affix"+i+ ".pdf";
//                    File pdf = new File(pdfName);
//                    WordUtil.imgToPdf(pdf,file);
//                    list.add(pdfName);
//                }
//            }
//        }
//        return list;
//    }
//
//
//	/**
//	 * 根据type下载附件----自然基金
//	 *
//	 * @param type
//	 * @throws Exception
//	 */
//	public List<String> getuploadFileAttachmentForpl＿zrjj( List<String> list,String projectbaseid,String tmpPDFFile,String type) throws Exception {
//		String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=? order by createtime ";
//		List<PmsAttachment> lis = this.findByHql(hql, projectbaseid,type);
//		String folder = tmpPDFFile ;
//		File tmpFolderPathFile = new File(tmpPDFFile);
//		if (!tmpFolderPathFile.exists()) {
//			tmpFolderPathFile.mkdirs();
//		} else {
//			// 如果已经有文件，先删除所有文件
//			String[] filesInFolder = tmpFolderPathFile.list();
//			if (filesInFolder.length > 0) {
//				for (int i = 0; i < filesInFolder.length; i++) {
//					File file = new File(folder + "/" + filesInFolder[i]);
//					file.delete();
//				}
//			}
//		}
//		if (lis.size() > 0) {
//			for (int i = 0; i < lis.size(); i++) {
//				String name = lis.get(i).getName();
//				String extendname=lis.get(i).getExtensions().toLowerCase();;
//				DmdbBlob b = getAffixContent(lis.get(i).getId().toString());
//				byte[] modual = b.getBytes(1L, (int) b.length());
//				String filename = folder + "/" + lis.get(i).getName();
//				File file = new File(filename);
//				// 建立输出字节流
//				FileOutputStream fos = new FileOutputStream(file);
//				// 用FileOutputStream 的write方法写入字节数组
//				fos.write(modual);
//				// 为了节省IO流的开销，需要关闭
//				fos.close();
//				File file2 = new File(folder+"/"+lis.get(i).getId()+".pdf");
//				WordUtil.wordToPdf(file.getAbsolutePath(),file2.getAbsolutePath());
//				list.add(filename);
//			}
//		}
//		return list;
//	}
//
//    /**
//     * 根据type下载附件----PmsAttachment表---批量附件用
//     *
//     * @param type
//     * @throws Exception
//     */
//    public List<String> getuploadFileAttachmentForplfj( List<String> list,String projectbaseid,String applicationno,String tmpPDFFile,String type) throws Exception {
//        String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=? order by createtime ";
//        List<PmsAttachment> lis = this.findByHql(hql, projectbaseid,type);
//        //String folder = tmpPDFFile + "/" + type;
//        String folder = tmpPDFFile;
//        /*File tmpFolderPathFile = new File(folder);
//        if (!tmpFolderPathFile.exists()) {
//            tmpFolderPathFile.mkdirs();
//        } else {
//            // 如果已经有文件，先删除所有文件
//            String[] filesInFolder = tmpFolderPathFile.list();
//            if (filesInFolder.length > 0) {
//                for (int i = 0; i < filesInFolder.length; i++) {
//                    File file = new File(folder + "/" + filesInFolder[i]);
//                    file.delete();
//                }
//            }
//        }*/
//        if (lis.size() > 0) {
//            for (int i = 0; i < lis.size(); i++) {
//                String name = lis.get(i).getName();
//                String extendname=lis.get(i).getExtensions().toLowerCase();;
//				DmdbBlob b = getAffixContent(lis.get(i).getId().toString());
//                byte[] modual = b.getBytes(1L, (int) b.length());
//                String filename = folder + "/" +applicationno+"-"+type+i+"."+extendname;
//                File file = new File(filename);
//                // 建立输出字节流
//                FileOutputStream fos = new FileOutputStream(file);
//                // 用FileOutputStream 的write方法写入字节数组
//                fos.write(modual);
//                // 为了节省IO流的开销，需要关闭
//                fos.close();
//                list.add(filename);
//            }
//        }
//        return list;
//    }
//
//
//
//    /**
//     * 根据type下载附件----PmsAttachment表---补正附件，sourceid不一样，category一样，传名字进来
//     *
//     * @param type
//     * @throws Exception
//     */
//    public List<String> getuploadFileAttachmentcg( List<String> list,String projectbaseid,String tmpPDFFile,String type,String affixname) throws Exception {
//        String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=? order by createtime ";
//        List<PmsAttachment> lis = this.findByHql(hql, projectbaseid,type);
//        String folder = tmpPDFFile + "/" + type;
//        File tmpFolderPathFile = new File(folder);
//        if (!tmpFolderPathFile.exists()) {
//            tmpFolderPathFile.mkdirs();
//        } else {
//            // 如果已经有文件，先删除所有文件
//            /*String[] filesInFolder = tmpFolderPathFile.list();
//            if (filesInFolder.length > 0) {
//                for (int i = 0; i < filesInFolder.length; i++) {
//                    File file = new File(folder + "/" + filesInFolder[i]);
//                    file.delete();
//                }
//            }*/
//        }
//        if (lis.size() > 0) {
//            for (int i = 0; i < lis.size(); i++) {
//                String name = lis.get(i).getName();
//                String extendname=lis.get(i).getExtensions().toLowerCase();;
//				DmdbBlob b = getAffixContent(lis.get(i).getId().toString());
//                byte[] modual = b.getBytes(1L, (int) b.length());
//                String filename = folder + "/" + affixname;
//                if (extendname.endsWith("pdf")) {
//                    filename=filename+".pdf";
//                }
//                File file = new File(filename);
//                // 建立输出字节流
//                FileOutputStream fos = new FileOutputStream(file);
//                // 用FileOutputStream 的write方法写入字节数组
//                fos.write(modual);
//                // 为了节省IO流的开销，需要关闭
//                fos.close();
//                if (extendname.endsWith("pdf")) {
//                    list.add(filename);
//                }if (extendname.endsWith("doc")
//                        || extendname.endsWith("docx")) {
//                    String pdfName=folder + "/" +affixname+ ".pdf";
//                    // word再转pdf
//                    WordUtil.wordToPdf(filename, pdfName);
//                    list.add(pdfName);
//                }if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
//                    String pdfName=folder + "/" +affixname+ ".pdf";
//                    File pdf = new File(pdfName);
//                    WordUtil.imgToPdf(pdf,file);
//                    list.add(pdfName);
//                }
//            }
//        }
//        return list;
//    }
//    /**
//     * 根据type下载附件----PmsAttachment表--一个项目关联其他表根据其他表的id存附件--如单位表
//     *
//     * @param type
//     * @throws Exception
//     */
//    public List<String> getuploadFileAttachmentByothertable( List<String> list,String projectbaseid,String tmpPDFFile,String type,Integer seq) throws Exception {
//        String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=? order by createtime ";
//        List<PmsAttachment> lis = this.findByHql(hql, projectbaseid,type);
//        String folder = tmpPDFFile + "/" + type+seq;
//        File tmpFolderPathFile = new File(folder);
//        if (!tmpFolderPathFile.exists()) {
//            tmpFolderPathFile.mkdirs();
//        } else {
//            // 如果已经有文件，先删除所有文件
//            String[] filesInFolder = tmpFolderPathFile.list();
//            if (filesInFolder.length > 0) {
//                for (int i = 0; i < filesInFolder.length; i++) {
//                    File file = new File(folder + "/" + filesInFolder[i]);
//                    file.delete();
//                }
//            }
//        }
//        if (lis.size() > 0) {
//            for (int i = 0; i < lis.size(); i++) {
//                String name = lis.get(i).getName();
//                String extendname=lis.get(i).getExtensions().toLowerCase();;
//				DmdbBlob b = getAffixContent(lis.get(i).getId().toString());
//                byte[] modual = b.getBytes(1L, (int) b.length());
//                String filename = folder + "/" + "affix"+i;
//                File file = new File(filename);
//                // 建立输出字节流
//                FileOutputStream fos = new FileOutputStream(file);
//                // 用FileOutputStream 的write方法写入字节数组
//                fos.write(modual);
//                // 为了节省IO流的开销，需要关闭
//                fos.close();
//                if (extendname.endsWith("pdf")) {
//                    list.add(filename);
//                }if (extendname.endsWith("doc")
//                        || extendname.endsWith("docx")) {
//                    String pdfName=folder + "/" +"affix"+i+ ".pdf";
//                    // word再转pdf
//                    WordUtil.wordToPdf(filename, pdfName);
//                    list.add(pdfName);
//                }if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
//                    String pdfName=folder + "/" +"affix"+i+ ".pdf";
//                    File pdf = new File(pdfName);
//                    WordUtil.imgToPdf(pdf,file);
//                    list.add(pdfName);
//                }
//            }
//        }
//        return list;
//    }
//
//
//	/**
//	 * 将图片转为pdf
//	 *
//	 * @param pdfFile
//	 * @param imgFile
//	 * @throws Exception
//	 */
//	public void jpgToPdf(File pdfFile, File imgFile) throws Exception {
//		// 文件转img
//
//		if (!pdfFile.exists()) {
//			pdfFile.createNewFile();
//		} else {
//			pdfFile.delete();
//		}
//		InputStream is = new FileInputStream(imgFile);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		for (int i; (i = is.read()) != -1;) {
//			baos.write(i);
//		}
//		baos.flush();
//
//		// 取得图像的宽和高。
//		Image img = Image.getInstance(baos.toByteArray());
//		float width = img.getWidth();
//		float height = img.getHeight();
//		img.setAbsolutePosition(0.0F, 0.0F);// 取消偏移
//
//		// img转pdf
//		Document doc = new Document(new Rectangle(width, height));
//		PdfWriter pw = PdfWriter
//				.getInstance(doc, new FileOutputStream(pdfFile));
//		doc.open();
//		doc.add(img);
//		// 释放资源
//		System.out.println(doc.newPage());
//		pw.flush();
//		baos.close();
//		doc.close();
//		pw.close();
//	}
//
//
//	public boolean importByExcelFile(File excelFile, String dataTable) throws BiffException, IOException {
//		// TODO Auto-generated method stub
//		Workbook rwb = null;
//        rwb = Workbook.getWorkbook(excelFile);
//        // 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...
//        Sheet sheet = rwb.getSheets()[0];
//        int rsColumns = sheet.getColumns();// 列数
//        int rsRows = sheet.getRows();// 行数
//        String simNumber = "" ;//每个单元格中的数据
//        String str="";//拼接要插入的列
//        // 第一列为列头
//        for (int j = 0; j <rsColumns; j++) {
//                Cell cell = sheet.getCell(j, 0);
//                simNumber = cell.getContents();
//                if(j==rsColumns-1){
//                    str +=  simNumber  ;
//                }else{
//                    str +=  simNumber+",";
//                }
//
//            }
//        for (int i = 1; i < rsRows; i++) {
//            String sql = "insert into "+dataTable+" ("+str+") values(sys_guid(),";//拼接sql
//            String uuid = "";
//            String uname = "";
//            for (int j = 0; j < rsColumns; j++) {
//                Cell cell = sheet.getCell(j, i);
//                simNumber = cell.getContents();
//                if(j==0){
//                	uuid = simNumber;
//                }
//                if(j==1){
//                	uname = simNumber;
//                }
//                if(j==rsColumns-1){
//                    sql += "'"+ simNumber.trim()+"'" ;
//                }else{
//                    sql +="'"+ simNumber.trim()+"',";
//                }
//
//            }
//            sql += " )";
//            System.out.println(sql);
//            try {
//            	String isExistsql = "select id from ? where name =? and cellphone =?";
//            	List lists = dbHelper.getRows(isExistsql, new Object[]{dataTable,uuid,uname});
//            	if(lists!=null&&lists.size()==0){
//            		sql = sql.replace(str, "id,"+str);
//            		dbHelper.runSql(sql);
//            	}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return false;
//			}
//        }
//        return true;
//	}
//
//
//	/**
//	 * 附件入库 带水印
//	 * 附件内容入单独库，业务库只记录附件基本信息
//	 * @author boboo
//	 * @param multipartRequest
//	 * @throws IOException
//	 * @throws DocumentException
//	 */
//	public void fileInputToDatabaseWithCategory(MultipartHttpServletRequest multipartRequest, String sourceid, String userid, String category, String memo) throws IOException, DocumentException{
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//        Iterator<String> fileIterator = multipartRequest.getFileNames();
//		String origid = multipartRequest.getParameter("origid");
//
//        int ii = 1;
//        while (fileIterator.hasNext()) {
//
//
//            String fileKey = fileIterator.next();
//            MultipartFile multipartFile = fileMap.get(fileKey);
////            PdfReader pdfReader = new PdfReader(multipartFile.getInputStream());
////            PdfStamper pdfStamper = new PdfStamper(pdfReader
////            		, new FileOutputStream(
////            				"d://itext-demo22.pdf"));
//            if (multipartFile.getSize() != 0L) {
//            	//获取上传文件名字
//            	String affixName=multipartFile.getOriginalFilename();
//            	//获取文件类型
//            	String affixType=affixName.substring(affixName.lastIndexOf(".")+1,affixName.length());
//
//            	String uuid = Util.NewGuid();
//            	PmsAttachment pmsAttachment = new PmsAttachment();
//            	pmsAttachment.setId(uuid);
//            	pmsAttachment.setCreatetime(new Date());
//            	pmsAttachment.setCreator(userid);
//            	pmsAttachment.setFilesize(multipartFile.getSize()+"");
//            	pmsAttachment.setName(multipartFile.getOriginalFilename());
//            	pmsAttachment.setExtensions(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1));
//            	pmsAttachment.setSourceid(sourceid);
//            	pmsAttachment.setSeq(ii);
//            	pmsAttachment.setCategory(category);
//				pmsAttachment.setMemo(memo);
//				pmsAttachment.setOrigid(origid);
//            	ii++;
//            	pmsAttachmentService.merge(pmsAttachment);
//
//            	InputStream in = new ByteArrayInputStream(multipartFile.getBytes());
//            	String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
//            			+ " values('"+uuid+"','"+sourceid+"',empty_blob())";
//            	String selsql = "select affixcontent from pms_allattachments where id = '"+uuid+"' for update";
//
//            	try {
//					dbHelperAffixNew.insertBlobSql(insql,selsql,new Object[]{},in);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					log.error("附件插入出错，请检查"+e.getMessage());
//					e.printStackTrace();
//				}
////            	//上传文件是pdf的加水印
////            	if("pdf".equals(affixType)){
////            		// 将生成好的pdf文件转byte数组
////            		byte[] bytes = multipartFile.getBytes();
////            		// 如果水印名称waterMarkName不为空字符串，说明需要添加水印，并替换以前生成的pdf文件
////            		String tmpPDFFile="D://tmpPDFFile.pdf";
////            			File file = new File(tmpPDFFile);
////            			FileOutputStream outPutStream = new FileOutputStream(file);
////            			OperationPdfUtil.setWatermark(bytes, outPutStream, "项目管理系统",0.6f);
////            			bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
////            			InputStream in=new FileInputStream(file);
////            			String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
////                    			+ " values('"+uuid+"','"+sourceid+"',empty_blob())";
////                    	String selsql = "select affixcontent from pms_allattachments where id = '"+uuid+"' for update";
////                    	try {
////        					dbHelperAffix.insertBlobSql(insql,selsql,new Object[]{},in);
////        				} catch (Exception e) {
////        					// TODO Auto-generated catch block
////        					log.error("附件插入出错，请检查"+e.getMessage());
////        					e.printStackTrace();
////        				}
////            	}else if("jpg".equals(affixType)||"png".equals(affixType)){
////            		// 将生成好的图片文件转byte数组
////            		byte[] bytes = multipartFile.getBytes();
////            		String tmpPDFFile="D://tmpImgFile."+affixType;
////            		File file = new File(tmpPDFFile);
////            		//建立输出字节流
////            		FileOutputStream fos = new FileOutputStream(file);
////            		//用FileOutputStream 的write方法写入字节数组
////            		fos.write(bytes);
////            		//为了节省IO流的开销，需要关闭
////            		fos.close();
////            		OperationPdfUtil.mark(tmpPDFFile, "d://tmpoutImgFile."+affixType, Color.GRAY, "项目管理系统");
////            		InputStream in=new FileInputStream(new File("d://tmpoutImgFile."+affixType));
////        			String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
////                			+ " values('"+uuid+"','"+sourceid+"',empty_blob())";
////                	String selsql = "select affixcontent from pms_allattachments where id = '"+uuid+"' for update";
////                	try {
////    					dbHelperAffix.insertBlobSql(insql,selsql,new Object[]{},in);
////    				} catch (Exception e) {
////    					log.error("附件插入出错，请检查"+e.getMessage());
////    					e.printStackTrace();
////    				}
////            	}else if("doc".equals(affixType)||"docx".equals(affixType)){
////            		String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
////                			+ " values('"+uuid+"','"+sourceid+"',empty_blob())";
////                	String selsql = "select affixcontent from pms_allattachments where id = '"+uuid+"' for update";
////                	try {
////    					dbHelperAffix.insertBlobSql(insql,selsql,new Object[]{},multipartFile.getInputStream());
////    				} catch (Exception e) {
////    					// TODO Auto-generated catch block
////    					log.error("附件插入出错，请检查"+e.getMessage());
////    					e.printStackTrace();
////    				}
////            	}
//
//            }
//        }
//	}
//
//	//附件存本地磁盘，业务库记录附件基本信息位置
//	public void fileInputToDatabaseWithCategorylocal(MultipartHttpServletRequest multipartRequest, String sourceid, String userid, String category, String memo) throws IOException, DocumentException{
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		Iterator<String> fileIterator = multipartRequest.getFileNames();
//		String origid = multipartRequest.getParameter("origid");
//
//		int ii = 1;
//		while (fileIterator.hasNext()) {
//			String fileKey = fileIterator.next();
//			MultipartFile multipartFile = fileMap.get(fileKey);
//			if (multipartFile.getSize() != 0L) {
//				//获取上传文件名字
//				String affixName=multipartFile.getOriginalFilename();
//				//获取文件类型
//				String affixType=affixName.substring(affixName.lastIndexOf(".")+1,affixName.length());
//
//				String uuid = Util.NewGuid();
//				PmsAttachment pmsAttachment = new PmsAttachment();
//				pmsAttachment.setId(uuid);
//				pmsAttachment.setCreatetime(new Date());
//				pmsAttachment.setCreator(userid);
//				pmsAttachment.setFilesize(multipartFile.getSize()+"");
//				pmsAttachment.setName(multipartFile.getOriginalFilename());
//				pmsAttachment.setExtensions(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1));
//				pmsAttachment.setSourceid(sourceid);
//				pmsAttachment.setSeq(ii);
//				pmsAttachment.setCategory(category);
//				pmsAttachment.setMemo(memo);
//				pmsAttachment.setOrigid(origid);
//				FileInfo fileInfo = fileStorage.write(localFilePath + Util.formatDate(new Date(), "yyyy/MM/dd")+File.separator+sourceid+File.separator+uuid+File.separator+multipartFile.getOriginalFilename(),multipartFile.getInputStream());
//				//pmsAttachment.setMemo(memo);
//				pmsAttachment.setLocalfilepath(diskFileStorageProperties.getRootpath() + fileInfo.getName());
//				ii++;
//				pmsAttachmentService.merge(pmsAttachment);
//
//			}
//		}
//	}
//
//	/**
//	 * 附件入库 带水印
//	 * 附件内容入单独库，业务库只记录附件基本信息
//	 * @author boboo
//	 * @param multipartRequest
//	 * @throws IOException
//	 * @throws DocumentException
//	 */
//	public void fileInputToDatabaseWithCategoryNoUser(MultipartHttpServletRequest multipartRequest, String sourceid, String category, String memo) throws IOException, DocumentException{
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		Iterator<String> fileIterator = multipartRequest.getFileNames();
//		int ii = 1;
//		while (fileIterator.hasNext()) {
//			String fileKey = fileIterator.next();
//			MultipartFile multipartFile = fileMap.get(fileKey);
////            PdfReader pdfReader = new PdfReader(multipartFile.getInputStream());
////            PdfStamper pdfStamper = new PdfStamper(pdfReader
////            		, new FileOutputStream(
////            				"d://itext-demo22.pdf"));
//			if (multipartFile.getSize() != 0L) {
//				//获取上传文件名字
//				String affixName=multipartFile.getOriginalFilename();
//				//获取文件类型
//				String affixType=affixName.substring(affixName.lastIndexOf(".")+1,affixName.length());
//
//				String uuid = Util.NewGuid();
//				PmsAttachment pmsAttachment = new PmsAttachment();
//				pmsAttachment.setId(uuid);
//				pmsAttachment.setCreatetime(new Date());
//				pmsAttachment.setFilesize(multipartFile.getSize()+"");
//				pmsAttachment.setName(multipartFile.getOriginalFilename());
//				pmsAttachment.setExtensions(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1));
//				pmsAttachment.setSourceid(sourceid);
//				pmsAttachment.setSeq(ii);
//				pmsAttachment.setCategory(category);
//				pmsAttachment.setMemo(memo);
//				ii++;
//				pmsAttachmentService.merge(pmsAttachment);
//
//				InputStream in = new ByteArrayInputStream(multipartFile.getBytes());
//				String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
//						+ " values('"+uuid+"','"+sourceid+"',empty_blob())";
//				String selsql = "select affixcontent from pms_allattachments where id = '"+uuid+"' for update";
//
//				try {
//					dbHelperAffixNew.insertBlobSql(insql,selsql,new Object[]{},in);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					log.error("附件插入出错，请检查"+e.getMessage());
//					e.printStackTrace();
//				}
////            	//上传文件是pdf的加水印
////            	if("pdf".equals(affixType)){
////            		// 将生成好的pdf文件转byte数组
////            		byte[] bytes = multipartFile.getBytes();
////            		// 如果水印名称waterMarkName不为空字符串，说明需要添加水印，并替换以前生成的pdf文件
////            		String tmpPDFFile="D://tmpPDFFile.pdf";
////            			File file = new File(tmpPDFFile);
////            			FileOutputStream outPutStream = new FileOutputStream(file);
////            			OperationPdfUtil.setWatermark(bytes, outPutStream, "项目管理系统",0.6f);
////            			bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
////            			InputStream in=new FileInputStream(file);
////            			String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
////                    			+ " values('"+uuid+"','"+sourceid+"',empty_blob())";
////                    	String selsql = "select affixcontent from pms_allattachments where id = '"+uuid+"' for update";
////                    	try {
////        					dbHelperAffix.insertBlobSql(insql,selsql,new Object[]{},in);
////        				} catch (Exception e) {
////        					// TODO Auto-generated catch block
////        					log.error("附件插入出错，请检查"+e.getMessage());
////        					e.printStackTrace();
////        				}
////            	}else if("jpg".equals(affixType)||"png".equals(affixType)){
////            		// 将生成好的图片文件转byte数组
////            		byte[] bytes = multipartFile.getBytes();
////            		String tmpPDFFile="D://tmpImgFile."+affixType;
////            		File file = new File(tmpPDFFile);
////            		//建立输出字节流
////            		FileOutputStream fos = new FileOutputStream(file);
////            		//用FileOutputStream 的write方法写入字节数组
////            		fos.write(bytes);
////            		//为了节省IO流的开销，需要关闭
////            		fos.close();
////            		OperationPdfUtil.mark(tmpPDFFile, "d://tmpoutImgFile."+affixType, Color.GRAY, "项目管理系统");
////            		InputStream in=new FileInputStream(new File("d://tmpoutImgFile."+affixType));
////        			String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
////                			+ " values('"+uuid+"','"+sourceid+"',empty_blob())";
////                	String selsql = "select affixcontent from pms_allattachments where id = '"+uuid+"' for update";
////                	try {
////    					dbHelperAffix.insertBlobSql(insql,selsql,new Object[]{},in);
////    				} catch (Exception e) {
////    					log.error("附件插入出错，请检查"+e.getMessage());
////    					e.printStackTrace();
////    				}
////            	}else if("doc".equals(affixType)||"docx".equals(affixType)){
////            		String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
////                			+ " values('"+uuid+"','"+sourceid+"',empty_blob())";
////                	String selsql = "select affixcontent from pms_allattachments where id = '"+uuid+"' for update";
////                	try {
////    					dbHelperAffix.insertBlobSql(insql,selsql,new Object[]{},multipartFile.getInputStream());
////    				} catch (Exception e) {
////    					// TODO Auto-generated catch block
////    					log.error("附件插入出错，请检查"+e.getMessage());
////    					e.printStackTrace();
////    				}
////            	}
//
//			}
//		}
//	}
//	/**
//	 * 附件入库
//	 * 附件内容入单独库，业务库只记录附件基本信息
//	 * @param multipartRequest
//	 * @throws IOException
//	 * @throws DocumentException
//	 */
//	public void fileInputToDatabaseWithCategory_bygsth(MultipartHttpServletRequest multipartRequest, String sourceid, String userid, String category) throws IOException, DocumentException{
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		Iterator<String> fileIterator = multipartRequest.getFileNames();
//		int ii = 1;
//		while (fileIterator.hasNext()) {
//			String fileKey = fileIterator.next();
//			MultipartFile multipartFile = fileMap.get(fileKey);
////            PdfReader pdfReader = new PdfReader(multipartFile.getInputStream());
////            PdfStamper pdfStamper = new PdfStamper(pdfReader
////            		, new FileOutputStream(
////            				"d://itext-demo22.pdf"));
//			if (multipartFile.getSize() != 0L) {
//				//获取上传文件名字
//				String affixName=multipartFile.getOriginalFilename();
//				//获取文件类型
//				String affixType=affixName.substring(affixName.lastIndexOf(".")+1,affixName.length());
//
//				String uuid = Util.NewGuid();
//				//更新验收公示的附件id
//				String sql="update pms_acceptancenotice a set a.gsthfjid=? where a.projectbaseid=?";
//				try {
//					dbHelper.runSql(sql,new Object[]{uuid,sourceid});
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				PmsAttachment pmsAttachment = new PmsAttachment();
//				pmsAttachment.setId(uuid);
//				pmsAttachment.setCreatetime(new Date());
//				pmsAttachment.setCreator(userid);
//				pmsAttachment.setFilesize(multipartFile.getSize()+"");
//				pmsAttachment.setName(multipartFile.getOriginalFilename());
//				pmsAttachment.setExtensions(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1));
//				pmsAttachment.setSourceid(sourceid);
//				pmsAttachment.setSeq(ii);
//				pmsAttachment.setCategory(category);
//				ii++;
//				pmsAttachmentService.merge(pmsAttachment);
//
//				InputStream in = new ByteArrayInputStream(multipartFile.getBytes());
//				String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
//						+ " values('"+uuid+"','"+sourceid+"',empty_blob())";
//				String selsql = "select affixcontent from pms_allattachments where id = '"+uuid+"' for update";
//
//				try {
//					dbHelperAffixNew.insertBlobSql(insql,selsql,new Object[]{},in);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					log.error("附件插入出错，请检查"+e.getMessage());
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	/**
//	 * 【附件上传，附件id由前台传参】
//	 * @param multipartRequest
//	 * @param sourceid
//	 * @param userid
//	 * @param category
//	 * @throws IOException
//	 * @throws DocumentException
//     */
//	public void fileInputToDatabaseWithCategoryById(MultipartHttpServletRequest multipartRequest, String affixid, String sourceid, String userid, String category) throws IOException, DocumentException{
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		Iterator<String> fileIterator = multipartRequest.getFileNames();
//		int ii = 1;
//		while (fileIterator.hasNext()) {
//			String fileKey = fileIterator.next();
//			MultipartFile multipartFile = fileMap.get(fileKey);
////            PdfReader pdfReader = new PdfReader(multipartFile.getInputStream());
////            PdfStamper pdfStamper = new PdfStamper(pdfReader
////            		, new FileOutputStream(
////            				"d://itext-demo22.pdf"));
//			if (multipartFile.getSize() != 0L) {
//				//获取上传文件名字
//				String affixName=multipartFile.getOriginalFilename();
//				//获取文件类型
//				String affixType=affixName.substring(affixName.lastIndexOf(".")+1,affixName.length());
//
//				String uuid = affixid;
//				PmsAttachment pmsAttachment = new PmsAttachment();
//				pmsAttachment.setId(uuid);
//				pmsAttachment.setCreatetime(new Date());
//				pmsAttachment.setCreator(userid);
//				pmsAttachment.setFilesize(multipartFile.getSize()+"");
//				pmsAttachment.setName(multipartFile.getOriginalFilename());
//				pmsAttachment.setExtensions(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1));
//				pmsAttachment.setSourceid(sourceid);
//				pmsAttachment.setSeq(ii);
//				pmsAttachment.setCategory(category);
//				ii++;
//				pmsAttachmentService.merge(pmsAttachment);
//
//				InputStream in = new ByteArrayInputStream(multipartFile.getBytes());
//				String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
//						+ " values('"+uuid+"','"+sourceid+"',empty_blob())";
//				String selsql = "select affixcontent from pms_allattachments where id = '"+uuid+"' for update";
//
//				try {
//					dbHelperAffixNew.insertBlobSql(insql,selsql,new Object[]{},in);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					log.error("附件插入出错，请检查"+e.getMessage());
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	/**
//	 * 读取附件
//	 * @param sourceid
//	 * @return
//	 */
//	public List<Map> getAffixs(String sourceid){
//		String sql = "select name,createtime,sourceid,filesize,seq,memo,id from pms_attachment where sourceid = ?";
//		return dbHelper.getRows(sql, new Object[]{sourceid});
//	}
//
//	/**
//	 * 根据类型读取附件
//	 * @param sourceid
//	 * @return
//	 */
	public List<Map> getAffixsByCategory(String sourceid,String category){

		String sql = "select name,category,createtime,sourceid,filesize,seq,memo,id,localfilepath ,state,origid from pms_attachment where sourceid = ? and category = ? order by seq,createtime";
       List<Map> result=new ArrayList<Map>();
       if(category.equals("补正材料")){
           sql = "select t.name,t.category,t.createtime,t.sourceid,t.filesize,t.seq,t.memo,t.id,t.localfilepath ,t.state,t.origid from pms_attachment t inner join pms_xsnrjl u on u.id = t.sourceid  where u.jxid = ? order by t.seq,t.createtime";
           result=dbHelper.getRows(sql, new Object[]{sourceid});
       }else{
           result=dbHelper.getRows(sql, new Object[]{sourceid,category});
       }
           //logger.info("sql="+sql);
		 //List<Map> result=dbHelper.getRows(sql, new Object[]{sourceid,category});
		 //logger.info("result.size()="+result.size());
		return result;
	}
//
//	/**
//	 * 根据类型读取附件及大字段
//	 *
//	 * @param sourceid
//	 * @return
//	 */
	public List<Map> getAffixsAndBlobByCategory(String sourceid, String category, String clobTypeName ) {
		StringBuilder stringBuilder = new StringBuilder();
		Object[] objects =null;
		if(Util.isNullOrEmpty(category)){
			stringBuilder.append("select t.name,t.category,t.createtime,t.sourceid,t.filesize,t.seq,t.memo,t.id,t.href,t.allfilepage,c.source as clobTypeName,t.localfilepath,t.state,t.origid from pms_attachment t left join (select * from PMS_CLOB t where t.mainid = ? and t.COLUMNNAME = ? ) c on t.category = c.columnname  where t.sourceid = ?  order by t.seq");
			objects = new Object[]{sourceid, clobTypeName, sourceid};
		}else{
			stringBuilder.append("select t.name,t.category,t.createtime,t.sourceid,t.filesize,t.seq,t.memo,t.id,t.href,t.allfilepage,c.source as clobTypeName,t.localfilepath,t.state,t.origid from pms_attachment t left join (select * from PMS_CLOB t where t.mainid = ? and t.COLUMNNAME = ? ) c on t.category = c.columnname  where t.sourceid = ?  and t.category = ? order by t.seq");
			objects = new Object[]{sourceid, clobTypeName, sourceid, clobTypeName};
		}
		if (("补正材料").equals(category)) {
			stringBuilder.delete(0, stringBuilder.length());
			stringBuilder.append("select t.name,t.category,t.createtime,t.sourceid,t.filesize,t.seq,t.memo,t.id,href,t.allfilepage,t.localfilepath,t.localfilepath,t.state,t.origid from pms_attachment t inner join pms_xsnrjl u on u.id = t.sourceid  where u.jxid = ? order by t.seq");
			objects = new Object[]{sourceid};
		}
//        String sql = "select t.name,t.category,t.createtime,t.sourceid,t.filesize,t.seq,t.memo,t.id,t.href,t.allfilepage,c.source as clobTypeName from pms_attachment t left join (select * from PMS_CLOB t where t.SOURCEID = ? and t.COLUMNNAME = ? ) c on t.category = c.columnname  where t.sourceid = ?  and t.category = ? order by t.seq";
//        List<Map> result = new ArrayList<Map>();
//        if (category.equals("补正材料")) {
//            sql = "select t.name,t.category,t.createtime,t.sourceid,t.filesize,t.seq,t.memo,t.id,href,t.allfilepage from pms_attachment t inner join pms_xsnrjl u on u.id = t.sourceid  where u.jxid = ? order by t.seq";
//            result = dbHelper.getRows(sql, new Object[]{sourceid});
//        } else {
//            result = dbHelper.getRows(stringBuilder.toString(), objects);
//        }
		return this.dbHelper.getRows(stringBuilder.toString(), objects);
	}
//
//	/**
//	 * 根据读取附件
//	 * @param sourceid
//	 * @return
//	 */
	public List<Map> getAffixsByCategory(String sourceid){

		String sql = "select name,category,createtime,sourceid,filesize,seq,memo,id,localfilepath,state,origid from pms_attachment where sourceid = ? order by seq,createtime";
		//logger.info("sql="+sql);
		List<Map> result = this.dbHelper.getRows(sql, new Object[]{sourceid});
		//logger.info("result.size()="+result.size());
		return result;
	}
//
//
	/**
	 * 【黑龙江开发】 --- 给个id集合，返回对应的附件
	 * @param json
	 */
	public JSONArray getAffixlistsBySourceids(JSONObject json, HttpServletRequest request){
		JSONArray resArr = new JSONArray();
		String webPath = request.getContextPath();
		JSONArray sourceids = json.getJSONArray("sourceids");
		if(sourceids!=null&&sourceids.size()>0){
			for (int i = 0; i < sourceids.size(); i++) {
				JSONObject affixObj = sourceids.getJSONObject(i);
				String sourceid = affixObj.get("sourceid")+"";
				if(!Util.isEoN(sourceid)){
					List<Map> lists = null;
					lists = this.getAffixsByCategory(sourceid);
					JSONArray configArr = new JSONArray();
					JSONArray arrayurls = new JSONArray();
					if(lists!=null&&lists.size()>0){
						for (int j = 0; j < lists.size(); j++) {
							Map tempMap = lists.get(j);
							Map map = new HashMap();
							arrayurls.add(webPath + "/getAffixContent.do?type=view&id="+tempMap.get("id"));
							map.put("caption",tempMap.get("name"));
							map.put("width","120px");
							map.put("size",tempMap.get("filesize"));
							map.put("url",webPath + "/deleteAffixFileByCategory.do?sourceId="+sourceid+"&delete=id");
							map.put("key",tempMap.get("id"));
							map.put("frameClass","my-custom-frame-css");
							map.put("ftype","image");
							configArr.add(map);
						}
					}
					affixObj.put("data", lists);
					affixObj.put("downurl", arrayurls);
					affixObj.put("filesConfig", configArr);
					resArr.add(affixObj);
				}
			}
		}
		return resArr;
	}
//
//
//
//
//
//
//	/**
//	 * 根据type下载附件
//	 *
//	 * @throws Exception
//	 */
//	public List<String> getuploadFile(List<String> list,String sourceid,String category)
//			throws Exception {
//		String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=?";
//		List<PmsAttachment> lis = pmsAttachmentService.findByHql(hql, sourceid,category);
//		String folder =	"D:/PdfTmpFile_award/"+sourceid;
//
//		if (lis.size() > 0) {
//			for (int i = 0; i < lis.size(); i++) {
//				String name = lis.get(i).getName();
//				String extendname=lis.get(i).getExtensions().toLowerCase();;
//				DmdbBlob b = getAffixContent(lis.get(i).getId().toString());
//				byte[] modual = b.getBytes(1L, (int) b.length());
//				String filename = folder + "/" + "affix"+i;
//				File file = new File(filename);
//				// 建立输出字节流
//				FileOutputStream fos = new FileOutputStream(file);
//				// 用FileOutputStream 的write方法写入字节数组
//				fos.write(modual);
//				// 为了节省IO流的开销，需要关闭
//				fos.close();
//				if (extendname.endsWith("pdf")) {
//					list.add(filename);
//				}if (extendname.endsWith("doc")
//						|| extendname.endsWith("docx")) {
//					String pdfName=folder + "/" +"affix"+i+ ".pdf";
//					// word再转pdf
//					WordUtil.wordToPdf(filename, pdfName);
//					list.add(pdfName);
//				}if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
//					String pdfName=folder + "/" +"affix"+i+ ".pdf";
//					File pdf = new File(pdfName);
//					WordUtil.imgToPdf(pdf,file);
//				}
//			}
//		}
//		return list;
//
//	}
//
//    /**
//     * 根据type下载附件
//     *
//     * @throws Exception
//     */
//    public List<String> getuploadFileAndPath(List<String> list,String tmpFolderPath,String sourceid,String category)
//            throws Exception {
//        String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=?";
//        List<PmsAttachment> lis = pmsAttachmentService.findByHql(hql, sourceid,category);
//        String folder =	tmpFolderPath;
//
//        if (lis.size() > 0) {
//            for (int i = 0; i < lis.size(); i++) {
//                String name = lis.get(i).getName();
//                String extendname=lis.get(i).getExtensions().toLowerCase();;
//                DmdbBlob b = getAffixContent(lis.get(i).getId().toString());
//                byte[] modual = b.getBytes(1L, (int) b.length());
//                String filename = folder + "/" + "affix"+i;
//                File file = new File(filename);
//                // 建立输出字节流
//                FileOutputStream fos = new FileOutputStream(file);
//                // 用FileOutputStream 的write方法写入字节数组
//                fos.write(modual);
//                // 为了节省IO流的开销，需要关闭
//                fos.close();
//                if (extendname.endsWith("pdf")|| extendname.endsWith("PDF")) {
//                    list.add(filename);
//                }if (extendname.endsWith("doc")
//                        || extendname.endsWith("docx")) {
//                    String pdfName=folder + "/" +"affix"+i+ ".pdf";
//                    // word再转pdf
//                    WordUtil.wordToPdf(filename, pdfName);
//                    list.add(pdfName);
//                }if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
//                    String pdfName=folder + "/" +"affix"+i+ ".pdf";
//                    File pdf = new File(pdfName);
//                    WordUtil.imgToPdf(pdf,file);
//                }
//            }
//        }
//        return list;
//
//    }
//
//	/**
//	 * 根据type下载附件
//	 *
//	 * @throws Exception
//	 */
//	public List<String> getuploadFileAwardgs(List<String> list,String tmpFolderPath,String sourceid,String category)
//			throws Exception {
//		String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=?";
//		List<PmsAttachment> lis = pmsAttachmentService.findByHql(hql, sourceid,category);
//		String folder =	tmpFolderPath;
//
//		if (lis.size() > 0) {
//			for (int i = 0; i < lis.size(); i++) {
//				String name = lis.get(i).getName();
//				String filename="";
//				String extendname=lis.get(i).getExtensions().toLowerCase();;
//				DmdbBlob b = getAffixContent(lis.get(i).getId().toString());
//				byte[] modual = b.getBytes(1L, (int) b.length());
//				//if (extendname.endsWith("pdf")) {
//					//filename = folder + "/" + "affix"+i+category+ ".pdf";
//				//}else{
//				filename = folder + "/" + "affix"+i+category;
//				//}
//				File file = new File(filename);
//				// 建立输出字节流
//				FileOutputStream fos = new FileOutputStream(file);
//				// 用FileOutputStream 的write方法写入字节数组
//				fos.write(modual);
//				// 为了节省IO流的开销，需要关闭
//				fos.close();
//				if (extendname.endsWith("pdf")) {
//					list.add(filename);
//				}if (extendname.endsWith("doc")
//						|| extendname.endsWith("docx")) {
//					String pdfName=folder + "/" +"affix"+i+category+ ".pdf";
//					// word再转pdf
//					WordUtil.wordToPdf(filename, pdfName);
//					list.add(pdfName);
//				}if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
//					String pdfName=folder + "/" +"affix"+i+category+ ".pdf";
//					File pdf = new File(pdfName);
//					WordUtil.imgToPdf(pdf,file);
//					list.add(pdfName);
//				}
//			}
//		}
//		return list;
//
//	}
//
//	/**
//	 * 根据type下载附件
//	 *
//	 * @throws Exception
//	 */
//	public List<String> getuploadFileAwardgsGs(List<String> list,String tmpFolderPath,String sourceid,String category)
//			throws Exception {
//		String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid in " +
//				"(select id from cn.topcheer.pms.pojo.PmsXsnrjl p where jxid=? and p.jtnr like '%知情同意%')";
//		List<PmsAttachment> lis = pmsAttachmentService.findByHql(hql, sourceid);
//		String folder =	tmpFolderPath;
//
//		if (lis.size() > 0) {
//			for (int i = 0; i < lis.size(); i++) {
//				String name = lis.get(i).getName();
//				String filename="";
//				String extendname=lis.get(i).getExtensions().toLowerCase();;
//				DmdbBlob b = getAffixContent(lis.get(i).getId().toString());
//				byte[] modual = b.getBytes(1L, (int) b.length());
//				//if (extendname.endsWith("pdf")) {
//					//filename = folder + "/" + "affix"+i+category+ ".pdf";
//				//}else{
//				filename = folder + "/" + "affix"+i+category;
//				//}
//				File file = new File(filename);
//				// 建立输出字节流
//				FileOutputStream fos = new FileOutputStream(file);
//				// 用FileOutputStream 的write方法写入字节数组
//				fos.write(modual);
//				// 为了节省IO流的开销，需要关闭
//				fos.close();
//				if (extendname.endsWith("pdf")) {
//					list.add(filename);
//				}if (extendname.endsWith("doc")
//						|| extendname.endsWith("docx")) {
//					String pdfName=folder + "/" +"affix"+i+category+ ".pdf";
//					// word再转pdf
//					WordUtil.wordToPdf(filename, pdfName);
//					list.add(pdfName);
//				}if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
//					String pdfName=folder + "/" +"affix"+i+category+ ".pdf";
//					File pdf = new File(pdfName);
//					WordUtil.imgToPdf(pdf,file);
//					list.add(pdfName);
//				}
//			}
//		}
//		return list;
//
//	}
//
//	/**
//	 * 根据type下载附件，跟前面的一样，改个地址
//	 *
//	 * @throws Exception
//	 */
//	public List<String> getuploadFile_patent1(List<String> list,String sourceid,String category,String tmpFolderPath)
//			throws Exception {
//		String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=?";
//		List<PmsAttachment> lis = pmsAttachmentService.findByHql(hql, sourceid,category);
//		String folder =	tmpFolderPath;
//
//		if (lis.size() > 0) {
//			for (int i = 0; i < lis.size(); i++) {
//				String name = lis.get(i).getName();
//				String extendname=lis.get(i).getExtensions().toLowerCase();;
//				DmdbBlob b = getAffixContent(lis.get(i).getId().toString());
//				byte[] modual = b.getBytes(1L, (int) b.length());
//				String filename = folder + "/" + "affix"+i;
//				File file = new File(filename);
//				// 建立输出字节流
//				FileOutputStream fos = new FileOutputStream(file);
//				// 用FileOutputStream 的write方法写入字节数组
//				fos.write(modual);
//				// 为了节省IO流的开销，需要关闭
//				fos.close();
//				if (extendname.endsWith("pdf")) {
//					list.add(filename);
//				}if (extendname.endsWith("doc")
//						|| extendname.endsWith("docx")) {
//					String pdfName=folder + "/" +"affix"+i+ ".pdf";
//					// word再转pdf
//					WordUtil.wordToPdf(filename, pdfName);
//					list.add(pdfName);
//				}if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
//					String pdfName=folder + "/" +"affix"+i+ ".pdf";
//					File pdf = new File(pdfName);
//					WordUtil.imgToPdf(pdf,file);
//				}
//			}
//		}
//		return list;
//
//	}
//
//	public DmdbBlob getAffixEnclosureBlob(String id) {
//		String sql = "select affixcontent from pms_allattachments where id = ?";
//		String sqltemp = "select id from pms_allattachments where id = ?";
//		String tempold = dbHelper.getOnlyStringValue(sqltemp, new Object[]{id});
//		DmdbBlob blob = null;
//		try {
//			if (tempold != null) {
//				blob = dbHelper.getBlobValue(sql, new Object[]{id});
//				if (blob != null) {
//					return blob;
//				}
//			}
//			blob = dbHelperAffix.getBlobValue(sql, new Object[]{id});
//			if (blob != null) {
//				return blob;
//			} else {
//				attachmentDownload(id);
//				blob = dbHelperAffixNew.getBlobValue(sql, new Object[]{id});
//				if (blob != null) {
//					return blob;
//				}else{
//					attachmentDownload(id);
//					return blob;
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			log.error("读取附件出错" + e.getMessage());
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//
//	public void attachmentDownload(String id) {
//		File file = new File(FileUtil.getRealPath() + "product.properties");
//		String sql = "select * from pms_attachment where id = ?";
//		List<Map> rows = dbHelper.getRows(sql, new Object[]{id});
//		for (int i = 0; i < rows.size(); i++) {
//			String href = Util.trunChinese((String) rows.get(i).get("href"));
//			String sourceid = (String) rows.get(i).get("sourceid");
//			try {
//				Connection connection = dbHelperAffixNew.getConnection();
//				if (connection != null || connection.isClosed() == false) {
//					URL url = new URL(href);
//					HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//					httpURLConnection.setRequestMethod("GET");
//					httpURLConnection.setConnectTimeout(5000);
//					httpURLConnection.setReadTimeout(10000);
//					httpURLConnection.connect();
//					int responseCode = httpURLConnection.getResponseCode();
//					InputStream inputStream = null;
//					if (responseCode == HttpURLConnection.HTTP_OK)
//						inputStream = httpURLConnection.getInputStream();
//					String findsql = "select id from pms_allattachments where id =?";
//					List<Map> jumprow = dbHelperAffixNew.getRows(findsql, new Object[]{id});
//					BLOB blob = null;
//					if (jumprow == null || jumprow.size() <= 0) {
//						String insertsql = "INSERT INTO pms_allattachments (ID, SOURCEID, AFFIXCONTENT) VALUES (?, ?, empty_blob())";
//						dbHelperAffixNew.runSql(insertsql, new Object[]{id, sourceid});
//					}
//					PreparedStatement preparedStatement = connection.prepareStatement("select AFFIXCONTENT from pms_allattachments where id ='" + id + "' for update");
//					ResultSet resultSet = preparedStatement.executeQuery();
//					if (resultSet.next()) {
//						blob = (BLOB) resultSet.getBlob(1);
//						OutputStream outputStream = blob.getBinaryOutputStream();
//						byte[] b = new byte[blob.getBufferSize()];
//						int len = 0;
//						while ((len = inputStream.read(b)) != -1)
//							outputStream.write(b, 0, len);
//						inputStream.close();
//						outputStream.close();
//						connection.commit();
//						connection.close();
//					}
//					String updateFlagSql = "update pms_attachment set isdownflag = ? where id = ?";
//					String isTrueSql = "select * from PMS_ALLATTACHMENTS where id = ? and dbms_lob.getLength(AFFIXCONTENT) <> 0";
//					if (!Util.isEoN(dbHelperAffixNew.getRows(isTrueSql, new Object[]{id}))) {
//						dbHelper.runSql(updateFlagSql, new Object[]{"true", id});
//					} else {
//						dbHelper.runSql(updateFlagSql, new Object[]{"false", id});
//					}
//				}
//			} catch (Exception e) {
//				String updateFlagSql = "update pms_attachment set isdownflag = 'false' where id = ?";
//				try {
//					dbHelper.runSql(updateFlagSql, new Object[]{id});
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 根据type下载附件,按顺序排
//	 *
//	 * @throws Exception
//	 */
//	public List<String> getuploadFile_patent2(List<String> list,String sourceid,String category,String tmpFolderPath)
//			throws Exception {
//		String hql = "select t.id,t.name,t.createtime,t.creator,t.sourceid,t.filesize,t.href,t.seq,t.memo,t.extensions " +
//				"from pms_attachment t where t.sourceid=? and t.category in ('专利质量',?,'运用效益评价材料')"
//				+ "order by (case t.category when '专利质量' then '1' when '技术先进性评价材料' then '2' when '运用效益评价材料' then '4' else '3' end)";
//		List<Map> lis = dbHelper.getRows(hql, new Object[]{sourceid,category});
//		String folder =	tmpFolderPath;
//
//		if (lis.size() > 0) {
//			for (int i = 0; i < lis.size(); i++) {
//				String name = lis.get(i).get("name").toString();
//				String extendname=lis.get(i).get("extensions").toString().toLowerCase();;
//				DmdbBlob b = getAffixContent(lis.get(i).get("id").toString());
//				byte[] modual = b.getBytes(1L, (int) b.length());
//				String filename = folder + "/" + "affix-"+i;
//				File file = new File(filename);
//				// 建立输出字节流
//				FileOutputStream fos = new FileOutputStream(file);
//				// 用FileOutputStream 的write方法写入字节数组
//				fos.write(modual);
//				// 为了节省IO流的开销，需要关闭
//				fos.close();
//				if (extendname.endsWith("pdf")) {
//					list.add(filename);
//				}if (extendname.endsWith("doc")
//						|| extendname.endsWith("docx")) {
//					String pdfName=folder + "/" +"affix-"+i+ ".pdf";
//					// word再转pdf
//					WordUtil.wordToPdf(filename, pdfName);
//					list.add(pdfName);
//				}if(extendname.endsWith("jpg")||extendname.endsWith("png")||extendname.endsWith("bmp")||extendname.endsWith("jpeg")){
//					String pdfName=folder + "/" +"affix-"+i+ ".pdf";
//					File pdf = new File(pdfName);
//					WordUtil.imgToPdf(pdf,file);
//				}
//			}
//		}
//		return list;
//
//	}
//
	// 读取附件内容
	public DmdbBlob getAffixContent(String id){
		String sql = "select affixcontent from pms_allattachments where id = ?";
        String sqltemp = "select id from pms_allattachments where id = ?";
        String tempold = dbHelper.getOnlyStringValue(sqltemp,new Object[]{id});
        DmdbBlob blob = null;
		try {
//            if(tempold!=null){
//                blob = dbHelper.getBlobValue(sql,new Object[]{id});
//                if(blob!=null){
//                    return blob;
//                }
//            }
			    blob = dbHelperAffix.getBlobValue(sql,new Object[]{id});
                if(blob!=null){
                    return blob;
                }else{
                    return dbHelperAffixNew.getBlobValue(sql,new Object[]{id});
                }
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			log.error("读取附件出错"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
//
//	public DmdbBlob getAffixContentBySourceId(String sourceId){
//		String sql = "select affixcontent from pms_allattachments where sourceid = ?";
//		String sqltemp = "select id from pms_allattachments where sourceid = ?";
//        String tempold = dbHelper.getOnlyStringValue(sqltemp,new Object[]{sourceId});
//		String temp = dbHelperAffix.getOnlyStringValue(sqltemp,new Object[]{sourceId});
//		try {
//            if(tempold!=null){
//                return dbHelper.getBlobValue(sql,new Object[]{sourceId});
//            }
//			if(temp==null){
//				return dbHelperAffixNew.getBlobValue(sql,new Object[]{sourceId});
//			}else{
//				return dbHelperAffix.getBlobValue(sql,new Object[]{sourceId});
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			log.error("读取附件出错"+e.getMessage());
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	// 读取附件名
//	public String getAffixName(String id){
//		String sql = "select name from pms_attachment where id = ?";
//		try {
//			return dbHelper.getValue(sql,"name",new Object[]{id});
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error("读取数据出错"+e.getMessage());
//			e.printStackTrace();
//		}
//		return "";
//	}
//	public static void main(String[] args) {
//		PmsAffixService paf = new PmsAffixService();
//		File file = new File("D:\\dd.xls");
//		try {
//			paf.importByExcelFile(file, "");
//		} catch (BiffException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	/**
//	 * 根据sourceid 删除全部附件
//	 * @param sourceId
//	 */
//	public void deleteAllFilesBySourceId(String sourceId) {
//		// TODO Auto-generated method stub
//		String delSql1 = "delete from pms_attachment s where s.sourceid = ?";
//		String delSql2 = "delete from pms_allattachments s where s.sourceid = ?";
//		try {
//			dbHelper.runSql(delSql1,new Object[]{sourceId});
//			dbHelperAffix.runSql(delSql2,new Object[]{sourceId});
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//
//	public void deleteAllFilesBySourceIdAndId(String sourceId,String id) {
//		// TODO Auto-generated method stub
//		String delSql1 = "delete from pms_attachment s where s.sourceid = ? and s.id =?";
//		String delSql2 = "delete from pms_allattachments s where s.sourceid = ? and s.id =?";
//		try {
//			dbHelper.runSql(delSql1,new Object[]{sourceId,id});
//			dbHelperAffix.runSql(delSql2,new Object[]{sourceId,id});
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	/**
//	 * 根据id 删除附件
//	 */
//	public void deleteAllFilesById(String id) {
//		// TODO Auto-generated method stub
//		String sql = "select id from pms_allattachments s where s.id = ?";
//		String delSql1 = "delete from pms_attachment s where s.id = ? ";
//		String delSql2 = "delete from pms_allattachments s where s.id = ? ";
//		String temp = dbHelperAffix.getOnlyStringValue(sql,new Object[]{id});
//		try {
//			dbHelper.runSql(delSql1,new Object[]{id});
//			if(temp==null){
//				dbHelperAffixNew.runSql(delSql2,new Object[]{id});
//			}else{
//				dbHelperAffix.runSql(delSql2,new Object[]{id});
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 根据id 删除数据库附件信息，同时删除服务器上的附件
//	 */
//	public JSONObject deleteAllFilesByObj(PmsAttachment pmsAttachment) {
//		JSONObject result=new JSONObject();
//		// TODO Auto-generated method stub
//		String sql = "select id from pms_allattachments s where s.id = ?";
//		String delSql1 = "delete from pms_attachment s where s.id = ? ";
//		String delSql2 = "delete from pms_allattachments s where s.id = ? ";
//		String temp = dbHelperAffix.getOnlyStringValue(sql,new Object[]{pmsAttachment.getId()});
//		try {
//			//删除附件信息
//			dbHelper.runSql(delSql1,new Object[]{pmsAttachment.getId()});
//
//
//			//以下大字段表不清楚需要哪些额外操作，暂时不动
//			if(temp==null){
//				dbHelperAffixNew.runSql(delSql2,new Object[]{pmsAttachment.getId()});
//			}else{
//				dbHelperAffix.runSql(delSql2,new Object[]{pmsAttachment.getId()});
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			result.put("success",false);
//			result.put("errMsg","删除方法出错");
//		}
//		return result;
//	}
//
//	/**
//	 * 根据 sourceid type createuesrid 来查找附件
//	 * @param sourceid
//	 * @param type
//	 * @param createuserid
//	 * @return
//	 */
//	public List<PmsAffix> findBySidTyoeCid(String sourceid,String type,String createuserid){
//
//		StringBuffer hql = new StringBuffer("select p from PmsAffix p where p.sourceid =? and p.type =? and " +
//				"p.createuserid =?");
//
//		List<PmsAffix> pmsAffix = this.getPmsAffixDao().findByHql(hql.toString(),sourceid,type,createuserid);
//		return pmsAffix;
//	}
//
//    /**
//     * 生成文件名称，按时间戳来生成
//     * @return
//     */
//    public String getPackName(){
//        StringBuffer packName = new StringBuffer();
//        Calendar calendar = new GregorianCalendar();
//        packName.append(String.valueOf(calendar.get(Calendar.YEAR)))
//                .append(String.valueOf(calendar.get(Calendar.MONTH)+1))
//                .append(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)))
//                .append(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)))
//                .append(String.valueOf(calendar.get(Calendar.MINUTE)))
//                .append(String.valueOf(calendar.get(Calendar.SECOND)));
//        return packName.toString()+".zip";
//    }
//
//    public byte[] packAffixToZip(String sourceid) throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ZipOutputStream zip = new ZipOutputStream(baos);
//        //1.获得附件
//        //1.1从老的附件库中或者新的附件库中查找附件集合
//        try {
//            List<Map> list = dbHelper.getRows("select b.*,t.affixcontent from pms_allattachments t " +
//                    "inner join pms_affix b on b.sourceid = t.sourceid where t.sourceid = ?", new Object[]{sourceid});
//            if (list == null || list.size() == 0) {
//                list = dbHelperAffix.getRows("select * from pms_affixs where sourceid = ?", new Object[]{sourceid});
//            }
//            if (list != null && list.size() > 0) {
//                //2.循环附件对象集合，进行打包
//                for (Map map : list) {
//                    //2.1 获取附件的Blob数据
//                    DmdbBlob blob = dbHelperAffix.getBlobValue("select modual from pms_affixs where id = ?", new Object[]{map.get("id") + ""});
//                    //2.2 将Blob数据转成缓冲输入流
//                    BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());
//                    //2.3 定义一个byte数组，长度为Blob的长度，
//                    byte[] bytes = new byte[(int) blob.length()];
//                    int len = bytes.length;
//                    int offset = 0;
//                    int read = 0;
//                    //2.4 将缓冲流中的数据给到byte数组中
//                    while (offset < len
//                            && (read = is.read(bytes, offset, len - offset)) >= 0) {
//                        offset += read;
//                    }
//                    ZipEntry zipEntry = new ZipEntry(map.get("type") + "-" + map.get("name") + "." + map.get("extendname"));
//                    zipEntry.setSize(bytes.length);
//                    zip.putNextEntry(zipEntry);
//                    zip.write(bytes);
//                    zip.closeEntry();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (zip != null) {
//                zip.close();
//            }
//        }
//        return baos.toByteArray();
//    }
//
//    public byte[] packAffixToZipForPath(String filepath) throws IOException {//传入文件夹路径压缩
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ZipOutputStream zip = new ZipOutputStream(baos);
//        try {
//            //1.获得路径下的附件
//            File file = new File(filepath);
//            File[] list = file.listFiles();
//            if (list != null && list.length > 0) {
//                //2.1循环附件对象集合，进行打包
//                for (int i = 0; i < list.length; i++) {
//                    //2.2 定义一个byte数组
//                    byte[] bytes = OperationPdfUtil.fileToByteArray(filepath+"/"+list[i].getName());
//                    InputStream is = new ByteArrayInputStream(bytes);
//                    int len = bytes.length;
//                    int offset = 0;
//                    int read = 0;
//                    //2.3 将缓冲流中的数据给到byte数组中
//                    while (offset < len
//                            && (read = is.read(bytes, offset, len - offset)) >= 0) {
//                        offset += read;
//                    }
//                    ZipEntry zipEntry = new ZipEntry(list[i].getName());
//                    zipEntry.setSize(bytes.length);
//                    zip.putNextEntry(zipEntry);
//                    zip.write(bytes);
//                    zip.closeEntry();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (zip != null) {
//                zip.close();
//            }
//        }
//        return baos.toByteArray();
//    }
//
//    /**
//     * 打包下载的相应头部设置
//     * @param response
//     * @param fileName
//     * @param bytes
//     * @throws IOException
//     */
//    public void packAffixToZipHeader(HttpServletResponse response, String fileName, byte[] bytes) throws IOException {
//        response.reset();
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
//        response.getOutputStream().write(bytes);
//        response.flushBuffer();
//    }
//
//	//读本地文件
//	public Map localfetchAllAffixForOneProject(JSONObject example, HttpServletRequest request) {
//		Map lastMap = new HashMap();
//		List<Map> list = new ArrayList<Map>();
//		List<Map> configArr = new ArrayList<Map>();
//		List<Map> typeList = new ArrayList<Map>();
//		List<String> arrayurls = new ArrayList<String>();
//		String batchid = example.get("bid")+"";
//		String projectbaseid = example.get("sourceid")+"";
//		String applicationno = this.getOnlyValueBySql("select applicationno from pms_projectbase where id = ?",new Object[]{projectbaseid});
//		String publishPath = request.getRealPath("/")+"attachment\\";
//		System.out.println(publishPath+example.get("pathName")+"\\"+batchid+"\\"+applicationno);
//		int num = 0;
//
//		File[] transFiles = new File(publishPath+example.get("pathName")+"\\"+batchid+"\\"+applicationno).listFiles();
//		if(transFiles.length>0){
//			for (int i = 0; i < transFiles.length; i++) {
//				File file = transFiles[i];
//				if(file.getName().indexOf(".")>-1){
//					String[] temp = file.getName().split("\\.");
//					int suffixIndex = temp.length -1;
//					if("doc".equals(temp[suffixIndex])){
//						String oldUrl = publishPath+example.get("pathName")+"\\"+batchid+"\\"+applicationno+"\\"+file.getName();
//						String newUrl = oldUrl.replace("doc","pdf");
//						File ff = new File(newUrl);
//						if(ff.exists()){
//							continue;
//						}else{
//							WordUtil.wordToPdf(oldUrl,newUrl);
//						}
//					}else if("docx".equals(temp[suffixIndex])){
//						String oldUrl = publishPath+example.get("pathName")+"\\"+batchid+"\\"+applicationno+"\\"+file.getName();
//						String newUrl = oldUrl.replace("docx","pdf");
//						File ff = new File(newUrl);
//						if(ff.exists()){
//							continue;
//						}else{
//							WordUtil.wordToPdf(oldUrl,newUrl);
//						}
//					}
//				}
//			}
//		}
//
//		File[] allFiles = new File(publishPath+example.get("pathName")+"\\"+batchid+"\\"+applicationno).listFiles();
//		if(allFiles.length>0){
//			for (int i = 0; i < allFiles.length; i++) {
//				JSONObject listobj = new JSONObject();
//				Map map = new HashMap();
//				File file = allFiles[i];
//				if(file.getName().indexOf(".")>-1){
//					String[] temp = file.getName().split("\\.");
//					int suffixIndex = temp.length -1;
//					if("doc".equals(temp[suffixIndex])||"docx".equals(temp[suffixIndex])||
//							"xls".equals(temp[suffixIndex])||"xlsx".equals(temp[suffixIndex])||
//							"ppt".equals(temp[suffixIndex])){
//						continue;
//					}
//					map.put("category", temp[suffixIndex]);
//					listobj.put("extendName", temp[suffixIndex]);
//				}
//
//				listobj.put("type", "附件信息");
//				String webFileParth = "/attachment/"+example.get("pathName")+"/" + batchid + "/" + applicationno + "/" + file.getName();
//
//				map.put("caption", file.getName());
//				map.put("width", "120px");
//				map.put("size", file.length());
//				map.put("url", webFileParth);
//				map.put("key", projectbaseid+i);
//				map.put("frameClass", "my-custom-frame-css");
//
//				listobj.put("filesize",file.length());
//				listobj.put("id",projectbaseid+i);
//				listobj.put("name",file.getName());
//				listobj.put("sourceid",projectbaseid);
//				list.add(num,listobj);
//				num++;
//
//				if ("pdf".equals(listobj.get("extendName")) || "ppt".equals(listobj.get("extendName")) || "doc".equals(listobj.get("extendName")) ||
//						"docx".equals(listobj.get("extendName")) || "xls".equals(listobj.get("extendName")) ||
//						"xlsx".equals(listobj.get("extendName"))) {
//					map.put("type", "pdf");
//				} else {
//					map.put("type", "image");
//				}
//
//				configArr.add(map);
//				arrayurls.add(webFileParth);
//			}
//			JSONObject obj = new JSONObject();
//			obj.put("type","附件信息");
//			typeList.add(0,obj);
//
//			lastMap.put("data", list);
//			lastMap.put("downurl", arrayurls);
//			lastMap.put("filesConfig", configArr);
//			lastMap.put("typelist", typeList);
//			lastMap.put("success", true);
//
//		}
//
//		return lastMap;
////		return structureForPreviewAffix(example, lastMap, list, typeList,request);
//	}
//
//
//	//读附件库文件
//	public Map fetchAllAffixForOneProject(JSONObject example, HttpServletRequest request) {
//		Map lastMap = new HashMap();
//		JSONArray categoryArray = example.getJSONArray("categoryArray");
//		String insql = "";
//		String categorys = categoryArray.stream().map(m->{
//			String category  = (String) m;
//			return category;
//		}).collect(Collectors.joining("','","('","')")).toString();
//		if(categorys.length()>0){
//			insql = " and w.category in " + categorys;
//		}
//		List<Map> list = dbHelper.getRows("select w.id,w.category as type,w.sourceid,w.name,w.extensions as extendname," +
//				"w.filesize from pms_attachment w where w.sourceid=? " + insql, new Object[]{example.get("sourceid")});
//		String typesql = "select b.category as type from pms_attachment b where b.sourceid = ?  group by b.category";
//		List<Map> typeList = dbHelper.getRows(typesql, new Object[]{example.get("sourceid")});
//		return structureForPreviewAffix(example, lastMap, list, typeList,request);
//	}
//
//
//	private Map structureForPreviewAffix(JSONObject example, Map lastMap, List<Map> list, List<Map> typeList, HttpServletRequest request) {
//		if (list != null && list.size() > 0) {
//			List<Map> configArr = new ArrayList<Map>();
//			List<String> arrayurls = new ArrayList<String>();
//			String bid = example.get("bid") + "";
//			String defaultPathName = Util.isEoN(example.get("pathName"))?"previewFile":example.get("pathName")+"";
//			if (Util.isEoN(bid)) {
//				bid = dbHelper.getValue("select planprojectbatchid from pms_projectbase where id=?",
//						"planprojectbatchid",new Object[]{example.get("sourceid")});
//			}
//			if (Util.isEoN(bid)) {
//				bid = System.currentTimeMillis() + "";
//			}
//			for (Map map1 : list) {
//				String extendName = map1.get("extendname")+"";
//				// 判断改路径下是否有文件，没有则重新生成
//				String publishPath = request.getRealPath("/");
//				String realpicpath = publishPath + defaultPathName + "/";
//				// 文件路径
//				if ("doc".equals(extendName) || "docx".equals(extendName)) {
//					extendName = "pdf";
//				}
//				String webFileParth = "/"+example.get("pathName")+"/" + bid + "/" + example.get("sourceid") + "/" + map1.get("id") + "." + extendName;
//				System.out.println(webFileParth);
//				File file = new File(publishPath+webFileParth);
//				if(file.exists()){
//
//				}else{
//					// 附件库类型
//					String affixDBType =  example.get("affixDBType") + "";
//					this.saveDocOnDiskForReviewById(map1, realpicpath, example.get("sourceid") + "",bid,affixDBType);
//				}
//				Map map = new HashMap();
//				// 附件下载
//				map.put("category", map1.get("type"));
//				map.put("caption", map1.get("name"));
//				map.put("width", "120px");
//				map.put("size", map1.get("filesize"));
//				map.put("url", webFileParth);
//				map.put("key", map1.get("id"));
//				map.put("frameClass", "my-custom-frame-css");
//				if ("pdf".equals(extendName) || "ppt".equals(extendName) || "doc".equals(extendName) ||
//						"docx".equals(extendName) || "xls".equals(extendName) ||
//						"xlsx".equals(extendName)) {
//					map.put("type", "pdf");
//				} else {
//					map.put("type", "image");
//				}
//				configArr.add(map);
//				arrayurls.add(webFileParth);
//			}
//			lastMap.put("data", list);
//			lastMap.put("downurl", arrayurls);
//			lastMap.put("filesConfig", configArr);
//			lastMap.put("typelist", typeList);
//			lastMap.put("success", true);
//		} else {
//			lastMap.put("success", false);
//		}
//		return lastMap;
//	}
//
//	/**
//	 *
//	 * @param map
//	 * @param realpicpath
//	 * @param sourceid
//	 * @param bid
//	 * @param affixDBType
//	 */
//	private void saveDocOnDiskForReviewById(Map map, String realpicpath, String sourceid,String bid,String affixDBType) {
//		if(map==null)
//			return;
//		String path = realpicpath ;
//		path += bid + "/" + sourceid + "/";
//		String extendName = map.get("extendname")+"";
//		File isCheckFile = new File(path + map.get("id") + "." + extendName);
//		if(isCheckFile.exists()&&isCheckFile.isFile()){
//			return;
//		}
//		File tmpFolderPathFile = new File(path );
//		File myFile = null;
//		InputStream blobReader = null;
//		DmdbBlob blob = null;
//		FileOutputStream fos = null;
//		try {
//			if (!tmpFolderPathFile.exists()) {
//				tmpFolderPathFile.mkdirs();
//				myFile = new File(path + map.get("id") + "." + extendName);
//				myFile.createNewFile();
//			} else {
//				myFile = new File(path + map.get("id") + "." + extendName);
//				myFile.createNewFile();
//			}
//			if(Util.isEoN(affixDBType)) {
//				blob = dbHelperAffixNew.getBlobValue("select affixcontent from pms_allattachments where id = ?", new Object[]{map.get("id")});
//			}
//			if (blob==null) {
//				return;
//			}
//			try {
//				blobReader = blob.getBinaryStream();
//			} catch (Exception e) {
//				System.out.println("saveDocOnDiskForReviewById---" + e.getMessage());
//				e.printStackTrace();
//			}
//			byte[] content = new byte[1024];
//			fos = new FileOutputStream(myFile);
//			while ((blobReader.read(content)) > 0 ) {
//				fos.write(content);
//			}
//			blob.free();
//			fos.flush();
//			blobReader.close();
//			fos.close();
//			if ("docx".equals(extendName) || "doc".equals(extendName)) {
//				WordUtil.wordToPdf(path + "/"+ map.get("id") + "." + map.get("extendname"), path + "/"+ map.get("id") + ".pdf");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (blobReader != null) {
//				try {
//					blobReader.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//
//			}
//			if (fos != null) {
//				try {
//					fos.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
//	}
//
//
//	public static void WordToPdfChange(String wordFile,String pdfFile){//wordFile word 的路径  //pdfFile pdf 的路径
//		ActiveXComponent app = null;
//		System.out.println("开始转换...");
//		// 开始时间
//		// long start = System.currentTimeMillis();
//		try {
//			// 打开word
//			app = new ActiveXComponent("Word.Application");
//			// 获得word中所有打开的文档
//			Dispatch documents = app.getProperty("Documents").toDispatch();
//			System.out.println("打开文件: " + wordFile);
//			// 打开文档
//			Dispatch document = Dispatch.call(documents, "Open", wordFile, false, true).toDispatch();
//			// 如果文件存在的话，不会覆盖，会直接报错，所以我们需要判断文件是否存在
//			File target = new File(pdfFile);
//			if (target.exists()) {
//				target.delete();
//			}
//			System.out.println("另存为: " + pdfFile);
//			Dispatch.call(document, "SaveAs", pdfFile, 17);
//			// 关闭文档
//			Dispatch.call(document, "Close", false);
//		}catch(Exception e) {
//			System.out.println("转换失败"+e.getMessage());
//		}finally {
//			// 关闭office
//			app.invoke("Quit", 0);
//		}
//	}

    public List<PmsAttachment> getPmsAttachmentByIds(List<String> ids) {
        String hql = "select t from PmsAttachment t where t.sourceid in (:ids) ";
        Query query = this.getQuery(hql);
        query.setParameterList("ids", ids);
        List<PmsAttachment> list = query.list();
        return list;
    }

//	/**
//	 * 获取附件内容
//	 * @param sourceid
//	 * @param hashmap
//	 * @return
//	 */
//	public Map<String, Object> getTableAffixFortype(
//			List<Map> arry,String sourceid, Map<String, Object> hashmap) {
//		List<Map> imagedata=new ArrayList<>();
//		for (int i = 0; i < arry.size(); i++) {
//			String position=arry.get(i).get("position")+"";
//			String positionEnd="";
//			String width=arry.get(i).get("width")+"";
//			String height=arry.get(i).get("height")+"";
//			String category=arry.get(i).get("category")+"";
//			String positiontype=arry.get(i).get("positiontype")+"";
//			String positionsql=arry.get(i).get("positionsql")+"";
//			/*
//			* positiontype=normal 则是不循环的，positiontype=repeat 为普通循环的用projectbaseid，
//			* positiontype=orther 则是先关联相关表再根据该表的id查附件
//			* */
//
//			if("other".equals(positiontype)){
//				List<Map> liscate = this.dbHelper.getRows(positionsql, new Object[]{sourceid});
//				for (int j = 0; j < liscate.size(); j++) {
//					String catesourceid=liscate.get(j).get("id")+"";
//					String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=? order by createtime ";
//					List<PmsAttachment> lis = this.findByHql(hql, catesourceid,category);
//					if (lis.size() > 0) {//如果附件存在
//						positionEnd=position+(j+1);
//						if(!Util.isEoN(lis.get(0).getId())){
//							Map<String, Object> map = new HashMap<String, Object>();
//							map.put("width",Float.parseFloat(width));
//							map.put("height",Float.parseFloat(height));
//							DmdbBlob b= null;
//							//b = this.getAffixContent(lis.get(0).getId());
//							if(Util.isEoN(lis.get(0).getLocalfilepath())){
//								b = this.getAffixContent(lis.get(0).getId());
//							}else{//如果是本地生成的方式
//								String localfilepath=lis.get(0).getLocalfilepath();
//								ByteOutputStream byteOutputStream =  new ByteOutputStream();
//								try {
//									FileInfo fileInfo = fileStorage.read(localfilepath,byteOutputStream);
//								} catch (IOException e) {
//									e.printStackTrace();
//								}
//
//								b = (DmdbBlob)org.hibernate.Hibernate.createBlob(byteOutputStream.getBytes());
//							}
//							map.put("blob",b);
//							map.put("position",positionEnd);
//							imagedata.add(map);
//						}
//					}
//				}
//			}else{
//				String hql = "select t from cn.topcheer.pms.pojo.PmsAttachment t where t.sourceid=? and t.category=? order by createtime ";
//				List<PmsAttachment> lis = this.findByHql(hql, sourceid,category);
//				if (lis.size() > 0) {//如果附件存在
//					if("repeat".equals(positiontype)){//如果是循环的类别
//						for (int j = 0; j < lis.size(); j++) {
//							if(!Util.isEoN(lis.get(j).getId())){
//								positionEnd=position+(j+1);//如果是循环的类别，position特殊处理
//								Map<String, Object> map = new HashMap<String, Object>();
//								map.put("width",Float.parseFloat(width));
//								map.put("height",Float.parseFloat(height));
//								DmdbBlob b= null;
//								if(Util.isEoN(lis.get(j).getLocalfilepath())){
//									b = this.getAffixContent(lis.get(j).getId());
//								}else{//如果是本地生成的方式\
//									String localfilepath=lis.get(j).getLocalfilepath();
//									ByteOutputStream byteOutputStream =  new ByteOutputStream();
//									try {
//										FileInfo fileInfo = fileStorage.read(localfilepath,byteOutputStream);
//									} catch (IOException e) {
//										e.printStackTrace();
//									}
//									b = (DmdbBlob)org.hibernate.Hibernate.createBlob(byteOutputStream.getBytes());
//								}
//								map.put("blob",b);
//								map.put("position",positionEnd);
//								imagedata.add(map);
//							}
//						}
//					}else{//如果是不循环的，就去第一个
//						positionEnd=position;
//						if(!Util.isEoN(lis.get(0).getId())){
//							Map<String, Object> map = new HashMap<String, Object>();
//							map.put("width",Float.parseFloat(width));
//							map.put("height",Float.parseFloat(height));
//							DmdbBlob b= null;
//							if(Util.isEoN(lis.get(0).getLocalfilepath())){
//								b = this.getAffixContent(lis.get(0).getId());
//							}else{//如果是本地生成的方式
//								String localfilepath=lis.get(0).getLocalfilepath();
//								ByteOutputStream byteOutputStream =  new ByteOutputStream();
//								try {
//									FileInfo fileInfo = fileStorage.read(localfilepath,byteOutputStream);
//								} catch (IOException e) {
//									e.printStackTrace();
//								}
//								b = (DmdbBlob)org.hibernate.Hibernate.createBlob(byteOutputStream.getBytes());
//							}
//							map.put("blob",b);
//							map.put("position",positionEnd);
//							imagedata.add(map);
//						}
//					}
//
//				}
//			}
//		}
//		hashmap.put("imagedata",imagedata);
//		return hashmap;
//	}
//
//
//
//
//	public boolean dealZrjjAffix(){
//
//		return true;
//	}
//
//	public boolean dealFhqAffix(){
//		//String hql = "select u from PmsProjectbase u where u.oldplanbatchid in ('2019HLJSZRKXJJPLANBATCH-ZDXM','2019HLJSZRKXJJPLANBATCH-YJTD','2019HLJSZRKXJJPLANBATCH-YXQN','2019HLJSZRKXJJPLANBATCH-LHZZ','2019HLJSZRKXJJPLANBATCH-JCQN')";
//		String hql = "select u from PmsKeepcase u where 1=1 ";
//
//		List<PmsKeepcase> list = this.getQuery(hql).list();
//
//		for(PmsKeepcase pmsKeepcase:list){
//			try{
//				List<PmsAttachment> slist = this.pmsAttachmentService.findPmsAttachmentBySourceid(pmsKeepcase.getId());
//				if(slist.size()>0){
//					for(int i=0;i<slist.size();i++){
//						DmdbBlob b = getAffixContent(slist.get(i).getId().toString());
//						byte[] modual = b.getBytes(1L, (int) b.length());
//						InputStream inputStream = new ByteArrayInputStream(modual);
//						Calendar cal = Calendar.getInstance();
//						cal.setTime(slist.get(i).getCreatetime());
//						int year = cal.get(Calendar.YEAR);
//						String sourceid = slist.get(i).getSourceid();
//						String category = slist.get(i).getCategory();
//						String name = slist.get(i).getName();
//						FileInfo fileInfo = fileStorage.write(localFilePath + year+File.separator+sourceid+File.separator+category+File.separator+name,inputStream);
//						PmsAttachment pmsAttachment = slist.get(i);
//						pmsAttachment.setLocalfilepath(diskFileStorageProperties.getRootpath() + fileInfo.getName());
//						this.pmsAttachmentService.merge(pmsAttachment);
//					}
//				}
//
//			}
//			catch (Exception e){
//				WordUtil.convertFailFile2(pmsKeepcase.getId()+"发生错误:"+e);
//			}
//		}
//		return true;
//	}
//
//	public JSONObject pageVerification(JSONObject jsonObject){
//		JSONObject returnObj = new JSONObject();
//		if(jsonObject.containsKey("files")&&jsonObject.containsKey("pageCount")){
//			JSONArray jsonArray = jsonObject.getJSONArray("files");
//			int actualCount = 0;
//			if(jsonArray.size()>0){
//				for(int i=0;i<jsonArray.size();i++){
//					JSONObject fileObj = jsonArray.getJSONObject(i);
//					JSONObject filePageObj = this.getFilePage(fileObj.getString("id"));
//					if(filePageObj.getBoolean("flag")){
//						actualCount += filePageObj.getInt("pageCount");
//					}else{
//						returnObj.put("flag",false);
//						returnObj.put("msg",filePageObj.getString("errMsg"));
//						return  returnObj;
//					}
//					//actualCount += this.getFilePage(fileObj.getString("id"));
//				}
//				if(actualCount>jsonObject.getInt("pageCount")){
//					returnObj.put("flag",true);
//					returnObj.put("isover",true);
//					returnObj.put("actualCount",actualCount);
//					return  returnObj;
//				}else{
//					returnObj.put("flag",true);
//					returnObj.put("isover",false);
//					returnObj.put("actualCount",actualCount);
//					return  returnObj;
//				}
//			}else{
//				returnObj.put("flag",false);
//				returnObj.put("msg","传入的附件数组为空，无法验证。");
//				return  returnObj;
//			}
//
//		}else{
//			returnObj.put("flag",false);
//			returnObj.put("msg","传入的参数有误");
//			return  returnObj;
//		}
//	}
//
//	/**	获取附件的页数 pdf|jpg|png|doc|docx
//	 *
//	 * @param fileid 附件的主id
//	 * @return jsonobj  flag:true/false errMsg pageCount
//	 */
//	public JSONObject getFilePage(String fileid){
//
//		JSONObject jsonObject = new JSONObject();
//		try{
//			int pageCount = 0;
//			PmsAttachment pmsAttachment = this.pmsAttachmentService.findById(fileid);
//			if(pmsAttachment!=null){
//				if(!fileStorage.exists(pmsAttachment.getLocalfilepath())){
//					DmdbBlob b = getAffixContent(pmsAttachment.getId());
//					byte[] modual = b.getBytes(1L, (int) b.length());
//					InputStream inputStream = new ByteArrayInputStream(modual);
//					Calendar cal = Calendar.getInstance();
//					cal.setTime(pmsAttachment.getCreatetime());
//					int year = cal.get(Calendar.YEAR);
//					String sourceid = pmsAttachment.getSourceid();
//					String category = pmsAttachment.getCategory();
//					String name = pmsAttachment.getName();
//					FileInfo fileInfo = fileStorage.write(localFilePath + year+File.separator+sourceid+File.separator+category+File.separator+name,inputStream);
//					pmsAttachment.setLocalfilepath(diskFileStorageProperties.getRootpath() + fileInfo.getName());
//					this.pmsAttachmentService.merge(pmsAttachment);
//				}
//				String sourceExtendName = pmsAttachment.getName().substring(pmsAttachment.getName().lastIndexOf(".") + 1,pmsAttachment.getName().length());
//				String extendname = pmsAttachment.getName().substring(pmsAttachment.getName().lastIndexOf(".") + 1,pmsAttachment.getName().length()).toLowerCase();
//				int dealType = PageCountNum.isAllowAndReturnTransferType(extendname);
//				String localpath = "";
//				switch (dealType){
//					case 0:localpath = diskFileStorageProperties.getRootpath()+pmsAttachment.getLocalfilepath();break;
//					case 1:
//						localpath = (diskFileStorageProperties.getRootpath()+pmsAttachment.getLocalfilepath()).replace(sourceExtendName,"pdf");
//						try{
//							WordUtil.wordToPdf(diskFileStorageProperties.getRootpath()+pmsAttachment.getLocalfilepath(),localpath);
//						}catch (Exception e){
//							jsonObject.put("flag",false);
//							jsonObject.put("errMsg","文件:"+pmsAttachment.getName()+"转化pdf出错");
//							return jsonObject;
//						}
//						break;
//					case 2:
//						localpath = (diskFileStorageProperties.getRootpath()+pmsAttachment.getLocalfilepath()).replace(sourceExtendName,"pdf");
//						try{
//							this.jpgToPdf(new File(localpath),new File(diskFileStorageProperties.getRootpath()+pmsAttachment.getLocalfilepath()));
//						}catch (Exception e){
//							jsonObject.put("flag",false);
//							jsonObject.put("errMsg","文件:"+pmsAttachment.getName()+"转化pdf出错");
//							return jsonObject;
//						}
//						break;
//				}
//				PdfReader p = null;
//				try{
//					p = new PdfReader(localpath);
//					pageCount = p.getNumberOfPages();
//					jsonObject.put("flag",true);
//					jsonObject.put("pageCount",pageCount);
//				}catch (Exception e){
//					jsonObject.put("flag",false);
//					jsonObject.put("errMsg","文件:"+pmsAttachment.getName()+"无法读取页数");
//				}finally {
//					if(p!=null){
//						p.close();
//					}
//
//				}
//				return jsonObject;
//			}else{
//				jsonObject.put("flag",false);
//				jsonObject.put("errMsg","文件:"+pmsAttachment.getId()+"附件id有误");
//				return jsonObject;
//			}
//		}catch (Exception e){
//			jsonObject.put("flag",false);
//			jsonObject.put("errMsg","文件:"+fileid+"附件id有误");
//			return jsonObject;
//		}
//
//	}
//
//	//byte数组 类型 sourceid 存入附件
//	public void FileByteIntoAffix(String sourceid,String filename,String category,byte[] content){
//		List<Map> list = this.getAffixsByCategory(sourceid,category);
//		if(list.size()>0){
//			this.deleteAllFilesById(list.get(0).get("id").toString());
//		}
//		PmsAttachment pmsAttachment = new PmsAttachment();
//		pmsAttachment.setId(UUID.randomUUID().toString());
//		pmsAttachment.setCreatetime(new Date());
//		pmsAttachment.setCreator(SecurityUserLocal.getSecurityUser().getId());
//		pmsAttachment.setName(filename);
//		pmsAttachment.setExtensions(filename.substring(filename.lastIndexOf(".")+1));
//		pmsAttachment.setSourceid(sourceid);
//		pmsAttachment.setSeq(0);
//		pmsAttachment.setCategory(category);
//		pmsAttachmentService.merge(pmsAttachment);
//		InputStream in = new ByteArrayInputStream(content);
//		String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
//				+ " values('"+pmsAttachment.getId()+"','"+sourceid+"',empty_blob())";
//		String selsql = "select affixcontent from pms_allattachments where id = '"+pmsAttachment.getId()+"' for update";
//
//		try {
//			dbHelperAffixNew.insertBlobSql(insql,selsql,new Object[]{},in);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error("附件插入出错，请检查"+e.getMessage());
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 业务获取方法中--获取循环类附件
//	 * @param tpAllAffixs  返回附件集合
//	 * @param tableObject  对象判断是否有附件
//	 * @param list  业务数据源
//     * @return
//     */
//	public JSONArray getRepeatAffixs(JSONArray tpAllAffixs,JSONObject tableObject,List<?> list){
//		if(tableObject.containsKey("ishaveaffix")&&tableObject.getBoolean("ishaveaffix")){
//			//说明当前对象里有存在循环类附件
//			if(list.size()>0){
//				List<String> targetIds =  list.stream().map(m-> {
//					try {
//						return (String) m.getClass().getMethod("getId").invoke(m);
//					} catch (Exception e) {
//						return "";
//					}
//				}).collect(Collectors.toList());
//				tpAllAffixs.addAll(JSONArray.fromObject(this.getPmsAttachmentByIds(targetIds)));
//			}
//		}
//		return tpAllAffixs;
//	}
//
//	public boolean saveWordClob(MultipartHttpServletRequest multipartRequest, String sourceid, String clobTypeName){
//
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//
//			for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
//				try {
//					String extensions = entry.getValue().getOriginalFilename().substring(entry.getValue().getOriginalFilename().lastIndexOf(".") + 0);
//					File file = FileUtil.createNewFile(Util.GetFileRealPath(PmsAffixService.defaultFilePath + "FileColbTemp/" + sourceid + "/" + new Date().getTime() + extensions));
//					entry.getValue().transferTo(file);
//					String wordtext = WordUtil.readWord(file.getAbsolutePath());
//					PmsClob pmsClob = new PmsClob();
//					pmsClob.setId(UUID.randomUUID().toString());
//					pmsClob.setMainid(sourceid);
//					pmsClob.setSource(wordtext);
//					pmsClob.setColumnname(clobTypeName);
//					this.pmsClobService.merge(pmsClob);
//				} catch (Exception e) {
//					log.info(PmsAffixService.defaultFilePath + "FileColbTemp/" + sourceid + "/" + new Date().getTime() + entry.getValue().getOriginalFilename());
//					log.error("saveWordClob", e);
//					return false;
//				}
//			}
//		return true;
//	}
//
//	public JSONArray fileInputToDatabaseWithCategoryForPage(MultipartHttpServletRequest multipartRequest, String sourceid, String userid, String category, String memo, JSONArray allowArray) throws IOException, DocumentException {
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		Iterator<String> fileIterator = multipartRequest.getFileNames();
//		int ii = 1;
//		JSONArray array=new JSONArray();
//		while (fileIterator.hasNext()) {
//			String fileKey = fileIterator.next();
//			MultipartFile multipartFile = fileMap.get(fileKey);
//			if (multipartFile.getSize() != 0L) {
//				//获取上传文件名字
//				String affixName = multipartFile.getOriginalFilename();
//				//获取文件类型
//				String affixType = affixName.substring(affixName.lastIndexOf(".") + 1, affixName.length());
//
//				String uuid = Util.NewGuid();
//				PmsAttachment pmsAttachment = new PmsAttachment();
//				pmsAttachment.setId(uuid);
//				pmsAttachment.setCreatetime(new Date());
//				pmsAttachment.setCreator(userid);
//				pmsAttachment.setFilesize(multipartFile.getSize() + "");
//				pmsAttachment.setName(multipartFile.getOriginalFilename());
//				pmsAttachment.setExtensions(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1));
//				pmsAttachment.setSourceid(sourceid);
//				pmsAttachment.setSeq(ii);
//				pmsAttachment.setCategory(category);
//				pmsAttachment.setMemo(memo);
//
//				ii++;
//				pmsAttachmentService.merge(pmsAttachment);
//				SysParamvalue sv=this.sysParamvalueService.findParamsClassByCodeAndTcodeOne("sysparamdata","serviceurl");
//				JSONObject obj=JSONObject.fromObject(pmsAttachment);
//				obj.put("loadurl", sv.getValue()+pmsAttachment.getId());
//				array.add(obj);
//
//				InputStream in = new ByteArrayInputStream(multipartFile.getBytes());
//				String insql = "insert into pms_allattachments(id,sourceid,affixcontent)"
//						+ " values('" + uuid + "','" + sourceid + "',empty_blob())";
//				String selsql = "select affixcontent from pms_allattachments where id = '" + uuid + "' for update";
//
//				try {
//					dbHelperAffixNew.insertBlobSql(insql, selsql, new Object[]{}, in);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					log.error("附件插入出错，请检查" + e.getMessage());
//					e.printStackTrace();
//				}
//
//			}
//		}
//		return array;
//	}
//	public List<String> fileInputToDatabaseWithCategoryByte(MultipartHttpServletRequest multipartRequest, String tmpPDFFile, String type, List<String> list) throws Exception,IOException, DocumentException {
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		Iterator<String> fileIterator = multipartRequest.getFileNames();
//		int ii = 1;
//		while (fileIterator.hasNext()) {
//			String fileKey = fileIterator.next();
//			MultipartFile multipartFile = fileMap.get(fileKey);
////            PdfReader pdfReader = new PdfReader(multipartFile.getInputStream());
////            PdfStamper pdfStamper = new PdfStamper(pdfReader
////            		, new FileOutputStream(
////            				"d://itext-demo22.pdf"));
//			if (multipartFile.getSize() != 0L) {
//
//				InputStream in = new ByteArrayInputStream(multipartFile.getBytes());
//				String folder = tmpPDFFile + "/" + type;
//				File tmpFolderPathFile = new File(folder);
//				if (!tmpFolderPathFile.exists()) {
//					tmpFolderPathFile.mkdirs();
//				} else {
//					// 如果已经有文件，先删除所有文件
//					String[] filesInFolder = tmpFolderPathFile.list();
//					if (filesInFolder.length > 0) {
//						for (int i = 0; i < filesInFolder.length; i++) {
//							File file = new File(folder + "/" + filesInFolder[i]);
//							file.delete();
//						}
//					}
//				}
//				String name = "";
//				String extendname = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
//				int len = 0;
//				byte[] buffer = new byte[1024];
//				String filename = folder + "/" + "affix" + 0;
//				File file = new File(filename);
//				// 建立输出字节流
//				FileOutputStream fos = new FileOutputStream(file);
//				BufferedOutputStream bos = new BufferedOutputStream(fos);
//				// 用FileOutputStream 的write方法写入字节数组
//				long crr1 = System.currentTimeMillis();
//				while ((len = in.read(buffer)) != -1) {
//					bos.write(buffer, 0, len);
//				}
//				System.out.println("--->" + (System.currentTimeMillis() - crr1));
//				// 为了节省IO流的开销，需要关闭
//				bos.close();
//				fos.close();
//				in.close();
//				if (extendname.endsWith("pdf")) {
//					list.add(filename);
//				}
//				if (extendname.endsWith("doc")
//						|| extendname.endsWith("docx")) {
//					String pdfName = folder + "/" + "affix" + 0 + ".pdf";
//					// word再转pdf
//					SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
//					String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
//					WordUtil.wordToPdfByType(filename, pdfName, toPdfType);
//					//WordUtil.wordToPdf(filename, pdfName);
//					list.add(pdfName);
//				}
//				if (extendname.endsWith("jpg") || extendname.endsWith("png") || extendname.endsWith("bmp") || extendname.endsWith("jpeg")) {
//					String pdfName = folder + "/" + "affix" + 0 + ".pdf";
//					File pdf = new File(pdfName);
//					WordUtil.imgToPdf(pdf, file);
//					list.add(pdfName);
//				}
//
//			}
//		}
//		return list;
//	}
//
//	//附件存本地磁盘，业务库记录附件基本信息位置--返回url
//	public Map fileInputToDatabaseWithCategorylocalUrl(MultipartHttpServletRequest multipartRequest, String sourceid, String userid, String category, String memo, String extendname) throws IOException, DocumentException{
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		Iterator<String> fileIterator = multipartRequest.getFileNames();
//		String origid = multipartRequest.getParameter("origid");
//
//		String localfilepath="";
//		int ii = 1;
//		PmsAttachment pmsAttachment = new PmsAttachment();
//		while (fileIterator.hasNext()) {
//			String fileKey = fileIterator.next();
//			MultipartFile multipartFile = fileMap.get(fileKey);
//			if (multipartFile.getSize() != 0L) {
//				//获取上传文件名字
//				String affixName=multipartFile.getOriginalFilename();
//				//获取文件类型
//				String affixType=affixName.substring(affixName.lastIndexOf(".")+1,affixName.length());
//
//				String uuid = Util.NewGuid();
//				pmsAttachment.setId(uuid);
//				pmsAttachment.setCreatetime(new Date());
//				pmsAttachment.setCreator(userid);
//				pmsAttachment.setFilesize(multipartFile.getSize()+"");
//				pmsAttachment.setName(multipartFile.getOriginalFilename());
//				pmsAttachment.setExtensions(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1));
//				pmsAttachment.setSourceid(sourceid);
//				pmsAttachment.setSeq(ii);
//				pmsAttachment.setCategory(category);
//				pmsAttachment.setMemo(memo);
//				pmsAttachment.setOrigid(origid);
//				if (affixType.indexOf("blob")<0) {
//					FileInfo fileInfo = fileStorage.write(localFilePath + Util.formatDate(new Date(), "yyyy/MM/dd")+File.separator+sourceid+File.separator+uuid+File.separator+multipartFile.getOriginalFilename(),multipartFile.getInputStream());
//					//pmsAttachment.setMemo(memo);
//					localfilepath=diskFileStorageProperties.getRootpath() + fileInfo.getName();
//					pmsAttachment.setLocalfilepath(localfilepath);
//				} else {
//					FileInfo fileInfo = fileStorage.write(localFilePath + Util.formatDate(new Date(), "yyyy/MM/dd")+File.separator+sourceid+File.separator+uuid+File.separator+uuid+"."+extendname,multipartFile.getInputStream());
//					//pmsAttachment.setMemo(memo);
//					localfilepath=diskFileStorageProperties.getRootpath() + fileInfo.getName();
//					pmsAttachment.setLocalfilepath(localfilepath);
//				}
//				ii++;
//				pmsAttachmentService.merge(pmsAttachment);
//
//			}
//		}
//		Map map=JSONObject.fromObject(pmsAttachment);
//		return map;
//	}
//
//	public JSONObject deleteFileByFilepath(String filepath){
//		JSONObject result = new JSONObject();
//		try{
//			//删除附件，清理内存占用
//			File file = new File(filepath);
//			boolean delete = file.delete();
//			if (delete){
//				result.put("success",delete);
//			}else{
//				result.put("success",delete);
//				result.put("errMsg","清理附件占用内存失败");
//			}
//		}catch (Exception e){
//			log.error("删除文件失败，错误信息："+e);
//			result.put("success",false);
//			result.put("errMsg","删除文件时出错");
//		}
//
//		return result;
//	}
}
