package com.brady.order.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author brady.si
 */
@JobHandler(value = "testJobHandler")
@Component
@Slf4j
public class TestHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        log.info("hhhhhhhhhhhhhhhhhh--------------------------------" + s);
        return SUCCESS;
    }
}
