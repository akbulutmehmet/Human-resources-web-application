package com.bm470.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
    private Date date;
    private SimpleDateFormat simpleDateFormat;
    private String dateString;

    public DateConvert () {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
    public DateConvert (Date date) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.date = date;
        convertDatetoString();
    }
    public DateConvert (String dateString) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.dateString = dateString;
        convertStringtoDate();
    }

    private void convertDatetoString () {
        try{
            dateString = simpleDateFormat.format(date);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void convertStringtoDate () {
        try{
            date = simpleDateFormat.parse(dateString);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        convertDatetoString();

    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
        convertStringtoDate();
    }
}
