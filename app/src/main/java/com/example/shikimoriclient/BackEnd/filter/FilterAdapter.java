package com.example.shikimoriclient.BackEnd.filter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.shikimoriclient.R;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FilterAdapter {
    private Set<Integer> uniqueGroup;
    private LinkedHashMap<FilterElement, Integer> filterConditions;

    public FilterAdapter(int groupCount, List<Integer> childrenCount, Collection<Integer> uniqueGroup) {
        filterConditions = new LinkedHashMap<>();
        for (int i = 0; i < groupCount; i++) {
            for (int j = 0; j < childrenCount.get(i); j++) {
                FilterElement filterElement = new FilterElement(i, j);
                filterConditions.put(filterElement, 0);
            }
        }
        this.uniqueGroup = new HashSet<>();
        this.uniqueGroup.addAll(uniqueGroup);
    }

    public int changeCondition(int groupId, int childId) {
        if (!uniqueGroup.contains(groupId)) {
            List<FilterElement> uniqCounter = new LinkedList<>();
            for (FilterElement counter : filterConditions.keySet()) {
                if (counter.getGroupId() == groupId && counter.getChildId() != childId) {
                    uniqCounter.add(counter);
                }
            }
            for (FilterElement counter : uniqCounter) {
                filterConditions.put(counter, 0);
            }
            FilterElement filterElement = new FilterElement(groupId, childId);
            int count = filterConditions.get(filterElement) + 1;
            if (count % 2 == 0) {
                count = 0;
            }
            filterConditions.put(filterElement, count);
            return count;
        } else {
            FilterElement filterElement = new FilterElement(groupId, childId);
            int count = filterConditions.get(filterElement) + 1;
            if (count % 3 == 0) {
                count = 0;
            }
            filterConditions.put(filterElement, count);
            return count;
        }
    }

    public void reset() {
        for (FilterElement counter : filterConditions.keySet()) {
            filterConditions.put(counter, 0);
        }
    }

    public LinkedHashMap<FilterElement, Integer> getCounter() {
        return filterConditions;
    }
}

