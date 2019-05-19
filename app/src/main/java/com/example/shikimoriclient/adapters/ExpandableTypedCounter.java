package com.example.shikimoriclient.adapters;

import java.util.Objects;

public class GroupChild {
    int groupId;
    int childId;

    public GroupChild(int groupId, int childId) {
        this.groupId = groupId;
        this.childId = childId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupChild)) return false;
        GroupChild that = (GroupChild) o;
        return getGroupId() == that.getGroupId() &&
                getChildId() == that.getChildId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getChildId());
    }
}