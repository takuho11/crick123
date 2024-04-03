package cn.topcheer.halberd.app.api.framework.vo;


public interface DbTableOrView {

    /**
     * @see DataStoreType.Type#name
     */
    String getDbType();

    String getSchema();

    String getName();

    String getFullName();

    String getComment();

    boolean isView();
}
