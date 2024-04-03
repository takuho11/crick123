package cn.topcheer.halberd.app.api.minidev.entity;

import lombok.Data;

@Data
public class TableInfo {
    String dbType;
    String schema;
    String name;
    String fullName;
    String comment;
    boolean isView;
    String entityName;
}
