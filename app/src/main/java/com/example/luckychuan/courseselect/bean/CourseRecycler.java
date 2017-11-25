package com.example.luckychuan.courseselect.bean;


/**
 * RecyclerView使用的bean
 */

public class CourseRecycler {

    private String name;
    private String time;
    private String teacher;
    private int level;
    private String studentLeft;
    private String teacherId;
    private String id;

    public CourseRecycler(String name, String time, String teacher, int level, String studentLeft, String teacherId, String id) {
        this.name = name;
        this.time = time;
        this.teacher = teacher;
        this.level = level;
        this.studentLeft = studentLeft;
        this.teacherId = teacherId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getLevel() {
        return level;
    }

    public String getStudentLeft() {
        return studentLeft;
    }

    public void setStudentLeft(String studentLeft) {
        this.studentLeft = studentLeft;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CourseRecycler{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", teacher='" + teacher + '\'' +
                ", level=" + level +
                ", studentLeft='" + studentLeft + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
