package com.vusieam.academics.deliciouscatering.domain.models;

import java.util.List;

/**
 *
 * @author vusi
 */
public class GenericResponse<T> {
    private Integer code = 0;
    private Boolean status = false;
    private String message = "";    
    private List<T> dataList;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}
