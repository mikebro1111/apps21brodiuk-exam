package domain;

import json.*;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private JsonArray exams;
    private static int badMark = 3;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        ArrayList<Json> jsonList = new ArrayList<Json>();
        for (Tuple<String, Integer> exam: exams) {
            JsonObject jsonExam = new JsonObject(
                    new JsonPair("course"
                            , new JsonString(exam.key)),
                    new JsonPair("mark"
                            , new JsonNumber(exam.value)),
                    new JsonPair("passed"
                            , new JsonBoolean(exam.value < badMark ? false : true)));
            jsonList.add(jsonExam);
        }
        this.exams = new JsonArray(jsonList.toArray(new Json[0]));
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();
        jsonObject.add(new JsonPair("exams", exams));
        return jsonObject;
    }
}