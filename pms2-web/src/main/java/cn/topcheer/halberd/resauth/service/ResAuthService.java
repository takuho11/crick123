package cn.topcheer.halberd.resauth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class ResAuthService {
    
    final List<ResAuthResolver> initResolvers;
    Map<String,ResAuthResolver> registResolvers = new HashMap<String,ResAuthResolver>();

public   ResAuthService(List<ResAuthResolver> resolvers) {
    this.initResolvers=resolvers;
}

   @PostConstruct
    public void init() {
           if(initResolvers!=null) {
              for(ResAuthResolver resolver : initResolvers){
                resolver.register(this);
              }
           }
    }

    public void addResolver(ResAuthResolver resolver,String resourceType){
        registResolvers.put(resourceType, resolver);
    }

}
