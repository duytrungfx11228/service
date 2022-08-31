package com.globits.da.dto.search;

public class CertificateSearchDto {
    private Integer pageIndex;
    private Integer pageSize;
    private String keyWrod;

    //region getter-setter
    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyWrod() {
        return keyWrod;
    }

    public void setKeyWrod(String keyWrod) {
        this.keyWrod = keyWrod;
    }
    //endregion
}
