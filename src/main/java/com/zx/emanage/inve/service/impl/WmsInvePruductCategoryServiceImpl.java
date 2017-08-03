package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePosDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryLogDao;
import com.zx.emanage.inve.persist.WmsInvePruductDeadlineDao;
import com.zx.emanage.inve.persist.WmsInvePruductDeadlineRewardDao;
import com.zx.emanage.inve.persist.WmsInvePruductRebateWayDao;
import com.zx.emanage.inve.persist.WmsInvePruductReturnDao;
import com.zx.emanage.inve.persist.WmsInvePruductYearPaySpecialDao;
import com.zx.emanage.inve.persist.WmsInveTransaPruductUserDao;
import com.zx.emanage.inve.service.IWmsInvePruductCategoryService;
import com.zx.emanage.inve.vo.WmsInvePruductCategorySearchBeanVO;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInvePos;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategoryLog;
import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.util.gen.entity.WmsInvePruductReturn;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvepruductcategoryService")
public class WmsInvePruductCategoryServiceImpl implements IWmsInvePruductCategoryService
{
    private static Logger log = LoggerFactory.getLogger(WmsInvePruductCategoryServiceImpl.class);

    @Autowired
    private WmsInvePruductCategoryDao wmsinvepruductcategoryDao;// 理财产品表

    @Autowired
    private WmsInvePruductDeadlineDao wmsInvePruductDeadlineDao;// 期限限制表

    @Autowired
    private WmsInvePruductReturnDao wmsInvePruductReturnDao;// 赎回限制表

    @Autowired
    private WmsInvePruductDeadlineRewardDao wmsInvePruductDeadlineRewardDao;// 奖励利率表

    @Autowired
    private WmsInvePosDao wmsInvePosDao;
    @Autowired
    private WmsInvePruductRebateWayDao wmsInvePruductRebateWayDao;
    @Autowired
    private WmsInvePruductYearPaySpecialDao  wmsInvePruductYearPaySpecialDao;
    @Autowired
    private WmsInvePruductCategoryLogDao  wmsInvePruductCategoryLogDao;//产品日志表
    @Autowired
    private WmsInveTransaPruductUserDao wmsInveTransaPruductUserDao;//上单产品限制表
    
    @Override
    public Map<String, Object> getListWithoutPaging(WmsInvePruductCategorySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvepruductcategoryDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public List<Map<String, Object>> getAllListWithoutPaging(UserBean user,String cktype, String category_name, Integer wms_inve_pruduct_category_id, String platform_user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        List<Map<String, Object>> products = new LinkedList<Map<String, Object>>();
        if ("ck".equals(cktype))
        {
            paramMap.put("cktype", "ck");
        }else{
      	 //如果是查看 不执行此方法   根据上单柜员的id  判断当前柜员可以上单的产品列表
       	 List<Integer> idlist=wmsInveTransaPruductUserDao.getidList(user.getUserId());//根据当前登陆人获取当前人的上单产品
            if(idlist != null&&idlist.size()>0) {
            	paramMap.put("idList", idlist);
            }else if(wms_inve_pruduct_category_id!=null){
            	idlist.add(wms_inve_pruduct_category_id);
            	paramMap.put("idList", idlist);
            }else{
            	return products;
            }
        }
        if(StringUtil.isNotBlank(category_name)) {
	 		paramMap.put("category_name", SysTools.getSqlLikeParam(category_name));
	 	}
        if(StringUtil.isNotBlank(platform_user)){
        	paramMap.put("platform_user", SysTools.getSqlLikeParam(platform_user));
        }
        if(wms_inve_pruduct_category_id != null) {
        	paramMap.put("wms_inve_pruduct_category_id", wms_inve_pruduct_category_id);
        }
       
        paramMap.put("sortname", "category_sales_statistics");
        paramMap.put("sortorder", "desc");
        //根据条件查询数据库获取可访问的产品列表
        products = wmsinvepruductcategoryDao.searchAll(paramMap);
        for (Map<String, Object> product : products)
        {
        	Integer category_id = (Integer) product.get("wms_inve_pruduct_category_id");
        	//年化利率
            WmsInvePruductYearPaySpecial nft = wmsInvePruductYearPaySpecialDao.get(category_id);
            product.put("nft", nft);
            
            paramMap = new HashMap<>();
            paramMap.put("wms_inve_pruduct_category_id", category_id);
            paramMap.put("sortname", "full_month");
            paramMap.put("sortorder", "asc");
            //奖励利率
            List<Map<String, Object>> jllls = wmsInvePruductRebateWayDao.search(paramMap);
            product.put("jllls", jllls);
        }
        return products;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInvePruductCategorySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        if (queryInfo.getCategory_name() != null && !"".equals(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", SysTools.getSqlLikeParam(queryInfo.getCategory_name()));
        }
        if (queryInfo.getIs_forbidden() != null && !"".equals(queryInfo.getIs_forbidden()))
        {
            paramMap.put("is_forbidden", queryInfo.getIs_forbidden());
        }
        List<Map<String, Object>> list = wmsinvepruductcategoryDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvepruductcategoryDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }
    /**
     * Description :理财产品审核确认列表
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @Override
    public Map<String, Object> getListWithPagingAuditing(WmsInvePruductCategorySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        if (queryInfo.getCategory_name() != null && !"".equals(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", SysTools.getSqlLikeParam(queryInfo.getCategory_name()));
        }
        if (queryInfo.getIs_forbidden() != null && !"".equals(queryInfo.getIs_forbidden()))
        {
            paramMap.put("is_forbidden", queryInfo.getIs_forbidden());
        }
        List<Map<String, Object>> list = wmsinvepruductcategoryDao.searchAuditing(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvepruductcategoryDao.findCountAuditing(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 理财产品初始化数据 baisong
     */
    @Override
    public WmsInvePruductCategory getInfoByPK(Integer wms_inve_pruduct_category_id)
    {
        return wmsinvepruductcategoryDao.get(wms_inve_pruduct_category_id);
    }

    @Override
    @Transactional
    public String save(WmsInvePruductCategory bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvepruductcategoryDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInvePruductCategory bean, UserBean user,String user_ip)
    {
        String resStr = "success";
        int ret = 0;
        SimpleDateFormat formatdater = new SimpleDateFormat("yyyy-MM-dd");
        Date date = DateUtil.strDate(formatdater.format(new Date()), "yyyy-MM-dd");
        if (bean.getCategory_over_time().before(date))
        {
           return "dateerror";//用户输入时间不正确
        }else if(bean.getCategory_over_time().getTime()==(date.getTime())){
        	bean.setIs_forbidden("1");//0：启用   1：禁用    2:待审批   3:未通过
        }
        ret = wmsinvepruductcategoryDao.update(bean); // update a record replace
        if (ret == 0)
        {
            resStr = "error";
        }
	    WmsInvePruductCategoryLog categoryLog =new WmsInvePruductCategoryLog();
		categoryLog.setWms_inve_pruduct_category_id(bean.getWms_inve_pruduct_category_id());
		categoryLog.setOperation_type(2);//1：产品确认   2：产品禁用    3：产品启用
		categoryLog.setDisable_time(bean.getCategory_over_time());
		categoryLog.setCategory_code(bean.getCategory_briefing());
		categoryLog.setOperator_user_id(user.getUserId());
		categoryLog.setOperator_name(user.getRealname());
		categoryLog.setOperator_datetime(new Timestamp(System.currentTimeMillis()));
		categoryLog.setOperator_ip(user_ip);
		categoryLog.setEnable_flag("1");
		ret =wmsInvePruductCategoryLogDao.save(categoryLog);//日志
		if (ret == 0)
	    {
	        resStr = "error";
	    }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInvePruductCategory() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInvePruductCategory> getListByEntity(WmsInvePruductCategory queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInvePruductCategory> beanList = wmsinvepruductcategoryDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public List<WmsInvePruductCategory> getAllInvePruductCategory()
    {
        List<com.zx.emanage.util.gen.entity.WmsInvePruductCategory> list = wmsinvepruductcategoryDao.getAllInvePruductCategory();
        com.zx.emanage.util.gen.entity.WmsInvePruductCategory entity = new com.zx.emanage.util.gen.entity.WmsInvePruductCategory();
        entity.setWms_inve_pruduct_category_id(-1);
        entity.setCategory_name("请选择");
        list.add(0, entity);
        return list;
    }

    @Override
    public List<WmsInvePos> getAllInvePos()
    {
        List<com.zx.emanage.util.gen.entity.WmsInvePos> list = wmsInvePosDao.getAllInvePosInfo();
        com.zx.emanage.util.gen.entity.WmsInvePos entity = new com.zx.emanage.util.gen.entity.WmsInvePos();
        entity.setWms_inve_pos_id(-1);
        entity.setPos_code("请选择");
        list.add(0, entity);
        return list;
    }
    /**
     * Description :理财产品保存 --产品限制表-产品期限表-产品奖励利率表--产品基本信息
     * 
     * @param WmsInvePruductCategory UserBean WmsInvePruductCategorySearchBeanVO WmsInvePruductYearPaySpecial
     * @return record list
     * @author baisong
     */
    @Override
    @Transactional
    public String saveall(WmsInvePruductCategory bean, UserBean user,WmsInvePruductCategorySearchBeanVO BeanVO,WmsInvePruductYearPaySpecial paySpecial)
    {
        String resStr = "success";
        int ret = 0;
        /*List<WmsInvePruductCategory> listByName = wmsinvepruductcategoryDao.getAllInvePruductCategoryByName(bean);
        if (listByName.size() > 0)
        {
            resStr = "exist";
            return resStr;
        }*/
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setEnable_flag("1");
        bean.setCreate_timestamp(new Timestamp(new Date().getTime()));
        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        bean.setIs_forbidden("2");//0：启用   1：禁用    2:待审批   3:未通过
        bean.setValid_begin_date(new java.sql.Date(new Date().getTime()));//启用时间
        bean.setCategory_sales_statistics(0);//热销产品统计(每月更新)
        ret = wmsinvepruductcategoryDao.save(bean);
        if (ret== 0)
        {
            resStr = "error";
            return resStr;
        }

        List<WmsInvePruductRebateWay> listgridBasicVal = JsonUtil.jsonArrayToList(BeanVO.getGridBasicVal(),WmsInvePruductRebateWay.class);
        for (int i = 0; i < listgridBasicVal.size(); i++)
        {
        	WmsInvePruductRebateWay gridBasic = listgridBasicVal.get(i);
            gridBasic.setCreate_user_id(user.getUserId());
            gridBasic.setEnable_flag("1");
            gridBasic.setCreate_user_id(user.getUserId());
            gridBasic.setCreate_datetime(new Timestamp(new Date().getTime()));
            gridBasic.setWms_inve_pruduct_category_id(bean.getWms_inve_pruduct_category_id());
            gridBasic.setWms_inve_pruduct_rebate_way_id(null);
            wmsInvePruductRebateWayDao.save(gridBasic);
         
        }
        // wms_inve_pruduct_return
        List<WmsInvePruductReturn> listgridLimitVal = JsonUtil.jsonArrayToList(BeanVO.getGridLimitVal(), WmsInvePruductReturn.class);
        for (int i = 0; i < listgridLimitVal.size(); i++)
        {
            WmsInvePruductReturn gridLimit = listgridLimitVal.get(i);
            gridLimit.setCreate_user_id(user.getUserId());
            gridLimit.setCreate_timestamp(new Timestamp(new Date().getTime()));
            gridLimit.setLast_update_timestamp(new Timestamp(new Date().getTime()));
            gridLimit.setWms_inve_pruduct_category_id(bean.getWms_inve_pruduct_category_id());
            gridLimit.setWms_inve_pruduct_return_id(null);
            wmsInvePruductReturnDao.save(gridLimit);
        }
        if(paySpecial!=null&&(paySpecial.getFirst_year_interest_rate()!=null||paySpecial.getSecond_year_interest_rate()!=null)){//判断理财——产品利率设置表-年付特 是否为空
        	   paySpecial.setWms_inve_pruduct_category_id(bean.getWms_inve_pruduct_category_id());
        	   paySpecial.setCreate_user_id(user.getUserId());
        	   paySpecial.setEnable_flag("1");
        	   paySpecial.setCreate_user_id(user.getUserId());
        	   paySpecial.setCreate_datetime(new Timestamp(new Date().getTime()));
        	   paySpecial.setWms_inve_pruduct_category_id(bean.getWms_inve_pruduct_category_id());
        	   paySpecial.setWms_inve_pruduct_year_pay_special_id(null);
        	   wmsInvePruductYearPaySpecialDao.save(paySpecial);
        }
     
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }
    /**
     * Description :理财产品保存 --产品限制表-产品期限表-产品奖励利率表--产品基本信息
     * 
     * @param WmsInvePruductCategory UserBean WmsInvePruductCategorySearchBeanVO WmsInvePruductYearPaySpecial
     * @return record list
     * @author baisong
     */
    @Override
    @Transactional
    public String updateall(WmsInvePruductCategory bean, UserBean user,WmsInvePruductCategorySearchBeanVO BeanVO,WmsInvePruductYearPaySpecial paySpecial)
    {
        String resStr = "success";
        int ret = 0;
        if(bean==null||bean.getWms_inve_pruduct_category_id()==null){//如果主表为空或者产品主键为空则错误
        	return "error";
        }
        /*List<WmsInvePruductCategory> listByName = wmsinvepruductcategoryDao.getAllInvePruductCategoryByName(bean);
        if (listByName.size() > 0)
        {
            resStr = "exist";
            return resStr;
        }*/
        //数据更新先删除后保存原则
        if(BeanVO!=null&&"updateall".equals(BeanVO.getUpdateVal())){//表示全部更新
        	wmsInvePruductRebateWayDao.delete(bean.getWms_inve_pruduct_category_id());//删除数据--理财——产品利率返利方式设置表
        	wmsInvePruductReturnDao.delete(bean.getWms_inve_pruduct_category_id());//删除数据--理财——产品赎回限制表
        	wmsInvePruductYearPaySpecialDao.delete(bean.getWms_inve_pruduct_category_id());//删除数据-理财——产品利率设置表-年付特
        }
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        bean.setEnable_flag("1");
        bean.setIs_forbidden("2");//0：启用   1：禁用    2:待审批   3:未通过
        bean.setValid_begin_date(new java.sql.Date(new Date().getTime()));//启用时间
        ret = wmsinvepruductcategoryDao.update(bean);
        if (ret== 0)
        {
            resStr = "error";
            return resStr;
        }

        List<WmsInvePruductRebateWay> listgridBasicVal = JsonUtil.jsonArrayToList(BeanVO.getGridBasicVal(),WmsInvePruductRebateWay.class);
        for (int i = 0; i < listgridBasicVal.size(); i++)
        {
        	WmsInvePruductRebateWay gridBasic = listgridBasicVal.get(i);
            gridBasic.setCreate_user_id(user.getUserId());
            gridBasic.setEnable_flag("1");
            gridBasic.setCreate_user_id(user.getUserId());
            gridBasic.setCreate_datetime(new Timestamp(new Date().getTime()));
            gridBasic.setWms_inve_pruduct_category_id(bean.getWms_inve_pruduct_category_id());
            gridBasic.setWms_inve_pruduct_rebate_way_id(null);
            wmsInvePruductRebateWayDao.save(gridBasic);
         
        }
        // wms_inve_pruduct_return
        List<WmsInvePruductReturn> listgridLimitVal = JsonUtil.jsonArrayToList(BeanVO.getGridLimitVal(), WmsInvePruductReturn.class);
        for (int i = 0; i < listgridLimitVal.size(); i++)
        {
            WmsInvePruductReturn gridLimit = listgridLimitVal.get(i);
            gridLimit.setCreate_user_id(user.getUserId());
            gridLimit.setCreate_timestamp(new Timestamp(new Date().getTime()));
            gridLimit.setLast_update_timestamp(new Timestamp(new Date().getTime()));
            gridLimit.setWms_inve_pruduct_category_id(bean.getWms_inve_pruduct_category_id());
            gridLimit.setWms_inve_pruduct_return_id(null);
            wmsInvePruductReturnDao.save(gridLimit);
        }
        if(paySpecial!=null&&(paySpecial.getFirst_year_interest_rate()!=null||paySpecial.getSecond_year_interest_rate()!=null)){//判断理财——产品利率设置表-年付特 是否为空
        	   paySpecial.setWms_inve_pruduct_category_id(bean.getWms_inve_pruduct_category_id());
        	   paySpecial.setCreate_user_id(user.getUserId());
        	   paySpecial.setEnable_flag("1");
        	   paySpecial.setCreate_user_id(user.getUserId());
        	   paySpecial.setCreate_datetime(new Timestamp(new Date().getTime()));
        	   paySpecial.setWms_inve_pruduct_category_id(bean.getWms_inve_pruduct_category_id());
        	   paySpecial.setWms_inve_pruduct_year_pay_special_id(null);
        	   wmsInvePruductYearPaySpecialDao.save(paySpecial);
        }
     
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }
	/***
	* Description:获取理财产品编号
	* 
	* @return String
	* @author baisong
	*/
    @Override
    public String getLCCode()
    {
        return CodeNoUtil.getLCCode();
    }
    /**
     * Description :残品确认保存审批意见方法
     * 
     * @param BeanVO
     * @return "success" or "error" or user defined
     * @author baisong
     */
	@Override
	public String doSaveSp(UserBean user,WmsInvePruductCategorySearchBeanVO BeanVO) {
	 	String resStr = "success";
	 	int	ret=0;
		 	WmsInvePruductCategory bean=new WmsInvePruductCategory();
		 	bean.setWms_inve_pruduct_category_id(BeanVO.getWms_inve_pruduct_category_id());
		 	bean.setIs_forbidden(BeanVO.getAppro_result().toString());
		 	//
	        bean.setLast_update_user_id(user.getUserId());
	        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
	        //bean.setValid_begin_date(new java.sql.Date(new Date().getTime()));//启用时间
		 	ret = wmsinvepruductcategoryDao.update(bean); // update a record replace
	        if (ret == 0)
	        {
	            resStr = "error";
	        }
	        WmsInvePruductCategoryLog categoryLog =new WmsInvePruductCategoryLog();
			categoryLog.setWms_inve_pruduct_category_id(BeanVO.getWms_inve_pruduct_category_id());
			categoryLog.setOperation_type(1);//1：产品确认   2：产品禁用    3：产品启用
			categoryLog.setOperator_user_id(user.getUserId());
			categoryLog.setOperator_name(user.getRealname());
			categoryLog.setOperator_datetime(new Timestamp(System.currentTimeMillis()));
			categoryLog.setOperator_ip(BeanVO.getOperator_ip());
			categoryLog.setAppro_result(BeanVO.getAppro_result());
			categoryLog.setAppro_advice(BeanVO.getAppro_advice());
			categoryLog.setCategory_code(BeanVO.getCategory_code());
			categoryLog.setEnable_flag("1");
			ret =wmsInvePruductCategoryLogDao.save(categoryLog);//日志
			if (ret == 0)
		    {
		        resStr = "error";
		    }
		return resStr;
	}
	/**
	 * 
	 * 
	 */
	@Override
	public String doSaveQy(UserBean user, WmsInvePruductCategoryLog beanlog,
			WmsInvePruductCategory bean) {
			String resStr = "success";
	        int	ret=0;
		 	if(beanlog.getT_appro_result()==1){//特批结果 0不同意 1同意
		 		bean.setIs_forbidden("0");//启用
		 		bean.setLast_update_user_id(user.getUserId());
			    bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
			 	ret = wmsinvepruductcategoryDao.update(bean); // update a record replace
		        if (ret == 0)
		        {
		            resStr = "error";
		        }
	        }
	        beanlog.setOperation_type(3);//1：产品确认   2：产品禁用    3：产品启用
	        beanlog.setOperator_user_id(user.getUserId());
			beanlog.setOperator_name(user.getRealname());
			beanlog.setOperator_datetime(new Timestamp(System.currentTimeMillis()));
			beanlog.setEnable_flag("1");
			ret =wmsInvePruductCategoryLogDao.save(beanlog);//日志
			if (ret == 0)
		    {
		        resStr = "error";
		    }
		return resStr;
	}
	/**
	 * 检查产品名称是否已经存在
	 * @param bean
	 * @return
	 */
	@Override
	public String isCheck(WmsInvePruductCategory bean){
		String resStr = "success";
		//判断该产品名称是否存在
	    List<WmsInvePruductCategory> listByName = wmsinvepruductcategoryDao.getAllInvePruductCategoryByName(bean);
        if (listByName.size() > 0)
        {
            resStr = "exist";
            return resStr;
        }
        return resStr;
	}
	
	/**
	 * 查看产品是否禁用
	 */
	public Map<String, Object> getProductIsForbidden(Integer wms_inve_pruduct_category_id) {
		return wmsinvepruductcategoryDao.getProductIsForbidden(wms_inve_pruduct_category_id);
	}
   /**
    * 根据传入产品有效状态，显示相应的产品信息
    */
	@Override
	public List<WmsInvePruductCategory> getInvePruductCategoryInfo(String is_forbidden) {
		 Map<String,Object> paMap = new HashMap<String,Object>();
		 paMap.put("is_forbidden", is_forbidden);
		 List<com.zx.emanage.util.gen.entity.WmsInvePruductCategory> list = wmsinvepruductcategoryDao.getInvePruductCategoryInfo(paMap);
	        com.zx.emanage.util.gen.entity.WmsInvePruductCategory entity = new com.zx.emanage.util.gen.entity.WmsInvePruductCategory();
	        entity.setWms_inve_pruduct_category_id(-1);
	        entity.setCategory_name("请选择");
	        list.add(0, entity);
	        return list;
	}
	
	/**
	 * 获取产品并按照产品类型分类
	 */
	@Override
	public Map<String, Object> getInvePruductCategorys() {
		Map<String, Object> resMap = new HashMap<String, Object>();
		//初始化无抵押类产品集合
		List<WmsInvePruductCategory> wdyCategory = new ArrayList<WmsInvePruductCategory>();
		//初始化抵押类产品集合
		List<WmsInvePruductCategory> dyCategory = new ArrayList<WmsInvePruductCategory>();
		
		List<WmsInvePruductCategory> wmsInvePruductCategories = wmsinvepruductcategoryDao.getListforLC(null);
		//区分产品类型
		for(WmsInvePruductCategory category : wmsInvePruductCategories) {
			//产品类型(category_type):  1:信用类产品  2：房贷抵押类产品  3：车贷抵押类产品
			if(category.getCategory_type() == 1) {	//无抵押类产品
				wdyCategory.add(category);
			} else {	//抵押类产品
				dyCategory.add(category);
			}
		}
		resMap.put("无抵押类产品", wdyCategory);
		resMap.put("抵押类产品", dyCategory);
		
		return resMap;
	}
	
	/**
     * Description :获取全部启用产品   (产品变更时使用 用户选择产品信息时调用  申请页面也会调用 用于处理奖励利率取值不准的情况--根据存量来处理)
     * 
     * @param queryInfo
     * @return  Map<String, Object>
     * @author baisong
     */
	@Override
    public Map<String, Object> getAllProductListWithoutPaging(String cktype, String category_name, Integer wms_inve_pruduct_category_id)
    {
        Map<String, Object> paramMap = new HashMap<>();
        if ("ck".equals(cktype))
        {
            paramMap.put("cktype", "ck");
        }
        if(StringUtil.isNotBlank(category_name)) {
	 		paramMap.put("category_name", SysTools.getSqlLikeParam(category_name));
	 	}
        if(wms_inve_pruduct_category_id != null) {
        	paramMap.put("wms_inve_pruduct_category_id", wms_inve_pruduct_category_id);
        }
        paramMap.put("sortname", "category_sales_statistics");
        paramMap.put("sortorder", "desc");
        List<Map<String, Object>> products = new LinkedList<Map<String, Object>>();
        products = wmsinvepruductcategoryDao.getAllProductList(paramMap);
        for (Map<String, Object> product : products)
        {
        	Integer category_id = (Integer) product.get("wms_inve_pruduct_category_id");
        	//年化利率
            WmsInvePruductYearPaySpecial nft = wmsInvePruductYearPaySpecialDao.get(category_id);
         	//年化利率
			String productInterest;
			//年化利率和（计算收益）
			BigDecimal productInterestToltal;
			//是否是特殊情况（年付特）
			String isNft = "0";
            if(nft!=null) {
				productInterest = nft.getFirst_year_interest_rate() + "/" + nft.getSecond_year_interest_rate();
				productInterestToltal = nft.getFirst_year_interest_rate().add(nft.getSecond_year_interest_rate());
				isNft = "1";
			} else {
				productInterest = product.get("category_return_rate").toString();
				productInterestToltal =new BigDecimal(product.get("category_return_rate").toString());
			}
            product.put("productInterest", productInterest);
            product.put("productInterestToltal", productInterestToltal);
            product.put("isNft", isNft);
          
            paramMap = new HashMap<>();
            paramMap.put("wms_inve_pruduct_category_id", category_id);
            paramMap.put("sortname", "full_month");
            paramMap.put("sortorder", "asc");
            //奖励利率
            List<Map<String, Object>> jllls = wmsInvePruductRebateWayDao.search(paramMap);
            product.put("jllls", jllls);
            
            //奖励利率
			String rewardInterest ="";
			BigDecimal  rewardInterestTotal =new BigDecimal(0);
			//根据返利方式判断奖励利率
			if(product.get("category_rebate_way").toString().equals("1")) { //满足月
				for(int m=0;m<jllls.size();m++){
					Map<String, Object>	obj=jllls.get(m);
					if(m > 0) {
						rewardInterest += "<br/>";
					}
					rewardInterest += "满足" + obj.get("full_month") + "个月，奖励利率" + new BigDecimal(obj.get("bonus_rate").toString()).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP)+ "%";
					rewardInterestTotal=rewardInterestTotal.add(new BigDecimal(obj.get("bonus_rate").toString()));
				}
			} else if(product.get("category_rebate_way").toString().equals("2")) { //累计存量
				for(int m=0;m<jllls.size();m++){
					Map<String, Object>	obj=jllls.get(m);
					if(m > 0) {
						rewardInterest += "<br/>";
					}
					rewardInterest += "客户存量满" + obj.get("customer_stock_begin") + "万至" + obj.get("customer_stock_end") + "万并且存满" + obj.get("full_month")+"个月，奖励利率"+new BigDecimal(obj.get("bonus_rate").toString()).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP)+ "%";
					rewardInterestTotal=rewardInterestTotal.add(new BigDecimal(obj.get("bonus_rate").toString()));
				}

			} else {
				rewardInterest = "0";
			}
			 product.put("rewardInterest", rewardInterest);
             product.put("rewardInterestTotal", rewardInterestTotal);
        }
        paramMap.clear();
        paramMap.put("Rows", products);
        return paramMap;
    }

    /**
     * @Title: getCategoryTypeByCategoryId
     * @Description: 根据产品id获得产品类型
     * @param categoryId 产品id 
     * @return 返回产品类型
     * @author: jinzhm
     * @time:2017年5月19日 上午9:30:04
     * @see com.zx.emanage.inve.service.IWmsInvePruductCategoryService#getCategoryTypeByCategoryId(java.lang.Integer)
     * history:
     * 1、2017年5月19日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getCategoryTypeByCategoryId(Integer categoryId)
    {
        // 查询产品信息
        WmsInvePruductCategory category = wmsinvepruductcategoryDao.get(categoryId);
        // 设置产品类型并返回
        Map<String, Object> rMap = new HashMap<String, Object>();
        rMap.put("category_type", category.getCategory_type());
        return rMap;
    }
}
