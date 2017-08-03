package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveSpecialApprovalDao;
import com.zx.emanage.inve.service.IWmsInveSpecialApprovalService;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveSpecialApproval;
import com.zx.emanage.inve.vo.WmsInveSpecialApprovalSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvespecialapprovalService")
public class WmsInveSpecialApprovalServiceImpl implements IWmsInveSpecialApprovalService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveSpecialApprovalServiceImpl.class);

    @Autowired
    private WmsInveSpecialApprovalDao wmsinvespecialapprovalDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveSpecialApprovalSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvespecialapprovalDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveSpecialApprovalSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvespecialapprovalDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvespecialapprovalDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveSpecialApproval getInfoByPK(Integer special_approval_leader_id)
    {
        return wmsinvespecialapprovalDao.get(special_approval_leader_id);
    }

    @Override
    @Transactional
    public String save(WmsInveSpecialApproval bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvespecialapprovalDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveSpecialApproval bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvespecialapprovalDao.update(bean); // update a record replace
                                                      // columns, nonsupport
                                                      // null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveSpecialApproval() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveSpecialApproval> getListByEntity(WmsInveSpecialApproval queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveSpecialApproval> beanList = wmsinvespecialapprovalDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public List<WmsInveSpecialApproval> getAllInveSpecialapprova()
    {
        List<com.zx.emanage.util.gen.entity.WmsInveSpecialApproval> list = wmsinvespecialapprovalDao.getAllInveSpecialapprova();
        com.zx.emanage.util.gen.entity.WmsInveSpecialApproval entity = new com.zx.emanage.util.gen.entity.WmsInveSpecialApproval();
        entity.setSpecial_approval_leader_id(-1);
        entity.setSpecial_approval_leader_name("请选择");
        list.add(0, entity);
        return list;
    }
}
