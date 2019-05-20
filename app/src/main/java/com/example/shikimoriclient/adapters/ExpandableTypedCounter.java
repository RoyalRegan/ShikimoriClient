package com.example.shikimoriclient.adapters;

import android.util.Log;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


//TODO: REWRITE!!!!!!
public class ExpandableTypedCounter {
    private int groupId;
    private int childId;
    private Set<Integer> uniqGroup;
    private LinkedHashMap<ExpandableTypedCounter, Integer> pressedCount;

    public ExpandableTypedCounter(int groupId, int childId) {
        this.groupId = groupId;
        this.childId = childId;
    }

    public ExpandableTypedCounter(int groupCount, List<Integer> childrensCount, Collection<Integer> uniqGroup) {
        pressedCount = new LinkedHashMap<>();
        for (int i = 0; i < groupCount; i++) {
            for (int j = 0; j < childrensCount.get(i); j++) {
                ExpandableTypedCounter expandableTypedCounter = new ExpandableTypedCounter(i, j);
                pressedCount.put(expandableTypedCounter, 0);
            }
        }
        this.uniqGroup = new HashSet<>();
        this.uniqGroup.addAll(uniqGroup);
    }

    public int incCounter(int groupId, int childId) {
        if (!uniqGroup.contains(groupId)) {
            List<ExpandableTypedCounter> uniqCounter = new LinkedList<>();
            for (ExpandableTypedCounter counter : pressedCount.keySet()) {
                if (counter.getGroupId() == groupId && counter.getChildId() != childId) {
                    uniqCounter.add(counter);
                }
            }
            for (ExpandableTypedCounter counter : uniqCounter) {
                Log.e("UniqCounter", counter.toString());
                pressedCount.put(counter, 0);
            }
        }
        ExpandableTypedCounter expandableTypedCounter = new ExpandableTypedCounter(groupId, childId);
        int count = pressedCount.get(expandableTypedCounter) + 1;
        if (count % 3 == 0) {
            count = 0;
        }
        pressedCount.put(expandableTypedCounter, count);
        return count;
    }

    public LinkedHashMap<ExpandableTypedCounter, Integer> getCounter() {
        return pressedCount;
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
        if (!(o instanceof ExpandableTypedCounter)) return false;
        ExpandableTypedCounter that = (ExpandableTypedCounter) o;
        return getGroupId() == that.getGroupId() &&
                getChildId() == that.getChildId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getChildId());
    }

}