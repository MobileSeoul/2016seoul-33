package com.hany.mosquitoforecast.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by HanyLuv on 2016-08-03.
 */
public class DataManager {
    private final int OEN_DAY = -1;
    private final int WEEK = 7;

    public enum FormattingType {
        DATE_FORMAT("yyyy-MM-dd"),
        DATE_FORMAT_KR("yyyy년 MM월 dd일"),
        DATE_FORMAT_MONTH_AND_DAY("MM-dd");
        private String formatType;
        FormattingType(String formatType) {
            this.formatType = formatType;
        }
        public String getFormatType() {
            return formatType;
        }
    }

    private Date mDate;
    private Calendar mCalendar;
    private SimpleDateFormat mFormatter;

    public DataManager() {
        mDate = new Date();
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);
        mFormatter = new SimpleDateFormat(FormattingType.DATE_FORMAT.getFormatType(), Locale.KOREA);
    }

    /**
     * @author hany
     * @description 오늘의 날짜를 기본 포멧형식으로 가져온다.
     *  @return eg. 2016-03-12
     */
    public String getTodayDate() {
        mFormatter = new SimpleDateFormat(FormattingType.DATE_FORMAT.getFormatType(), Locale.KOREA);
        return formattingDate(mDate);
    }

    public String getTodayDate(FormattingType formattingType) {
        mFormatter = new SimpleDateFormat(formattingType.getFormatType(), Locale.KOREA);
        return formattingDate(mDate);
    }

    /**

     * @author hany
     * @description 오늘의 날짜를 기준으로 7일전의 날짜리스트를 반환한다.
     */
    public ArrayList<String> getWeekDate() {
        ArrayList<String> weekData = new ArrayList<>();
        weekData.add(getTodayDate());
        for (int count = 1; count < WEEK; count++) {
            mCalendar.add(mCalendar.DAY_OF_MONTH, OEN_DAY);
            weekData.add(formattingDate(mCalendar.getTime()));
        }
        mCalendar.add(mCalendar.DAY_OF_MONTH, WEEK);
        LogManager.e("list " + weekData.toString() + " size " + weekData.size());
        return weekData;
    }

    private String formattingDate(Date date) {
        return mFormatter.format(date);
    }

    public String extractMonthAndDayFromDate(String date) {
        String strMonthAndDay = null;
        if (checkDateFormatType(date)) {
            strMonthAndDay = changeFormatting(date,FormattingType.DATE_FORMAT_MONTH_AND_DAY);
        } else {
            /// TODO: 16. 8. 8. 데이터 형식이 옳바르지않을경우.. 
        LogManager.e("데이터형식이 옳바르지않습니다.");
        }
        return strMonthAndDay;
    }


    /***/
    private String changeFormatting(String date,FormattingType formatting) {
        SimpleDateFormat changeFormatter = new SimpleDateFormat(formatting.getFormatType(), Locale.KOREA);
        ParsePosition pos = new ParsePosition(0);
        Date temp = mFormatter.parse(date, pos);
        return changeFormatter.format(temp);
    }

    private boolean checkDateFormatType(String date) {
        boolean dateValidity = true;
        mFormatter.setLenient(false);
        try {
            mFormatter.parse(date);
        } catch (ParseException e) {
            dateValidity = false;
            LogManager.e(e.getMessage());
        } catch (IllegalArgumentException e) {
            dateValidity = false;
            LogManager.e(e.getMessage());
        }
        return dateValidity;
    }


}
