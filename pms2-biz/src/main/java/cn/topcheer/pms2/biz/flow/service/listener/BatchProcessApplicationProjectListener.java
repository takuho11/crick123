package cn.topcheer.pms2.biz.flow.service.listener;


import cn.topcheer.halberd.flow.entity.BatchTaskDetail;
import cn.topcheer.halberd.flow.entity.BatchTaskJob;
import cn.topcheer.halberd.flow.model.OneTaskParameter;
import cn.topcheer.halberd.flow.service.BatchFlowUserTaskListener;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;


/**
 * BatchProcessApplicationProjectListener
 *
 * @author szs
 * @date 2024-03-18
 */
@Service
public class BatchProcessApplicationProjectListener implements BatchFlowUserTaskListener {


    @Override
    public void taskStarted(OneTaskParameter parameter, BatchTaskDetail taskdetail) {
        System.err.println(">>>>>>>BatchProcessApplicationProjectListener.taskStarted：" + JSON.toJSONString(taskdetail));

    }

    @Override
    public void taskSuccess(OneTaskParameter parameter, BatchTaskDetail taskdetail) {
        System.err.println(">>>>>>>BatchProcessApplicationProjectListener.taskSuccess：" + JSON.toJSONString(taskdetail));
    }

    @Override
    public void taskFailed(OneTaskParameter parameter, BatchTaskDetail taskdetail) {
        System.err.println(">>>>>>>BatchProcessApplicationProjectListener.taskFailed：" + JSON.toJSONString(taskdetail));
    }

    @Override
    public void batchJobStarted(BatchTaskJob job) {
        System.err.println(">>>>>>>BatchProcessApplicationProjectListener.BatchJobStarted：" + JSON.toJSONString(job));
    }


}
