package com.zx.emanage.loanfina.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loanfina.persist.WmsFinaCrePreRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.loanfina.service.IWmsFinaCrePreRepayService;
import com.zx.emanage.loanfina.vo.WmsFinaCrePreRepaySearchBeanVO;
import com.zx.emanage.loanpost.service.IWmsPostLoanWorkFlowService;
import com.zx.emanage.loanpost.service.impl.WmsPostLoanWorkFlowServiceImpl;
import com.zx.emanage.util.gen.entity.WmsFinaCrePreRepay;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinacreprerepayService")
public class WmsFinaCrePreRepayServiceImpl implements IWmsFinaCrePreRepayService
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCrePreRepayServiceImpl.class);

    @Autowired
    private WmsFinaCrePreRepayDao wmsfinacreprerepayDao;

    @Autowired
    private WmsFinaCreRepayDao wmsFinaCreRepayDao;

    @Autowired
    private IWmsPostLoanWorkFlowService wmspostloanworkflowService;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsFinaCrePreRepaySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsfinacreprerepayDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsFinaCrePreRepaySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsfinacreprerepayDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsfinacreprerepayDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsFinaCrePreRepay getInfoByPK(Integer wms_fina_cre_pre_repay_id)
    {
        return wmsfinacreprerepayDao.get(wms_fina_cre_pre_repay_id);
    }

    @Override
    @Transactional
    public String save(WmsFinaCrePreRepay bean, UserBean user, String taskId)
    {
        String resStr = "success";
        int ret = 0;
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(sysTime);
        if (StringUtil.isNotBlank(taskId))
        {
            ret = wmsfinacreprerepayDao.updateByEntity(bean);
        }
        else
        {
            bean.setCreate_user_id(user.getUserId());
            bean.setCreate_user_name(user.getRealname());
            bean.setCreate_timestamp(sysTime);
            bean.setEnable_flag("1");
            ret = wmsfinacreprerepayDao.save(bean);
            WmsFinaCreRepay entity = new WmsFinaCreRepay();
            entity.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
            entity.setPre_repay_status("1");// 修改是否提前还款申请状态
            wmsFinaCreRepayDao.updateByEntity(entity);
        }
        // 判断是否存在taskID，实现提前还款的流程开启和重新提交申请操作
        wmspostloanworkflowService.repeatOrBooleanWmsPostLoanWorkFlowBefore(taskId,
                                                                            String.valueOf(bean.getWms_cre_credit_head_id()),
                                                                            String.valueOf(user.getUserId()));
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsFinaCrePreRepay bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsfinacreprerepayDao.update(bean); // update a record replace
                                                  // columns, nonsupport null
                                                  // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsFinaCrePreRepay() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsFinaCrePreRepay> getListByEntity(WmsFinaCrePreRepay queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsFinaCrePreRepay> beanList = wmsfinacreprerepayDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public WmsFinaCrePreRepay getInfoByEntity(WmsFinaCrePreRepay entity)
    {
        return wmsfinacreprerepayDao.getInfoByEntity(entity);
    }
}
