package com.ry_050.ducheyicheapplication.ui.data;

import java.util.List;

public class MallprovinceListBean {

    private String provinceCode;
    private String provinceName;
    private List<MallCityListBean> mallCityList;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<MallCityListBean> getMallCityList() {
        return mallCityList;
    }

    public void setMallCityList(List<MallCityListBean> mallCityList) {
        this.mallCityList = mallCityList;
    }
}
