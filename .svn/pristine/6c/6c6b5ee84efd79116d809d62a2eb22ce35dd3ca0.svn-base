package com.zx.emanage.sysmanage.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.internal.LinkedTreeMap;
import com.zx.emanage.inve.persist.WmsInveCustomerDao;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.sysmanage.persist.CrmCustomerInfoDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysPostDao;
import com.zx.emanage.sysmanage.service.ICrmCustomerInfoService;
import com.zx.emanage.sysmanage.vo.CrmCustomerInfoSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.CrmCustomerInfo;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("crmcustomerinfoService")
public class CrmCustomerInfoServiceImpl implements ICrmCustomerInfoService {
	private static Logger log = LoggerFactory
			.getLogger(CrmCustomerInfoServiceImpl.class);

	@Autowired
	private CrmCustomerInfoDao crmcustomerinfoDao;
	@Autowired
	private PmPersonnelDao pmPersonnelDao;
	@Autowired
	private WmsInveCustomerDao wmsInveCustomerDao;
	@Autowired
	private SysDeptDao sysDeptDao;
	@Autowired
	private SysPostDao sysPostDao;

	@Autowired
	private IWmsInveTransaService wmsInveTransaService;

	@Override
	public Map<String, Object> getListWithoutPaging(
			CrmCustomerInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		List<Map<String, Object>> list = crmcustomerinfoDao.search(paramMap);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows", list);
		return resMap;
	}

	@Override
	public Map<String, Object> getListWithPaging(
			CrmCustomerInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		paramMap.put("pagesize", queryInfo.getPagesize());
		paramMap.put("offset", queryInfo.getOffset());
		List<Map<String, Object>> list = crmcustomerinfoDao.search(paramMap);
		GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
				queryInfo.getPage(), crmcustomerinfoDao.findCount(paramMap),
				list);
		return bean.getGridData();
	}

	@Override
	public CrmCustomerInfo getInfoByPK(Integer costomer_id) {
		return crmcustomerinfoDao.get(costomer_id);
	}

	@Override
	@Transactional
	public String save(CrmCustomerInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = crmcustomerinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(CrmCustomerInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = crmcustomerinfoDao.update(bean); // update a record replace
												// columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	/**
	 * Description :check repeat by "and" method, union checking, need new
	 * CrmCustomerInfo() include check-params
	 * 
	 * @param queryInfo
	 * @param isExludePKFlag
	 *            , true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<CrmCustomerInfo> getListByEntity(CrmCustomerInfo queryInfo,
			Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<CrmCustomerInfo> beanList = crmcustomerinfoDao
				.getListByEntity(queryInfo);
		return beanList;
	}

	/**
	 * 传递已知参数，通过平台获取CRM客户信息
	 */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getInfoByBean(CrmCustomerInfo bean)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> resMap = new HashMap<String, Object>();

        List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();
        // 设置调用接口信息
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("interface_num", "CRM_OUT_001"));
        nvps.add(new BasicNameValuePair("sys_num", "WMS"));
        nvps.add(new BasicNameValuePair("user_id", "0"));

        // 判断联系电话是否为空
		if (StringUtil.isNotBlank(bean.getContact_number())) {
            nvps.add(new BasicNameValuePair("contact_number", bean.getContact_number()));// 联系电话
		} else {
            nvps.add(new BasicNameValuePair("contact_number", ""));// 联系电话
		}
        // 判断客户编号是否为空
		if (StringUtil.isNotBlank(bean.getCustomer_num())) {
            nvps.add(new BasicNameValuePair("customer_num", bean.getCustomer_num()));// 客户编号
		}
        // 判断客户有效证件是否为空
		if (StringUtil.isNotBlank(bean.getId_card_number())) {
            nvps.add(new BasicNameValuePair("id_card_number", bean.getId_card_number()));// 身份证号码
		} else {
            nvps.add(new BasicNameValuePair("id_card_number", ""));// 身份证号码
		}
        // 判断客户姓名是否为空
        if (StringUtil.isNotBlank(bean.getCustomer_name()))
        {
            nvps.add(new BasicNameValuePair("customer_name", bean.getCustomer_name()));// 客户姓名
        }
        else
        {
            nvps.add(new BasicNameValuePair("customer_name", ""));// 客户姓名
        }
        // 判断业务员id是否为空
        if (bean.getPersonnel_id() != null)
        {
            nvps.add(new BasicNameValuePair("personnel_id", bean.getPersonnel_id().toString()));// 业务员id
        }

        // 判断page是否为空
        if (bean.getPage() != null)
        {
            nvps.add(new BasicNameValuePair("page", bean.getPage().toString()));// 设置page
        }

        // 判断size是否为空
        if (bean.getSize() != null)
        {
            nvps.add(new BasicNameValuePair("size", bean.getSize().toString()));// 设置size
        }

        try
        {
            HttpPost httpPost = new HttpPost(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpPost);

            InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
            BufferedReader rd = new BufferedReader(reader);
            String result_str = rd.readLine();
            JSONObject obj = JSONObject.parseObject(result_str);

            List<Map<String, Object>> crmlist = (List<Map<String, Object>>) obj.get("result");

            // 判断获取的客户信息是否为空
            if (crmlist != null && crmlist.size() > 0)
            {
                for (int i = 0; i < crmlist.size(); i++)
                {
                    WmsInveCustomer wmsInveCustomer = new WmsInveCustomer();
                    Map<String, Object> crmMap = crmlist.get(i);
                    if (crmMap != null && crmMap.size() > 0)
                    {
                        Integer costomerId = (Integer) crmMap.get("costomer_id");
                        wmsInveCustomer.setMobile_phone(((String) crmMap.get("contact_number")) != null && !"".equals(((String) crmMap.get("contact_number"))) ? ((String) crmMap.get("contact_number")).trim():"");// 移动电话
                        wmsInveCustomer.setId_card(((String) crmMap.get("id_card_number")) != null && !"".equals(((String) crmMap.get("id_card_number"))) ? ((String) crmMap.get("id_card_number")).trim():"");// 身份证号码
                        wmsInveCustomer.setCus_name(((String) crmMap.get("customer_name")) != null && !"".equals(((String) crmMap.get("customer_name"))) ? ((String) crmMap.get("customer_name")).trim():"");// 客户姓名
                        wmsInveCustomer.setCus_birthday_str(((String) crmMap.get("customer_birthday")) != null && !"".equals(((String) crmMap.get("customer_birthday"))) ? ((String) crmMap.get("customer_birthday")).trim():"");// 出生日期
                        wmsInveCustomer.setCus_gender(((String) crmMap.get("customer_gender"))!=null && !"".equals(((String) crmMap.get("customer_gender"))) ? ((String) crmMap.get("customer_gender")).trim():"");// 性别0女1男
                        wmsInveCustomer.setCustomer_source("" + crmMap.get("customer_sources"));
                        wmsInveCustomer.setContact_address(((String) crmMap.get("domicile_place")) != null && !"".equals(((String) crmMap.get("domicile_place"))) ? ((String) crmMap.get("domicile_place")).trim():"");
                        wmsInveCustomer.setCostomer_id(costomerId);
                        wmsInveCustomer.setCustomer_email(((String) crmMap.get("email_adress"))!=null && !"".equals(((String) crmMap.get("email_adress"))) ? ((String) crmMap.get("email_adress")).trim():"");// 电邮地址
                        wmsInveCustomer.setCus_address(((String) crmMap.get("present_address"))!=null && !"".equals(((String) crmMap.get("present_address"))) ? ((String) crmMap.get("present_address")).trim():"");// 居住地址
                        wmsInveCustomer.setSalesman_id((Integer) crmMap.get("personnel_id"));// 业务员ID
                        wmsInveCustomer.setCustomer_num((String) crmMap.get("customer_num"));// 客户编号
                        wmsInveCustomer.setCertificate_type((Integer) crmMap.get("certificate_type"));// 证件类型（表示id_card_number是1139（身份证）、1140（军官证）、1141（护照））
                        wmsInveCustomer.setCrm_create_timestamp((String) crmMap.get("create_timestamp"));// 设置crm创建时间

                        // 业务员信息
                        PmPersonnel pmPersonnel = pmPersonnelDao.get((Integer) crmMap.get("personnel_id"));
                        wmsInveCustomer.setSalesman_name(pmPersonnel.getPersonnel_name() + "/" + pmPersonnel.getPersonnel_shortcode());// 业务员姓名/短工号
                        wmsInveCustomer.setSalesman_city(pmPersonnel.getPersonnel_account());// 业务员所在城市
                        wmsInveCustomer.setSalesman_city_code(pmPersonnel.getPersonnel_regionnumber());// 业务员区域编码
                        wmsInveCustomer.setSalesman_dept_id(pmPersonnel.getPersonnel_deptid());// 业务员部门ID


                        resMap = SysTools.convertBean(wmsInveCustomer);

                        String salesmanStatus = "";
                        PmPersonnel pmPersonnel1 = pmPersonnelDao.get(wmsInveCustomer.getSalesman_id());

                        if (pmPersonnel1 != null)
                        {
                            resMap.put("salesman_name", pmPersonnel1.getPersonnel_name() + "/" + pmPersonnel1.getPersonnel_shortcode());
                            if ("7".equals(pmPersonnel1.getPersonnel_state()))
                            {
                                salesmanStatus = "jz";
                            }
                            else if ("0".equals(pmPersonnel1.getPersonnel_state()) || "1".equals(pmPersonnel1.getPersonnel_state())
                                     || "2".equals(pmPersonnel1.getPersonnel_state()))
                            {
                                salesmanStatus = "zz";
                            }
                            else if ("3".equals(pmPersonnel1.getPersonnel_state()) || "4".equals(pmPersonnel1.getPersonnel_state()))
                            {
                                salesmanStatus = "lz";
                            }
                        }
                        
                        Map<String, Object> listRY = null;

                        if (pmPersonnel1.getPersonnel_id() != null && pmPersonnel1.getPersonnel_deptid() != null)
                        {
                            listRY = wmsInveTransaService.findPersonnelInformationByPersonnelId(pmPersonnel1.getPersonnel_id(),
                                                                                                pmPersonnel1.getPersonnel_deptid());
                        }
                        resMap.put("listRY", listRY);
                        resMap.put("salesman_status", salesmanStatus);
                        reslist.add(resMap);
                    }
                }
            }

            resultMap.put("list", reslist);


            if (crmlist != null && crmlist.size() != 0)
            {
                resultMap.put("count", (Integer) obj.get("count"));
            }
            else
            {
                resultMap.put("count", 0);
            }

            response.close();
            httpclient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return resultMap;
	}

	/**
	 * Description :通过查询条件查询相对应的显示信息
	 * 
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getsearchKHlist(CrmCustomerInfoSearchBeanVO queryInfo)
    {
		CrmCustomerInfo beanInfo = new CrmCustomerInfo();

        if (StringUtil.isNotBlank(queryInfo.getId_card()) && !queryInfo.getId_card().equals("undefined"))
        {
			beanInfo.setId_card_number(queryInfo.getId_card());
		}
        if (StringUtil.isNotBlank(queryInfo.getContact_number()) && !queryInfo.getContact_number().equals("undefined"))
        {
			beanInfo.setContact_number(queryInfo.getContact_number());
		}
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()) && !queryInfo.getCustomer_name().equals("undefined"))
        {
            beanInfo.setCustomer_name(queryInfo.getCustomer_name());

            // try
            // {
            // String cus_name = new
            // String(queryInfo.getCustomer_name().getBytes("ISO-8859-1"),
            // "UTF-8");
            // beanInfo.setCustomer_name(cus_name);
            // }
            // catch (Exception e)
            // {
            // e.printStackTrace();
            // }

        }

        // 判断业务员名称或者短工号是否为空
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            PmPersonnel personnel = pmPersonnelDao.getPersonnelByShortCode(queryInfo.getSalesman_name());
            if (personnel != null)
            {
                beanInfo.setPersonnel_id(personnel.getPersonnel_id());
            }
            else
            {
                beanInfo.setPersonnel_id(0);
            }
        }

        // 设置分页信息
        beanInfo.setPage(queryInfo.getPage());
        beanInfo.setSize(queryInfo.getPagesize());
        Map<String, Object> resultMap = getInfoByBean(beanInfo);
        List<Map<String, Object>> list = null;
        if (resultMap != null && resultMap.get("list") != null)
        {
            list = (List<Map<String, Object>>) resultMap.get("list");
        }
		
        int count = 0;
        if (list != null && list.size() > 0)
        {
            count = (int) resultMap.get("count");
        }

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), count, list);
		return bean.getGridData();
	}

	// 根据部门ID和职务编码获取人员信息
	private PmPersonnel getPersonnelInfo(Integer deptId, String code,
			PmPersonnel pmPersonnel) {
		Map<String, Object> paMap = new HashMap<String, Object>();
		paMap.put("post_deptid", deptId);// 部门id
		paMap.put("post_number", PlatformGlobalVar.SYS_PROPERTIES.get(code));// 职务编号
		PmPersonnel pm = new PmPersonnel();
		try {
			pm = pmPersonnelDao.getByPost(paMap).get(0);// 查询经理的人员信息
		} catch (Exception e) {
			pm = pmPersonnel;
		}
		return pm;
	}
	
	/**
	 * 
	 * @param pm_personnel_id 原客户经理Id
	 * @param cus_ids 迁移客户集合（多个用户使用逗号分割）
	 * @return
	 */
	@Override
	public Map<String, Object> getCustomerDataMoa(String pm_personnel_id, String cus_ids){
		return wmsInveTransaService.getCustomerDataMoa(pm_personnel_id, cus_ids);
	}

	/**
	 * 
	 * @param pm_personnel_id 原客户经理Id
	 * @param cus_ids 迁移客户集合（多个用户使用逗号分割）
	 * @param new_pm_personnel_id 新客户经理Id
	 * @param UserBean 登录用户信息
	 * @return
	 */
	@Override
	public Map<String, Object> changeCustomerDataMoa(String pm_personnel_id,
			List<LinkedTreeMap> cusIdList, String new_pm_personnel_id, UserBean user) {
		return wmsInveTransaService.changeCustomerDataMoa(pm_personnel_id, cusIdList, new_pm_personnel_id, user);
	}
	
	/**
	 * 接口：crm端查看客户的存量信息。<br/>
	 * 当传入客户id是多个的时候，返回多个客户的存量信息；当传入客户id是空的时候，返回所有客户存量信息。
	 * @param cus_ids 客户集合，多个客户间用逗号隔开；或者是空；
	 * @return {ret_code: 000,ret_msg: '操作成功',data_list:[{customer_id:'1',stock_amount:'100000'}]}
	 * @author jinzhm
	 */
	@Override
	public Map<String, Object> getCustomerStockMoa(String cus_ids) {
		return wmsInveTransaService.getCustomerStockMoa(cus_ids);
	}

    /**
     * @Title: getBankName
     * @Description: 根据银行卡号获得银行名称
     * @param card_no 银行卡号
     * @return 银行名称
     * @author: jinzhm
     * @throws IOException 
     * @throws ClientProtocolException 
     * @time:2017年2月28日 上午10:05:44
     * @see com.zx.emanage.sysmanage.service.ICrmCustomerInfoService#getBankName(java.lang.String)
     * history:
     * 1、2017年2月28日 jinzhm 创建方法
     */
    @Override
    public Map<String, Object> getBankName(String card_no) throws ClientProtocolException, IOException
    {
        // 返回集合
        Map<String, Object> resMap = new HashMap<String, Object>();
        
        String cardNumber = card_no.substring(0, 6) + card_no.substring(6, card_no.length()).replaceAll("[0-9]", "0");

        // 设置调用接口信息
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("interface_num", "BANKCARD_OUT_001"));
        nvps.add(new BasicNameValuePair("sys_num", "WMS"));
        nvps.add(new BasicNameValuePair("user_id", "0"));
        nvps.add(new BasicNameValuePair("cardNo", cardNumber));

        // 调用接口
        HttpPost httpPost = new HttpPost(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple");
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpPost);
        // 读取返回信息
        InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
        BufferedReader rd = new BufferedReader(reader);
        String result_str = rd.readLine();
        JSONObject obj = JSONObject.parseObject(result_str);

        // 调用成功标志
        boolean validated = (boolean) obj.get("validated");
        // 判断调用是否成功
        if (validated)
        {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("bank_name", obj.get("bankName"));
            resMap.put("ret_data", dataMap);
        }
        return resMap;
    }

}
