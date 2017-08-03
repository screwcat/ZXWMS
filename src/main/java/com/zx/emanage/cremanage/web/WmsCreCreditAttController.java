package com.zx.emanage.cremanage.web;

/**
 * 版权所有：版权所有(C) 2014，卓信财富
 * 文件名称: WmsCreCreditAttController.Java
 * 系统名称：WMS
 * 模块名称：上传附件模块
 * 完成日期：20141203
 * 作    者：hancd
 * 内容摘要：   
 *   实现上传附件的读取和存储保存功能
 * 文件调用：
 * 修改记录：
 * 修改时间：
 * 修 改  人: 
 * 修改内容：
 * 关联BUG ：
 * 修改方法：
 */

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.cremanage.service.IWmsCreCreditAttService;
import com.zx.emanage.util.gen.entity.WmsCreCreditAtt;
import com.zx.emanage.cremanage.vo.WmsCreCreditAttSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @hancd
 */

@Controller
public class WmsCreCreditAttController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditAttController.class);

    @Autowired
    private IWmsCreCreditAttService wmscrecreditattService;

    /**
     * Description :根据提供的贷款主键以及附件类型 查询相应的附件信息
     * 
     * @param primary key
     * @return WmsCreCreditAttVO
     * @author hancd
     */
    @RequestMapping(value = "/cremanage/wmscrecreditattinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<WmsCreCreditAtt> getInfoByFK(Integer wms_cre_credit_head_id, String data_type)
    {
        return wmscrecreditattService.getInfoByFK(wms_cre_credit_head_id, data_type);
    }

}