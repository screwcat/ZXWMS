package com.zx.sframe.util;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @ClassName: ValidFormat
 * @Description: 内容摘要：
 * @author wangyihan
 * @date 2016年11月30日
 * @version V1.0
 * history:
 * 1、2016年11月30日 wangyihan 创建文件
 */
public class ValidFormat {
    
	/**
	 * 校验身份证号
	 * @throws ParseException 
	 */
	public static boolean isIdCard(String value) throws ParseException {
		value = value.trim();
    	String regex = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])"; 
        if (!Pattern.matches(regex, value)) {
            return false;
        }
		return true;
	}
	
	/**
	 * 校验移动电话
	 */
	public static boolean isMobilePhone(String str) {
		Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        // 验证手机号
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");   
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
	}
	
	/**
	 * 
	 * @Title: isNumWithLine
	 * @Description: TODO(校验是否为数字：可包含'-')
	 * @param str
	 * @return 
	 * @author: Administrator
	 * @time:2016年11月30日 上午9:02:13
	 * history:
	 * 1、2016年11月30日 wangyihan 创建方法
	 */
	public static boolean isNumWithLine(String str) {
	    Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("[0-9]*-?[0-9]*");   
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
	}
	
	public static void main(String args[]) {
	    System.out.println(ValidFormat.isNumWithLine("04122564-7492"));
	}
	
	
	/**
	 * 校验固定电话
	 */
	public static boolean isPhone(String str) {   
        Pattern p1 = null,p2 = null;  
        Matcher m = null;  
        boolean b = false;   
        // 验证带区号的
        p1 = Pattern.compile("^[0][1-9]{2,3}-?[0-9]{5,10}$");
        // 验证没有区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");           
        if(str.length() > 9) {   
        	m = p1.matcher(str);  
            b = m.matches();    
        } else {  
            m = p2.matcher(str);  
            b = m.matches();   
        }    
        return b;  
    }
	
	private static boolean isMatch(String regex, String orginal) {  
        Pattern pattern = Pattern.compile(regex);  
        Matcher isNum = pattern.matcher(orginal);  
        return isNum.matches();  
    }  
  
	/**
	 * 校验正整数
	 */
    public static boolean isPositiveInteger(String orginal) {  
        return isMatch("^[1-9]+[0-9]*$", orginal);  
    }  
  
    /**
	 * 校验负整数
	 */
    public static boolean isNegativeInteger(String orginal) {  
        return !isMatch("^-[1-9]\\d*", orginal);  
    }  
  
    
    public static boolean isWholeNumber(String orginal) {  
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);  
    }  
      
    /**
	 * 校验正小数
	 */
    public static boolean isPositiveDecimal(String orginal) {  
        return isMatch("\\d+\\.\\d+$|-\\d+\\.\\d+$", orginal);  
    }  
      
    /**
	 * 校验负小数
	 */
    public static boolean isNegativeDecimal(String orginal) {  
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);  
    }  
      
    public static boolean isDecimal(String orginal) {  
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);  
    }  
      
    /**
	 * 校验实数
	 */
    public static boolean isRealNumber(String orginal){  
        return isWholeNumber(orginal) || isDecimal(orginal);  
    }
	
	/**
	 * 校验银行卡号
	 */
    public static boolean isCardNo(String card_no) {
    	if(StringUtils.isEmpty(card_no)) {
    		return false;
    	}
    	card_no = card_no.replace(" ", "");//去除空格
    	if(!isPositiveInteger(card_no)) {
    		return false;
    	}
    	if(card_no.length() < 15 || card_no.length() > 19) {
            return false;
        }
        /*char bit = getBankCardCheckCode(card_no.substring(0, card_no.length() - 1));  
        if(bit == 'N') {  
            return false;  
        }  
        return card_no.charAt(card_no.length() - 1) == bit;*/
    	return true;
    }
    
    /** 
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位 
     * @param nonCheckCodeBankCard 
     * @return 
     */  
    public static char getBankCardCheckCode(String nonCheckCodeBankCard) {  
        if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0  
                || !nonCheckCodeBankCard.matches("\\d+")) {  
            //如果传的不是数据返回N  
            return 'N';  
        }  
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();  
        int luhmSum = 0;  
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {  
            int k = chs[i] - '0';  
            if(j % 2 == 0) {  
                k *= 2;  
                k = k / 10 + k % 10;  
            }  
            luhmSum += k;             
        }  
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');  
    }
    
}
