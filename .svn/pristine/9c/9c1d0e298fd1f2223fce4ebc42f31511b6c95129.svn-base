package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveClerkDataDao;
import com.zx.emanage.inve.persist.WmsInveSaleLimitDao;
import com.zx.emanage.inve.service.IWmsInveClerkDataService;
import com.zx.emanage.inve.vo.WmsInveClerkDataSearchBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.WmsInveClerkData;
import com.zx.emanage.util.gen.entity.WmsInveSaleLimit;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveclerkdataService")
public class WmsInveClerkDataServiceImpl implements IWmsInveClerkDataService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveClerkDataServiceImpl.class);

    @Autowired
    private PmPersonnelDao personnelDao;

    @Autowired
    private SysSpecialUserDao specialUserDao;

    @Autowired
    private WmsInveClerkDataDao wmsInveClerkDataDao;
    
    @Autowired
    private WmsInveSaleLimitDao wmsInveSaleLimitDao;

    /**
     * @Title: getClerkDataWithPaging
     * @Description: 根据当前登录人查询该地区的所有柜员工作台信息
     * @param queryInfo 查询条件
     * @param user 登录用户信息
     * @return 工作台业务数据信息
     * @author: jinzhm
     * @time:2017年2月8日 上午11:05:11
     * @see com.zx.emanage.inve.service.IWmsInveClerkDataService#getClerkDataWithPaging(com.zx.emanage.inve.vo.WmsInveClerkDataSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月8日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getClerkDataWithPaging(WmsInveClerkDataSearchBeanVO queryInfo, UserBean user)
    {
        // 获得登录用户地区
        PmPersonnel personnel = personnelDao.get(user.getUserId());

        // 封装查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (!StringUtil.isEmpty(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", queryInfo.getBill_code());
        }
        if (!StringUtil.isEmpty(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", queryInfo.getCus_name());
        }
        if (queryInfo.getCategory_id() != null)
        {
            paramMap.put("category_id", queryInfo.getCategory_id());
        }
        if (!StringUtil.isEmpty(queryInfo.getData_type()))
        {
            paramMap.put("data_type", queryInfo.getData_type());
        }
        if (!StringUtil.isEmpty(queryInfo.getData_status()))
        {
            if ("3".equals(queryInfo.getData_status()))
            {
                paramMap.put("data_type", "2");
            }
            else if ("4".equals(queryInfo.getData_status()))
            {
                paramMap.put("data_type", "3");
            }
            else if ("1".equals(queryInfo.getData_status()))
            {
                paramMap.put("data_status", "2");
            }
            else if ("2".equals(queryInfo.getData_status()))
            {
                paramMap.put("data_status", "13");
            }
        }
        if (!StringUtil.isEmpty(queryInfo.getPersonnel_name()))
        {
            paramMap.put("personnel_name", queryInfo.getPersonnel_name());
        }
        if (!StringUtil.isEmpty(queryInfo.getIs_new()) && "1".equals(queryInfo.getIs_new()))
        {
            paramMap.put("is_new", "1");
        }
        if (!StringUtil.isEmpty(queryInfo.getIs_extend()) && "1".equals(queryInfo.getIs_extend()))
        {
            paramMap.put("is_extend", "1");
        }

        paramMap.put("personnel_regionNumber", personnel.getPersonnel_regionnumber());
        // 分页和排序信息
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // paramMap.put("pagesize", queryInfo.getPagesize());
        // paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("userid", user.getUserId());

        // 查询数据
        List<Map<String, Object>> clerkDataMapList = wmsInveClerkDataDao.searchClerkData(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        clerkDataMapList = SysTools.setListView(clerkDataMapList, user, specilaUsers);
        // 进行数据分页格式化
        // GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String,
        // Object>>(
        // queryInfo.getPage(),
        // wmsInveClerkDataDao.findClerkDataCount(paramMap),
        // clerkDataMapList);
        // return bean.getGridData();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", clerkDataMapList);
        return resMap;
    }

    /**
     * @Title: getRedeemClerkDataWithPaging
     * @Description: 分区查询合同改签单据信息
     * @param queryInfo 查询条件
     * @param user 登录用户
     * @return 合同改签单据信息集合
     * @author: jinzhm
     * @time:2017年2月9日 上午11:11:43
     * @see com.zx.emanage.inve.service.IWmsInveClerkDataService#getRedeemClerkDataWithPaging(com.zx.emanage.inve.vo.WmsInveClerkDataSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getRedeemClerkDataWithPaging(WmsInveClerkDataSearchBeanVO queryInfo, UserBean user)
    {
        // 获得登录用户地区
        PmPersonnel personnel = personnelDao.get(user.getUserId());

        // 封装查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (!StringUtil.isEmpty(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", queryInfo.getCus_name());
        }
        if (!StringUtil.isEmpty(queryInfo.getMobile_phone()))
        {
            paramMap.put("mobile_phone", queryInfo.getMobile_phone());
        }
        if (queryInfo.getCategory_id() != null)
        {
            paramMap.put("category_id", queryInfo.getCategory_id());
        }
        if (!StringUtil.isEmpty(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        if (queryInfo.getRedeem_date_begin() != null)
        {
            paramMap.put("redeem_date_begin", queryInfo.getRedeem_date_begin());
        }
        if (queryInfo.getRedeem_date_end() != null)
        {
            paramMap.put("redeem_date_end", queryInfo.getRedeem_date_end());
        }

        paramMap.put("data_type", REDEEM);
        paramMap.put("personnel_regionNumber", personnel.getPersonnel_regionnumber());
        // 分页和排序信息
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());

        // 查询数据
        List<Map<String, Object>> clerkDataMapList = wmsInveClerkDataDao.searchRedeemClerkData(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        clerkDataMapList = SysTools.setListView(clerkDataMapList, user, specilaUsers);
        // 进行数据分页格式化
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsInveClerkDataDao.findRedeemClerkDataCount(paramMap),
                                                                                       clerkDataMapList);
        return bean.getGridData();
    }

    /**
     * @Title: getExtendClerkDataWithPaging
     * @Description: 分页查询合同续签单据信息
     * @param queryInfo 查询条件
     * @param user 登录用户信息
     * @return 合同续签单据信息集合
     * @author: jinzhm
     * @time:2017年2月9日 上午11:55:10
     * @see com.zx.emanage.inve.service.IWmsInveClerkDataService#getExtendClerkDataWithPaging(com.zx.emanage.inve.vo.WmsInveClerkDataSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getExtendClerkDataWithPaging(WmsInveClerkDataSearchBeanVO queryInfo, UserBean user)
    {
        // 获得登录用户地区
        PmPersonnel personnel = personnelDao.get(user.getUserId());

        // 封装查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (!StringUtil.isEmpty(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", queryInfo.getCus_name());
        }
        if (!StringUtil.isEmpty(queryInfo.getMobile_phone()))
        {
            paramMap.put("mobile_phone", queryInfo.getMobile_phone());
        }
        if (queryInfo.getCategory_id() != null)
        {
            paramMap.put("category_id", queryInfo.getCategory_id());
        }
        if (!StringUtil.isEmpty(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        if (queryInfo.getApply_extend_date_begin() != null)
        {
            paramMap.put("extend_date_begin", queryInfo.getApply_extend_date_begin());
        }
        if (queryInfo.getApply_extend_date_end() != null)
        {
            paramMap.put("extend_date_end", queryInfo.getApply_extend_date_end());
        }

        paramMap.put("data_type", EXTEND_TRANSA);
        paramMap.put("personnel_regionNumber", personnel.getPersonnel_regionnumber());
        // 分页和排序信息
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());

        // 查询数据
        List<Map<String, Object>> clerkDataMapList = wmsInveClerkDataDao.searchExtendClerkData(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        clerkDataMapList = SysTools.setListView(clerkDataMapList, user, specilaUsers);
        // 进行数据分页格式化
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsInveClerkDataDao.findExtendClerkDataCount(paramMap),
                                                                                       clerkDataMapList);
        return bean.getGridData();
    }

    /**
     * @Title: reStartSort
     * @Description: 将业务单据重新进入排序
     * @param clerkData 业务单据信息
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年2月9日 上午8:58:49
     * @see com.zx.emanage.inve.service.IWmsInveClerkDataService#reStartSort(com.zx.emanage.util.gen.entity.WmsInveClerkData, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
    */
    @Override
    public void reStartSort(WmsInveClerkData clerkData, UserBean user)
    {
        // 获得柜员工作台数据
        clerkData = wmsInveClerkDataDao.get(clerkData.getWms_inve_clerk_data_id());
        // 要修改的业务数据
        WmsInveClerkData updClerkData = new WmsInveClerkData();
        // 封装要修改的数据
        updClerkData.setWms_inve_clerk_data_id(clerkData.getWms_inve_clerk_data_id());
        updClerkData.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        updClerkData.setLast_update_user_id(user.getUserId());
        updClerkData.setIs_locked("0");
        // 设置地区及单据类型来更新柜员单据排队号
        updClerkData.setPersonnel_regionnumber(clerkData.getPersonnel_regionnumber());
        updClerkData.setData_type(clerkData.getData_type());
        // 重新排序
        updClerkData.setSort_timestamp(new Timestamp(System.currentTimeMillis()));

        // 修改柜员业务数据排队号
        wmsInveClerkDataDao.updateTransaProcCode(updClerkData);

        // 修改信息
        wmsInveClerkDataDao.update(updClerkData);
    }

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveClerkDataSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsInveClerkDataDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveClerkDataSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsInveClerkDataDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsInveClerkDataDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveClerkData getInfoByPK(Integer wms_inve_clerk_data_id)
    {
        return wmsInveClerkDataDao.get(wms_inve_clerk_data_id);
    }

    @Override
    @Transactional
    public String save(WmsInveClerkData bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsInveClerkDataDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveClerkData bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsInveClerkDataDao.update(bean); // update a record replace
                                                // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new WmsInveClerkData() include check-params
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveClerkData> getListByEntity(WmsInveClerkData queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveClerkData> beanList = wmsInveClerkDataDao.getListByEntity(queryInfo);
        return beanList;
    }
    
    /**
     * @Title: getSummaryClerkTransaData
     * @Description: 获得柜员工作台某日的提单统计信息
     * @param data 日期
     * @param user 登录用户信息
     * @return 统计信息
     * @author: jinzhm
     * @time:2017年3月21日 上午11:24:38
     * @see com.zx.emanage.inve.service.IWmsInveClerkDataService#getSummaryClerkTransaData(java.util.Date, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年3月21日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getSummaryClerkTransaData(Date date, UserBean user)
    {
        PmPersonnel personnel = personnelDao.get(user.getUserId());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("personnel_regionNumber", personnel.getPersonnel_regionnumber());
        paramMap.put("date", date);
        return wmsInveClerkDataDao.getSummaryClerkTransaData(paramMap);
    }

    /**
     * @Title: getKeepAccount
     * @Description: 获得当天设置的剩余可售额度
     * @param user 登录用户
     * @return 可售额度
     * @author: jinzhm
     * @time:2017年4月6日 上午8:09:36
     * @see com.zx.emanage.inve.service.IWmsInveClerkDataService#getKeepAccount(com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
    */
    @Override
    public String getKeepAccount(UserBean user)
    {
        // 获得当前设置的剩余可售额度数据
        WmsInveSaleLimit saleLimit = getTodayKeepAccount(user);
        // 如果没有查询到数据返回null
        if(saleLimit == null)
        {
            return null;
        }
        // 返回限制额度
        return saleLimit.getLimit_amount().toBigInteger().toString();
    }
    
    /**
     * @Title: getTodayKeepAccount
     * @Description: 获取当前设置的理财销售限额数据
     * @param user 登录用户信息
     * @return 理财销售限额数据
     * @author: jinzhm
     * @time:2017年4月6日 下午3:59:05
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    public WmsInveSaleLimit getTodayKeepAccount(UserBean user)
    {
        // 封装查询当日设置的限制销售额度条件
        // 当前日期
        Date nowDate = DateUtil.getDate10(new Date());
        // 获取登录用户信息的地区
        PmPersonnel personnel = personnelDao.get(user.getUserId());
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 设置限制类型为归元工作台限额
        paramMap.put("limit_type", "3");
        paramMap.put("enable_flag", "1");
        paramMap.put("create_timestamp", nowDate);
        paramMap.put("region_number", personnel.getPersonnel_regionnumber());
        // 查询当日设置的限制销售额度
        List<WmsInveSaleLimit> saleLimitList = wmsInveSaleLimitDao.getSaleLimit(paramMap);
        // 如果当日没有设置剩余销售额度，直接返回null
        if(saleLimitList.isEmpty())
        {
            return null;
        }
        return saleLimitList.get(0);
    }

    /**
     * @Title: setKeepAccount
     * @Description: 设置剩余可售额度
     * @param user 登录用户
     * @param account 可售额度
     * @return 设置成功标志
     * @author: jinzhm
     * @time:2017年4月6日 上午8:14:33
     * @see com.zx.emanage.inve.service.IWmsInveClerkDataService#setKeepAccount(com.zx.sframe.util.vo.UserBean, java.math.BigDecimal)
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
    */
    @Transactional
    @Override
    public String setKeepAccount(UserBean user, BigDecimal account)
    {
        // 获取当前设置的剩余销售额度
        WmsInveSaleLimit saleLimit = getTodayKeepAccount(user);
        // 如果当天设置了剩余销售额度
        if (saleLimit != null)
        {
            // 将之前设置的剩余销售额度设置成失效
            saleLimit.setEnable_flag("0");
            saleLimit.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            saleLimit.setLast_update_user_id(user.getUserId());
            wmsInveSaleLimitDao.update(saleLimit);
        }

        // 获取登录用户信息的地区
        PmPersonnel personnel = personnelDao.get(user.getUserId());

        // 封装新的剩余销售额度设置信息，进行保存
        saleLimit = new WmsInveSaleLimit();
        // 销售限额类型为工作台限额=3
        saleLimit.setLimit_type("3");
        // 销售限额
        saleLimit.setLimit_amount(account);
        // 地区
        saleLimit.setRegion_number(personnel.getPersonnel_regionnumber());
        // 创建时间，创建用户，有效标志
        saleLimit.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        saleLimit.setCreate_user_id(user.getUserId());
        saleLimit.setEnable_flag("1");
        // 保存
        wmsInveSaleLimitDao.save(saleLimit);
        // 返回成功标志
        return "success";
    }

    /**
     * @Title: reOrder
     * @Description: 单据整体重新排序，将排序时间早于设置的时间之前的所有签单单据重新进行排序
     * @param hour 时
     * @param minute 分
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年4月6日 上午9:27:35
     * @see com.zx.emanage.inve.service.IWmsInveClerkDataService#reOrder(java.lang.Integer, java.lang.Integer, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
    */
    @Override
    public void reOrder(Integer hour, Integer minute, UserBean user)
    {
        // 设置限制时间
        Date date = DateUtil.getDate10(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 设置小时为24小时制
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        date = cal.getTime();

        // 获取登录用户信息的地区
        PmPersonnel personnel = personnelDao.get(user.getUserId());

        // 封装条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("regionNumber", personnel.getPersonnel_regionnumber());
        params.put("time", date);

        // 查询要重新排队的柜员业务数据集合
        List<WmsInveClerkData> clerkDataList = wmsInveClerkDataDao.getReOrderClerkData(params);

        // 要修改的柜员业务数据
        WmsInveClerkData updClerkData = null;
        // 循环要重新排队的柜员业务数据集合进行重新排队
        for (WmsInveClerkData clerkData : clerkDataList)
        {
            // 封装要修改的数据
            updClerkData = new WmsInveClerkData();
            updClerkData.setWms_inve_clerk_data_id(clerkData.getWms_inve_clerk_data_id());
            updClerkData.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            updClerkData.setLast_update_user_id(user.getUserId());
            updClerkData.setData_type(clerkData.getData_type());
            // 重排进行解锁
            updClerkData.setIs_locked("0");
            // 设置单据业务类型及地区编号来修改排队号
            updClerkData.setPersonnel_regionnumber(clerkData.getPersonnel_regionnumber());
            updClerkData.setSort_timestamp(new Timestamp(System.currentTimeMillis()));
            // 修改柜员业务数据排队号
            wmsInveClerkDataDao.updateTransaProcCode(updClerkData);

            // 修改信息
            wmsInveClerkDataDao.update(updClerkData);
        }
    }
}
