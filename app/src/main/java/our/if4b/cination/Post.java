package our.if4b.cination;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable{
    private String id;
    private String content;
    private String user_id;
    private String created_date;
    private String modified_date;
    private String username;

    protected Post(Parcel in) {
        id = in.readString();
        content = in.readString();
        user_id = in.readString();
        created_date = in.readString();
        modified_date = in.readString();
        username = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(content);
        dest.writeString(user_id);
        dest.writeString(created_date);
        dest.writeString(modified_date);
        dest.writeString(username);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getModified_date() {
        return modified_date;
    }

    public String getUsername() {
        return username;
    }
}
