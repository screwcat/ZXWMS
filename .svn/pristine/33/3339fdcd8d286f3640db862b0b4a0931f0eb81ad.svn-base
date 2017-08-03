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

import com.zx.emanage.inve.persist.WmsInvePadPowerDao;
import com.zx.emanage.inve.service.IWmsInvePadPowerService;
import com.zx.emanage.inve.vo.WmsInvePadPowerSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInvePadPower;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvepadpowerService")
public class WmsInvePadPowerServiceImpl implements IWmsInvePadPowerService {
	private static Logger log = LoggerFactory.getLogger(WmsInvePadPowerServiceImpl.class);

	@Autowired
	private WmsInvePadPowerDao wmsinvepadpowerDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInvePadPowerSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvepadpowerDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInvePadPowerSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvepadpowerDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvepadpowerDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInvePadPower getInfoByPK(Integer wms_inve_pad_power_id) {
		return wmsinvepadpowerDao.get(wms_inve_pad_power_id);
	}

	@Override	
	@Transactional
	public String save(WmsInvePadPower bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvepadpowerDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInvePadPower bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
        ret = wmsinvepadpowerDao.update(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	

    /**
     * 
     * @Title: getWmsInvePadPowerListByDeviceNum
     * @Description: 根据设备编号查询pad权限表集合  判断当前用户有没有使用该pad的权限
     * @param device_num
     * @return 
     * @author: Administrator
     * @time:2017年2月23日 下午1:36:55
     * @see com.zx.emanage.inve.service.IWmsInvePadPowerService#getWmsInvePadPowerListByDeviceNum(java.lang.String)
     * history:
     * 1、2017年2月23日 Administrator 创建方法
     */
    @Override
    public boolean getWmsInvePadPowerAuthorByDeviceNum(String device_num, UserBean user)
    {
        boolean flg = true;
        WmsInvePadPower wmsInvePadPower = new WmsInvePadPower();
        wmsInvePadPower.setDevice_num(device_num);
        wmsInvePadPower.setPersonnel_shortcode(user.getUserCode());
        List<WmsInvePadPower> wmsInvePadPowerList = wmsinvepadpowerDao.getWmsInvePadAuthorList(wmsInvePadPower);
        if (wmsInvePadPowerList.size() > 0)
        {
            flg = true;
        }
        else
        {
            flg = false;
        }
        return flg;
    }

    /**
     * @Title: authorPad
     * @Description: pad解锁 或强退 
     * @param wmsInvePadPower
     * @param sys_status 
     * @author: Administrator
     * @time:2017年2月23日 下午4:34:17
     * @see com.zx.emanage.inve.service.IWmsInvePadPowerService#authorPad(com.zx.emanage.util.gen.entity.WmsInvePadPower, java.lang.Integer)
     * history:
     * 1、2017年2月23日 Administrator 创建方法
    */
    @Override
    public void authorPad(WmsInvePadPower wmsInvePadPower, Integer sys_status)
    {
        if (new Integer(1).equals(sys_status))
        {
            unlockPad(wmsInvePadPower);
        }else{
            forceQuit(wmsInvePadPower);
        }

    }

    /**
     * 
     * @Title: unlockPad
     * @Description: 解锁pad 插入pad权限表操作
     * @param wmsInvePadPower 
     * @author: zhangyunfei
     * @time:2017年2月23日 下午4:52:46
     * history:
     * 1、2017年2月23日 Administrator 创建方法
     */
    private void unlockPad(WmsInvePadPower wmsInvePadPower)
    {
        wmsInvePadPower.setStart_time(new Timestamp(new Date().getTime()));
        wmsInvePadPower.setEnd_time(DateUtil.strTimestamp("9999-12-31", null));
        wmsInvePadPower.setCreate_timestamp(new Timestamp(new Date().getTime()));
        wmsInvePadPower.setEnable_flag("1");
        wmsinvepadpowerDao.save(wmsInvePadPower);
    }

    /**
     * 
     * @Title: forceQuit
     * @Description: 强退操作  更新失效时间
     * @param wmsInvePadPower 
     * @author: zhangyunfei
     * @time:2017年2月23日 下午4:53:11
     * history:
     * 1、2017年2月23日 Administrator 创建方法
     */
    private void forceQuit(WmsInvePadPower wmsInvePadPower)
    {
        wmsinvepadpowerDao.updateEndTime(wmsInvePadPower);
    }
}
