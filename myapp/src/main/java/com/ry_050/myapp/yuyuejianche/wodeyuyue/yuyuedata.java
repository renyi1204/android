package com.ry_050.myapp.yuyuejianche.wodeyuyue;

import java.util.List;

public class yuyuedata {

    private int total;
    private List<RowsBean> rows;
    private int code;
    private String msg;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class RowsBean {
        private int id;
        private int userId;
        private Object plateNum;
        private Object mainNum;
        private Object carType;
        private Object mileage;
        private Object carPhone;
        private String placeName;
        private String remarks;
        private String address;
        private String phone;
        private String aptTime;
        private String success;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getPlateNum() {
            return plateNum;
        }

        public void setPlateNum(Object plateNum) {
            this.plateNum = plateNum;
        }

        public Object getMainNum() {
            return mainNum;
        }

        public void setMainNum(Object mainNum) {
            this.mainNum = mainNum;
        }

        public Object getCarType() {
            return carType;
        }

        public void setCarType(Object carType) {
            this.carType = carType;
        }

        public Object getMileage() {
            return mileage;
        }

        public void setMileage(Object mileage) {
            this.mileage = mileage;
        }

        public Object getCarPhone() {
            return carPhone;
        }

        public void setCarPhone(Object carPhone) {
            this.carPhone = carPhone;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAptTime() {
            return aptTime;
        }

        public void setAptTime(String aptTime) {
            this.aptTime = aptTime;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }
    }
}
