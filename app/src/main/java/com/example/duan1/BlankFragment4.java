package com.example.duan1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class BlankFragment4 extends Fragment {

    LinearLayout layoutofadmin,quanlysanpham ;
    ImageView ic_support;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // vùng phân quyền , cấm đụng
        layoutofadmin = view.findViewById(R.id.layoutofadmin);
        quanlysanpham = view.findViewById(R.id.quanlysanpham);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null); // null là giá trị mặc định nếu không tìm thấy
        if ("admin@gmail.com".equals(email)) {
            layoutofadmin.setVisibility(View.VISIBLE); // Hiện layout cho admin
        } else {
            layoutofadmin.setVisibility(View.INVISIBLE); // Ẩn layout cho người dùng
        }
        ////// vùng phân quyền , cấm đụng













        view.findViewById(R.id.ic_support).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ChatActivity.class));
            }
        });
        view.findViewById(R.id.QuanLyNguoiDung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), UserManagementAcivity.class));
            }
        });
        quanlysanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),QuanLySanPhamActivity.class));
            }
        });
        view.findViewById(R.id.layoutChangePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
            }
        });
        view.findViewById(R.id.layoutDiaChi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddressActivity.class));
            }
        });
        view.findViewById(R.id.layoutmyprofile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyProfileActivity.class));
            }
        });
        view.findViewById(R.id.layoutSignOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa thông tin đăng nhập trong SharedPreferences
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Xóa tất cả dữ liệu
                editor.apply(); // Lưu thay đổi

                // Khởi động lại LoginActivity và xóa các Activity trước đó
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Dọn sạch stack
                startActivity(intent);
            }
        });
        view.findViewById(R.id.layoutshipeed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Shipped.class));
            }
        });
        view.findViewById(R.id.layoutThongKe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ThongKeActivity.class));
            }
        });
        view.findViewById(R.id.layoutdonhanh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DonhangActivity.class));
            }
        });
    }
}