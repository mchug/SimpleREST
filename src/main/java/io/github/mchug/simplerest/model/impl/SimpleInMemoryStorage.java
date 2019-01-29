package io.github.mchug.simplerest.model.impl;

import io.github.mchug.simplerest.model.SimpleStorage;

import java.util.*;

public class SimpleInMemoryStorage implements SimpleStorage<Integer, SimpleData>
{
    private static final SimpleStorage<Integer, SimpleData> instance = new SimpleInMemoryStorage();

    public static SimpleStorage<Integer, SimpleData> getInstance()
    {
        return instance;
    }

    private final Map<Integer, SimpleData> mapStorage;
    private final Random random;

    private SimpleInMemoryStorage()
    {
        this.mapStorage = new HashMap<>();
        this.random = new Random();
    }

    @Override
    public List<SimpleData> getAll()
    {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public List<SimpleData> clear()
    {
        List<SimpleData> allData = getAll();
        mapStorage.clear();
        return allData;
    }

    @Override
    public SimpleData save(SimpleData newEntity) throws IllegalArgumentException
    {
        Objects.requireNonNull(newEntity);

        if (newEntity.getId() == null)
        {
            return saveWithRandomId(newEntity);
        }

        if (mapStorage.containsKey(newEntity.getId()))
        {
            throw new IllegalArgumentException("Entity already exists with specified ID = " + newEntity.getId());
        }

        return mapStorage.put(newEntity.getId(), newEntity);
    }

    private SimpleData saveWithRandomId(SimpleData newEntityWithoutId)
    {
        SimpleData newEntityWithId = new SimpleData(
                random.nextInt(), newEntityWithoutId.getTimestamp(), newEntityWithoutId.getPayload()
        );

        mapStorage.put(newEntityWithId.getId(), newEntityWithId);

        return newEntityWithId;
    }

    @Override
    public SimpleData getById(Integer id)
    {
        return mapStorage.get(id);
    }

    @Override
    public SimpleData updateById(Integer id, SimpleData newEntity) throws IllegalArgumentException
    {
        SimpleData byId = getById(id);

        if (byId == null)
        {
            throw new IllegalArgumentException("Can't find entity with specified ID = " + id);
        }

        mapStorage.replace(id, newEntity);

        return byId;
    }

    @Override
    public SimpleData deleteById(Integer id) throws IllegalArgumentException
    {
        SimpleData byId = getById(id);

        if (byId == null)
        {
            throw new IllegalArgumentException("Can't find entity with specified ID = " + id);
        }

        mapStorage.remove(id);

        return byId;
    }
}
