package com.phong.model;

import androidx.annotation.NonNull;

public class MatHang {
    protected String tenMatHang;

    public MatHang() {
    }

    public MatHang(String tenMatHang) {
        this.tenMatHang = tenMatHang;
    }

    public String getTenMatHang() {
        return tenMatHang;
    }

    public void setTenMatHang(String tenMatHang) {
        this.tenMatHang = tenMatHang;
    }

    @NonNull
    @Override
    public String toString() {
        return this.tenMatHang;
    }
}
