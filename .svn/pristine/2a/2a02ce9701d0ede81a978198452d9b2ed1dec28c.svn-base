package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCustomerWxDao;
import com.zx.emanage.inve.persist.WmsInveCustomerWxLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.service.IWmsInveCustomerWxService;
import com.zx.emanage.inve.vo.WmsInveCustomerWxSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCustomerWx;
import com.zx.emanage.util.gen.entity.WmsInveCustomerWxLog;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.platform.syscontext.util.DateUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecustomerwxService")
public class WmsInveCustomerWxServiceImpl implements IWmsInveCustomerWxService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCustomerWxServiceImpl.class);

	@Autowired
	private WmsInveCustomerWxDao wmsinvecustomerwxDao;
	
	@Autowired
	private WmsInveCustomerWxLogDao wmsInveCustomerWxLogDao;

    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCustomerWxSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecustomerwxDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCustomerWxSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecustomerwxDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecustomerwxDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCustomerWx getInfoByPK(Integer wms_inve_customer_wx_id) {
		return wmsinvecustomerwxDao.get(wms_inve_customer_wx_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCustomerWx bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecustomerwxDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCustomerWx bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecustomerwxDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCustomerWx() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCustomerWx> getListByEntity(WmsInveCustomerWx queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCustomerWx> beanList = wmsinvecustomerwxDao.getListByEntity(queryInfo);
		return beanList;
	}

	/**
     * 
     * @Title: verifyCustomerPhoneIsRegistered
     * @Description: 根据手机号验证当前客户是否已经注册过或者是否允许注册
     * @param phone_number 手机号
     * @return 返回map错误信息提示集合
     * @author: DongHao
     * @time:2017年7月20日 上午11:16:25
     * @see com.zx.emanage.inve.service.IWmsInveCustomerWxLogService#verifyCustomerPhoneIsRegistered(java.lang.String)
     * history:
     * 1、2017年7月20日 DongHao 创建方法
     */
    @Override
    public Map<String, Object> verifyCustomerPhoneIsRegistered(String phone_number)
    {
        //定义返回的信息提示集合
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("ret_code", "000");
        resMap.put("ret_msg", "操作成功");
        
        //定义返回的数据
        Map<String, Object> childResMap = new HashMap<String, Object>();
        
        /***************(1)第一步验证当前手机号是否注册过*********************/
        
        //根据当前验证的手机号进行获取用户表中知否存在该客户
        WmsInveCustomerWx wmsInveCustomerWx = wmsinvecustomerwxDao.getWmsInveCustomerWxByPhoneNumber(phone_number);
        
        //判断根据手机号获取的客户信息是否为空,如果为空则说明该手机号没有注册过,需要到wms系统进行验证
        if(wmsInveCustomerWx == null)
        {
            
            /***************(2)第二步验证当前手机号是否购买过不提供纸质债权的产品*********************/
            
            int count = wmsinvecustomerwxDao.getWmsInveTransaByPhoneNumber(phone_number);
            
            //判断根据手机号获取的客户购买的不提供纸质债权的产品的数量大于0说明购买过,否则没有购买过
            if(count > 0)
            {
                //客户信息不等于空时则返回信息
                
                childResMap.put("result", "0");//设置当前手机号标识为注册过
                
                resMap.put("ret_data", childResMap);
            }
            else
            {
                //客户信息不等于空时则返回信息
                
                childResMap.put("result", "-1");//设置当前手机号标识为注册过
                resMap.put("ret_data", childResMap);
            }
            
        }
        else
        {
            //客户信息不等于空时则返回信息
            
            childResMap.put("result", "1");//设置当前手机号标识为注册过
            
            resMap.put("ret_data", childResMap);
        }
        
        return resMap;
    }

    /**
     * 
     * @Title: zshCustomerLogin
     * @Description: 验证客户手机号和密码登录
     * @param phone_number 手机号
     * @param password 登录密码
     * @return 返回map集合信息
     * @author: DongHao
     * @throws Exception 
     * @time:2017年7月20日 下午1:26:11
     * @see com.zx.emanage.inve.service.IWmsInveCustomerWxService#zshCustomerLogin(java.lang.String, java.lang.String)
     * history:
     * 1、2017年7月20日 DongHao 创建方法
     */
    @Override
    @Transactional
    public Map<String, Object> zshCustomerLogin(String phone_number, String password) throws Exception
    {
        //定义返回结果集合map
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("ret_code", "000");
        resMap.put("ret_msg", "操作成功");
        
        //定义验证登录通过返回客户信息
        Map<String, Object> childMap = new HashMap<String, Object>();
        
        //根据当前验证的手机号进行获取用户表中知否存在该客户
        WmsInveCustomerWx wmsInveCustomerWx = wmsinvecustomerwxDao.getWmsInveCustomerWxByPhoneNumber(phone_number);
        
        //判断根据手机号获取的客户信息是否为空
        if(wmsInveCustomerWx != null)
        {
            //判断密码不相同
            if(!password.equals(wmsInveCustomerWx.getPassword()))
            {
                /*********密码不正确*********/
                //设置密码不正确
                childMap.put("result", "0");
                resMap.put("ret_data", childMap);
            }
            else
            {
                /*********登录成功*********/
                
                //定义需要返回给zsh的用户信息
                Map<String, Object> userMap = new HashMap<String, Object>();
                
                //创建记录登录的日志对象
                WmsInveCustomerWxLog log = new WmsInveCustomerWxLog();

                //登录时间
                String loginTime = DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
                
                //登录成功需要添加登录日志wmsInveCustomerWxLogDao
                String logTxt = "客户"+ wmsInveCustomerWx.getCus_name() + "使用手机号" + wmsInveCustomerWx.getPhone_number() + "在" + loginTime + "登录";
                log.setLog_text(logTxt);
                log.setLog_time(new Timestamp(DateUtil.string2Date(loginTime, "yyyy-MM-dd HH:mm:ss").getTime()));
                
                //保存客户登录日志
                wmsInveCustomerWxLogDao.save(log);
                
                //设置密码不正确
                childMap.put("result", "1");
                //登录成功之后返回客户信息
                userMap.put("wms_inve_customer_wx_id", wmsInveCustomerWx.getWms_inve_customer_wx_id());
                userMap.put("phone_number", wmsInveCustomerWx.getPhone_number());
                userMap.put("password", wmsInveCustomerWx.getPassword());
                userMap.put("cus_name", wmsInveCustomerWx.getCus_name());
                
                childMap.put("user", userMap);
                resMap.put("ret_data", childMap);
            }
            
        }
        else
        {
            /*********手机号不存在*********/
            //设置当前手机号不存在
            childMap.put("result", "0");
            resMap.put("ret_data", childMap);
        }
        
        return resMap;
    }
    

    /**
     * @Title: setPassword
     * @Description: 设置密码
     * @param mobile_phone 手机号
     * @param password 密码
     * @return 设置成功返回用户账户信息，失败返回失败标志
     * @author: jinzhm
     * @time:2017年7月21日 上午8:24:35
     * @see com.zx.emanage.inve.service.IWmsInveCustomerWxService#setPassword(java.lang.String, java.lang.String)
     * history:
     * 1、2017年7月21日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> setPassword(String mobile_phone, String password)
    {
        // 返回数据
        Map<String, Object> resMap = new HashMap<String, Object>();

        WmsInveCustomerWx wxCustomer = new WmsInveCustomerWx();
        wxCustomer.setPhone_number(mobile_phone);

        List<WmsInveCustomerWx> customerList = wmsinvecustomerwxDao.getListByEntity(wxCustomer);

        // 如果库中没有该手机号的客户账户信息，就新建
        if (customerList.isEmpty())
        {
            wxCustomer.setPassword(password);
            List<WmsInveTransa> transaList = wmsInveTransaDao.getWithoutPaperProtocolTransaInfoList(mobile_phone);
            wxCustomer.setCus_name(transaList.get(0).getCus_name());
            wxCustomer.setCostomer_id(transaList.get(0).getCostomer_id());
            wxCustomer.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsinvecustomerwxDao.save(wxCustomer);
        }
        // 如果库中有该手机号的客户账户信息，就修改密码
        else
        {
            // 获得对应客户账户信息
            wxCustomer = customerList.get(0);
            // 封装修改数据
            WmsInveCustomerWx updWXCustomer = new WmsInveCustomerWx();
            updWXCustomer.setWms_inve_customer_wx_id(wxCustomer.getWms_inve_customer_wx_id());
            updWXCustomer.setPassword(password);
            updWXCustomer.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            // 修改客户账户信息
            wmsinvecustomerwxDao.update(updWXCustomer);
        }

        // 封装登录客户信息
        resMap.put("wms_inve_customer_wx_id", wxCustomer.getWms_inve_customer_wx_id());
        resMap.put("cus_name", wxCustomer.getCus_name());
        resMap.put("phone_number", wxCustomer.getPhone_number());

        return resMap;
    }
}
