package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.exception.InveTransException;
import com.zx.emanage.inve.persist.WmsInveTransaPruductDao;
import com.zx.emanage.inve.persist.WmsInveTransaPruductUserDao;
import com.zx.emanage.inve.persist.WmsInveTransaUserDao;
import com.zx.emanage.inve.service.IWmsInveTransaPruductUserService;
import com.zx.emanage.inve.vo.WmsInveTransaPruductUserSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveTransaPruduct;
import com.zx.emanage.util.gen.entity.WmsInveTransaPruductUser;
import com.zx.emanage.util.gen.entity.WmsInveTransaUser;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransapruductuserService")
public class WmsInveTransaPruductUserServiceImpl implements IWmsInveTransaPruductUserService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaPruductUserServiceImpl.class);

	@Autowired
	private WmsInveTransaPruductUserDao wmsinvetransapruductuserDao;
	
	@Autowired
    private WmsInveTransaPruductDao wmsInveTransaPruductDao;
	
	@Autowired
    private WmsInveTransaUserDao wmsInveTransaUserDao;
	
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTransaPruductUserSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetransapruductuserDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	/**
	 * 查询上单产品限制表list(带分页)
	 * @author wangyihan
	 */
	public Map<String, Object> getListWithPaging(WmsInveTransaPruductUserSearchBeanVO queryInfo) {
	    log.debug("--------------------查询上单产品限制表列表数据处理开始--------------------");
	    
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvetransapruductuserDao.search(paramMap);
        
        Integer wms_inve_transa_pruduct_user_id = 0;
        String productIds = "";
        String productNames = "";
        String userIds = "";
        String userNames = "";
        String shortCodes = "";
        
        Map<String, Object> paramMapProduct = new HashMap<String, Object>();
        Map<String, Object> paramMapUser = new HashMap<String, Object>();
        
        List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
        
        if(list != null && list.size() > 0){
            for(int i = 0;i < list.size(); i++){
                
                wms_inve_transa_pruduct_user_id = Integer.valueOf(list.get(i).get("wms_inve_transa_pruduct_user_id").toString());
                paramMapProduct.put("wms_inve_transa_pruduct_user_id", wms_inve_transa_pruduct_user_id);
                paramMapProduct.put("sortname", "wms_inve_transa_pruduct_id");
                paramMapProduct.put("sortorder", "asc");
                
                paramMapUser.put("wms_inve_transa_pruduct_user_id", wms_inve_transa_pruduct_user_id);
                paramMapUser.put("sortname", "wms_inve_transa_user_id");
                paramMapUser.put("sortorder", "asc");
                
                productList = wmsInveTransaPruductDao.search(paramMapProduct);
                userList = wmsInveTransaUserDao.search(paramMapUser);
                
                //产品list
                list.get(i).put("productList", wmsInveTransaPruductDao.search(paramMapProduct));
                //人员list
                list.get(i).put("userList", wmsInveTransaUserDao.search(paramMapUser));
                
                //往list里插入id name
                productIds = "";
                productNames = "";
                userIds = "";
                userNames = "";
                shortCodes = "";
                for(int j = 0;j < productList.size(); j++){
                    if(j == 0){
                        productIds += productList.get(j).get("wms_inve_pruduct_category_id");
                        productNames += productList.get(j).get("category_name");
                    }else{
                        productIds += "," + productList.get(j).get("wms_inve_pruduct_category_id");
                        productNames += "," + productList.get(j).get("category_name");
                    }
                }
                for(int j = 0;j < userList.size(); j++){
                    if(j == 0){
                        userIds += userList.get(j).get("personnel_id");
                        userNames += userList.get(j).get("personnel_name");
                        shortCodes += userList.get(j).get("personnel_shortcode");
                    }else{
                        userIds += "," + userList.get(j).get("personnel_id");
                        userNames += "," + userList.get(j).get("personnel_name");
                        shortCodes += "," + userList.get(j).get("personnel_shortcode");
                    }
                }
                list.get(i).put("productIds", productIds);
                list.get(i).put("productNames", productNames);
                list.get(i).put("userIds", userIds);
                list.get(i).put("userNames", userNames);
                list.get(i).put("shortCodes", shortCodes);
            }
        }
        
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetransapruductuserDao.findCount(paramMap), list);
        
        log.debug("--------------------查询上单产品限制表列表数据处理结束--------------------");
		return bean.getGridData();			
	}

	@Override
	public WmsInveTransaPruductUser getInfoByPK(Integer wms_inve_transa_pruduct_user_id) {
		return wmsinvetransapruductuserDao.get(wms_inve_transa_pruduct_user_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveTransaPruductUser bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransapruductuserDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveTransaPruductUser bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransapruductuserDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveTransaPruductUser() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveTransaPruductUser> getListByEntity(WmsInveTransaPruductUser queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveTransaPruductUser> beanList = wmsinvetransapruductuserDao.getListByEntity(queryInfo);
		return beanList;
	}
	
	/**
     * Description :整个页面数据保存
     * @param bean user
     * @return "success" or "error" or user defined
     * @author wangyihan
     */
	@Transactional
    public String allSave(WmsInveTransaPruductUserSearchBeanVO bean, UserBean user) throws InveTransException{
        log.debug("--------------------整个页面数据保存处理开始--------------------");
        
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        
        Map<String, Object> deleteParamMap_User = new HashMap<String, Object>();
        Map<String, Object> deleteParamMap_Product = new HashMap<String, Object>();
        
        List<WmsInveTransaPruductUserSearchBeanVO> list = 
                JsonUtil.jsonArrayToList(bean.getWmsInveTransaPruductUserSearchBeanVOList_JSON(), WmsInveTransaPruductUserSearchBeanVO.class);
        
        //delete页面删除行记录
        if(StringUtils.isNotBlank(bean.getDeleteIds())){
            String deleteIds = bean.getDeleteIds();
            //根据主表id删除人员表与产品表数据
            for(String s : deleteIds.split(",")){
                deleteParamMap_User = new HashMap<String, Object>();
                deleteParamMap_User.put("wms_inve_transa_pruduct_user_id", s);
                
                deleteParamMap_Product = new HashMap<String, Object>();
                deleteParamMap_Product.put("wms_inve_transa_pruduct_user_id", s);
                
                wmsInveTransaUserDao.deleteByWmsInveTransaPruductUserId(deleteParamMap_User);
                wmsInveTransaPruductDao.deleteByWmsInveTransaPruductUserId(deleteParamMap_Product);
                
                this.wmsinvetransapruductuserDao.deleteByPK(Integer.parseInt(s));
            }
        }
        
        WmsInveTransaPruductUser outerBean = new WmsInveTransaPruductUser();
        
        WmsInveTransaPruduct innerBean1 = new WmsInveTransaPruduct();
        WmsInveTransaUser innerBean2 = new WmsInveTransaUser();
        
        List<WmsInveTransaPruduct> wmsInveTransaPruductList = new ArrayList<WmsInveTransaPruduct>();
        List<WmsInveTransaUser> wmsInveTransaUserList = new ArrayList<WmsInveTransaUser>();
        
        for(WmsInveTransaPruductUserSearchBeanVO vo : list){
            //先更新主表记录
            outerBean = new WmsInveTransaPruductUser();
            outerBean.setWms_inve_transa_pruduct_user_id(vo.getWms_inve_transa_pruduct_user_id());
            outerBean.setCreate_user_id(user.getUserId());
            outerBean.setCreate_user_name(user.getRealname());
            outerBean.setEnable_flag("1");
            outerBean.setCreate_timestamp(nowTime);
            outerBean.setGroup_info(vo.getGroup_info());
            if(outerBean.getWms_inve_transa_pruduct_user_id() == null){//id不存在insert
                wmsinvetransapruductuserDao.save(outerBean);
            }else{//id存在update
                wmsinvetransapruductuserDao.update(outerBean);
                //删除两个副表记录
                deleteParamMap_User = new HashMap<String, Object>();
                deleteParamMap_User.put("wms_inve_transa_pruduct_user_id", outerBean.getWms_inve_transa_pruduct_user_id());
                
                deleteParamMap_Product = new HashMap<String, Object>();
                deleteParamMap_Product.put("wms_inve_transa_pruduct_user_id", outerBean.getWms_inve_transa_pruduct_user_id());
                
                wmsInveTransaUserDao.deleteByWmsInveTransaPruductUserId(deleteParamMap_User);
                wmsInveTransaPruductDao.deleteByWmsInveTransaPruductUserId(deleteParamMap_Product);
            }
            
            wmsInveTransaPruductList = new ArrayList<WmsInveTransaPruduct>();//重置list
            if(StringUtils.isNotBlank(vo.getProductIds())){
                String productIds[] = vo.getProductIds().split(",");
                String productNames[] = vo.getProductNames().split(",");
                //循环插入product表记录
                for(int i = 0; i < productIds.length; i++){
                    innerBean1 = new WmsInveTransaPruduct();
                    innerBean1.setWms_inve_transa_pruduct_user_id(outerBean.getWms_inve_transa_pruduct_user_id());
                    Integer id = Integer.parseInt(productIds[i]);
                    innerBean1.setWms_inve_pruduct_category_id(id);
                    
                    innerBean1.setCategory_name(productNames[i]);
                    innerBean1.setEnable_flag("1");
                    wmsInveTransaPruductList.add(innerBean1);
                }  
                this.wmsInveTransaPruductDao.saveList(wmsInveTransaPruductList);
            }
            wmsInveTransaUserList = new ArrayList<WmsInveTransaUser>();//重置list
            if(StringUtils.isNotBlank(vo.getUserIds())){
                String userIds[] = vo.getUserIds().split(",");
                String userNames[] = vo.getUserNames().split(",");
                String shortCodes[] = vo.getShortCodes().split(",");
                
                //循环插入user表记录
                for(int i = 0; i < userIds.length; i++){
                    innerBean2 = new WmsInveTransaUser();
                    innerBean2.setWms_inve_transa_pruduct_user_id(outerBean.getWms_inve_transa_pruduct_user_id());
                    Integer id = Integer.parseInt(userIds[i]);
                    innerBean2.setPersonnel_id(id);
                    innerBean2.setPersonnel_name(userNames[i]);
                    innerBean2.setPersonnel_shortcode(shortCodes[i]);
                    innerBean2.setEnable_flag("1");
                    wmsInveTransaUserList.add(innerBean2);
                }
                this.wmsInveTransaUserDao.saveList(wmsInveTransaUserList);
            }
        }
        
        List<Map<String, Object>> sameUserList = this.wmsInveTransaUserDao.searchSameUser();
        if(sameUserList != null && sameUserList.size() > 0){
            throw new InveTransException();
        }
        
        log.debug("--------------------整个页面数据保存处理结束--------------------");
        return "success";
    }
    
	
}
