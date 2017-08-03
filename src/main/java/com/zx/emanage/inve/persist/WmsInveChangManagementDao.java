package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveChangManagementBeanVO;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveChangManagementDao
 * 模块名称：变更申请
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年4月11日
 * @version V3.5
 * history:
 * 1、2017年4月11日 zhangmingjian 创建文件
 */
@MyBatisRepository
public interface WmsInveChangManagementDao
{
    /**
     * 
     * @Title: getBillNumber
     * @Description: 获取单据编号
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月11日 上午10:16:23
     * history:
     * 1、2017年4月11日 zhangmingjian 创建方法
     */
    public String getBillNumber();

    /**
     * 
     * @Title: selectBillInfo
     * @Description:查询单据列表
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月11日 下午1:14:32
     * history:
     * 1、2017年4月11日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectBillInfo(WmsInveChangManagementBeanVO bean);

    /**
     * 
     * @Title: saveCustomerCardInfo
     * @Description: 保存收益卡信息 
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月12日 下午1:51:41
     * history:
     * 1、2017年4月12日 zhangmingjian 创建方法
     */
    public Integer saveCustomerCardInfo(WmsInveChangManagementBeanVO bean);

    /**
     * 
     * @Title: saveChangeAppTransaInfo
     * @Description: 保存变更申请单附件
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月12日 下午1:53:27
     * history:
     * 1、2017年4月12日 zhangmingjian 创建方法
     */

    public Integer saveChangeAppAttInfo(WmsInveChangManagementBeanVO bean);

    /**
     * 
     * @Title: saveChangeAppInfo
     * @Description: 保存变更申请单
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月12日 下午1:53:09
     * history:
     * 1、2017年4月12日 zhangmingjian 创建方法
     */
    public Integer saveChangeAppInfo(WmsInveChangManagementBeanVO bean);

    /**
     * 
     * @Title: updateTransaProdInfo
     * @Description: 保存变更申请单单据
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月12日 下午1:54:09
     * history:
     * 1、2017年4月12日 zhangmingjian 创建方法
     */
    public Integer saveChangeAppTransaInfo(WmsInveChangManagementBeanVO bean);

    /**
     * 
     * @Title: updateTransaProdInfo
     * @Description: 更新上单产品表的收益卡id
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月12日 下午1:57:04
     * history:
     * 1、2017年4月12日 zhangmingjian 创建方法
     */
    public Integer updateTransaProdInfo(WmsInveChangManagementBeanVO bean);

    /**
     * 
     * @Title: updateTransaIncomeInfo
     * @Description:  更新客户收益表的收益卡id 
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月12日 下午1:57:25
     * history:
     * 1、2017年4月12日 zhangmingjian 创建方法
     */
    public Integer updateTransaIncomeInfo(WmsInveChangManagementBeanVO bean);

    /**
     * 
     * @Title: selectDictInfo
     * @Description: 查询数据字典数据(查询省)
     * @param dict_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月14日 上午9:24:22
     * history:
     * 1、2017年4月14日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectDictInfo(String dict_id);

    /**
     * 
     * @Title: selectDictInfoByCity
     * @Description: 查询数据字典数据(查询市)
     * @param dict_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月14日 上午10:43:02
     * history:
     * 1、2017年4月14日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectDictInfoByCity(String dict_id);

    /**
     * 
     * @Title: selectChangeAppList
     * @Description: 变更申请单据列表
     * @param personnel_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月14日 下午3:06:42
     * history:
     * 1、2017年4月14日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectChangeAppList(WmsInveChangManagementBeanVO bean);

    public Integer countChangeAppList(WmsInveChangManagementBeanVO bean);

    /**
     * 
     * @Title: selectCustomerInfoBybillID
     * @Description: 根据单据id查询客户电话 身份证
     * @param id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月15日 下午12:51:17
     * history:
     * 1、2017年4月15日 zhangmingjian 创建方法
     */
    public WmsInveChangManagementBeanVO selectCustomerInfoBybillID(String id);

    /**
     * 
     * @Title: selectCarNoInfo
     * @Description: 根据客户姓名和身份证查询收益卡号
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月15日 下午12:56:42
     * history:
     * 1、2017年4月15日 zhangmingjian 创建方法
     */
    public List<WmsInveChangManagementBeanVO> selectCarNoInfo(WmsInveChangManagementBeanVO bean);
    
    /**
     * 
     * @Title: selectChangeAppInfoById
     * @Description: 根据id查询变更单据信息
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月16日 上午10:26:26
     * history:
     * 1、2017年4月16日 zhangmingjian 创建方法
     */
    public Map<String,Object> selectChangeAppInfoById(WmsInveChangManagementBeanVO bean);
    
    /**
     * 
     * @Title: selectBankInfoByid
     * @Description: 根据上单id查询收益卡信息
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月16日 下午12:14:06
     * history:
     * 1、2017年4月16日 zhangmingjian 创建方法
     */
    public List<Map<String,Object>> selectBankInfoByid(WmsInveChangManagementBeanVO bean);
    
    /**
     * 
     * @Title: selectChangeAppAttInfo
     * @Description: 查询变更单据附件信息
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月16日 下午2:30:59
     * history:
     * 1、2017年4月16日 zhangmingjian 创建方法
     */
    public List<Map<String,Object>> selectChangeAppAttInfo(WmsInveChangManagementBeanVO bean);
}
