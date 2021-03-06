package com.zx.emanage.creditRightManager.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.creditRightManager.vo.WmsInveAutoCreditPackageVO;
import com.zx.emanage.creditRightManager.vo.WmsInveCreditLog;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveAutoCreditPackageDao
 * 模块名称：自动采集
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年7月18日
 * @version V3.5
 * history:
 * 1、2017年7月18日 zhangmingjian 创建文件
 */
@MyBatisRepository
public interface WmsInveAutoCreditPackageDao
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
    public List<Map<String,Object>> selectAutoCreditPackageInfo(WmsInveAutoCreditPackageVO bean);
    public Integer  countAutoCreditPackageInfo(WmsInveAutoCreditPackageVO bean);
    
    
    /**
     * 
     * @Title: updateAutoCreditPackageType
     * @Description: 更新抵押包类型
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月19日 上午11:59:29
     * history:
     * 1、2017年7月19日 zhangmingjian 创建方法
     */
    public Integer updateAutoCreditPackageType(Map<String,Object> map);
    /**
     * 
     * @Title: saveAutoCreditPackageType
     * @Description: 保存债权包数据(wms_inve_credit_package表)
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月19日 上午11:59:44
     * history:
     * 1、2017年7月19日 zhangmingjian 创建方法
     */
    public Integer saveAutoCreditPackageType(Map<String,Object> map);
    
    /**
     * 
     * @Title: saveNotSetCreditPackageInfo
     * @Description: 保存未设置的抵押包信息（贷款端调用）
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月20日 上午11:04:05
     * history:
     * 1、2017年7月20日 zhangmingjian 创建方法
     */
    public Integer saveNotSetCreditPackageInfo(Map<String,Object> map);
    /**
     * 
     * @Title: selectClerkCompanyInfo
     * @Description: 查询他项人信息
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月20日 上午11:22:48
     * history:
     * 1、2017年7月20日 zhangmingjian 创建方法
     */
    public Map<String,Object> selectClerkCompanyInfo(Map<String,Object> map);
    /**
     * 
     * @Title: saveAutoCreditPackageLog
     * @Description: 保存日志
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月20日 下午1:35:57
     * history:
     * 1、2017年7月20日 zhangmingjian 创建方法
     */
    public Integer saveAutoCreditPackageLog(WmsInveCreditLog log);
    /**
     * 
     * @Title: selectProtocolInfo
     * @Description: 校验合同编号是否存在
     * @param code
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月20日 下午2:32:16
     * history:
     * 1、2017年7月20日 zhangmingjian 创建方法
     */
    public Integer selectProtocolInfo(String code);
    
    /**
     * 
     * @Title: updateCreditPackageType
     * @Description: 
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月25日 下午2:55:16
     * history:
     * 1、2017年7月25日 zhangmingjian 创建方法
     */
    public Integer updateCreditPackageType(Map<String,Object> map);
    /**
     * 
     * @Title: updateCreditPackageType
     * @Description: 修改债权包限制表enable_flag设置为0
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月25日 下午3:20:47
     * history:
     * 1、2017年7月25日 zhangmingjian 创建方法
     */
    public Integer updateCreditPackageLimit(String flag);
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
    public Map<String,Object> iverificationContract(Map<String,Object> map);
}
