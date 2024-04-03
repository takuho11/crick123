package cn.topcheer.halberd.app.biz.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Config放在api 子模块下是否合适？
 * @author ly
 * @date 2023/3/15 13:46
 */
@Component
@ConfigurationProperties("ssoclient")
@Getter
@Setter
public class SsoAuthConfig {


    String ssoValidateUrl="http://10.10.136.13:8088/ssoserver/caslogin/serviceValidate";
    String ssoLoginPathUrl="http://10.10.48.50:8088/ssoserver/caslogin/agentlogin";


    String companyBasicInfoSuccessRedirectUrl="http://10.10.49.203:18081/#/startPage";
    String companyBasicInfoFailRedirectUrl="http://10.10.49.203:18081/#/login";

    String companyBasicInfoLoginUrl="http://10.10.49.203:18081/api/sso_login/CompanyBasicInfo";


    String bigScreenSuccessRedirectUrl="http://10.10.48.241:8080/screen/#/startPage";
    String bigScreenFailRedirectUrl="http://10.10.48.241:8080/screen/#/login";

    String bigScreenLoginUrl="http://10.10.48.241:8080/tzapi/api/sso_login/BigScreen";



}

