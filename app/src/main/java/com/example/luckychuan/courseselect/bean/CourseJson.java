package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * 可选课程
 */

public class CourseJson {

    @SerializedName("XQ")
    private String campus;    //校区
    @SerializedName("JB")
    private int level;     //等级
    @SerializedName("ZC")
    private int week;     //星期几
    @SerializedName("JC")
    private int section;   //节次
    private Course[] courses;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "CourseJson{" +
                "campus='" + campus + '\'' +
                ", level=" + level +
                ", section=" + section +
                ", week=" + week +
                ", courses=" + Arrays.toString(courses) +
                '}';
    }

    public static class Course {
        @SerializedName("KCMC")
        private String name;
        @SerializedName("SHENGYU")
        private String studentLeft;  //剩余名额
        @SerializedName("RKJS")
        private String teacher;
        @SerializedName("ZGH")
        private String teacherId;
        @SerializedName("KCBH")
        private String id;

        @Override
        public String toString() {
            return "Course{" +
                    "name='" + name + '\'' +
                    ", studentLeft='" + studentLeft + '\'' +
                    ", teacher='" + teacher + '\'' +
                    ", teacherId='" + teacherId + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStudentLeft() {
            return studentLeft;
        }

        public void setStudentLeft(String studentLeft) {
            this.studentLeft = studentLeft;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }


    }


}
