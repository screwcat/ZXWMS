package com.zx.emanage.loanappro.service;

import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditFinalAppl;
import com.zx.sframe.util.vo.UserBean;

public interface WmsCreloanApproVisaInterviewService
{
    /**
     * * @version 2.0 终审审批---终审面签--面签页面 请求的controller 终审审批 ---审批 页面请求
     * 的controller Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditHeadVO bean, UserBean user, WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * @version 2.0 终审审批---终审面签--面签页面 请求的controller Description :add method
     * @param bean
     * @return "success" or "error" or user defined
     * @author hancd
     */
    public String saveInterview(WmsCreCreditHeadVO bean, UserBean user, WmsCreditWorkFlowVO approveWorkFlowVO,
                                String fileArrs);

    /**
     * * @version 2.0 终审审批--房产模块 终审审批--房产模块 Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String saveforhouse(WmsCreCreditFinalAppl bean, UserBean user, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, WmsCreCreditHeadVO beanVo);
    /**
     * * @version 2.0 终审审批--车模块 终审审批--车模块 Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public String saveforcar(WmsCreCreditHeadVO bean, UserBean user, WmsCarLoanWorkFlowVO wVo);
}
