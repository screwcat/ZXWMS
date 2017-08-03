package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCustomerCardDao;
import com.zx.emanage.inve.service.IWmsInveCustomerCardService;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.inve.vo.WmsInveCustomerCardSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecustomercardService")
public class WmsInveCustomerCardServiceImpl implements IWmsInveCustomerCardService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCustomerCardServiceImpl.class);

	@Autowired
	private WmsInveCustomerCardDao wmsinvecustomercardDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCustomerCardSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecustomercardDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCustomerCardSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecustomercardDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecustomercardDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCustomerCard getInfoByPK(Integer wms_inve_customer_card_id) {
		return wmsinvecustomercardDao.get(wms_inve_customer_card_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCustomerCard bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecustomercardDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCustomerCard bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecustomercardDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCustomerCard() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCustomerCard> getListByEntity(WmsInveCustomerCard queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCustomerCard> beanList = wmsinvecustomercardDao.getListByEntity(queryInfo);
		return beanList;
	}

    /**
     * @Title: newAddIncomeCard
     * @Description: 新增客户收益卡信息
     * @param user 当前登录人信息对象
     * @param wmsInveCustomerCard 收益卡信息对象
     * @return 返回map信息提示
     * @author: DongHao
     * @time:2017年4月7日 下午2:29:08
     * @see com.zx.emanage.inve.service.IWmsInveCustomerCardService#newAddIncomeCard(com.zx.sframe.util.vo.UserBean, com.zx.emanage.util.gen.entity.WmsInveCustomerCard)
     * history:
     * 1、2017年4月7日 DongHao 创建方法
    */
    @Override
    @Transactional
    public Map<String, Object> newAddIncomeCard(UserBean user, WmsInveCustomerCard wmsInveCustomerCard)
    {
        //定义返回结果集合
        Map<String, Object> res_map = new HashMap<String, Object>();
        
        //定义收益卡信息表主键变量
        Integer wms_inve_customer_card_id = 0;
        
        //设置创建时间
        wmsInveCustomerCard.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        
        //设置创建人
        wmsInveCustomerCard.setCreate_user_id(user.getUserId());
        
        //设置是否有效标识
        wmsInveCustomerCard.setEnable_flag("1");
        
        //根据收益卡信息获取收益卡信息是否存在重复的数据
        List<WmsInveCustomerCard> list = wmsinvecustomercardDao.getIncomeCardRepeat(wmsInveCustomerCard);
        
        //判断是否存在重复的数据
        if(list != null && list.size() > 0)
        {
            
            //遍历收益卡集合,更新收益卡信息
            for(WmsInveCustomerCard card : list)
            {
                card.setBank_branch(wmsInveCustomerCard.getBank_branch());
                card.setCard_no(wmsInveCustomerCard.getCard_no());
                card.setId_card(wmsInveCustomerCard.getId_card());
                card.setCard_owner_name(wmsInveCustomerCard.getCard_owner_name());
                card.setBank_of_deposit(wmsInveCustomerCard.getBank_of_deposit());
                card.setBank_of_deposit_city(wmsInveCustomerCard.getBank_of_deposit_city());
                card.setBank_of_deposit_pro(wmsInveCustomerCard.getBank_of_deposit_pro());
                
                //更新收益卡信息
                wmsinvecustomercardDao.update(card);
                
            }
            wms_inve_customer_card_id = list.get(0).getWms_inve_customer_card_id();
        }
        else
        {
            //保存收益卡信息
            wmsinvecustomercardDao.save(wmsInveCustomerCard);
            
            //得到保存收益卡信息表主键
            wms_inve_customer_card_id = wmsInveCustomerCard.getWms_inve_customer_card_id();
        }
        
        res_map.put("wms_inve_customer_card_id", wms_inve_customer_card_id);
        res_map.put("error", "success");
        
        return res_map;
    }
}
