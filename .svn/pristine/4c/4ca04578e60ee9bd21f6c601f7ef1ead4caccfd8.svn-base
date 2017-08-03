package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jodd.typeconverter.Convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.number.NumberFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveTransaCardDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.service.IWmsInveTransaCardService;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransacardService")
public class WmsInveTransaCardServiceImpl implements IWmsInveTransaCardService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaCardServiceImpl.class);

    @Autowired
    private WmsInveTransaCardDao wmsinvetransacardDao;

    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;
    
    @Autowired
    private SysSpecialUserDao specialUserDao;
    
    /**
     * @Title:getListWithoutPaging
     * Description :【财务管理>理财上单>金额到账】 列表显示
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月17日 15:16
     */
    @Override
    public Map<String, Object> getListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo,UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //客户姓名 模糊
        if(StringUtil.isNotBlank(queryInfo.getCus_name())){
        	paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        //身份证号 模糊
        if(StringUtil.isNotBlank(queryInfo.getId_card())){
        	paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        //理财产品 精确
        if(StringUtil.isNotBlank(queryInfo.getCategory_name())&&!"-1".equals(queryInfo.getCategory_name())){
        	paramMap.put("category_name", queryInfo.getCategory_name());
        }
        //金额状态 精确
        if(StringUtil.isNotBlank(queryInfo.getAccount_status())&& !"-1".equals(queryInfo.getAccount_status())){
        	paramMap.put("account_status", queryInfo.getAccount_status());
        }
        //上单时间 精确
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())){
        	paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())){
        	paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        //如果没传查询条件，自动带条件查询   
        //焦德龙 2016年1月30日  
        if(StringUtil.isBlank(queryInfo.getCus_name()) 
        		&& StringUtil.isBlank(queryInfo.getId_card()) 
        			&& StringUtil.isBlank(queryInfo.getCategory_name())
//        				&& "-1".equals(queryInfo.getCategory_name())
        					&& StringUtil.isBlank(queryInfo.getAccount_status())
//        						&& "-1".equals(queryInfo.getAccount_status())
        							&& StringUtil.isBlank(queryInfo.getCreate_timestamp_begin())
        								&& StringUtil.isBlank(queryInfo.getCreate_timestamp_end())
        									)
        {
        	paramMap.put("listshowstatus", "已到账");
        }
        
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransacardDao.searchForJEDZ(paramMap);
        list = setPayName(list);
        SysSpecialUser specilaUsers = new SysSpecialUser();
        specilaUsers.setEnable_flag("1");
        List<SysSpecialUser>sysSpecialUsers =specialUserDao.getListByEntity(specilaUsers);
        list=SysTools.setListView(list, user, sysSpecialUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransacardDao.findCountForJEDZ(paramMap),
                                                                                       list);
        return bean.getGridData();
    }
    
    /**
     * @Title:getListWithPaging
     * Description :【财务管理】【理财上单】【金额到账】 列表导出
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月17日 15:16
     */
    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //客户姓名 模糊
        if(StringUtil.isNotBlank(queryInfo.getCus_name())){
        	paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        //身份证号 模糊
        if(StringUtil.isNotBlank(queryInfo.getId_card())){
        	paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        //理财产品 精确
        if(StringUtil.isNotBlank(queryInfo.getCategory_name())&&!"-1".equals(queryInfo.getCategory_name())){
        	paramMap.put("category_name", queryInfo.getCategory_name());
        }
        //金额状态 精确
        if(StringUtil.isNotBlank(queryInfo.getAccount_status())&& !"-1".equals(queryInfo.getAccount_status())){
        	paramMap.put("account_status", queryInfo.getAccount_status());
        }
        //上单时间 精确
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())){
        	paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())){
        	paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransacardDao.searchForJEDZ(paramMap);
        list = setPayName(list);
        SysSpecialUser specilaUsers = new SysSpecialUser();
        specilaUsers.setEnable_flag("1");
        List<SysSpecialUser>sysSpecialUsers =specialUserDao.getListByEntity(specilaUsers);
        list=SysTools.setListView(list, user, sysSpecialUsers);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }
    
    @Override
    public Map<String, Object> getListWithoutPagingForJEDZ(WmsInveTransaCardSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(StringUtil.isNotBlank(queryInfo.getWms_inve_transa_id())){
        	paramMap.put("wms_inve_transa_id", queryInfo.getWms_inve_transa_id());        	
        }
        if(StringUtil.isNotBlank(queryInfo.getPay_type())){
        	paramMap.put("pay_type", queryInfo.getPay_type());        	
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransacardDao.searchByWms_inve_transa_id(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public WmsInveTransaCard getInfoByPK(Integer wms_inve_transa_card_id)
    {
        return wmsinvetransacardDao.get(wms_inve_transa_card_id);
    }

    @Override
    @Transactional
    public String save(WmsInveTransaCard bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransacardDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveTransaCard bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransacardDao.update(bean); // update a record replace
                                                 // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String updateForJEDZ(UserBean user, String wms_inve_transa_id, String itcardJSON, String tzje,
                                String payTypeStr)
    {
        String resStr = "success";
        // 获取当前系统时间
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); 
        Date sysDate = new Date(System.currentTimeMillis());
        //获取支付卡信息
        List<WmsInveTransaCard> wmsInveTransaCardList = JsonUtil.jsonArrayToList(itcardJSON, WmsInveTransaCard.class);
        int ydz = 0;// 已到账数量
        BigDecimal dzjehj = new BigDecimal(0);// 到账金额合计
        try
        {
            for (WmsInveTransaCard witc : wmsInveTransaCardList)
            {
                witc.setLast_update_timestamp(sysTime);
                witc.setLast_update_user_id(user.getUserId());
                if ("1".equals(witc.getPay_type()) || "2".equals(witc.getPay_type()) || "3".equals(witc.getPay_type()))
                {
                	if (witc.getIs_finish().equals("1"))
                    {
                        ydz += 1;
                        dzjehj = dzjehj.add(witc.getPay_account());
                        witc.setAct_account(witc.getPay_account());
                        wmsinvetransacardDao.updateForJEDZ(witc);
                    }
                }
            }
            if (ydz == 0)
            {
                WmsInveTransa wit = new WmsInveTransa();
                wit.setWms_inve_transa_id(Integer.parseInt(wms_inve_transa_id));
                wit.setAccount_status("2");
                wit.setLast_update_timestamp(sysTime);
                wit.setLast_update_user_id(user.getUserId());
                wmsInveTransaDao.updateInve_transaForJEZF(wit);
            }
            else if (ydz == wmsInveTransaCardList.size())
            {
                WmsInveTransa wit = new WmsInveTransa();
                wit.setWms_inve_transa_id(Integer.parseInt(wms_inve_transa_id));
                wit.setAccount_status("3");
                wit.setAct_account_usr_id(user.getUserId());
                wit.setDate_of_act(sysDate);
                wit.setAct_account(dzjehj);
                BigDecimal tzjeBd = new BigDecimal(tzje);
                wit.setFee_account(tzjeBd.subtract(dzjehj));
                wit.setLast_update_timestamp(sysTime);
                wit.setLast_update_user_id(user.getUserId());
                wmsInveTransaDao.updateInve_transaForJEZF(wit);
            }
            else
            {
                WmsInveTransa wit = new WmsInveTransa();
                wit.setWms_inve_transa_id(Integer.parseInt(wms_inve_transa_id));
                wit.setAccount_status("4");
                wit.setLast_update_timestamp(sysTime);
                wit.setLast_update_user_id(user.getUserId());
                wmsInveTransaDao.updateInve_transaForJEZF(wit);
            }
        }
        catch (Exception e)
        {
            resStr = "error";
        }

        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveTransaCard() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveTransaCard> getListByEntity(WmsInveTransaCard queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveTransaCard> beanList = wmsinvetransacardDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public Map<String, Object> getInfoByFK(Integer wms_inve_transa_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = wmsinvetransacardDao.getWmsInveTransaCard(wms_inve_transa_id);
        paramMap.put("Rows", list);
        return paramMap;
    }

	@Override
	public Map<String, Object> findZfInfoByWmsInveTransaId(Integer wms_inve_transa_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = wmsinvetransacardDao.getWmsInveTransaCardByWmsInveTransaId(wms_inve_transa_id);
		resultMap.put("Rows", list);
		return resultMap;
	}
	
	/**
	 * 1.该方法主要是通过NumberFormat将列表中的pay_type_binary格式化成二进制的形式(如:111)
	 *   二进制形式的数据从左到右依次表示为 现金--银行卡--续单本金,如果二进制形式的数据表示为000则为单据续期
	 * 2.将格式化完的二进制数据转换成char[]数组。
	 * 3.对char[]数组中的每一位数据进行判断,判断的顺序遵循上面的二进制数据的规则
	 *   从左到右依次判断每一位数据是否==1如果==1就设置成对应的文字信息,如果从左到右依次都为0的情况,就表示是单据续期
	 * @param list
	 */
	public List<Map<String, Object>> setPayName(List<Map<String, Object>> list){
		NumberFormat nf = new DecimalFormat("000");
        for(Map<String, Object> map : list){
        	String values = nf.format(Integer.parseInt(""+map.get("pay_type_binary")));
        	String payTypeName = "";
        	char[] payTypeValues = values.toCharArray();
        	if(String.valueOf(payTypeValues[0]).equals("1")){
        		payTypeName+= "现金" + "+";
    		}
    		if(String.valueOf(payTypeValues[1]).equals("1")){
    			payTypeName+= "银行卡" + "+";
    		}
    		if(String.valueOf(payTypeValues[2]).equals("1")){
    			payTypeName+= "续单本金" + "+";
    		}
    		if(String.valueOf(payTypeValues[0]).equals("0") && String.valueOf(payTypeValues[1]).equals("0") && String.valueOf(payTypeValues[2]).equals("0")){
    			payTypeName+= "单据续期" + "+";
    		}
    		if(!"".equals(payTypeName)){
    			payTypeName = payTypeName.substring(0, payTypeName.length() - 1);
    		}
    		map.put("pay_type_name", payTypeName);
        }
        return list;
	}
}
