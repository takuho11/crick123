/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2016-1-8 14:53:32
 */
package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsEnterpriseServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.biz.modules.resource.service.IHalberdAttachService;
import cn.topcheer.halberd.biz.modules.resource.vo.AttachVO;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.api.plan.entity.PmsAttachment;
import cn.topcheer.pms2.dao.plan.PmsAttachmentDao;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.utils.FileUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PmsAttachment 服务类
 */
@Service(value = "AttachService")
public class PmsAttachmentService extends GenericService<PmsAttachment>  implements IHalberdAttachService<PmsAttachment> {

    public PmsAttachmentDao getPmsAttachmentDao() {
        return (PmsAttachmentDao) this.getGenericDao();
    }

//	@Autowired
//	DBHelper dbHelper;
//	@Autowired
//	private FileStorage fileStorage;
//	@Autowired
//    PmsAttachmentService pmsAttachmentService;
//	@Autowired
//	PmsEnterpriseServiceImpl pmsEnterpriseService;
//	@Autowired
//	DiskFileStorageProperties diskFileStorageProperties;

    @Autowired
    public void setPmsAttachmentDao(PmsAttachmentDao pmsAttachmentDao) {

        this.setGenericDao(pmsAttachmentDao);
    }

//	public Boolean deletePmsAttachmentBySourceid(String sourceid) {
//		String hql = "select t from PmsAttachment t where t.sourceid = ? ";
//		List<PmsAttachment> pmsAttachmentList = this.findByHql(hql, sourceid);
//		this.deleteList(pmsAttachmentList);
//		return true;
//	}

    public List<PmsAttachment> findPmsAttachmentBySourceid(String souceid) {
        String hql = "select t from PmsAttachment t where t.sourceid = ?0";
        Query query = this.getQuery(hql);
        query.setParameter(0, souceid);
        return query.list();
    }


//	public List<PmsAttachment> findPmsAttachmentBySourceidOrder(String souceid) {
//		String hql = "select t from PmsAttachment t where t.sourceid = ? order by t.createtime desc";
//		Query query = this.getQuery(hql);
//		query.setParameter(0,souceid);
//		return query.list();
//	}
//
//
//	public List<PmsAttachment> findPmsAttachmentBySourceidAndCategory(String souceid,String category) {
//		String hql = "select t from PmsAttachment t where t.sourceid = ? and t.category=? order by t.createtime desc";
//		List<PmsAttachment> list = this.findByHql(hql, new Object[]{souceid, category});
//		return list;
//	}
//
//
//	public void copyAffix(JSONObject jsonObject){
//		String location_old = jsonObject.get("location_old")+"";
//		String location_new = jsonObject.get("location_new")+"";
//		String sourceid_old = jsonObject.get("sourceid_old")+"";
//		String category_old = jsonObject.get("category_old")+"";
//		String sourceid_new = jsonObject.get("sourceid_new")+"";
//		String category_new = jsonObject.get("category_new")+"";
//
////		//判断是否是二级学院
////		PmsEnterprise pmsEnterprise = this.pmsEnterpriseService.findById(sourceid_old);
////		if(pmsEnterprise!=null){
////			if(!Util.isEoN(pmsEnterprise.getCollegename())){
////				sourceid_old = pmsEnterprise.getPmsParentEnterprise().getId();
////			}
////		}
//
//
//		if("database".equals(location_old)&&"database".equals(location_new)){ //数据库转数据库
//			if(!Util.isEoN(sourceid_old)&&!Util.isEoN(category_old)&&!Util.isEoN(sourceid_new)&&!Util.isEoN(category_new)){
//				//判断是否存在现在的sourceid
//				String isExist = this.getOnlyValueBySql("select id from pms_attachment " +
//						"where sourceid = ? and category = ?",new Object[]{sourceid_new,category_new});
//
//				if(!Util.isEoN(isExist)){
//					this.runSql("delete from pms_attachment where id = ?",new Object[]{isExist});
//				}
//
//				try {
//					String id_new = Util.NewGuid();
//					String id_old = this.getOnlyValueBySql("select id from pms_attachment " +
//							"where sourceid = ? and category = ?",new Object[]{sourceid_old,category_old});
//					this.runSql("insert into pms_attachment (id,name,category,createtime,creator,sourceid,filesize,extensions,localfilepath,seq,judgetime) " +
//							"select ? as id,name,? as category,sysdate as createtime,creator,? as sourceid,filesize,extensions,localfilepath,seq,createtime " +
//							"from pms_attachment " +
//							"where sourceid = ? and category = ? ",new Object[]{id_new,category_new,sourceid_new,sourceid_old,category_old});
//
//					this.runSql("insert into pms_allattachments (id,sourceid,affixcontent) " +
//							"select ? as id,? as sourceid,affixcontent from pms_allattachments " +
//							"where id = ? and sourceid = ?",new Object[]{id_new,sourceid_new,id_old,sourceid_old});
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}else if("local".equals(location_old)&&"database".equals(location_new)) { //本地转数据库
//
//		}else if("database".equals(location_old)&&"local".equals(location_new)) { //数据库转本地
//			if(!Util.isEoN(sourceid_old)&&!Util.isEoN(category_old)&&!Util.isEoN(sourceid_new)&&!Util.isEoN(category_new)){
//				//判断是否存在现在的sourceid
//				String isExist = this.getOnlyValueBySql("select id from pms_attachment " +
//						"where sourceid = ? and category = ?",new Object[]{sourceid_new,category_new});
//
//				if(!Util.isEoN(isExist)){
//					this.runSql("delete from pms_attachment where id = ?",new Object[]{isExist});
//				}
//
//				try {
//					String localfilepath = "";
//					String id_new = Util.NewGuid();
//					String id_old = this.getOnlyValueBySql("select id from pms_attachment " +
//							"where sourceid = ? and category = ?",new Object[]{sourceid_old,category_old});
//
//					PmsAttachment pmsAttachment = this.pmsAttachmentService.findById(id_old);
//
//					//2.1 获取附件的Blob数据
//					Blob blob = dbHelper.getBlobValue("select affixcontent from pms_allattachments where id = ? and sourceid = ?",
//							new Object[]{id_old,sourceid_old});
//					//2.2 将Blob数据转成缓冲输入流
//					BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());
//					//2.3 定义一个byte数组，长度为Blob的长度，
//					byte[] bytes = new byte[(int) blob.length()];
//					int len = bytes.length;
//					int offset = 0;
//					int read = 0;
//					//2.4 将缓冲流中的数据给到byte数组中
//					while (offset < len
//							&& (read = is.read(bytes, offset, len - offset)) >= 0) {
//						offset += read;
//					}
//
//					InputStream sbs = new ByteArrayInputStream(bytes);
//					FileInfo fileInfo = fileStorage.write(PmsAffixService.localFilePath + Util.formatDate(new Date(), "yyyy/MM/dd")+
//							File.separator+sourceid_new+File.separator+id_new+File.separator+pmsAttachment.getName(),sbs);
//					localfilepath = fileInfo.getName();
//
//					this.runSql("insert into pms_attachment (id,name,category,createtime,creator,sourceid,filesize,extensions,localfilepath,seq,judgetime) " +
//							"select ? as id,name,? as category,sysdate as createtime,creator,? as sourceid,filesize,extensions,? as localfilepath,seq,createtime " +
//							"from pms_attachment " +
//							"where sourceid = ? and category = ? ",new Object[]{id_new,category_new,sourceid_new,diskFileStorageProperties.getRootpath() + localfilepath,sourceid_old,category_old});
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}else{ //本地转本地
//			if(!Util.isEoN(sourceid_old)&&!Util.isEoN(category_old)&&!Util.isEoN(sourceid_new)&&!Util.isEoN(category_new)){
//				//判断是否存在现在的sourceid
//				String isExist = this.getOnlyValueBySql("select id from pms_attachment " +
//						"where sourceid = ? and category = ?",new Object[]{sourceid_new,category_new});
//
//				if(!Util.isEoN(isExist)){
//					this.runSql("delete from pms_attachment where id = ?",new Object[]{isExist});
//				}
//
//				try {
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//					String date_string = sdf.format(new Date());
//					String localfilepath_new = "";
//					String id_new = Util.NewGuid();
//					String localfilepath_old = this.getOnlyValueBySql("select localfilepath from pms_attachment " +
//							"where sourceid = ? and category = ?",new Object[]{sourceid_old,category_old});
//					String name_old = this.getOnlyValueBySql("select name from pms_attachment " +
//							"where sourceid = ? and category = ?",new Object[]{sourceid_old,category_old});
//
//					localfilepath_new = "/"+PmsAffixService.localFilePath+(date_string.replace("-","/"))+"/"+sourceid_new+"/"+id_new+"/"+name_old;
//
//					String tmpFolderPath = "/"+PmsAffixService.localFilePath+(date_string.replace("-","/"))+"/"+sourceid_new+"/"+id_new;
//
//					File tmpFolderPathFile = new File(tmpFolderPath);
//					if (!tmpFolderPathFile.exists()) {
//						tmpFolderPathFile.mkdirs();
//					}
//
//					this.copy(localfilepath_old,localfilepath_new);
//
//					this.runSql("insert into pms_attachment (id,name,category,createtime,creator,sourceid,filesize,extensions,localfilepath,seq,judgetime) " +
//							"select ? as id,name,? as category,sysdate as createtime,creator,? as sourceid,filesize,extensions,? as localfilepath,seq,createtime " +
//							"from pms_attachment " +
//							"where sourceid = ? and category = ? ",new Object[]{id_new,category_new,sourceid_new,diskFileStorageProperties.getRootpath() + localfilepath_new,sourceid_old,category_old});
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//
//	/*复制*/
//	public static void copy(String url1, String url2) throws Exception {
//		FileInputStream in = new FileInputStream(new File(url1));
//		FileOutputStream out = new FileOutputStream(new File(url2));
//		byte[] buff = new byte[512];
//		int n = 0;
//        /*System.out.println("复制文件：" + "\n" + "源路径：" + url1 + "\n" + "目标路径："
//                + url2);*/
//		while ((n = in.read(buff)) != -1) {
//			out.write(buff, 0, n);
//		}
//		out.flush();
//		in.close();
//		out.close();
//		//System.out.println("复制完成");
//	}



    @Override
    public PmsAttachment buildAttach(String fileName, Long fileSize, BladeFile bladeFile, String category, String sourceId) {
        String fileExtension = FileUtil.getFileExtension(fileName);
        BladeUser bladeUser =  AuthUtil.getUser();
        PmsAttachment pmsAttachment = new PmsAttachment();
        pmsAttachment.setId(Util.NewGuid());
        pmsAttachment.setCreatetime(new Date());
        if(bladeUser!=null)
        {
            pmsAttachment.setCreator(bladeUser.getUserId());
            pmsAttachment.setOrigid(bladeUser.getDeptId());
        }
        pmsAttachment.setLocalfilepath(bladeFile.getLink());
        pmsAttachment.setHref(bladeFile.getFullLink());
        pmsAttachment.setFilesize(Func.toStr(fileSize));
        pmsAttachment.setName(bladeFile.getOriginalName());
        pmsAttachment.setExtensions(fileExtension);
        pmsAttachment.setSourceid(sourceId);
        pmsAttachment.setSeq(0);
        pmsAttachment.setCategory(category);

        //pmsAttachment.setMemo();
        this.saveOrUpdate(pmsAttachment);
        return pmsAttachment;
    }

    @Override
    public PmsAttachment getAttachById(String id) {
        return this.getById(id);
    }

    @Override
    public PmsAttachment detail(String id) {
        return  this.getById(id);
    }

    @Override
    public PageResult<List<AttachVO>> page(AttachVO attach, org.springblade.core.mp.support.Query query) {
        PmsAttachment pmsAttachment=new PmsAttachment();
        //pmsAttachment.setName(attach.getName());
        pmsAttachment.setName(attach.getOriginalName());
        pmsAttachment.setCategory(attach.getCategory());
        pmsAttachment.setOrigid(attach.getOrgId());
        PageResult<List<PmsAttachment>> pageList =  this.getPagination(Page.of(query.getCurrent(),query.getSize()),pmsAttachment);
        List<AttachVO> attachVOList = pageList.getData().stream().map(item->{
            AttachVO attachVO=new AttachVO();
            attachVO.setId(item.getId());
            attachVO.setName(item.getName());
            attachVO.setDomainUrl(item.getDomainUrl());
            attachVO.setOriginalName(item.getOriginalName());
            attachVO.setCategory(item.getCategory());
            attachVO.setAttachSize(item.getFileSize());
            attachVO.setLink(item.getLocalfilepath());
            attachVO.setOrgId(item.getOrgId());
            attachVO.setExtension(item.getExtension());
            attachVO.setCreateUser(item.getCreator());
            attachVO.setCreateTime(item.getCreatetime());
            return attachVO;
        }).collect(Collectors.toList());
        PageResult<List<AttachVO>> rv=PageResult.data(attachVOList,pageList.getTotal());
        return rv;
    }

    @Override
    public Result<List<PmsAttachment>> List(AttachVO attach) {
        PmsAttachment pmsAttachment=new PmsAttachment();
        //pmsAttachment.setName(attach.getName());
        pmsAttachment.setName(attach.getOriginalName());
        pmsAttachment.setCategory(attach.getCategory());
        pmsAttachment.setOrigid(attach.getOrgId());
        List<PmsAttachment> list= this.findByExample(pmsAttachment);

        return Result.data(list);
    }

    @Override
    public boolean submit(PmsAttachment pmsAttachment) {
        this.saveOrUpdate(pmsAttachment);
        return true;
    }

    @Override
    public boolean deleteAttach(List<String> listIds) {
        this.deleteByIds(listIds);
        return true;
    }

}
