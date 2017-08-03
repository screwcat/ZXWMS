package com.zx.emanage.util.gen;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zx.emanage.sysmanage.persist.SysLogDao;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * @author gx 描述：是系统管理模块的工具类
 */
public class SysTools
{
    private static Logger log = LoggerFactory.getLogger(SysTools.class);

    public static String getIP(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (!checkIP(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip))
        {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip))
        {
            ip = "127.0.0.1";
        }
        return ip;
    }

    private static boolean checkIP(String ip)
    {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip) || ip.split("\\.").length != 4)
        {
            return false;
        }
        return true;
    }

    /**
     * 模糊匹配时将传入的参数的特殊字符(%\_)进行转义，并将前后增加通配符%
     * 
     * @param sqlParam
     * @return
     */
    public static String getSqlLikeParam(String sqlParam)
    {
        sqlParam = sqlParam.replace("\\", "\\\\");
        sqlParam = sqlParam.replace("%", "\\%");
        sqlParam = sqlParam.replace("_", "\\_");
        return "%" + sqlParam + "%";
    }

    /**
     * in查询时将传入的参数的特殊字符(%\_)进行转义，并将前后加上括号()
     * 
     * @param sqlParam
     * @return
     */
    public static String getSqlInParam(String sqlParam)
    {
        sqlParam = sqlParam.replace("\\", "\\\\");
        sqlParam = sqlParam.replace("%", "\\%");
        sqlParam = sqlParam.replace("_", "\\_");
        return "(" + sqlParam + ")";
    }
    
    public static void saveLog(HttpServletRequest request, String msg)
    {
        try
        {
            HttpSession session = request.getSession();
            UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);

            com.zx.emanage.util.gen.entity.SysLog bean = new com.zx.emanage.util.gen.entity.SysLog();
            SysLogDao sysLogDao = (SysLogDao) GlobalVal.ctx.getBean("syslogDao");

            // 插入日志表记录
            bean.setOper_behavior(msg);
            bean.setOper_ip(getIP(request));
            bean.setUser_name(user.getRealname());
            bean.setUser_code(user.getUserCode());
            bean.setUnit_name(user.getDeptSimpleName());
            bean.setOper_timestamp(new Timestamp(new Date().getTime()));
            bean.setOper_type("1");
            sysLogDao.save(bean);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
    }
    
    
  	/**
  	 * @Title: getReturnRatio 
  	 * @Description: (计算提成比例) 
  	 * 提成比例：主管 提成：提部门全部缴回违约金的2%
     *    	     贷后专员： 10之内缴回提违约金的10%
     *            	10天之后转上门，专员提违约金的2%,主管提违约金的2%,上门人员提10%
  	 * 
  	 * @param day
  	 * @param userType 0:贷后主管；1：贷后专员； 2：上门人员
  	 * @return    
  	 * @return float    返回类型
  	 * @throws
  	 * @author lvtu
  	 */
  	public static BigDecimal getReturnRatio(int day, int userType) {
  		/*
  		 * 提成比例：主管 提成：提部门全部缴回违约金的2%
          		     贷后专员 10之内缴回提违约金的10%
                   10天之后转上门，专员提违约金的2%,主管提违约金的2%,上门人员提10%
  		 */
  		String ratio = "0";
  		
  		if(userType == 0) {
  			ratio = "0.02";
  		} else {
  			if(day <= 10) {
  				if(userType == 1) {
  					ratio = "0.1";
  				}
  	  		} else {
  	  			if(userType == 1) {
					ratio = "0.02";
				} else if(userType == 2) {
					ratio = "0.1";
				}
  	  		}
  			
  		}
  		
  		return new BigDecimal(ratio);
  	}
  	
  	public static String getReturnRatio(BigDecimal ratio) {
  		return ratio.movePointRight(2) + "%";
  	}
  	
  	/**
  	 * @Title: getReturnAmount 
  	 * @Description: (计算缴回违约金额	缴回违约金=预期缴回金额-应还本金-应还利息) 
  	 * @param expectPaymentAmount 预期缴回金额
  	 * @param orgRepayPrincipal 应还本金
  	 * @param orgRepayInterest 应还利息
  	 * @return    
  	 * @return double    返回类型
  	 * @throws
  	 * @author lvtu
  	 */
  	public static BigDecimal getReturnAmount(BigDecimal expectPaymentAmount, BigDecimal orgRepayPrincipal, BigDecimal orgRepayInterest) {
  		if(expectPaymentAmount == null) {
  			expectPaymentAmount = new BigDecimal(0);
  		}
  		BigDecimal returnAmount = expectPaymentAmount.subtract(orgRepayPrincipal).subtract(orgRepayInterest);
  		//compareTo()返回值为int类型,-1表示小于,0是等于,1是大于
  		int i = returnAmount.compareTo(new BigDecimal(0));
  		//判断缴回违约金与预期缴回金额对比，如果相等说明 应还本金+应还利息=0，数据异常
  		int j = returnAmount.compareTo(expectPaymentAmount);
  		if(i == -1 || i == 0 || j == 0) {
  			returnAmount = new BigDecimal(0);
  		}
  		return returnAmount;
  	}
  	
  	/**
  	 * @Title: returnOmmission 
  	 * @Description: (计算提成金额	提成金额 = 缴回违约金*提成比例) 
  	 * @param returnAmount
  	 * @param ratio
  	 * @return    
  	 * @return BigDecimal    返回类型
  	 * @throws
  	 * @author lvtu
  	 */
  	public static BigDecimal getReturnOmmission(BigDecimal returnAmount, BigDecimal ratio) {
  		return returnAmount.multiply(ratio);
  	}
  	
  	
  	/**
  	 * 将一个 Map 对象转化为一个 JavaBean
  	 * @Title: convertMap 
  	 * @Description: 
  	 * @param type
  	 * @param map
  	 * @return
  	 * @throws IntrospectionException
  	 * @throws IllegalAccessException
  	 * @throws InstantiationException    
  	 * @return Object    
  	 * @throws
  	 * @author lvtu 
  	 * @date 2015年8月17日 下午1:28:19
  	 */
    public static Object convertMap(Class<?> type, Map map) throws IntrospectionException, IllegalAccessException,
            InstantiationException {
    	
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                try {
					Object value = map.get(propertyName);

					Object[] args = new Object[1];
					args[0] = value;

					descriptor.getWriteMethod().invoke(obj, args);
					
				} catch (IllegalArgumentException e) {
				} catch (InvocationTargetException e) {
				}
            }
        }
        return obj;
    }
    
    
    /**
     * 将一个 JavaBean 对象转化为一个  Map
     * @Title: convertBean 
     * @Description: 
     * @param bean
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException    
     * @return Map    
     * @throws
     * @author lvtu 
     * @date 2015年8月17日 下午1:28:42
     */
    public static Map convertBean(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class<?> type = bean.getClass();
        Map returnMap = new HashMap();
        //希望提供有关其 bean 的显式信息的 bean 实现者可以提供某个 BeanInfo 类，该类实现此 BeanInfo 接口并提供有关其 bean 的方法、属性、事件等显式信息。 
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        //获取属性集合
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
            	//获得应该用于读取属性值的方法。
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
    /**
     * 集中处理屏蔽信息
     */
    public static List<Map<String,Object>> setListView(List<Map<String,Object>> list,UserBean uBean,List<SysSpecialUser> specilaUsers){
    	if(list !=null && list.size()>0){
    		 //存在特批人 只有特批人能看到
            if(specilaUsers!=null && specilaUsers.size()>0){
        		 for(SysSpecialUser sys:specilaUsers){
     	        	if(!uBean.getUserId().equals(sys.getPersonnel_id())){
     	        		 for(Map<String,Object> map :list){
     	        			 	//判断身份证号和手机号是否存在
     	        			 	String idCard=(String)map.get("id_card");
     	        			 	String credit_idCard = (String)map.get("credit_id_card");
     	        			 	String mobile_phone =(String)map.get("mobile_phone"); 
     	        			 	if(idCard!=null){
     	        			 		map.remove("id_card");
     	        			 		if(!idCard.equals("")&&idCard.length()==18){
                                    map.put("id_card", idCard.substring(0, 4) + "********" + idCard.substring(14, idCard.length()));
     	        			 		}else if(!idCard.equals("")&&idCard.length()==8){
                                    map.put("id_card", idCard.substring(0, 4) + "********" + idCard.substring(4, idCard.length()));
     	        			 		}else if(!idCard.equals("")){
     	        			 			map.put("id_card","********");
     	        			 		}
     	        			 	}
     	        			 	if(credit_idCard != null){
                                    map.remove("credit_id_card");
                                    if(!credit_idCard.equals("") && credit_idCard.length() == 18){
                                    map.put("credit_id_card", credit_idCard.substring(0, 4) + "********" + credit_idCard.substring(14, credit_idCard.length()));
                                    }else if(!credit_idCard.equals("")){
                                        map.put("credit_id_card","********");
                                    }
                                }
     	        			 	if(mobile_phone !=null){
     	        			 		map.remove("mobile_phone");
     	        			 		if(!mobile_phone.equals("")&&mobile_phone.length()==11){
                                    map.put("mobile_phone", mobile_phone.substring(0, 3) + "******" + mobile_phone.substring(7, mobile_phone.length()));
     	        			 		}else if(!mobile_phone.equals("")){
     	        			 			map.put("mobile_phone","******");
     	        			 		}
     	        			 	}
     	        			 	map.put("inkey", 0);
     	        	      }
     	        		 break;
     	        	}
     	        }
        	}else{
        		//不存在特批人 任何人都看不到信息
        		for(Map<String,Object> map :list){
     			 	//判断身份证号和手机号是否存在
     			 	String idCard=(String)map.get("id_card");
     			 	String mobile_phone =(String)map.get("mobile_phone"); 
     			 	if(idCard!=null){
     			 		map.remove("id_card");
     			 		if(!idCard.equals("")&&idCard.length()==18){
                            map.put("id_card", idCard.substring(0, 4) + "********" + idCard.substring(14, idCard.length()));
     			 		}else if(!idCard.equals("")){
     			 			map.put("id_card","********");
     			 		}
     			 	}
     			 	if(mobile_phone !=null){
     			 		map.remove("mobile_phone");
     			 		if(!mobile_phone.equals("")&&mobile_phone.length()==11){
                            map.put("mobile_phone", mobile_phone.substring(0, 3) + "******" + mobile_phone.substring(7, mobile_phone.length()));
     			 		}else if(!mobile_phone.equals("")){
     			 			map.put("mobile_phone","******");
     			 		}
     			 	}
     			 	map.put("inkey", 0);
     	      }
        	}
    	}
    	return list;
    }
    
    /**
     * 判断当前用户是否包含特批人
     */
    public static boolean isSpecialUser(List<SysSpecialUser> specilaUsers, UserBean uBean){
        if(specilaUsers != null && specilaUsers.size() > 0){
             for(SysSpecialUser sys : specilaUsers){
                if(uBean.getUserId().equals(sys.getPersonnel_id())){
                     return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 将一个 JavaBean对象转化为一个Map
     * @Title: ConvertObjToMap 
     * @Description: 
     * @param Object obj
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException    
     * @return Map<String,Object> resMap    
     * @throws
     * @author wangyihan 
     * @date 2015年12月3日
     */
    public static Map<String,Object> ConvertObjToMap(Object obj){
        Map<String,Object> resMap = new HashMap<String,Object>();
        if (obj == null) {
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for(int i=0;i<fields.length;i++){
                try {
                    Field f = obj.getClass().getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    resMap.put(fields[i].getName(), o);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } 
        return resMap;
    }
    
}
