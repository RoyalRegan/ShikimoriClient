package com.example.shikimoriclient.BackEnd.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SearchableMangaData implements Searchable {
    @Override
    public LinkedHashMap<Integer, List<String>> getParamsValueByColumnId() {
        LinkedHashMap<Integer, List<String>> expandableListDetail = new LinkedHashMap<>();

        List<String> status = new ArrayList<>();
        status.add("anons");
        status.add("ongoing");
        status.add("released");

        List<String> type = new ArrayList<>();
        type.add("manga");
        type.add("manhwa");
        type.add("manhua");
        type.add("one_shot");
        type.add("doujin");

        List<String> sort = new ArrayList<>();
        sort.add("ranked");
        sort.add("popularity");
        sort.add("name");
        sort.add("aired_on");
        sort.add("id");

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

        List<String> genre = new ArrayList<>();
        genre.add("47");
        genre.add("55");
        genre.add("52");
        genre.add("63");
        genre.add("73");
        genre.add("87");
        genre.add("49");
        genre.add("62");
        genre.add("60");
        genre.add("90");
        genre.add("66");
        genre.add("64");
        genre.add("70");
        genre.add("71");
        genre.add("72");
        genre.add("46");
        genre.add("77");
        genre.add("50");
        genre.add("79");
        genre.add("69");
        genre.add("86");
        genre.add("58");
        genre.add("84");
        genre.add("83");
        genre.add("78");
        genre.add("86");
        genre.add("54");
        genre.add("89");
        genre.add("68");
        genre.add("67");
        genre.add("88");
        genre.add("48");
        genre.add("76");
        genre.add("82");
        genre.add("80");
        genre.add("53");
        genre.add("57");
        genre.add("56");
        genre.add("51");
        genre.add("81");
        genre.add("59");
        genre.add("65");
        genre.add("75");

        expandableListDetail.put(0, status);
        expandableListDetail.put(1, type);
        expandableListDetail.put(2, sort);
        expandableListDetail.put(3, season);
        expandableListDetail.put(4, genre);
        expandableListDetail.put(5, score);

        return expandableListDetail;
    }

    @Override
    public String getParamsNameByColumnId(int id) {
        LinkedHashMap<Integer, String> expandableListDetail = new LinkedHashMap<>();
        expandableListDetail.put(0, "status");
        expandableListDetail.put(1, "kind");
        expandableListDetail.put(2, "order");
        expandableListDetail.put(3, "season");
        expandableListDetail.put(4, "genre");
        expandableListDetail.put(5, "score");
        return expandableListDetail.get(id);
    }
}
