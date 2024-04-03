
package cn.topcheer.pms2.biz.utils;

import cn.topcheer.halberd.starter.office.conversion.OfficeConversionService;
import cn.topcheer.halberd.starter.office.conversion.impl.IOfficeConversion;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.utils.SpringUtil;

/**
 * @文件名 OfficeConversionUtil.java
 * @Description 文档转化
 * @文件编号
 * @创建人 JOSONHu
 * @日期 2015年9月17日 下午3:03:39
 * @修改人
 * @日期
 * @修改内容
 * @version V1.0
 */

@Slf4j
public class OfficeConversionUtil
{
	/**
	 * 可配置获取转化实例
	 * 配置项   halberd.officeConversion: aspose
	 * @return
	 */
	public static IOfficeConversion instance(){
		return SpringUtil.getBean(OfficeConversionService.class);
	}


	
}
