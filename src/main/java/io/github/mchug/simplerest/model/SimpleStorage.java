package io.github.mchug.simplerest.model;

import java.util.List;

/**
 * Simple interface for CRUD storage
 */
public interface SimpleStorage<ID, T extends Identifiable<ID>>
{
    /**
     * Gets all entities of storage
     */
    List<T> getAll();

    /**
     * Clears storage
     *
     * @return all entities which were deleted from storage
     */
    List<T> clear();

    /**
     * Saves new entity to the storage
     *
     * @return saved entity
     * @throws IllegalArgumentException if entity already exists with specified ID
     */
    T save(T newEntity) throws IllegalArgumentException;

    /**
     * Searches entity by ID
     *
     * @return entity with specified ID or null
     */
    T getById(ID id);

    /**
     * Updates entity by ID
     *
     * @return replaced entity
     * @throws IllegalArgumentException if can't find entity with specified ID
     */
    T updateById(ID id, T newEntity) throws IllegalArgumentException;

    /**
     * Deletes entity by ID
     *
     * @return deleted entity
     * @throws IllegalArgumentException if can't find entity with specified ID
     */
    T deleteById(ID id) throws IllegalArgumentException;
}
