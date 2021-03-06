package com.zx.emanage.loanappro.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;

import org.activiti.engine.ActivitiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.service.IWmsCreApproBorrowProtocolService;
import com.zx.emanage.loanappro.service.IWmsCreApproProtocolAttachService;
import com.zx.emanage.loanappro.vo.WmsCreApproBorrowProtocolSearchBeanVO;
import com.zx.emanage.loanappro.vo.WmsCreApproBorrowProtocolSearchConditionBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsCreApproProtocolAttach;
import com.zx.emanage.util.gen.entity.WmsCreApproProtocolSecured;
import com.zx.emanage.util.gen.entity.WmsCreApproServiceProtocol;
import com.zx.emanage.util.gen.entity.WmsCrePersonnelInfo;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.sframe.util.CopyFileUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.PoiExportForWord;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreApproBorrowProtocolController
 * 模块名称：合同
 * @Description: 内容摘要：合同
 * @author baisong
 * @date 2016年12月29日
 * @version V1.0
 * history:
 * 1、2016年12月29日 baisong 创建文件
 */
@Controller
public class WmsCreApproBorrowProtocolController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreApproBorrowProtocolController.class);

    @Autowired
    private IWmsCreApproBorrowProtocolService wmscreapproborrowprotocolService;
    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;// 流程
    @Autowired
    private IWmsCreApproProtocolAttachService wmscreapproprotocolattachservice;
    
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproborrowprotocolwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreApproBorrowProtocolSearchBeanVO queryInfo)
    {
        return wmscreapproborrowprotocolService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproborrowprotocolwithpaginglist.do", method = { RequestMethod.GET,
                                                                                               RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreApproBorrowProtocolSearchBeanVO queryInfo)
    {
        return wmscreapproborrowprotocolService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreApproBorrowProtocolVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproborrowprotocolinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreApproBorrowProtocol getInfoByPK(Integer wms_cre_appro_id)
    {
        return wmscreapproborrowprotocolService.getInfoByPK(wms_cre_appro_id);
    }

    /**
     * Description :通过wms_cre_credit_head_id获取对应的合同信息
     * 
     * @param primary wms_cre_credit_head_id
     * @return WmsCreApproBorrowProtocol
     * @author zhubo
     */
    @RequestMapping(value = "/loanappro/wmscreapproborrowprotocolinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreApproBorrowProtocol getBorrowProtocolByFK(Integer wms_cre_credit_head_id)
    {

        return wmscreapproborrowprotocolService.getBorrowProtocolByFK(wms_cre_credit_head_id);
    }
    /**
     * Description :通过wms_cre_credit_head_id获取对应的合同信息
     * 
     * @param primary wms_cre_credit_head_id
     * @return int
     * @author baisong
     */
    @RequestMapping(value = "/loanappro/getnumberbyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public int getNumberByFK(Integer wms_cre_credit_head_id)
    {

        return wmscreapproborrowprotocolService.getNumberByFK(wms_cre_credit_head_id);
    }

    /**
     * 保存借款合同信息
     * 
     * @param
     * @return
     * @author 张风山
     */
    @RequestMapping(value = "/loanappro/wmscreapproborrowprotocolsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreApproBorrowProtocol bean,
                         WmsCreApproServiceProtocol wmsCreApproServiceProtocol, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapproborrowprotocolService.saveBorrow(bean, user, wmsCreApproServiceProtocol,
                                                                 approveWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "终审管理-信贷终审-合同签订-借款合同保存";
            SysTools.saveLog(request, msg);
            String msg2 = "合同打印@@@" + bean.getWms_cre_credit_head_id() + "@@@" + bean.getProtocol_type() + "@@@"
                          + bean.getProtocol_id_year_num();
            SysTools.saveLog(request, msg2);
        }
        return result;
    }
    /**
     * 保存借款合同信息--车贷
     * 
     * @param
     * @return
     * @author baisong
     */
    @RequestMapping(value = "/loanappro/wmscreapproborrowprotocolsaveCar.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveCar(HttpServletRequest request, WmsCreApproBorrowProtocol bean,
                         WmsCreApproServiceProtocol wmsCreApproServiceProtocol, WmsCarLoanWorkFlowVO wVo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapproborrowprotocolService.saveBorrowCar(bean, user, wmsCreApproServiceProtocol,wVo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "终审管理-车贷终审-合同签订-借款合同保存";
            SysTools.saveLog(request, msg);
            String msg2 = "合同打印@@@" + bean.getWms_cre_credit_head_id() + "@@@" + bean.getProtocol_type() + "@@@"
                          + bean.getProtocol_id_year_num();
            SysTools.saveLog(request, msg2);
        }
        return result;
    }

    /**
     * 借款合同信息更新
     * 
     * @param
     * @return
     * @author 张风山
     */
    @RequestMapping(value = "/loanappro/wmscreapproborrowprotocolupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreApproBorrowProtocol bean,
                           WmsCreApproServiceProtocol wmsCreApproServiceProtocol, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapproborrowprotocolService.update(bean, user, wmsCreApproServiceProtocol, approveWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "终审管理-信贷终审-终审面签-借款合同更新";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }

    /**
     * 借款合同信息查询
     * 
     * @param
     * @return
     * @author 张风山
     */
    @RequestMapping(value = "/loancheck/wmscreapproborrowprotocolQuery.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> showTelTeamCheck(HttpServletRequest request, String wms_cre_credit_head_id)
    {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscreapproborrowprotocolService.selectAllByWmsCreCreditHeadId(wms_cre_credit_head_id, user);
    }

    /**
     * 判断合同编号是否重复
     * 
     * @param
     * @return
     * @author 张风山
     */
    @RequestMapping(value = "/loancheck/wmscreapproborrowprotocolJudgeProtocolId.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> telTeamCheckJudgeProtocolId(HttpServletRequest request, String judgeString)
    {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscreapproborrowprotocolService.selectAllByProtocolId(judgeString, user);
    }

    /**
     * 保存借款合同信息--房产终审---签合同
     * 
     * @param
     * @return
     * @author baisong
     */
    @RequestMapping(value = "/loanappro/wmscreapproborrowprotocolsaveforhouse.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveforhouse(HttpServletRequest request, WmsCreApproBorrowProtocol bean,
                                 WmsCrePersonnelInfo person, WmsCreApproServiceProtocol service, String person_name1,
                                 String person_identity_id1, String person_name2, String person_identity_id2,
                                 WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String tablevalue, String comnumval,
                                 WmsCreApproProtocolSecured wmsCreApproProtocolSecured)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapproborrowprotocolService.saveforhouse(bean, user, person, service, person_name1,
                                                                   person_identity_id1, person_name2,
                                                                   person_identity_id2, approveHouseWorkFlowVO,
                                                                   tablevalue, comnumval,wmsCreApproProtocolSecured);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "终审管理-房产终审-签合同-借款合同保存";
            SysTools.saveLog(request, msg);
            String msg2 = "合同打印@@@" + bean.getWms_cre_credit_head_id() + "@@@" + bean.getProtocol_type() + "@@@"
                          + bean.getProtocol_id_year_num();
            SysTools.saveLog(request, msg2);
        }
        return result;
    }

    /**
     * for 房产终审 合同查询列表
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanappro/wmssyspropertyobjforhouse.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getObjforhouse(Integer wms_cre_credit_head_id)
    {
        // List<Integer> idss =new ArrayList()
        return wmscreapproborrowprotocolService.searchobjforhouse(wms_cre_credit_head_id);
    }
    
    /**
     * -车贷--放款申请
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanappro/wmssyspropertyobjforcar.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getObjforCar(Integer wms_cre_credit_head_id)
    {
        // List<Integer> idss =new ArrayList()
        return wmscreapproborrowprotocolService.searchobjforCar(wms_cre_credit_head_id);
    }


    /**
     * for 合同打印保存日志
     * 
     * @return success
     * @author ry
     */
    @RequestMapping(value = "/loanappro/wmssyspropertysavelogforoprint.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public String saveLogForPrint(HttpServletRequest request, Integer wms_cre_credit_head_id, Integer htType,
                                  String htbhVal,String confirm_house_value,WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        String result = "success";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        WmsCreApproProtocolAttach attach = new WmsCreApproProtocolAttach();
        attach.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        attach.setConfirm_house_value(confirm_house_value);
        String res = wmscreapproprotocolattachservice.updateAttach(attach,approveHouseWorkFlowVO,user);
        if(res.equals("error")){
    	   result = "error";
        }
        try
        {
            String msg = "合同打印@@@" + wms_cre_credit_head_id + "@@@" + htType + "@@@" + htbhVal;
            SysTools.saveLog(request, msg);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        return result;
    }
 
    /**
     * Description : 借款合同返回pdf
     * 
     * @param Integer
     * @return Map<String, Object>
     * @author baisong
     * @throws FileNotFoundException 
     * @throws JRException 
     */
    @RequestMapping(value = "/wms/ireportpdf.pdf", method = { RequestMethod.GET, RequestMethod.POST })  
    public ModelAndView showReport(ModelMap model, HttpServletRequest request,WmsCreApproBorrowProtocolSearchBeanVO beanVO) throws FileNotFoundException, JRException {  
    	Map<String,Object> parameterMap = wmscreapproborrowprotocolService.searchinfoforhouse(beanVO.getWms_cre_credit_head_id());
        JREmptyDataSource emptyData = new JREmptyDataSource();  
        parameterMap.put("datasource", emptyData); 
    	String fileurl ="";
        // 打印合同版本 1为本公司合同2为房产局合同
        if ("1".equals(beanVO.getProtocol_num()))
        {
    		//jasper文件路径
    		if(parameterMap!=null&&parameterMap.get("jasper_name")!=null){
    			fileurl=parameterMap.get("jasper_name").toString();
    		}
    		//备用代码
    		if("".equals(fileurl)&&"2".equals(parameterMap.get("payment_contract_type").toString())){
    			//fileurl="creditBorrowProtocol2.jasper";
    			fileurl="simpleReport2";
    			
    		}else if("".equals(fileurl)&&"1".equals(parameterMap.get("payment_contract_type").toString())){
    			fileurl="simpleReport1";
    			//fileurl="creditBorrowProtocol1.jasper";
    		}else if("".equals(fileurl)){
    			return null;
    		}
        }
        // 打印合同版本 1为本公司合同2为房产局合同
        else if ("2".equals(beanVO.getProtocol_num()))
        {
            // jasper文件路径
            if (parameterMap != null && parameterMap.get("fcj_jasper_name") != null)
            {
                fileurl = parameterMap.get("fcj_jasper_name").toString();
            }
            // 备用代码
            if ("".equals(fileurl))
            {
                fileurl = "fcj_simpleReport2";
            }
    	}
		/*HttpSession session = request.getSession();
	    UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);*/
	    try
        {
            String msg = "合同打印@@@" + beanVO.getWms_cre_credit_head_id()+ "@@@" + 2 + "@@@" + parameterMap.get("protocol_type");
            SysTools.saveLog(request, msg);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        return new ModelAndView(fileurl, parameterMap); //simpleReport和views.properties配置的相同  
    }  
    
    
    /**
     * 房贷合同结束流程 新版合同 ireport
     * @param approveHouseWorkFlowVO
     * @param user
     * @return
     * @author baisong 
     * @date 20160902
     */
    @RequestMapping(value = "/loanappro/updateHTQDworkflow.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public String updateHTQDworkflow(HttpServletRequest request, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        String result = "success";
        try
        {  try
            {
                HttpSession session = request.getSession();
                UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
                String res = wmscreapproprotocolattachservice.updateHTQDworkflow(approveHouseWorkFlowVO, user);
                // 判断结果
                if (res.equals("error"))
                {
                    result = "error";
                }
                String msg = "合同完善打印结束@@@" + approveHouseWorkFlowVO.getWms_cre_credit_head_id();
                SysTools.saveLog(request, msg);
            }
            catch (ActivitiException e)
            {
                log.error(e.getMessage());
                //流程报错
                result = "activitierror";
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }
    
    /**
     * 合同信息完善保存
     * 
     * @param bean
     * @return "success" or "error"
     * @author jiaodelong
     */
    @RequestMapping(value = "/loancheck/perfectContractSave.do", method = { RequestMethod.POST,RequestMethod.GET })
    @ResponseBody
    public String perfectContractSave(HttpServletRequest request,WmsCreApproBorrowProtocolSearchBeanVO vo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapproborrowprotocolService.perfectContractSave(user,vo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "终审管理-合同信息完善";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    
    
    /**
     * 判断是否保存过合同
     * 
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/loanappro/wmsPerfectContractCount.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> wmsPerfectContractCount(HttpServletRequest request,Integer wms_cre_credit_head_id)
    {
        return wmscreapproborrowprotocolService.wmsPerfectContractCount(wms_cre_credit_head_id);
    }
    
    /**
     * 放款申请 获取贷款额度
     * @param queryInfo
     * @return map
     * @author wangyihan
     */
    @RequestMapping(value = "/loanappro/getApproLimit.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getApproLimit(HttpServletRequest request, WmsCreApproBorrowProtocolSearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> resMap = this.wmscreapproborrowprotocolService.getApproLimit(user, queryInfo);
        return resMap;
    }

    /**
     * 
     * @Title: exportWordForContract
     * @Description: TODO(合同下载 word)
     * @param request
     * @param response
     * @param beanVO
     * @throws Exception 
     * @author: wangyihan
     * @time:2017年1月3日 下午2:59:22
     * history:
     * 1、2017年1月3日 wangyihan 创建方法
     */
    @RequestMapping(value = "/loanappro/exportWordForContract.do", method = { RequestMethod.GET, RequestMethod.POST })
    public void exportWordForContract(HttpServletRequest request, HttpServletResponse response, WmsCreApproBorrowProtocolSearchBeanVO beanVO) throws Exception{
    	//获取数据
    	Map<String, Object> parameterMap = wmscreapproborrowprotocolService.searchinfoforhouse(beanVO.getWms_cre_credit_head_id());

		File downFiles = null;
        // 判断是本公司合同还是房产局合同 1为本公司 2为房产局
        if ("1".equals(beanVO.getProtocol_num()))
        {
            // 1：等额本息 2：先息后本
            downFiles = new File(WmsCreApproBorrowProtocolController.class.getResource("/").getPath() + "jasper/word/" + parameterMap.get("word_name").toString());
        }
        // 判断是本公司合同还是房产局合同 1为本公司 2为房产局
        else if ("2".equals(beanVO.getProtocol_num()))
        {
            // 1：等额本息 2：先息后本
            downFiles = new File(WmsCreApproBorrowProtocolController.class.getResource("/").getPath() + "jasper/word/" + parameterMap.get("fcj_word_name").toString());
        }
		/*if("2".equals(parameterMap.get("payment_contract_type").toString())) {//;
			downFiles = new File(WmsCreApproBorrowProtocolController.class.getResource("/").getPath() + "jasper/word/合同模板(等额等息).doc");
		} else if("1".equals(parameterMap.get("payment_contract_type").toString())) {
			downFiles = new File(WmsCreApproBorrowProtocolController.class.getResource("/").getPath() + "jasper/word/合同模板(先息后本).doc");
		}*/
	
        if(!downFiles.exists()) {
            return;
        }
		
		String copyFileName = PlatformGlobalVar.SYS_PROPERTIES.get("realDownloadCatalog") + "/" + downFiles.getName();
        CopyFileUtil.copyFile(downFiles.getPath(), copyFileName, true);
    	
        //替换文档中的占位符$P{key}
        PoiExportForWord.replaceWordValueByMap(parameterMap, copyFileName);
        //下载
        PoiExportForWord.downLoadWord(downFiles, copyFileName, request, response);
        
    }
    
    /**
     * 合同列表
     * @param queryInfo
     * @return
     */
    @RequestMapping(value="/loanappro/wmscreapproborrowprotocollist.do",method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getBorrowProtocolList(HttpServletRequest request, WmsCreApproBorrowProtocolSearchConditionBeanVO queryInfo){
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscreapproborrowprotocolService.getProtocolList(user, queryInfo);
	}
	/**
	 * 下载合同excel
	 * @param response
	 * @param search_params
	 */
	@RequestMapping(value="/loanappro/wmscreapproborrowprotocolExportExcel.dos",method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void protocolExportExcel(HttpServletRequest request, HttpServletResponse response,String search_params){
		HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		wmscreapproborrowprotocolService.protocolExportExcel(user, response, search_params);
	}
    
	/**
	 * 
	 * @Title: getBorrowProtocolDetails
	 * @Description: TODO(根据单据主表id获取合同信息)
	 * @param request
	 * @param response
	 * @param wms_cre_credit_head_id
	 * @return 
	 * @author: handongchun
	 * @time:2017年3月29日 下午1:52:18
	 * history:
	 * 1、2017年3月29日 handongchun 创建方法
	 */
	@RequestMapping(value="/loanappro/getBorrowProtocolDetails.do",method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getBorrowProtocolDetails(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam(value="wms_cre_credit_head_id",defaultValue="0") int wms_cre_credit_head_id){
        if(wms_cre_credit_head_id==0){
            return null;
        }
	    HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscreapproborrowprotocolService.getBorrowProtocolDetails(user, wms_cre_credit_head_id);
    }
	
	@RequestMapping(value="/loanappro/findPreviousBorrowProtocol.do",method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, String> findPreviousBorrowProtocol(HttpServletRequest request,
                                         @RequestParam(value="refund_name",defaultValue="") String refund_name,
                                         @RequestParam(value="debtor_identity_id",defaultValue="") String debtor_identity_id,
                                         @RequestParam(value="wms_cre_credit_head_id",defaultValue="") String wms_cre_credit_head_id){
        //参数字典
	    Map<String, String> map = new HashMap<>();
	    map.put("refund_name", refund_name);
	    map.put("debtor_identity_id", debtor_identity_id);
	    map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        return wmscreapproborrowprotocolService.findPreviousBorrowProtocol(map);
    }
	
	/**
     * 验证合同编号是否重复
     * 
     * @param bean
     * @return "success" or "error"
     * @author jiaodelong
     */
    @RequestMapping(value = "/loancheck/validateProtocolIdYearNum.do", method = { RequestMethod.POST,RequestMethod.GET })
    @ResponseBody
    public String validateProtocolIdYearNum(HttpServletRequest request,
                                            @RequestParam(value="wms_cre_credit_head_id",defaultValue="-1") int wms_cre_credit_head_id,
                                            @RequestParam(value="protocol_id_year_num") String protocol_id_year_num,
                                            @RequestParam(value="bill_status") String bill_status)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        result = wmscreapproborrowprotocolService.validateProtocolIdYearNum(user,wms_cre_credit_head_id,protocol_id_year_num,bill_status);
        return result;
    }
}
