package cn.topcheer.halberd.app.api.minidev.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConnectorDTO  implements Serializable {
    private String id;
    private String code;
    private String name;
    private String type="2";  //2 request  3  dict
    private String dictCode;   // type=3 有效
    private String method= "";
    private Boolean addToRuntime=false;
    private Boolean isForm=false;
    private String dataScript="";
    private String fixScript="";


}
