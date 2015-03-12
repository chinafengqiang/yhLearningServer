package com.smlearning.datasync;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.smlearning.infrastructure.utils.DateUtil;


public class MediaFileprog {
    private Integer id;
    private Integer orgId;
    private Integer channelId;
    private Integer sendMode;
    private Integer sendPeriod;
    private Long startValidDate;
    private Long endValidDate;
    private Integer repeatcount;
    private Integer filecount;
    private Long filesize;
    private Float validRate;
    private Integer creatorId;
    private Long creationdate;
    private Integer status;
    private String sendDate;
    private String sendTime;
    private String strEndValidDate;
    private String strStartValidDate;
    private Integer isPermed;
    private Long curSendSize = 0L;
    private Long curSendStartTime = 0L;
    private Long curSendEndTime = 0L;
    private Integer curSendFileId = 0;
    private Long curSendFileLen = 0L;
    private Long curFileToalTime = 0L;
    private float schData=0;
    private String strSchData = "0%";
    
    
    public float getSchData() {
        return schData;
    }
    public void setSchData(float schData) {
        this.schData = schData;
    }
    public String getStrSchData() {
        return strSchData;
    }
    public void setStrSchData(String strSchData) {
        this.strSchData = strSchData;
    }
    public Long getCurSendSize() {
        return curSendSize;
    }
    public void setCurSendSize(Long curSendSize) {
        this.curSendSize = curSendSize;
    }
    public Long getCurSendStartTime() {
        return curSendStartTime;
    }
    public void setCurSendStartTime(Long curSendStartTime) {
        this.curSendStartTime = curSendStartTime;
    }
    public Long getCurSendEndTime() {
        return curSendEndTime;
    }
    public void setCurSendEndTime(Long curSendEndTime) {
        this.curSendEndTime = curSendEndTime;
    }
    public Integer getCurSendFileId() {
        return curSendFileId;
    }
    public void setCurSendFileId(Integer curSendFileId) {
        this.curSendFileId = curSendFileId;
    }
    public Long getCurSendFileLen() {
        return curSendFileLen;
    }
    public void setCurSendFileLen(Long curSendFileLen) {
        this.curSendFileLen = curSendFileLen;
    }
    public Long getCurFileToalTime() {
        return curFileToalTime;
    }
    public void setCurFileToalTime(Long curFileToalTime) {
        this.curFileToalTime = curFileToalTime;
    }
    public void setStrEndValidDate(String strEndValidDate) {
        this.strEndValidDate = strEndValidDate;
        this.endValidDate = DateUtil.dateTimeString2Long(this.strEndValidDate);
    }
    public void setStrStartValidDate(String strStartValidDate) {
        this.strStartValidDate = strStartValidDate;
        this.startValidDate = DateUtil.dateTimeString2Long(this.strStartValidDate);
    }
    public String getStrStartValidDate() {
        String d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long l = getStartValidDate();
        if(l!=null){
            Date date = new Date(l.longValue());
            d = sdf.format(date);
        }
        return d;
    }
    public String getStrEndValidDate() {
        String d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long l = getEndValidDate();
        if(l!=null){
            Date date = new Date(l.longValue());
            d = sdf.format(date);
        }
        return d;
    }
    /**
     * @hibernate.property
     *      column="channel_id"
     *      length="4"
     * @return Returns the channel_id
     */
    public Integer getChannelId() {
        return channelId;
    }
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
    /**
     * @hibernate.property
     *      column="creationdate"
     *      length="8"
     * @return Returns the creationdate
     */
    public Long getCreationdate() {
        return creationdate;
    }
    public void setCreationdate(Long creationdate) {
        this.creationdate = creationdate;
    }
    /**
     * @hibernate.property
     *       column="creator_id"
     *       length="4"
     * @return Returns the creator_id
     */
    public Integer getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
    /**
     * @hibernate.property
     *        column="end_valid_date"
     *        length="8"
     * @return Returns the end_valid_date
     */
    public Long getEndValidDate() {
        return endValidDate;
    }
    public void setEndValidDate(Long endValidDate) {
        this.endValidDate = endValidDate;
    }
    /**
     * @hibernate.property
     *      column="filecount"
     *      length="4"
     * @return Returns the filecount
     */
    public Integer getFilecount() {
        return filecount;
    }
    public void setFilecount(Integer filecount) {
        this.filecount = filecount;
    }
    /**
     * @hibernate.id
     *     column="ID"
     *     length="4"
     *     generator-class="identity"
     * @return Returns the id
     */
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @hibernate.property
     *       column="org_id"
     *       length="4"
     * @return Returns the org_id
     */
    public Integer getOrgId() {
        return orgId;
    }
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
    /**
     * @hibernate.property
     *       column="repeatcount"
     *       length="4"
     * @return Returns the repeatcount
     */
    public Integer getRepeatcount() {
        return repeatcount;
    }
    public void setRepeatcount(Integer repeatcount) {
        this.repeatcount = repeatcount;
    }
    /**
     * @hibernate.property
     *       column="send_mode"
     *       length="4"
     * @return Returns the send_mode
     */
    public Integer getSendMode() {
        return sendMode;
    }
    public void setSendMode(Integer sendMode) {
        this.sendMode = sendMode;
    }
    /**
     * @hibernate.property
     *      column="send_period"
     *      length="4"
     * @return Returns the send_period
     */
    public Integer getSendPeriod() {
        return sendPeriod;
    }
    public void setSendPeriod(Integer sendPeriod) {
        this.sendPeriod = sendPeriod;
    }
    /**
     * @hibernate.property
     *      column="start_valid_date"
     *      length="8"
     * @return Returns the start_valid_date
     */
    public Long getStartValidDate() {
        return startValidDate;
    }
    public void setStartValidDate(Long startValidDate) {
        this.startValidDate = startValidDate;
    }
    /**
     * @hibernate.property
     *       column="valid_rate"
     *       length="8"
     * @return Returns the valid_rate
     */
    public Float getValidRate() {
        return validRate;
    }
    public void setValidRate(Float validRate) {
        this.validRate = validRate;
    }
    /**
     * @hibernate.property
     *    column="status"
     *    length="4"
     * @return Returns the status
     */
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @hibernate.property
     *   column="filesize"
     *   length="20"
     * @return Returns the filesize
     */
    public Long getFilesize() {
        return filesize;
    }
    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }
    /**
     * @hibernate.property
     *    column="send_date"
     *   length="200"
     * @return Returns the send_date
     */
    public String getSendDate() {
        return sendDate;
    }
    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
    /**
     * @hibernate.property
     *    column="send_time"
     *    length="20"
     * @return Returns the send_time
     */
    public String getSendTime() {
        return sendTime;
    }
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
    public Integer getIsPermed() {
        return isPermed;
    }
    public void setIsPermed(Integer isPermed) {
        this.isPermed = isPermed;
    }

}
