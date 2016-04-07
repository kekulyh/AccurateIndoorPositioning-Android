package au.usyd.capstone.indoorandroid.domain;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by LYH on 16/4/7.
 */
public class Room implements Serializable {

    private Drawable roomImage;

    private String roomName;

    private String roomDetail;

    public Drawable getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(Drawable roomImage) {
        this.roomImage = roomImage;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(String roomDetail) {
        this.roomDetail = roomDetail;
    }
}
