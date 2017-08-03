package com.zx.emanage.loanfina.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loanfina.persist.WmsFinaCreLoanAppAttDao;
import com.zx.emanage.loanfina.service.IWmsFinaCreLoanAppAttService;
import com.zx.emanage.loanfina.vo.WmsFinaCreLoanAppAttSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanAppAtt;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinacreloanappattService")
public class WmsFinaCreLoanAppAttServiceImpl implements IWmsFinaCreLoanAppAttService
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCreLoanAppAttServiceImpl.class);

    @Autowired
    private WmsFinaCreLoanAppAttDao wmsFinaCreLoanAppAttDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsFinaCreLoanAppAttSearchBeanVO queryInfo,
                                                    String wms_fina_cre_loan_app_id)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (queryInfo.getWms_fina_cre_loan_app_id() != null)
        {
            resMap.put("wms_fina_cre_loan_app_id", Integer.parseInt(wms_fina_cre_loan_app_id));
        }
        resMap.put("Rows", wmsFinaCreLoanAppAttDao.search(resMap));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsFinaCreLoanAppAttSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("offset", queryInfo.getOffset());
        resMap.put("pagesize", queryInfo.getPagesize());
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmsFinaCreLoanAppAttDao.findCount(resMap),
                                             wmsFinaCreLoanAppAttDao.search(resMap));
        return bean.getGridData();
    }

    @Override
    public WmsFinaCreLoanAppAtt getInfoByPK(Integer wms_fina_cre_loan_app_att_id)
    {

        return wmsFinaCreLoanAppAttDao.get(wms_fina_cre_loan_app_att_id);
    }

    @Override
    public String save(WmsFinaCreLoanAppAtt bean, UserBean user)
    {
        String resStr = "success";
        int res = 0;
        res = wmsFinaCreLoanAppAttDao.save(bean);
        if (res == 0)
        {
            return "error";
        }
        return resStr;
    }

    @Override
    public String update(WmsFinaCreLoanAppAtt bean, UserBean user)
    {
        String resStr = "success";
        int res = 0;
        res = wmsFinaCreLoanAppAttDao.update(bean);
        if (res == 0)
        {
            return "error";
        }
        return resStr;
    }

}
