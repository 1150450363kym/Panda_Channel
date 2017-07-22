//package comuxi.example.administrator.panda_channel.activity;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.support.v7.widget.Toolbar;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.io.File;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import comuxi.example.administrator.panda_channel.Base.BaseActivity;
//import comuxi.example.administrator.panda_channel.R;
//import comuxi.example.administrator.panda_channel.Utils.ACache;
//import comuxi.example.administrator.panda_channel.Utils.Log_Utils;
//import comuxi.example.administrator.panda_channel.app.App;
//
//public class PersonInfoActivity extends BaseActivity {
////    @Override
////    protected int getLayoutId() {
////        return R.layout.activity_personal_center;
////    }
////
////    @Override
////    protected void initView() {
////
////    }
//
//    @BindView(R.id.info_back)
//    ImageView infoBack;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.info_imageheader)
//    ImageView infoImageheader;
//    @BindView(R.id.image_tophoto)
//    ImageView imageTophoto;
//    @BindView(R.id.tv_info_username)
//    TextView tvInfoUsername;
//    @BindView(R.id.image_toname)
//    ImageView imageToname;
//    @BindView(R.id.btn_dropout)
//    TextView btnDropout;
//    @BindView(R.id.activity_person_info)
//    LinearLayout activityPersonInfo;
//    private String path ;
//    /* 请求识别码 */
//    private static final int CODE_GALLERY_REQUEST = 0xa0;
//    private static final int CODE_CAMERA_REQUEST = 0xa1;
//    private static final int CODE_RESULT_REQUEST = 0xa2;
//    /* 头像文件 */
//    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
//    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
//    private static int output_X = 480;
//    private static int output_Y = 480;
//     Intent in;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_person_info;
//    }
//
//    @Override
//    protected void initView() {
//
//        Intent intent = getIntent();
//        in = getIntent();
//        String username = intent.getStringExtra("username");
//        tvInfoUsername.setText(username);
//    }
//
//    @OnClick({R.id.info_back, R.id.info_imageheader, R.id.image_tophoto, R.id.tv_info_username, R.id.image_toname, R.id.btn_dropout, R.id.activity_person_info})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.info_back:
//
//                finish();
//                break;
//            case R.id.info_imageheader:
//
//                showPopwindow();
//
//
//                break;
//            case R.id.image_tophoto:
//
//                showPopwindow();
//                break;
//            case R.id.tv_info_username:
//
//                startActivity(new Intent(this,PersonalNickNameActivity.class));
//                break;
//            case R.id.image_toname:
//
//                Intent intent = new Intent(this,PersonalNickNameActivity.class);
//                intent.putExtra("name",tvInfoUsername.getText().toString());
//                Log_Utils.log_d("TAG",tvInfoUsername.getText().toString());
//                startActivity(intent);
//                break;
//            case R.id.btn_dropout:
//
//                ACache acache= ACache.get(App.content);
//                acache.clear();
//
//                setResult(3000,in);
//                finish();
//
//                break;
//            case R.id.activity_person_info:
//                break;
//        }
//    }
//
//
//    private void showPopwindow() {
//        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
//        View popView = View.inflate(this, R.layout.popu_window, null);
//
//        Button btnCamera = (Button) popView.findViewById(R.id.btn_camera_pop_camera);
//        Button btnAlbum = (Button) popView.findViewById(R.id.btn_camera_pop_album);
//        Button btnCancel = (Button) popView.findViewById(R.id.btn_camera_pop_cancel);
//
//        int width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels;
//
//        final PopupWindow popWindow = new PopupWindow(popView,width,height);
////        popWindow.setAnimationStyle(R.style.AnimBottom);
//        popWindow.setFocusable(true);
//        popWindow.setOutsideTouchable(false);// 设置允许在外点击消失
//
//        View.OnClickListener listener = new View.OnClickListener() {
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.btn_camera_pop_camera:
//
//                        // 从本地相册选取图片作为头像
//
//                        Intent intentFromGallery = new Intent();
//                        // 设置文件类型
//                        intentFromGallery.setType("image/*");
//                        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
//
//
//                    break;
//                    case R.id.btn_camera_pop_album:
//
//                        // 启动手机相机拍摄照片作为头像
//
//                        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                        // 判断存储卡是否可用，存储照片文件
//                        if (hasSdcard()) {
//                            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
//                                    .fromFile(new File(Environment
//                                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
//                        }
//
//                        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
//
//                    break;
//                    case R.id.btn_camera_pop_cancel:
//
//                        popWindow.dismiss();
//                        break;
//                }
//                popWindow.dismiss();
//            }
//        };
//
//        btnCamera.setOnClickListener(listener);
//        btnAlbum.setOnClickListener(listener);
//        btnCancel.setOnClickListener(listener);
//
//        ColorDrawable dw = new ColorDrawable(0x30000000);
//        popWindow.setBackgroundDrawable(dw);
//        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//    }
//
//    /**
//     * 提取保存裁剪之后的图片数据，并设置头像部分的View
//     */
//    private void setImageToHeadView(Intent intent) {
//        Bundle extras = intent.getExtras();
//        if (extras != null) {
//            Bitmap photo = extras.getParcelable("data");
//            infoImageheader.setImageBitmap(photo);
////            AlertDialog.Builder dilog = new AlertDialog.Builder(this);
////            dilog.setMessage("您的头像已提交审核请稍后回来确认吧");
////            dilog.setNegativeButton("我知道啦", new DialogInterface.OnClickListener() {
////                @Override
////                public void onClick(DialogInterface dialog, int which) {
////                    dialog.dismiss();
////                }
////            });
//
//        }
//    }
//
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
//    /**
//     * 裁剪原始的图片
//     */
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
//    /**
//     * 检查设备是否存在SDCard的工具方法
//     */
//    public static boolean hasSdcard() {
//        String state = Environment.getExternalStorageState();
//        if (state.equals(Environment.MEDIA_MOUNTED)) {
//            // 有存储的SDCard
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
