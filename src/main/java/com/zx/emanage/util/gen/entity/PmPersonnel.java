package com.zx.emanage.util.gen.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zx.sframe.util.mybatis.BaseEntity;

/**
 * 人员信息实体Bean
 * 
 * @author hancd
 */
public class PmPersonnel implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer personnel_id;

    private String personnel_encryptionid;

    private String personnel_code;

    private String personnel_shortcode;

    private String personnel_name;

    private String personnel_sex;

    private String personnel_age;

    private java.sql.Date personnel_birthtime;

    private String personnel_birthtime_str;

    private String personnel_identitycode;

    private java.sql.Date personnel_identitytime;

    private String personnel_identitytime_str;

    private String personnel_account;

    private String personnel_education;

    private String personnel_politicsstatus;

    private String personnel_marriage;

    private String personnel_nation;

    private String personnel_stature;

    private String personnel_weight;

    private String personnel_contacttel;

    private String personnel_corporationtel;

    private String personnel_extensiontel;

    private String personnel_emial;

    private String personnel_school;

    private String personnel_specialty;

    private java.sql.Date personnel_entrancetime;

    private String personnel_entrancetime_str;

    private java.sql.Date personnel_graduatetime;

    private String personnel_graduatetime_str;

    private String personnel_laborcontract;

    private String personnel_reinstated;

    private String personnel_confidential;

    private String personnel_pay;

    private String personnel_emergencyname;

    private String personnel_emergencyremark;

    private String personnel_accountaddress;

    private String personnel_emergencytel;

    private String personnel_othertel;

    private String personnel_regionnumber;

    private Integer personnel_organid;

    private String personnel_organcode;

    private Integer personnel_deptid;

    private String personnel_deptcode;

    private String personnel_deptname;

    private String personnel_team;

    private Integer personnel_postid;

    private String personnel_postname;

    private Integer personnel_postlevelid;

    private java.sql.Date personnel_trialstarttime;

    private String personnel_trialstarttime_str;

    private java.sql.Date personnel_trialendtime;

    private String personnel_trialendtime_str;

    private java.sql.Date personnel_traintime;

    private String personnel_traintime_str;

    private String personnel_trainremark;

    private String personnel_employmentform;

    private java.sql.Date personnel_positivetime;

    private String personnel_positivetime_str;

    private String personnel_bankname;

    private String personnel_bankcardcode;

    private String personnel_socialsecurity;

    private String personnel_socialinfo;

    private java.sql.Date personnel_contractstarttime;

    private String personnel_contractstarttime_str;

    private java.sql.Date personnel_contractendtime;

    private String personnel_contractendtime_str;

    private String personnel_workage;

    private String personnel_seniority;

    private String personnel_remark;

    private String enable_flag;

    private String create_user;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private String last_update_user;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    // 离职类型
    private String leave_type;

    // 离职时间
    private java.sql.Timestamp leave_time;

    private String leave_time_str;

    // 工作QQ
    private String personnel_QQ;

    // 人员录入状态 1.兼职员工 2.普通员工 3.普通转兼职员工
    private Integer personnel_enter_state;

    private String personnel_state;

    private String personnel_accounttype;

    private String flow1;

    private String flow2;

    private String flow3;

    private String flow4;

    private String flow5;

    // 所在省编号
    private String region_province_dict_id;

    // 所在市编号
    private String region_city_dict_id;

    /**
     * @return the region_province_dict_id
     */
    public String getRegion_province_dict_id()
    {
        return region_province_dict_id;
    }

    /**
     * @param region_province_dict_id the region_province_dict_id to set
     */
    public void setRegion_province_dict_id(String region_province_dict_id)
    {
        this.region_province_dict_id = region_province_dict_id;
    }

    /**
     * @return the region_city_dict_id
     */
    public String getRegion_city_dict_id()
    {
        return region_city_dict_id;
    }

    /**
     * @param region_city_dict_id the region_city_dict_id to set
     */
    public void setRegion_city_dict_id(String region_city_dict_id)
    {
        this.region_city_dict_id = region_city_dict_id;
    }

    /**
     * 部门信息 用于查询
     */
    private String dept_id;

    private String dept_name;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "personnel_id" };

    @SuppressWarnings("unused")
    private static String[] columnNameArr = { "personnel_id", "personnel_encryptionid", "personnel_code", "personnel_name", "personnel_sex", "personnel_age", "personnel_birthtime", "personnel_birthtime_str", "personnel_identitycode", "personnel_identitytime", "personnel_identitytime_str", "personnel_account", "personnel_education", "personnel_politicsstatus", "personnel_marriage", "personnel_nation", "personnel_stature", "personnel_weight", "personnel_contacttel", "personnel_corporationtel", "personnel_extensiontel", "personnel_emial", "personnel_school", "personnel_specialty", "personnel_entrancetime", "personnel_entrancetime_str", "personnel_graduatetime", "personnel_graduatetime_str", "personnel_laborcontract", "personnel_insurance", "personnel_reinstated", "personnel_confidential", "personnel_pay", "personnel_emergencyname", "personnel_emergencyremark", "personnel_accountaddress", "personnel_emergencytel", "personnel_othertel", "personnel_regionnumber", "personnel_organid", "personnel_organcode", "personnel_deptid", "personnel_deptcode", "personnel_deptname", "personnel_team", "personnel_postid", "personnel_postname", "personnel_trialstarttime", "personnel_trialstarttime_str",
                                             "personnel_trialendtime", "personnel_trialendtime_str", "personnel_traintime", "personnel_traintime_str", "personnel_trainremark", "personnel_employmentform", "personnel_positivetime", "personnel_positivetime_str", "personnel_bankname", "personnel_bankcardcode", "personnel_socialsecurity", "personnel_socialinfo", "personnel_contractstarttime", "personnel_contractstarttime_str", "personnel_contractendtime", "personnel_contractendtime_str", "personnel_workage", "personnel_seniority", "personnel_state", "personnel_remark", "enable_flag", "create_user", "create_timestamp", "create_timestamp_str", "last_update_user", "last_update_timestamp", "last_update_timestamp_str", "leave_type", "leave_time", "leave_time_str", "personnel_QQ", "personnel_enter_state", "personnel_postLevelid", "personnel_accounttype", "flow1", "flow2", "flow3", "flow4", "flow5" };

    public Integer getPersonnel_id()
    {
        return personnel_id;
    }

    public void setPersonnel_id(Integer personnel_id)
    {
        this.personnel_id = personnel_id;
    }

    public String getPersonnel_code()
    {
        return personnel_code;
    }

    public void setPersonnel_code(String personnel_code)
    {
        this.personnel_code = personnel_code;
    }

    public String getPersonnel_name()
    {
        return personnel_name;
    }

    public void setPersonnel_name(String personnel_name)
    {
        this.personnel_name = personnel_name;
    }

    public String getPersonnel_sex()
    {
        return personnel_sex;
    }

    public void setPersonnel_sex(String personnel_sex)
    {
        this.personnel_sex = personnel_sex;
    }

    public String getPersonnel_age()
    {
        return personnel_age;
    }

    public void setPersonnel_age(String personnel_age)
    {
        this.personnel_age = personnel_age;
    }

    public java.sql.Date getPersonnel_birthtime()
    {
        return personnel_birthtime;
    }

    public void setPersonnel_birthtime(java.sql.Date personnel_birthtime)
    {
        this.personnel_birthtime = personnel_birthtime;
    }

    public String getPersonnel_birthtime_str()
    {
        return personnel_birthtime_str;
    }

    public void setPersonnel_birthtime_str(String personnel_birthtime_str)
    {
        this.personnel_birthtime_str = personnel_birthtime_str;
    }

    public String getPersonnel_identitycode()
    {
        return personnel_identitycode;
    }

    public void setPersonnel_identitycode(String personnel_identitycode)
    {
        this.personnel_identitycode = personnel_identitycode;
    }

    public java.sql.Date getPersonnel_identitytime()
    {
        return personnel_identitytime;
    }

    public void setPersonnel_identitytime(java.sql.Date personnel_identitytime)
    {
        this.personnel_identitytime = personnel_identitytime;
    }

    public String getPersonnel_identitytime_str()
    {
        return personnel_identitytime_str;
    }

    public void setPersonnel_identitytime_str(String personnel_identitytime_str)
    {
        this.personnel_identitytime_str = personnel_identitytime_str;
    }

    public String getPersonnel_account()
    {
        return personnel_account;
    }

    public void setPersonnel_account(String personnel_account)
    {
        this.personnel_account = personnel_account;
    }

    public String getPersonnel_education()
    {
        return personnel_education;
    }

    public void setPersonnel_education(String personnel_education)
    {
        this.personnel_education = personnel_education;
    }

    public String getPersonnel_politicsstatus()
    {
        return personnel_politicsstatus;
    }

    public void setPersonnel_politicsstatus(String personnel_politicsstatus)
    {
        this.personnel_politicsstatus = personnel_politicsstatus;
    }

    public String getPersonnel_marriage()
    {
        return personnel_marriage;
    }

    public void setPersonnel_marriage(String personnel_marriage)
    {
        this.personnel_marriage = personnel_marriage;
    }

    public String getPersonnel_nation()
    {
        return personnel_nation;
    }

    public void setPersonnel_nation(String personnel_nation)
    {
        this.personnel_nation = personnel_nation;
    }

    public String getPersonnel_stature()
    {
        return personnel_stature;
    }

    public void setPersonnel_stature(String personnel_stature)
    {
        this.personnel_stature = personnel_stature;
    }

    public String getPersonnel_weight()
    {
        return personnel_weight;
    }

    public void setPersonnel_weight(String personnel_weight)
    {
        this.personnel_weight = personnel_weight;
    }

    public String getPersonnel_contacttel()
    {
        return personnel_contacttel;
    }

    public void setPersonnel_contacttel(String personnel_contacttel)
    {
        this.personnel_contacttel = personnel_contacttel;
    }

    public String getPersonnel_corporationtel()
    {
        return personnel_corporationtel;
    }

    public void setPersonnel_corporationtel(String personnel_corporationtel)
    {
        this.personnel_corporationtel = personnel_corporationtel;
    }

    public String getPersonnel_extensiontel()
    {
        return personnel_extensiontel;
    }

    public void setPersonnel_extensiontel(String personnel_extensiontel)
    {
        this.personnel_extensiontel = personnel_extensiontel;
    }

    public String getPersonnel_emial()
    {
        return personnel_emial;
    }

    public void setPersonnel_emial(String personnel_emial)
    {
        this.personnel_emial = personnel_emial;
    }

    public String getPersonnel_school()
    {
        return personnel_school;
    }

    public void setPersonnel_school(String personnel_school)
    {
        this.personnel_school = personnel_school;
    }

    public String getPersonnel_specialty()
    {
        return personnel_specialty;
    }

    public void setPersonnel_specialty(String personnel_specialty)
    {
        this.personnel_specialty = personnel_specialty;
    }

    public java.sql.Date getPersonnel_entrancetime()
    {
        return personnel_entrancetime;
    }

    public void setPersonnel_entrancetime(java.sql.Date personnel_entrancetime)
    {
        this.personnel_entrancetime = personnel_entrancetime;
    }

    public String getPersonnel_entrancetime_str()
    {
        return personnel_entrancetime_str;
    }

    public void setPersonnel_entrancetime_str(String personnel_entrancetime_str)
    {
        this.personnel_entrancetime_str = personnel_entrancetime_str;
    }

    public java.sql.Date getPersonnel_graduatetime()
    {
        return personnel_graduatetime;
    }

    public void setPersonnel_graduatetime(java.sql.Date personnel_graduatetime)
    {
        this.personnel_graduatetime = personnel_graduatetime;
    }

    public String getPersonnel_graduatetime_str()
    {
        return personnel_graduatetime_str;
    }

    public void setPersonnel_graduatetime_str(String personnel_graduatetime_str)
    {
        this.personnel_graduatetime_str = personnel_graduatetime_str;
    }

    public String getPersonnel_laborcontract()
    {
        return personnel_laborcontract;
    }

    public void setPersonnel_laborcontract(String personnel_laborcontract)
    {
        this.personnel_laborcontract = personnel_laborcontract;
    }

    public String getPersonnel_reinstated()
    {
        return personnel_reinstated;
    }

    public void setPersonnel_reinstated(String personnel_reinstated)
    {
        this.personnel_reinstated = personnel_reinstated;
    }

    public String getPersonnel_confidential()
    {
        return personnel_confidential;
    }

    public void setPersonnel_confidential(String personnel_confidential)
    {
        this.personnel_confidential = personnel_confidential;
    }

    public String getPersonnel_pay()
    {
        return personnel_pay;
    }

    public void setPersonnel_pay(String personnel_pay)
    {
        this.personnel_pay = personnel_pay;
    }

    public String getPersonnel_emergencyname()
    {
        return personnel_emergencyname;
    }

    public void setPersonnel_emergencyname(String personnel_emergencyname)
    {
        this.personnel_emergencyname = personnel_emergencyname;
    }

    public String getPersonnel_emergencyremark()
    {
        return personnel_emergencyremark;
    }

    public void setPersonnel_emergencyremark(String personnel_emergencyremark)
    {
        this.personnel_emergencyremark = personnel_emergencyremark;
    }

    public String getPersonnel_accountaddress()
    {
        return personnel_accountaddress;
    }

    public void setPersonnel_accountaddress(String personnel_accountaddress)
    {
        this.personnel_accountaddress = personnel_accountaddress;
    }

    public String getPersonnel_emergencytel()
    {
        return personnel_emergencytel;
    }

    public void setPersonnel_emergencytel(String personnel_emergencytel)
    {
        this.personnel_emergencytel = personnel_emergencytel;
    }

    public String getPersonnel_othertel()
    {
        return personnel_othertel;
    }

    public void setPersonnel_othertel(String personnel_othertel)
    {
        this.personnel_othertel = personnel_othertel;
    }

    public String getPersonnel_regionnumber()
    {
        return personnel_regionnumber;
    }

    public void setPersonnel_regionnumber(String personnel_regionnumber)
    {
        this.personnel_regionnumber = personnel_regionnumber;
    }

    public Integer getPersonnel_organid()
    {
        return personnel_organid;
    }

    public void setPersonnel_organid(Integer personnel_organid)
    {
        this.personnel_organid = personnel_organid;
    }

    public String getPersonnel_organcode()
    {
        return personnel_organcode;
    }

    public void setPersonnel_organcode(String personnel_organcode)
    {
        this.personnel_organcode = personnel_organcode;
    }

    public Integer getPersonnel_deptid()
    {
        return personnel_deptid;
    }

    public void setPersonnel_deptid(Integer personnel_deptid)
    {
        this.personnel_deptid = personnel_deptid;
    }

    public String getPersonnel_deptcode()
    {
        return personnel_deptcode;
    }

    public void setPersonnel_deptcode(String personnel_deptcode)
    {
        this.personnel_deptcode = personnel_deptcode;
    }

    public String getPersonnel_deptname()
    {
        return personnel_deptname;
    }

    public void setPersonnel_deptname(String personnel_deptname)
    {
        this.personnel_deptname = personnel_deptname;
    }

    public String getPersonnel_team()
    {
        return personnel_team;
    }

    public void setPersonnel_team(String personnel_team)
    {
        this.personnel_team = personnel_team;
    }

    public Integer getPersonnel_postid()
    {
        return personnel_postid;
    }

    public void setPersonnel_postid(Integer personnel_postid)
    {
        this.personnel_postid = personnel_postid;
    }

    public String getPersonnel_postname()
    {
        return personnel_postname;
    }

    public void setPersonnel_postname(String personnel_postname)
    {
        this.personnel_postname = personnel_postname;
    }

    public java.sql.Date getPersonnel_trialstarttime()
    {
        return personnel_trialstarttime;
    }

    public void setPersonnel_trialstarttime(java.sql.Date personnel_trialstarttime)
    {
        this.personnel_trialstarttime = personnel_trialstarttime;
    }

    public String getPersonnel_trialstarttime_str()
    {
        return personnel_trialstarttime_str;
    }

    public void setPersonnel_trialstarttime_str(String personnel_trialstarttime_str)
    {
        this.personnel_trialstarttime_str = personnel_trialstarttime_str;
    }

    public java.sql.Date getPersonnel_trialendtime()
    {
        return personnel_trialendtime;
    }

    public void setPersonnel_trialendtime(java.sql.Date personnel_trialendtime)
    {
        this.personnel_trialendtime = personnel_trialendtime;
    }

    public String getPersonnel_trialendtime_str()
    {
        return personnel_trialendtime_str;
    }

    public void setPersonnel_trialendtime_str(String personnel_trialendtime_str)
    {
        this.personnel_trialendtime_str = personnel_trialendtime_str;
    }

    public java.sql.Date getPersonnel_traintime()
    {
        return personnel_traintime;
    }

    public void setPersonnel_traintime(java.sql.Date personnel_traintime)
    {
        this.personnel_traintime = personnel_traintime;
    }

    public String getPersonnel_traintime_str()
    {
        return personnel_traintime_str;
    }

    public void setPersonnel_traintime_str(String personnel_traintime_str)
    {
        this.personnel_traintime_str = personnel_traintime_str;
    }

    public String getPersonnel_trainremark()
    {
        return personnel_trainremark;
    }

    public void setPersonnel_trainremark(String personnel_trainremark)
    {
        this.personnel_trainremark = personnel_trainremark;
    }

    public String getPersonnel_employmentform()
    {
        return personnel_employmentform;
    }

    public void setPersonnel_employmentform(String personnel_employmentform)
    {
        this.personnel_employmentform = personnel_employmentform;
    }

    public java.sql.Date getPersonnel_positivetime()
    {
        return personnel_positivetime;
    }

    public void setPersonnel_positivetime(java.sql.Date personnel_positivetime)
    {
        this.personnel_positivetime = personnel_positivetime;
    }

    public String getPersonnel_positivetime_str()
    {
        return personnel_positivetime_str;
    }

    public void setPersonnel_positivetime_str(String personnel_positivetime_str)
    {
        this.personnel_positivetime_str = personnel_positivetime_str;
    }

    public String getPersonnel_bankname()
    {
        return personnel_bankname;
    }

    public void setPersonnel_bankname(String personnel_bankname)
    {
        this.personnel_bankname = personnel_bankname;
    }

    public String getPersonnel_bankcardcode()
    {
        return personnel_bankcardcode;
    }

    public void setPersonnel_bankcardcode(String personnel_bankcardcode)
    {
        this.personnel_bankcardcode = personnel_bankcardcode;
    }

    public String getPersonnel_socialsecurity()
    {
        return personnel_socialsecurity;
    }

    public void setPersonnel_socialsecurity(String personnel_socialsecurity)
    {
        this.personnel_socialsecurity = personnel_socialsecurity;
    }

    public String getPersonnel_socialinfo()
    {
        return personnel_socialinfo;
    }

    public void setPersonnel_socialinfo(String personnel_socialinfo)
    {
        this.personnel_socialinfo = personnel_socialinfo;
    }

    public java.sql.Date getPersonnel_contractstarttime()
    {
        return personnel_contractstarttime;
    }

    public void setPersonnel_contractstarttime(java.sql.Date personnel_contractstarttime)
    {
        this.personnel_contractstarttime = personnel_contractstarttime;
    }

    public String getPersonnel_contractstarttime_str()
    {
        return personnel_contractstarttime_str;
    }

    public void setPersonnel_contractstarttime_str(String personnel_contractstarttime_str)
    {
        this.personnel_contractstarttime_str = personnel_contractstarttime_str;
    }

    public java.sql.Date getPersonnel_contractendtime()
    {
        return personnel_contractendtime;
    }

    public void setPersonnel_contractendtime(java.sql.Date personnel_contractendtime)
    {
        this.personnel_contractendtime = personnel_contractendtime;
    }

    public String getPersonnel_contractendtime_str()
    {
        return personnel_contractendtime_str;
    }

    public void setPersonnel_contractendtime_str(String personnel_contractendtime_str)
    {
        this.personnel_contractendtime_str = personnel_contractendtime_str;
    }

    public String getPersonnel_workage()
    {
        return personnel_workage;
    }

    public void setPersonnel_workage(String personnel_workage)
    {
        this.personnel_workage = personnel_workage;
    }

    public String getPersonnel_seniority()
    {
        return personnel_seniority;
    }

    public void setPersonnel_seniority(String personnel_seniority)
    {
        this.personnel_seniority = personnel_seniority;
    }

    public String getPersonnel_remark()
    {
        return personnel_remark;
    }

    public void setPersonnel_remark(String personnel_remark)
    {
        this.personnel_remark = personnel_remark;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String enable_flag)
    {
        this.enable_flag = enable_flag;
    }

    public Integer getPersonnel_postlevelid()
    {
        return personnel_postlevelid;
    }

    public void setPersonnel_postlevelid(Integer personnel_postLevelid)
    {
        this.personnel_postlevelid = personnel_postlevelid;
    }

    public String getCreate_user()
    {
        return create_user;
    }

    public void setCreate_user(String create_user)
    {
        this.create_user = create_user;
    }

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp create_timestamp)
    {
        this.create_timestamp = create_timestamp;
    }

    public String getCreate_timestamp_str()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestamp_str(String create_timestamp_str)
    {
        this.create_timestamp_str = create_timestamp_str;
    }

    public String getLast_update_user()
    {
        return last_update_user;
    }

    public void setLast_update_user(String last_update_user)
    {
        this.last_update_user = last_update_user;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp last_update_timestamp)
    {
        this.last_update_timestamp = last_update_timestamp;
    }

    public String getLast_update_timestamp_str()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestamp_str(String last_update_timestamp_str)
    {
        this.last_update_timestamp_str = last_update_timestamp_str;
    }

    public String getLeave_type()
    {
        return leave_type;
    }

    public void setLeave_type(String leave_type)
    {
        this.leave_type = leave_type;
    }

    public java.sql.Timestamp getLeave_time()
    {
        return leave_time;
    }

    public void setLeave_time(java.sql.Timestamp leave_time)
    {
        this.leave_time = leave_time;
    }

    public String getLeave_time_str()
    {
        return leave_time_str;
    }

    public void setLeave_time_str(String leave_time_str)
    {
        this.leave_time_str = leave_time_str;
    }

    public String getPersonnel_accounttype()
    {
        return personnel_accounttype;
    }

    public void setPersonnel_accounttype(String personnel_accounttype)
    {
        this.personnel_accounttype = personnel_accounttype;
    }

    public String getPersonnel_state()
    {
        return personnel_state;
    }

    public void setPersonnel_state(String personnel_state)
    {
        this.personnel_state = personnel_state;
    }

    public String getPersonnel_QQ()
    {
        return personnel_QQ;
    }

    public void setPersonnel_QQ(String personnel_QQ)
    {
        this.personnel_QQ = personnel_QQ;
    }

    public Integer getPersonnel_enter_state()
    {
        return personnel_enter_state;
    }

    public void setPersonnel_enter_state(Integer personnel_enter_state)
    {
        this.personnel_enter_state = personnel_enter_state;
    }

    public String getFlow1()
    {
        return flow1;
    }

    public void setFlow1(String flow1)
    {
        this.flow1 = flow1;
    }

    public String getFlow2()
    {
        return flow2;
    }

    public void setFlow2(String flow2)
    {
        this.flow2 = flow2;
    }

    public String getFlow3()
    {
        return flow3;
    }

    public void setFlow3(String flow3)
    {
        this.flow3 = flow3;
    }

    public String getFlow4()
    {
        return flow4;
    }

    public void setFlow4(String flow4)
    {
        this.flow4 = flow4;
    }

    public String getFlow5()
    {
        return flow5;
    }

    public void setFlow5(String flow5)
    {
        this.flow5 = flow5;
    }

    public String getPersonnel_encryptionid()
    {
        return personnel_encryptionid;
    }

    public void setPersonnel_encryptionid(String personnel_encryptionid)
    {
        this.personnel_encryptionid = personnel_encryptionid;
    }

    public String getPersonnel_shortcode()
    {
        return personnel_shortcode;
    }

    public void setPersonnel_shortcode(String personnel_shortcode)
    {
        this.personnel_shortcode = personnel_shortcode;
    }
    
    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("personnel_id", this.personnel_id);
        paramMap.put("personnel_encryptionid", this.personnel_encryptionid);
        paramMap.put("personnel_code", this.personnel_code);
        paramMap.put("personnel_shortcode", this.personnel_shortcode);
        paramMap.put("personnel_name", this.personnel_name);
        paramMap.put("personnel_sex", this.personnel_sex);
        paramMap.put("personnel_age", this.personnel_age);
        paramMap.put("personnel_birthtime", this.personnel_birthtime);
        paramMap.put("personnel_birthtime_str", this.personnel_birthtime_str);
        paramMap.put("personnel_identitycode", this.personnel_identitycode);
        paramMap.put("personnel_identitytime", this.personnel_identitytime);
        paramMap.put("personnel_identitytime_str", this.personnel_identitytime_str);
        paramMap.put("personnel_account", this.personnel_account);
        paramMap.put("personnel_education", this.personnel_education);
        paramMap.put("personnel_politicsstatus", this.personnel_politicsstatus);
        paramMap.put("personnel_marriage", this.personnel_marriage);
        paramMap.put("personnel_nation", this.personnel_nation);
        paramMap.put("personnel_stature", this.personnel_stature);
        paramMap.put("personnel_weight", this.personnel_weight);
        paramMap.put("personnel_contacttel", this.personnel_contacttel);
        paramMap.put("personnel_corporationtel", this.personnel_corporationtel);
        paramMap.put("personnel_extensiontel", this.personnel_extensiontel);
        paramMap.put("personnel_emial", this.personnel_emial);
        paramMap.put("personnel_school", this.personnel_school);
        paramMap.put("personnel_specialty", this.personnel_specialty);
        paramMap.put("personnel_entrancetime", this.personnel_entrancetime);
        paramMap.put("personnel_entrancetime_str", this.personnel_entrancetime_str);
        paramMap.put("personnel_graduatetime", this.personnel_graduatetime);
        paramMap.put("personnel_graduatetime_str", this.personnel_graduatetime_str);
        paramMap.put("personnel_laborcontract", this.personnel_laborcontract);
        paramMap.put("personnel_reinstated", this.personnel_reinstated);
        paramMap.put("personnel_confidential", this.personnel_confidential);
        paramMap.put("personnel_pay", this.personnel_pay);
        paramMap.put("personnel_emergencyname", this.personnel_emergencyname);
        paramMap.put("personnel_emergencyremark", this.personnel_emergencyremark);
        paramMap.put("personnel_accountaddress", this.personnel_accountaddress);
        paramMap.put("personnel_emergencytel", this.personnel_emergencytel);
        paramMap.put("personnel_othertel", this.personnel_othertel);
        paramMap.put("personnel_regionnumber", this.personnel_regionnumber);
        paramMap.put("personnel_organid", this.personnel_organid);
        paramMap.put("personnel_organcode", this.personnel_organcode);
        paramMap.put("personnel_deptid", this.personnel_deptid);
        paramMap.put("personnel_deptcode", this.personnel_deptcode);
        paramMap.put("personnel_deptname", this.personnel_deptname);
        paramMap.put("personnel_team", this.personnel_team);
        paramMap.put("personnel_postid", this.personnel_postid);
        paramMap.put("personnel_postname", this.personnel_postname);
        paramMap.put("personnel_trialstarttime", this.personnel_trialstarttime);
        paramMap.put("personnel_trialstarttime_str", this.personnel_trialstarttime_str);
        paramMap.put("personnel_trialendtime", this.personnel_trialendtime);
        paramMap.put("personnel_trialendtime_str", this.personnel_trialendtime_str);
        paramMap.put("personnel_traintime", this.personnel_traintime);
        paramMap.put("personnel_traintime_str", this.personnel_traintime_str);
        paramMap.put("personnel_trainremark", this.personnel_trainremark);
        paramMap.put("personnel_employmentform", this.personnel_employmentform);
        paramMap.put("personnel_positivetime", this.personnel_positivetime);
        paramMap.put("personnel_positivetime_str", this.personnel_positivetime_str);
        paramMap.put("personnel_bankname", this.personnel_bankname);
        paramMap.put("personnel_bankcardcode", this.personnel_bankcardcode);
        paramMap.put("personnel_socialsecurity", this.personnel_socialsecurity);
        paramMap.put("personnel_socialinfo", this.personnel_socialinfo);
        paramMap.put("personnel_contractstarttime", this.personnel_contractstarttime);
        paramMap.put("personnel_contractstarttime_str", this.personnel_contractstarttime_str);
        paramMap.put("personnel_contractendtime", this.personnel_contractendtime);
        paramMap.put("personnel_contractendtime_str", this.personnel_contractendtime_str);
        paramMap.put("personnel_workage", this.personnel_workage);
        paramMap.put("personnel_seniority", this.personnel_seniority);
        paramMap.put("personnel_remark", this.personnel_remark);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("create_user", this.create_user);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user", this.last_update_user);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("leave_type", this.leave_type);
        paramMap.put("leave_time", this.leave_time);
        paramMap.put("leave_time_str", this.leave_time_str);
        paramMap.put("personnel_QQ", this.personnel_QQ);
        paramMap.put("personnel_enter_state", this.personnel_enter_state);
        paramMap.put("personnel_state", this.personnel_state);
        paramMap.put("personnel_postlevelid", this.personnel_postlevelid);
        paramMap.put("personnel_accounttype", this.personnel_accounttype);
        paramMap.put("flow1", this.flow1);
        paramMap.put("flow2", this.flow2);
        paramMap.put("flow3", this.flow3);
        paramMap.put("flow4", this.flow4);
        paramMap.put("flow5", this.flow5);
    }

    /**
     * return the columns map
     */
    public Map<String, Object> getInfoMap()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        this.putInMap(paramMap);
        return paramMap;
    }

    /**
     * remove default value and pk if it is null
     */
    @SuppressWarnings("unused")
    private Map<String, Object> dealWithMap(Map<String, Object> paramMap)
    {
        Set<String> set = new HashSet<String>();
        for (String colName : defaultValColArr)
        {
            set.add(colName);
        }
        for (String colName : pkColArr)
        {
            set.add(colName);
        }
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
        {
            String colName = iterator.next();
            if (paramMap.get(colName) == null)
            {
                paramMap.remove(colName);
            }
        }
        return paramMap;
    }

    public String getDept_id()
    {
        return dept_id;
    }

    public void setDept_id(String dept_id)
    {
        this.dept_id = dept_id;
    }

    public String getDept_name()
    {
        return dept_name;
    }

    public void setDept_name(String dept_name)
    {
        this.dept_name = dept_name;
    }
}