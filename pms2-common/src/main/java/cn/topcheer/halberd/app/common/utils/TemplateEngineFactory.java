package cn.topcheer.halberd.app.common.utils;

import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.extra.template.engine.beetl.BeetlEngine;
import cn.hutool.extra.template.engine.enjoy.EnjoyEngine;
import cn.hutool.extra.template.engine.freemarker.FreemarkerEngine;
import cn.hutool.extra.template.engine.rythm.RythmEngine;
import cn.hutool.extra.template.engine.thymeleaf.ThymeleafEngine;
import cn.hutool.extra.template.engine.velocity.VelocityEngine;
import cn.hutool.extra.template.engine.wit.WitEngine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TemplateEngineFactory {

    private    static  Map<String, TemplateEngine> templateEngines = new ConcurrentHashMap<>();
    private final static Object lock = new Object();

    public static TemplateEngine getTemplateEngine(String name) {

        TemplateEngine engine = templateEngines.get(name);
        if (engine == null) {
            synchronized (lock) {
                engine = templateEngines.get(name);
                if (engine == null) {
                    TemplateConfig config = new TemplateConfig();

                    if (name.equals("beetl")) {

                        config.setCustomEngine(BeetlEngine.class);
                    } else if (name.equals("freemarker")) {
                        config.setCustomEngine(FreemarkerEngine.class);
                    } else if (name.equals("velocity")) {
                        config.setCustomEngine(VelocityEngine.class);
                    } else if (name.equals("thymeleaf")) {
                        config.setCustomEngine(ThymeleafEngine.class);
                    } else if (name.equals("enjoy")) {
                        config.setCustomEngine(EnjoyEngine.class);
                    } else if (name.equals("rythm")) {
                        config.setCustomEngine(RythmEngine.class);
                    } else if (name.equals("wit")) {
                        config.setCustomEngine(WitEngine.class);
                    }
                    engine = TemplateUtil.createEngine(config);
                    templateEngines.put(name, engine);
                }
            }
        }
        return engine;
    }

}
