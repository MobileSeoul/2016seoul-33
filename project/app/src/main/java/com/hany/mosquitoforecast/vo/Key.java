package com.hany.mosquitoforecast.vo;

/**
 * Created by hany on 16. 8. 8..
 */
public interface Key {
    String TODAY_MOSQUITO_DATA = "today_mosquito_data";
    String WEEK_MOSQUITO_DATA = "week_mosquito_data";
    String NAVER_CLIENT_ID = "X-Naver-Client-Id";
    String NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";

    String STEP_ONE = "1단계(쾌적)";
    String STEP_TWO = "2단계(관심)";
    String STEP_THREE = "3단계(주의)";
    String STEP_FOUR = "4단계(불쾌)";
    String STEP_NULL = "NULL";

    String RESULT_FAILED = "INFO-200";
    String RESULT_SUCCESS = "INFO-000";

    enum SortType {
        SIM("sim"), //유사도순
        DATE("date"), //날짜순
        ASC("asc"), // 가격오름차순
        DSC("dsc"); //가격내림차순
        private String sortType;

        SortType() {
        }

        SortType(String sortType) {
            this.sortType = sortType;
        }

        public String getSortType() {
            return sortType;
        }
    }

}
