package com.smlearning.domain.entity;

import java.util.Date;

public class CoursewareCategory {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware_category.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware_category.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware_category.parent_id
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware_category.sort_flag
     *
     * @mbggenerated
     */
    private Integer sortFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware_category.created_time
     *
     * @mbggenerated
     */
    private Date createdTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware_category.creator
     *
     * @mbggenerated
     */
    private Long creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware_category.use_type
     *
     * @mbggenerated
     */
    private Integer useType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware_category.id
     *
     * @return the value of courseware_category.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware_category.id
     *
     * @param id the value for courseware_category.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware_category.name
     *
     * @return the value of courseware_category.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware_category.name
     *
     * @param name the value for courseware_category.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware_category.parent_id
     *
     * @return the value of courseware_category.parent_id
     *
     * @mbggenerated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware_category.parent_id
     *
     * @param parentId the value for courseware_category.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware_category.sort_flag
     *
     * @return the value of courseware_category.sort_flag
     *
     * @mbggenerated
     */
    public Integer getSortFlag() {
        return sortFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware_category.sort_flag
     *
     * @param sortFlag the value for courseware_category.sort_flag
     *
     * @mbggenerated
     */
    public void setSortFlag(Integer sortFlag) {
        this.sortFlag = sortFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware_category.created_time
     *
     * @return the value of courseware_category.created_time
     *
     * @mbggenerated
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware_category.created_time
     *
     * @param createdTime the value for courseware_category.created_time
     *
     * @mbggenerated
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware_category.creator
     *
     * @return the value of courseware_category.creator
     *
     * @mbggenerated
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware_category.creator
     *
     * @param creator the value for courseware_category.creator
     *
     * @mbggenerated
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware_category.use_type
     *
     * @return the value of courseware_category.use_type
     *
     * @mbggenerated
     */
    public Integer getUseType() {
        return useType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware_category.use_type
     *
     * @param useType the value for courseware_category.use_type
     *
     * @mbggenerated
     */
    public void setUseType(Integer useType) {
        this.useType = useType;
    }
}