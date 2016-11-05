package net.nitrado.api.order;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;

public class Dimension {
    private String id;
    private String name;
    private DimensionValues values;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DimensionValue getValue(String[] previousDimensions) {
        return values.get(path(previousDimensions));
    }

    public DimensionValue getDefaultValue() {
        return values.values().iterator().next();
    }



    public static class DimensionValues extends HashMap<String, DimensionValue> {

    }

    public static abstract class DimensionValue {
    }

    public static class SimpleDimensionValue extends DimensionValue {
        //private String key;
        private int[] value;
        public SimpleDimensionValue(int[] value) {
           // this.key = key;
            this.value = value;
        }

        /*public String getKey() {
            return key;
        }*/

        public int[] getValue() {
            return value;
        }
    }

    public static class ComplexDimensionValue extends DimensionValue {
        private NameDescription[] values;

        public ComplexDimensionValue(NameDescription[] values) {
            this.values = values;
        }
        public NameDescription[] getValues() {
            return values;
        }
    }

    public static class NameDescription {
        private String key;
        private String name;
        private String[] description;
        public NameDescription(String key, String name, String[] description) {
            this.key = key;
            this.name = name;
            this.description = description;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String[] getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return name; // TODO print name and description?
        }
    }

    public static class PriceDimensionValue extends DimensionValue {
        private int value;
        public PriceDimensionValue( int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Complex construct to generate the classes from the recursive DimensionValues thing
     */
    public static class DimensionValuesDeserializer implements JsonDeserializer<DimensionValues> {

        public DimensionValues deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null) { return null;}

            if (!json.isJsonObject()) {
                throw new JsonParseException("the values of a dimension should be an object {map}");
            }

            DimensionValues values = new DimensionValues();

            String[] path = new String[0];
            handleJson(json, path, values, context);

            return values;
        }

        public void handleJson(JsonElement json, String[] path, DimensionValues values, JsonDeserializationContext context) {

            if (json == null||json.isJsonNull()) {
                return;
            }
            if (json.isJsonArray()) {
                // this is a simple value (int array)
                int[] value = context.deserialize(json, int[].class);
                values.put(path(path), new SimpleDimensionValue(value));
                return;
            }
            if (json.isJsonObject()) {
                Set<Map.Entry<String, JsonElement>> entries = json.getAsJsonObject().entrySet();

                JsonElement firstObj = entries.iterator().next().getValue();
                if (firstObj.isJsonObject()) {
                    Set<Map.Entry<String, JsonElement>> entries2 = firstObj.getAsJsonObject().entrySet();
                    if (entries2.iterator().next().getKey().equals("name")) { // TODO: are there no dimensions named "name"
                        NameDescription[] intValues = new NameDescription[entries.size()];

                        int i = 0;
                        for (Map.Entry<String, JsonElement> entry: entries) {
                            JsonObject obj = entry.getValue().getAsJsonObject();
                            JsonArray desc= obj.get("description").getAsJsonArray();
                            String[] strDesc = new String[desc.size()];
                            int j = 0;
                            for (JsonElement str : desc) {
                                strDesc[j] = str.getAsString();
                                j++;
                            }
                            intValues[i] = new NameDescription(entry.getKey(), obj.get("name").getAsString(), strDesc);
                            i++;
                        }

                        ComplexDimensionValue value = new ComplexDimensionValue(intValues);
                        values.put(path(path), value);
                        return;
                    }
                }

                // test if this is a price object
                for (Map.Entry<String, JsonElement> entry : entries) {
                    if (entry.getKey().equals("price")) {
                        // this is a price value
                        PriceDimensionValue value = new PriceDimensionValue(json.getAsJsonObject().get("price").getAsInt());
                        values.put(path(path), value);
                        return;
                    }
                }

                // well, this just seems to be another dimension

                for (Map.Entry<String, JsonElement> entry: entries) {
                    String[] newPath = new String[path.length+1];
                    for (int i = 0; i < path.length; i++) {
                        newPath[i] = path[i];
                    }
                    // build path
                    newPath[path.length] = entry.getKey();

                    handleJson(entry.getValue(), newPath, values, context);
                }

                return;
            }

            throw new JsonParseException("No idea what to do with "+json);
        }
    }

    // TODO: is now used to create hash-map key. Make more efficient
    public static String path(String[] path) {
        String p = "";
        for (int i = 0; i < path.length; i++) {
            if (path[i] != null) {
                p += "/" + path[i];
            }
        }
        return p;
    }
}
