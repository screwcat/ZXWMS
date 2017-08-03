package com.zx.emanage.cusmanage.service.impl;
/**
 * 版权所有：版权所有(C) 2014，沈阳新融金融信息服务有限公司
 * 文件名称: WmsCusCustomerHeadController.java
 * 系统名称：WMS财务管理系统
 * 模块名称：客户管理模块
 * 内容摘要：主要实现贷款客户信息
 */
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.typeconverter.Convert;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerDataAttachmentDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineCarpinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineCompanyDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineContactDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineWorkinfoDao;
import com.zx.emanage.cremanage.persist.WmsCusCustomerChangeChildDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.cusmanage.persist.IWmsCusCustomerHeadDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerChildDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerHeadDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineCarpinfoDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineCompanyDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineHouseinfoDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineWorkinfoDao;
import com.zx.emanage.cusmanage.service.IWmsCusCustomerHeadService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerHeadSearchBeanVO;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineCompany;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineWorkinfo;
import com.zx.emanage.util.gen.entity.WmsCusCustomerChangeChild;
import com.zx.emanage.util.gen.entity.WmsCusCustomerChild;
import com.zx.emanage.util.gen.entity.WmsCusCustomerHead;
import com.zx.emanage.util.gen.entity.WmsCusCustomerLineCarpinfo;
import com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscuscustomerheadService")
public class WmsCusCustomerHeadServiceImpl implements IWmsCusCustomerHeadService
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerHeadServiceImpl.class);

    @Autowired
    private IWmsCusCustomerHeadDao wmscuscustomerheadDao;
   
    @Autowired
    private WmsCusCustomerHeadDao wmscuscustomerheadDao2;

    @Autowired
    private WmsCusCustomerLineWorkinfoDao wmscuscustomerlineworkinfodao2;

    @Autowired
    private WmsCusCustomerLineHouseinfoDao wmscuscustomerlinehouseinfodao2;
    
    @Autowired
    private WmsCusCustomerChildDao wmsCusCustomerChildDao;
    
    @Autowired
    private WmsCusCustomerLineCarpinfoDao wmsCusCustomerLineCarpinfoDao;
    
    @Autowired
    private WmsCusCustomerLineCompanyDao wmsCusCustomerLineCompanyDao;
    
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmscrecreditlinecustomerchangeheadDao;

    @Autowired
    private WmsCreCustomerChangeLineContactDao wmscrecustomerchangelinecontactDao;

    @Autowired
    private WmsCreCreditLineCustomerDataAttachmentDao wmscrecreditlinecustomerdataattachmentDao;

    @Autowired
    private WmsCreCustomerChangeLineHouseinfoDao wmscrecustomerchangelinehouseinfoDao;
    
    @Autowired
    private WmsCreCustomerChangeLineCarpinfoDao wmscrecustomerchangelinecarpinfoDao;

    @Autowired
    private WmsCreCustomerChangeLineWorkinfoDao wmscrecustomerchangelineworkinfoDao;
    
    @Autowired
    private WmsCreCustomerChangeLineCompanyDao wmscrecustomerchangelinecompanydao;
    
    @Autowired
    private WmsCusCustomerChangeChildDao wmscuscustomerchangechilddao;
    
    @Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;
    
    @Override
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("sortname",queryInfo.getSortname());
        resMap.put("sortorder",queryInfo.getSortorder());
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            resMap.put("customer_name",SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            resMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            resMap.put("id_card",queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            resMap.put("create_timestamp",Convert.toDate(queryInfo.getCreate_timestamp()));
            resMap.put("create_timestamp_end", new StringBuffer(queryInfo.getCreate_timestamp()).append(" 23:59:59.9").toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getStatus()))
        {
            resMap.put("status", queryInfo.getStatus());
        }
        resMap.put("Rows", wmscuscustomerheadDao2.getListWithoutPaging(resMap));
        return resMap;
    }
    
    @Override
    public Map<String, Object> getListWithoutPagingforadd(WmsCusCustomerHeadSearchBeanVO queryInfo, Integer userId)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        if (StringUtil.isBlank(queryInfo.getId_card()))
        {
            resMap.put("create_user_id",userId);
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            resMap.put("customer_name","%"+queryInfo.getCustomer_name()+"%");
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            resMap.put("mobile_telephone", "%"+queryInfo.getMobile_telephone()+"%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            resMap.put("id_card",queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            resMap.put("create_timestamp",Convert.toDate(queryInfo.getCreate_timestamp()));
            resMap.put("create_timestamp_end", sb.append(" 23:59:59.9").toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getStatus()))
        {
            resMap.put("status", queryInfo.getStatus());
        }
        resMap.put("Rows", wmscuscustomerheadDao2.search(resMap));
        return resMap;
    }

  	/**
     * 实现客户查询列表显示功能
     */
    @Override
	public Map<String, Object> getListWithPaging(WmsCusCustomerHeadSearchBeanVO queryInfo) 
	{
       
        Map<String,Object>paramMap = new HashMap<String, Object>();
	    if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
	    {
	        paramMap.put("customer_name","%"+queryInfo.getCustomer_name()+"%");
	    }
	    if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
	    {
            paramMap.put("mobile_telephone", "%"+queryInfo.getMobile_telephone()+"%");
        }
	    if (StringUtil.isNotBlank(queryInfo.getId_card()))
	    {
	        paramMap.put("id_card",queryInfo.getId_card());
	    }
	    if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
	    {
	        StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
	        paramMap.put("create_timestamp",Convert.toDate(queryInfo.getCreate_timestamp()));
	        paramMap.put("create_timestamp_end", sb.append(" 23:59:59.9").toString());
	    }
	    if (StringUtil.isNotBlank(queryInfo.getStatus()))
	    {
	        paramMap.put("status", queryInfo.getStatus());
	    }
	    //以下四项必须要写
	    paramMap.put("sortname",queryInfo.getSortname());
	    paramMap.put("sortorder",queryInfo.getSortorder());
	    paramMap.put("pagesize",queryInfo.getPagesize());
        paramMap.put("offset",queryInfo.getOffset());
        List<Map<String,Object>>list =wmscuscustomerheadDao2.search(paramMap);// mybatis查询列表
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(),wmscuscustomerheadDao2.findCount(paramMap),list);// mybatis构建前台需要数据结构*/
        /*
		GridDataBean bean = new GridDataBean(queryInfo.getPage(),
				wmscuscustomerheadDao.getListCountNum(queryInfo),
				wmscuscustomerheadDao.getListWithPaging(queryInfo));
				*/
		return bean.getGridData();
	}

    @Override
    public Map<String, Object> getListWithPagingforadd(WmsCusCustomerHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String,Object>paramMap = new HashMap<String, Object>();
        // 录入的身份证是否为空字符串 身份证为空利用用户id查询
        if (StringUtil.isBlank(queryInfo.getId_card()) &&
                StringUtil.isBlank(queryInfo.getCustomer_name()) && 
                StringUtil.isBlank(queryInfo.getMobile_telephone()) && 
                StringUtil.isBlank(queryInfo.getCreate_timestamp())
           )
        {
            paramMap.put("create_user_id", user.getUserId());
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp_end", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getStatus()))
        {
            paramMap.put("status", queryInfo.getStatus());
        }
        // 以下四项必须要写
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>>list = wmscuscustomerheadDao2.search(paramMap);// mybatis查询列表
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(),wmscuscustomerheadDao2.findCount(paramMap),list);// mybatis构建前台需要数据结构*/
        /*
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmscuscustomerheadDao.getListforAddCountNum(queryInfo, userId),
                                             wmscuscustomerheadDao.getListforAddWithPaging(queryInfo, userId));
        */
        return bean.getGridData();
    }

    @Override
    public WmsCusCustomerHead getInfoByPK(Integer wms_cus_customer_id)
    {
        return wmscuscustomerheadDao2.get(wms_cus_customer_id);
    }

    /**
     * 新增
     * 
     * @param bean
     * @return id
     */
    @Override
    @Transactional
    public int saveByPk(com.zx.emanage.util.gen.entity.WmsCusCustomerHead khxinfo,
                        com.zx.emanage.util.gen.entity.WmsCusCustomerLineWorkinfo gzinfo, String housestr,
                        UserBean user, String cusChild,String companystr, String carstr)
    {
        // 房产信息实体类list
        List<com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo> Lfcinfo = new ArrayList<com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo>();
        List<WmsCusCustomerChild> mcccListAdd=new ArrayList<WmsCusCustomerChild>();
        // 将房产信息 转换为list . 每个客户有多个房产

         Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        // //获取当前系统时间
        // ---------------------------------------------用户信息---------------------------------------------\\
        khxinfo.setCreate_user_id(user.getUserId()); // 传入创建人id
        khxinfo.setCreate_user_name(user.getRealname());// 传入创建人名称
        khxinfo.setCustomer_code(CodeNoUtil.getCustomerCode()); // 生产用户编码
        khxinfo.setCreate_timestamp(sysTime);
        khxinfo.setLast_update_timestamp(sysTime);

        // khxinfo.setCreate_timestamp(sysTime); //传入创建时间 = 当前系统时间
        khxinfo.setEnable_flag("1"); // 是否有效
        // ---------------------------------------------用户信息---------------------------------------------\\
        // ---------------------------------------------工作信息---------------------------------------------\\
        gzinfo.setCreate_user_id(user.getUserId()); // 工作信息表中插入创建人ID
        gzinfo.setLast_update_timestamp(sysTime);
        gzinfo.setCreate_timestamp(sysTime);// 传入创建时间
        gzinfo.setEnable_flag("1");// 是否有效
        // ---------------------------------------------工作信息---------------------------------------------\\

        List<com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo> Lfc = JsonUtil.jsonArrayToList(housestr,
                                                                                                             com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo.class);
        // List<WmsCusCustomerLineHouseinfoVO> Lfc =
        // JsonUtil.jsonArrayToList(housestr,
        // WmsCusCustomerLineHouseinfoVO.class);
        // List<WmsCusCustomerLineHouseinfoVO> Lfc = new
        // Gson().fromJson(housestr,new
        // TypeToken<WmsCusCustomerLineHouseinfoVO>() {}.getType());
        wmscuscustomerheadDao2.save(khxinfo); // 添加客户信息
        gzinfo.setWms_cus_customer_id(khxinfo.getWms_cus_customer_id()); // 往工作信息实体类中添加
        if ((gzinfo.getWork_unit_full_name()!=null&&!"".equals(gzinfo.getWork_unit_full_name()))||
         		(gzinfo.getWork_unit_telephone()!=null&&!"".equals(gzinfo.getWork_unit_telephone()))||
         		(gzinfo.getWork_unit_entry_date()!=null&&!"".equals(gzinfo.getWork_unit_entry_date().toString()))||
         		(gzinfo.getWork_unit_property()!=null&&!"0".equals(gzinfo.getWork_unit_property()))||
         		(gzinfo.getWork_unit_address_province()!=null&&!"".equals(gzinfo.getWork_unit_address_province()))||
         		(gzinfo.getWork_unit_address_city()!=null&&!"".equals(gzinfo.getWork_unit_address_city()))||
         		(gzinfo.getWork_unit_address_district()!=null&&!"".equals(gzinfo.getWork_unit_address_district()))||
         		(gzinfo.getWork_unit_address_more()!=null&&!"".equals(gzinfo.getWork_unit_address_more()))||
         		(gzinfo.getWork_unit_department()!=null&&!"".equals(gzinfo.getWork_unit_department()))){
        	wmscuscustomerlineworkinfodao2.save(gzinfo); 
         }                                                   

        for (com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo wmsCusCustomerLineHouseinfo : Lfc)
        {
            wmsCusCustomerLineHouseinfo.setCreate_user_id(user.getUserId()); // 创建人id
            // //创建时间
            wmsCusCustomerLineHouseinfo.setEnable_flag("1"); // 是否有效

            wmsCusCustomerLineHouseinfo.setWms_cus_customer_id(khxinfo.getWms_cus_customer_id()); // 关联客户表主键
            wmsCusCustomerLineHouseinfo.setLast_update_timestamp(sysTime);
            wmsCusCustomerLineHouseinfo.setCreate_timestamp(sysTime);//传入创建时间
            
            Lfcinfo.add(wmsCusCustomerLineHouseinfo);
        }
        for (int i = 0; i < Lfcinfo.size(); i++)
        {
            wmscuscustomerlinehouseinfodao2.save(Lfcinfo.get(i)); // 新增房产信息
        }
        
        List<com.zx.emanage.util.gen.entity.WmsCusCustomerChild> cusChildList = JsonUtil.jsonArrayToList(cusChild,
                com.zx.emanage.util.gen.entity.WmsCusCustomerChild.class);
        for (WmsCusCustomerChild mccc : cusChildList)
        {
        	mccc.setCreate_user_id(user.getUserId()); // 创建人id
            // //创建时间
        	mccc.setEnable_flag("1"); // 是否有效
        	mccc.setCreate_user_name(user.getRealname());// 传入创建人名称
        	mccc.setWms_cus_customer_id(khxinfo.getWms_cus_customer_id()); // 关联客户表主键
        	mccc.setLast_update_timestamp(sysTime);
        	mccc.setCreate_timestamp(sysTime);//传入创建时间
        	mcccListAdd.add(mccc);
        }
        for (int i = 0; i < mcccListAdd.size(); i++) 
        {
        	wmsCusCustomerChildDao.save(mcccListAdd.get(i));//添加子女信息
		}
        //车贷信息
        List<WmsCusCustomerLineCarpinfo> carpinfoListAdd=new ArrayList<WmsCusCustomerLineCarpinfo>();
        List<WmsCusCustomerLineCarpinfo> carCompList = JsonUtil.jsonArrayToList(carstr, WmsCusCustomerLineCarpinfo.class);
        for(WmsCusCustomerLineCarpinfo carpinfo : carCompList) {
        	carpinfo.setCreate_user_id(user.getUserId()); // 创建人id
            // //创建时间
        	carpinfo.setEnable_flag("1"); // 是否有效
        	carpinfo.setWms_cus_customer_id(khxinfo.getWms_cus_customer_id()); // 关联客户表主键
        	carpinfo.setLast_update_timestamp(sysTime);
        	carpinfo.setCreate_timestamp(sysTime);//传入创建时间
        	carpinfoListAdd.add(carpinfo);
        }
        for (int i = 0; i < carpinfoListAdd.size(); i++) {
        	wmsCusCustomerLineCarpinfoDao.save(carpinfoListAdd.get(i));//添加车贷信息
		}
        
        List<WmsCusCustomerLineCompany> mcclcListAdd=new ArrayList<WmsCusCustomerLineCompany>();
        List<com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany> cusCompList = JsonUtil.jsonArrayToList(companystr,
                com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany.class);
        for(WmsCusCustomerLineCompany mcclc : cusCompList)
        {
        	mcclc.setCreate_user_id(user.getUserId()); // 创建人id
            // //创建时间
        	mcclc.setEnable_flag("1"); // 是否有效
        	mcclc.setWms_cus_customer_id(khxinfo.getWms_cus_customer_id()); // 关联客户表主键
        	mcclc.setLast_update_timestamp(sysTime);
        	mcclc.setCreate_timestamp(sysTime);//传入创建时间
        	mcclcListAdd.add(mcclc);
        }
        for (int i = 0; i < mcclcListAdd.size(); i++) 
        {
        	wmsCusCustomerLineCompanyDao.save(mcclcListAdd.get(i));//添加子女信息
		}

        return khxinfo.getWms_cus_customer_id();
    }

    @Override
    public Map<String, Object> getInfoMapByPK(Integer wms_cus_customer_id)
    {
        Map<String, Object> mapAll = new HashMap<String, Object>();
        mapAll.putAll(wmscuscustomerheadDao2.get(wms_cus_customer_id).getInfoMap());
        if (wmscuscustomerlineworkinfodao2.get(wms_cus_customer_id)!=null)
        {
            mapAll.putAll(wmscuscustomerlineworkinfodao2.get(wms_cus_customer_id).getInfoMap());  
        }
        return mapAll;
    }

    /**
     * 更新
     */
    @Override
    @Transactional
    public String updateHHW(com.zx.emanage.util.gen.entity.WmsCusCustomerHead khxinfo,
                            com.zx.emanage.util.gen.entity.WmsCusCustomerLineWorkinfo gzinfo, String housestr,
                            UserBean user, String delrowids, String delccrowids, String delcomprowids,String delChildIds,
                            String cusChild,String companystr,String carstr, WmsCusCustomerHeadSearchBeanVO queryInfo)
    {
        // 房产信息实体类
        List<com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo> Lfcinfo = new ArrayList<com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo>();
        // 房产信息实体类list
        com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo fcinfoDel = new com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo();
        // 公司信息实体类
        List<com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany> compListInfo = new ArrayList<com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany>();
        //公司信息
        com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany compinfoDel = new com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany();
        
        // 子女信息
        List<com.zx.emanage.util.gen.entity.WmsCusCustomerChild> childListInfo = new ArrayList<com.zx.emanage.util.gen.entity.WmsCusCustomerChild>();
        //子女信息删除的IDS
        com.zx.emanage.util.gen.entity.WmsCusCustomerChild childinfoDel = new com.zx.emanage.util.gen.entity.WmsCusCustomerChild();
        
        // 将房产信息 转换为list . 每个客户有多个房产
        List<com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo> Lfc = JsonUtil.jsonArrayToList(housestr,
                                                                                                             com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo.class);
        // 将公司信息 转换为list
        List<com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany> compList = JsonUtil.jsonArrayToList(companystr,
                                                                                                             com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany.class);
        // 将子女信息 转换为list
        List<com.zx.emanage.util.gen.entity.WmsCusCustomerChild> childList = JsonUtil.jsonArrayToList(cusChild,
                                                                                                            com.zx.emanage.util.gen.entity.WmsCusCustomerChild.class);
        //车贷信息
        List<WmsCusCustomerLineCarpinfo> carpinfoListInfo = new ArrayList<WmsCusCustomerLineCarpinfo>();
        WmsCusCustomerLineCarpinfo carpinfoDel = new WmsCusCustomerLineCarpinfo();
        List<WmsCusCustomerLineCarpinfo> carCompList = JsonUtil.jsonArrayToList(carstr, WmsCusCustomerLineCarpinfo.class);
        
        // List<WmsCusCustomerLineHouseinfoVO> Lfc = new
        // Gson().fromJson(housestr,new
        // TypeToken<WmsCusCustomerLineHouseinfoVO>() {}.getType());

        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间

        khxinfo.setCreate_user_id(user.getUserId());
        khxinfo.setCreate_user_name(user.getRealname());
        khxinfo.setLast_update_timestamp(sysTime); // 最后修改时间
        khxinfo.setLast_update_user_id(user.getUserId());// 最后修改人ID
        wmscuscustomerheadDao2.update(khxinfo); // 更新客户信息
        // gzinfo.setWms_cus_customer_id(khxinfo.getWms_cus_customer_id());
        // //往工作信息实体类中添加 新增后的客户id.
       
        if(gzinfo.getWms_cus_customer_line_workinfo_id()==null||gzinfo.getWms_cus_customer_line_workinfo_id()==0){
        	 if((gzinfo.getWork_unit_full_name()!=null&&!"".equals(gzinfo.getWork_unit_full_name()))||
               		(gzinfo.getWork_unit_telephone()!=null&&!"".equals(gzinfo.getWork_unit_telephone()))||
               		(gzinfo.getWork_unit_entry_date()!=null&&!"".equals(gzinfo.getWork_unit_entry_date().toString()))||
               		(gzinfo.getWork_unit_property()!=null&&!"0".equals(gzinfo.getWork_unit_property()))||
               		(gzinfo.getWork_unit_address_province()!=null&&!"".equals(gzinfo.getWork_unit_address_province()))||
               		(gzinfo.getWork_unit_address_city()!=null&&!"".equals(gzinfo.getWork_unit_address_city()))||
               		(gzinfo.getWork_unit_address_district()!=null&&!"".equals(gzinfo.getWork_unit_address_district()))||
               		(gzinfo.getWork_unit_address_more()!=null&&!"".equals(gzinfo.getWork_unit_address_more()))||
               		(gzinfo.getWork_unit_department()!=null&&!"".equals(gzinfo.getWork_unit_department()))){
         		 gzinfo.setCreate_user_id(user.getUserId());
             	 gzinfo.setCreate_timestamp(sysTime);
                 gzinfo.setLast_update_timestamp(sysTime);
                 gzinfo.setEnable_flag("1");
             	 wmscuscustomerlineworkinfodao2.save(gzinfo);
              }           
        }else{
             gzinfo.setLast_update_timestamp(sysTime);
        	 gzinfo.setLast_update_user_id(user.getUserId());
        	 wmscuscustomerlineworkinfodao2.update(gzinfo); // 更新工作信息
        }
       

        fcinfoDel.setLast_update_timestamp(sysTime);
        fcinfoDel.setLast_update_user_id(user.getUserId());
        fcinfoDel.setEnable_flag("0"); // 设置为无效
        if (!(delrowids.equals("")))
        {
            for (String fcid : delrowids.split(","))
            {

                fcinfoDel.setWms_cus_customer_line_houseinfo_id(Integer.valueOf(fcid));
                wmscuscustomerlinehouseinfodao2.update(fcinfoDel);
            }

        }
        for (com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo wmsCusCustomerLineHouseinfo : Lfc)
        {
            wmsCusCustomerLineHouseinfo.setLast_update_timestamp(sysTime); // 最后更新时间
            wmsCusCustomerLineHouseinfo.setLast_update_user_id(user.getUserId()); // 最后更新人ID
            wmsCusCustomerLineHouseinfo.setWms_cus_customer_line_houseinfo_id(wmsCusCustomerLineHouseinfo.getWms_cus_customer_line_houseinfo_id()); // 主键ID
            Lfcinfo.add(wmsCusCustomerLineHouseinfo);
        }
        for (int i = 0; i < Lfcinfo.size(); i++)
        {
            if(null != Lfcinfo.get(i).getWms_cus_customer_line_houseinfo_id()) {
                if (Lfcinfo.get(i).getWms_cus_customer_line_houseinfo_id() == 0)
                {
                    Lfcinfo.get(i).setLast_update_timestamp(sysTime);
                    Lfcinfo.get(i).setLast_update_user_id(user.getUserId());
                    Lfcinfo.get(i).setCreate_timestamp(sysTime);
                    Lfcinfo.get(i).setCreate_user_id(user.getUserId()); // 创建人id
                    Lfcinfo.get(i).setEnable_flag("1"); // 是否有效
                    Lfcinfo.get(i).setWms_cus_customer_id(khxinfo.getWms_cus_customer_id()); // 关联客户表主键
                    wmscuscustomerlinehouseinfodao2.save(Lfcinfo.get(i));// 新增房产信息
                }
                else
                {
                    wmscuscustomerlinehouseinfodao2.update(Lfcinfo.get(i)); // 更新
                }
            }
        }
        
        //车产信息修改
        carpinfoDel.setLast_update_timestamp(sysTime);
        carpinfoDel.setLast_update_user_id(user.getUserId());
        carpinfoDel.setEnable_flag("0"); // 设置为无效
        if (!(delccrowids.equals(""))) {
            for (String ccid : delccrowids.split(",")) {

            	carpinfoDel.setWms_cus_customer_line_carpinfo_id(Integer.valueOf(ccid));
            	wmsCusCustomerLineCarpinfoDao.update(carpinfoDel);
            }

        }
        for (WmsCusCustomerLineCarpinfo wmsCusCustomerLineCarpinfo : carCompList) {
        	wmsCusCustomerLineCarpinfo.setLast_update_timestamp(sysTime); // 最后更新时间
        	wmsCusCustomerLineCarpinfo.setLast_update_user_id(user.getUserId()); // 最后更新人ID
        	wmsCusCustomerLineCarpinfo.setWms_cus_customer_line_carpinfo_id(wmsCusCustomerLineCarpinfo.getWms_cus_customer_line_carpinfo_id()); // 主键ID
        	carpinfoListInfo.add(wmsCusCustomerLineCarpinfo);
        }
        for (int i = 0; i < carpinfoListInfo.size(); i++) {
            if (carpinfoListInfo.get(i).getWms_cus_customer_line_carpinfo_id() == 0) {
                carpinfoListInfo.get(i).setLast_update_timestamp(sysTime);
                carpinfoListInfo.get(i).setLast_update_user_id(user.getUserId());
                carpinfoListInfo.get(i).setCreate_timestamp(sysTime);
                carpinfoListInfo.get(i).setCreate_user_id(user.getUserId()); // 创建人id
                carpinfoListInfo.get(i).setEnable_flag("1"); // 是否有效
                carpinfoListInfo.get(i).setWms_cus_customer_id(khxinfo.getWms_cus_customer_id()); // 关联客户表主键
                wmsCusCustomerLineCarpinfoDao.save(carpinfoListInfo.get(i));// 新增房产信息
            } else {
            	wmsCusCustomerLineCarpinfoDao.update(carpinfoListInfo.get(i)); // 更新
            }
        }
        
        
        compinfoDel.setLast_update_timestamp(sysTime);
        compinfoDel.setLast_update_user_id(user.getUserId());
        compinfoDel.setEnable_flag("0"); // 设置为无效
        if (!(delcomprowids.equals("")))
        {
            for (String comprid : delcomprowids.split(","))
            {

            	compinfoDel.setWms_cus_customer_line_company_id(Integer.valueOf(comprid));
            	wmsCusCustomerLineCompanyDao.update(compinfoDel);
            }
        }
        for (com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany wmsCusCustomerLineCompanyEn : compList)
        {
        	wmsCusCustomerLineCompanyEn.setLast_update_timestamp(sysTime); // 最后更新时间
        	wmsCusCustomerLineCompanyEn.setLast_update_user_id(user.getUserId()); // 最后更新人ID
        	wmsCusCustomerLineCompanyEn.setWms_cus_customer_line_company_id(wmsCusCustomerLineCompanyEn.getWms_cus_customer_line_company_id());
        	compListInfo.add(wmsCusCustomerLineCompanyEn);
        }
        for (int i = 0; i < compListInfo.size(); i++)
        {
            if (compListInfo.get(i).getWms_cus_customer_line_company_id()==null||compListInfo.get(i).getWms_cus_customer_line_company_id() == 0)
            {
            	compListInfo.get(i).setLast_update_timestamp(sysTime);
            	compListInfo.get(i).setLast_update_user_id(user.getUserId());
            	compListInfo.get(i).setCreate_timestamp(sysTime);
            	compListInfo.get(i).setCreate_user_id(user.getUserId()); // 创建人id
            	compListInfo.get(i).setEnable_flag("1"); // 是否有效
            	compListInfo.get(i).setWms_cus_customer_id(khxinfo.getWms_cus_customer_id()); // 关联客户表主键
            	wmsCusCustomerLineCompanyDao.save(compListInfo.get(i));// 新增房产信息
            }
            else
            {
            	wmsCusCustomerLineCompanyDao.update(compListInfo.get(i)); // 更新
            }
        }
        
        
        childinfoDel.setLast_update_timestamp(sysTime);
        childinfoDel.setLast_update_user_id(user.getUserId());
        childinfoDel.setEnable_flag("0"); // 设置为无效
        if (!(delChildIds.equals("")))
        {
            for (String delChildId : delChildIds.split(","))
            {

            	childinfoDel.setWms_cus_customer_child_id(Integer.valueOf(delChildId));
            	wmsCusCustomerChildDao.update(childinfoDel);
            }
            ;
        }
        for (com.zx.emanage.util.gen.entity.WmsCusCustomerChild wmsCusCustomerChildEn : childList)
        {
        	wmsCusCustomerChildEn.setLast_update_timestamp(sysTime); // 最后更新时间
        	wmsCusCustomerChildEn.setLast_update_user_id(user.getUserId()); // 最后更新人ID
        	wmsCusCustomerChildEn.setWms_cus_customer_child_id(wmsCusCustomerChildEn.getWms_cus_customer_child_id());
        	childListInfo.add(wmsCusCustomerChildEn);
        }
        for (int i = 0; i < childListInfo.size(); i++)
        {
            if (childListInfo.get(i).getWms_cus_customer_child_id() ==null||childListInfo.get(i).getWms_cus_customer_child_id() == 0)
            {
            	childListInfo.get(i).setLast_update_timestamp(sysTime);
            	childListInfo.get(i).setLast_update_user_id(user.getUserId());
            	childListInfo.get(i).setCreate_timestamp(sysTime);
            	childListInfo.get(i).setCreate_user_id(user.getUserId()); // 创建人id
            	childListInfo.get(i).setEnable_flag("1"); // 是否有效
            	childListInfo.get(i).setWms_cus_customer_id(khxinfo.getWms_cus_customer_id()); // 关联客户表主键
            	wmsCusCustomerChildDao.save(childListInfo.get(i));// 新增房产信息
            }
            else
            {
            	wmsCusCustomerChildDao.update(childListInfo.get(i)); // 更新
            }
        }
        
        if(StringUtils.isNotEmpty(queryInfo.getSyncCustomerInfoFlag())) {
            if(queryInfo.getSyncCustomerInfoFlag().equals("1")) {
                //修改客户信息时同步更新所有客户信息(没签合同的单据对应的客户信息变更表的信息)
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                paramMap.put("id_card", khxinfo.getId_card());
                paramMap.put("mobile_telephone1", khxinfo.getMobile_telephone1());
                paramMap.put("current_address_province", khxinfo.getCurrent_address_province());
                paramMap.put("current_address_city", khxinfo.getCurrent_address_city());
                paramMap.put("current_address_district", khxinfo.getCurrent_address_district());
                paramMap.put("current_address_more", khxinfo.getCurrent_address_more());
                paramMap.put("wms_cus_customer_head_id", khxinfo.getWms_cus_customer_id());
                
                this.wmscrecreditlinecustomerchangeheadDao.syncCustomerInfoForContract(paramMap);
            }
        }
        
        return "OK";
    }
/**
     * 实现客户管理对暂存中的数据进行软删除操作
     * 通过客户主键wms_cus_customer_id
     * wms_cus_customer_head客户主表
     * wms_cus_customer_child客户子女表
     * wms_cus_customer_line_workinfo客户工作信息
     * wms_cus_customer_line_houseinfo客户房产信息
     * wms_cus_customer_line_company客户公司信息
     * @return "succsss" or "error"
     */
   
    @Override
    public String deleteByPk(Integer wms_cus_customer_id) {
    	String result="success";
    	int ret=0;
    	//当返回>0的值说明有相应的数据,删除相关数据。
    	ret=wmscuscustomerheadDao2.deleteByPK(wms_cus_customer_id);
    	if(ret==0){
    		result="error";
    	}else{
    		wmsCusCustomerChildDao.deleteByPK(wms_cus_customer_id);
    		wmscuscustomerlineworkinfodao2.deleteByPK(wms_cus_customer_id);
    		wmscuscustomerlinehouseinfodao2.deleteByPK(wms_cus_customer_id);
    		wmsCusCustomerLineCompanyDao.deleteByPK(wms_cus_customer_id);    		
    	}
    	return result;
    }
    
    
    
    
    /**
     * 保存客户信息
     * @author Administrator
     */
    @Override
    @Transactional
    public int wmsCustomerSave(WmsCusCustomerHeadSearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------保存客户信息开始--------------------");
        
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        
        long now_long = System.currentTimeMillis();
        java.sql.Timestamp now_timestamp = new java.sql.Timestamp(now_long);
        
        //类型: 0:新增  1:修改
        Integer type = queryInfo.getType();
        
        //客户信息(json)
        String customerInfo = queryInfo.getCustomerInfo();
        
        //子女信息(json)
        String childInfo = queryInfo.getChildInfo();
        
        //房产信息(json)
        String houseInfo = queryInfo.getHouseInfo();
        
        //工作信息(json)
        String workInfo = queryInfo.getWorkInfo();
        
        //公司信息(json)
        String companyInfo = queryInfo.getCompanyInfo();
        
        WmsCreCreditLineCustomerChangeHead customer = new WmsCreCreditLineCustomerChangeHead();
        
        customer = gson.fromJson(customerInfo, WmsCreCreditLineCustomerChangeHead.class);
        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childInfo, WmsCusCustomerChangeChild.class);
        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(houseInfo, WmsCreCustomerChangeLineHouseinfo.class);
        WmsCreCustomerChangeLineWorkinfo work = JsonUtil.jsonStringToBean(workInfo, WmsCreCustomerChangeLineWorkinfo.class);
        WmsCreCustomerChangeLineCompany company = JsonUtil.jsonStringToBean(companyInfo, WmsCreCustomerChangeLineCompany.class);
        
        //保存修改日志
        try {
            WmsCreCreditHeadSearchBeanVO vo = new WmsCreCreditHeadSearchBeanVO();
            vo.setWms_cre_credit_head_id(customer.getWms_cre_credit_head_id());
            vo.setNow_timestamp(now_timestamp);
            vo.setLog_type("1");//修改客户信息
            this.wmsCreCreditHeadService.saveHouseLoanLog(vo, user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        
        if(type == 0) {//新增
            //保存客户信息
            customer.setCreate_timestamp(now_timestamp);
            customer.setCreate_user_id(user.getUserId());
            customer.setCreate_user_name(user.getRealname());
            customer.setLast_update_user_id(user.getUserId());
            customer.setLast_update_timestamp(now_timestamp);
            customer.setStatus("1");
            customer.setEnable_flag("1");
            wmscrecreditlinecustomerchangeheadDao.saveWithKey(customer);
            
            if(childList != null) {
              //保存子女信息
                for(WmsCusCustomerChangeChild child : childList) {
                    child.setCreate_user_id(user.getUserId());
                    child.setCreate_timestamp(now_timestamp);
                    child.setCreate_user_name(user.getRealname());
                    child.setLast_update_user_id(user.getUserId());
                    child.setLast_update_timestamp(now_timestamp);
                    child.setEnable_flag("1");
                    child.setWms_cre_credit_line_customer_change_head_id(customer.getWms_cre_credit_line_customer_change_head_id());
                    wmscuscustomerchangechilddao.save(child);
                } 
            }
            
            //保存房产信息
            for(WmsCreCustomerChangeLineHouseinfo house : houseList) {
                house.setCreate_user_id(user.getUserId());
                house.setCreate_timestamp(now_timestamp);
                house.setLast_update_user_id(user.getUserId());
                house.setLast_update_timestamp(now_timestamp);
                house.setEnable_flag("1");
                house.setWms_cre_credit_line_customer_change_head_id(customer.getWms_cre_credit_line_customer_change_head_id());
                wmscrecustomerchangelinehouseinfoDao.addNewRecord(house);
            }
            
            //保存工作信息
            work.setCreate_user_id(user.getUserId());
            work.setCreate_timestamp(now_timestamp);
            work.setLast_update_user_id(user.getUserId());
            work.setLast_update_timestamp(now_timestamp);
            work.setEnable_flag("1");
            work.setWms_cre_credit_line_customer_change_head_id(customer.getWms_cre_credit_line_customer_change_head_id());
            wmscrecustomerchangelineworkinfoDao.addNewRecord(work);
            
            //保存公司信息
            company.setCreate_user_id(user.getUserId());
            company.setCreate_timestamp(now_timestamp);
            company.setLast_update_user_id(user.getUserId());
            company.setLast_update_timestamp(now_timestamp);
            company.setEnable_flag("1");
            company.setWms_cre_credit_line_customer_change_head_id(customer.getWms_cre_credit_line_customer_change_head_id());
            wmscrecustomerchangelinecompanydao.save(company);
            
            
        } else if(type == 1) {//修改
            //更新客户信息
            customer.setLast_update_user_id(user.getUserId());
            customer.setLast_update_timestamp(now_timestamp);
            wmscrecreditlinecustomerchangeheadDao.update(customer);
            
            if(childList != null) {
                //更新子女信息
                for(WmsCusCustomerChangeChild child : childList) {
                    child.setLast_update_user_id(user.getUserId());
                    child.setLast_update_timestamp(now_timestamp);
                    if(child.getWms_cus_customer_change_child_id() != null) {
                        wmscuscustomerchangechilddao.update(child);
                    } else {
                        child.setCreate_user_id(user.getUserId());
                        child.setCreate_timestamp(now_timestamp);
                        child.setCreate_user_name(user.getRealname());
                        child.setEnable_flag("1");
                        child.setWms_cre_credit_line_customer_change_head_id(customer.getWms_cre_credit_line_customer_change_head_id());
                        wmscuscustomerchangechilddao.save(child);
                    }
                }
            }
            
            //根据主键删除子女信息
            if(queryInfo.getDeleteChildId() != null && queryInfo.getDeleteChildId().length > 0) {
                for(Integer deleteChildId : queryInfo.getDeleteChildId()) {
                    wmscuscustomerchangechilddao.deleteByKey(deleteChildId);
                }
            }
            
            //更新房产信息
            for(WmsCreCustomerChangeLineHouseinfo house : houseList) {
                house.setLast_update_user_id(user.getUserId());
                house.setLast_update_timestamp(now_timestamp);
                if(house.getWms_cre_customer_change_line_houseinfo_id() != null) {
                    wmscrecustomerchangelinehouseinfoDao.update(house);
                } else {
                    house.setCreate_user_id(user.getUserId());
                    house.setCreate_timestamp(now_timestamp);
                    house.setEnable_flag("1");
                    house.setWms_cre_credit_line_customer_change_head_id(customer.getWms_cre_credit_line_customer_change_head_id());
                    wmscrecustomerchangelinehouseinfoDao.addNewRecord(house);
                }
            }
            
            //更新工作信息
            work.setLast_update_user_id(user.getUserId());
            work.setLast_update_timestamp(now_timestamp);
            wmscrecustomerchangelineworkinfoDao.update(work);
            if(work.getWms_cre_customer_change_line_workinfo_id() != null) {
                wmscrecustomerchangelineworkinfoDao.update(work);
            } else {
                work.setCreate_user_id(user.getUserId());
                work.setCreate_timestamp(now_timestamp);
                work.setEnable_flag("1");
                work.setWms_cre_credit_line_customer_change_head_id(customer.getWms_cre_credit_line_customer_change_head_id());
                wmscrecustomerchangelineworkinfoDao.addNewRecord(work);
            }
            
            //更新公司信息
            company.setLast_update_user_id(user.getUserId());
            company.setLast_update_timestamp(now_timestamp);
            wmscrecustomerchangelinecompanydao.update(company);
            if(company.getWms_cre_customer_change_line_company_id() != null) {
                wmscrecustomerchangelinecompanydao.update(company);
            } else {
                company.setCreate_user_id(user.getUserId());
                company.setCreate_timestamp(now_timestamp);
                company.setEnable_flag("1");
                company.setWms_cre_credit_line_customer_change_head_id(customer.getWms_cre_credit_line_customer_change_head_id());
                wmscrecustomerchangelinecompanydao.save(company);
            }
        }
        
        log.debug("--------------------保存客户信息结束--------------------");
        return customer.getWms_cre_credit_line_customer_change_head_id();
    }
    
    
    
}
