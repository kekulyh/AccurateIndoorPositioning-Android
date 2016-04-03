package au.usyd.capstone.indoorandroid.domain;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by LYH on 16/4/3.
 */
public class Floor implements Serializable {

    private Drawable floorImage;

    private String floorName;

    private String floorDetail;

    public Drawable getFloorImage() {
        return floorImage;
    }

    public void setFloorImage(Drawable floorImage) {
        this.floorImage = floorImage;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getFloorDetail() {
        return floorDetail;
    }

    public void setFloorDetail(String floorDetail) {
        this.floorDetail = floorDetail;
    }
}
