package com.example.shikimoriclient.BackEnd.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SearchableAnimeData implements Searchable {
    @Override
    public LinkedHashMap<Integer, List<String>> getParamsValueByColumnId() {
        LinkedHashMap<Integer, List<String>> expandableListDetail = new LinkedHashMap<>();

        List<String> status = new ArrayList<>();
        status.add("anons");
        status.add("ongoing");
        status.add("released");

        List<String> type = new ArrayList<>();
        type.add("tv_48");
        type.add("tv_24");
        type.add("tv_13");
        type.add("movie");
        type.add("ova");
        type.add("ona");
        type.add("special");
        type.add("music");

        List<String> sort = new ArrayList<>();
        sort.add("ranked");
        sort.add("popularity");
        sort.add("name");
        sort.add("aired_on");
        sort.add("id");

        List<String> myList = new ArrayList<>();
        myList.add("planned");
        myList.add("watching");
        myList.add("rewatching");
        myList.add("completed");
        myList.add("on_hold");
        myList.add("dropped");

        List<String> season = new ArrayList<>();
        season.add("summer_2019");
        season.add("spring_2019");
        season.add("winter_2019");
        season.add("autumn_2018");
        season.add("2019");
        season.add("2018");
        season.add("2016_2017");
        season.add("2011_2015");
        season.add("2000_2010");
        season.add("199x");
        season.add("198x");

        List<String> score = new ArrayList<>();
        score.add("8");
        score.add("7");
        score.add("6");

        List<String> duration = new ArrayList<>();
        duration.add("S");
        duration.add("D");
        duration.add("F");

        List<String> censored = new ArrayList<>();
        censored.add("g");
        censored.add("pg");
        censored.add("pg_13");
        censored.add("r");
        censored.add("r_plus");
        censored.add("rx");

        List<String> genre = new ArrayList<>();
        genre.add("27");
        genre.add("28");
        genre.add("42");
        genre.add("25");
        genre.add("26");
        genre.add("43");
        genre.add("4");
        genre.add("22");
        genre.add("23");
        genre.add("5");
        genre.add("17");
        genre.add("32");
        genre.add("38");
        genre.add("35");
        genre.add("6");
        genre.add("7");
        genre.add("15");
        genre.add("8");
        genre.add("11");
        genre.add("13");
        genre.add("29");
        genre.add("16");
        genre.add("3");
        genre.add("18");
        genre.add("19");
        genre.add("20");
        genre.add("36");
        genre.add("39");
        genre.add("2");
        genre.add("40");
        genre.add("21");
        genre.add("37");
        genre.add("30");
        genre.add("31");
        genre.add("14");
        genre.add("24");
        genre.add("10");
        genre.add("1");
        genre.add("9");
        genre.add("41");
        genre.add("12");
        genre.add("33");
        genre.add("34");

        expandableListDetail.put(0, status);
        expandableListDetail.put(1, type);
        expandableListDetail.put(2, sort);
        expandableListDetail.put(3, myList);
        expandableListDetail.put(4, season);
        expandableListDetail.put(5, genre);
        expandableListDetail.put(6, censored);
        expandableListDetail.put(7, duration);
        expandableListDetail.put(8, score);

        return expandableListDetail;
    }

    @Override
    public String getParamsNameByColumnId(int id) {
        LinkedHashMap<Integer, String> expandableListDetail = new LinkedHashMap<>();
        expandableListDetail.put(0, "status");
        expandableListDetail.put(1, "kind");
        expandableListDetail.put(2, "order");
        expandableListDetail.put(3, "mylist");
        expandableListDetail.put(4, "season");
        expandableListDetail.put(5, "genre");
        expandableListDetail.put(6, "rating");
        expandableListDetail.put(7, "duration");
        expandableListDetail.put(8, "score");
        return expandableListDetail.get(id);
    }
}
