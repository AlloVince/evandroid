package com.example;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by allovince on 15/8/17.
 */
public class Policy {

    /**
     * tags : []
     * count : 3
     * range : 1
     * status : 1
     * return : {"farReturn":0,"weeklyReturn":0,"dailyReturn":0,"monthlyReturn":0}
     * type : 0
     * stocks : [{"position":"0","sort":"1","minHoldTime":null,"symbol":"SH600004","stockName":"白云机场","restrationRatio":null,"maxHoldTime":null,"startPrice":"15.37","restrationAt":"0","targetPrice":null,"updatedAt":"1439626624","id":"1","isDelete":"0","market":"SH","suggestPrice":null,"policyId":"1","stopLossPrice":null,"stockId":"600004","returns":null},{"position":"25","sort":"2","minHoldTime":null,"symbol":"SH600000","stockName":"浦发银行","restrationRatio":null,"maxHoldTime":null,"startPrice":"15.49","restrationAt":"0","targetPrice":null,"updatedAt":"1439626624","id":"2","isDelete":"0","market":"SH","suggestPrice":null,"policyId":"1","stopLossPrice":null,"stockId":"600000","returns":null},{"position":"0","sort":"3","minHoldTime":null,"symbol":"SH600006","stockName":"东风汽车","restrationRatio":null,"maxHoldTime":null,"startPrice":"13.81","restrationAt":"0","targetPrice":null,"updatedAt":"1439626624","id":"5","isDelete":"0","market":"SH","suggestPrice":null,"policyId":"1","stopLossPrice":null,"stockId":"600006","returns":null},{"position":"25","sort":"4","minHoldTime":null,"symbol":"SH600008","stockName":"首创股份","restrationRatio":null,"maxHoldTime":null,"startPrice":"12.54","restrationAt":"0","targetPrice":null,"updatedAt":"1439626624","id":"6","isDelete":"0","market":"SH","suggestPrice":null,"policyId":"1","stopLossPrice":null,"stockId":"600008","returns":null}]
     * id : 1
     * content : <p>dqadwad</p>
     * updatedAt : 1439626624
     * additions : {"total":5,"result":[{"content":"<p>dqadwad<\/p>","id":"5","title":"测试1","stockChange":{"SH600006":{"symbol":"SH600006","stockName":"东风汽车","type":"suspend"}},"policyId":"1","createdAt":"1439626624"},{"content":"<p>dwad<\/p>","id":"4","title":"测试1","stockChange":{"SH600004":{"symbol":"SH600004","stockName":"白云机场","type":"suspend"}},"policyId":"1","createdAt":"1439626624"},{"content":"<p>12313<\/p>","id":"3","title":"测试1","stockChange":{"SH600010":{"symbol":"SH600010","stockName":"包钢股份","type":"delete"},"SH600006":{"symbol":"SH600006","stockName":"东风汽车","type":"create","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]},"SH600008":{"symbol":"SH600008","stockName":"首创股份","type":"delete","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]}},"policyId":"1","createdAt":"1439626624"},{"content":"<p>2313<\/p>","id":"2","title":"测试1","stockChange":{"SH600010":{"symbol":"SH600010","stockName":"包钢股份","type":"create","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]},"SH600008":{"symbol":"SH600008","stockName":"首创股份","type":"create","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]}},"policyId":"1","createdAt":"1439626624"},{"content":"<p>123<\/p>","id":"1","title":"测试1","stockChange":{"SH600000":{"symbol":"SH600000","stockName":"浦发银行","type":"create","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]},"SH600004":{"symbol":"SH600004","stockName":"白云机场","type":"create","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]}},"policyId":"1","createdAt":"1439626624"}]}
     * title : 测试1
     * duration : longTerm
     * level : 0
     * importance : 1
     * share_url : http://stock.wallstcn.com/strategy/1
     * createdAt : 1439626624
     * userId : 152147
     * user : {"id":"152147","lastName":"管","returns":null,"avatar":"http://avatar.cdn.wallstcn.com//2d/10/8e/-2014-12-09-10-26-05.png","firstName":"清友","profile":{"bio":"民生证券研究院执行院长","company":"民生","jobTitle":"民生证券研究院执行院长、首席宏观分析师"}}
     * thumb : null
     */
    private List<?> tags;
    private int count;
    private String range;
    private String status;
    @SerializedName("return")
    private ReturnXEntity returnX;
    private String type;
    private List<StocksEntity> stocks;
    private String id;
    private String content;
    private String updatedAt;
    private AdditionsEntity additions;
    private String title;
    private String duration;
    private String level;
    private String importance;
    private String share_url;
    private String createdAt;
    private String userId;
    private UserEntity user;
    private String thumb;

    public void setTags(List<?> tags) {
        this.tags = tags;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReturnX(ReturnXEntity returnX) {
        this.returnX = returnX;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStocks(List<StocksEntity> stocks) {
        this.stocks = stocks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAdditions(AdditionsEntity additions) {
        this.additions = additions;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public List<?> getTags() {
        return tags;
    }

    public int getCount() {
        return count;
    }

    public String getRange() {
        return range;
    }

    public String getStatus() {
        return status;
    }

    public ReturnXEntity getReturnX() {
        return returnX;
    }

    public String getType() {
        return type;
    }

    public List<StocksEntity> getStocks() {
        return stocks;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public AdditionsEntity getAdditions() {
        return additions;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public String getLevel() {
        return level;
    }

    public String getImportance() {
        return importance;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getThumb() {
        return thumb;
    }

    public static class ReturnXEntity {
        /**
         * farReturn : 0
         * weeklyReturn : 0
         * dailyReturn : 0
         * monthlyReturn : 0
         */
        private int farReturn;
        private int weeklyReturn;
        private int dailyReturn;
        private int monthlyReturn;

        public void setFarReturn(int farReturn) {
            this.farReturn = farReturn;
        }

        public void setWeeklyReturn(int weeklyReturn) {
            this.weeklyReturn = weeklyReturn;
        }

        public void setDailyReturn(int dailyReturn) {
            this.dailyReturn = dailyReturn;
        }

        public void setMonthlyReturn(int monthlyReturn) {
            this.monthlyReturn = monthlyReturn;
        }

        public int getFarReturn() {
            return farReturn;
        }

        public int getWeeklyReturn() {
            return weeklyReturn;
        }

        public int getDailyReturn() {
            return dailyReturn;
        }

        public int getMonthlyReturn() {
            return monthlyReturn;
        }
    }

    public static class StocksEntity {
        /**
         * position : 0
         * sort : 1
         * minHoldTime : null
         * symbol : SH600004
         * stockName : 白云机场
         * restrationRatio : null
         * maxHoldTime : null
         * startPrice : 15.37
         * restrationAt : 0
         * targetPrice : null
         * updatedAt : 1439626624
         * id : 1
         * isDelete : 0
         * market : SH
         * suggestPrice : null
         * policyId : 1
         * stopLossPrice : null
         * stockId : 600004
         * returns : null
         */
        private String position;
        private String sort;
        private String minHoldTime;
        private String symbol;
        private String stockName;
        private String restrationRatio;
        private String maxHoldTime;
        private String startPrice;
        private String restrationAt;
        private String targetPrice;
        private String updatedAt;
        private String id;
        private String isDelete;
        private String market;
        private String suggestPrice;
        private String policyId;
        private String stopLossPrice;
        private String stockId;
        private String returns;

        public void setPosition(String position) {
            this.position = position;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setMinHoldTime(String minHoldTime) {
            this.minHoldTime = minHoldTime;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public void setRestrationRatio(String restrationRatio) {
            this.restrationRatio = restrationRatio;
        }

        public void setMaxHoldTime(String maxHoldTime) {
            this.maxHoldTime = maxHoldTime;
        }

        public void setStartPrice(String startPrice) {
            this.startPrice = startPrice;
        }

        public void setRestrationAt(String restrationAt) {
            this.restrationAt = restrationAt;
        }

        public void setTargetPrice(String targetPrice) {
            this.targetPrice = targetPrice;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public void setSuggestPrice(String suggestPrice) {
            this.suggestPrice = suggestPrice;
        }

        public void setPolicyId(String policyId) {
            this.policyId = policyId;
        }

        public void setStopLossPrice(String stopLossPrice) {
            this.stopLossPrice = stopLossPrice;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        public void setReturns(String returns) {
            this.returns = returns;
        }

        public String getPosition() {
            return position;
        }

        public String getSort() {
            return sort;
        }

        public String getMinHoldTime() {
            return minHoldTime;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getStockName() {
            return stockName;
        }

        public String getRestrationRatio() {
            return restrationRatio;
        }

        public String getMaxHoldTime() {
            return maxHoldTime;
        }

        public String getStartPrice() {
            return startPrice;
        }

        public String getRestrationAt() {
            return restrationAt;
        }

        public String getTargetPrice() {
            return targetPrice;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public String getId() {
            return id;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public String getMarket() {
            return market;
        }

        public String getSuggestPrice() {
            return suggestPrice;
        }

        public String getPolicyId() {
            return policyId;
        }

        public String getStopLossPrice() {
            return stopLossPrice;
        }

        public String getStockId() {
            return stockId;
        }

        public String getReturns() {
            return returns;
        }
    }

    public static class AdditionsEntity {
        /**
         * total : 5
         * result : [{"content":"<p>dqadwad<\/p>","id":"5","title":"测试1","stockChange":{"SH600006":{"symbol":"SH600006","stockName":"东风汽车","type":"suspend"}},"policyId":"1","createdAt":"1439626624"},{"content":"<p>dwad<\/p>","id":"4","title":"测试1","stockChange":{"SH600004":{"symbol":"SH600004","stockName":"白云机场","type":"suspend"}},"policyId":"1","createdAt":"1439626624"},{"content":"<p>12313<\/p>","id":"3","title":"测试1","stockChange":{"SH600010":{"symbol":"SH600010","stockName":"包钢股份","type":"delete"},"SH600006":{"symbol":"SH600006","stockName":"东风汽车","type":"create","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]},"SH600008":{"symbol":"SH600008","stockName":"首创股份","type":"delete","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]}},"policyId":"1","createdAt":"1439626624"},{"content":"<p>2313<\/p>","id":"2","title":"测试1","stockChange":{"SH600010":{"symbol":"SH600010","stockName":"包钢股份","type":"create","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]},"SH600008":{"symbol":"SH600008","stockName":"首创股份","type":"create","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]}},"policyId":"1","createdAt":"1439626624"},{"content":"<p>123<\/p>","id":"1","title":"测试1","stockChange":{"SH600000":{"symbol":"SH600000","stockName":"浦发银行","type":"create","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]},"SH600004":{"symbol":"SH600004","stockName":"白云机场","type":"create","log":[{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"targetPrice"},{"newNum":"0","oldNum":null,"operationType":"create","fieldName":"stopLossPrice"}]}},"policyId":"1","createdAt":"1439626624"}]
         */
        private int total;
        private List<ResultEntity> result;

        public void setTotal(int total) {
            this.total = total;
        }

        public void setResult(List<ResultEntity> result) {
            this.result = result;
        }

        public int getTotal() {
            return total;
        }

        public List<ResultEntity> getResult() {
            return result;
        }

        public static class ResultEntity {
            /**
             * content : <p>dqadwad</p>
             * id : 5
             * title : 测试1
             * stockChange : {"SH600006":{"symbol":"SH600006","stockName":"东风汽车","type":"suspend"}}
             * policyId : 1
             * createdAt : 1439626624
             */
            private String content;
            private String id;
            private String title;
            private StockChangeEntity stockChange;
            private String policyId;
            private String createdAt;

            public void setContent(String content) {
                this.content = content;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setStockChange(StockChangeEntity stockChange) {
                this.stockChange = stockChange;
            }

            public void setPolicyId(String policyId) {
                this.policyId = policyId;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getContent() {
                return content;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public StockChangeEntity getStockChange() {
                return stockChange;
            }

            public String getPolicyId() {
                return policyId;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public static class StockChangeEntity {
                /**
                 * SH600006 : {"symbol":"SH600006","stockName":"东风汽车","type":"suspend"}
                 */
                private SH600006Entity SH600006;

                public void setSH600006(SH600006Entity SH600006) {
                    this.SH600006 = SH600006;
                }

                public SH600006Entity getSH600006() {
                    return SH600006;
                }

                public static class SH600006Entity {
                    /**
                     * symbol : SH600006
                     * stockName : 东风汽车
                     * type : suspend
                     */
                    private String symbol;
                    private String stockName;
                    private String type;

                    public void setSymbol(String symbol) {
                        this.symbol = symbol;
                    }

                    public void setStockName(String stockName) {
                        this.stockName = stockName;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getSymbol() {
                        return symbol;
                    }

                    public String getStockName() {
                        return stockName;
                    }

                    public String getType() {
                        return type;
                    }
                }
            }
        }
    }

    public static class UserEntity {
        /**
         * id : 152147
         * lastName : 管
         * returns : null
         * avatar : http://avatar.cdn.wallstcn.com//2d/10/8e/-2014-12-09-10-26-05.png
         * firstName : 清友
         * profile : {"bio":"民生证券研究院执行院长","company":"民生","jobTitle":"民生证券研究院执行院长、首席宏观分析师"}
         */
        private String id;
        private String lastName;
        private String returns;
        private String avatar;
        private String firstName;
        private ProfileEntity profile;

        public void setId(String id) {
            this.id = id;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setReturns(String returns) {
            this.returns = returns;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setProfile(ProfileEntity profile) {
            this.profile = profile;
        }

        public String getId() {
            return id;
        }

        public String getLastName() {
            return lastName;
        }

        public String getReturns() {
            return returns;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getFirstName() {
            return firstName;
        }

        public ProfileEntity getProfile() {
            return profile;
        }

        public static class ProfileEntity {
            /**
             * bio : 民生证券研究院执行院长
             * company : 民生
             * jobTitle : 民生证券研究院执行院长、首席宏观分析师
             */
            private String bio;
            private String company;
            private String jobTitle;

            public void setBio(String bio) {
                this.bio = bio;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public void setJobTitle(String jobTitle) {
                this.jobTitle = jobTitle;
            }

            public String getBio() {
                return bio;
            }

            public String getCompany() {
                return company;
            }

            public String getJobTitle() {
                return jobTitle;
            }
        }
    }
}
