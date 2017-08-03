package com.zx.emanage.loanpost.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanpost.persist.WmsPostDunningDetailedDao;
import com.zx.emanage.loanpost.service.IWmsPostDunningDetailedService;
import com.zx.emanage.util.gen.entity.WmsPostDunningDetailed;
import com.zx.emanage.loanpost.vo.WmsPostDunningDetailedSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmspostdunningdetailedService")
public class WmsPostDunningDetailedServiceImpl implements IWmsPostDunningDetailedService {
    private static Logger log = LoggerFactory.getLogger(WmsPostDunningDetailedServiceImpl.class);

    @Autowired
    private WmsPostDunningDetailedDao wmspostdunningdetailedDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsPostDunningDetailedSearchBeanVO queryInfo) {
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String,Object>>  list = wmspostdunningdetailedDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows",list);
        return resMap;      
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsPostDunningDetailedSearchBeanVO queryInfo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmspostdunningdetailedDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmspostdunningdetailedDao.findCount(paramMap),list);
        return bean.getGridData();          
    }

    @Override
    public WmsPostDunningDetailed getInfoByPK(Integer wms_post_dunning_detailed_id) {
        return wmspostdunningdetailedDao.get(wms_post_dunning_detailed_id);
    }

    @Override   
    @Transactional
    public String save(WmsPostDunningDetailed bean, UserBean user) {
        String resStr = "success";
        int ret = 0;
        ret = wmspostdunningdetailedDao.save(bean);
        if (ret == 0) {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsPostDunningDetailed bean, UserBean user) {
        String resStr = "success";
        int ret = 0;
        ret = wmspostdunningdetailedDao.update(bean); // update a record replace columns, nonsupport null val
        if (ret == 0) {
            resStr = "error";
        }
        return resStr;
    }   
    
    /**
     * Description :check repeat by "and" method, union checking, need new WmsPostDunningDetailed() include check-params
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    @Override
    public List<WmsPostDunningDetailed> getListByEntity(WmsPostDunningDetailed queryInfo, Boolean isExcludePKFlag) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String resStr = "success";
        List<WmsPostDunningDetailed> beanList = wmspostdunningdetailedDao.getListByEntity(queryInfo);
        return beanList;
    }
    /**
     * Description :通过催缴单主键获取相关明细表信息
     * @param wms_post_dunning_head_id
     * @return map
     * @author hancd
     */
    @Override
    public List<WmsPostDunningDetailed> getWmsPostDunningDetailedInfoByPK(Integer wms_post_dunning_head_id){
        WmsPostDunningDetailed wmsPostDunningDetailed = new WmsPostDunningDetailed();
        wmsPostDunningDetailed.setWms_post_dunning_head_id(wms_post_dunning_head_id);
        return wmspostdunningdetailedDao.getListByEntity(wmsPostDunningDetailed);
    }
}
