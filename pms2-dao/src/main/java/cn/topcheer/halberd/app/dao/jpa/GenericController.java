package cn.topcheer.halberd.app.dao.jpa;

import cn.topcheer.halberd.biz.common.utils.Util;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.common.enumerate.ErrorCodeEnum;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JsonConfig;
import org.springblade.core.secure.utils.AuthUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GenericController<T> {
	private Class<T> t;
	/**
	 * JSON转换时对象及父对象的列选择器集合，
	 */
	protected  Map<String, String>  PS_SET_SIMPLE=new HashMap<String, String>();
	protected  Map<String, String>  PS_SET=new HashMap<String, String>();
	
	protected JsonConfig PS_Config_SIMPLE=null;
	protected JsonConfig PS_Config=null;
	private GenericService<T> genericService;  
	
	
	public GenericService<T> getGenericService() {
		return genericService;
	} 


	public void setGenericService(GenericService<T> genericService) {
		this.genericService = genericService;
	}


	public GenericController(){
		try {
			Class cls = getClass();
			while (!(cls.getGenericSuperclass() instanceof ParameterizedType)) {
				cls = cls.getSuperclass();
			}
			if (cls != null) {
				t = (Class<T>) ((ParameterizedType) cls.getGenericSuperclass())
						.getActualTypeArguments()[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  根据传入的id返回相应的SysBookmark 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysBookmark 对象（json格式，字符串形式）
	 */
	//@RequestMapping({ "/findById.do" })
	protected  String _findById(String id,Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8");  
		T t=genericService.findById(id); 
		Result res= Result.data(t);// new ResponseBack(MessageCode.MessageCode_Success, t);
		JsonConfig config=null;
		if(fetchParent)
			config=PS_Config;
		else
			config=PS_Config_SIMPLE;
		return res.toJsonString(config); 
	}
	
	
	/**
	 *  根据传入的id返回相应的 SysBookmark 对象（json格式的字符串）
	 * @param example
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysBookmark 对象（json格式，字符串形式）
	 */
	//@RequestMapping({ "/findByExample" })
	protected String _findByExample(String example,Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8");  
		if(Util.isNullOrEmpty(example))
			example="{}";
		T jsonExample = (T)JSON.parseObject(example,t);  //; JSONObject.fromObject(example);
		List<T> list= genericService.findByExample(jsonExample); 
		Result res=Result.data(list);
		JsonConfig config=null;
		if(fetchParent)
			config=PS_Config;
		else
			config=PS_Config_SIMPLE;  
		return res.toJsonString(config); 
	}	
	
	
	
	/**
	 *  保存传入的 SysBookmark 对象（json格式的字符串）
	 * @param pojo 传入的pojok对象
	 * @param request
	 * @param response
	 * @return
	 */
	//@RequestMapping(value={ "/save"})	
	protected String _save(String pojo,
			HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8");
		T jsonExample = (T)JSON.parseObject(pojo,t);
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("user", AuthUtil.getUser());
		genericService.save (jsonExample,params);
		Result res=Result.data(t);// new ResponseBack(MessageCode.MessageCode_Success, t);
		JsonConfig config=null; 
		config=PS_Config; 
		return res.toJsonString(config); 
	}
	/**
	 *  根据传入的id删除相应的 SysBookmark 对象（json格式的字符串）
	 * @param id 要删除的 SysBookmark 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	//@RequestMapping({ "/deleteById" })
	protected String _deleteById(String id,
			HttpServletRequest request, HttpServletResponse response){
		
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8"); 
		
		boolean ret= genericService.deleteById(id);
		 
		Result res=null;
		if(ret)
			res=Result.data(ret);
		else
			res=Result.fail(Integer.parseInt(ErrorCodeEnum.MessageCode_BussinessError.getCode()),ErrorCodeEnum.MessageCode_BussinessError.getMessage());// ew ResponseBack(MessageCode.MessageCode_BussinessError,ret);
		JsonConfig config=null; 
		config=PS_Config; 
		return res.toJsonString(config); 
	}
	
	/**
	 *  根据传入的ids删除相应的 SysBookmark 对象（json格式的字符串）
	 * @param ids 要删除的 SysBookmark 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	//@RequestMapping({ "/deleteByIds" })
	protected String _deleteByIds(String ids,
			HttpServletRequest request, HttpServletResponse response){
		
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8"); 
		//ids.split(",");
		boolean ret= genericService.deleteByIds(ids.split(","));
		Result res=null;
		if(ret)
			res=Result.data(ret);
		else
			res=Result.fail(Integer.parseInt(ErrorCodeEnum.MessageCode_BussinessError.getCode()),ErrorCodeEnum.MessageCode_BussinessError.getMessage());
		JsonConfig config=null; 
		config=PS_Config; 
		return res.toJsonString(config); 
	}
	
	
}
