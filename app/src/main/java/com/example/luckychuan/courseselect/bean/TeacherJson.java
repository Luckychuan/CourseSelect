package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Luckychuan on 2017/12/1.
 */

public class TeacherJson {
    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("msgcode")
    private int msgCode;
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    @Override
    public String toString() {
        return "StudentJson{" +
                "isSuccess=" + isSuccess +
                ", msgCode=" + msgCode +
                ", data=" + data +
                '}';
    }

    public class Data implements Serializable{
        @SerializedName("lingPai")
        private String userKey;
        @SerializedName("zhiGongHao")
        private String id;
        @SerializedName("xingMing")
        private String name;
        private String xingBie;
        private String chuShengRiQi;
        private String buMen;
        private String keShi;
        private String zhiCheng;
        private String xueLi;
        @SerializedName("miMa")
        private String password;
        private String jianLi;

        @Override
        public String toString() {
            return "Data{" +
                    "userKey='" + userKey + '\'' +
                    ", id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", xingBie='" + xingBie + '\'' +
                    ", chuShengRiQi='" + chuShengRiQi + '\'' +
                    ", buMen='" + buMen + '\'' +
                    ", keShi='" + keShi + '\'' +
                    ", zhiCheng='" + zhiCheng + '\'' +
                    ", xueLi='" + xueLi + '\'' +
                    ", password='" + password + '\'' +
                    ", jianLi='" + jianLi + '\'' +
                    '}';
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
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

        public String getXingBie() {
            return xingBie;
        }

        public void setXingBie(String xingBie) {
            this.xingBie = xingBie;
        }

        public String getChuShengRiQi() {
            return chuShengRiQi;
        }

        public void setChuShengRiQi(String chuShengRiQi) {
            this.chuShengRiQi = chuShengRiQi;
        }

        public String getBuMen() {
            return buMen;
        }

        public void setBuMen(String buMen) {
            this.buMen = buMen;
        }

        public String getKeShi() {
            return keShi;
        }

        public void setKeShi(String keShi) {
            this.keShi = keShi;
        }

        public String getZhiCheng() {
            return zhiCheng;
        }

        public void setZhiCheng(String zhiCheng) {
            this.zhiCheng = zhiCheng;
        }

        public String getXueLi() {
            return xueLi;
        }

        public void setXueLi(String xueLi) {
            this.xueLi = xueLi;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getJianLi() {
            return jianLi;
        }

        public void setJianLi(String jianLi) {
            this.jianLi = jianLi;
        }
    }


}
