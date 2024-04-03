package cn.topcheer.halberd.app.biz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("halberd.encryptor")
public class EncryptorProperties {

    private String password = "B&6t^Bv(pqkq[k9";

    private String algorithm = "PBEWithMD5AndTripleDES";//"PBEWITHHMACSHA512ANDAES_256"

}
