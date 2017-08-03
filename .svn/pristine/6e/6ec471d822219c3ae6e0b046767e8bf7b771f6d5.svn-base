package com.zx.emanage.sysmanage.web;
/**
 * 版权所有：版权所有(C) 2014，沈阳新融金融信息服务有限公司
 * 文件名称: WmsInveWarningController.java
 * 系统名称：WMS
 * 模块名称：债权预警
 * 完成日期：2015-9-6
 * 作    者：	baisong
 * 内容摘要：  债权预警查询
 * 
 * 文件调用：
 * 修改记录：01
 * 修改时间：2014-11-27
 * 修 改 人:  hancd
 * 修改内容：增加能够删除信贷和房贷处于草稿状态下的单据 软删除
 * 关联BUG：
 * 修改方法：通过给定的主贷款ID和贷款标示cre_loan
 */
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.sysmanage.service.IWmsInveWarningService;
import com.zx.emanage.sysmanage.vo.WmsInveWarningBeanVO;

@Controller
public class WmsInveWarningController {
	
	private static Logger log = LoggerFactory.getLogger(WmsInveWarningController.class);
	 @Autowired
	 public IWmsInveWarningService wmsinvewarningservice;
	

	/**
	 * Description :获取债权预警信息
	 * @param WmsInveWarningBeanVO
	 * @return map
	 * @author baisong
	 */
    @RequestMapping(value = "/sysmanage/getInfoforWarning.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> getInfo(WmsInveWarningBeanVO bean)
    {
    	Map<String,Object> paramMap = wmsinvewarningservice.getInfo(bean);
    	return paramMap ;
	}
	/**
	 * Description :获取债权预警信息--excel
	 * @param WmsInveWarningBeanVO
	 * @return map
	 * @author baisong
	 */
    @RequestMapping(value = "/sysmanage/getInfoforWarningExcel.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> getInfoExcel(WmsInveWarningBeanVO bean)
    {
    	Map<String,Object> paramMap = wmsinvewarningservice.getInfoExcel(bean);
    	return paramMap ;
	}
	/**
	 * Description :获取债权预警信息
	 * @param 
	 * @return map
	 * @author baisong
	 */
    @RequestMapping(value = "/sysmanage/getSumInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> getSumInfo()
    {
    	Map<String,Object> paramMap = wmsinvewarningservice.getSumInfo();
    	return paramMap ;
	}
}
