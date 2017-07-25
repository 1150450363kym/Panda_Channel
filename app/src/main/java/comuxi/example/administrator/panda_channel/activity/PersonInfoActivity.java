package comuxi.example.administrator.panda_channel.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;

/**
 * Created by Lenovo on 2017/7/21.
 */
//

public class PersonInfoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.person_information_image)
    ImageView personInformationImage;
    @BindView(R.id.img_touxiang)
    ImageView imgTouxiang;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.bt_quit_login)
    Button btQuitLogin;
    private PopupWindow popupWindow;
    private Button btn_camera;
    private Button btn_album;
    private Button btn_cancel;
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;

    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/myHead/";// sd路径
    @Override
    protected void initView() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("username");
        tvNickname.setText(stringExtra);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personinformation;
    }

    @OnClick({R.id.person_information_image, R.id.img_touxiang, R.id.tv_nickname, R.id.bt_quit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_information_image:
                finish();
                break;
            case R.id.img_touxiang:

                init();
                popwindow();
                break;
            case R.id.tv_nickname:
                Intent intent=new Intent(PersonInfoActivity.this,PersonalNickNameActivity.class);
                intent.putExtra("nickname",tvNickname.getText().toString());
                startActivity(intent);
                break;
            case R.id.bt_quit_login:
                ACache aCache=ACache.get(PersonInfoActivity.this);
                aCache.clear();
                Intent intent2=getIntent();
                setResult(3000,intent2);
                finish();
                break;
        }
    }

    private void init() {

        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            imgTouxiang.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }
    }
    public void popwindow() {
        popupWindow = new PopupWindow();
        View view = LayoutInflater.from(PersonInfoActivity.this).inflate(
                R.layout.item_popwindow, null);
        popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的

        ColorDrawable dw = new ColorDrawable(0x30000000);
        popupWindow.setBackgroundDrawable(dw);

        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        btn_camera = (Button) view.findViewById(R.id.btn_camera);
        btn_album = (Button) view.findViewById(R.id.btn_album);
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);

        btn_camera.setOnClickListener(this);
        btn_album.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:

                // 从本地相册选取图片作为头像
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                popupWindow.dismiss();

                break;
            case R.id.btn_album:

                // 启动手机相机拍摄照片作为头像

                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                popupWindow.dismiss();

                break;
            case R.id.btn_cancel:
                popupWindow.dismiss();
                break;
        }
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            imgTouxiang.setImageBitmap(photo);
            AlertDialog.Builder dilog = new AlertDialog.Builder(this);
            dilog.setMessage("您的头像已提交审核请稍后回来确认吧");
            dilog.setNegativeButton("我知道啦", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);// 保存在SD卡中
                        imgTouxiang.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//                                    Intent intent) {
//
//        // 用户没有进行有效的设置操作，返回
//        if (resultCode == RESULT_CANCELED) {
//            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        switch (requestCode) {
//            case CODE_GALLERY_REQUEST:
//                cropRawPhoto(intent.getData());
//                break;
//
//            case CODE_CAMERA_REQUEST:
//                if (hasSdcard()) {
//                    File tempFile = new File(
//                            Environment.getExternalStorageDirectory(),
//                            IMAGE_FILE_NAME);
//                    cropRawPhoto(Uri.fromFile(tempFile));
//                } else {
//                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
//                            .show();
//                }
//
//                break;
//
//            case CODE_RESULT_REQUEST:
//                if (intent != null) {
//
//                    setImageToHeadView(intent);
//                }
//
//                break;
//        }
//
//        super.onActivityResult(requestCode, resultCode, intent);
//    }

    /**
     * 裁剪原始的图片
     */
//    public void cropRawPhoto(Uri uri) {
//
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//
//        // 设置裁剪
//        intent.putExtra("crop", "true");
//
//        // aspectX , aspectY :宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//
//        // outputX , outputY : 裁剪图片宽高
//        intent.putExtra("outputX", output_X);
//        intent.putExtra("outputY", output_Y);
//        intent.putExtra("return-data", true);
//
//        startActivityForResult(intent, CODE_RESULT_REQUEST);
//    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

}