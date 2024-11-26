package com.example.duan1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.duan1.Adapter.SanPhamRecyclerViewAdapter;
import com.example.duan1.Dao.SanPhamDAO;
import com.example.duan1.Models.SanPham;

public class ThemSanPhamActivity extends AppCompatActivity {
    SanPhamDAO sanPhamDAO;
    Button btnLuu,btnChonAnh;
    EditText etTenSanPham,etGiaSanPham,etMoTaSanPham;
    ImageView imgSanPham;
    private static final int REQUEST_CODE_CHON_ANH = 1;
    private Uri imageUri;
    private int maDanhMuc = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.themsanpham);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnLuu = findViewById(R.id.btnLuuSanPham);
        btnChonAnh = findViewById(R.id.btnChonAnh);
        etTenSanPham = findViewById(R.id.etTenSanPham);
        etGiaSanPham = findViewById(R.id.etGiaSanPham);
        etMoTaSanPham = findViewById(R.id.etMoTaSanPham);
        imgSanPham = findViewById(R.id.imgSanPham);
        sanPhamDAO = new SanPhamDAO(this);
        btnChonAnh.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,REQUEST_CODE_CHON_ANH);
        });
        btnLuu.setOnClickListener(v->{

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CHON_ANH && data != null) {
            try {
                // Gán giá trị cho imageUri
                imageUri = data.getData();

                // Lấy Bitmap từ Uri
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                // Hiển thị ảnh lên ImageView
                imgSanPham.setImageBitmap(bitmap);

                // Lấy các thông tin khác của sản phẩm
                String tenSanPham = etTenSanPham.getText().toString();
                float giaSanPham = Float.parseFloat(etGiaSanPham.getText().toString());
                String moTaSanPham = etMoTaSanPham.getText().toString();

                // Lấy tên file từ Uri
                String fileName = getFileNameFromUri(imageUri);

                // Lưu ảnh vào thư mục drawable (hoặc thư mục bạn muốn)

                // ... (Code để lưu ảnh vào thư mục drawable)

                // Tạo đối tượng SanPham với tên file ảnh
                SanPham sanphamMoi = new SanPham(tenSanPham, giaSanPham, moTaSanPham, maDanhMuc, 0, fileName, false);

                //Thêm sản phẩm vào database
                long result = sanPhamDAO.insert(sanphamMoi);

                if (result > 0) {
                    Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("sanpham_added");
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Hiển thị thông báo nếu chưa chọn ảnh
            Toast.makeText(this, "Vui lòng chọn ảnh sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }
    // hàm lấy tên file từ uri
    private String getFileNameFromUri(Uri uri){
        String fileName = null;
        String scheme = uri.getScheme();
        if (scheme.equals("file")) {fileName = uri.getLastPathSegment();} else if (scheme.equals("content")){
            Cursor cursor = getContentResolver().query(uri,null,null,null,null);
            if (cursor != null && cursor.moveToFirst()) {
// Kiểm tra nếu cột DISPLAY_NAME tồn tại
                int columnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (columnIndex != -1) {
                    fileName = cursor.getString(columnIndex);
                }
                cursor.close();
            }


    }
        return  fileName;
        }
}
