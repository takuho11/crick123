package cn.topcheer.halberd.app.api.framework.vo.mysql;

import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;
import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.db.DbObject;
import cn.topcheer.halberd.app.api.framework.db.DbObjectKey;
import cn.topcheer.halberd.app.api.framework.db.DbProp;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@DbObject(type = "table")
public class MySqlTable implements DbTableOrView {

    @JsonAlias("TABLE_SCHEMA")
    @DbProp(label = "库名", seq = 1, showInGrid = false)
    private String database;

    @DbObjectKey
    @JsonAlias("TABLE_NAME")
    @DbProp(label = "表名", seq = 2)
    private String name;

    @JsonAlias("ENGINE")
    @DbProp(label = "引擎", seq = 5)
    private String engine;

    @JsonAlias("ROW_FORMAT")
    private String rowFormat;

    @JsonAlias("AUTO_INCREMENT")
    @DbProp(label = "自动增加", seq = 7)
    private Long autoIncrement;

    @JsonAlias("CREATE_TIME")
    @DbProp(label = "创建时间", seq = 8)
    private String createTime;

    @DbProp(label = "默认字符集", seq = 9)
    public String getCharset(){
        if(StringUtils.isBlank(this.collation)){
            return "";
        }
        return this.collation.split("_")[0];
    }

    @JsonAlias("TABLE_COLLATION")
    @DbProp(label = "默认排序规则", seq = 10)
    private String collation;

    @JsonAlias("TABLE_COMMENT")
    @DbProp(label = "注释", seq = 11)
    private String comment;


    @Override
    public String getDbType() {
        return DataStoreType.Type.MYSQL.getName();
    }

    @Override
    public String getSchema() {
        return this.database;
    }

    @Override
    public String getFullName() {
        return this.database + "." + this.name;
    }

    @Override
    public boolean isView() {
        return false;
    }
}
