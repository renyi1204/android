package com.ry_050.ducheyicheapplication.ui.data;

import java.util.List;

public class MallCityListBean {
    private String cityCode;
    private String cityName;
    private List<MallAreaListBean> mallAreaList;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<MallAreaListBean> getMallAreaList() {
        return mallAreaList;
    }

    public void setMallAreaList(List<MallAreaListBean> mallAreaList) {
        this.mallAreaList = mallAreaList;
    }
}
