package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveCreditEmail;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveCreditEmailDao extends BaseDao<WmsInveCreditEmail> {

    /**
     * @Title: getCreditEmailByEmailId
     * @Description: 根据邮件表主键获取邮件信息
     * @param wms_inve_credit_email_id
     * @return 
     * @author: DongHao
     * @time:2017年9月3日 上午1:02:36
     * history:
     * 1、2017年9月3日 DongHao 创建方法
    */
    Map<String, Object> getCreditEmailByEmailId(Integer wms_inve_credit_email_id);

    /**
     * @Title: getWmsInveCreditEmailInfoAll
     * @Description: 获取未发送的邮件(条件: 1. 邮件状态为未发送状态, 2. 合同类型为线上合同)
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年3月20日 上午9:19:46
     * history:
     * 1、2017年3月20日 DongHao 创建方法
    */
    List<Map<String, Object>> getWmsInveCreditEmailInfoAll();

    /**
     * @Title: updateWmsInveCreditEmailStatusById
     * @Description: 根据邮件表主键进行更新邮件信息
     * @param paramsMap 
     * @author: DongHao
     * @time:2017年3月20日 上午9:40:44
     * history:
     * 1、2017年3月20日 DongHao 创建方法
    */
    void updateWmsInveCreditEmailStatusById(Map<String, Object> paramsMap);

    /**
     * @Title: getSendEmailInfos
     * @Description: 获取未发送的邮件信息
     * @return 返回list数据集合信息
     * @author: DongHao
     * @time:2017年3月20日 上午9:42:13
     * history:
     * 1、2017年3月20日 DongHao 创建方法
    */
    List<Map<String, Object>> getSendEmailInfos();

    /**
     * @Title: updateCreditEmailById
     * @Description: 根据邮件表主键进行更新
     * @param params 
     * @author: DongHao
     * @time:2017年3月20日 上午10:09:56
     * history:
     * 1、2017年3月20日 DongHao 创建方法
    */
    void updateCreditEmailById(Map<String, Object> params);
	
}