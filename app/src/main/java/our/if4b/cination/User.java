package our.if4b.cination;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String id;
    private String username;
    private String created_date;

    protected User(Parcel in) {
        id = in.readString();
        username = in.readString();
        created_date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(username);
        dest.writeString(created_date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}