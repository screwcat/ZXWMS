package com.zx.emanage.loanpost.web;

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

import com.zx.emanage.loanfina.vo.WmsFinaCreRepaySearchBeanVO;
import com.zx.emanage.loanpost.service.IWmsPostDunningHeadService;
import com.zx.emanage.loanpost.vo.WmsAllocationSearchBeanVO;
import com.zx.emanage.loanpost.vo.WmsPostDunningDetailedSearchBeanVO;
import com.zx.emanage.loanpost.vo.WmsPostDunningHeadSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneMain;
import com.zx.emanage.util.gen.entity.WmsPostDunningDetailed;
import com.zx.emanage.util.gen.entity.WmsPostDunningHead;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsPostDunningHeadController {
    private static Logger log = LoggerFactory.getLogger(WmsPostDunningHeadController.class);
    
    @Autowired
    private IWmsPostDunningHeadService wmspostdunningheadService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanpost/wmspostdunningheadwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsPostDunningHeadSearchBeanVO queryInfo) {
        return wmspostdunningheadService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanpost/wmspostdunningheadwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsPostDunningHeadSearchBeanVO queryInfo) {
        return wmspostdunningheadService.getListWithPaging(queryInfo);
    }
    
    /**
     * Description :get single-table information by primary key 
     * @param primary key 
     * @return WmsPostDunningHeadVO
     * @author baisong
     */ 
    @RequestMapping(value = "/loanpost/wmspostdunningheadinfobypk.do", method = {RequestMethod.GET})
    @ResponseBody
    public WmsPostDunningHead getInfoByPK(Integer wms_post_dunning_head_id) {
        return wmspostdunningheadService.getInfoByPK(wms_post_dunning_head_id);
    }   
    /**
     * Description :getlist
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */ 
    @RequestMapping(value = "/loanpost/getListByEntityHead.do", method = {RequestMethod.GET})
    @ResponseBody
    private List<WmsPostDunningHead> getListByEntity(WmsPostDunningHead queryInfo, Boolean isExcludePKFlag) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsPostDunningHead> beanList = wmspostdunningheadService.getListByEntity(queryInfo,isExcludePKFlag);
        return beanList;
    }
    /**
     * Description :add method
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */ 
    @RequestMapping(value = "/loanpost/wmspostdunningheadsave.do", method = {RequestMethod.POST})
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsPostDunningHead bean) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmspostdunningheadService.save(bean, user);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        /*
        // record log   
        if("success".equals(result)){
            String msg = "log content";
            SysTools.saveLog(request, msg); // record log method
        }
        */
        return result;
    }
    
    /**
     * Description :update method
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */ 
    @RequestMapping(value = "/loanpost/wmspostdunningheadupdate.do", method = {RequestMethod.POST})
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsPostDunningHead bean) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmspostdunningheadService.update(bean, user);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        /*          
        // record log   
        if("success".equals(result)){
            String msg = "log content";
            SysTools.saveLog(request, msg); // record log method
        }
        */
        return result;
    }
    /**
     * 电话催缴   催缴单信息保存更新
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */ 
    @RequestMapping(value = "/loanpost/wmspostdunningheadsaveupdate.do", method = {RequestMethod.POST})
    @ResponseBody
    public String doSaveUpdate(HttpServletRequest request, WmsPostDunningHead bean,WmsPostDunningDetailed beanDetailed,WmsPostDunningDetailedSearchBeanVO wmsPostDunningDetailedSearchBeanVO) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmspostdunningheadService.saveUpdate(bean, user,beanDetailed,wmsPostDunningDetailedSearchBeanVO );                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
                    
        // record log   
        if("success".equals(result)){
            String msg = "电话催缴-保存";
            SysTools.saveLog(request, msg); // record log method
        }
        
        return result;
    }   
    /**
     * 上门催缴   催缴单信息保存更新
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */ 
    @RequestMapping(value = "/loanpost/wmspostdunningheadsaveupdateVisit.do", method = {RequestMethod.POST})
    @ResponseBody
    public String doSaveUpdateVisit(HttpServletRequest request, WmsPostDunningHead bean,WmsPostDunningDetailed beanDetailed,WmsPostDunningDetailedSearchBeanVO wmsPostDunningDetailedSearchBeanVO) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmspostdunningheadService.doSaveUpdateVisit(bean, user,beanDetailed,wmsPostDunningDetailedSearchBeanVO );                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
                    
        // record log   
        if("success".equals(result)){
            String msg = "上门催缴-保存";
            SysTools.saveLog(request, msg); // record log method
        }
        
        return result;
    }   
    /**
     * Description :获取催缴单据审核列表显示
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = "/loanpost/wmspostdunningreviewwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getPostDunningReviewListWithPaging(WmsPostDunningHeadSearchBeanVO queryInfo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmspostdunningheadService.getPostDunningReviewListWithPaging(queryInfo,user);
    }
    /**
     * Description :通过催缴单ID获取详细信息
     * @param primary key 
     * @return WmsPostDunningHeadVO
     * @author hancd
     */ 
     @RequestMapping(value = "/loanpost/wmspostdunningheadbypk.do", method = {RequestMethod.GET})
     @ResponseBody
     public Map<String,Object> getWmsPostDunningHeadInfoByPK(Integer wms_post_dunning_head_id) {
         return wmspostdunningheadService.getWmsPostDunningHeadInfoByPK(wms_post_dunning_head_id);
     }
     /**
      * Description :获取催缴单历史信息--逾期还款确认驳回单据使用
      * @param primary key 
      * @return map
      * @author baisong
      */ 
      @RequestMapping(value = "/loanpost/wmspostdunningheadbyHk.do", method = {RequestMethod.GET})
      @ResponseBody
      public Map<String,Object> getWmspostDunningheadInfoByHK(HttpSession session, Integer wms_post_dunning_head_id) {
    	 UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
         return wmspostdunningheadService.getWmspostDunningheadInfoByHK(wms_post_dunning_head_id, user);
     }
    	  
     /**
      * Description :通过催缴单ID获催缴详细信息
      * @param primary key 
      * @return WmsPostDunningHeadVO
      * @author hancd
      */ 
     @RequestMapping(value = "/loanpost/wmspostdunningheadinfobypkk.do", method = {RequestMethod.GET})
     @ResponseBody
     public Map<String,Object> getWmspostDunningheadInfoByPK(HttpSession session, Integer wms_post_dunning_head_id) {
    	 UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
         return wmspostdunningheadService.getWmspostDunningheadInfoByPK(wms_post_dunning_head_id, user);
     }   
        /**
         * Description :实现催缴单审核结果以及详细信息的存储
         * @param bean
         * @param flag  判断暂存和提交标识  0代表暂存  1代表提交
         * @param taskId 流程任务ID
         * @return "success" or "error" or user defined
         * @author hancd
         */ 
        @RequestMapping(value = "/loanpost/wmspostdunningheadreviewupdate.do", method = {RequestMethod.POST})
        @ResponseBody
        public String doReviewUpdate(HttpServletRequest request, WmsPostDunningHead bean,WmsPostDunningHeadSearchBeanVO queryInfo,String flag) {
            String result = "";
            HttpSession session = request.getSession();
            UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
            try {
                result = wmspostdunningheadService.reviewUpdate(bean,queryInfo,user,flag);
            } catch (Exception e) {
                log.error(e.getMessage());
                result = "error";
            }
            if("success".equals(result)){
                String msg = "服务管理-催缴管理-电催确认审核";
                SysTools.saveLog(request, msg);
            }
            return result;
        }
        /**
         * Description :实现催缴单据审核页面数据的导出
         * @author hancd
         */
        @RequestMapping(value = "/loanpost/wmspostdunningreviewwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
        @ResponseBody
        public Map<String, Object> getReviewWithoutPagingList(HttpServletRequest request,WmsPostDunningHeadSearchBeanVO queryInfo) {
            HttpSession session = request.getSession();
            UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
            return wmspostdunningheadService.getReviewWithoutPagingList(queryInfo,user);
        }
        /**
        * Description :实现获取上门催缴跟踪列表显示数据
        * @param queryInfo
        * @return record list
        * @author hancd
        */
        @RequestMapping(value = "/loanpost/wmspostdunninghomewithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
        @ResponseBody
        public Map<String, Object> getPostDunningHomeListWithPaging(WmsPostDunningHeadSearchBeanVO queryInfo,HttpServletRequest request) {
            HttpSession session = request.getSession();
            UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
            return wmspostdunningheadService.getPostDunningHomeListWithPaging(queryInfo,user);
        }
        /**
         * Description :实现获取上门催缴跟踪列表的导出
         * @author hancd
         */
        @RequestMapping(value = "/loanpost/wmspostdunninghomewithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
        @ResponseBody
        public Map<String, Object> getHomeWithoutPagingList(HttpServletRequest request,WmsPostDunningHeadSearchBeanVO queryInfo) {
             HttpSession session = request.getSession();
             UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
             return wmspostdunningheadService.getHomeWithoutPagingList(queryInfo,user);
        }
            /**
             * Description :实现上门催缴确认的数据保存
             * @param bean
             * @param flag  判断暂存和提交标识  0代表暂存  1代表提交
             * @param taskId 流程任务ID
             * @return "success" or "error" or user defined
             * @author hancd
             */ 
            @RequestMapping(value = "/loanpost/wmspostdunningheadhomesave.do", method = {RequestMethod.POST})
            @ResponseBody
            public String doHomeSave(HttpServletRequest request, WmsPostDunningHead bean,WmsPostDunningHeadSearchBeanVO queryInfo,String flag,String taskId) {
                String result = "";
                HttpSession session = request.getSession();
                UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
                try {
                    result = wmspostdunningheadService.doHomeSave(bean,queryInfo,user,flag,taskId);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    result = "error";
                }
                if("success".equals(result)){
                    String msg = "服务管理-催缴管理-上门催缴跟踪";
                    SysTools.saveLog(request, msg);
                }
                return result;
            }   
    /**
     * Description : 查询催缴人员名单’
     * 
     * @param primary key
     * @return WmsSysDictData
     * @author auto_generator
     */
    @RequestMapping(value = "/loanpost/wmspostdunningheadnamelist.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<Map<String,Object>> getDictDataByDictIdEmpty()
    {
    	List<Map<String,Object>> wmsSysDictDataList = wmspostdunningheadService.getDictDataByDictIdEmpty();
        return wmsSysDictDataList;
    }
    /**
     * Description : 查询催缴人员名单
     * 
     * @param primary key
     * @return int
     * @author  baisong
     */
    @RequestMapping(value = "/loanpost/seachCount.do", method = { RequestMethod.GET })
    @ResponseBody
    public int seachCount(HttpServletRequest request,WmsFinaCreRepaySearchBeanVO queryInfo)
    {
    	  HttpSession session = request.getSession();
          UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
    	 int number = wmspostdunningheadService.seachCount(queryInfo,user);
        return number;
    }
    /**
	 * 贷后管理-催缴管理 --单据分配
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	@RequestMapping(value = "/loanpost/allocationNnumber.do", method = {RequestMethod.POST})
	@ResponseBody
	public String allocationNnumber(HttpServletRequest request, WmsAllocationSearchBeanVO wmsAllocationSearchBeanVO,WmsFinaCreRepaySearchBeanVO queryInfo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmspostdunningheadService.allocationNnumber(wmsAllocationSearchBeanVO ,queryInfo);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}		
		// record log	
		if("success".equals(result)){
			String msg = "单据分配-保存";
			SysTools.saveLog(request, msg); // record log method
		}
		
		return result;
	}
		/***
	     * Description:判断是否进行过电话催缴提交操作
	     * @param taskId
	     * @return String
	     * @author baisong
	     * 
	     * 修改记录：当校验电话催缴状态的时候，增加当前操作人ID
	     * 修改时间:2015-5-30
	     * 修改人：hancd
	     */
		@RequestMapping(value = "/loanpost/ischeck.do", method = {RequestMethod.GET})
		@ResponseBody
		public String ischeck(HttpServletRequest request, WmsPostDunningHead bean) {
			String result = "";
			 HttpSession session = request.getSession();
		     UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		     bean.setFinal_dunning_id(user.getUserId());
			try {
				result = wmspostdunningheadService.ischeck(bean);
			} catch (Exception e) {
				log.error(e.getMessage());
				result = "error";
			}		
			return result;
		}
		/**
	     * 服务管理--贷后管理--贷后查询数据显示
	     * @param queryInfo
	     * @return record list
	     * @author hancd
	     */
	    @RequestMapping(value = "/loanpost/postLoanSearchWithPagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
	    @ResponseBody
	    public Map<String, Object> getPostLoanSearchListWithPaging(HttpServletRequest request,WmsPostDunningHeadSearchBeanVO queryInfo) {
	        HttpSession session = request.getSession();
	        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
	        return wmspostdunningheadService.getPostLoanSearchListWithPaging(queryInfo,user);
	    }
	    /**
	     * 服务管理--贷后管理--贷后查询导出数据功能
	     * @param queryInfo
	     * @return recod list
	     * @author hancd
	     */
	    @RequestMapping(value="/loanpost/postLoanSearchWithoutPagingList.do",method = {RequestMethod.GET, RequestMethod.POST})
	    @ResponseBody
	    public Map<String, Object> getPostLoanSearchWithoutPagingList(HttpServletRequest request,WmsPostDunningHeadSearchBeanVO queryInfo) {
	        HttpSession session = request.getSession();
	        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
	        return wmspostdunningheadService.getPostLoanSearchWithoutPagingList(queryInfo,user);
	    }
	    /**
	     * 服务管理--贷后管理-贷后查询-页面查看电审意见
	     */
	    @RequestMapping(value = "/loanpost/getTelTeamAdvice.do", method = {RequestMethod.GET})
	    @ResponseBody
	    public WmsCreRevPhoneMain getTelTeamAdvice(Integer wms_cre_credit_head_id) 
	    {
	        return wmspostdunningheadService.getTelTeamAdvice(wms_cre_credit_head_id);    
	    }  
	    /**
         * Description :逾期确认查询列表 催缴状态弹出页面保存
         * @param WmsPostDunningHead 
         * @return "success" or "error" or user defined
         * @author baisong
         * 
         * 修改记录：保存后，记录日志的信息修订：
         *           原：服务管理-催缴管理-电催确认审核  (不准确)
         *           新：财务管理-逾期还款确认管理-财务驳回操作
         * 修改时间:2015-5-30
         * 修改人：hancd
         */ 
        @RequestMapping(value = "/loanpost/wmspostdunningheaddoUpdateForReject.do", method = {RequestMethod.POST})
        @ResponseBody
        public String doUpdateForReject(HttpServletRequest request, WmsPostDunningHead bean) {
            String result = "";
            HttpSession session = request.getSession();
            UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
            try {
                result = wmspostdunningheadService.doUpdateForReject(bean,user);
            } catch (Exception e) {
                log.error(e.getMessage());
                result = "error";
            }
            if("success".equals(result)){
                String msg = "财务管理-逾期还款确认管理-财务驳回操作";
                SysTools.saveLog(request, msg);
            }
            return result;
        }
        
        @RequestMapping(value = "/loanpost/wmspostremindersearchwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
        @ResponseBody
        public Map<String, Object> getReminderSearchListWithPaging(WmsPostDunningHeadSearchBeanVO queryInfo, HttpSession session) {
        	UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
            return wmspostdunningheadService.getReminderSearchList(queryInfo, user);
        }
        
        @RequestMapping(value = "/loanpost/wmspostremindersearchwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
        @ResponseBody
        public Map<String, Object> getReminderSearchListWithoutPage(WmsPostDunningHeadSearchBeanVO queryInfo, HttpSession session) {
        	UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
            return wmspostdunningheadService.getReminderSearchListWithoutPage(queryInfo, user);
        }
        
        /**
         * Description :获取催缴单列表（贷后查询-查看-催缴单列表）
         * @param queryInfo
         * @return record list
         * @author hancd
         */
        @RequestMapping(value = "/loanpost/getReminderListBychid.do", method = {RequestMethod.GET, RequestMethod.POST})
        @ResponseBody
        public Map<String, Object> getReminderListBychid(Integer wms_cre_credit_head_id) {
            return wmspostdunningheadService.getReminderListBychid(wms_cre_credit_head_id);
        }
		
        /**
         * Description :跟踪处理结果（贷后查询-查看-跟踪处理结果）
         * @param primary key 
         * @return WmsPostDunningHeadVO
         * @author hancd
         */ 
         @RequestMapping(value = "/loanpost/wmspostdunningprocessingoftracking.do", method = {RequestMethod.GET})
         @ResponseBody
         public List<Map<String, Object>> getProcessIngofTracking(Integer wms_cre_credit_head_id) {
             return wmspostdunningheadService.getProcessIngofTracking(wms_cre_credit_head_id);
         }
}