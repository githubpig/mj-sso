package com.mj.sso.server.core.model;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * sso集成应用表
 *
 * @author piggy
 * @date 2021-11-13
 */
@ApiModel(value="SysSso", description="sso集成应用表")
@Table(name="sys_sso")
public class SysSso implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    @ApiModelProperty(value = "主键")  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

	/**
     * 应用logo
     */
    @ApiModelProperty(value = "应用logo")
    @Column(name = "logo")
    private String logo;

	/**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")  
    @Column(name = "app_name")
    private String appName;

	/**
     * 应用登录url
     */
    @ApiModelProperty(value = "应用登录url")  
    @Column(name = "sso_login")
    private String ssoLogin;

	/**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")  
    @Column(name = "createtime")
    private LocalDateTime createtime;

	/**
     * 状态（启用:1,停用:0）
     */
    @ApiModelProperty(value = "状态（启用:1,停用:0）")  
    @Column(name = "status")
    private Integer status;

    @Transient
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSsoLogin() {
        return ssoLogin;
    }

    public void setSsoLogin(String ssoLogin) {
        this.ssoLogin = ssoLogin;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysSso{" +
        "id=" + id +
        ", logo=" + logo +
        ", appName=" + appName +
        ", ssoLogin=" + ssoLogin +
        ", createtime=" + createtime +
        ", status=" + status +
        "}";
    }
}
