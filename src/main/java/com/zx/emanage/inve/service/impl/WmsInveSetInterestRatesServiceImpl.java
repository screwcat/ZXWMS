package com.zx.emanage.inve.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveSetInterestRatesDao;
import com.zx.emanage.inve.service.IWmsInveSetInterestRatesService;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;
/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSetInterestRatesServiceImpl
 * 模块名称：设定央行利率
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年4月24日
 * @version V3.5
 * history:
 * 1、2017年4月24日 zhangmingjian 创建文件
 */
@Service
public class WmsInveSetInterestRatesServiceImpl implements IWmsInveSetInterestRatesService
{
    @Autowired
    private WmsInveSetInterestRatesDao wmsInveSetInterestRatesDao;
    @Autowired
    private SysRoleDao sysroleDao_m;
    /**
     * 
     * @Title: saveRateInfo
     * @Description: 保存利率
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月24日 下午1:24:00
     * history:
     * 1、2017年4月24日 zhangmingjian 创建方法
     */
    @Override
    @Transactional
    public int saveRateInfo(Map<String, Object> map)
    {
        //先将上次设置的利率enable_flag设置为0
        wmsInveSetInterestRatesDao.updateRateInfo(map);
        //设置新利率
        int num =    wmsInveSetInterestRatesDao.saveRateInfo(map);
        
        return num;
    }
  
    /**
     * 
     * @Title: saveRateInfo
     * @Description: 查询利率
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月24日 下午1:24:00
     * history:
     * 1、2017年4月24日 zhangmingjian 创建方法
     */
    @Override
    public Map<String, Object> selectRateInfo(Map<String, Object> map)
    {
        return wmsInveSetInterestRatesDao.selectRateInfo(map);
    }
    /**
     * 
     * @Title: selectRoleInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param request
     * @return 校验角色
     * @author: zhangmingjian
     * @time:2017年4月24日 下午1:52:38
     * history:
     * 1、2017年4月24日 zhangmingjian 创建方法
     */
    @Override
    public List<Integer> selectRoleInfo(HttpServletRequest request)
    {
        List<Integer> num = new ArrayList<Integer>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);  
        
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        if(roleList !=null && roleList.size()>0){
            for (String s : roleList)
            {
                switch (StringUtils.lowerCase(s))
                {
                    case "理财财务主管":
                        num.add(1);
                        break;
                    case "理财业务部总监":
                        num.add(2);
                        break;
                    case "ptp抵押包专员":
                        num.add(3);
                        break;    
                    case "超级用户" :
                        num.add(-1);
                        num.add(1);
                        num.add(2);
                        return num;
                    default:
                        break;
                }
            }
            
        }
        
        return num;
    }

}
