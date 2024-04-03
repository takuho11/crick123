package cn.topcheer.halberd.app.biz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import cn.topcheer.halberd.biz.modules.auth.granter.CaptchaTokenGranter;
import cn.topcheer.halberd.biz.modules.auth.granter.ClientTokenGranter;
import cn.topcheer.halberd.biz.modules.auth.granter.PasswordTokenGranter;
import cn.topcheer.halberd.biz.modules.auth.granter.RefreshTokenGranter;
import cn.topcheer.halberd.biz.modules.auth.granter.SMSCaptchaTokenGranter;
import cn.topcheer.halberd.biz.modules.auth.granter.SocialTokenGranter;
import cn.topcheer.halberd.biz.modules.auth.provider.TokenGranterBuilder;

import javax.annotation.PostConstruct;
 
@Configuration
public class TokenGranterConfig {

@Autowired
private CaptchaTokenGranter captchaTokenGranter;

@Autowired
private SMSCaptchaTokenGranter smsCaptchaTokenGranter;

@Autowired
private PasswordTokenGranter passwordTokenGranter;

@Autowired
private RefreshTokenGranter refreshTokenGranter;


@Autowired
private SocialTokenGranter socialTokenGranter;


@Autowired
private ClientTokenGranter clientTokenGranter;  


@PostConstruct
public void init() {
	TokenGranterBuilder.clear();

    //这里后期根据实际情况增减TokenGranter实现

    TokenGranterBuilder.addGranter(CaptchaTokenGranter.GRANT_TYPE,captchaTokenGranter);
    TokenGranterBuilder.addGranter(PasswordTokenGranter.GRANT_TYPE, passwordTokenGranter);
    TokenGranterBuilder.addGranter(SMSCaptchaTokenGranter.GRANT_TYPE, smsCaptchaTokenGranter);
	TokenGranterBuilder.addGranter(RefreshTokenGranter.GRANT_TYPE, refreshTokenGranter);
	TokenGranterBuilder.addGranter(SocialTokenGranter.GRANT_TYPE, socialTokenGranter);
	TokenGranterBuilder.addGranter(ClientTokenGranter.GRANT_TYPE,clientTokenGranter);


}


}
