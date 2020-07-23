package com.dupake.common.pojo.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BasePageRequest implements Serializable {

    private static final long serialVersionUID = -7263369729729706669L;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;

    private Integer pageNumber;

    public Integer getPageNumber() {
        return size * (page - 1);
    }
}
