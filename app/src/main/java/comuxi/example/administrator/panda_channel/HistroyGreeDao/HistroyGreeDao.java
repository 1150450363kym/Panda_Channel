package comuxi.example.administrator.panda_channel.HistroyGreeDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "HISTROY_GREE_DAO".
 */
public class HistroyGreeDao {

    private Long id;
    private String imagpath;
    private String name;
    private String data;
    private String moviepath;

    private boolean flg_bulen;
    private boolean flg;

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

    public HistroyGreeDao() {
    }

    public HistroyGreeDao(Long id) {
        this.id = id;
    }

    public HistroyGreeDao(Long id, String imagpath, String name, String data, String moviepath) {
        this.id = id;
        this.imagpath = imagpath;
        this.name = name;
        this.data = data;
        this.moviepath = moviepath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagpath() {
        return imagpath;
    }

    public void setImagpath(String imagpath) {
        this.imagpath = imagpath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMoviepath() {
        return moviepath;
    }

    public void setMoviepath(String moviepath) {
        this.moviepath = moviepath;
    }

}