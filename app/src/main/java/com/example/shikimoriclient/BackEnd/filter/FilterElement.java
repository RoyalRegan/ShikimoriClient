package com.example.shikimoriclient.BackEnd.filter;

import java.util.Objects;


public class FilterElement {
    private int groupId;
    private int childId;

    public FilterElement(int groupId, int childId) {
        this.groupId = groupId;
        this.childId = childId;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getChildId() {
        return childId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterElement)) return false;
        FilterElement that = (FilterElement) o;
        return getGroupId() == that.getGroupId() &&
                getChildId() == that.getChildId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getChildId());
    }
}