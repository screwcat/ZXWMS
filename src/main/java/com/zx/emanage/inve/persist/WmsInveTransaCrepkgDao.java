package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveTransaCrepkg;
import com.zx.emanage.util.gen.vo.WmsInveClerkProtocolTransaCrepkgTemp;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveTransaCrepkgDao
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月11日
 * @version V1.0
 * history:
 * 1、2017年2月11日 jinzhm 创建文件
 */
@MyBatisRepository
public interface WmsInveTransaCrepkgDao extends BaseDao<WmsInveTransaCrepkg>
{
    /**
     * @Title: queryAllTransaCrepkg
     * @Description: 根据条件查找关联的债权信息集合
     * @param paramMap 查询条件
     * @return 债权信息集合
     * @author: jinzhm
     * @time:2017年2月11日 上午10:39:42
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public List<Map<String, Object>> queryAllTransaCrepkg(Map<String, Object> paramMap);

    /**
     * 
     * @Title: queryCrepkgByPk
     * @Description: 查询债权的匹配情况信息
     * @param paramMap
     * @return 
     * @author: Guanxu
     * @time:2017年2月21日 下午6:23:34
     * history:
     * 1、2017年2月21日 Guanxu 创建方法
     */
    public List<Map<String, Object>> queryCrepkgByPk(Map<String, Object> paramMap);

    /**
     * @Title: updateAllHoldTransaCrepkgByTransaId
     * @Description: 根据上单主键将占用中的债权生效，并同时修改合同主键和匹配时间
     * @param paramMap 修改条件及修改内容
     * @author: jinzhm
     * @time:2017年2月13日 下午2:58:43
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    public void updateAllHoldTransaCrepkgByTransaId(Map<String, Object> paramMap);

    /**
     * @Title: queryAllTransaCrepkgByTransaId
     * @Description: 根据上单主键查询占用中或生效中的所有债权信息
     * @param paramMap
     * @return 
     * @author: jinzhm
     * @time:2017年2月13日 下午3:27:36
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    public List<WmsInveTransaCrepkg> queryAllTransaCrepkgByTransaId(Map<String, Object> paramMap);

    /**
     * @Title: batchInsertTransaCrepkg
     * @Description: 批量新增单据债权关联信息
     * @param list 
     * @author: jinzhm
     * @time:2017年2月14日 上午9:16:24
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public void batchInsertTransaCrepkg(List<WmsInveTransaCrepkg> list);

    /**
     * @Title: updateProtocol
     * @Description: 更新债权匹配关系表的合同编号
     * @param crepkg 
     * @author: Guanxu
     * @time:2017年2月16日 下午4:16:20
     * history:
     * 1、2017年2月16日 Guanxu 创建方法
    */
    public int updateProtocol(WmsInveTransaCrepkg crepkg);

    /**
     * @Title: queryAllProtocolTransaCrepkg
     * @Description: 查询债权匹配历表中匹配记录
     * @param paramMap
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月21日 下午4:31:40
     * history:
     * 1、2017年2月21日 Administrator 创建方法
    */
    public List<Map<String, Object>> queryAllProtocolTransaCrepkg(Map<String, Object> paramMap);

    /**
     * @Title: insertCreditMatchErrorLog
     * @Description: 保存债权匹配不成功日志信息
     * @param logText 日志内容
     * @author: jinzhm
     * @time:2017年2月24日 下午3:31:49
     * history:
     * 1、2017年2月24日 jinzhm 创建方法
     */
    public void insertCreditMatchErrorLog(String logText);

    /**
     * @Title: queryCreditPackageInState
     * @Description: 查询某个抵押包和单据的关联信息
     * @param paramMap 查询条件
     * @return 个数
     * @author: jinzhm
     * @time:2017年3月14日 下午4:01:00
     * history:
     * 1、2017年3月14日 jinzhm 创建方法
     */
    public List<WmsInveTransaCrepkg> queryCreditPackageInState(Map<String, Object> paramMap);

    /**
     * @Title: saveProtocolTransaCrepkgTempData
     * @Description: 保存债权单据关联临时数据
     * @param temp 债权单据关联临时对象
     * @author: jinzhm
     * @time:2017年4月8日 上午10:58:46
     * history:
     * 1、2017年4月8日 jinzhm 创建方法
     */
    public void saveProtocolTransaCrepkgTempData(WmsInveClerkProtocolTransaCrepkgTemp temp);

    /**
     * @Title: getWmsInveTransaCrepkgList
     * @Description: 通过上单主键和柜员合同主键查询当前生效的债权匹配关系
     * @param paramsMap
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月16日 下午5:26:39
     * history:
     * 1、2017年5月16日 Administrator 创建方法
    */
    public List<WmsInveTransaCrepkg> getWmsInveTransaCrepkgList(Map<String, Object> paramsMap);

    /**
     * @Title: disableWmsInveTransaCrepkg
     * @Description: 失效原合同债权匹配关系
     * @param paramsMap 
     * @author: zhangyunfei
     * @time:2017年5月16日 下午2:47:44
     * history:
     * 1、2017年5月16日 Administrator 创建方法
    */
    public void disableWmsInveTransaCrepkg(Map<String, Object> paramsMap);

    /**
     * @Title: saveWmsInveTransaCrepkgBatch
     * @Description: 批量保存债权匹配关系
     * @param wmsInveTransaCrepkgList 
     * @author: zhangyunfei
     * @time:2017年5月16日 下午6:00:47
     * history:
     * 1、2017年5月16日 Administrator 创建方法
    */
    public void saveWmsInveTransaCrepkgBatch(List<WmsInveTransaCrepkg> wmsInveTransaCrepkgList);

    /**
     * @Title: getWmsInveTransaCrepkgIds
     * @Description: 根据上单主键和柜员合同主键获取匹配关联表主键集合(以逗号分隔)
     * @param pMap
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月16日 下午6:01:47
     * history:
     * 1、2017年5月16日 Administrator 创建方法
    */
    public String getWmsInveTransaCrepkgIds(Map<String, Object> pMap);
}
