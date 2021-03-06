package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveChangManagementDao;
import com.zx.emanage.inve.service.IWmsInveChangManagementService;
import com.zx.emanage.inve.vo.WmsInveChangManagementBeanVO;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveChangManagementServiceImpl
 * 模块名称：变更申请
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年4月11日
 * @version V3.5
 * history:
 * 1、2017年4月11日 zhangmingjian 创建文件
 */
@Service
public class WmsInveChangManagementServiceImpl implements IWmsInveChangManagementService
{
    @Autowired
    private WmsInveChangManagementDao wmsInveChangManagementDao;
    
    /**
     * 
     * @Title: getBillNumber
     * @Description:获取单据编号
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月11日 上午10:15:56
     * history:
     * 1、2017年4月11日 zhangmingjian 创建方法
     */
    @Override
    public String getBillNumber()
    {
        return wmsInveChangManagementDao.getBillNumber();
    }

    /**
     * 
     * @Title: selectBillInfo
     * @Description: 查询单据列表
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月11日 下午1:19:16
     * history:
     * 1、2017年4月11日 zhangmingjian 创建方法
     */
    @Override
    public List<Map<String, Object>> selectBillInfo(WmsInveChangManagementBeanVO bean)
    {
        if(StringUtils.isNotBlank(bean.getId())){
            bean.setId(bean.getId().split(",")[0]);
        }
        List<Map<String,Object>> list = wmsInveChangManagementDao.selectBillInfo(bean);
        if(list!=null&&list.size()>0){
        for (int i = 0; i < list.size(); i++)
        {
              getIdCardAndMobilePhoneInfo(list.get(i));
        }
        }
        return list;
    }

    /**
     * 
     * @Title: saveChangeAppInfo
     * @Description: 保存变更单据信息
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月12日 下午2:00:44
     * history:
     * 1、2017年4月12日 zhangmingjian 创建方法
     */
    @Override
    @Transactional
    public String saveChangeAppInfo(WmsInveChangManagementBeanVO bean)
    {
        Integer id = (Integer) bean.getDatagrid().get(0).get("wms_inve_transa_id");
        bean.setId(id.toString());
        //查询联系方式 证件
        WmsInveChangManagementBeanVO customerInfo = wmsInveChangManagementDao.selectCustomerInfoBybillID(bean.getId());
        if (customerInfo != null)
        {
            // 将电话保存到bean
            bean.setMobile_phone(customerInfo.getMobile_phone());
            // 将身份证保存到bean
            bean.setId_card(customerInfo.getId_card());
        }
           //查询客户所有的收益卡号
        List<WmsInveChangManagementBeanVO> card_no_list = wmsInveChangManagementDao.selectCarNoInfo(bean);
        boolean saveflag = true;
        if(card_no_list!=null&&card_no_list.size()>0){
            //遍历收益卡号
            for (int i = 0; i < card_no_list.size(); i++)
            {
                //如果卡号相同  取出收益卡的id 存入bean
                if(card_no_list.get(i).equals(bean.getCard_no())){
                    bean.setWms_inve_customer_card_id(card_no_list.get(i).getWms_inve_customer_card_id());
                    saveflag = false;
                    break;
                }
            }
        }
        
        String errorstr = "true";
        int num = 0;
        if(saveflag){
        // 保存收益卡信息
            num= wmsInveChangManagementDao.saveCustomerCardInfo(bean);
        }
        if (num > 0)
        {
            // 保存变更申请单
            num = wmsInveChangManagementDao.saveChangeAppInfo(bean);
        }
        else
        {
            return errorstr = "保存收益卡信息失败";
        }
        if (num > 0)
        {
            // 保存变更申请单附件
            num = wmsInveChangManagementDao.saveChangeAppAttInfo(bean);
        }
        else
        {
            return errorstr = "保存变更申请单附件失败";

        }
        if (num > 0)
        {
            // 保存变更申请单附件
            num = wmsInveChangManagementDao.saveChangeAppTransaInfo(bean);
        }
        else
        {
            return errorstr = "保存变更申请单失败";
            
        }

        // 更新客户收益表的收益卡id
        num = wmsInveChangManagementDao.updateTransaIncomeInfo(bean);
        // 更新上单产品表的收益卡id
        num = wmsInveChangManagementDao.updateTransaProdInfo(bean);

        return errorstr;
    }

    /**
     * 
     * @Title: selectDictInfo
     * @Description: 查询数据字典
     * @param dict_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月14日 上午9:28:52
     * history:
     * 1、2017年4月14日 zhangmingjian 创建方法
     */
    @Override
    public List<Map<String, Object>> selectDictInfo(String dict_id)
    {
        if (StringUtils.isBlank(dict_id))
        {
            return wmsInveChangManagementDao.selectDictInfo(dict_id);
        }
        else
        {
            return wmsInveChangManagementDao.selectDictInfoByCity(dict_id);
        }

    }

    /**
     * 
     * @Title: selectChangeAppList
     * @Description: 变量申请列表
     * @param personnel_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月14日 下午3:08:28
     * history:
     * 1、2017年4月14日 zhangmingjian 创建方法
     */
    @Override
    public Map<String, Object> selectChangeAppList(WmsInveChangManagementBeanVO bean)
    {
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Object>> list = wmsInveChangManagementDao.selectChangeAppList(bean);
        if(list!=null&&list.size()>0){
        for (int i = 0; i < list.size(); i++)
        {
              getIdCardAndMobilePhoneInfo(list.get(i));
        }
        }
        
        

        map.put("Rows", list);
        
        map.put("total", wmsInveChangManagementDao.countChangeAppList(bean));
        return map;
    }
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
    public Map<String,Object> selectChangeAppInfoById(WmsInveChangManagementBeanVO bean){
        Map<String,Object> map = wmsInveChangManagementDao.selectChangeAppInfoById(bean);
        //屏蔽身份证号
        getIdCardAndMobilePhoneInfo(map);
        bean.setIds(map.get("wms_inve_transa_ids").toString().split(","));
        //查询收益卡相关信息
        List<Map<String,Object>> bankinfo  =   wmsInveChangManagementDao.selectBankInfoByid(bean);
        //查询附件相关信息
        List<Map<String,Object>> attinfo  =   wmsInveChangManagementDao.selectChangeAppAttInfo(bean);
        
        map.put("bankinfo", bankinfo);
        map.put("attinfo", attinfo);
        return map;
    };
    /**
     * 
     * @Title: getIdCardAndMobilePhoneInfo
     * @Description: 处理身份证和联系方式
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月15日 下午2:03:42
     * history:
     * 1、2017年4月15日 zhangmingjian 创建方法
     */
    private Map<String,Object>  getIdCardAndMobilePhoneInfo(Map<String,Object> map){
        String id_card="",mobile_phone="";
        if(map.get("id_card")!=null){
            id_card = (String)map.get("id_card");
        }
        if(map.get("mobile_phone")!=null){
            mobile_phone = (String)map.get("mobile_phone");
        }
        
        
        if(id_card.length() == 18)
          {
              id_card = id_card.substring(0,4) + "**********" + id_card.substring(14);  
          }
          else if(id_card.length() == 8)
          {
              id_card = id_card.substring(0,4) + "**********" + id_card.substring(4, id_card.length());
          }
          else
          {
              id_card = "********";
          }
        if( StringUtils.isNotBlank(mobile_phone)&& mobile_phone.length()==11){
            String str1 =  mobile_phone.substring(0,3);
            String str2 =  mobile_phone.substring(mobile_phone.length()-4,mobile_phone.length());
             mobile_phone = str1+"****"+str2;
        }else{
            mobile_phone = "********";
        }
        
        map.put("mobile_phone", mobile_phone);
        map.put("id_card", id_card);
        return map;
    }
    
    

}
