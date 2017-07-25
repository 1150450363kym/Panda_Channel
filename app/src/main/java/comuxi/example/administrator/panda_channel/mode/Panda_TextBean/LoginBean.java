package comuxi.example.administrator.panda_channel.mode.Panda_TextBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2017/7/20.
 */

public class LoginBean implements Serializable{


    /**
     * timestamp : 2017-07-20 17:44:23
     * ticket : b3f15b78-a84f-4a16-9e3e-ca0c4251d923
     * errType : 0
     * errMsg : 成功
     * sso : []
     * user_seq_id : 54047809
     * usrid : 540478091500291419990
     */

    private String timestamp;
    private String ticket;
    private String errType;
    private String errMsg;
    private String user_seq_id;
    private String usrid;
    private List<?> sso;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getErrType() {
        return errType;
    }

    public void setErrType(String errType) {
        this.errType = errType;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getUser_seq_id() {
        return user_seq_id;
    }

    public void setUser_seq_id(String user_seq_id) {
        this.user_seq_id = user_seq_id;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public List<?> getSso() {
        return sso;
    }

    public void setSso(List<?> sso) {
        this.sso = sso;
    }
}
