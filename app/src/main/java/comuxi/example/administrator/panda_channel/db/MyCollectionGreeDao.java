package comuxi.example.administrator.panda_channel.db;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2017/7/24.
 */

public class MyCollectionGreeDao {
    public static void main(String [] arg){

        Schema schema = new Schema(1,"comuxi.example.administrator.panda_channel.MyCollectGreeDao");
        Entity entity= schema.addEntity("CollectionGreeDao");
        entity.addIdProperty();

        entity.addStringProperty("imagpath");
        entity.addStringProperty("name");
        entity.addStringProperty("data");
        entity.addStringProperty("moviepath");

        try {
            new DaoGenerator().generateAll(schema,"E:\\Android13\\Panda_Channel\\app\\src\\main\\java");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
