package com.zx.emanage.inve.service;

import java.util.Date;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IWmsInveExtendService
 * 模块名称：单据续期
 * @Description: 内容摘要：单据续期接口
 * @author Guanxu
 * @date 2016年12月3日
 * @version V1.0
 * history:
 * 1、2016年12月3日 Guanxu 创建文件
 * 2、2016年12月28日16:01:54 DongHao 修改方法  在方法中增加String类型的bill_type,bill_type等于0表示老单据,等于1表示新单据
 */
public interface IWmsInveExtendService {

    /**
      * @Title inveInveExtendSave
      * @Description 内容摘要:添加续期信息
      * @param wms_inve_transa_id 单据id
      * @param date_of_payment 续期时间
      * @param is_order_redeem 是否预约赎回
      * @param bill_type 单据类型(0表示为老单据,1表示为新单据)
      * @param protocol_type 债权协议类型(1表示字纸债权;2表示电子债权)
      * @param new_customer_email 邮箱地址
      * @param user 用户信息
      * @param wmsInveCustomerCard收益卡信息表
      * @author DongHao
      * @Time 2016年11月14日14:23:26
      * @history 1. 2016年11月14日14:23:31 DongHao 修改方法(修改方法添加是否预约赎回参数)
      */
    void inveInveExtendSave(WmsInveTransaProd wmsInveTransaProd, Integer wms_inve_transa_id, Date date_of_payment, Date date_of_end,
                            Integer is_order_redeem, String bill_type, String protocol_type, String new_customer_email, UserBean user,
                            WmsInveCustomerCard wmsInveCustomerCard);

    void autoInveExtendSave(String is_new_data);

    /**
     * @Title: inveReservationRenewalAutoHandler
     * @Description: 将预约续期的单据进行续期操作
     * @return 返回结果  scueess 或者 error 
     * @author: DongHao
     * @time:2016年11月18日 下午5:05:14
     * history:
     * 1、2016年11月18日 DongHao 创建方法
    */
    String inveReservationRenewalAutoHandler(String is_new_data);

    /**
     * @Title: verifyRenewalBill
     * @Description: 验证所要续期的单据是否是预约续期并且是可拆分类的产品
     * @param wms_inve_transa_id 上单表主键
     * @param wms_inve_transa_prod_id 上单产品表主键
     * @param date_of_payment 续期开始时间
     * @return 返回boolean类型的值,如果所要续期的单据是预约续期并且是可拆分类的产品返回true,否则返回false
     * @author: DongHao
     * @time:2017年3月13日 下午3:59:31
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    boolean verifyRenewalBill(String wms_inve_transa_id, String wms_inve_transa_prod_id, Date date_of_payment);

    /**
     * @Title: generateCreditProtocol
     * @Description: 生成预约续期债权合同
     * @return  返回map集合
     * @author: DongHao
     * @time:2017年3月20日 上午9:15:37
     * history:
     * 1、2017年3月20日 DongHao 创建方法
    */
    Map<String, Object> generateCreditProtocol();

}
