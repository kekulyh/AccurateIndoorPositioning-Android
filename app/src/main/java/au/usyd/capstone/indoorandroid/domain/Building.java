package au.usyd.capstone.indoorandroid.domain;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by LYH on 16/4/3.
 */
public class Building implements Serializable {

    private Drawable buildingImage;

    private String buildingName;

    private String buildingOpeningTime;

    public Drawable getBuildingImage() {
        return buildingImage;
    }

    public void setBuildingImage(Drawable buildingImage) {
        this.buildingImage = buildingImage;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingOpeningTime() {
        return buildingOpeningTime;
    }

    public void setBuildingOpeningTime(String buildingOpeningTime) {
        this.buildingOpeningTime = buildingOpeningTime;
    }
}
