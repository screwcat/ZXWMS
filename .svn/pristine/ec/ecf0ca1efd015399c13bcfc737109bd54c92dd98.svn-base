package com.zx.emanage.inve.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveSaleLimitDao;
import com.zx.emanage.inve.service.IWmsInveSaleLimitService;
import com.zx.emanage.util.gen.entity.WmsInveSaleLimit;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvesalelimitService")
public class WmsInveSaleLimitServiceImpl implements IWmsInveSaleLimitService {
	private static Logger log = LoggerFactory.getLogger(WmsInveSaleLimitServiceImpl.class);

	@Autowired
    private WmsInveSaleLimitDao wmsInveSaleLimitDao;

    /**
     * @Title: saveWmsInveSaleLimitCurrentDay
     * @Description: 批量保存当日销售限额信息 
     * @param user
     * @param dataJson(当日销售限额集合)
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月7日 下午2:39:31
     * @see com.zx.emanage.inve.service.IWmsInveSaleLimitService#saveWmsInveSaleLimitCurrentDay(com.zx.sframe.util.vo.UserBean, java.lang.String)
     * history:
     * 1、2017年4月7日 Administrator 创建方法
    */
    @Override
    @Transactional
    public String saveWmsInveSaleLimitCurrentDay(UserBean user, String dataJson)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 将json格式集合转成WmsInveSaleLimit集合
        List<WmsInveSaleLimit> WmsInveSaleLimitList = JsonUtil.jsonArrayToList(dataJson, WmsInveSaleLimit.class);
        // 用于新增每日销售限额的list
        List<WmsInveSaleLimit> WmsInveSaleLimitListForInsert = new ArrayList<WmsInveSaleLimit>();
        // 用于修改每日销售限额的list
        List<WmsInveSaleLimit> WmsInveSaleLimitListForUpdate = new ArrayList<WmsInveSaleLimit>();

        for (int i = 0; i < WmsInveSaleLimitList.size(); i++)
        {
            // Wms_inve_sale_limit_id主键不为空 说明表中存在对应的数据 执行更新操作
            if (WmsInveSaleLimitList.get(i).getWms_inve_sale_limit_id() != null)
            {
                WmsInveSaleLimitListForUpdate.add(WmsInveSaleLimitList.get(i));
            }
            else
            {
                // 主键为空 执行插入操作
                WmsInveSaleLimitListForInsert.add(WmsInveSaleLimitList.get(i));
            }
        }
        try
        {
            // size大于0执行批量插入
            if (WmsInveSaleLimitListForInsert.size() > 0)
            {
                paramMap.put("list", WmsInveSaleLimitListForInsert);
                paramMap.put("create_user_id", user.getUserId());
                // 批量保存
                wmsInveSaleLimitDao.saveWmsInveSaleLimitBatch(paramMap);
            }
            // size大于0执行批量更新
            if (WmsInveSaleLimitListForUpdate.size() > 0)
            {
                paramMap.clear();
                paramMap.put("list", WmsInveSaleLimitListForUpdate);
                paramMap.put("last_update_user_id", user.getUserId());

                wmsInveSaleLimitDao.updateWmsInveSaleLimitBatch(paramMap);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
