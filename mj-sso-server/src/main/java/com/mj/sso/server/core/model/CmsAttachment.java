package com.mj.sso.server.core.model;

import java.time.LocalDateTime;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 
 *
 * @author piggy
 * @date 2021-11-13
 */
@ApiModel(value="CmsAttachment", description="")
@Table(name="cms_attachment")
public class CmsAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
     * id
     */
    @ApiModelProperty(value = "id")  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attach_id")
    private String attachId;
	/**
     * 组ID
     */
    @ApiModelProperty(value = "组ID")  
    @Column(name = "zid")
    private String zid;

    @Column(name = "user_id")
    private String userId;

	/**
     * 文件类型，引用cms_filetype表ID
     */
    @ApiModelProperty(value = "文件类型，引用cms_filetype表ID")
    @Column(name = "file_type")
    private String fileType;

	/**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")  
    @Column(name = "file_name")
    private String fileName;

	/**
     * 相对路径
     */
    @ApiModelProperty(value = "相对路径")  
    @Column(name = "file_path")
    private String filePath;

	/**
     * url
     */
    @ApiModelProperty(value = "url")  
    @Column(name = "file_url")
    private String fileUrl;

	/**
     * 文件空间
     */
    @ApiModelProperty(value = "文件空间")  
    @Column(name = "size")
    private Integer size;

	/**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")  
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private LocalDateTime createTime;

	/**
     * 排序
     */
    @ApiModelProperty(value = "排序")  
    @Column(name = "sort")
    private Integer sort;


    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "CmsAttachment{" +
        "attachId=" + attachId +
        ", zid=" + zid +
        ", userId=" + userId +
        ", fileType=" + fileType +
        ", fileName=" + fileName +
        ", filePath=" + filePath +
        ", fileUrl=" + fileUrl +
        ", size=" + size +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", sort=" + sort +
        "}";
    }
}
