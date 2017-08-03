package com.zx.emanage.creditRightManager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.zx.emanage.creditRightManager.persist.WmsInveAutoCreditPackageDao;
import com.zx.emanage.creditRightManager.service.WmsInveAutoCreditPackageService;
import com.zx.emanage.creditRightManager.vo.WmsInveAutoCreditPackageVO;
import com.zx.emanage.creditRightManager.vo.WmsInveCreditLog;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveAutoCreditPackageServiceImpl
 * 模块名称：自动采集
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年7月18日
 * @version V3.5
 * history:
 * 1、2017年7月18日 zhangmingjian 创建文件
 */
@Service
public class WmsInveAutoCreditPackageServiceImpl implements WmsInveAutoCreditPackageService
{
    @Autowired
    private WmsInveAutoCreditPackageDao wmsInveAutoCreditPackageDao;

    /**
     * 
     * @Title: selectAutoCreditPackageInfo
     * @Description: 查询抵押包相关信息
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月18日 下午2:44:16
     * history:
     * 1、2017年7月18日 zhangmingjian 创建方法
     */
    @Override
    public Map<String, Object> selectAutoCreditPackageInfo(WmsInveAutoCreditPackageVO queryInfo)
    {
        List<Map<String, Object>> list = wmsInveAutoCreditPackageDao.selectAutoCreditPackageInfo(queryInfo);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsInveAutoCreditPackageDao.countAutoCreditPackageInfo(queryInfo), list);
        return bean.getGridData();
    }

    /**
     * 
     * @Title: updateAutoCreditPackageType
     * @Description: 设置抵押包类型
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月19日 上午10:43:36
     * history:
     * 1、2017年7月19日 zhangmingjian 创建方法
     */
    @Override
    @Transactional
    public String updateAutoCreditPackageType(Map<String, Object> map)
    {
        // 修改未设定的抵押包表类型
        Integer num = wmsInveAutoCreditPackageDao.updateAutoCreditPackageType(map);

        if (map.get("numFlag").equals("4"))
        {
            // web传来的全部数据
            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("listData");
            // list1表示从房产变更为可拆分的数据（需要更新的数据）
            List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
            // list2表示除了从房产变更为可拆分的数据（需要保存的数据）
            List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
            // 进行数据筛选
            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i).get("cre_type").equals("2"))
                {
                    list1.add(list.get(i));
                }
                else
                {
                    list2.add(list.get(i));
                }
            }
            Integer flag = 0;
            Integer flag1 = 0;
            // 更新抵押包表的数据（update）
            if (list1.size() > 0)
            {
                map.put("list1", list1);
                flag = wmsInveAutoCreditPackageDao.updateCreditPackageType(map);
            }
            // 保存抵押包表的数据(insert)
            if (list2.size() > 0)
            {
                map.put("listData", list2);
                flag1 = wmsInveAutoCreditPackageDao.saveAutoCreditPackageType(map);
            }
            if (flag > 0 || flag1 > 0)
            {
                // 修改债权包限制表enable_flag设置为0
                wmsInveAutoCreditPackageDao.updateCreditPackageLimit(null);
                return "success";
            }
            else
            {
                return "error";
            }

        }
        else
        {
            Integer num1 = wmsInveAutoCreditPackageDao.saveAutoCreditPackageType(map);
            if (num1 > 0)
            {
                return "success";
            }
            else
            {
                return "error";
            }
        }

    }

    /**
     * 
     * @Title: saveNotSetCreditPackageInfo
     * @Description: 保存未设置的抵押包信息（贷款端调用）
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月20日 上午11:01:11
     * 参数: 
     * 
     *  wms_cre_credit_head_id  抵押包id
     *  cre_per_name            抵押包姓名
     *  protocol_id_year_num    合同编号
     *  cre_per_card_id         抵押包身份证号
     *  cre_pledge_mon          抵押金额
     *  house_size              平米数
     *  crepg_start_date        抵押开始日期
     *  crepg_end_date          抵押结束日期
     *  rele_per_id_card        他项人身份证号
     *  loca_num                抵押包所在区域编号
     *  
     * 返回值：
     *      map的key如果是000 代表保存数据成功
     *      map的key如果是001 代表数据异常
     *      map的key如果是002 代表系统异常
     *      
     * 1、2017年7月20日 zhangmingjian 创建方法
     */
    @Override
    public Map<String, Object> saveNotSetCreditPackageInfo(List<Map<String, Object>> list, UserBean user)
    {
        final WmsInveCreditLog log = new WmsInveCreditLog();

        String[] ids = new String[] { "wms_cre_credit_head_id", "cre_per_name", "protocol_id_year_num", "cre_per_card_id", "cre_pledge_mon", "house_size", "crepg_start_date", "crepg_end_date", "rele_per_id_card", "loca_num" };
        String[] names = new String[] { "抵押包id", "抵押包姓名", "合同编号", "抵押包身份证号", "抵押金额", "平米数", "抵押开始日期", "抵押结束日期", "他项人身份证号", "抵押包所在区域编号" };

        if (user != null)
        {
            // 日志创建人
            log.setCreate_user_id(user.getUserId());
        }
        // 日志类型
        log.setLog_type(1);
        // 数据异常时保存 抵押包id
        final List<String> error_ids = new ArrayList<String>();
        // 系统异常时保存 抵押包id
        final List<String> exception_ids = new ArrayList<String>();
        // 返回结果
        final Map<String, Object> resultmap = new HashMap<String, Object>();

        int i = 0;
        String wms_cre_credit_head_id = "";
        if (list != null && list.size() > 0)
        {
            for (; i < list.size(); i++)
            {
                try
                {
                    if (user != null)
                    {
                        // 保存创建人id
                        list.get(i).put("create_user_id", user.getUserId());
                    }
                    wms_cre_credit_head_id = list.get(i).get("wms_cre_credit_head_id").toString();
                    Integer flag = 0;
                    // 校验抵押包属性是否有空值
                    for (int j = 0; j < ids.length; j++)
                    {
                        if (list.get(i).get(ids[j]) instanceof String && StringUtils.isBlank((String) list.get(i).get(ids[j])))
                        {
                            // 将抵押包id存入数据异常集合
                            error_ids.add(wms_cre_credit_head_id);
                            // 保存日志
                            log.setLog_text("抵押包id:" + wms_cre_credit_head_id + ",原因：" + names[j] + "为空");
                            wmsInveAutoCreditPackageDao.saveAutoCreditPackageLog(log);
                            flag = 1;
                            break;

                        }

                        if (list.get(i).get(ids[j]) == null)
                        {
                            // 将抵押包id存入数据异常集合
                            error_ids.add(wms_cre_credit_head_id);
                            // 保存日志
                            log.setLog_text("抵押包id:" + wms_cre_credit_head_id + ",原因：" + names[j] + "为空");
                            wmsInveAutoCreditPackageDao.saveAutoCreditPackageLog(log);
                            flag = 1;
                            break;
                        }
                    }
                    // 如果抵押包属性为空，不保存此抵押包
                    if (flag == 1)
                    {
                        continue;
                    }

                    // 校验合同编号
                    Integer num1 = wmsInveAutoCreditPackageDao.selectProtocolInfo((String) list.get(i).get("protocol_id_year_num"));
                    if (num1 > 0)
                    {
                        // 保存日志
                        log.setLog_text("抵押包id:" + wms_cre_credit_head_id + ",原因:合同编号" + list.get(i).get("protocol_id_year_num") + "重复");
                        wmsInveAutoCreditPackageDao.saveAutoCreditPackageLog(log);
                        continue;
                    }

                    // 查询他项人并且校验他项人是否存在
                    Map<String, Object> clerkMap = wmsInveAutoCreditPackageDao.selectClerkCompanyInfo(list.get(i));
                    if (clerkMap != null && clerkMap.size() > 0)
                    {
                        list.get(i).put("rele_per_id", clerkMap.get("wms_inve_clerk_company_creditor_id"));
                        list.get(i).put("rele_per_name", clerkMap.get("personnel_name"));
                    }
                    else
                    {
                        // 保存日志
                        log.setLog_text("抵押包id:" + wms_cre_credit_head_id + ",他项人身份证号码：" + list.get(i).get("rele_per_id_card") + ",原因：未找到他项人");
                        wmsInveAutoCreditPackageDao.saveAutoCreditPackageLog(log);
                        // 将抵押包id存入数据异常集合
                        error_ids.add(wms_cre_credit_head_id);
                        continue;
                    }

                    // 保存抵押包
                    Integer num = wmsInveAutoCreditPackageDao.saveNotSetCreditPackageInfo(list.get(i));
                    if (num <= 0)
                    {
                        // 如果保存数据库失败，记录保存抵押包编号
                        error_ids.add(wms_cre_credit_head_id);
                        // 保存日志
                        log.setLog_text("抵押包id:" + wms_cre_credit_head_id + ",原因：未找到他项人,他项人的身份证号码是:" + list.get(i).get("rele_per_id_card"));
                        wmsInveAutoCreditPackageDao.saveAutoCreditPackageLog(log);

                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    // 如果保存数据出现异常，记录抵押包id
                    exception_ids.add(wms_cre_credit_head_id);
                    // 保存日志
                    log.setLog_text("抵押包id:" + wms_cre_credit_head_id + ",原因：系统报错");
                    wmsInveAutoCreditPackageDao.saveAutoCreditPackageLog(log);

                }

            }

        }
        else
        {
            log.setLog_text(",原因:参数为空");
            wmsInveAutoCreditPackageDao.saveAutoCreditPackageLog(log);
        }

        if (error_ids.size() == 0 && exception_ids.size() == 0)
        {
            resultmap.put("000", "success");
        }
        else
        {
            resultmap.put("001", Joiner.on(",").join(error_ids));
            resultmap.put("002", Joiner.on(",").join(exception_ids));
        }
        return resultmap;
    }
    
    
    /**
     * 
     * @Title: iverificationContract
     * @Description: 验证合同编号
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月28日 下午3:04:26
     * history:
     * 1、2017年7月28日 zhangmingjian 创建方法
     */
    public List<String> iverificationContract(List<Map<String,Object>> list,String enable_flag){
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        { 
         Map<String,Object> obj = list.get(i);
         obj.put("enable_flag", enable_flag); 
        Map<String,Object> result =  wmsInveAutoCreditPackageDao.iverificationContract(obj);
        //取出合同编号重复的未设置的债权包id
        if(!result.get("count").equals(new Long(0))){
               ids.add(obj.get("wms_inve_credit_package_set_id").toString());
        }
        }
        return ids;
    }

}
