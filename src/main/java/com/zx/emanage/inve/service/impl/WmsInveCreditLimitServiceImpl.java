package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCreditLimitDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.service.IWmsInveCreditLimitService;
import com.zx.emanage.inve.vo.WmsInveCreditLimitSearchBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveCreditLimit;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecreditlimitService")
public class WmsInveCreditLimitServiceImpl implements IWmsInveCreditLimitService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCreditLimitServiceImpl.class);

	@Autowired
	private WmsInveCreditLimitDao wmsinvecreditlimitDao;
	
	@Autowired
	private PmPersonnelDao pmPersonnelDao;//查询人员信息对象
	
	@Autowired
	private WmsInveTransaDao wmsInveTransaDao;//上单表查询dao

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCreditLimitSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecreditlimitDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCreditLimitSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecreditlimitDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecreditlimitDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCreditLimit getInfoByPK(Integer wms_inve_credit_limit) {
		return wmsinvecreditlimitDao.get(wms_inve_credit_limit);
	}

	@Override	
	@Transactional
	public String save(WmsInveCreditLimit bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditlimitDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCreditLimit bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditlimitDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCreditLimit() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCreditLimit> getListByEntity(WmsInveCreditLimit queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCreditLimit> beanList = wmsinvecreditlimitDao.getListByEntity(queryInfo);
		return beanList;
	}

    /**
     * @Title: getLocationRegionCreditMinAccount
     * @Description: 根据当前登录人所在区域获取债权限制金额
     * @param user 当前登录人信息对象
     * @return 返回当前登录人所在区域的限制债权金额
     * @author: DongHao
     * @time:2017年4月7日 上午11:04:54
     * @see com.zx.emanage.inve.service.IWmsInveCreditLimitService#getLocationRegionCreditMinAccount(com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年4月7日 DongHao 创建方法
    */
    @Override
    public BigDecimal getLocationRegionCreditMinAccount(UserBean user)
    {
        //定义返回的债权限制金额变量
        BigDecimal res_account = BigDecimal.ZERO;
        
        //根据当前登录人的id进行获取当前登录人所在区域的债权限制金额
        Map<String, Object> res_map = wmsinvecreditlimitDao.getLocationRegionCreditMinAccountByUserRegion(user.getUserId());
        
        //判断获取的债权限制金额返回的map集合是否为空
        if(res_map != null && res_map.size() > 0)
        {
            //判断返回的债权限制金额是否为空,为空时赋值为0,否则赋值得到的限制金额
            res_account = ((BigDecimal) res_map.get("limit_amount")).abs();
        }
        return res_account;
    }

    /**
     * @Title: verifyCreditLimitAccountAvailable
     * @Description: 判断当前业务员所在区域的债权限制金额与投资金额进行比较
     * @param user当前登录业务员信息对象
     * @param product_account 投资金额
     * @param actDateOfPayment 实际支付日期
     * @param wms_inve_transa_id 上单表主键
     * @param origCategoryId 老产品id
     * @param newCategoryId 新产品id
     * @return 如果投资金额小于债权限制金额返回true,否则返回false
     * @author: DongHao
     * @time:2017年4月7日 上午11:26:36
     * @see com.zx.emanage.inve.service.IWmsInveCreditLimitService#verifyCreditLimitAccountAvailable(com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年4月7日 DongHao 创建方法
    */
    @Override
    public boolean verifyCreditLimitAccountAvailable(UserBean user, BigDecimal product_account,Date actDateOfPayment,Integer wms_inve_transa_id, 
                                                                                                                     Integer origCategoryId, 
                                                                                                                     Integer newCategoryId)
    {
        
        //定义返回结果的布尔类型的变量(投资金额小于债权限制金额返回true,债权限制金额为0时返回true,否则返回false)
        boolean bool = true;
        
        //验证实际支付日期是否在当前系统时间之前,(之前返回false,等于当前系统时间返回true)
        boolean dateBefore = verifyActDateOfPaymentToNowDateBefore(actDateOfPayment);
        
        //判断如果实际支付日期等于当前系统时间
        if(dateBefore)
        {
            
            Integer wms_inve_product_category_id = null;
            
            if(origCategoryId != null)
            {
                if(newCategoryId == null)
                {
                    wms_inve_product_category_id = origCategoryId;
                }
                else
                {
                    //判断产品是否更换
                    if(origCategoryId.compareTo(newCategoryId) == 0)
                    {
                        wms_inve_product_category_id = origCategoryId;
                    }
                    else
                    {
                        wms_inve_product_category_id = newCategoryId;
                    }
                }
            }
            
            
            //判断产品是否为可拆分类产品
            Map<String, Object> categoryMap = wmsInveTransaDao.getCategoryType(wms_inve_product_category_id);
            
            //判断返回的产品类型是否为空
            if(categoryMap != null && categoryMap.size() > 0)
            {
                
                if(categoryMap.get("category_type") != null)
                {
                    
                    Integer category_type = (Integer) categoryMap.get("category_type");
                    
                    // 判断是否时可拆分类产品
                    if(category_type.compareTo(4) == 0)
                    {
                      //根据单据主键获取对应的单据信息
                        WmsInveTransa wmsInveTransa = wmsInveTransaDao.get(wms_inve_transa_id);
                        
                        //判断对应的单据是否时线上合同,线上合同则进行债权的限制逻辑
                        if(wmsInveTransa != null && wmsInveTransa.getContract_signing_type().equals("2"))
                        {
                            //获取债权限制金额
                            BigDecimal limit_account = getLocationRegionCreditMinAccount(user);
                            
                            //判断债权限制金额是否不等于0
                            if(limit_account.compareTo(BigDecimal.ZERO) != 0)
                            {
                                //判断投资金额不小于债权限制金额
                                if(product_account.compareTo(limit_account) != -1)
                                {
                                    bool = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return bool;
    }

    /**
     * @Title: saveCreditLimit
     * @Description: 添加债权不足的限制
     * @param user 当前登录的用户信息
     * @param product_account 投资金额
     * @author: DongHao
     * @time:2017年4月7日 上午11:35:13
     * @see com.zx.emanage.inve.service.IWmsInveCreditLimitService#saveCreditLimit(com.zx.sframe.util.vo.UserBean, java.math.BigDecimal)
     * history:
     * 1、2017年4月7日 DongHao 创建方法
    */
    @Override
    @Transactional
    public Map<String, Object> saveCreditLimit(UserBean user, BigDecimal product_account)
    {
        //定义返回结果信息集合
        Map<String, Object> res_map = new HashMap<String, Object>();
        
        //根据当前人员信息id获取对应的人员信息
        PmPersonnel pmPersonnel = pmPersonnelDao.get(user.getUserId());
        
        //创建债权限制表信息对象
        WmsInveCreditLimit creditLimit = new WmsInveCreditLimit();
        
        creditLimit.setRegion_number(pmPersonnel != null ? pmPersonnel.getPersonnel_regionnumber() : "");//设置区域信息
        creditLimit.setLimit_amount(product_account);//设置限制金额
        creditLimit.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));//设置创建时间
        creditLimit.setCreate_user_id(user.getUserId());//设置创建人
        creditLimit.setEnable_flag("1");//设置是否启用
        
        wmsinvecreditlimitDao.save(creditLimit);//保存债权限制信息
        
        res_map.put("error", "success");
        
        return res_map;
    }
    
    /**
     * 
     * @Title: verifyActDateOfPaymentToNowDateBefore
     * @Description: 判断实际支付日期是否在当前系统时间之前(之前返回false,等于当前系统时间返回true)
     * @param actDateOfPayment 实际支付日期
     * @return 实际支付日期在当前系统时间之前返回false,等于当前系统时间返回true
     * @author: DongHao
     * @time:2017年4月12日 下午10:04:28
     * history:
     * 1、2017年4月12日 DongHao 创建方法
     */
    public boolean verifyActDateOfPaymentToNowDateBefore(Date actDateOfPayment)
    {
        
        //定义返回布尔类型的结果
        boolean res_bool = true;
        
        //定义时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        try
        {
            //定义实际支付日期变量
            Date actPaymentDate = null;
            
            //判断实际支付日期不为空
            if(actDateOfPayment != null)
            {
                //将字符串类型的实际支付日期转成date类型的时间
                actPaymentDate = format.parse(format.format(actDateOfPayment)); 
            }
            
            //定义当前系统时间
            Date nowDate = format.parse(format.format(new Date()));
            
            //判断实际支付日期在当前系统时间之前
            if(actPaymentDate.compareTo(nowDate) < 0)
            {
                //实际支付日期在当前系统时间之前则设置为false
                res_bool = false;
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
        return res_bool;
    }
}
