package com.evandroid.rio.model;

import java.util.List;

/**
 * Created by allovince on 15/9/1.
 */
public class Posts {

    /**
     * paginator : {"total":50701,"last":"http://api.wallstreetcn.com/v2/posts?type=news&status=published&order=-created_at&limit=25&page=2029","previous":"http://api.wallstreetcn.com/v2/posts?type=news&status=published&order=-created_at&limit=25&page=1","next":"http://api.wallstreetcn.com/v2/posts?type=news&status=published&order=-created_at&limit=25&page=2"}
     * results : [{"tags":[{"id":"3","tagName":"美联储"},{"id":"386","tagName":"FOMC"}],"summary":"","count":"2","imageUrl":"http://posts.cdn.wallstcn.com/e9/92/16/150126095901-stocks-to-buy-780x439.jpg","commentStatus":"open","codeType":"html","type":"news","sourceUrl":"","commentCount":"0","url":"http://wallstreetcn.com/node/223064","id":"223064","title":"摩根士丹利：09年来全球股市首现全线买入信号","createdAt":"1441168978","sourceName":"","slug":"KVA7fhy4","user":{"id":"26610","username":"shifangsheng","screenName":"时芳胜"}}]
     */
    private PaginatorEntity paginator;
    private List<ResultsEntity> results;

    public void setPaginator(PaginatorEntity paginator) {
        this.paginator = paginator;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public PaginatorEntity getPaginator() {
        return paginator;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class PaginatorEntity {
        /**
         * total : 50701
         * last : http://api.wallstreetcn.com/v2/posts?type=news&status=published&order=-created_at&limit=25&page=2029
         * previous : http://api.wallstreetcn.com/v2/posts?type=news&status=published&order=-created_at&limit=25&page=1
         * next : http://api.wallstreetcn.com/v2/posts?type=news&status=published&order=-created_at&limit=25&page=2
         */
        private int total;
        private String last;
        private String previous;
        private String next;

        public void setTotal(int total) {
            this.total = total;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public int getTotal() {
            return total;
        }

        public String getLast() {
            return last;
        }

        public String getPrevious() {
            return previous;
        }

        public String getNext() {
            return next;
        }
    }

    public static class ResultsEntity {
        /**
         * tags : [{"id":"3","tagName":"美联储"},{"id":"386","tagName":"FOMC"}]
         * summary :
         * count : 2
         * imageUrl : http://posts.cdn.wallstcn.com/e9/92/16/150126095901-stocks-to-buy-780x439.jpg
         * commentStatus : open
         * codeType : html
         * type : news
         * sourceUrl :
         * commentCount : 0
         * url : http://wallstreetcn.com/node/223064
         * id : 223064
         * title : 摩根士丹利：09年来全球股市首现全线买入信号
         * createdAt : 1441168978
         * sourceName :
         * slug : KVA7fhy4
         * user : {"id":"26610","username":"shifangsheng","screenName":"时芳胜"}
         */
        private List<TagsEntity> tags;
        private String summary;
        private String count;
        private String imageUrl;
        private String commentStatus;
        private String codeType;
        private String type;
        private String sourceUrl;
        private String commentCount;
        private String url;
        private String id;
        private String title;
        private String createdAt;
        private String sourceName;
        private String slug;
        private UserEntity user;

        public void setTags(List<TagsEntity> tags) {
            this.tags = tags;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setCommentStatus(String commentStatus) {
            this.commentStatus = commentStatus;
        }

        public void setCodeType(String codeType) {
            this.codeType = codeType;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public String getSummary() {
            return summary;
        }

        public String getCount() {
            return count;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getCommentStatus() {
            return commentStatus;
        }

        public String getCodeType() {
            return codeType;
        }

        public String getType() {
            return type;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public String getUrl() {
            return url;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getSourceName() {
            return sourceName;
        }

        public String getSlug() {
            return slug;
        }

        public UserEntity getUser() {
            return user;
        }

        public static class TagsEntity {
            /**
             * id : 3
             * tagName : 美联储
             */
            private String id;
            private String tagName;

            public void setId(String id) {
                this.id = id;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }

            public String getId() {
                return id;
            }

            public String getTagName() {
                return tagName;
            }
        }

        public static class UserEntity {
            /**
             * id : 26610
             * username : shifangsheng
             * screenName : 时芳胜
             */
            private String id;
            private String username;
            private String screenName;

            public void setId(String id) {
                this.id = id;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setScreenName(String screenName) {
                this.screenName = screenName;
            }

            public String getId() {
                return id;
            }

            public String getUsername() {
                return username;
            }

            public String getScreenName() {
                return screenName;
            }
        }
    }
}
