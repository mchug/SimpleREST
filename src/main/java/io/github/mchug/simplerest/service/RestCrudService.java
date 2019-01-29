package io.github.mchug.simplerest.service;

import io.github.mchug.simplerest.model.SimpleStorage;
import io.github.mchug.simplerest.model.impl.SimpleData;
import io.github.mchug.simplerest.model.impl.SimpleInMemoryStorage;

import java.util.List;

public class RestCrudService implements SimpleStorage<Integer, SimpleData>
{
    private final SimpleStorage<Integer, SimpleData> inMemoryStorage;

    public RestCrudService()
    {
        this.inMemoryStorage = SimpleInMemoryStorage.getInstance();
    }

    @Override
    public List<SimpleData> getAll()
    {
        return inMemoryStorage.getAll();
    }

    @Override
    public List<SimpleData> clear()
    {
        return inMemoryStorage.clear();
    }

    @Override
    public SimpleData save(SimpleData newEntity) throws IllegalArgumentException
    {
        return inMemoryStorage.save(newEntity);
    }

    @Override
    public SimpleData getById(Integer id)
    {
        return inMemoryStorage.getById(id);
    }

    @Override
    public SimpleData updateById(Integer id, SimpleData newEntity) throws IllegalArgumentException
    {
        return inMemoryStorage.updateById(id, newEntity);
    }

    @Override
    public SimpleData deleteById(Integer id) throws IllegalArgumentException
    {
        return inMemoryStorage.deleteById(id);
    }
}
