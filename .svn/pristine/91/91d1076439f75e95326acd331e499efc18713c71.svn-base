package com.zx.emanage.util.gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysConstant 
 * @Description: 公有静态属性类
 * @author lvtu
 * @date 2015年6月30日 上午11:38:12 
 *
 */
public class SysConstant {
	//房贷平台费率简称
	public static final String FD_PTFL = "FD_PTFL";
	//房贷贷款利率简称
	public static final String FD_DKLL = "FD_DKLL";
	
	
	//平台费率3%
	public static final String PTFL_3 = "0.03";
	//平台费率3.5%
	public static final String PTFL_3_5 = "0.035";
	//平台费率5%
	public static final String PTFL_5 = "0.05";
	//平台费率2.5%
	public static final String PTFL_2_5 = "0.025";
	
	
	//贷款利率1.39%
	public static final String DKLL_1_39 = "0.0139";
	//贷款利率1.45%
	public static final String DKLL_1_45 = "0.0145";
	//贷款利率1.49%
	public static final String DKLL_1_49 = "0.0149";
	//贷款利率1.51%
	public static final String DKLL_1_51 = "0.0151";
	//贷款利率1.59%
	public static final String DKLL_1_59 = "0.0159";
	//贷款利率1.69%
	public static final String DKLL_1_69 = "0.0169";
	//贷款利率1.79%
	public static final String DKLL_1_79 = "0.0179";
	//贷款利率2.19%
	public static final String DKLL_2_19 = "0.0219";
	
	
	//贷款期限3期
	public static final String DKQX_3 = "3";
	//贷款期限6期
	public static final String DKQX_6 = "6";
	//贷款期限12期
	public static final String DKQX_12 = "12";
	//贷款期限24期
	public static final String DKQX_24 = "24";
	//贷款期限36期
	public static final String DKQX_36 = "36";
	//贷款期限48期
	public static final String DKQX_48 = "48";
	
	
	//他项费100
	public static final String TXF = "100";
	//加急费200
	public static final String JJF = "200";
	
	
	//卓车贷（押车）贷款利率
	public static final String CD_DKLL_YCXX = "0.035";
	//卓车贷-等额（不押车）
	public static final String CD_DKLL_BYCDE = "0.0245";
	//卓车贷-先息（不押车）
	public static final String CD_DKLL_BYCXX = "0.045";
	
	//GPS安装费
	public static final String GPS_FEE = "1200";
	
	
	/**
	 * @Title: getFD1HInfo 
	 * @Description: 根据字典表定义的产品code获取相应的map
	 * @return    
	 * @return Map    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月2日 上午11:37:50
	 */
	public static Map<String, Object> getFDInfo(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(code != null && !"-1".equals(code)) {
			if("116".equals(code)) { //房贷1号
				map.put(FD_PTFL, PTFL_3);
				map.put(FD_DKLL, DKLL_1_49);
			} else if("117".equals(code)) {	//房贷2号
				map.put(FD_PTFL, PTFL_5);
				map.put(FD_DKLL, DKLL_1_49);
			} else if("319".equals(code)) {	//房贷3号
				map.put(FD_PTFL, PTFL_2_5);
				map.put(FD_DKLL, DKLL_2_19);
			} else if("320".equals(code)) {	//房贷4号
				map.put(FD_PTFL, PTFL_2_5);
				map.put(FD_DKLL, DKLL_1_45);
			} else if("785".equals(code)) {	//新房贷1号
				map.put("newFD", setNewFd1());
			} else if("786".equals(code)) {	//新房贷2号
				map.put("newFD", setNewFd2());
			}
			map.put("TXF", TXF);
			map.put("JJF", JJF);
		}
		return map;
	}
	
	/*
	 *	设置新房贷产品1信息 
	 */
	private static List<Map<String, Object>> setNewFd1() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("DKQX", DKQX_3);
		map0.put(FD_PTFL, PTFL_3_5);
		map0.put(FD_DKLL, DKLL_1_49);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("DKQX", DKQX_6);
		map1.put(FD_PTFL, PTFL_5);
		map1.put(FD_DKLL, DKLL_1_49);
		//贷款期限12期
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("DKQX", DKQX_12);
		map2.put(FD_PTFL, PTFL_3);
		map2.put(FD_DKLL, DKLL_1_51);
		
		list.add(map0); 
		list.add(map1);
		list.add(map2); 
		return list;
	}
	
	/*
	 *	设置新房贷产品2信息 
	 */
	private static List<Map<String, Object>> setNewFd2() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("DKQX", DKQX_12);
		map0.put(FD_PTFL, PTFL_2_5);
		map0.put(FD_DKLL, DKLL_1_69);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("DKQX", DKQX_24);
		map1.put(FD_PTFL, PTFL_2_5);
		map1.put(FD_DKLL, DKLL_1_59);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("DKQX", DKQX_36);
		map2.put(FD_PTFL, PTFL_2_5);
		map2.put(FD_DKLL, DKLL_1_49);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("DKQX", DKQX_48);
		map3.put(FD_PTFL, PTFL_2_5);
		map3.put(FD_DKLL, DKLL_1_39);
		list.add(map0); 
		list.add(map1); 
		list.add(map2); 
		list.add(map3); 
		return list;
	}
	
	/**
	 * @Title: getCDInfo 
	 * @Description: 车贷信息
	 * @param code
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月7日 上午9:40:33
	 */
	public static Map<String, Object> getCDInfo(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(code != null && !"-1".equals(code)) {
			if("1".equals(code)) {
				map.put("CD_DKLL", CD_DKLL_YCXX);
				map.put("GPS_FEE", "0");
			} else if("2".equals(code)) {
				map.put("CD_DKLL", CD_DKLL_BYCDE);
				map.put("GPS_FEE", GPS_FEE);
			} else if("3".equals(code)) {
				map.put("CD_DKLL", CD_DKLL_BYCXX);
				map.put("GPS_FEE", GPS_FEE);
			} 
		}
		return map;
	}
}
