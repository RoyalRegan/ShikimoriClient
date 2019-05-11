package com.example.shikimoriclient.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO: Add all categories
public class ExpandableListData {
    public static  HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        List<String> status = new ArrayList<>();
        status.add("Анонсировано");
        status.add("Сейчас выходит");
        status.add("Вышедшее");
        status.add("Недавно вышедшее");

        List<String> type = new ArrayList<>();
        type.add("TV Сериал");
        type.add("Фильм");
        type.add("OVA");
        type.add("ONA");
        type.add("Спешл");
        type.add("Клип");

        List<String> sort = new ArrayList<>();
        sort.add("По рейтингу");
        sort.add("По популярности");
        sort.add("По алфавиту");
        sort.add("По дате выхода");
        sort.add("По ID");

        List<String> season = new ArrayList<>();
        season.add("Зима");
        season.add("Весна");
        season.add("Лето");
        season.add("Осень");

        List genre = new ArrayList();
        genre.add("Комедия");
        genre.add("Романтика");
        genre.add("Школа");

        List censored = new ArrayList();
        censored.add("G");
        censored.add("PG");
        censored.add("PG-13");
        censored.add("R-17");
        censored.add("R+");

        List duration = new ArrayList();
        duration.add("До 10 минут");
        duration.add("До 30 минут");
        duration.add("Более 30 минут");

        List score = new ArrayList();
        score.add("8+");
        score.add("7+");
        score.add("6+");

        expandableListDetail.put("СТАТУС", status);
        expandableListDetail.put("ТИП", type);
        expandableListDetail.put("СОРТИРОВКА", sort);
        expandableListDetail.put("СЕЗОН", season);
        expandableListDetail.put("ЖАНРЫ", genre);
        expandableListDetail.put("РЕЙТИНГ", censored);
        expandableListDetail.put("ПРОДОЛЖИТЕЛЬНОСТЬ", duration);
        expandableListDetail.put("ОЦЕНКА", score);

        return expandableListDetail;
    }
}
