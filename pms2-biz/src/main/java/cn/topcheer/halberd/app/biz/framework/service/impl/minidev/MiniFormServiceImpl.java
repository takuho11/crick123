package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;


import cn.topcheer.halberd.app.api.minidev.entity.FormHistory;
import cn.topcheer.halberd.app.api.minidev.entity.MiniForm;
import cn.topcheer.halberd.app.api.minidev.service.FormHistoryService;
import cn.topcheer.halberd.app.api.minidev.service.MiniFormService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniFormMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 *  服务实现类
 *
 * @author Chill
 */
@Service
public class MiniFormServiceImpl extends ServiceImpl<MiniFormMapper, MiniForm> implements MiniFormService {

    @Resource
    private FormHistoryService formHistoryService;

    @Override
    public boolean saveForm(MiniForm miniForm) {
        this.saveBefore(miniForm);
        this.save(miniForm);
        return true;
    }

    @Override
    public MiniForm saveBefore(MiniForm miniForm) {
        miniForm.setCreateTime(LocalDateTime.now());
        miniForm.setUpdateTime(LocalDateTime.now());
        BladeUser user = AuthUtil.getUser();
        if(user != null){
            miniForm.setCreateUser(user.getUserId());
            miniForm.setUpdateUser(user.getUserId());
        }
        miniForm.setIsDeleted(0);
        miniForm.setVersion(1);
        return miniForm;
    }

    @Override
    public boolean submit(MiniForm miniForm) {
        if(miniForm.getId() == null){
            miniForm = this.saveBefore(miniForm);
        }
        MiniForm old = this.getById(miniForm.getId());
        if (old != null) {
            miniForm.setVersion(old.getVersion() + 1);
        }
        FormHistory formHistory = new FormHistory();
        BeanUtil.copyProperties(miniForm, formHistory);
        this.saveOrUpdate(miniForm);
        formHistory.setFormId(miniForm.getId());
        formHistory.setId(null);
        formHistoryService.saveBefore(formHistory);
        formHistoryService.save(formHistory);
        return true;
    }

    @Override
    public boolean removeById(String id) {
        MiniForm miniForm = this.getById(id);
        miniForm.setIsDeleted(1);
        return this.updateById(miniForm);
    }

    @Override
    public IPage<MiniForm> pageList(MiniForm miniForm, Query query) {
        String name = miniForm.getName();
        miniForm.setName(null);
        QueryWrapper<MiniForm> queryWrapper = Condition.getQueryWrapper(miniForm);
        queryWrapper.orderByDesc("create_time");
        if(name != null){
            queryWrapper.lambda().like(MiniForm::getName, name);
        }
        return this.page(Condition.getPage(query), queryWrapper);
    }
}
