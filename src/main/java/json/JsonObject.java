package json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private HashMap<String, Json> data = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair: jsonPairs) {
            data.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        return "{" + getJsonObjectBody() + "}";
    }
    private String getJsonObjectBody() {
        StringBuilder jsonStr = new StringBuilder();
        Iterator<Map.Entry<String, Json>> jsonIterator = data.entrySet()
                .iterator();
        while (jsonIterator.hasNext()) {
            Map.Entry<String, Json> jsonEntry = jsonIterator.next();
            jsonStr.append(jsonEntry.getKey());
            jsonStr.append(": ");
            jsonStr.append(jsonEntry.getValue().toJson());
            if (jsonIterator.hasNext()) {
                jsonStr.append(", ");
            }
        }
        return jsonStr.toString();
    }

    public void add(JsonPair jsonPair) {
        data.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return data.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject result = new JsonObject();
        for (String name: names) {
            if (!Objects.isNull(find(name))) {
                result.add(new JsonPair(name, find(name)));
            }
        }
        return result;
    }
}
