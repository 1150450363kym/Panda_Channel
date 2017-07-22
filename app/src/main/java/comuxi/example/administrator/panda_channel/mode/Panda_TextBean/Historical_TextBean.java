package comuxi.example.administrator.panda_channel.mode.Panda_TextBean;

/**
 * Created by Administrator on 2017/7/21.
 */

public class Historical_TextBean  {

    String imag_url;
    String title;
    String data;
    boolean flg;
    boolean flg_bulen;



    public Historical_TextBean() {
    }

    public Historical_TextBean(String imag_url, String title, String data, boolean flg, boolean flg_bulen) {
        this.imag_url = imag_url;
        this.title = title;
        this.data = data;
        this.flg = flg;
        this.flg_bulen = flg_bulen;
    }

    public String getImag_url() {
        return imag_url;
    }

    public void setImag_url(String imag_url) {
        this.imag_url = imag_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isFlg() {
        return flg;
    }

    public void setFlg(boolean flg) {
        this.flg = flg;
    }

    public boolean isFlg_bulen() {
        return flg_bulen;
    }

    public void setFlg_bulen(boolean flg_bulen) {
        this.flg_bulen = flg_bulen;
    }

    @Override
    public String toString() {
        return "Historical_TextBean{" +
                "imag_url='" + imag_url + '\'' +
                ", title='" + title + '\'' +
                ", data='" + data + '\'' +
                ", flg=" + flg +
                ", flg_bulen=" + flg_bulen +
                '}';
    }
}
