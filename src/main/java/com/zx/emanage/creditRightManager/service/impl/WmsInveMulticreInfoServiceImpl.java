package com.zx.emanage.creditRightManager.service.impl;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zx.emanage.creditRightManager.persist.WmsInveCreditCRPackageDao;
import com.zx.emanage.creditRightManager.persist.WmsInveMulticreInfoDao;
import com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService;
import com.zx.emanage.creditRightManager.service.WmsInveMulticreInfoService;
import com.zx.emanage.creditRightManager.utils.ReadExcel;
import com.zx.emanage.creditRightManager.vo.WmsInveMulticreInfoVO;
import com.zx.emanage.inve.persist.WmsInveCreditLimitDao;
import com.zx.emanage.util.gen.vo.WmsInveOfflineCreditImportTemp;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName: WmsInveMulticreInfoServiceImpl
 * @Description: 内容摘要：债权采集实现类
 * @author WangShuai
 * @date 2017年2月8日
 * @version V1.0
 * history:
 * 1、2017年2月8日 WangShuai 创建文件
 */
@Service("wmsInveMulticreInfoService")
public class WmsInveMulticreInfoServiceImpl implements WmsInveMulticreInfoService {
	
	@Autowired
	private WmsInveCreditCRPackageDao wmsInveCreditCRPackageDao;
	@Autowired
	WmsInveMulticreInfoDao wmsInveMulticreInfoDao ;

    @Autowired
    private WmsInveCreditLimitDao wmsInveCreditLimitDao;

    @Autowired
    private IWmsInveCreditPackageService wmsInveCreditPackageService;

	/**
	 * @Title: creditCollectionlistall
	 * @Description: 债权采集查询
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月8日 上午10:44:55
	 * history:
	 * 1、2017年2月8日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> creditCollectionlistall(
			WmsInveMulticreInfoVO wmsInveMulticreInfoVO) {
		//参数处理
		Map<String,Object> para=new HashMap<String,Object>();
		para.put("cre_type", wmsInveMulticreInfoVO.getCre_type());
		para.put("cre_whole_mon_tt_low", wmsInveMulticreInfoVO.getCre_whole_mon_tt_low());
		para.put("cre_whole_mon_tt_high", wmsInveMulticreInfoVO.getCre_whole_mon_tt_high());
		para.put("create_time_start", wmsInveMulticreInfoVO.getCreate_time_start());
		para.put("create_time_end", wmsInveMulticreInfoVO.getCreate_time_end());
		para.put("multicre_state", wmsInveMulticreInfoVO.getMulticre_state());
		para.put("page",wmsInveMulticreInfoVO.getPage());
		para.put("pageSize",wmsInveMulticreInfoVO.getPagesize());
		
		para.put("sortname",wmsInveMulticreInfoVO.getSortname());
		para.put("sortorder",wmsInveMulticreInfoVO.getSortorder());
		
		//查询结果
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        list= wmsInveMulticreInfoDao.creditCollectionlistall(para); 
        
        GridDataBean<Map<String,Object>> bean = new GridDataBean<Map<String,Object>>(wmsInveMulticreInfoVO.getPage(), wmsInveMulticreInfoDao.creditCollectionlistall_findcount(para),list);
		
        return bean.getGridData();
	}
	
	/**
	 * @Title: doCreditRightExcelImport
	 * @Description: 债权采集导入
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月8日 上午10:44:55
	 * history:
	 * 1、2017年2月8日 WangShuai 创建方法
	*/
	@Override 
	public Map<String, Object> doCreditRightExcelImport(String name,MultipartFile file){
		Map<String,Object> res = new HashMap<String,Object>();
		//获取转换用Map
		List<Map<String,Object>> localist=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> oplist=new ArrayList<Map<String,Object>>();
		//先获取两个List
		localist=wmsInveCreditCRPackageDao.getAllLocalNumList();
		oplist=wmsInveCreditCRPackageDao.getAllRelePerList();
		//定义Map并进行转换
		Map<String,Object> localMap=new HashMap<String,Object>();
		Map<String,Object> opMap=new HashMap<String,Object>();
		
		localMap=doListChangeMap(localist,"region_name","loca_num");
		opMap=doListChangeMap(oplist,"personnel_name","rele_per_id");
		
		//创建处理EXCEL
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取客户信息集合。
        List<Map<String,Object>> customerList = readExcel.getExcelInfo(name ,file);
        
        List<Map<String,Object>> resList=new ArrayList<Map<String,Object>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //处理数据，因为他项人和地址都是中文，需要改成编码
        if(null!=customerList&&customerList.size()>0){
        	for(int i=0;i<customerList.size();i++){
        		Map<String,Object> temp=customerList.get(i);
        		String loca_num=temp.get("loca_num")==null?"":temp.get("loca_num").toString();
        		String rele_per_id=temp.get("rele_per_id")==null?"":temp.get("rele_per_id").toString();
        		
        		temp.put("loca_num", localMap.get(loca_num));
        		temp.put("rele_per_id", opMap.get(rele_per_id));
        		if(null!=temp.get("crepg_start_date")){
        			temp.put("crepg_start_date", sdf.format((Date)temp.get("crepg_start_date")));
        		}
        		
        		if(null!=temp.get("crepg_end_date")){
        			temp.put("crepg_end_date", sdf.format((Date)temp.get("crepg_end_date")));
        		}
        		
        		resList.add(temp);
        	}
        	
        }
        
        if(customerList != null){
        	res.put("rows",resList);
            return res;
        }else{
        	return null;
        }
		
	}
	
	/**
	 * @Title: doListChangeMap
	 * @Description: 将List转换为Map
	 * @param oplist
	 * @param string
	 * @param string2
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月21日 下午5:23:56
	 * history:
	 * 1、2017年2月21日 WangShuai 创建方法
	*/
	private Map<String, Object> doListChangeMap(
			List<Map<String, Object>> oplist, String key, String val) {
		Map<String,Object> res=new HashMap<String,Object>();
		for(int i=0;i<oplist.size();i++){
			Map<String, Object> temp=new HashMap<String,Object>();
			temp=oplist.get(i);
			res.put(temp.get(key).toString(), temp.get(val));
		}
		return res;
	}

	/**
	 * @Title: wmsCredRightColData
	 * @Description:获取单据新增初始化数据
	 * @param user
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月21日 上午10:41:58
	 * history:
	 * 1、2017年2月21日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> wmsCredRightColData(UserBean user) {
		int personnel_id=user.getUserId();
		
		Map<String,Object> para=new HashMap<String,Object>();
		para.put("personnel_id", personnel_id);
		//获取编号，人员信息 时间等
		Map<String,Object> res=wmsInveMulticreInfoDao.getWmsCredRightColData(para);
		//获取地区和他项人列表
		List<Map<String,Object>> localist=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> oplist=new ArrayList<Map<String,Object>>();
		
		localist=wmsInveCreditCRPackageDao.getAllLocalNumList();
		oplist=wmsInveCreditCRPackageDao.getAllRelePerList();
		
		res.put("localist", localist);
		res.put("oplist", oplist);
		
		
		return res;
	}
	
	/**
	 * @Title: saveWmsCreditRightCollAddInfo
	 * @Description: 保存在全采集包
	 * @param user
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月21日 下午12:01:39
	 * history:
	 * 1、2017年2月21日 WangShuai 创建方法
	*/
    @Transactional
	@Override
	public Map<String, Object> saveWmsCreditRightCollAddInfo(UserBean user,
			WmsInveMulticreInfoVO wmsInveMulticreInfoVO) {
		Map<String,Object> res=new HashMap<String,Object>();
		//操作采集信息
		int personnel_id=user.getUserId();
		String multicre_state=wmsInveMulticreInfoVO.getMulticre_state();
		Map<String,Object> para=new HashMap<String,Object>();
		para.put("personnel_id", personnel_id);
		para.put("col_code", wmsInveMulticreInfoVO.getCol_code());
		para.put("multicre_state",multicre_state);
		para.put("cre_type",wmsInveMulticreInfoVO.getCre_type());
		para.put("personnel_id",personnel_id);
		para.put("enable_flag","1");
		
		//页面设置的值
		List<WmsInveMulticreInfoVO> configs=JsonUtil.jsonArrayToList(wmsInveMulticreInfoVO.getAllConfigInfo(), WmsInveMulticreInfoVO.class);
		//处理configs 中的日期
		List<WmsInveMulticreInfoVO> newc=new ArrayList<WmsInveMulticreInfoVO>();
		if(null!=configs&&configs.size()>0){
			for(int i=0;i<configs.size();i++){
				WmsInveMulticreInfoVO wmv=new WmsInveMulticreInfoVO();
				wmv=configs.get(i);
				if(StringUtil.isEmpty(wmv.getCre_pledge_mon())){
					wmv.setCre_pledge_mon(null);
				}
				if(StringUtil.isEmpty(wmv.getHouse_size())){
					wmv.setHouse_size(null);
				}
				if(StringUtil.isEmpty(wmv.getCrepg_start_date())){
					wmv.setCrepg_start_date(null);
				}
				if(StringUtil.isEmpty(wmv.getCrepg_end_date())){
					wmv.setCrepg_end_date(null);
				}
				if(StringUtil.isEmpty(wmv.getRele_per_id())){
					wmv.setRele_per_id(null);
				}
				newc.add(wmv);
			}
		}
		//当不是保存为草稿的时候进行判断合同号是否存在
		if("1".equals(multicre_state)){
			//判断合同号是否在表中存在，如果存在，如果存在则要返回错误
			if(newc.size()>0){
				Map<String,Object> tpm=new HashMap<String,Object>();
				tpm.put("alconf", newc);
				List<Map<String,Object>> restl=wmsInveMulticreInfoDao.checkProtocolNumIsExist(tpm);
				if(null!=restl&&restl.size()>0){
					String ptn="";
					for(int i=0;i<restl.size();i++){
						Map<String,Object> tp=new HashMap<String,Object>();
						tp=restl.get(i);
						if(StringUtils.isNotEmpty(String.valueOf(tp.get("protocol_id_year_num")))){
							ptn=ptn+String.valueOf(tp.get("protocol_id_year_num"))+",";
						}
					}
					if(ptn.length()>0){
						res.put("result", "fail");
						res.put("msg",ptn);
						return res;
					}
				}
			}
            // jinzhm 2017-04-08添加 不是草稿状态下将该地区的债权匹配限制清空
            if (!newc.isEmpty())
            {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("userId", user.getUserId());
                wmsInveCreditLimitDao.updateCreditLimitDataByLocalNum(paramMap);
            }
		}
		//现获取指定采集编号的债权采集包是否存在，如果存在则现删除对应的2个表的数据
		Map<String,Object> tpMulMap=wmsInveMulticreInfoDao.getWmsInveMulticreInfo(para);
		if(null!=tpMulMap){
			wmsInveMulticreInfoDao.deleteWmsInveMulticreById(tpMulMap);
			wmsInveMulticreInfoDao.deleteWmsInvePkgById(tpMulMap);
		}
		//插入数据
		wmsInveMulticreInfoDao.insertWmsInveMulticreInfo(para);
		//操作抵押包信息
		Map<String,Object> pakpara=new HashMap<String,Object>();
		pakpara.put("create_user_id", personnel_id);
		pakpara.put("enable_flag", multicre_state);
		pakpara.put("wms_inve_multicre_info_id", para.get("wms_inve_multicre_info_id"));
		pakpara.put("cre_type",wmsInveMulticreInfoVO.getCre_type());
		
		pakpara.put("al", newc);
		if(null!=configs&&configs.size()>0){
			wmsInveMulticreInfoDao.insertWmsInveCreditPackage(pakpara);
			//根据债权包，更新本次采集信息
			wmsInveMulticreInfoDao.updateCurrMultiInfo(pakpara);
		}
		
		
		res.put("result", "success");
		return res;
	}
	
	/**
	 * @Title: getWmsCredRightColDataList
	 * @Description: 债权采集详情页面
	 * @param user
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月21日 下午3:09:33
	 * history:
	 * 1、2017年2月21日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> getWmsCredRightColDataList(UserBean user,
			WmsInveMulticreInfoVO wmsInveMulticreInfoVO) {
		Map<String,Object> para=new HashMap<String,Object>();
		para.put("wms_inve_multicre_info_id", wmsInveMulticreInfoVO.getWms_inve_multicre_info_id());
		Map<String,Object> res=new HashMap<String,Object>();
		//债权采集MAP
		res = wmsInveMulticreInfoDao.getWmsInveMulticreInfoDetail(para);
		//抵押包列表
		List<Map<String,Object>> paklist=new ArrayList<Map<String,Object>>();
		paklist = wmsInveMulticreInfoDao.getWmsInveCreditPackageListByMK(para);
		
		res.put("paklist", paklist);
		
		return res;
	}

	/**
	 * @Title: wmsCredRightColDataEdit
	 * @Description: 采集包编辑页面信息获取
	 * @param user
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月22日 下午5:03:49
	 * history:
	 * 1、2017年2月22日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> wmsCredRightColDataEdit(UserBean user,
			WmsInveMulticreInfoVO wmsInveMulticreInfoVO) {
		int personnel_id=user.getUserId();
		Map<String,Object> para=new HashMap<String,Object>();
		//获取地区和他项人列表
		List<Map<String,Object>> localist=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> oplist=new ArrayList<Map<String,Object>>();
		
		localist=wmsInveCreditCRPackageDao.getAllLocalNumList();
		oplist=wmsInveCreditCRPackageDao.getAllRelePerList();
		
		para.put("wms_inve_multicre_info_id", wmsInveMulticreInfoVO.getWms_inve_multicre_info_id());
		Map<String,Object> res=new HashMap<String,Object>();
		//债权采集MAP
		res = wmsInveMulticreInfoDao.getWmsInveMulticreInfoDetail(para);
		//抵押包列表
		List<Map<String,Object>> paklist=new ArrayList<Map<String,Object>>();
		paklist = wmsInveMulticreInfoDao.getWmsInveCreditPackageListByMK(para);
		
		res.put("paklist", paklist);
		res.put("localist", localist);
		res.put("oplist", oplist);
		
		return res;
	}

    /**
     * @Title: handleOfflineImportCredit
     * @Description: 处理线下导入债权
     *      处理wms_inve_offline_credit_import_temp表中的enable_flag为1的数据
     * @param user 
     * @author: jinzhm
     * @time:2017年4月11日 上午8:52:51
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#handleOfflineImportCredit(com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年4月11日 jinzhm 创建方法
    */
    @Transactional
    @Override
    public void handleOfflineImportCredit(UserBean user)
    {
        // 获得要导入的线下债权匹配数据集合
        List<WmsInveOfflineCreditImportTemp> offlineCreditList = wmsInveCreditCRPackageDao.queryOfflineCredit();
        // 如果没有要处理的线下债权匹配数据，直接返回
        if (offlineCreditList.isEmpty())
        {
            return;
        }

        // 封装保存批次信息
        Map<String, Object> multicreInfoMap = new HashMap<String, Object>();
        // 设置债权类型为可拆分类型
        multicreInfoMap.put("cre_type", "4");
        multicreInfoMap.put("personnel_id", user.getUserId());
        // 设置批次状态为已匹配
        multicreInfoMap.put("multicre_state", "1");
        multicreInfoMap.put("enable_flag", "1");

        // 获取并设置批次编号
        Map<String, Object> multicreColCodeInfoMap = wmsInveMulticreInfoDao.getWmsCredRightColData(multicreInfoMap);
        multicreInfoMap.put("col_code", multicreColCodeInfoMap.get("col_code"));
        // 保存批次信息
        wmsInveMulticreInfoDao.insertWmsInveMulticreInfo(multicreInfoMap);

        // 标记是否需要删除采集批次信息
        boolean flag = true;
        // 错误计数
        int count = 0;
        // 循环所有线下债权匹配数据
        for (WmsInveOfflineCreditImportTemp offlineCredit : offlineCreditList)
        {
            try
            {
                int returnFlag = wmsInveCreditPackageService.handleOfflineImportCredit(offlineCredit,
                                                                                       ((Long) multicreInfoMap.get("wms_inve_multicre_info_id")).intValue(),
                                                                                       user);
                // 如果有导入未使用库中抵押包，使用抵押包信息进行了导入
                if(returnFlag == 0)
                {
                    // 将标记是否删除采集批次信息设置成false
                    flag = false;
                }
            }
            catch (Exception e)
            {
                count++;
                continue;
            }
        }
        // 如果没有成功数据
        if (count == offlineCreditList.size())
        {
            throw new RuntimeException("所有数据处理都出现错误，全部回滚！");
        }
        // 如果有成功数据
        else
        {
            // 需要删除采集批次信息
            if(flag)
            {
                // 删除插入的采集批次信息
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("wms_inve_multicre_info_id", multicreInfoMap.get("wms_inve_multicre_info_id"));
                wmsInveMulticreInfoDao.deleteWmsInveMulticreById(param);
            }
            // 不需要删除采集批次信息
            else
            {
                // 更新批次抵押包金额
                Map<String, Object> pakpara = new HashMap<String, Object>();
                pakpara.put("wms_inve_multicre_info_id", multicreInfoMap.get("wms_inve_multicre_info_id"));
                wmsInveMulticreInfoDao.updateCurrMultiInfo(pakpara);
            }
        }
    }

}
