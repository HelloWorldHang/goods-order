package com.brady.order.export;

import com.brady.order.ServiceNameConst;
import com.brady.order.dto.OrderDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author brady.si
 */
@FeignClient(ServiceNameConst.SERVICE_NAME)
@Api("订单api")
public interface OrderApi {
    /**
     * 提交订单
     *
     * @param dto 接口入参
     * @return
     */
    @ApiOperation(value = "提交订单", response = Boolean.class)
    @PostMapping(value = "/order/submitOrder", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    boolean submitOrder(@Validated @RequestBody OrderDTO.SubmitOrderDTO dto);
}
