package xyz.moonlightpanel.nativeapp.ui.theme;

import android.graphics.Color;
import android.util.Log;

import java.nio.ByteBuffer;

public class ThemeVariantItemBuilder {
    private ThemeVariantItem item;
    public ThemeVariantItemBuilder(){
        item = new ThemeVariantItem();
    }

    public ThemeVariantItemBuilder withColor(int r, int g, int b, int a){
        item.setType(ThemeVariantItemType.color);
        byte[] col = new byte[4];
        col[0] = (byte)(r-128);
        col[1] = (byte)(g-128);
        col[2] = (byte)(b-128);
        col[3] = (byte)(a-128);

        item.setValue(col);

        return this;
    }

    public ThemeVariantItemBuilder withDouble(double num){
        ByteBuffer dbuf = ByteBuffer.allocate(8);
        dbuf.putDouble(num);
        item.setValue(dbuf.array());

        return this;
    }

    public ThemeVariantItemBuilder withDescription(String description){
        item.setDescription(description);

        return this;
    }

    public ThemeVariantItemBuilder withId(String id){
        item.setId(id);

        return this;
    }

    public ThemeVariantItem build(){
        return item;
    }
}
