package com.wangyz.commonmvvm.bean.model;

import java.util.List;

/**
 * @author wangyz
 * @time 2019/7/3 16:55
 * @description Project
 */
public class Project {


    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","author":"TzuChiangLi","chapterId":294,
     * "chapterName":"完整项目","collect":false,"courseId":13,
     * "desc":"当时毕业公司安排我学习Android的开发以快速开发项目，所以在公司一直MVC的模式开发，在看了现在的主流及趋势后，发现MVP
     * 是进步路上的必修课，所以就参考了很多大神的项目学习MVP的写法和思路。\r\n","envelopePic":"https://wanandroid
     * .com/blogimgs/bf9ed860-3ab1-4bea-9c9a-6de3c75e861b.png","fresh":false,"id":8658,
     * "link":"http://www.wanandroid.com/blog/show/2617","niceDate":"1天前","origin":"",
     * "prefix":"","projectLink":"https://github.com/TzuChiangLi/WanAndroid",
     * "publishTime":1561983121000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"WanAndroid 个人第一个练手项目分享",
     * "type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"guofudong",
     * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"Meterail
     * Design风格商城类项目","envelopePic":"https://www.wanandroid
     * .com/blogimgs/5a3eb937-b429-49f4-9a77-d3ff15cf4988.png","fresh":false,"id":8547,
     * "link":"http://www.wanandroid.com/blog/show/2598","niceDate":"2019-06-19","origin":"",
     * "prefix":"","projectLink":"https://github.com/guofudong/EShop",
     * "publishTime":1560920162000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Meterail Design风格商城类项目",
     * "type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"jiwenjie",
     * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
     * "desc":"毕设项目，自己搭建的前后台，Android使用 Kotlin + RxJava + Retrofit + MVP
     * 实现。比较好的封装了代码以及多状态界面的切换。后台代码也可以看到，通过 IDEA 使用 SSM 搭建。","envelopePic":"https://www.wanandroid
     * .com/resources/image/pc/default_project_img.jpg","fresh":false,"id":8605,
     * "link":"http://www.wanandroid.com/blog/show/2609","niceDate":"2019-06-12","origin":"",
     * "prefix":"","projectLink":"https://github.com/jiwenjie/Graduation_App",
     * "publishTime":1560352439000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Meterail Design风格毕设项目",
     * "type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"xing16","chapterId":294,
     * "chapterName":"完整项目","collect":false,"courseId":13,"desc":"基于 wanandroid.com 和 gank.io API
     * 开发的 MVP + Retrofit + RxJava2 组件化模式开发的 Android APP","envelopePic":"https://www.wanandroid
     * .com/blogimgs/bbb7bedc-84f0-436c-a46a-1122705b6000.png","fresh":false,"id":8544,
     * "link":"http://www.wanandroid.com/blog/show/2595","niceDate":"2019-06-01","origin":"",
     * "prefix":"","projectLink":"https://github.com/xing16/WanAndroid",
     * "publishTime":1559357087000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"MVP 组件化 WanAndroid APP",
     * "type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"mouxuefei",
     * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"RN练手项目",
     * "envelopePic":"https://wanandroid.com/blogimgs/439ee0a6-cc89-493d-82b7-adad76396ac9.png",
     * "fresh":false,"id":8508,"link":"http://www.wanandroid.com/blog/show/2584",
     * "niceDate":"2019-05-28","origin":"","prefix":"","projectLink":"https://github
     * .com/mouxuefei/RN-wanAndroid","publishTime":1559045153000,"superChapterId":294,
     * "superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],
     * "title":"RN-WanAndroid的学习项目","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"",
     * "author":"goweii","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
     * "desc":"开发初期主要是为了试水一些自己开发的开源框架，但是后面发现本人对这个APP的使用频率还是挺高的，在坐地铁的时候都会拿出来刷一刷文章。所以决定把这个APP
     * 做好看，做好用，不至于影响刷文章的心情。\r\n\r\n如果你也觉得好用，欢迎给个star，谢谢。","envelopePic":"https://wanandroid
     * .com/blogimgs/eb948f06-8895-4b67-8bf9-1aa41dea75cb.png","fresh":false,"id":8501,
     * "link":"http://www.wanandroid.com/blog/show/2577","niceDate":"2019-05-28","origin":"",
     * "prefix":"","projectLink":"https://github.com/goweii/WanAndroid",
     * "publishTime":1559044835000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"简洁美观的WanAndroid客户端",
     * "type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Taonce","chapterId":294,
     * "chapterName":"完整项目","collect":false,"courseId":13,
     * "desc":"使用Kotlin语言开发的WanAndroid客户端，使用了AndroidX和RxJava、Retrofit、OkHttp等开元技术。",
     * "envelopePic":"https://wanandroid.com/blogimgs/a8ee81e1-a24c-431a-8730-9fe9b49f09cb.png",
     * "fresh":false,"id":8504,"link":"http://www.wanandroid.com/blog/show/2580",
     * "niceDate":"2019-05-28","origin":"","prefix":"","projectLink":"https://github
     * .com/Taonce/WanKotlin","publishTime":1559044791000,"superChapterId":294,
     * "superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],
     * "title":"WanAndroid ---- Kotlin版","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"",
     * "author":"ITGungnir","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
     * "desc":"Kotlin + MVVM + 模块化 + 响应式 的WanAndroid客户端，简约优雅。\r\n\r\n## 技术点\r\n*
     * 基于APT和ASM的GRouter路由框架实现模块化通信；\r\n* 参考Redux的原理，实现一套事件总线框架；\r\n* 封装MVVM和UI库，可供其他应用使用；\r\n*
     * 项目整体使用响应式编程风格，简介优雅易读。","envelopePic":"https://www.wanandroid
     * .com/blogimgs/d95d3a13-85ae-4aa7-bec0-ca9f4bba9eae.png","fresh":false,"id":8480,
     * "link":"http://www.wanandroid.com/blog/show/2575","niceDate":"2019-05-26","origin":"",
     * "prefix":"","projectLink":"https://github.com/ITGungnir/KotlinWanAndroid",
     * "publishTime":1558869577000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"WanAndroid模块化响应式客户端 很赞",
     * "type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"jimmysuncpt",
     * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
     * "desc":"UltraRecyclerView是一个封装多种特性的RecyclerView。主要功能包括：\r\n- 支持横向滑动／纵向滑动\r\n-
     * 支持分页滑动，并且支持对齐方式和距离\r\n- 支持循环滚动\r\n- 支持定时自动滚动，计时器使用Handler实现\r\n-
     * BannerView内置指示器，支持设置底部距离、已选/默认的颜色和宽度、高度和内部距离","envelopePic":"https://www.wanandroid
     * .com/blogimgs/0858c600-1b34-41c1-a2ff-f67cdc376558.png","fresh":false,"id":8467,
     * "link":"http://www.wanandroid.com/blog/show/2574","niceDate":"2019-05-20","origin":"",
     * "prefix":"","projectLink":"https://github.com/jimmysuncpt/UltraRecyclerView",
     * "publishTime":1558354164000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],
     * "title":"UltraRecyclerView是一个封装多种特性的RecyclerView","type":0,"userId":-1,"visible":1,
     * "zan":0},{"apkLink":"","author":"digtal","chapterId":294,"chapterName":"完整项目",
     * "collect":false,"courseId":13,"desc":"一款用来学习安卓的微信小程序","envelopePic":"https://www
     * .wanandroid.com/blogimgs/58dbab50-0821-40fc-9db4-0f4c10803650.png","fresh":false,
     * "id":8400,"link":"http://www.wanandroid.com/blog/show/2572","niceDate":"2019-05-12",
     * "origin":"","prefix":"","projectLink":"https://github.com/digtal/wanandroid-program",
     * "publishTime":1557670418000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"玩安卓微信小程序","type":0,
     * "userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"liangperfect","chapterId":294,
     * "chapterName":"完整项目","collect":false,"courseId":13,"desc":"wanAndroid项目基于 Material Design
     * + MVP + Rxjava2 + Retrofit + Room + Glide\r\n该项目主要是用作于学习,
     * 采用现在热门的Android框架\r\n数据接口是用的鸿神的wanAndroid APi https://www.wanandroid.com/blog/show/2\r\n",
     * "envelopePic":"https://www.wanandroid
     * .com/blogimgs/01e987fe-1a12-42bc-b170-06b709745545.png","fresh":false,"id":8397,
     * "link":"http://www.wanandroid.com/blog/show/2569","niceDate":"2019-05-12","origin":"",
     * "prefix":"","projectLink":"https://github.com/liangperfect/aLiangWanAndroid",
     * "publishTime":1557670141000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"wanAndroid学习项目","type":0,
     * "userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"cwjfeifei","chapterId":294,
     * "chapterName":"完整项目","collect":false,"courseId":13,"desc":"Kotlin  高德地图 仿微信 发送位置",
     * "envelopePic":"https://www.wanandroid
     * .com/blogimgs/ba2f751d-c323-476c-8114-777569836805.png","fresh":false,"id":8395,
     * "link":"http://www.wanandroid.com/blog/show/2567","niceDate":"2019-05-12","origin":"",
     * "prefix":"","projectLink":"https://github.com/cwjfeifei/GDLocation",
     * "publishTime":1557670059000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Kotlin  高德地图 仿微信 发送位置",
     * "type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"lulululbj",
     * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"Github 上关于
     * Wanandroid 的客户端也层出不穷，Java的，Kotlin 的，Flutter 的，Mvp 的，MVMM 的，各种各样，但是还没看到
     * Kotlin+MVVM+LiveData+协程 版本的，加上最近正在看 MVVM 和 LiveData，就着手把我之前写的 Mvp 版本的 Wanandroid 改造成 MVVM
     * + Kotlin + LiveData + 协程 版本。","envelopePic":"https://wanandroid
     * .com/blogimgs/54f4350f-039d-48b6-a38b-0933e1405004.png","fresh":false,"id":8273,
     * "link":"http://www.wanandroid.com/blog/show/2554","niceDate":"2019-04-18","origin":"",
     * "prefix":"","projectLink":"https://github.com/lulululbj/wanandroid/tree/mvvm-kotlin",
     * "publishTime":1555593015000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"真香！Kotlin+MVVM+LiveData+协程
     * 打造 Wanandroid！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"",
     * "author":"OnexZgj","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
     * "desc":"该应用程序是玩Android部分api和干货网站部分api的flutter版本的技术类文章查看APP。\r\n主要功能包括：首页、项目、公众号、搜索等。",
     * "envelopePic":"https://wanandroid.com/blogimgs/4681d6c0-0d76-4c69-a866-7ad66dde10cd.png",
     * "fresh":false,"id":8269,"link":"http://www.wanandroid.com/blog/show/2550",
     * "niceDate":"2019-04-18","origin":"","prefix":"","projectLink":"https://github
     * .com/OnexZgj/flutter_onex","publishTime":1555592366000,"superChapterId":294,
     * "superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],
     * "title":"是时候体验一波Flutter啦","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"",
     * "author":"dlgchg","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
     * "desc":"使用flutter开发的github客户端","envelopePic":"https://wanandroid
     * .com/blogimgs/af4530e7-f244-4b3f-b278-9be41044e811.png","fresh":false,"id":8268,
     * "link":"http://www.wanandroid.com/blog/show/2549","niceDate":"2019-04-18","origin":"",
     * "prefix":"","projectLink":"https://github.com/dlgchg/flutter_github",
     * "publishTime":1555592326000,"superChapterId":294,"superChapterName":"开源项目主Tab",
     * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"使用flutter开发的github客户端",
     * "type":0,"userId":-1,"visible":0,"zan":0}],"offset":0,"over":false,"pageCount":10,
     * "size":15,"total":147}
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
         * datas : [{"apkLink":"","author":"TzuChiangLi","chapterId":294,"chapterName":"完整项目",
         * "collect":false,"courseId":13,
         * "desc":"当时毕业公司安排我学习Android的开发以快速开发项目，所以在公司一直MVC的模式开发，在看了现在的主流及趋势后，发现MVP
         * 是进步路上的必修课，所以就参考了很多大神的项目学习MVP的写法和思路。\r\n","envelopePic":"https://wanandroid
         * .com/blogimgs/bf9ed860-3ab1-4bea-9c9a-6de3c75e861b.png","fresh":false,"id":8658,
         * "link":"http://www.wanandroid.com/blog/show/2617","niceDate":"1天前","origin":"",
         * "prefix":"","projectLink":"https://github.com/TzuChiangLi/WanAndroid",
         * "publishTime":1561983121000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"WanAndroid
         * 个人第一个练手项目分享","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"",
         * "author":"guofudong","chapterId":294,"chapterName":"完整项目","collect":false,
         * "courseId":13,"desc":"Meterail Design风格商城类项目","envelopePic":"https://www.wanandroid
         * .com/blogimgs/5a3eb937-b429-49f4-9a77-d3ff15cf4988.png","fresh":false,"id":8547,
         * "link":"http://www.wanandroid.com/blog/show/2598","niceDate":"2019-06-19","origin":"",
         * "prefix":"","projectLink":"https://github.com/guofudong/EShop",
         * "publishTime":1560920162000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Meterail
         * Design风格商城类项目","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"",
         * "author":"jiwenjie","chapterId":294,"chapterName":"完整项目","collect":false,
         * "courseId":13,"desc":"毕设项目，自己搭建的前后台，Android使用 Kotlin + RxJava + Retrofit + MVP
         * 实现。比较好的封装了代码以及多状态界面的切换。后台代码也可以看到，通过 IDEA 使用 SSM 搭建。","envelopePic":"https://www
         * .wanandroid.com/resources/image/pc/default_project_img.jpg","fresh":false,"id":8605,
         * "link":"http://www.wanandroid.com/blog/show/2609","niceDate":"2019-06-12","origin":"",
         * "prefix":"","projectLink":"https://github.com/jiwenjie/Graduation_App",
         * "publishTime":1560352439000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Meterail
         * Design风格毕设项目","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"",
         * "author":"xing16","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
         * "desc":"基于 wanandroid.com 和 gank.io API 开发的 MVP + Retrofit + RxJava2 组件化模式开发的 Android
         * APP","envelopePic":"https://www.wanandroid
         * .com/blogimgs/bbb7bedc-84f0-436c-a46a-1122705b6000.png","fresh":false,"id":8544,
         * "link":"http://www.wanandroid.com/blog/show/2595","niceDate":"2019-06-01","origin":"",
         * "prefix":"","projectLink":"https://github.com/xing16/WanAndroid",
         * "publishTime":1559357087000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"MVP 组件化 WanAndroid
         * APP","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"mouxuefei",
         * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"RN练手项目",
         * "envelopePic":"https://wanandroid
         * .com/blogimgs/439ee0a6-cc89-493d-82b7-adad76396ac9.png","fresh":false,"id":8508,
         * "link":"http://www.wanandroid.com/blog/show/2584","niceDate":"2019-05-28","origin":"",
         * "prefix":"","projectLink":"https://github.com/mouxuefei/RN-wanAndroid",
         * "publishTime":1559045153000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"RN-WanAndroid的学习项目",
         * "type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"goweii",
         * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
         * "desc":"开发初期主要是为了试水一些自己开发的开源框架，但是后面发现本人对这个APP的使用频率还是挺高的，在坐地铁的时候都会拿出来刷一刷文章。所以决定把这个APP
         * 做好看，做好用，不至于影响刷文章的心情。\r\n\r\n如果你也觉得好用，欢迎给个star，谢谢。","envelopePic":"https://wanandroid
         * .com/blogimgs/eb948f06-8895-4b67-8bf9-1aa41dea75cb.png","fresh":false,"id":8501,
         * "link":"http://www.wanandroid.com/blog/show/2577","niceDate":"2019-05-28","origin":"",
         * "prefix":"","projectLink":"https://github.com/goweii/WanAndroid",
         * "publishTime":1559044835000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"简洁美观的WanAndroid客户端",
         * "type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Taonce",
         * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
         * "desc":"使用Kotlin语言开发的WanAndroid客户端，使用了AndroidX和RxJava、Retrofit、OkHttp等开元技术。",
         * "envelopePic":"https://wanandroid.com/blogimgs/a8ee81e1-a24c-431a-8730-9fe9b49f09cb
         * .png","fresh":false,"id":8504,"link":"http://www.wanandroid.com/blog/show/2580",
         * "niceDate":"2019-05-28","origin":"","prefix":"","projectLink":"https://github
         * .com/Taonce/WanKotlin","publishTime":1559044791000,"superChapterId":294,
         * "superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],
         * "title":"WanAndroid ---- Kotlin版","type":0,"userId":-1,"visible":1,"zan":0},
         * {"apkLink":"","author":"ITGungnir","chapterId":294,"chapterName":"完整项目",
         * "collect":false,"courseId":13,"desc":"Kotlin + MVVM + 模块化 + 响应式
         * 的WanAndroid客户端，简约优雅。\r\n\r\n## 技术点\r\n* 基于APT和ASM的GRouter路由框架实现模块化通信；\r\n*
         * 参考Redux的原理，实现一套事件总线框架；\r\n* 封装MVVM和UI库，可供其他应用使用；\r\n* 项目整体使用响应式编程风格，简介优雅易读。",
         * "envelopePic":"https://www.wanandroid
         * .com/blogimgs/d95d3a13-85ae-4aa7-bec0-ca9f4bba9eae.png","fresh":false,"id":8480,
         * "link":"http://www.wanandroid.com/blog/show/2575","niceDate":"2019-05-26","origin":"",
         * "prefix":"","projectLink":"https://github.com/ITGungnir/KotlinWanAndroid",
         * "publishTime":1558869577000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"WanAndroid模块化响应式客户端
         * 很赞","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"jimmysuncpt",
         * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
         * "desc":"UltraRecyclerView是一个封装多种特性的RecyclerView。主要功能包括：\r\n- 支持横向滑动／纵向滑动\r\n-
         * 支持分页滑动，并且支持对齐方式和距离\r\n- 支持循环滚动\r\n- 支持定时自动滚动，计时器使用Handler实现\r\n-
         * BannerView内置指示器，支持设置底部距离、已选/默认的颜色和宽度、高度和内部距离","envelopePic":"https://www.wanandroid
         * .com/blogimgs/0858c600-1b34-41c1-a2ff-f67cdc376558.png","fresh":false,"id":8467,
         * "link":"http://www.wanandroid.com/blog/show/2574","niceDate":"2019-05-20","origin":"",
         * "prefix":"","projectLink":"https://github.com/jimmysuncpt/UltraRecyclerView",
         * "publishTime":1558354164000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],
         * "title":"UltraRecyclerView是一个封装多种特性的RecyclerView","type":0,"userId":-1,"visible":1,
         * "zan":0},{"apkLink":"","author":"digtal","chapterId":294,"chapterName":"完整项目",
         * "collect":false,"courseId":13,"desc":"一款用来学习安卓的微信小程序","envelopePic":"https://www
         * .wanandroid.com/blogimgs/58dbab50-0821-40fc-9db4-0f4c10803650.png","fresh":false,
         * "id":8400,"link":"http://www.wanandroid.com/blog/show/2572","niceDate":"2019-05-12",
         * "origin":"","prefix":"","projectLink":"https://github.com/digtal/wanandroid-program",
         * "publishTime":1557670418000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"玩安卓微信小程序","type":0,
         * "userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"liangperfect",
         * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
         * "desc":"wanAndroid项目基于 Material Design + MVP + Rxjava2 + Retrofit + Room +
         * Glide\r\n该项目主要是用作于学习,采用现在热门的Android框架\r\n数据接口是用的鸿神的wanAndroid APi https://www
         * .wanandroid.com/blog/show/2\r\n","envelopePic":"https://www.wanandroid
         * .com/blogimgs/01e987fe-1a12-42bc-b170-06b709745545.png","fresh":false,"id":8397,
         * "link":"http://www.wanandroid.com/blog/show/2569","niceDate":"2019-05-12","origin":"",
         * "prefix":"","projectLink":"https://github.com/liangperfect/aLiangWanAndroid",
         * "publishTime":1557670141000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"wanAndroid学习项目",
         * "type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"cwjfeifei",
         * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"Kotlin
         * 高德地图 仿微信 发送位置","envelopePic":"https://www.wanandroid
         * .com/blogimgs/ba2f751d-c323-476c-8114-777569836805.png","fresh":false,"id":8395,
         * "link":"http://www.wanandroid.com/blog/show/2567","niceDate":"2019-05-12","origin":"",
         * "prefix":"","projectLink":"https://github.com/cwjfeifei/GDLocation",
         * "publishTime":1557670059000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Kotlin  高德地图 仿微信
         * 发送位置","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"lulululbj",
         * "chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"Github 上关于
         * Wanandroid 的客户端也层出不穷，Java的，Kotlin 的，Flutter 的，Mvp 的，MVMM 的，各种各样，但是还没看到
         * Kotlin+MVVM+LiveData+协程 版本的，加上最近正在看 MVVM 和 LiveData，就着手把我之前写的 Mvp 版本的 Wanandroid 改造成
         * MVVM + Kotlin + LiveData + 协程 版本。","envelopePic":"https://wanandroid
         * .com/blogimgs/54f4350f-039d-48b6-a38b-0933e1405004.png","fresh":false,"id":8273,
         * "link":"http://www.wanandroid.com/blog/show/2554","niceDate":"2019-04-18","origin":"",
         * "prefix":"","projectLink":"https://github.com/lulululbj/wanandroid/tree/mvvm-kotlin",
         * "publishTime":1555593015000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],
         * "title":"真香！Kotlin+MVVM+LiveData+协程 打造 Wanandroid！","type":0,"userId":-1,"visible":1,
         * "zan":0},{"apkLink":"","author":"OnexZgj","chapterId":294,"chapterName":"完整项目",
         * "collect":false,"courseId":13,
         * "desc":"该应用程序是玩Android部分api和干货网站部分api的flutter版本的技术类文章查看APP。\r\n主要功能包括：首页、项目、公众号、搜索等。",
         * "envelopePic":"https://wanandroid.com/blogimgs/4681d6c0-0d76-4c69-a866-7ad66dde10cd
         * .png","fresh":false,"id":8269,"link":"http://www.wanandroid.com/blog/show/2550",
         * "niceDate":"2019-04-18","origin":"","prefix":"","projectLink":"https://github
         * .com/OnexZgj/flutter_onex","publishTime":1555592366000,"superChapterId":294,
         * "superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],
         * "title":"是时候体验一波Flutter啦","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"",
         * "author":"dlgchg","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,
         * "desc":"使用flutter开发的github客户端","envelopePic":"https://wanandroid
         * .com/blogimgs/af4530e7-f244-4b3f-b278-9be41044e811.png","fresh":false,"id":8268,
         * "link":"http://www.wanandroid.com/blog/show/2549","niceDate":"2019-04-18","origin":"",
         * "prefix":"","projectLink":"https://github.com/dlgchg/flutter_github",
         * "publishTime":1555592326000,"superChapterId":294,"superChapterName":"开源项目主Tab",
         * "tags":[{"name":"项目","url":"/project/list/1?cid=294"}],
         * "title":"使用flutter开发的github客户端","type":0,"userId":-1,"visible":0,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 10
         * size : 15
         * total : 147
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
             * author : TzuChiangLi
             * chapterId : 294
             * chapterName : 完整项目
             * collect : false
             * courseId : 13
             * desc : 当时毕业公司安排我学习Android的开发以快速开发项目，所以在公司一直MVC的模式开发，在看了现在的主流及趋势后，发现MVP
             * 是进步路上的必修课，所以就参考了很多大神的项目学习MVP的写法和思路。
             * <p>
             * envelopePic : https://wanandroid.com/blogimgs/bf9ed860-3ab1-4bea-9c9a-6de3c75e861b
             * .png
             * fresh : false
             * id : 8658
             * link : http://www.wanandroid.com/blog/show/2617
             * niceDate : 1天前
             * origin :
             * prefix :
             * projectLink : https://github.com/TzuChiangLi/WanAndroid
             * publishTime : 1561983121000
             * superChapterId : 294
             * superChapterName : 开源项目主Tab
             * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
             * title : WanAndroid 个人第一个练手项目分享
             * type : 0
             * userId : -1
             * visible : 0
             * zan : 0
             */

            private String apkLink;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
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

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
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
                 * url : /project/list/1?cid=294
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
