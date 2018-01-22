package com.national.security.community.data.model;

import java.util.List;

public class HomeBean {

    /**
     * arrCarousel : [{"tittle":"12","linkAddress":"","path":"http://47.93.29.96/carousel/image/68d7c12db71d4097a0566e7532c2f2af.jpg"},{"tittle":"13","linkAddress":"","path":"http://47.93.29.96/carousel/image/de65409e5b224a7891a88600fd74e5d5.jpg"}]
     * arrNotice : [{"id":"2f3b8ae9e1ad40fbad70249f2299992b","titlle":"东鼎小贷震撼上线","content":"http:www.baidu.com"}]
     * arrInformation : [{"id":"95f1532211454d4f92ceb712655f0b08","columnType":"公司动态","tittle":"公司简介","thumbnails":"http://47.93.29.96/zxinformation/image/c039e4b71bf7424da5d92fd837e5d29b.png","content":"http://www.baidu.com"}]
     * arrProduct : [{"id":"f736bf814bf344d7b12ff1ad1a33ff01","productName":"安意购","productType":"1","interestMode":0,"loanAmountRange":"20,000","yearInterestRate":9.6},{"id":"68b8bfcfef0f4ee48540bb5272eadc6e","productName":"员工贷","productType":"1","interestMode":0,"loanAmountRange":"300,000","yearInterestRate":10.8}]
     * flag : 0
     */

    private int flag;
    private List<ArrCarouselBean> arrCarousel;
    private List<ArrNoticeBean> arrNotice;
    private List<ArrInformationBean> arrInformation;
    private List<ArrProductBean> arrProduct;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<ArrCarouselBean> getArrCarousel() {
        return arrCarousel;
    }

    public void setArrCarousel(List<ArrCarouselBean> arrCarousel) {
        this.arrCarousel = arrCarousel;
    }

    public List<ArrNoticeBean> getArrNotice() {
        return arrNotice;
    }

    public void setArrNotice(List<ArrNoticeBean> arrNotice) {
        this.arrNotice = arrNotice;
    }

    public List<ArrInformationBean> getArrInformation() {
        return arrInformation;
    }

    public void setArrInformation(List<ArrInformationBean> arrInformation) {
        this.arrInformation = arrInformation;
    }

    public List<ArrProductBean> getArrProduct() {
        return arrProduct;
    }

    public void setArrProduct(List<ArrProductBean> arrProduct) {
        this.arrProduct = arrProduct;
    }

    public static class ArrCarouselBean {
        /**
         * tittle : 12
         * linkAddress :
         * path : http://47.93.29.96/carousel/image/68d7c12db71d4097a0566e7532c2f2af.jpg
         */
        private String tittle;
        private String linkAddress;
        private String path;

        public String getTittle() {
            return tittle;
        }

        public void setTittle(String tittle) {
            this.tittle = tittle;
        }

        public String getLinkAddress() {
            return linkAddress;
        }

        public void setLinkAddress(String linkAddress) {
            this.linkAddress = linkAddress;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    public static class ArrNoticeBean {
        /**
         * id : 2f3b8ae9e1ad40fbad70249f2299992b
         * titlle : 东鼎小贷震撼上线
         * content : http:www.baidu.com
         */

        private String id;
        private String titlle;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitlle() {
            return titlle;
        }

        public void setTitlle(String titlle) {
            this.titlle = titlle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class ArrInformationBean {
        /**
         * id : 95f1532211454d4f92ceb712655f0b08
         * columnType : 公司动态
         * tittle : 公司简介
         * thumbnails : http://47.93.29.96/zxinformation/image/c039e4b71bf7424da5d92fd837e5d29b.png
         * content : http://www.baidu.com
         */

        private String id;
        private String columnType;
        private String tittle;
        private String thumbnails;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getColumnType() {
            return columnType;
        }

        public void setColumnType(String columnType) {
            this.columnType = columnType;
        }

        public String getTittle() {
            return tittle;
        }

        public void setTittle(String tittle) {
            this.tittle = tittle;
        }

        public String getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(String thumbnails) {
            this.thumbnails = thumbnails;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class ArrProductBean {
        /**
         * id : f736bf814bf344d7b12ff1ad1a33ff01
         * productName : 安意购
         * productType : 1
         * interestMode : 0
         * loanAmountRange : 20,000
         * yearInterestRate : 9.6
         */

        private String id;
        private String productName;
        private String productType;
        private int interestMode;
        private String loanAmountRange;
        private double yearInterestRate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public int getInterestMode() {
            return interestMode;
        }

        public void setInterestMode(int interestMode) {
            this.interestMode = interestMode;
        }

        public String getLoanAmountRange() {
            return loanAmountRange;
        }

        public void setLoanAmountRange(String loanAmountRange) {
            this.loanAmountRange = loanAmountRange;
        }

        public double getYearInterestRate() {
            return yearInterestRate;
        }

        public void setYearInterestRate(double yearInterestRate) {
            this.yearInterestRate = yearInterestRate;
        }
    }
}



