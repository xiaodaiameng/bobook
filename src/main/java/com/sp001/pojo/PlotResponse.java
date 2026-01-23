package com.sp001.pojo;

import java.util.List;

public class PlotResponse {
    private boolean success;
    private String imageBase64;  // 对应Python返回的imageBase64字段
    private List<Integer> data;

    // 正确的getter和setter
    public String getImageBase64() {
        return imageBase64;  // 返回真实字段值，而非硬编码字符串
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}