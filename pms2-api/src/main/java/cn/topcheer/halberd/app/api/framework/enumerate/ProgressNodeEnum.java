package cn.topcheer.halberd.app.api.framework.enumerate;

/**
 * base64文件类型,前缀
 * @author ouyangjun
 */
public enum ProgressNodeEnum {

    // 文件类型
    PROGRESS_STATUS_NODE1("10", "数据接收"),
    PROGRESS_STATUS_NODE2("20", "参数鉴定"),
    PROGRESS_STATUS_NODE3("30", "转发MQ"),
    PROGRESS_STATUS_NODE4("40", "数据处理"),
    PROGRESS_STATUS_NODE5("50", "数据入仓"),
    PROGRESS_STATUS_NODE6("60", "转发推送"),
    PROGRESS_STATUS_NODE7("70", "接口回调"),
    PROGRESS_STATUS_FINISH("200", "已完成");



    private ProgressNodeEnum(String node, String name) {
        this.node = node;
        this.name = name;
    }

    private String node;
    private String name;

    public String getNode() {return node;}
    public String getName() {return name;}

    public static String name(String node) {
        ProgressNodeEnum[] types = values();
        for (ProgressNodeEnum x : types) {
            if (x.getNode().equals(node)) {
                return x.getName();
            }
        }
        return null;
    }

    public static String node(String name) {
        ProgressNodeEnum[] types = values();
        for (ProgressNodeEnum x : types) {
            if (x.getName().equals(name)) {
                return x.getNode();
            }
        }
        return null;
    }
}