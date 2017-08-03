package com.zx.emanage.telUserLoanInfo.web;



import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.telUserLoanInfo.service.ItelUserLoanInfoService;
import com.zx.emanage.telUserLoanInfo.vo.TelUserInfoBeanVO;


@Controller
public class TelUserLoanInfoController {
	@Autowired
	private ItelUserLoanInfoService teluserloaninfoService;
	/**
	 * 根据客户姓名身份证号码  手机号码查询用户借贷情况
	 * @param telUserInfoBeanVO
	 * @return
	 * baisong
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/telUserLoanInfo/getInfo.dos",method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getInfo(TelUserInfoBeanVO telUserInfoBeanVO){
		/*try{
			telUserInfoBeanVO.setCustomer_name(new String(telUserInfoBeanVO.getCustomer_name().getBytes("iso8859-1"),"UTF-8"));
		}catch (Exception e){
			e.printStackTrace();
		}*/
		return teluserloaninfoService.getInfo(telUserInfoBeanVO);
	}
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--借贷金额
	 * @param map
	 * @return Map
	 * baisong
	 */
	@RequestMapping(value="/telUserLoanInfo/searchisum.dos",method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> searchisum(TelUserInfoBeanVO telUserInfoBeanVO) {
		/*try{
			telUserInfoBeanVO.setCustomer_name(new String(telUserInfoBeanVO.getCustomer_name().getBytes("iso8859-1"),"UTF-8"));
		}catch (Exception e){
			e.printStackTrace();
		}*/
		return teluserloaninfoService.searchisum(telUserInfoBeanVO);
	}
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--还款中单据数量
	 * @param map
	 * @return Map
	 * baisong
	 */
	@RequestMapping(value="/telUserLoanInfo/searchnum.dos",method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> searchnum(TelUserInfoBeanVO telUserInfoBeanVO) {
		/*try{
			telUserInfoBeanVO.setCustomer_name(new String(telUserInfoBeanVO.getCustomer_name().getBytes("iso8859-1"),"UTF-8"));
		}catch (Exception e){
			e.printStackTrace();
		}*/
		return teluserloaninfoService.searchnum(telUserInfoBeanVO);
	}
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--期还款台账
	 * @param map
	 * @return Map
	 * baisong
	 */
	@RequestMapping(value="/telUserLoanInfo/searchPeriodRepay.dos",method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> searchPeriodRepay(TelUserInfoBeanVO telUserInfoBeanVO) {
		/*try{
			telUserInfoBeanVO.setCustomer_name(new String(telUserInfoBeanVO.getCustomer_name().getBytes("iso8859-1"),"UTF-8"));
		}catch (Exception e){
			e.printStackTrace();
		}*/
		return teluserloaninfoService.searchPeriodRepay(telUserInfoBeanVO);
	}
}