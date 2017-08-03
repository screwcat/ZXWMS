package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.inve.persist.WmsInveClerkDataDao;
import com.zx.emanage.inve.service.ISingleWmsInveClerkDataService;
import com.zx.emanage.util.gen.entity.WmsInveClerkData;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: SingleWmsInveClerkDataServiceImpl
 * 模块名称：
 * @Description: 内容摘要：单例模式对柜员数据进行锁定
 * @author Guanxu
 * @date 2017年2月13日
 * @version V1.0
 * history:
 * 1、2017年2月13日 Guanxu 创建文件
 */
@Service("singleWmsInveClerkDataService")
@Singleton
public class SingleWmsInveClerkDataServiceImpl implements ISingleWmsInveClerkDataService
{
    private static Logger log = LoggerFactory.getLogger(SingleWmsInveClerkDataServiceImpl.class);

    @Autowired
    private WmsInveClerkDataDao wmsInveClerkDataDao;

    /**
     * 
     * @Title: checkAndLockData
     * @Description: 判断单据是否锁定，如果没有锁定则锁定单据，如果已锁定判断锁定人是否一致
     * 1、根据wms_inve_transa_id或者wms_inve_clerk_data_id查找柜员业务表数据
     * 2、判断当前数据是否已被锁定，如果已锁定，判断本次操作人员是否与锁定人一致，如果未锁定数据，则进行锁定操作
     * @param wms_inve_clerk_data_id
     * @param wms_inve_transa_id
     * @return 
     * success:锁定成功，开始处理数据
     * other_locked：锁定失败，其他人已锁定数据
     * error：柜员业务表中无此数据
     * @author: Guanxu
     * @time:2017年2月13日 下午1:36:13
     * history:
     * 1、2017年2月13日 Guanxu 创建方法
     */
    @Override
    public synchronized String checkAndLockData(UserBean user, Integer wms_inve_clerk_data_id, Integer wms_inve_transa_id)
    {
        WmsInveClerkData queryData = new WmsInveClerkData();
        queryData.setWms_inve_clerk_data_id(wms_inve_clerk_data_id);
        queryData.setWms_inve_transa_id(wms_inve_transa_id);
        List<WmsInveClerkData> list = wmsInveClerkDataDao.getListByEntity(queryData);
        if (list == null || list.size() == 0)
        {
            return "error";// 柜员业务表中无此数据
        }
        WmsInveClerkData data = list.get(0);
        String is_locked = data.getIs_locked();
        Integer locked_personnel_id = data.getLocked_personnel_id();
        if (is_locked == null || "0".equals(is_locked))
        {
            data.setLocked_personnel_id(user.getUserId());
            data.setIs_locked("1");
            data.setLocked_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsInveClerkDataDao.update(data);
            return "success";
        }
        else
        {
            if (locked_personnel_id.compareTo(user.getUserId()) == 0)
            {
                return "success";
            }
            else
            {
                return "other_locked";
            }
        }
    }

}
