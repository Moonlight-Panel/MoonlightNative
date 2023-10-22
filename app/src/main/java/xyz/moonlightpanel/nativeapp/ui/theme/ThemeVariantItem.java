package xyz.moonlightpanel.nativeapp.ui.theme;

import android.graphics.Color;
import android.util.Log;

import java.nio.ByteBuffer;

public class ThemeVariantItem {
    private ThemeVariantItemType type;
    private String description;
    private byte[] value;
    private String id;

    public ThemeVariantItemType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(ThemeVariantItemType type) {
        this.type = type;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Color asColor(){
        return Color.valueOf((value[0]+128)/255f, (value[1]+128)/255f, (value[2]+128)/255f, (value[3]+128)/255f);
    }

    public double asDouble(){
        ByteBuffer wrapped = ByteBuffer.wrap(value);
        return wrapped.getDouble();
    }
}
