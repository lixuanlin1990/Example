package com.lixlop.example.multiscreens;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lixlop.example.R;

public class MultiScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiscreen);
        ImageView image = findViewById(R.id.image);
        image.getLayoutParams().width = getResources().getDisplayMetrics().widthPixels;
        image.getLayoutParams().height = getResources().getDisplayMetrics().widthPixels * 2300 / 1080;
        image.setImageResource(R.mipmap.czj_icon_guide_1);
    }

    /**
     * 裁剪
     *
     * @param bitmap 原图
     * @return 裁剪后的图像
     */
    private Bitmap cropBitmap(Bitmap bitmap) {
        // 得到图片的宽，高
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        return Bitmap.createBitmap(bitmap, 0, h-Resources.getSystem().getDisplayMetrics().heightPixels , w,  Resources.getSystem().getDisplayMetrics().heightPixels, null, false);
    }
}
