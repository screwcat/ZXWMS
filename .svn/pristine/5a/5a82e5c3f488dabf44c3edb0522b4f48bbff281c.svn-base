package com.zx.emanage.inve.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveTransaIncomeService;
import com.zx.emanage.inve.vo.WmsInveTransaIncomeSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanImportVo;
import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTransaIncomeController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaIncomeController.class);

    @Autowired
    private IWmsInveTransaIncomeService wmsinvetransaincomeService;
    
    /**
     * @Title: computeCategoryIncome
     * @Description: 计算某个产品投资多少金额时的收益情况
     * @param categoryId 产品id
     * @param productAccount 投资金额（单位：万元）
     * @return 返回收益情况
     * @author: jinzhm
     * @time:2017年2月21日 上午11:58:30
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/computeCategoryIncomePad.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> computeCategoryIncome(Integer categoryId, Integer productAccount)
    {
        Map<String, Object> rMap = new HashMap<>();

        try
        {
            rMap.put("ret_code", "000");
            rMap.put("ret_msg", "操作成功");
            rMap.put("ret_data", wmsinvetransaincomeService.computeCategoryIncome(categoryId, productAccount));
        }
        catch (Exception e)
        {
            rMap.put("ret_code", "002");
            rMap.put("ret_msg", "操作失败，请联系客服");
        }
        return rMap;
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/inve/wmsinvetransaincomewithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInveTransaIncomeSearchBeanVO queryInfo)
    {
        return wmsinvetransaincomeService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaincomewithpaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInveTransaIncomeSearchBeanVO queryInfo)
    {
        return wmsinvetransaincomeService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaIncomeVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaincomeinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveTransaIncome getInfoByPK(Integer wms_inve_transa_income_id)
    {
        return wmsinvetransaincomeService.getInfoByPK(wms_inve_transa_income_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaincomesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveTransaIncome bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaincomeService.save(bean, user);
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
    @RequestMapping(value = "/inve/wmsinvetransaincomeupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveTransaIncome bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaincomeService.update(bean, user);
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
	 * 根据传入对象中的参数获取对应的数据并且更新调整金额信息
	 * @param wmsInveTransaIncome
	 * @param request
	 * @param response
	 * @return
	 * @author donghao
	 * @date 2016年10月9日13:53:54
	 */
	@RequestMapping(value="/inve/updateInveTransaIncome.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateInveTransaIncome(WmsInveTransaIncome wmsInveTransaIncome, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
    	UserBean user =(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		
		Map<String , Object> resultMap = wmsinvetransaincomeService.updateInveTransaIncome(wmsInveTransaIncome, user); 
		return resultMap;
	}
	
	/**
	 * 导出客户每月收益报表
	 * @param queryInfo
	 * @param request
	 * @param response
	 * @author donghao
	 * @date 2016年10月10日17:30:49
	 */
	@RequestMapping(value="/inve/expertWmsInveTransaIncomeInfo.do", method={RequestMethod.GET, RequestMethod.POST})
	public void expertWmsInveTransaIncomeInfo(WmsInveCustomerMonthlyIncomeBeanVo queryInfo, HttpServletRequest request, HttpServletResponse response){
		wmsinvetransaincomeService.expertWmsInveTransaIncomeInfo(queryInfo, response);
	}

    /**
     * 
     * @Title: expertWmsInveTransaIncomeInfo
     * @Description: 导入客户收益报表
     * @param queryInfo
     * @param request
     * @param response 
     * @author: jinzm
     * @time:2016年12月1日 下午3:23:23
     * history:
     * 1、2016年12月1日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/importWmsInveTransaIncomeInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> importWmsInveTransaIncomeInfo(WmsInveCustomerMonthlyIncomeBeanImportVo queryInfo, HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        queryInfo = wmsinvetransaincomeService.importWmsInveTransaIncomeInfo(request, response, queryInfo, user);
        resMap.put("result", queryInfo.getResult());
        resMap.put("err_num", queryInfo.getErr_num());
        resMap.put("flag", queryInfo.getFlag());
        return resMap;
    }

    /**
     * @Title: getCusIncomeWithCustomerSummary
     * @Description: 获取客户收益按客户汇总（查询我的客户收益或部门经理查询团队下某个业务员的客户收益）
     *      根据传入的查询时间及客户经理id查询按客户维度汇总的客户经理的客户收益信息
     * @param page 要查询页数
     * @param page_size 每页显示计数数
     * @param statics_month 查询月份
     * @param salesman_id 客户经理id
     * @param cus_name 客户姓名，支持准确查询
     * @return 按客户维度汇总的某个客户经理的客户收益信息
     * @author: jinzhm
     * @time:2017年1月3日 下午3:05:26
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getCusIncomeWithCustomerSummaryMoa.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCusIncomeWithCustomerSummary(Integer page, Integer page_size, String statics_month,
                                                               String salesman_id, String cus_name)
    {
        return wmsinvetransaincomeService.getCusIncomeWithCustomerSummary(page, page_size, statics_month, salesman_id,
                                                                          cus_name);
    }
    
    /**
     * @Title: getCusIncomeWithTransaSummary
     * @Description: 查询某一个客户在某一个月的单据收益信息集合
     * @param page 要查询页数
     * @param page_size 每页显示计数数
     * @param statics_month 查询月份
     * @param cus_id 客户id
     * @return 返回某一个客户在某一个月的单据收益信息集合
     * @author: jinzhm
     * @time:2017年1月3日 下午4:32:03
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getCusIncomeWithTransaSummaryMoa.do", method = { RequestMethod.GET,
                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCusIncomeWithTransaSummary(Integer page, Integer page_size, String statics_month,
                                                             String cus_id)
    {
        return wmsinvetransaincomeService.getCusIncomeWithTransaSummary(page, page_size, statics_month, cus_id);
    }

    /**
     * @Title: getCusIncomeWithSalesmanSummary
     * @Description: 查询某个月团队下所有客户经理维度汇总的收益信息集合
     * @param page 要查询页数
     * @param page_size 每页显示计数数
     * @param statics_month 查询月份
     * @param team_id 团队id（多个用英文逗号隔开）
     * @param salesman_name 客户经理姓名或短工号，支持准确查询
     * @return 返回某个月团队下所有客户经理维度汇总的收益信息集合
     * @author: jinzhm
     * @time:2017年1月3日 下午5:38:05
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getCusIncomeWithSalesmanSummaryMoa.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCusIncomeWithSalesmanSummary(Integer page, Integer page_size, String statics_month,
                                                               String team_ids, String salesman_name)
    {
        return wmsinvetransaincomeService.getCusIncomeWithSalesmanSummary(page, page_size, statics_month, team_ids,
                                                                          salesman_name);
    }
}