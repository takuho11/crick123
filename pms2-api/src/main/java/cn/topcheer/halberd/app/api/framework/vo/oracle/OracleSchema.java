package cn.topcheer.halberd.app.api.framework.vo.oracle;


import cn.topcheer.halberd.app.api.framework.db.DbObject;
import cn.topcheer.halberd.app.api.framework.db.DbObjectKey;
import cn.topcheer.halberd.app.api.framework.db.DbProp;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DbObject(type = "database")
public class OracleSchema {

    @DbObjectKey
    @JsonAlias("USERNAME")
    @DbProp(label = "名称", seq = 1)
    private String name;

    @JsonAlias("CREATED")
    @DbProp(label = "创建时间", seq = 2)
    private String createTime;
}
