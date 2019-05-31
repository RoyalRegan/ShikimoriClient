package com.example.shikimoriclient.BackEnd.data;

import java.util.LinkedHashMap;
import java.util.List;

public interface Searchable {
    LinkedHashMap<Integer, List<String>> getParamsValueByColumnId();
    String getParamsNameByColumnId(int id);
}
