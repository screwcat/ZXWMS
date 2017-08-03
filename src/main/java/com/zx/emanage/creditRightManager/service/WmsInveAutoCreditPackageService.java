package com.zx.emanage.creditRightManager.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.creditRightManager.vo.WmsInveAutoCreditPackageVO;
import com.zx.sframe.util.vo.UserBean;

public interface WmsInveAutoCreditPackageService
{
    /**
     * 
     * @Title: selectAutoCreditPackageInfo
     * @Description: 查询抵押包相关信息
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月18日 下午2:41:55
     * history:
     * 1、2017年7月18日 zhangmingjian 创建方法
     */
     public Map<String, Object>  selectAutoCreditPackageInfo(WmsInveAutoCreditPackageVO bean);
     
     
     /**
      * 
      * @Title: updateAutoCreditPackageType
      * @Description: 设置抵押包类型
      * @param bean
      * @return 
      * @author: zhangmingjian
      * @time:2017年7月19日 上午10:42:21
      * history:
      * 1、2017年7月19日 zhangmingjian 创建方法
      */
     public String  updateAutoCreditPackageType(Map<String,Object> map);
     
     
     
     /**
      * 
      * @Title: saveNotSetCreditPackageInfo
      * @Description: 保存未设置的抵押包信息（贷款端调用）
      * @return 
      * @author: zhangmingjian
      * @time:2017年7月20日 上午11:01:11
      * history:
      * 1、2017年7月20日 zhangmingjian 创建方法
      */
     public Map<String,Object> saveNotSetCreditPackageInfo(List<Map<String,Object>> map,UserBean user);
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
     public List<String> iverificationContract(List<Map<String,Object>> list,String enable_flag);
     
}
