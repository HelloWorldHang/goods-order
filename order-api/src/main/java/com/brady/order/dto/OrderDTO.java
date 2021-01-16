package com.brady.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author brady.si
 */
public class OrderDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SubmitOrderDTO{

        @NotNull
        private Integer gid;

        @NotNull
        private String goodsName;

        @NotNull
        private Double price;

        private String comment;

        @NotNull
        private String username;

        @NotNull
        private Integer uid;

        @NotNull
        private String tel;

        @NotNull
        private String addr;
    }

}
