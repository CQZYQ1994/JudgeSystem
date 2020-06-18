package com.judgesystem.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected long total;

    protected List<T> list;

}

