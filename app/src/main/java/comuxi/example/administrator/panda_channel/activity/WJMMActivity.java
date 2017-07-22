package comuxi.example.administrator.panda_channel.activity;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lizhuofang on 2017/7/14.
 */
public class WJMMActivity extends BaseActivity {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.register_forget_number)
    EditText forgetNumber;
    @BindView(R.id.register_forget_photoyanzheng)
    EditText forgetPhotoyanzheng;
    @BindView(R.id.licechina_forget_imgeyanzheng)
    ImageView forgetImgeyanzheng;
    @BindView(R.id.register_forget_reveive)
    EditText forgetReveive;
    @BindView(R.id.register_forget_butreceive)
    Button forgetButreceive;
    @BindView(R.id.register_forget_setpass)
    EditText forgetSetpass;
    @BindView(R.id.register_forget_button)
    Button forgetButton;
    private OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    private byte[] bytes;
    private String jsonid;
    private ACache aCache;

    @Override
    protected void initView() {
//        aCache = ACache.get(App.activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.livechina_forget_pass;
    }

    @OnClick({R.id.imageView, R.id.licechina_forget_imgeyanzheng, R.id.register_forget_butreceive, R.id.register_forget_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                finish();
                break;
            case R.id.licechina_forget_imgeyanzheng:
               getPhoto();
                break;
            case R.id.register_forget_butreceive:
                getMessage();


                break;
            case R.id.register_forget_button:
                register();
                break;
        }
    }
    /*
    * 图片验证码
    * */
    private void getPhoto() {
        String from = "http://reg.cntv.cn/simple/verificationCode.action";
        final Request request = new Request.Builder()
                .url(from)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                bytes = response.body().bytes();
                Headers headers = response.headers();
                jsonid = headers.get("Set-Cookie");


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        forgetImgeyanzheng.setImageDrawable(byteToDrawable(bytes));
                    }
                });

            }
        });
    }
    public Drawable byteToDrawable(byte[] buteArray) {
        try {
            String string = new String(buteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ByteArrayInputStream ins = new ByteArrayInputStream(buteArray);
        return Drawable.createFromStream(ins, null);
    }

    /*
   * 短信验证码
   * */
    private void getMessage() {
        String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
        String from = "http://cbox_mobile.regclientuser.cntv.cn";
//                    手机号
        String phoneNumber = forgetNumber.getText().toString().trim();
//                    验证码
        String imgyanzhengma = forgetPhotoyanzheng.getText().toString().trim();

        RequestBody body = new FormBody.Builder()
                .add("method", "getRequestVerifiCodeM")
                .add("mobile", phoneNumber)
                .add("verfiCodeType", "1")
                .add("verificationCode", imgyanzhengma)
                .build();

        try {
            Request request = new Request.Builder().url(url)
                    .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .addHeader("Cookie", jsonid)
                    .post(body).build();
//            aCache.put("Cookie", jsonid);
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("TAG", "失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    Log.i("TAG", "手机验证码打印：" + string);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /*
   * 注册
   * */
    private void register() {
        String url = "https://reg.cntv.cn/regist/mobileRegist.do";
        RequestBody requestBody = null;
        try {
            requestBody = new FormBody.Builder()
                    .add("method", "saveMobileRegisterM")
                    .add("mobile", forgetNumber.getText().toString().trim())
                    .add("verfiCode",forgetReveive.getText().toString().toString())
                    .add("passWd", URLEncoder.encode(forgetSetpass.getText().toString().trim(), "UTF-8"))
                    .add("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        OkHttpClient okHttpClient1 = new OkHttpClient();
        Request request2 = null;
        try {
            request2 = new Request.Builder()
                    .url(url)
                    .addHeader("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .post(requestBody)
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        okHttpClient1.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("lianxi", "result:" + e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("Main", "请求结果" + response.body().string());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WJMMActivity.this, "新密码设置成功", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}
