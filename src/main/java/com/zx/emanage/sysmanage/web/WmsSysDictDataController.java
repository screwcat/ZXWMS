package com.zx.emanage.sysmanage.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveJobService;
import com.zx.emanage.inve.service.IWmsInvePruductCategoryService;
import com.zx.emanage.sysmanage.service.IWmsSysDictDataService;
import com.zx.emanage.sysmanage.vo.WmsSysDictDataSearchBeanVO;
import com.zx.emanage.util.gen.SysConstant;
import com.zx.emanage.util.gen.domain.WmsSysDictData;
import com.zx.emanage.util.gen.entity.SysDeptDataBean;
import com.zx.emanage.util.gen.entity.WmsInveJob;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.vo.WmsSysDictDataVO;
import com.zx.platform.syscontext.vo.TreeBean;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsSysDictDataController
{
    private static Logger log = LoggerFactory.getLogger(WmsSysDictDataController.class);

    @Autowired
    private IWmsSysDictDataService wmssysdictdataService;
    @Autowired
    private IWmsInvePruductCategoryService wmsInvePruductCategoryService;
    @Autowired
    private IWmsInveJobService wmsInveJobService;
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatawithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsSysDictDataSearchBeanVO queryInfo)
    {
        return wmssysdictdataService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatawithpaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsSysDictDataSearchBeanVO queryInfo)
    {
        return wmssysdictdataService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsSysDictDataVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatainfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsSysDictDataVO getInfoByPK(Integer wms_sys_dict_data_id)
    {
        return wmssysdictdataService.getInfoByPK(wms_sys_dict_data_id);
    }

    /**
     * Description :通过传入字典ID获取字典数据值
     * 
     * @param primary key
     * @return WmsSysDictDataVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatabydictid.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictId(Integer wms_sys_dict_id,
                                                                                   String isEmpty, String isAll)
    {

        List<com.zx.emanage.util.gen.entity.WmsSysDictData> wmsSysDictDataList = wmssysdictdataService.getDictDataByDictId(wms_sys_dict_id,
                                                                                                                           isEmpty,
                                                                                                                           isAll);
        return wmsSysDictDataList;
    }

    /**
     * Description :通过传入字典ID获取字典数据值，请选择使用‘-1’
     * 
     * @param primary key
     * @return WmsSysDictData
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatabydictidempty.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmpty(Integer wms_sys_dict_id,
                                                                                        String isEmpty, String isAll)
    {

        List<com.zx.emanage.util.gen.entity.WmsSysDictData> wmsSysDictDataList = wmssysdictdataService.getDictDataByDictIdEmpty(wms_sys_dict_id,
                                                                                                                                isEmpty,
                                                                                                                                isAll);
        return wmsSysDictDataList;
    }

    /**
     * 
     * @Title: getDictDataByDictIdEmptyNotCombination
     * @Description: 根据需要获取贷款产品 并判断是否是组合贷
     * @param wms_sys_dict_id
     * @param isEmpty
     * @param isAll
     * @return isCombin true位组合贷 false位普通贷
     * @author: Administrator
     * @time:2017年5月12日 上午11:14:24
     * history:
     * 1、2017年5月12日 Administrator 创建方法
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatabydictidemptycombin.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmptyCombination(Integer wms_sys_dict_id, String isEmpty, String isAll, String isCombin)
    {

        List<com.zx.emanage.util.gen.entity.WmsSysDictData> wmsSysDictDataList = wmssysdictdataService.getDictDataByDictIdEmptyCombination(wms_sys_dict_id, isEmpty, isAll, isCombin);
        return wmsSysDictDataList;
    }
	
    /**
     * Description :通过传入字典ID获取字典数据值，请选择使用‘-1’
     * 
     * @param primary
     *            key
     * @return WmsSysDictData
     * @author auto_generator
     */
	@RequestMapping(value = "/sysmanage/wmssysdictdatabydictidemptyall.do", method = { RequestMethod.GET })
	@ResponseBody
	public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmptyAll(
			String wms_sys_dict_id, String isEmpty, String isAll) {

		List<com.zx.emanage.util.gen.entity.WmsSysDictData> wmsSysDictDataList = wmssysdictdataService
				.getDictDataByDictIdEmptyAll(wms_sys_dict_id, isEmpty, isAll);
		return wmsSysDictDataList;
	}

    /**
     * Description :通过传入字典ID获取字典数据值，请选择使用‘-1’  俩个值决定 Integer wms_sys_dict_id,Integer p_wms_sys_dict_data_id,
     * 
     * @param primary key
     * @return WmsSysDictData
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatabydictidemptyP.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmptyP(Integer wms_sys_dict_id,Integer p_wms_sys_dict_data_id,
                                                                                        String isEmpty, String isAll)
    {
    	  List<com.zx.emanage.util.gen.entity.WmsSysDictData> wmsSysDictDataList = wmssysdictdataService.getDictDataByDictIdEmptyP(wms_sys_dict_id,p_wms_sys_dict_data_id, isEmpty,isAll);
    	  return wmsSysDictDataList;
	}
    
    /**
     * Description :通过传入字典ID获取字典数据值，请选择使用‘-1’  俩个值决定 Integer wms_sys_dict_id,Integer value_code,
     * 
     * @param primary key
     * @return WmsSysDictData
     * @author baisong
     */
    @RequestMapping(value = "/sysmanage/getDictDataCode.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getDictDataCode(Integer wms_sys_dict_id,String value_code,
                                                                                        String isEmpty, String isAll)
    {
    	Map<String, Object> map = wmssysdictdataService.getDictDataCode(wms_sys_dict_id,value_code, isEmpty,isAll);
    	  return map;
	}   
    
    /**
     * Description :通过传入字典ID获取字典数据值，请选择使用‘-1’
     * 
     * @param primary key
     * @return WmsSysDictData
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatabydictidemptyc.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmpty(Integer wms_sys_dict_id, Integer p_wms_sys_dict_data_id,
                                                                                        String isEmpty, String isAll)
    {
      
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> wmsSysDictDataList = wmssysdictdataService.getDictDataByDictIdEmpty(wms_sys_dict_id, p_wms_sys_dict_data_id,
                                                                                                                                isEmpty,
                                                                                                                                isAll);
        return wmsSysDictDataList;
    }

    /**
     * getDictDataTreeBean:(获取字典表中的父子数据). <br/>
     * 
     * @author zoe
     * @param p_wms_sys_dict_id
     * @param wms_sys_dict_id
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/sysmanage/getdictdatatreebean.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<TreeBean> getDictDataTreeBean(Integer p_wms_sys_dict_id, Integer wms_sys_dict_id, String... isnotval)
    {
        List<TreeBean> wmsSysDictDataVOList = new ArrayList<>();
        if (isnotval != null && !"".equals(isnotval))
        {
            wmsSysDictDataVOList = wmssysdictdataService.getDictDataTreeBean(p_wms_sys_dict_id, wms_sys_dict_id, isnotval);
        }
        else
        {
            wmsSysDictDataVOList = wmssysdictdataService.getDictDataTreeBean(p_wms_sys_dict_id, wms_sys_dict_id);
        }
        return wmsSysDictDataVOList;

    }

    /**
     * Description :通过传入字典编码获取字典数据值
     * 
     * @param primary key
     * @return WmsSysDictDataVO
     * @author auto_generator
     */
    @Deprecated
    @RequestMapping(value = "/sysmanage/wmssysdictdatabydictcode.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<WmsSysDictDataVO> getDictDataByDictCode(String dict_code)
    {

        List<WmsSysDictDataVO> wmsSysDictDataVOList = wmssysdictdataService.getDictDataByDictCode(dict_code);
        return wmsSysDictDataVOList;

    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatasave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsSysDictData bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmssysdictdataService.save(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }

    /**
     * Description :update method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdataupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsSysDictData bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmssysdictdataService.update(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatadelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, WmsSysDictData bean)
    {
        String result = wmssysdictdataService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }

    /**
     * Description :通过传入金额状态ID获取金额状态，去掉待支付状态，请选择使用‘-1’
     * 
     * @param primary key
     * @return WmsSysDictData
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatabydictidamount.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdAmount(Integer wms_sys_dict_id,
                                                                                         String isEmpty)
    {

        List<com.zx.emanage.util.gen.entity.WmsSysDictData> wmsSysDictDataList = wmssysdictdataService.getDictDataByDictIdAmount(wms_sys_dict_id,
                                                                                                                                 isEmpty);
        return wmsSysDictDataList;
    }

    /**
     * Description :通过传入字典ID获取字典数据值，请选择使用‘-1’ 实现联动查询
     * 
     * @param wms_sys_dict_id 字典表ID
     * @param isEmpty
     * @param cre_type 贷款产品类型
     */
    @RequestMapping(value = "/sysmanage/wmssysdictdatabyidandcreType.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdAndCreType(Integer wms_sys_dict_id,
                                                                                             String isEmpty,
                                                                                             String cre_type)
    {

        List<com.zx.emanage.util.gen.entity.WmsSysDictData> wmsSysDictDataList = wmssysdictdataService.getDictDataByDictIdAndCreType(wms_sys_dict_id,
                                                                                                                                     isEmpty,
                                                                                                                                     cre_type);
        return wmsSysDictDataList;
    }
    
    /**
     * @Title: getAjaxRequest 
     * @Description: 获取房贷产品相关费率
     * @param cre_loan_type
     * @return    
     * @return Map<String,Object>    
     * @throws
     * @author lvtu 
     * @date 2015年7月2日 下午1:30:06
     */
    @RequestMapping(value = "/sysmanage/wmssysgetajaxrequest.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getAjaxRequest(String cre_loan_type) {
        return SysConstant.getFDInfo(cre_loan_type);
    }
    
    /**
     * @Title: getAjaxRequestCD 
     * @Description: 获取车贷产品相关费率
     * @param cre_loan_type
     * @return    
     * @return Map<String,Object>    
     * @throws
     * @author lvtu 
     * @date 2015年7月27日 上午10:19:55
     */
    @RequestMapping(value = "/sysmanage/wmssysgetajaxrequestcd.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getAjaxRequestCD(String cre_loan_type) {
        return SysConstant.getCDInfo(cre_loan_type);
    }
    
    
    @RequestMapping(value = "/sysmanage/wmssysgetvalmeaningbycodes.do", method = { RequestMethod.GET })
    @ResponseBody
    public String getValMeaningBycodes(Integer wms_sys_dict_id, String valCodes) {
    	return wmssysdictdataService.getValMeaningBycodes(wms_sys_dict_id, valCodes);
    }
    
    /**
     * Description :查询系统中所有产品信息
     * 
     * @return List
     * @author hancd
     * @date 2015年8月24日 上午17:28
     */
    @RequestMapping(value = "/sysmanage/getAllInvePruductCategory.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<WmsInvePruductCategory> getAllInvePruductCategory()
    {

        List<WmsInvePruductCategory> wmsInvePruductCategoryList = wmsInvePruductCategoryService.getAllInvePruductCategory();
        return wmsInvePruductCategoryList;
    }
    
    /**
     * 通过给定的职务代号获取相对应的信息
     * @param job_code
     * @return
     * @author hancd
     */
     @RequestMapping(value="/sysmanage/getAllSysPostWithList.do",method ={RequestMethod.GET})
     @ResponseBody
     public WmsInveJob getAllSysPostWithList(String job_code){
    	    WmsInveJob wmsInveJobList =wmsInveJobService.getAllSysPostWithList(job_code);
    	    return wmsInveJobList;
     }
     
    /**
     *根据城市获取所属门店 
     * @param isEmpty
     * @param deptpId
     * @return List
     * @yangqiyu
     */
    @RequestMapping(value="/sysmanage/getAllSysStoresWithList.do",method={RequestMethod.GET})
    @ResponseBody
    public List<SysDeptDataBean> getAllSysStoresWithList(String isEmpty,Integer deptpId){
    	List<SysDeptDataBean> sysDeptList=wmssysdictdataService.getAllSysStoresWithList(isEmpty,deptpId);
    	return sysDeptList;
    }
    
    /**
     * Description :通过传入字典ID获取字典数据值，请选择使用‘-1’  俩个值决定 Integer wms_sys_dict_id,Integer value_code,
     * 
     * @param primary key
     * @return WmsSysDictData
     * @author baisong
     */
    @RequestMapping(value = "/sysmanage/getDictDataByCode.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getDictDataByCode(Integer wms_sys_dict_id,String value_code)
    {
    	Map<String, Object> map = wmssysdictdataService.getDictDataByCode(wms_sys_dict_id,value_code);
    	  return map;
    }

    /**
     * 
     * @Title: getDictDataByCode
     * @Description: 赎回查询=》单据来源查询条件加载
     * @param wms_sys_dict_id
     * @param value_code
     * @return 
     * @author: zhangmingjian
     * @time:2017年6月30日 上午11:54:48
     * history:
     * 1、2017年6月30日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/sysmanage/getDictDataBybillSource.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<Map<String, Object>> getDictDataBybillSource(Integer wms_sys_dict_id, String value_code)
    {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("value_code", -1);
        map.put("value_meaning", "请选择");
        list.add(map);
        map = new HashMap<>();
        map.put("value_code", 0);
        map.put("value_meaning", "MWF赎回");
        list.add(map);
        map = new HashMap<>();
        map.put("value_code", 3);
        map.put("value_meaning", "PTP赎回");
        list.add(map);
        return list;
    }

}