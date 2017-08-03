package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.inve.persist.WmsInveClerkRegionDao;
import com.zx.emanage.inve.persist.WmsInveSaleLimitDao;
import com.zx.emanage.inve.service.IWmsInveClerkRegionService;
import com.zx.emanage.inve.vo.WmsInveSaleLimitVo;
import com.zx.emanage.util.gen.entity.ColumnInfo;

/*
 * @version 2.0
 */

@Service("wmsinveclerkregionService")
public class WmsInveClerkRegionServiceImpl implements IWmsInveClerkRegionService {
	private static Logger log = LoggerFactory.getLogger(WmsInveClerkRegionServiceImpl.class);

    @Autowired
    private WmsInveClerkRegionDao wmsinveclerkregionDao;

    @Autowired
    private WmsInveSaleLimitDao wmsInveSaleLimitDao;

    /**
     * @Title: getWmsInveClerkRegionColumnList
     * @Description: 查询柜员区域表(Column)集合
     * @return 
     * @author: Administrator
     * @time:2017年2月20日 上午11:11:02
     * @see com.zx.emanage.inve.service.IWmsInveClerkRegionService#getWmsInveClerkRegionList()
     * history:
     * 1、2017年2月20日 Administrator 创建方法
    */
    @Override
    public LinkedList<ColumnInfo> getWmsInveClerkRegionColumnList()
    {
        LinkedList<ColumnInfo> list = wmsinveclerkregionDao.getWmsInveClerkRegionColumnList();

        return list;
    }

    /**
     * 
     * @Title: getWmsInveClerkRegionList
     * @Description: 查询柜员区域表集合(先去查每日销售限额表中当天的数据，如果不存在则去柜员区域表去取)
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月20日 上午9:27:51
     * history:
     * 1、2017年4月20日 Administrator 创建方法
     */
    @Override
    public List<WmsInveSaleLimitVo> getWmsInveClerkRegionList(String limit_type)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // limit_type三种类型(1、每日销售限额2、各区域预警阈值3、柜员工作台限额')
        paramMap.put("limit_type", limit_type);
        // 先去查每日销售限额表中当天的设定销售限额的数据
        return wmsInveSaleLimitDao.getWmsInveSaleLimitVoList(paramMap);
    }
}
