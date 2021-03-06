package com.zx.emanage.inve.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.inve.persist.WmsInveActivityStatisticsDao;
import com.zx.emanage.inve.service.IWmsInveActivityStatisticsService;
import com.zx.emanage.inve.vo.WmsInveActivityStatisticsSearchBeanVO;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.XlsUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveActivityStatisticsServiceImpl
 * 模块名称：活动量统计
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年3月23日
 * @version V3.5
 * history:
 * 1、2017年3月23日 zhangmingjian 创建文件
 */
@Service
public class WmsInveActivityStatisticsServiceImpl implements IWmsInveActivityStatisticsService
{
    @Autowired
    private WmsInveActivityStatisticsDao wmsInveActivityStatisticsDao;

    @Autowired
    private SysRoleDao sysroleDao_m;

    @Autowired
    private SysDeptDao sysdeptDao;
    
    /**
     * 
     * @Title: selectActivityStatisticsList
     * @Description: 活动量统计列表信息
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月23日 下午3:18:01
     * history:
     * 1、2017年3月23日 zhangmingjian 创建方法
     */
    @Override
    public Map<String, Object> selectActivityStatisticsList(WmsInveActivityStatisticsSearchBeanVO bean, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
         int page = bean.getPage();
        // 处理分页参数
        if (bean.getPage() != null && bean.getPagesize() != null)
        {
            bean.setPage((bean.getPage() - 1) * bean.getPagesize());
        }
        paramMap = XlsUtil.beanToMap(bean);
        Map<String, Object> map = new HashMap<>();

        // 获取该用户的角色信息
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        for (String role : roleList)
        {
            // 默认为查看自己数据
            paramMap.put("salesman_id", user.getUserId());
            if (role.equals("理财业务部主管"))
            {
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }
            if (role.equals("理财业务部副总"))
            {
                // 根据数据权限去获取部门
                paramMap.put("deptIds_menu", bean.getMenu_id());
                paramMap.put("deptIds_user_id", user.getUserId());

            }
            if (role.equals("理财业务部总监"))
            {
                paramMap.put("super_user", 1);
            }
            if (role.equals("超级用户"))
            {
                // 可以看见所有数据
                paramMap.put("super_user", 1);
            }
        }
        GridDataBean<Map<String, Object>> grid = null;
        // 如果num_flag==1为个人活动量，2为团队活动量
        if (bean.getFlag_num() == 1)
        {
            grid = new GridDataBean<Map<String, Object>>(
                    page,
                    wmsInveActivityStatisticsDao.countActivityStatisticsList(paramMap),
                    wmsInveActivityStatisticsDao.selectActivityStatisticsList(paramMap));
        }
        else
        {
            grid = new GridDataBean<Map<String, Object>>(
                    page,
                    wmsInveActivityStatisticsDao.countActivityStatisticsListForTeam(paramMap),
                    wmsInveActivityStatisticsDao.selectActivityStatisticsListForTeam(paramMap));
        }
        
        return grid.getGridData();
    }

    /**
     * 
     * @Title: selectActivityStatisticsImportExcel
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param bean
     * @param user 
     * @author: zhangmingjian
     * @time:2017年4月7日 下午2:29:33
     * history:
     * 1、2017年4月7日 zhangmingjian 创建方法
     */
    @Override
    public Map<String, Object> selectActivityStatisticsImportExcel(Map<String, Object> paramMap, UserBean user)
    {
        // 处理分页参数
        // paramMap = XlsUtil.beanToMap(bean);
        Map<String, Object> map = new HashMap<>();

        // 获取该用户的角色信息
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        for (String role : roleList)
        {
            // 默认为查看自己数据
            paramMap.put("salesman_id", user.getUserId());
            if (role.equals("理财业务部主管"))
            {
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }
            if (role.equals("理财业务部副总"))
            {
                // 根据数据权限去获取部门
                paramMap.put("deptIds_menu", paramMap.get("menu_id"));
                paramMap.put("deptIds_user_id", user.getUserId());

            }
            if (role.equals("理财业务部总监"))
            {
                paramMap.put("super_user", 1);
            }
            if (role.equals("超级用户"))
            {
                // 可以看见所有数据
                paramMap.put("super_user", 1);
            }
        }
        paramMap.put("page", null);
        paramMap.put("pagesize", null);

        List<Map<String, Object>> personnel_data = wmsInveActivityStatisticsDao.selectActivityStatisticsList(paramMap);
        List<Map<String, Object>> team_data =wmsInveActivityStatisticsDao.selectActivityStatisticsListForTeam(paramMap);

        map.put("personnel_data", ProcesData(personnel_data,1));
        map.put("team_data", ProcesData(team_data,2));


        return map;
    }

    /**
     * 
     * @Title: ProcesData
     * @Description: 处理导出数据
     * @param list
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月7日 下午5:49:59
     * history:
     * 1、2017年4月7日 zhangmingjian 创建方法
     */
    private List<Map<String, Object>> ProcesData(List<Map<String, Object>> list,int num)
    {
        String personnel_state[] = { "试用", "在职", "实习", "离职", "离退休", "异动", "", "兼职" };
        // 如果list不是null,处理list集合里的数据
        if (list != null && list.size() > 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                Map<String, Object> m = list.get(i);
                WmsInveActivityStatisticsSearchBeanVO bean = new WmsInveActivityStatisticsSearchBeanVO();
                // 将map转成bean
                try
                {
                    BeanUtils.populate(bean, m);
                }
                catch (IllegalAccessException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (InvocationTargetException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                
                if (StringUtils.isNotBlank((String) m.get("personnel_name")))
                {
                    // 人员加工号显示
                m.put("personnel_name", "" + m.get("personnel_name") + m.get("personnel_shortCode"));
                    // 人员状态
                    m.put("personnel_state", personnel_state[Integer.parseInt(m.get("personnel_state").toString())]);
               
                }
                // 通话时长
                if(m.get("sum_time_M")!=null&&m.get("sum_time_S")!=null&&m.get("sum_time_H")!=null){
                    Integer sum_time_M = bean.getSum_time_M();
                    Integer sum_time_S = bean.getSum_time_S();
                    Integer sum_time_H = bean.getSum_time_H();
                    
                    
                      sum_time_M = sum_time_M + sum_time_S / 60;
                      sum_time_S = sum_time_S % 60;
                      sum_time_H = sum_time_H+sum_time_M/60;
                      sum_time_M = sum_time_M%60;
                    m.put("sum_time",sum_time_H+":"+sum_time_M+":"+sum_time_S);
                }
                // 邀约率
                if(bean.getCustomer_count()!=null&&bean.getCustomer_invite_count()!=null&&num==2){
                    m.put("customer_invite_percent", String.format("%.2f", (double) bean.getCustomer_invite_count() / bean.getCustomer_count()*100));
                }
                // 签单率
                if (bean.getCustomer_count() != null && bean.getCustomer_sign_bill_count() != null)
                {
                    m.put("customer_sign_bill_percent", String.format("%.2f", (double) bean.getCustomer_sign_bill_count() / (double)bean.getCustomer_count()*100));
                }
                // 新增客户邀约率
                if (bean.getCustomer_new() != null && bean.getCustomer_new_invite() != null&&num==2)
                {
                    m.put("customer_new_invite_percent", String.format("%.2f", (double) bean.getCustomer_new_invite() / (double)bean.getCustomer_new()*100));
                }
                // 新增客户签单率
                if (bean.getCustomer_new() != null && bean.getCustomer_new_sign_bill_count() != null)
                {
                    m.put("customer_new_sign_bill_percent", String.format("%.2f", (double) bean.getCustomer_new_sign_bill_count() / (double)bean.getCustomer_new()*100));
                }
                // 计划完成率
                if (bean.getPlan_count() != null && bean.getPlan_done() != null&&num==2)
                {
                    m.put("plan_done_percent", String.format("%.2f", (double) bean.getPlan_done() / bean.getPlan_count()*100));
                }
            }

        }

        return list;
    }


}
