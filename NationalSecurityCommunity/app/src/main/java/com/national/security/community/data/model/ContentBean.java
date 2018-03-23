package com.national.security.community.data.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ContentBean implements MultiItemEntity {

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_DATA = 2;
    private int itemType;

    public String getPinnedHeaderName() {
        return pinnedHeaderName;
    }

    public void setPinnedHeaderName(String pinnedHeaderName) {
        this.pinnedHeaderName = pinnedHeaderName;
    }

    private String pinnedHeaderName;

    public ContentBean(int itemType) {
        this.itemType = itemType;
    }
    public ContentBean(int itemType, String pinnedHeaderName) {
        this(itemType);
        this.pinnedHeaderName = pinnedHeaderName;
    }
    @Override
    public int getItemType() {
        return itemType;
    }

}
