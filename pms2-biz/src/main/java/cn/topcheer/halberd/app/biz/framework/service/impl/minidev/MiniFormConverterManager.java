package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniForm;
import cn.topcheer.halberd.app.api.minidev.service.MiniFormConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MiniFormConverterManager {
    
@Autowired
private List<MiniFormConverter> converters=new ArrayList<>();

public void convert(MiniForm miniForm) throws Exception{
    if(converters.size()==0) throw new Exception("no MiniFormConverter exists");

    for (MiniFormConverter converter : converters) {

        if(converter.canHandle(miniForm)){
            converter.convert(miniForm);
            return ;
        }
        
    }

}

}
