package com.training.todo_list.model.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.training.todo_list.utils.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * A "Todo" is uniquely identified by an id and a description (2 Todos cannot
 * have the same description). The description is entered by user. The timeCreation correspond to
 * the moment where the user asked to create the "Todo". IdTodoType is a link to a todoType.
 * IsDone is also choosen by user through interface.
 */
public class Todo implements Parcelable {

    private String mSDescription;
    private Date mTimeCreation;
    @Nullable private UUID mIdTodoType;
    private boolean mBIsDone;
    private UUID mId;

    public Todo(String pSDescription, Date pDayCreation,
                UUID pIdTodoType, boolean pBIsDone, UUID pId) {
        mSDescription = pSDescription;
        mTimeCreation = pDayCreation;
        mIdTodoType = pIdTodoType;
        mBIsDone = pBIsDone;
        mId = pId;
    }

    protected Todo(Parcel in) {
        mSDescription = in.readString();
        mBIsDone = in.readByte() != 0;
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public String description() {
        return mSDescription;
    }

    public Date timeCreation() {
        return mTimeCreation;
    }

    @Nullable public UUID idTodoType() {
        return mIdTodoType;
    }

    public boolean isDone() {
        return mBIsDone;
    }

    public UUID id() {
        return mId;
    }

    @Override
    public int hashCode() {
        String tSName = (null == mSDescription) ? "desc" : mSDescription;
        UUID tId = (null == mId) ? UUID.randomUUID() : mId;
        return tSName.hashCode() * 20 + tId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Todo))
            return false;
        Todo tOther = (Todo) o;
        boolean tBNameEquals = StringUtils.areEquals(mSDescription, tOther.mSDescription);
        boolean tBIdEquals = (null == mId && null == tOther.mId) ||
                (null != mId && null != tOther.mId && mId.equals(tOther.mId));
        return tBNameEquals && tBIdEquals;
    }

    public String getmSDescription() {
        return mSDescription;
    }

    public void setmSDescription(String mSDescription) {
        this.mSDescription = mSDescription;
    }

    public Date getmTimeCreation() {
        return mTimeCreation;
    }

    public void setmTimeCreation(Date mTimeCreation) {
        this.mTimeCreation = mTimeCreation;
    }

    @Nullable
    public UUID getmIdTodoType() {
        return mIdTodoType;
    }

    public void setmIdTodoType(@Nullable UUID mIdTodoType) {
        this.mIdTodoType = mIdTodoType;
    }

    public boolean ismBIsDone() {
        return mBIsDone;
    }

    public void setmBIsDone(boolean mBIsDone) {
        this.mBIsDone = mBIsDone;
    }

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSDescription);
        dest.writeByte((byte) (mBIsDone ? 1 : 0));
    }
}
