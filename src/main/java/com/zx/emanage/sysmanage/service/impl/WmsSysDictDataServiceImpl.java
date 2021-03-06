package com.zx.emanage.sysmanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.sysmanage.persist.IWmsSysDictDataDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.WmsSysDictDataDao;
import com.zx.emanage.sysmanage.service.IWmsSysDictDataService;
import com.zx.emanage.sysmanage.vo.WmsSysDictDataSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsSysDictData;
import com.zx.emanage.util.gen.entity.SysDept;
import com.zx.emanage.util.gen.entity.SysDeptDataBean;
import com.zx.emanage.util.gen.vo.WmsSysDictDataVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.platform.syscontext.vo.TreeBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmssysdictdataService")
public class WmsSysDictDataServiceImpl implements IWmsSysDictDataService
{
    private static Logger log = LoggerFactory.getLogger(WmsSysDictDataServiceImpl.class);

    @Autowired
    private IWmsSysDictDataDao wmssysdictdataDao;

    @Autowired
    private WmsSysDictDataDao wmssysdictdataDao_m;
    
    @Autowired
    private SysDeptDao sysDeptDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsSysDictDataSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", wmssysdictdataDao.getListWithoutPaging(queryInfo));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsSysDictDataSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmssysdictdataDao.getListCountNum(queryInfo),
                                             wmssysdictdataDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public WmsSysDictDataVO getInfoByPK(Integer wms_sys_dict_data_id)
    {
        return wmssysdictdataDao.getInfoByPK(wms_sys_dict_data_id);
    }

    @Override
    @Transactional
    public String save(WmsSysDictData bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmssysdictdataDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsSysDictData bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmssysdictdataDao.updateRecordAll(bean); // update a record
                                                       // replace all, support
                                                       // null val
        // ret = wmssysdictdataDao.updateRecordCols(bean); // update a record
        // replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(WmsSysDictData bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = wmssysdictdataDao.deleteRecordByDomain(bean); // nonsupport
                                                                // null val
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resStr = "fkerror"; // default foreign key error
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsSysDictData() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(WmsSysDictData queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_sys_dict_data_id() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<WmsSysDictData> beanList = wmssysdictdataDao.getSingleTableListByAndMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * WmsSysDictData() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(WmsSysDictData queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_sys_dict_data_id() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<WmsSysDictData> beanList = wmssysdictdataDao.getSingleTableListByOrMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    @Override
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictId(Integer wms_sys_dict_id,
                                                                                   String isEmpty, String isAll)
    {
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m.getDictDataByDictId(wms_sys_dict_id);
        if ("true".equals(isEmpty))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-1);
            entity.setValue_code(" ");
            entity.setValue_meaning("请选择");
            list.add(0, entity);
        }
        if ("true".equals(isAll))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-1);
            entity.setValue_meaning("全部");
            list.add(0, entity);
        }
        return list;
    }

    @Override
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmpty(Integer wms_sys_dict_id,
                                                                                        String isEmpty, String isAll)
    {
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m.getDictDataByDictId(wms_sys_dict_id);
        if ("true".equals(isEmpty))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-1);
            entity.setValue_code("-1");
            entity.setValue_meaning("请选择");
            list.add(0, entity);
        }
        if ("true".equals(isAll))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-2);
            entity.setValue_meaning("全部");
            list.add(0, entity);
        }
        return list;
    }

    /**
     * 
     * @Title: getDictDataByDictIdEmptyNotCombination
     * @Description: 根据需要获取贷款产品 并判断是否是组合贷
     * @param wms_sys_dict_id
     * @param isEmpty
     * @param isAll
     * @return 
     * @author: Administrator
     * @time:2017年5月12日 上午11:14:24
     * history:
     * 1、2017年5月12日 Administrator 创建方法
     */
    @Override
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmptyCombination(Integer wms_sys_dict_id, String isEmpty, String isAll, String isCombin)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("wms_sys_dict_id", wms_sys_dict_id);
        if (StringUtil.isNotBlank(isCombin))
        {
            map.put("isCombin", isCombin);
        }
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m.getDictDataByDictIdCombination(map);
        if ("true".equals(isEmpty))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-1);
            entity.setValue_code("-1");
            entity.setValue_meaning("请选择");
            list.add(0, entity);
        }
        if ("true".equals(isAll))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-2);
            entity.setValue_meaning("全部");
            list.add(0, entity);
        }
        return list;
    }
	@Override
	public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmptyAll(
			String wms_sys_dict_id, String isEmpty, String isAll) {
		List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m
				.getDictDataByDictIdAll(wms_sys_dict_id);
		if ("true".equals(isEmpty)) {
			com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
			entity.setWms_sys_dict_data_id(-1);
			entity.setValue_code("-1");
            entity.setValue_meaning("---请选择---");
			list.add(0, entity);
		}
		if ("true".equals(isAll)) {
			com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
			entity.setWms_sys_dict_data_id(-2);
            entity.setValue_meaning("---全部---");
			list.add(0, entity);
		}
		return list;
	}
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmpty(Integer wms_sys_dict_id, Integer p_wms_sys_dict_data_id,
                                                                                        String isEmpty, String isAll) {
    	List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = new ArrayList<com.zx.emanage.util.gen.entity.WmsSysDictData>();
    	if(wms_sys_dict_id != null && p_wms_sys_dict_data_id != null) {
    		Map<String, Object> paramMap = new HashMap<String, Object>();
        	paramMap.put("wms_sys_dict_id", wms_sys_dict_id);
        	paramMap.put("p_wms_sys_dict_data_id", p_wms_sys_dict_data_id);
            list = wmssysdictdataDao_m.getDictData(paramMap);
    	}
        if ("true".equals(isEmpty))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-1);
            entity.setValue_code("-1");
            entity.setValue_meaning("请选择");
            list.add(0, entity);
        }
        if ("true".equals(isAll))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-2);
            entity.setValue_meaning("全部");
            list.add(0, entity);
        }
        return list;
    }

    @Deprecated
    public List<WmsSysDictDataVO> getDictDataByDictCode(String dict_code)
    {
        return wmssysdictdataDao.getDictDataByDictCode(dict_code);
    }

    @Override
    public List<TreeBean> getDictDataTreeBean(Integer p_wms_sys_dict_id, Integer wms_sys_dict_id, String... isnotval)
    {
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = new ArrayList<>();
        if (isnotval != null && !"".equals(isnotval) && isnotval.length > 0 && isnotval[0] != null && !"".equals(isnotval[0]))
        {
            Map<String, Object> map = new HashMap<>();
            map.put("wms_sys_dict_id", wms_sys_dict_id);
            map.put("value_list", isnotval[0]);
            list = wmssysdictdataDao_m.getDictDataByDictIdNotVal(map);
        }
        else
        {
            list = wmssysdictdataDao_m.getDictDataByDictId(wms_sys_dict_id);
        }
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> p_list = wmssysdictdataDao_m.getDictDataByDictId(p_wms_sys_dict_id);
        List<TreeBean> pList = new ArrayList<TreeBean>();
        for (com.zx.emanage.util.gen.entity.WmsSysDictData entity : p_list)
        {
            String id = "" + entity.getWms_sys_dict_data_id();

            TreeBean p_bean = new TreeBean();
            p_bean.setId(id);
            p_bean.setText(entity.getValue_meaning());

            List<TreeBean> childList = new ArrayList<TreeBean>();
            for (com.zx.emanage.util.gen.entity.WmsSysDictData child : list)
            {
                String p_id = "" + child.getP_wms_sys_dict_data_id();
                if (id.equals(p_id))
                {
                    TreeBean bean = new TreeBean();
                    bean.setId("" + child.getWms_sys_dict_data_id());
                    bean.setText(child.getValue_meaning());
                    childList.add(bean);
                }
            }
            p_bean.setChildren(childList);
            pList.add(p_bean);
        }
        return pList;
    }

    @Override
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdAmount(Integer wms_sys_dict_id,
                                                                                         String isEmpty)
    {
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m.getDictDataByDictId(wms_sys_dict_id);
        list.remove(0);
        if ("true".equals(isEmpty))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-1);
            entity.setValue_code("-1");
            entity.setValue_meaning("请选择");
            list.add(0, entity);
        }
        return list;
    }

    @Override
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdAndCreType(Integer wms_sys_dict_id,
                                                                                             String isEmpty,
                                                                                             String cre_type)
    {
        Map<String, Object> paMap = new HashMap<>();
        paMap.put("wms_sys_dict_id", wms_sys_dict_id);
        paMap.put("cre_type", cre_type);
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = new ArrayList<>();
        // 解决:当前台贷款产品选择"请选择"，单据状态只显示请选择
        if (!cre_type.equals("-1"))
        {
            list = wmssysdictdataDao_m.getDictDataByDictIdAndCreType(paMap);
        }
        if ("true".equals(isEmpty))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-1);
            entity.setValue_code("-1");
            entity.setValue_meaning("请选择");
            list.add(0, entity);
        }
        return list;
    }
    
    @Override
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmptyP(Integer wms_sys_dict_id,Integer p_wms_sys_dict_data_id,
                                                                                        String isEmpty, String isAll)
    {
    	Map<String, Object> parMap=new HashMap<>();
    	parMap.put("wms_sys_dict_id", wms_sys_dict_id);
    	parMap.put("p_wms_sys_dict_data_id", p_wms_sys_dict_data_id);
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m.getDictDataByDictIdP(parMap);
        if ("true".equals(isEmpty))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-1);
            entity.setValue_code("-1");
            entity.setValue_meaning("请选择");
            list.add(0, entity);
        }
        if ("true".equals(isAll))
        {
            com.zx.emanage.util.gen.entity.WmsSysDictData entity = new com.zx.emanage.util.gen.entity.WmsSysDictData();
            entity.setWms_sys_dict_data_id(-2);
            entity.setValue_meaning("全部");
            list.add(0, entity);
        }
        return list;
    }
    @Override
    public Map<String, Object> getDictDataCode(Integer wms_sys_dict_id,String value_code,
                                                                                        String isEmpty, String isAll)
    {
    	Map<String, Object> parMap=new HashMap<>();
    	parMap.put("wms_sys_dict_id", wms_sys_dict_id);
    	parMap.put("value_code", value_code);
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m.getDictDataCode(parMap);
        if(list!=null&&list.size()>0){
        	com.zx.emanage.util.gen.entity.WmsSysDictData wmsSysDictData=list.get(0);
        	parMap.clear();
        	parMap.put("cre_type_name", wmsSysDictData.getValue_meaning());
        }
        return parMap;
    }

	@Override
	public String getValMeaningBycodes(Integer wms_sys_dict_id, String valCodes) {
		if(valCodes != null) {
			String[] valCodeArrays = valCodes.split(",");
			List<String> valueCodes = java.util.Arrays.asList(valCodeArrays);
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("wms_sys_dict_id", wms_sys_dict_id);
			paramMap.put("valueCodes", valueCodes);
			List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m.getValMeaningBycodes(paramMap);
			if(list != null && list.size() != 0) {
				StringBuffer sb = new StringBuffer();
				for(com.zx.emanage.util.gen.entity.WmsSysDictData wmsSysDictData : list) {
					sb.append(wmsSysDictData.getValue_meaning()).append(",");
				}
				return sb.substring(0, sb.length() - 1);
			}
		}
		return "";
	}
	@Override
	public List<SysDeptDataBean> getAllSysStoresWithList(String isEmpty,Integer deptpId) {
		SysDept sysDept = new SysDept();
		sysDept.setDept_pid(deptpId);
		sysDept.setEnable_flag("1");
		List<SysDept> sysdeptids = sysDeptDao.getListByEntity(sysDept);
		List<SysDeptDataBean> list = new ArrayList<>();
		if("true".equals(isEmpty)){
			SysDeptDataBean sysbean = new SysDeptDataBean();
			sysbean.setStoresId(-1);
            sysbean.setStoresName("---请选择---");
			list.add(0, sysbean);
		}
		for(SysDept dept:sysdeptids){
			SysDeptDataBean sysbean = new SysDeptDataBean();
			sysbean.setStoresId(dept.getDept_id());
			sysbean.setStoresName(dept.getDept_name());
			list.add(sysbean);
		}
		return list;
	}
  
    /**
     * 通过valuecode 获取对应的值
     * @param wms_sys_dict_id
     * @param value_code
     * @return
     */
    @Override
    public Map<String, Object> getDictDataByCode(Integer wms_sys_dict_id,String value_code)
    {
    	Map<String, Object> parMap=new HashMap<>();
    	parMap.put("wms_sys_dict_id", wms_sys_dict_id);
    	parMap.put("value_code", value_code);
        List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m.getDictDataByCode(parMap);
        if(list!=null&&list.size()>0){
        	com.zx.emanage.util.gen.entity.WmsSysDictData wmsSysDictData=list.get(0);
        	parMap.clear();
        	parMap.put("certificate_type_name", wmsSysDictData.getValue_meaning());
        }
        return parMap;
    }

    /**
     * @Title: getDictionaryDataPad
     * @Description: pad请求获取数据字典详细内容
     * @param wms_sys_dict_id
     * @param p_wms_sys_dict_data_id
     * @return 
     * @author: Administrator
     * @time:2017年2月22日 下午6:31:06
     * @see com.zx.emanage.sysmanage.service.IWmsSysDictDataService#getDictionaryDataPad(java.lang.String, java.lang.String)
     * history:
     * 1、2017年2月22日 Administrator 创建方法
     */
    @Override
    public Map<String, Object> getDictionaryDataPad(String wms_sys_dict_id, String p_wms_sys_dict_data_id)
    {
        Map<String, Object> parMap = new HashMap<>();
        Map<String, Object> resMap = new HashMap<>();

        if (p_wms_sys_dict_data_id != null)
        {
            parMap.put("p_wms_sys_dict_data_id", p_wms_sys_dict_data_id);
            parMap.put("wms_sys_dict_id", wms_sys_dict_id);
            List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m.getDictDataByDictIdP(parMap);
            resMap.put(p_wms_sys_dict_data_id, list);

        }else{
            String[] dic_ids = wms_sys_dict_id.split(",");
            parMap.put("wms_sys_dict_id", wms_sys_dict_id);
            for (int i = 0; i < dic_ids.length; i++)
            {
                List<com.zx.emanage.util.gen.entity.WmsSysDictData> list = wmssysdictdataDao_m.getDictDataByDictIdP(parMap);
                resMap.put(wms_sys_dict_id, list);
            }
        }
        return resMap;
    }
}
