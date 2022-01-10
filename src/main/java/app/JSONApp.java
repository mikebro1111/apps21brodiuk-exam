package app;

import domain.*;
import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JSONApp {
    public static void main(String[] args) {
        Json jYear = new JsonNumber(2);
        print(jYear); // 2

        Json jMarks = new JsonArray(new JsonNumber(3), new JsonNumber(4));
        print(jMarks); // [3, 4]

        JsonPair name = new JsonPair("name", new JsonString("Andrii"));
        JsonPair surname = new JsonPair("surname", new JsonString("Rodionov"));
        JsonPair marks = new JsonPair("marks", jMarks);
        JsonPair year = new JsonPair("year", jYear);
        JsonObject jsonObj = new JsonObject(name, surname, year, marks);
        print(jsonObj); // {'name': 'Andrii', 'surname': 'Rodionov', 'year': 2, 'marks': [3, 4]}

        print(jsonObj.projection("surname", "age", "year", "marks")); // {'surname': 'Rodionov', 'year': 2, 'marks': [3, 4]}

        BasicStudent basicStudent = new BasicStudent("Andrii", "Rodionov", 2);
        print(basicStudent.toJsonObject()); // {'name': 'Andrii', 'surname': 'Rodionov', 'year': 2}

    }

    private static void print(Json json) {
        System.out.println(json.toJson());
    }

    public static JsonObject sessionResult() {
        JsonPair name = new JsonPair("name", new JsonString("Andrii"));
        JsonPair surname = new JsonPair("surname", new JsonString("Rodionov"));
        JsonPair year = new JsonPair("year", new JsonNumber(2));

        JsonPair OOPCourse = new JsonPair("course", new JsonString("OOP"));
        JsonPair MathCourse = new JsonPair("course", new JsonString("Math"));
        JsonPair EnglishCourse = new JsonPair("course", new JsonString("English"));

        JsonPair markTwo = new JsonPair("mark", new JsonNumber(2));
        JsonPair markThree = new JsonPair("mark", new JsonNumber(3));
        JsonPair markFive = new JsonPair("mark", new JsonNumber(5));

        JsonPair passedTrue = new JsonPair("passed", new JsonBoolean(true));
        JsonPair passedFalse = new JsonPair("passed", new JsonBoolean(false));

        JsonObject OOP = new JsonObject(OOPCourse, markThree, passedTrue);
        JsonObject English = new JsonObject(EnglishCourse, markFive, passedTrue);
        JsonObject Math = new JsonObject(MathCourse, markTwo, passedFalse);

        JsonArray examsArray = new JsonArray(OOP, English, Math);
        JsonPair exams = new JsonPair("exams", examsArray);

        JsonObject jsonObject = new JsonObject(name, surname, year, exams);
        return jsonObject;
    }
}
