package comuxi.example.administrator.panda_channel;

import android.view.View;
import android.widget.ImageView;

import comuxi.example.administrator.panda_channel.Base.BaseActivity;

public class PersonAboutActivity extends BaseActivity implements View.OnClickListener {

    private ImageView personabout_image;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_about;
    }

    @Override
    protected void initView() {

        personabout_image = (ImageView) findViewById(R.id.personabout_image);
        personabout_image.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        this.finish();
    }
}
