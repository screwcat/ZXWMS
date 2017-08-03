package com.zx.emanage.util.gen.vo;

import java.sql.Timestamp;

import com.zx.sframe.util.mybatis.BaseEntity;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveClerkProtocolTransaCrepkgTemp
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年4月8日
 * @version V1.0
 * history:
 * 1、2017年4月8日 jinzhm 创建文件
 */
public class WmsInveClerkProtocolTransaCrepkgTemp implements BaseEntity
{

    /**
     * @Fields serialVersionUID : 
     */
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_clerk_protocol_transa_crepkg_temp_id;

    private Integer wms_inve_transa_id;

    private Integer wms_inve_clerk_protocol_id;

    private Integer wms_inve_transa_crepkg_id;

    private Integer create_user_id;

    private Timestamp create_timestamp;

    private String enable_flag;

    /**
     * @return the wms_inve_clerk_protocol_transa_crepkg_temp_id
     */
    public Integer getWms_inve_clerk_protocol_transa_crepkg_temp_id()
    {
        return wms_inve_clerk_protocol_transa_crepkg_temp_id;
    }

    /**
     * @param wms_inve_clerk_protocol_transa_crepkg_temp_id the wms_inve_clerk_protocol_transa_crepkg_temp_id to set
     */
    public void setWms_inve_clerk_protocol_transa_crepkg_temp_id(Integer wms_inve_clerk_protocol_transa_crepkg_temp_id)
    {
        this.wms_inve_clerk_protocol_transa_crepkg_temp_id = wms_inve_clerk_protocol_transa_crepkg_temp_id;
    }

    /**
     * @return the wms_inve_transa_id
     */
    public Integer getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    /**
     * @param wms_inve_transa_id the wms_inve_transa_id to set
     */
    public void setWms_inve_transa_id(Integer wms_inve_transa_id)
    {
        this.wms_inve_transa_id = wms_inve_transa_id;
    }

    /**
     * @return the wms_inve_clerk_protocol_id
     */
    public Integer getWms_inve_clerk_protocol_id()
    {
        return wms_inve_clerk_protocol_id;
    }

    /**
     * @param wms_inve_clerk_protocol_id the wms_inve_clerk_protocol_id to set
     */
    public void setWms_inve_clerk_protocol_id(Integer wms_inve_clerk_protocol_id)
    {
        this.wms_inve_clerk_protocol_id = wms_inve_clerk_protocol_id;
    }

    /**
     * @return the wms_inve_transa_crepkg_id
     */
    public Integer getWms_inve_transa_crepkg_id()
    {
        return wms_inve_transa_crepkg_id;
    }

    /**
     * @param wms_inve_transa_crepkg_id the wms_inve_transa_crepkg_id to set
     */
    public void setWms_inve_transa_crepkg_id(Integer wms_inve_transa_crepkg_id)
    {
        this.wms_inve_transa_crepkg_id = wms_inve_transa_crepkg_id;
    }

    /**
     * @return the create_user_id
     */
    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    /**
     * @param create_user_id the create_user_id to set
     */
    public void setCreate_user_id(Integer create_user_id)
    {
        this.create_user_id = create_user_id;
    }

    /**
     * @return the create_timestamp
     */
    public Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    /**
     * @param create_timestamp the create_timestamp to set
     */
    public void setCreate_timestamp(Timestamp create_timestamp)
    {
        this.create_timestamp = create_timestamp;
    }

    /**
     * @return the enable_flag
     */
    public String getEnable_flag()
    {
        return enable_flag;
    }

    /**
     * @param enable_flag the enable_flag to set
     */
    public void setEnable_flag(String enable_flag)
    {
        this.enable_flag = enable_flag;
    }

}
