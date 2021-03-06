package com.zx.emanage.loanappro.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.vo.WmsCreCreditCopyBeanVO;
import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditGroupDao;
import com.zx.emanage.loanappro.service.IWmsCreCreditGroupService;
import com.zx.emanage.loanappro.vo.WmsCreCreditGroupSearchBeanVO;
import com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsCreCreditGroup;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditgroupService")
public class WmsCreCreditGroupServiceImpl implements IWmsCreCreditGroupService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditGroupServiceImpl.class);

	@Autowired
	private WmsCreCreditGroupDao wmscrecreditgroupDao;
	@Autowired
	private WmsCreCreditHeadDao wmscrecreditheadDao;
	@Autowired
	private IWmsCreCreditHeadService wmsCreCreditHeadService;
	@Autowired
    private WmsCreApproBorrowProtocolDao WmsCreApproBorrowProtocolDao;
	
	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCreditGroupSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecreditgroupDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreCreditGroupSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecreditgroupDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecreditgroupDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCreditGroup getInfoByPK(Integer wms_cre_credit_group_id) {
		return wmscrecreditgroupDao.get(wms_cre_credit_group_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCreditGroup bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecreditgroupDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCreditGroup bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecreditgroupDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCreditGroup() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCreditGroup> getListByEntity(WmsCreCreditGroup queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCreditGroup> beanList = wmscrecreditgroupDao.getListByEntity(queryInfo);
		return beanList;
	}

    /**
     * @Title: combinedLoanInfoSave
     * @Description: TODO(组合贷保存)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2016年12月28日 上午11:03:40
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditGroupService#combinedLoanInfoSave(com.zx.emanage.util.gen.entity.WmsCreCreditGroup, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月28日 jiaodelong 创建方法
    */
    @Override
    @Transactional
    public String combinedLoanInfoSave(WmsCreCreditGroup queryInfo, UserBean user)
    {
        // 合同编号
        queryInfo.setBill_code_group( CodeNoUtil.getGroup_code());
        queryInfo.setGroup_date(new Timestamp(System.currentTimeMillis()));
        queryInfo.setCreate_user_id(user.getUserId());
        queryInfo.setCreate_user_name(user.getRealname());
        queryInfo.setEnable_flag("1");
        String resStr = "success";
        int ret = 0;
        //新增组合贷
        ret = wmscrecreditgroupDao.save(queryInfo);
        //给主表增加组合贷ID
        WmsCreCreditHead head = new WmsCreCreditHead();
        head.setWms_cre_credit_group_id(queryInfo.getWms_cre_credit_group_id());
        for(int i=0;i<queryInfo.getHead_ids().length;i++){
            head.setWms_cre_credit_head_id(Integer.parseInt(queryInfo.getHead_ids()[i]));
            wmscrecreditheadDao.updateGroupId(head);
        }
        if (ret == 0) {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * @Title: combinedLoanInfoSaveForCaiFen
     * @Description: TODO(组合贷拆分保存)
     * @param bean
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2016年12月28日 下午6:27:26
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditGroupService#combinedLoanInfoSaveForCaiFen(com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月28日 jiaodelong 创建方法
    */
    @Override
    @Transactional
    public String combinedLoanInfoSaveForCaiFen(WmsCreCreditHeadVO bean, UserBean user)
    {
        String resStr = "success";
        int res = 0;
        int ret = 0;
        //根据主表ID获取组合贷ID
        Integer credit_group_id = wmscrecreditheadDao.getGroupIdForHeadId(bean.getWms_cre_credit_head_id());
        //组合贷拆分
        if(bean.getIs_new() == 1){
            BigDecimal zscount = new BigDecimal(0);
            //保存之前获取所有不为结清状态的组合贷单据
            List<Integer> headListId = wmscrecreditheadDao.getHeadIdList(bean.getWms_cre_credit_head_id());
            if(headListId.size() > 0){
                for(int i=0;i<headListId.size();i++){
                    WmsCreApproBorrowProtocol Protocol = WmsCreApproBorrowProtocolDao.selectZSJE(headListId.get(i));
                    if(Protocol == null){
                        WmsCreCreditHead head = wmscrecreditheadDao.selectZSJE(headListId.get(i));
                        if(head != null){
                            zscount =new BigDecimal(zscount.add(head.getAppro_limit()).doubleValue());
                        }
                    }else{
                        zscount =new BigDecimal(zscount.add(Protocol.getAppro_limit().divide(new BigDecimal(10000))).doubleValue());
                    }
                }
            }
//            zscount.add(bean.getAppro_limit()).doubleValue();
            if (bean != null && bean.getArraryJson() != null)
            {
             // 组合贷数据
                List<WmsCreCreditCopyBeanVO> zhinfolist = JsonUtil.jsonArrayToList(bean.getArraryJson(), WmsCreCreditCopyBeanVO.class); // 前台json客户联系人数据转换为list数据
                for (WmsCreCreditCopyBeanVO zhdinfo : zhinfolist)
                {
                    zscount =new BigDecimal(zscount.add(new BigDecimal(zhdinfo.getCredit_limit())).doubleValue());
                }
            }
           
            
            WmsCreCreditGroup Group = wmscrecreditgroupDao.get(credit_group_id);
            if(credit_group_id != null && Group.getAppro_limit_group().compareTo(zscount)<0){
                res=1;
                resStr = "xiaoyu";
            }else{
                // 组合贷数据处理
                if (bean != null && bean.getArraryJson() != null)
                {
                    //组合贷再 拆分
                    if(bean.getIs_new() == 1 && bean.getIs_parent() == 0){
                        // 组合贷数据
                        List<WmsCreCreditCopyBeanVO> zhdinfolist = JsonUtil.jsonArrayToList(bean.getArraryJson(), WmsCreCreditCopyBeanVO.class); // 前台json客户联系人数据转换为list数据
                        // 判断是否为空
                        if (zhdinfolist != null && zhdinfolist.size() > 0)
                        {
                            for (WmsCreCreditCopyBeanVO zhdinfo : zhdinfolist)
                            {
                                //根据组合贷编号获取组合贷ID
                                Integer GroupId = wmscrecreditgroupDao.getGroupIdForBillCode(bean.getBill_code_group());
                                WmsCreCreditHead head = new WmsCreCreditHead();
                                head.setWms_cre_credit_group_id(GroupId);
//                                head.setWms_cre_credit_head_id(zhdinfo.getWms_cre_credit_head_id());
//                                wmscrecreditheadDao.update(head);
                                // E待放款准备
                                zhdinfo.setBill_status("H");
                                // 终审金额等于申请金额
                                zhdinfo.setAppro_limit(new java.math.BigDecimal(zhdinfo.getCredit_limit()));
                                zhdinfo.setWms_cre_credit_group_id(GroupId);
                                // 审批节点名称
                                zhdinfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_QDHT);
                                zhdinfo.setApproval_task_code("QDHT");
                                zhdinfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
                                zhdinfo.setIs_group_flag("1");// 组合贷拆分单据 0否1是
                                wmsCreCreditHeadService.copyHeadTableInfo(zhdinfo, user);
                            }
                        }
                        ret = 1;
                    }else{
                        // 组合贷主表
                        WmsCreCreditGroup wmsCreCreditGroup = new WmsCreCreditGroup();
                        // 终审合计金额
                        wmsCreCreditGroup.setAppro_limit_group(bean.getAppro_limit());
                        // 合同编号
                        if(bean.getBill_code_group() != null && !bean.getBill_code_group().equals("")){
                            wmsCreCreditGroup.setBill_code_group(bean.getBill_code_group());
                        }else{
                            wmsCreCreditGroup.setBill_code_group(CodeNoUtil.getGroup_code());
                        }
                        // 时间
                        wmsCreCreditGroup.setGroup_date(new Timestamp(System.currentTimeMillis()));
                        wmsCreCreditGroup.setCreate_user_id(user.getUserId());
                        wmsCreCreditGroup.setCreate_user_name(user.getRealname());
                        wmsCreCreditGroup.setLast_update_user_id(user.getUserId());
                        wmsCreCreditGroup.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                        wmsCreCreditGroup.setEnable_flag("1");
                        ret = wmscrecreditgroupDao.save(wmsCreCreditGroup);
                        // 原数据的操作
                        bean.setWms_cre_credit_group_id(wmsCreCreditGroup.getWms_cre_credit_group_id());
                        bean.setAppro_limit(new java.math.BigDecimal(bean.getCredit_limit()));
                        bean.setCredit_limit(null);
                        bean.setCredit_limit_backup(null);
                        // 组合贷数据
                        List<WmsCreCreditCopyBeanVO> zhdinfolist = JsonUtil.jsonArrayToList(bean.getArraryJson(), WmsCreCreditCopyBeanVO.class); // 前台json客户联系人数据转换为list数据
                        // 判断是否为空
                        if (zhdinfolist != null && zhdinfolist.size() > 0)
                        {
                            for (WmsCreCreditCopyBeanVO zhdinfo : zhdinfolist)
                            {
                                // E待放款准备
                                zhdinfo.setBill_status("H");
                                // 终审金额等于申请金额
                                zhdinfo.setAppro_limit(new java.math.BigDecimal(zhdinfo.getCredit_limit()));
                                zhdinfo.setWms_cre_credit_group_id(wmsCreCreditGroup.getWms_cre_credit_group_id());
                                // 审批节点名称
                                zhdinfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_QDHT);
                                zhdinfo.setApproval_task_code("QDHT");
                                zhdinfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
                                zhdinfo.setIs_group_flag("1");// 组合贷拆分单据 0否1是
                                wmsCreCreditHeadService.copyHeadTableInfo(zhdinfo, user);
                            }

                        }
                    }
                }
                wmscrecreditheadDao.updateCombindeLoan(bean);
            }
        }
        if (ret == 0 && res!=1) {
            resStr = "error";
        }
        return resStr;
    }
}
