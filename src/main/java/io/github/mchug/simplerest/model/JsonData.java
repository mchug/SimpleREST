package io.github.mchug.simplerest.model;

import org.json.JSONObject;

import java.util.Objects;

public abstract class JsonData
{
    private final JSONObject rawJsonObject;

    public JsonData(JSONObject rawJsonObject)
    {
        this.rawJsonObject = rawJsonObject;
    }

    public abstract JSONObject toJSON();

    public JSONObject getRawJsonObject()
    {
        return rawJsonObject;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof JsonData)) return false;
        JsonData jsonData = (JsonData) o;
        return Objects.equals(rawJsonObject, jsonData.rawJsonObject);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(rawJsonObject);
    }

    @Override
    public String toString()
    {
        return "JsonData{" +
                "rawJsonObject=" + rawJsonObject +
                '}';
    }
}
