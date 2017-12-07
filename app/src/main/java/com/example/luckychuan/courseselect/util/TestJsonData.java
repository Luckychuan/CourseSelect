package com.example.luckychuan.courseselect.util;

import com.example.luckychuan.courseselect.bean.CourseJson;
import com.example.luckychuan.courseselect.bean.TeacherJson;

import java.util.ArrayList;

/**
 * Created by Luckychuan on 2017/11/25.
 */

public class TestJsonData {

    //假的课程数据
    public static ArrayList<CourseJson> getTestCourseData(){
        ArrayList<CourseJson> courseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CourseJson courseJson= new CourseJson();
            courseJson.setCampus("曲江校区");
            courseJson.setLevel(1);
            courseJson.setWeek(1);
            courseJson.setSection(3);
            CourseJson.Course[] courses = new CourseJson.Course[10];
            for (int j = 0; j < 10; j++) {
                courses[j] = new CourseJson.Course();
                courses[j].setId("201720130");
                courses[j].setName("体育基础131 杜云(7)");
                courses[j].setStudentLeft("7");
                courses[j].setTeacher("杜云");
                courses[j].setTeacherId("1000007");
            }
            courseJson.setCourses(courses);
            courseList.add(courseJson);
        }
        return courseList;
    }

    //假的教师数据
    public static TeacherJson getTestTeacherData(){
        TeacherJson.Data data = new TeacherJson.Data();
        data.setUserKey("1000011_111.19.43.229_20171201160102");
        data.setId("1000011");
        data.setName("我是老师");
        data.setXingBie("性别1");
        data.setChuShengRiQi("出生日期1");
        data.setBuMen("部门1");
        data.setKeShi("科室1");
        data.setZhiCheng("职称1");
        data.setXueLi("学历1");
        data.setJianLi("建立1");

        TeacherJson teacherJson = new TeacherJson();
        teacherJson.setSuccess(true);
        teacherJson.setMsgCode(0);
        teacherJson.setData(data);
        return  teacherJson;
    }

}
