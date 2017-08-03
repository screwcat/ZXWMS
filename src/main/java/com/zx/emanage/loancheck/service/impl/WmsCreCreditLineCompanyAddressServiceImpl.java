package com.zx.emanage.loancheck.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCompanyAddressDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineEnteFixedAssetEquipDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineEnteFixedAssetHouseDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineEnteFixedAssetVehicleDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineEnteSuppPurchaDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineEquityDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineInventoryGoodMaterialDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineMortgageDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineOtherDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineReceivablePayableDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineCompanyAddressService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCompanyAddressSearchBeanVO;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineGoodMaterialVo;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineInventoryVo;
import com.zx.emanage.loancheck.vo.WmsCreCreditLinePayableVo;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineReceivableVo;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyAddress;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerDataAttachment;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteFixedAssetEquip;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteFixedAssetHouse;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteFixedAssetVehicle;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteSuppPurcha;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEquity;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineInventoryGoodMaterial;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineMortgage;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineOther;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineReceivablePayable;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineWorkinfo;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinecompanyaddressService")
public class WmsCreCreditLineCompanyAddressServiceImpl implements IWmsCreCreditLineCompanyAddressService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCompanyAddressServiceImpl.class);

    @Autowired
    private WmsCreCreditLineCompanyAddressDao wmscrecreditlinecompanyaddressDao;

    @Autowired
    private WmsCreCreditLineEquityDao wmsCreCreditLineEquityDao;

    @Autowired
    private WmsCreCreditLineEnteFixedAssetHouseDao wmsCreCreditLineEnteFixedAssetHouseDao;

    @Autowired
    private WmsCreCreditLineEnteFixedAssetEquipDao wmsCreCreditLineEnteFixedAssetEquipDao;

    @Autowired
    private WmsCreCreditLineEnteFixedAssetVehicleDao wmsCreCreditLineEnteFixedAssetVehicleDao;

    @Autowired
    private WmsCreCreditLineEnteSuppPurchaDao wmsCreCreditLineEnteSuppPurchaDao;

    @Autowired
    private WmsCreCreditLineMortgageDao wmsCreCreditLineMortgageDao;

    @Autowired
    private WmsCreCreditLineInventoryGoodMaterialDao wmsCreCreditLineInventoryGoodMaterialDao;

    @Autowired
    private WmsCreCreditLineReceivablePayableDao wmsCreCreditLineReceivablePayableDao;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private WmsCreCreditLineOtherDao wmsCreCreditLineOtherDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCompanyAddressSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinecompanyaddressDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCompanyAddressSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinecompanyaddressDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinecompanyaddressDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineCompanyAddress getInfoByPK(Integer wms_cre_credit_line_company_address_id)
    {
        return wmscrecreditlinecompanyaddressDao.get(wms_cre_credit_line_company_address_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineCompanyAddress bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecompanyaddressDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineCompanyAddress bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecompanyaddressDao.update(bean); // update a record
                                                              // replace
                                                              // columns,
                                                              // nonsupport null
                                                              // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCreditLineCompanyAddress() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineCompanyAddress> getListByEntity(WmsCreCreditLineCompanyAddress queryInfo,
                                                                 Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineCompanyAddress> beanList = wmscrecreditlinecompanyaddressDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 保存电审审核信息
     * 
     * @author 张风山
     */
    @Override
    @Transactional
    public String saveAll(String qyjbqk, String gqjg, String qygdzc_cfxx, String qygdzc_clxx, String qygdzc_sbxx,
                          String sxyqyxx_syghs, String sxyqyxx_xycgs, String dywqk, String jyqk_yszkmx,
                          String jyqk_yfzkmx, String jyqk_kcyl, String jyqk_kcspmx, String wms_cre_credit_head_id,
                          WmsCreCreditLineOther wmsCreCreditLineOther, WmsCreditWorkFlowVO approveWorkFlowVO,
                          UserBean user)
    {
        String result = "success";
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        List<WmsCreCreditLineCompanyAddress> mcclca = JsonUtil.jsonArrayToList(qyjbqk,
                                                                               WmsCreCreditLineCompanyAddress.class);
        List<WmsCreCreditLineEquity> mccle = JsonUtil.jsonArrayToList(gqjg, WmsCreCreditLineEquity.class);
        List<WmsCreCreditLineEnteFixedAssetHouse> mcclefah = JsonUtil.jsonArrayToList(qygdzc_cfxx,
                                                                                      WmsCreCreditLineEnteFixedAssetHouse.class);
        List<WmsCreCreditLineEnteFixedAssetVehicle> mcclefav = JsonUtil.jsonArrayToList(qygdzc_clxx,
                                                                                        WmsCreCreditLineEnteFixedAssetVehicle.class);
        List<WmsCreCreditLineEnteFixedAssetEquip> mcclefae = JsonUtil.jsonArrayToList(qygdzc_sbxx,
                                                                                      WmsCreCreditLineEnteFixedAssetEquip.class);
        List<WmsCreCreditLineEnteSuppPurcha> mccles = JsonUtil.jsonArrayToList(sxyqyxx_syghs,
                                                                               WmsCreCreditLineEnteSuppPurcha.class);
        List<WmsCreCreditLineEnteSuppPurcha> mcclep = JsonUtil.jsonArrayToList(sxyqyxx_xycgs,
                                                                               WmsCreCreditLineEnteSuppPurcha.class);
        List<WmsCreCreditLineMortgage> mcclm = JsonUtil.jsonArrayToList(dywqk, WmsCreCreditLineMortgage.class);
        List<WmsCreCreditLineReceivablePayable> mcclr = JsonUtil.jsonArrayToList(jyqk_yszkmx,
                                                                                 WmsCreCreditLineReceivablePayable.class);
        List<WmsCreCreditLineReceivablePayable> mcclp = JsonUtil.jsonArrayToList(jyqk_yfzkmx,
                                                                                 WmsCreCreditLineReceivablePayable.class);
        List<WmsCreCreditLineInventoryGoodMaterial> mccli = JsonUtil.jsonArrayToList(jyqk_kcyl,
                                                                                     WmsCreCreditLineInventoryGoodMaterial.class);
        List<WmsCreCreditLineInventoryGoodMaterial> mcclgm = JsonUtil.jsonArrayToList(jyqk_kcspmx,
                                                                                      WmsCreCreditLineInventoryGoodMaterial.class);

        /*------------------------wms_cre_credit_line_company_address 电审审批_企业基本情况表 begin----------------------------------------------*/
        if (mcclca.size() != 0)
        {
            for (int j = 0; j < mcclca.size(); j++)
            {
                WmsCreCreditLineCompanyAddress vo = mcclca.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmscrecreditlinecompanyaddressDao.save(vo);
            }
        }

        /*------------------------wms_cre_credit_line_company_address 电审审批_企业基本情况表 end----------------------------------------------*/

        /*------------------------wms_cre_credit_line_equity 电审审批_股权结构表 begin----------------------------------------------*/

        if (mccle.size() != 0)
        {
            for (int j = 0; j < mccle.size(); j++)
            {
                WmsCreCreditLineEquity vo = mccle.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineEquityDao.save(vo);
            }
        }

        /*------------------------wms_cre_credit_line_company_address 电审审批_企业基本情况表 end----------------------------------------------*/

        /*------------------------wms_cre_credit_line_ente_fixed_asset_house 电审审批_企业固定资产_房产 begin----------------------------------------------*/

        if (mcclefah.size() != 0)
        {
            for (int j = 0; j < mcclefah.size(); j++)
            {
                WmsCreCreditLineEnteFixedAssetHouse vo = mcclefah.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineEnteFixedAssetHouseDao.save(vo);
            }
        }

        /*------------------------wms_cre_credit_line_ente_fixed_asset_house 电审审批_企业固定资产_房产 end----------------------------------------------*/

        /*------------------------wms_cre_credit_line_ente_fixed_asset_vehicle 电审审批_企业固定资产_车辆 begin----------------------------------------------*/
        if (mcclefav.size() != 0)
        {
            for (int j = 0; j < mcclefav.size(); j++)
            {
                WmsCreCreditLineEnteFixedAssetVehicle vo = mcclefav.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineEnteFixedAssetVehicleDao.save(vo);
            }
        }
        /*------------------------wms_cre_credit_line_ente_fixed_asset_vehicle 电审审批_企业固定资产_车辆 end----------------------------------------------*/
        /*------------------------wms_cre_credit_line_ente_fixed_asset_equip 电审审批_企业固定资产_设备 begin----------------------------------------------*/
        if (mcclefae.size() != 0)
        {
            for (int j = 0; j < mcclefae.size(); j++)
            {
                WmsCreCreditLineEnteFixedAssetEquip vo = mcclefae.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineEnteFixedAssetEquipDao.save(vo);
            }
        }
        /*------------------------wms_cre_credit_line_ente_fixed_asset_equip 电审审批_企业固定资产_设备 end----------------------------------------------*/

        /*------------------------wms_cre_credit_line_ente_supp_purcha 电审审批_上下游企业_供应商_采购商 begin----------------------------------------------*/
        if (mccles.size() != 0)
        {
            for (int j = 0; j < mccles.size(); j++)
            {
                WmsCreCreditLineEnteSuppPurcha vo = mccles.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setEnte_type("1");
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineEnteSuppPurchaDao.save(vo);
            }
        }
        if (mcclep.size() != 0)
        {
            for (int j = 0; j < mcclep.size(); j++)
            {
                WmsCreCreditLineEnteSuppPurcha vo = mcclep.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setEnte_type("2");
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineEnteSuppPurchaDao.save(vo);
            }
        }
        /*------------------------wms_cre_credit_line_ente_supp_purcha 电审审批_上下游企业_供应商_采购商 end----------------------------------------------*/

        /*------------------------wms_cre_credit_line_mortgage 电审审批_抵押物 begin----------------------------------------------*/

        if (mcclm.size() != 0)
        {
            for (int j = 0; j < mcclm.size(); j++)
            {
                WmsCreCreditLineMortgage vo = mcclm.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineMortgageDao.save(vo);
            }
        }
        /*------------------------wms_cre_credit_line_mortgage 电审审批_抵押物 end----------------------------------------------*/

        /*------------------------wms_cre_credit_line_receivable_payable 电审审批_经营情况_应收应付账款 begin----------------------------------------------*/
        if (mcclr.size() != 0)
        {
            for (int j = 0; j < mcclr.size(); j++)
            {
                WmsCreCreditLineReceivablePayable vo = mcclr.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setReceivable_payable_type("1");
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineReceivablePayableDao.save(vo);
            }
        }

        if (mcclp.size() != 0)
        {
            for (int j = 0; j < mcclp.size(); j++)
            {
                WmsCreCreditLineReceivablePayable vo = mcclp.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setReceivable_payable_type("2");
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineReceivablePayableDao.save(vo);
            }
        }
        /*------------------------wms_cre_credit_line_receivable_payable 电审审批_经营情况_应收应付账款 end----------------------------------------------*/

        /*------------------------wms_cre_credit_line_inventory_good_material 电审审批_经营情况_库存原料商品 begin----------------------------------------------*/
        if (mccli.size() != 0)
        {
            for (int j = 0; j < mccli.size(); j++)
            {
                WmsCreCreditLineInventoryGoodMaterial vo = mccli.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setGood_material_type("1");
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineInventoryGoodMaterialDao.save(vo);
            }
        }

        if (mcclgm.size() != 0)
        {
            for (int j = 0; j < mcclgm.size(); j++)
            {
                WmsCreCreditLineInventoryGoodMaterial vo = mcclgm.get(j);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_user_name(user.getRealname());
                vo.setGood_material_type("2");
                vo.setEnable_flag("1");
                vo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreCreditLineInventoryGoodMaterialDao.save(vo);
            }
        }

        /*------------------------wms_cre_credit_line_inventory_good_material 电审审批_经营情况_库存原料商品 end----------------------------------------------*/
        /*------------------------wms_cre_credit_line_other 电审审批_其他情况说明 begin----------------------------------------------*/

        wmsCreCreditLineOther.setCreate_user_id(user.getUserId());
        wmsCreCreditLineOther.setCreate_user_name(user.getRealname());
        wmsCreCreditLineOther.setEnable_flag("1");
        wmsCreCreditLineOther.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineOther.setCreate_timestamp(new Timestamp(new Date().getTime()));
        wmsCreCreditLineOther.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        wmsCreCreditLineOtherDao.save(wmsCreCreditLineOther);

        /*------------------------wms_cre_credit_line_other 电审审批_其他情况说明 end----------------------------------------------*/

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("phone_appro_user_id", user.getUserId());
        params.put("phone_appro_user_name", user.getRealname());
        params.put("phone_appro_timestamp", sysTime);
        params.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        wmscrecreditheaddao.updateRecord(params);
        approveWorkFlowVO.setUser_id(user.getUserId());
        approveWorkFlowVO.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        creditWorkFlowService.telTeamCheckApproveWorkFlow(approveWorkFlowVO);

        return "success";
    }

    /**
     * 根据贷款信息表id查询对应的电审审批信息
     * 
     * @author 张风山
     */
    @Override
    public Map<String, Object> selectAllByWmsCreCreditHeadId(String wms_cre_credit_head_id, UserBean user)
    {
        // 创建审批信息对应表的实体
        WmsCreCreditLineCompanyAddress wmsCreCreditLineCompanyAddress = new WmsCreCreditLineCompanyAddress();
        WmsCreCreditLineEquity wmsCreCreditLineEquity = new WmsCreCreditLineEquity();
        WmsCreCreditLineEnteFixedAssetHouse wmsCreCreditLineEnteFixedAssetHouse = new WmsCreCreditLineEnteFixedAssetHouse();
        WmsCreCreditLineEnteFixedAssetVehicle wmsCreCreditLineEnteFixedAssetVehicle = new WmsCreCreditLineEnteFixedAssetVehicle();
        WmsCreCreditLineEnteFixedAssetEquip wmsCreCreditLineEnteFixedAssetEquip = new WmsCreCreditLineEnteFixedAssetEquip();
        WmsCreCreditLineEnteSuppPurcha wmsCreCreditLineEnteSupp = new WmsCreCreditLineEnteSuppPurcha();
        WmsCreCreditLineEnteSuppPurcha wmsCreCreditLineEntePurcha = new WmsCreCreditLineEnteSuppPurcha();
        WmsCreCreditLineMortgage wmsCreCreditLineMortgage = new WmsCreCreditLineMortgage();
        WmsCreCreditLineReceivablePayable wmsCreCreditLineReceivable = new WmsCreCreditLineReceivablePayable();
        WmsCreCreditLineReceivablePayable wmsCreCreditLinePayable = new WmsCreCreditLineReceivablePayable();
        WmsCreCreditLineInventoryGoodMaterial wmsCreCreditLineInventory = new WmsCreCreditLineInventoryGoodMaterial();
        WmsCreCreditLineInventoryGoodMaterial wmsCreCreditLineGoodMaterial = new WmsCreCreditLineInventoryGoodMaterial();
        WmsCreCreditLineOther wmsCreCreditLineOther = new WmsCreCreditLineOther();

        // 插入查询条件
        wmsCreCreditLineCompanyAddress.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineEquity.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineEnteFixedAssetHouse.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineEnteFixedAssetVehicle.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineEnteFixedAssetEquip.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineEntePurcha.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineEntePurcha.setEnte_type("2");
        wmsCreCreditLineEnteSupp.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineEnteSupp.setEnte_type("1");
        wmsCreCreditLineMortgage.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineReceivable.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineReceivable.setReceivable_payable_type("1");
        wmsCreCreditLinePayable.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLinePayable.setReceivable_payable_type("2");
        wmsCreCreditLineInventory.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineInventory.setGood_material_type("1");
        wmsCreCreditLineGoodMaterial.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreCreditLineGoodMaterial.setGood_material_type("2");
        wmsCreCreditLineOther.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));

        // 调用查询方法，获取对应表的数据
        List<WmsCreCreditLineCompanyAddress> mcclcaList = wmscrecreditlinecompanyaddressDao.getListByEntity(wmsCreCreditLineCompanyAddress);
        List<WmsCreCreditLineEquity> mccleList = wmsCreCreditLineEquityDao.getListByEntity(wmsCreCreditLineEquity);
        List<WmsCreCreditLineEnteFixedAssetHouse> mcclefahList = wmsCreCreditLineEnteFixedAssetHouseDao.getListByEntity(wmsCreCreditLineEnteFixedAssetHouse);
        List<WmsCreCreditLineEnteFixedAssetVehicle> mcclefavList = wmsCreCreditLineEnteFixedAssetVehicleDao.getListByEntity(wmsCreCreditLineEnteFixedAssetVehicle);
        List<WmsCreCreditLineEnteFixedAssetEquip> mcclefaeList = wmsCreCreditLineEnteFixedAssetEquipDao.getListByEntity(wmsCreCreditLineEnteFixedAssetEquip);
        List<WmsCreCreditLineEnteSuppPurcha> mcclesList = wmsCreCreditLineEnteSuppPurchaDao.getListByEntity(wmsCreCreditLineEnteSupp);
        List<WmsCreCreditLineEnteSuppPurcha> mcclepList = wmsCreCreditLineEnteSuppPurchaDao.getListByEntity(wmsCreCreditLineEntePurcha);
        List<WmsCreCreditLineMortgage> mcclmList = wmsCreCreditLineMortgageDao.getListByEntity(wmsCreCreditLineMortgage);
        List<WmsCreCreditLineReceivablePayable> mcclrList = wmsCreCreditLineReceivablePayableDao.getListByEntity(wmsCreCreditLineReceivable);
        List<WmsCreCreditLineReceivablePayable> mcclpList = wmsCreCreditLineReceivablePayableDao.getListByEntity(wmsCreCreditLinePayable);
        List<WmsCreCreditLineInventoryGoodMaterial> mccliList = wmsCreCreditLineInventoryGoodMaterialDao.getListByEntity(wmsCreCreditLineInventory);
        List<WmsCreCreditLineInventoryGoodMaterial> mcclgmList = wmsCreCreditLineInventoryGoodMaterialDao.getListByEntity(wmsCreCreditLineGoodMaterial);
        List<WmsCreCreditLineOther> mccloList = wmsCreCreditLineOtherDao.getListByEntity(wmsCreCreditLineOther);

        // 装载数据，返回到前台页面
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("qyjbqk", mcclcaList);
        resMap.put("gqjg", mccleList);
        resMap.put("qygdzc_cfxx", mcclefahList);
        resMap.put("qygdzc_clxx", mcclefavList);
        resMap.put("qygdzc_sbxx", mcclefaeList);
        resMap.put("sxyqyxx_syghs", mcclesList);
        resMap.put("sxyqyxx_xycgs", mcclepList);
        resMap.put("dywqk", mcclmList);
        resMap.put("jyqk_yszkmx", mcclrList);
        resMap.put("jyqk_yfzkmx", mcclpList);
        resMap.put("jyqk_kcyl", mccliList);
        resMap.put("jyqk_kcspmx", mcclgmList);
        resMap.put("other", mccloList);
        return resMap;
    }

}
