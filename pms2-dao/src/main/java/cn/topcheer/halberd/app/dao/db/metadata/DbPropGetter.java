package cn.topcheer.halberd.app.dao.db.metadata;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

@Getter
@Setter
@Builder
public class DbPropGetter {

    private String label;

    private Method getter;

    private boolean original;
}
