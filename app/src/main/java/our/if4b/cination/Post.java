package our.if4b.cination;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Post implements Parcelable{
    private String id;
    private String negara;
    private String ibukota;
    private String user_id;
    private String created_date;
    private String modified_date;
    private String username;

    protected Post(Parcel in) {
        id = in.readString();
        negara = in.readString();
        ibukota = in.readString();
        user_id = in.readString();
        created_date = in.readString();
        modified_date = in.readString();
        username = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(negara);
        dest.writeString(ibukota);
        dest.writeString(user_id);
        dest.writeString(created_date);
        dest.writeString(modified_date);
        dest.writeString(username);
    }

    public String getId() {
        return id;
    }

    public String getNegara() {
        return negara;
    }

    public String getIbukota() {
        return ibukota;
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
