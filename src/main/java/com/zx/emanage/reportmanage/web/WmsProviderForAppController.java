package com.zx.emanage.reportmanage.web;

import java.text.ParseException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.reportmanage.service.WmsProviderForAppService;
import com.zx.emanage.reportmanage.vo.WmsProviderForAppVO;

/**
 * @ClassName: WmsProviderForAppController
 * @Description: APP端接口controller
 * @author WangShuai
 * @date 2017年1月3日
 * @version V1.0
 * history:
 * 1、2017年1月3日 WangShuai 创建文件
 */
@Controller
public class WmsProviderForAppController {
	
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(WmsProviderForAppController.class);
	@Autowired
	WmsProviderForAppService wmsProviderForAppService;
	
	
	/**
	 * @Title: getPersonnelcomm_dept_sta_v197
	 * @Description: 接口18
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月3日 下午3:56:54
	 * history:
	 * 1、2017年1月3日 WangShuai 创建方法
	*/
    @RequestMapping(value = "/mobpt/personnelcomm_dept_sta_v197MOA.do", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> getPersonnelcomm_dept_sta_v197(WmsProviderForAppVO wmsProviderForAppVO){
	    
	    return wmsProviderForAppService.getPersonnelcomm_dept_sta_v197(wmsProviderForAppVO);
	}
	
	/**
	 * @Title: getPersonnelcomm_comm_item_sta_v197
	 * @Description: 接口19
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月4日 上午10:39:11
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
    @RequestMapping(value = "/mobpt/personnelcomm_comm_item_sta_v197MOA.do", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> getPersonnelcomm_comm_item_sta_v197(WmsProviderForAppVO wmsProviderForAppVO){
	    
	    return wmsProviderForAppService.getPersonnelcomm_comm_item_sta_v197(wmsProviderForAppVO);
	}
	
	
	/**
	 * @Title: getPersonnel_comm_inve_sta_v197
	 * @Description: 接口20
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月4日 上午11:11:04
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
    @RequestMapping(value = "/mobpt/personnel_comm_inve_sta_v197MOA.do", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> getPersonnel_comm_inve_sta_v197(WmsProviderForAppVO wmsProviderForAppVO){
	    
	    return wmsProviderForAppService.getPersonnel_comm_inve_sta_v197(wmsProviderForAppVO);
	}
	
	/**
	 * @Title: getAch_center_sta_v197
	 * @Description: 接口21
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 上午11:11:04
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
    @RequestMapping(value = "/mobpt/ach_center_sta_v197MOA.do", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> getAch_center_sta_v197(WmsProviderForAppVO wmsProviderForAppVO) throws ParseException{
	    
	    return wmsProviderForAppService.getAch_center_sta_v197(wmsProviderForAppVO);
	}
	
	/**
	 * @Title: getAch_dept_sta_v197
	 * @Description: 接口22
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 上午11:11:04
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
    @RequestMapping(value = "/mobpt/ach_dept_sta_v197MOA.do", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> getAch_dept_sta_v197(WmsProviderForAppVO wmsProviderForAppVO) throws ParseException{
	    
	    return wmsProviderForAppService.getAch_dept_sta_v197(wmsProviderForAppVO);
	}
	
	/**
	 * @Title: getAch_team_sta_v197
	 * @Description: 接口23
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 上午11:11:04
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
    @RequestMapping(value = "/mobpt/ach_team_sta_v197MOA.do", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> getAch_team_sta_v197(WmsProviderForAppVO wmsProviderForAppVO) throws ParseException{
	    
	    return wmsProviderForAppService.getAch_team_sta_v197(wmsProviderForAppVO);
	}
	
	/**
	 * @Title: getAch_personnel_sta_v197
	 * @Description: 接口24
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 上午11:11:04
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
    @RequestMapping(value = "/mobpt/ach_personnel_sta_v197MOA.do", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> getAch_personnel_sta_v197(WmsProviderForAppVO wmsProviderForAppVO) throws ParseException{
	    
	    return wmsProviderForAppService.getAch_personnel_sta_v197(wmsProviderForAppVO);
	}
	
	
	/**
	 * @Title: getAch_inve_sta_v197
	 * @Description: 接口25
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 上午11:11:04
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
    @RequestMapping(value = "/mobpt/ach_inve_sta_v197MOA.do", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> getAch_inve_sta_v197(WmsProviderForAppVO wmsProviderForAppVO) throws ParseException{
	    
	    return wmsProviderForAppService.getAch_inve_sta_v197(wmsProviderForAppVO);
	}
	
	/**
	 * @Title: getPersonnel_comm_inve_sta_v197
	 * @Description: 接口29
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 上午11:11:04
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
    @RequestMapping(value = "/mobpt/ach_vice_sta_v197MOA.do", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> getAch_vice_sta_v197(WmsProviderForAppVO wmsProviderForAppVO) throws ParseException{
	    
	    return wmsProviderForAppService.getAch_vice_sta_v197(wmsProviderForAppVO);
	}
}
