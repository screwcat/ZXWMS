package com.zx.emanage.inve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveTransaCrepkgService;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveTransaCrepkgController
 * 模块名称：债权单据关联信息controller
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月11日
 * @version V1.0
 * history:
 * 1、2017年2月11日 jinzhm 创建文件
 */
@Controller
public class WmsInveTransaCrepkgController
{

    @Autowired
    private IWmsInveTransaCrepkgService wmsInveTransaCrepkgService;

    /**
     * @Title: getAllTransaCrepkg
     * @Description: 根据单据id或协议id获得关联的所有债权包信息
     * @param transaId 单据id
     * @param protocolId 协议id
     * @return 关联债权包集合
     * @author: jinzhm
     * @time:2017年2月11日 上午10:31:51
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getAllTransaCrepkg.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAllTransaCrepkg(Integer transaId, Integer protocolId)
    {
        return wmsInveTransaCrepkgService.getAllTransaCrepkg(transaId, protocolId);
    }

    @RequestMapping(value = "/inve/getCrepkgByPk.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> queryCrepkgByPk(Integer wms_inve_credit_package_id, String disacl_type)
    {
        return wmsInveTransaCrepkgService.queryCrepkgByPk(wms_inve_credit_package_id, disacl_type);
    }

}
