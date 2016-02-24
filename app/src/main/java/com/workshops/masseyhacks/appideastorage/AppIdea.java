package com.workshops.masseyhacks.appideastorage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Michael on 2016-02-24.
 */
public class AppIdea implements Parcelable {

    private String name;
    private String dueDate;
    private String description;
    private int priority;

    public AppIdea(final String name, final String dueDate, final String description, final int priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.description = description;
        this.priority = priority;
    }

    public AppIdea(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);

        name = data[0];
        dueDate = data[1];
        description = data[2];
        priority = Integer.getInteger(data[3]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { name, dueDate, description, Integer.toString(priority) });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AppIdea createFromParcel(Parcel in) {
            return new AppIdea(in);
        }

        public AppIdea[] newArray(int size) {
            return new AppIdea[size];
        }
    };
}
