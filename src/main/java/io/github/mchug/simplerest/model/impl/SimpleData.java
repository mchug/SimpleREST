package io.github.mchug.simplerest.model.impl;

import io.github.mchug.simplerest.model.Identifiable;
import io.github.mchug.simplerest.model.JsonData;
import org.json.JSONObject;

import java.time.Instant;
import java.util.Objects;

public class SimpleData extends JsonData implements Identifiable<Integer>
{
    private final String JSON_KEY_ID = "id";
    private final String JSON_KEY_TIMESTAMP = "timestamp";
    private final String JSON_KEY_PAYLOAD = "payload";

    private final Integer id;
    private final Instant timestamp;
    private final String payload;

    public SimpleData(JSONObject rawJsonObject)
    {
        super(rawJsonObject);
        this.id = getRawJsonObject().getInt(JSON_KEY_ID);
        this.timestamp = Instant.ofEpochMilli(getRawJsonObject().getLong(JSON_KEY_TIMESTAMP));
        this.payload = getRawJsonObject().getString(JSON_KEY_PAYLOAD);
    }

    public SimpleData(int id, Instant timestamp, String payload)
    {
        super(null);
        this.id = id;
        this.timestamp = timestamp;
        this.payload = payload;
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    public Instant getTimestamp()
    {
        return timestamp;
    }

    public String getPayload()
    {
        return payload;
    }

    @Override
    public JSONObject toJSON()
    {
        return new JSONObject()
                .accumulate(JSON_KEY_ID, id)
                .accumulate(JSON_KEY_TIMESTAMP, timestamp)
                .accumulate(JSON_KEY_PAYLOAD, payload);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof SimpleData)) return false;
        if (!super.equals(o)) return false;
        SimpleData that = (SimpleData) o;
        return id == that.id &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), id, timestamp, payload);
    }

    @Override
    public String toString()
    {
        return "SimpleData{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", payload='" + payload + '\'' +
                '}';
    }
}
