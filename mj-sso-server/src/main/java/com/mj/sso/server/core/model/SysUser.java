package com.mj.sso.server.core.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:piggy
 * @Description:SysUser构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "SysUser",value = "SysUser")
@Table(name="sys_user")
public class SysUser implements Serializable{

    public SysUser() {
    }
    @ApiModelProperty(value = "用户ID",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;//用户ID

    @ApiModelProperty(value = "部门ID",required = false)
    @Column(name = "dept_id")
    private Long deptId;//部门ID

    @ApiModelProperty(value = "登录账号",required = false)
    @Column(name = "login_name")
    private String loginName;//登录账号

    @ApiModelProperty(value = "用户昵称",required = false)
    @Column(name = "user_name")
    private String userName;//用户昵称

    @ApiModelProperty(value = "用户类型（00系统用户 01注册用户）",required = false)
    @Column(name = "user_type")
    private String userType;//用户类型（00系统用户 01注册用户）

    @ApiModelProperty(value = "用户邮箱",required = false)
    @Column(name = "email")
    private String email;//用户邮箱

    @ApiModelProperty(value = "手机号码",required = false)
    @Column(name = "phonenumber")
    private String phonenumber;//手机号码

    @ApiModelProperty(value = "个人介绍",required = false)
    @Column(name = "description")
    private String description;//个人介绍

    @ApiModelProperty(value = "邮箱验证标志",required = false)
    @Column(name = "email_flag")
    private Integer emailFlag;//邮箱验证标志

    @ApiModelProperty(value = "手机验证标志",required = false)
    @Column(name = "phone_flag")
    private Integer phoneFlag;//手机验证标志

    @ApiModelProperty(value = "积分",required = false)
    @Column(name = "score")
    private Integer score;//积分

    @ApiModelProperty(value = "用户性别（0男 1女 2未知）",required = false)
    @Column(name = "sex")
    private String sex;//用户性别（0男 1女 2未知）

    @ApiModelProperty(value = "头像路径",required = false)
    @Column(name = "avatar")
    private String avatar;//头像路径

    @ApiModelProperty(value = "密码",required = false)
    @Column(name = "password")
    private String password;//密码

    @ApiModelProperty(value = "盐加密",required = false)
    @Column(name = "salt")
    private String salt;//盐加密

    @ApiModelProperty(value = "帐号状态（0正常 1停用）",required = false)
    @Column(name = "status")
    private String status;//帐号状态（0正常 1停用）

    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）",required = false)
    @Column(name = "del_flag")
    private String delFlag;//删除标志（0代表存在 2代表删除）

    @ApiModelProperty(value = "最后登陆IP",required = false)
    @Column(name = "login_ip")
    private String loginIp;//最后登陆IP

    @ApiModelProperty(value = "最后登陆时间",required = false)
    @Column(name = "login_date")
    private Date loginDate;//最后登陆时间

    @ApiModelProperty(value = "创建者",required = false)
    @Column(name = "create_by")
    private String createBy;//创建者

    @ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
    private Date createTime;//创建时间

    @ApiModelProperty(value = "更新者",required = false)
    @Column(name = "update_by")
    private String updateBy;//更新者

    @ApiModelProperty(value = "更新时间",required = false)
    @Column(name = "update_time")
    private Date updateTime;//更新时间

    @ApiModelProperty(value = "备注",required = false)
    @Column(name = "remark")
    private String remark;//备注

    @ApiModelProperty(value = "用于判断每天的第一次登录",required = false)
    @Column(name = "last_login_time")
    private Date lastLoginTime;//用于判断每天的第一次登录

    @ApiModelProperty(value = "用户来自哪个系统",required = false)
    @Column(name = "from_sys")
    private String fromSys;//用户来自哪个系统

    @ApiModelProperty(value = "微信openid",required = false)
    @Column(name = "openid")
    private String openid;//微信openid

    @ApiModelProperty(value = "微信unionid",required = false)
    @Column(name = "unionid")
    private String unionid;//微信unionid



    //get方法
    public Long getUserId() {
        return userId;
    }

    //set方法
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    //get方法
    public Long getDeptId() {
        return deptId;
    }

    //set方法
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
    //get方法
    public String getLoginName() {
        return loginName;
    }

    //set方法
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    //get方法
    public String getUserName() {
        return userName;
    }

    //set方法
    public void setUserName(String userName) {
        this.userName = userName;
    }
    //get方法
    public String getUserType() {
        return userType;
    }

    //set方法
    public void setUserType(String userType) {
        this.userType = userType;
    }
    //get方法
    public String getEmail() {
        return email;
    }

    //set方法
    public void setEmail(String email) {
        this.email = email;
    }
    //get方法
    public String getPhonenumber() {
        return phonenumber;
    }

    //set方法
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    //get方法
    public String getDescription() {
        return description;
    }

    //set方法
    public void setDescription(String description) {
        this.description = description;
    }
    //get方法
    public Integer getEmailFlag() {
        return emailFlag;
    }

    //set方法
    public void setEmailFlag(Integer emailFlag) {
        this.emailFlag = emailFlag;
    }
    //get方法
    public Integer getPhoneFlag() {
        return phoneFlag;
    }

    //set方法
    public void setPhoneFlag(Integer phoneFlag) {
        this.phoneFlag = phoneFlag;
    }
    //get方法
    public Integer getScore() {
        return score;
    }

    //set方法
    public void setScore(Integer score) {
        this.score = score;
    }
    //get方法
    public String getSex() {
        return sex;
    }

    //set方法
    public void setSex(String sex) {
        this.sex = sex;
    }
    //get方法
    public String getAvatar() {
        return avatar;
    }

    //set方法
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    //get方法
    public String getPassword() {
        return password;
    }

    //set方法
    public void setPassword(String password) {
        this.password = password;
    }
    //get方法
    public String getSalt() {
        return salt;
    }

    //set方法
    public void setSalt(String salt) {
        this.salt = salt;
    }
    //get方法
    public String getStatus() {
        return status;
    }

    //set方法
    public void setStatus(String status) {
        this.status = status;
    }
    //get方法
    public String getDelFlag() {
        return delFlag;
    }

    //set方法
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    //get方法
    public String getLoginIp() {
        return loginIp;
    }

    //set方法
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    //get方法
    public Date getLoginDate() {
        return loginDate;
    }

    //set方法
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    //get方法
    public String getCreateBy() {
        return createBy;
    }

    //set方法
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    //get方法
    public Date getCreateTime() {
        return createTime;
    }

    //set方法
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    //get方法
    public String getUpdateBy() {
        return updateBy;
    }

    //set方法
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    //get方法
    public Date getUpdateTime() {
        return updateTime;
    }

    //set方法
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    //get方法
    public String getRemark() {
        return remark;
    }

    //set方法
    public void setRemark(String remark) {
        this.remark = remark;
    }
    //get方法
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    //set方法
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getFromSys() {
        return fromSys;
    }

    public void setFromSys(String fromSys) {
        this.fromSys = fromSys;
    }

    //get方法
    public String getOpenid() {
        return openid;
    }

    //set方法
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    //get方法
    public String getUnionid() {
        return unionid;
    }

    //set方法
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }


}
