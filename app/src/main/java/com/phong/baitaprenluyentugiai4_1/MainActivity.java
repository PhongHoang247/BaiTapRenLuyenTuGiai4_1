package com.phong.baitaprenluyentugiai4_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import com.phong.model.DanhMuc;
import com.phong.model.SanPham;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerDanhMuc;
    //Cặp đối tượng dùng cho Spinner
    ArrayList<DanhMuc> arraySpinner = new ArrayList<DanhMuc>();
    ArrayAdapter<DanhMuc> adapterSpinner = null;

    AutoCompleteTextView autoCompleteSanPham;
    //Cặp đối tượng dùng cho AutoCompleteTextView
    ArrayList<String> arraySanPham = new ArrayList<String>();
    ArrayAdapter<String> adapterSanPham = null;

    GridView gvSanPham;
    //Cặp đối tượng dùng cho GridView
    ArrayList<SanPham> arrayGridView = new ArrayList<SanPham>();
    ArrayAdapter<SanPham> adapterGridView = null;

    Button btnNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        fakeData();
        addEvents();
    }

    private void addEvents() {
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themSanPhamChoDanhMuc();
            }
        });
        spinnerDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //mỗi lần chọn danh mục trong Spinner thì cập nhập GridView
                taiDanhSachSanPhamChoDanhMuc(arraySpinner.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        gvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chonSanPham(adapterGridView.getItem(i));
            }
        });
    }

    private void chonSanPham(SanPham item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //Thiết lập tiêu đề:
        builder.setTitle("Thông tin chi tiết");
        //Thiết lập icon:
        builder.setIcon(android.R.drawable.ic_dialog_info);
        //Thiết lập nội dung:
        builder.setMessage(item + "");
        //Thiết lập nút lệnh:
        /*builder.setNeutralButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });*/
        //Tạo cửa sổ Dialog:
        AlertDialog dialog = builder.create();
        //Hiển thị cửa sổ Dialog:
        dialog.show();
    }

    /**
     * Lọc danh sách sản phẩm theo danh mục và update lại GridView
     * @param c
     */
    private void taiDanhSachSanPhamChoDanhMuc(DanhMuc c) {
        //Xoá danh sách cũ:
        arrayGridView.clear();
        //lấy danh sách mới từ DanhMuc chọn trong Spinner:
        arrayGridView.addAll(c.getDanhSachSanPham());
        //Cập nhật lại GridView:
        adapterGridView.notifyDataSetChanged();
    }

    private void themSanPhamChoDanhMuc() {
        SanPham p = new SanPham();
        p.setTenMatHang(autoCompleteSanPham.getText() + "");
        DanhMuc c = (DanhMuc) spinnerDanhMuc.getSelectedItem();
        c.themSanPham(p);
        //Mỗi lần thêm xong thì cập nhập lại GridView
        taiDanhSachSanPhamChoDanhMuc(c);
        //Sau khi nhập thì xóa trắng autoComplete
        autoCompleteSanPham.setText("");
    }

    private void fakeData() {
        DanhMuc danhMuc1 = new DanhMuc("Giải khát");
        DanhMuc danhMuc2 = new DanhMuc("Điện thoại");
        DanhMuc danhMuc3 = new DanhMuc("Thực phẩm");
        arraySpinner.add(danhMuc1);
        arraySpinner.add(danhMuc2);
        arraySpinner.add(danhMuc3);
        adapterSpinner.notifyDataSetChanged();
    }

    private void addControls() {
        spinnerDanhMuc = (Spinner) findViewById(R.id.spinnerDanhMuc);
        autoCompleteSanPham = (AutoCompleteTextView) findViewById(R.id.autoCompleteSanPham);
        gvSanPham = (GridView) findViewById(R.id.gvSanPham);
        btnNhap = (Button) findViewById(R.id.btnNhap);

        //Cấu hình cho Spinner:
        adapterSpinner = new ArrayAdapter<DanhMuc>(
                MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                arraySpinner);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDanhMuc.setAdapter(adapterSpinner);

        //Cấu hình cho GridView:
        adapterGridView = new ArrayAdapter<SanPham>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayGridView);
        gvSanPham.setAdapter(adapterGridView);

        //Cấu hình cho AutoCompleteTextView:
        adapterSanPham = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arraySanPham);
        autoCompleteSanPham.setAdapter(adapterSanPham);
        //Nạp dữ liệu:
        adapterSanPham.addAll(getResources().getStringArray(R.array.arrSanPham));
    }
}
