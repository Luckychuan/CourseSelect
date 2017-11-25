package com.example.luckychuan.courseselect.test;

import com.example.luckychuan.courseselect.bean.CourseJson;

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

}
