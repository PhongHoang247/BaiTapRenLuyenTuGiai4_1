package com.phong.model;

import android.widget.Toast;

import com.phong.baitaprenluyentugiai4_1.MainActivity;
import com.phong.baitaprenluyentugiai4_1.R;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhMuc extends MatHang {
    protected ArrayList<SanPham> dsSP = null;

    public DanhMuc(String tenMatHang) {
        super(tenMatHang);
        this.dsSP = new ArrayList<SanPham>();
    }

    public DanhMuc() {
        super();
        this.dsSP = new ArrayList<SanPham>();
    }

    /**
     * kiểm tra sản phẩm đã tồn tại trong danh mục hay chưa
     * @param p
     * @return true nếu tồn tại
     */
    public boolean isDuplicate(SanPham p)
    {
        for (SanPham p1:dsSP)
        {
            if (p1.getTenMatHang().trim().equalsIgnoreCase(p.getTenMatHang().trim()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * thêm 1 sản phẩm vào danh mục
     * thêm thành công = true
     * @param p
     * @return
     */

    public boolean themSanPham(SanPham p)
    {
        boolean isDup = isDuplicate(p);
        if (!isDup)
        {
            p.setDanhMuc(this);
            return dsSP.add(p);
        }
        return !isDup;
    }

    public ArrayList<SanPham>getDanhSachSanPham(){
        return this.dsSP;
    }

    public int size(){
        return dsSP.size();
    }

    public SanPham get(int i){
        return dsSP.get(i);
    }
}
