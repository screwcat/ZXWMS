package com.zx.emanage.inve.service;

import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: ISingleWmsInveClerkDataService
 * 模块名称：
 * @Description: 内容摘要：
 * @author Guanxu
 * @date 2017年2月13日
 * @version V1.0
 * history:
 * 1、2017年2月13日 Guanxu 创建文件
 */
public interface ISingleWmsInveClerkDataService
{

        /**
     * @Title: checkAndLockData
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param user
     * @param wms_inve_clerk_data_id
     * @param wms_inve_transa_id
     * @return 
     * @author: Guanxu
     * @time:2017年2月13日 下午3:25:08
     * history:
     * 1、2017年2月13日 Guanxu 创建方法
    */
    String checkAndLockData(UserBean user, Integer wms_inve_clerk_data_id, Integer wms_inve_transa_id);

}
