package com.example.shikimoriclient.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

//TODO: Check all positions
public class ExpandableListAnimeData {
    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<>();

        List<String> status = new ArrayList<>();
        status.add("Анонсировано");
        status.add("Сейчас выходит");
        status.add("Вышедшее");

        List<String> type = new ArrayList<>();
        type.add("TV Сериал длинный");
        type.add("TV Сериал средний");
        type.add("TV Сериал короткий");
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
        season.add("Лето 2019");
        season.add("Весна 2019");
        season.add("Зима 2019");
        season.add("Осень 2018");
        season.add("2019 год");
        season.add("2018 год");
        season.add("2017-2016");
        season.add("2015-2011");
        season.add("2010-2000");
        season.add("1990е годы");
        season.add("1980е годы");

        List<String> score = new ArrayList<>();
        score.add("8+");
        score.add("7+");
        score.add("6+");

        List<String> duration = new ArrayList<>();
        duration.add("До 10 минут");
        duration.add("До 30 минут");
        duration.add("Более 30 минут");

        List<String> censored = new ArrayList<>();
        censored.add("G");
        censored.add("PG");
        censored.add("PG-13");
        censored.add("R-17");
        censored.add("R+");

        List<String> genre = new ArrayList<>();
        genre.add("Сёнен");
        genre.add("Сёнен Ай");
        genre.add("Сейнен");
        genre.add("Сёдзе");
        genre.add("Сёдзе Ай");
        genre.add("Дзёсей");
        genre.add("Комедия");
        genre.add("Романтика");
        genre.add("Школа");
        genre.add("Безумие");
        genre.add("Боевые искусства");
        genre.add("Вампиры");
        genre.add("Военное");
        genre.add("Гарем");
        genre.add("Демоны");
        genre.add("Детектив");
        genre.add("Детское");
        genre.add("Драма");
        genre.add("Игры");
        genre.add("Исторический");
        genre.add("Космос");
        genre.add("Магия");
        genre.add("Машины");
        genre.add("Меха");
        genre.add("Музыка");
        genre.add("Пародия");
        genre.add("Повседневность");
        genre.add("Полиция");
        genre.add("Приключения");
        genre.add("Психологические");
        genre.add("Самураи");
        genre.add("Сверхъестественное");
        genre.add("Спорт");
        genre.add("Супер сила");
        genre.add("Ужасы");
        genre.add("Фантастика");
        genre.add("Фентези");
        genre.add("Экшен");
        genre.add("Этти");
        genre.add("Триллер");
        genre.add("Хентай");
        genre.add("Яой");
        genre.add("Юри");

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
