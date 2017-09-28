package com.hany.mosquitoforecast.vo.mosquito;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by HanyLuv on 2016-08-03.
 */

@Root
public class MosquitoStatus implements Parcelable {
    @Element(name = "list_total_count")
    private int listTotalCount;
    @Element(name = "RESULT")
    private Result result;
    @Element(name = "row")
    private Row row;

    //현재 모기의 단계
    private String mosquitoStep;

    public MosquitoStatus() {

    }

    public void setMosquitoStep(String mosquitoStep) {
        this.mosquitoStep = mosquitoStep;
    }

    public String getMosquitoStep() {
        return mosquitoStep;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public int getListTotalCount() {
        return listTotalCount;
    }

    public void setListTotalCount(int listTotalCount) {
        this.listTotalCount = listTotalCount;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(listTotalCount);
    }

    @Override
    public String toString() {
        return "MosquitoStatus{" +
                "listTotalCount=" + listTotalCount +
                ", result=" + result +
                ", row=" + row +
                '}';
    }

    protected MosquitoStatus(Parcel in) {
        listTotalCount = in.readInt();
    }

    public static final Creator<MosquitoStatus> CREATOR = new Creator<MosquitoStatus>() {
        @Override
        public MosquitoStatus createFromParcel(Parcel in) {
            return new MosquitoStatus(in);
        }

        @Override
        public MosquitoStatus[] newArray(int size) {
            return new MosquitoStatus[size];
        }
    };
}
