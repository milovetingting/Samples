package com.wangyz.mvvm.bean;

import java.util.List;

/**
 * @author wangyz
 * @time 2020/4/21 15:20
 * @description Home
 */
public class Home {

    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"simplepeng","canEdit":false,"chapterId":358,"chapterName":"项目基础功能","collect":false,"courseId":13,"desc":"SpiderMan能为您做的事：\r\n在Android手机上显示闪退崩溃信息，直接分享给相关开发人员!\r\n再也不用担心测试妹妹给你重现怎样操作才能触发闪退崩溃的尴尬！\r\n再也不用担心产品给你说哪儿哪儿会闪退崩溃，但是又不能场景还原的无奈！\r\n再也不用担心某些国产Rom禁止异常log输出！\r\n再也不用担心开发工具异常log信息输出时灵时不灵！","descMd":"","envelopePic":"https://www.wanandroid.com/blogimgs/16ebacd1-b1d8-48d5-b889-138857057067.png","fresh":true,"id":12962,"link":"https://www.wanandroid.com/blog/show/2739","niceDate":"15小时前","niceShareDate":"15小时前","origin":"","prefix":"","projectLink":"https://github.com/simplepeng/SpiderMan","publishTime":1587396402000,"selfVisible":0,"shareDate":1587396402000,"shareUser":"","superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=358"}],"title":"🔥🔥🔥崩溃日志手机端显示 ，测试妹妹的最爱，开发哥哥的小棉袄","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xfhy","canEdit":false,"chapterId":402,"chapterName":"跨平台应用","collect":false,"courseId":13,"desc":"Flutter版本 WanAndroid客户端 适合Flutter入门学习 ","descMd":"","envelopePic":"https://www.wanandroid.com/blogimgs/f6481711-10b1-4852-acd9-4ef41f2ab89c.png","fresh":true,"id":12960,"link":"https://www.wanandroid.com/blog/show/2737","niceDate":"15小时前","niceShareDate":"15小时前","origin":"","prefix":"","projectLink":"https://github.com/xfhy/WanAndroid-Flutter","publishTime":1587396253000,"selfVisible":0,"shareDate":1587396253000,"shareUser":"","superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=402"}],"title":"Flutter版本 WanAndroid客户端 适合Flutter入门学习 ","type":0,"userId":-1,"visible":1,"zan":0}],"offset":0,"over":false,"pageCount":416,"size":20,"total":8308}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"simplepeng","canEdit":false,"chapterId":358,"chapterName":"项目基础功能","collect":false,"courseId":13,"desc":"SpiderMan能为您做的事：\r\n在Android手机上显示闪退崩溃信息，直接分享给相关开发人员!\r\n再也不用担心测试妹妹给你重现怎样操作才能触发闪退崩溃的尴尬！\r\n再也不用担心产品给你说哪儿哪儿会闪退崩溃，但是又不能场景还原的无奈！\r\n再也不用担心某些国产Rom禁止异常log输出！\r\n再也不用担心开发工具异常log信息输出时灵时不灵！","descMd":"","envelopePic":"https://www.wanandroid.com/blogimgs/16ebacd1-b1d8-48d5-b889-138857057067.png","fresh":true,"id":12962,"link":"https://www.wanandroid.com/blog/show/2739","niceDate":"15小时前","niceShareDate":"15小时前","origin":"","prefix":"","projectLink":"https://github.com/simplepeng/SpiderMan","publishTime":1587396402000,"selfVisible":0,"shareDate":1587396402000,"shareUser":"","superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=358"}],"title":"🔥🔥🔥崩溃日志手机端显示 ，测试妹妹的最爱，开发哥哥的小棉袄","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xfhy","canEdit":false,"chapterId":402,"chapterName":"跨平台应用","collect":false,"courseId":13,"desc":"Flutter版本 WanAndroid客户端 适合Flutter入门学习 ","descMd":"","envelopePic":"https://www.wanandroid.com/blogimgs/f6481711-10b1-4852-acd9-4ef41f2ab89c.png","fresh":true,"id":12960,"link":"https://www.wanandroid.com/blog/show/2737","niceDate":"15小时前","niceShareDate":"15小时前","origin":"","prefix":"","projectLink":"https://github.com/xfhy/WanAndroid-Flutter","publishTime":1587396253000,"selfVisible":0,"shareDate":1587396253000,"shareUser":"","superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=402"}],"title":"Flutter版本 WanAndroid客户端 适合Flutter入门学习 ","type":0,"userId":-1,"visible":1,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 416
         * size : 20
         * total : 8308
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * audit : 1
             * author : simplepeng
             * canEdit : false
             * chapterId : 358
             * chapterName : 项目基础功能
             * collect : false
             * courseId : 13
             * desc : SpiderMan能为您做的事：
             * 在Android手机上显示闪退崩溃信息，直接分享给相关开发人员!
             * 再也不用担心测试妹妹给你重现怎样操作才能触发闪退崩溃的尴尬！
             * 再也不用担心产品给你说哪儿哪儿会闪退崩溃，但是又不能场景还原的无奈！
             * 再也不用担心某些国产Rom禁止异常log输出！
             * 再也不用担心开发工具异常log信息输出时灵时不灵！
             * descMd :
             * envelopePic : https://www.wanandroid.com/blogimgs/16ebacd1-b1d8-48d5-b889-138857057067.png
             * fresh : true
             * id : 12962
             * link : https://www.wanandroid.com/blog/show/2739
             * niceDate : 15小时前
             * niceShareDate : 15小时前
             * origin :
             * prefix :
             * projectLink : https://github.com/simplepeng/SpiderMan
             * publishTime : 1587396402000
             * selfVisible : 0
             * shareDate : 1587396402000
             * shareUser :
             * superChapterId : 294
             * superChapterName : 开源项目主Tab
             * tags : [{"name":"项目","url":"/project/list/1?cid=358"}]
             * title : 🔥🔥🔥崩溃日志手机端显示 ，测试妹妹的最爱，开发哥哥的小棉袄
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private int audit;
            private String author;
            private boolean canEdit;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String descMd;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<TagsBean> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public boolean isCanEdit() {
                return canEdit;
            }

            public void setCanEdit(boolean canEdit) {
                this.canEdit = canEdit;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDescMd() {
                return descMd;
            }

            public void setDescMd(String descMd) {
                this.descMd = descMd;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public static class TagsBean {
                /**
                 * name : 项目
                 * url : /project/list/1?cid=358
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
