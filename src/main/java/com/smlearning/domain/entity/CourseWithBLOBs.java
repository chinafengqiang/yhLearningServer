package com.smlearning.domain.entity;

public class CourseWithBLOBs extends Course {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.teacher_description
     *
     * @mbggenerated
     */
    private String teacherDescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.course_description
     *
     * @mbggenerated
     */

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.description
     *
     * @return the value of course.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.description
     *
     * @param description the value for course.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.teacher_description
     *
     * @return the value of course.teacher_description
     *
     * @mbggenerated
     */
    public String getTeacherDescription() {
        return teacherDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.teacher_description
     *
     * @param teacherDescription the value for course.teacher_description
     *
     * @mbggenerated
     */
    public void setTeacherDescription(String teacherDescription) {
        this.teacherDescription = teacherDescription == null ? null : teacherDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.course_description
     *
     * @return the value of course.course_description
     *
     * @mbggenerated
     */

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.course_description
     *
     * @param courseDescription the value for course.course_description
     *
     * @mbggenerated
     */
}