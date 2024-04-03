package cn.topcheer.halberd.app.biz.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Provider;
import java.security.Security;

@Configuration
public class EncryptorConfig {

    @Bean
    public StringEncryptor dataCenterEncryptor(EncryptorProperties encryptorProperties){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(encryptorProperties.getPassword());
        encryptor.setAlgorithm(encryptorProperties.getAlgorithm());
        return encryptor;
    }


    public static void main(String[] args) {
//        for (Provider provider : Security.getProviders()) {
//            System.out.println("Provider: " + provider.getName());
//            for (Provider.Service service : provider.getServices()) {
//                System.out.println("  Algorithm: " + service.getAlgorithm());
//            }
//        }
        EncryptorProperties encryptorProperties=new EncryptorProperties();
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(encryptorProperties.getPassword());
        encryptor.setAlgorithm(encryptorProperties.getAlgorithm());
        System.out.println(encryptor.encrypt("Topcheer2023"));

    }
}
