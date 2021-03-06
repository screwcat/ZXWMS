package com.zx.emanage.inve.web;

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

import com.zx.emanage.inve.service.IWmsInvePruductCategoryService;
import com.zx.emanage.inve.service.IWmsInveTransaAttService;
import com.zx.emanage.inve.vo.WmsInvePruductCategorySearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategoryLog;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInvePruductCategoryController
{
    private static Logger log = LoggerFactory.getLogger(WmsInvePruductCategoryController.class);

    @Autowired
    private IWmsInvePruductCategoryService wmsinvepruductcategoryService;
    @Autowired
    private IWmsInveTransaAttService wmsInveTransaAttService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorywithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                          RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInvePruductCategorySearchBeanVO queryInfo)
    {
        return wmsinvepruductcategoryService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/inve/getallwmsinvepruductcategorywithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getAllListWithoutPaging(HttpServletRequest request,String cktype, String category_name, Integer wms_inve_pruduct_category_id,String platform_user)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvepruductcategoryService.getAllListWithoutPaging(user,cktype, category_name, wms_inve_pruduct_category_id, platform_user);
    }
    
    
    /**
     * 根据wms_inve_transa_prod_id查询附件信息
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmspaginglist.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLisPaging(String wms_inve_transa_prod_id)
    {
        return wmsInveTransaAttService.getListPaging(wms_inve_transa_prod_id);
    }
    

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorywithpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInvePruductCategorySearchBeanVO queryInfo)
    {
        return wmsinvepruductcategoryService.getListWithPaging(queryInfo);
    }
    
    /**
     * Description :理财产品审核确认列表
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorywithpaginglistauditing.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingAuditing(WmsInvePruductCategorySearchBeanVO queryInfo)
    {
        return wmsinvepruductcategoryService.getListWithPagingAuditing(queryInfo);
    }

    /**
     * Description :get single-table information by primary key 理财产品 查询 根据产品id查询
     * 
     * @param primary key
     * @return WmsInvePruductCategoryVO
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategoryinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInvePruductCategory getInfoByPK(Integer wms_inve_pruduct_category_id)
    {
        return wmsinvepruductcategoryService.getInfoByPK(wms_inve_pruduct_category_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorysave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInvePruductCategory bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvepruductcategoryService.save(bean, user);
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
     * Description :产品禁用
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategoryupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInvePruductCategory bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        String   user_ip = SysUtil.getIP(request);
        try
        {
            result = wmsinvepruductcategoryService.update(bean, user ,user_ip);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "系统管理-参数管理-理财产品-产品禁用";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * Description :查询理财产品，请选择使用‘-1’
     * 
     * @return list
     * @author ry
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorylistforselect.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsInvePruductCategory> getInvePruductCategory()
    {

        List<com.zx.emanage.util.gen.entity.WmsInvePruductCategory> wmsInvePruductCategory = wmsinvepruductcategoryService.getAllInvePruductCategory();
        return wmsInvePruductCategory;
    }

    /**
     * Description :查询所有pos机，请选择使用‘-1’
     * 
     * @return list
     * @author ry
     */
    @RequestMapping(value = "/inve/wmsinveallposlistforselect.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsInvePos> getInveAllPos()
    {

        List<com.zx.emanage.util.gen.entity.WmsInvePos> wmsInvePos = wmsinvepruductcategoryService.getAllInvePos();
        return wmsInvePos;
    }

    /**
     * Description :保存理财产品 期限表 限制表 产品表 奖励利率表
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorysaveall.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveALl(HttpServletRequest request, WmsInvePruductCategory bean,WmsInvePruductCategorySearchBeanVO BeanVO,WmsInvePruductYearPaySpecial paySpecial )
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvepruductcategoryService.saveall(bean, user,BeanVO,paySpecial);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-参数管理-理财产品-保存";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }
    /**
     * Description :更新理财产品 期限表 限制表 产品表 奖励利率表
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategoryupdateall.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdateALl(HttpServletRequest request, WmsInvePruductCategory bean,WmsInvePruductCategorySearchBeanVO BeanVO,WmsInvePruductYearPaySpecial paySpecial )
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvepruductcategoryService.updateall(bean, user,BeanVO,paySpecial);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-参数管理-理财产品-更新";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }
	/***
	* Description:获取理财产品编号
	* 
	* @return String
	* @author baisong
	*/
    @RequestMapping(value = "/inve/getLCCode.do", method = { RequestMethod.GET })
    @ResponseBody
    public String getLCCode(HttpServletRequest request){
    	return wmsinvepruductcategoryService.getLCCode();
    }
    
    /**
     * Description :残品确认保存审批意见方法
     * 
     * @param BeanVO
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorydoSaveSp.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveSp(HttpServletRequest request, WmsInvePruductCategorySearchBeanVO BeanVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        BeanVO.setOperator_ip(SysUtil.getIP(request));
        try
        {
            result = wmsinvepruductcategoryService.doSaveSp(user,BeanVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-参数管理-理财产品-产品审批";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }
    /**
     * Description :残品启用意见方法
     * 
     * @param BeanVO bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorydoSaveQy.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveQy(HttpServletRequest request, WmsInvePruductCategoryLog beanlog,WmsInvePruductCategory bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        beanlog.setOperator_ip(SysUtil.getIP(request));
        try
        {
            result = wmsinvepruductcategoryService.doSaveQy(user,beanlog,bean);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-参数管理-理财产品-产品启用";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }
    
    /**
     * @Title: getProductIsForbidden 
     * @Description: 查看产品是否禁用
     * @param wms_inve_pruduct_category_id
     * @return    
     * @return Map<String,Object>    
     * @throws
     * @author lvtu 
     * @date 2015年8月21日 下午3:44:28
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorygetproductisforbidden.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getProductIsForbidden(Integer wms_inve_pruduct_category_id) {
        return wmsinvepruductcategoryService.getProductIsForbidden(wms_inve_pruduct_category_id);
    }
    
    /**
     * Description :查询理财产品，请选择使用‘-1’
     * @param key 传递的参数 决定要看那些产品信息
     * @return list
     * @author hancd
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorylistforselectKey.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsInvePruductCategory> getInvePruductCategoryInfo(String is_forbidden)
    {

        List<com.zx.emanage.util.gen.entity.WmsInvePruductCategory> wmsInvePruductCategory = wmsinvepruductcategoryService.getInvePruductCategoryInfo(is_forbidden);
        return wmsInvePruductCategory;
    }
    /**
     * @Title: isCheck 
     * @Description: 检查产品名称是否已经存在
     * @param WmsInvePruductCategory
     * @return    
     * @return Map<String,Object>    
     * @throws
     * @author baisong
     * @date 2015年9月7日 下午3:44:28
     */
    @RequestMapping(value = "/inve/categoryisCheck.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public String isCheck(WmsInvePruductCategory bean) {
        return wmsinvepruductcategoryService.isCheck(bean);
    }
    
    /**
     * @Title: getInvePruductCategorys 
     * @Description: 获取产品并按照产品类型分类
     * @return Map<String,Object> 
     * @throws
     * @author lvtu 
     * @date 2015年9月15日 上午9:41:48
     */
    @RequestMapping(value = "/inve/wmsinvepruductcategorys.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInvePruductCategorys() {

    	Map<String, Object> resMap = wmsinvepruductcategoryService.getInvePruductCategorys();
        return resMap;
    }
    /**
     * Description :获取全部启用产品信息
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/inve/getAllProductListWithoutPaging.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public  Map<String, Object> getAllProductListWithoutPaging(String cktype, String category_name, Integer wms_inve_pruduct_category_id)
    {
        return wmsinvepruductcategoryService.getAllProductListWithoutPaging(cktype, category_name, wms_inve_pruduct_category_id);
    }

    /**
     * @Title: getCategoryTypeByCategoryId
     * @Description: 根据产品id获得产品类型
     * @param categoryId 产品id 
     * @return 返回产品类型
     * @author: jinzhm
     * @time:2017年5月19日 上午9:28:42
     * history:
     * 1、2017年5月19日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getCategoryTypeByCategoryId.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCategoryTypeByCategoryId(Integer categoryId)
    {
        return wmsinvepruductcategoryService.getCategoryTypeByCategoryId(categoryId);
    }
}