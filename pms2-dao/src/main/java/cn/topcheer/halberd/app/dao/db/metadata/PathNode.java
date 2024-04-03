package cn.topcheer.halberd.app.dao.db.metadata;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.List;

@Getter
@Setter
@Builder
public class PathNode {

    private String path;

    private Method method;

    private int seq;

    private PathNode father;

    private List<PathNode> children;

}
