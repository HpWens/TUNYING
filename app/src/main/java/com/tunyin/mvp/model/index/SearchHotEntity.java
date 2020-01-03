package com.tunyin.mvp.model.index;

import java.util.List;

/**
 * create by wangrongchao
 * on 2019/10/31
 **/
public class SearchHotEntity {


    private String hotSearch;

    public String getHotSearch() {
        return hotSearch;
    }

    public void setHotSearch(String hotSearch) {
        this.hotSearch = hotSearch;
    }

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * num : 2
         * title : 小睡眠
         */

        private String num;
        private String title;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
