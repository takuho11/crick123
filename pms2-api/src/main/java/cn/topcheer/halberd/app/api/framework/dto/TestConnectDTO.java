package cn.topcheer.halberd.app.api.framework.dto;

import cn.topcheer.halberd.app.common.json.WhiteSpaceRemovalDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TestConnectDTO {
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @NotBlank(message = "连接url必填！")
    private String url;

    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    private String user;

    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    private String password;
}
