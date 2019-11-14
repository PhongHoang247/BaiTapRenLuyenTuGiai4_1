package com.phong.model;

import java.io.Serializable;

public class SanPham extends MatHang {
    //Lấy tham chiếu để lập trình cho lẹ
    protected DanhMuc danhMuc;

    public SanPham() {
    }

    public SanPham(String tenMatHang) {
        super(tenMatHang);
    }

    public SanPham(String tenMatHang, DanhMuc danhMuc) {
        super(tenMatHang);
        this.danhMuc = danhMuc;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }
}
