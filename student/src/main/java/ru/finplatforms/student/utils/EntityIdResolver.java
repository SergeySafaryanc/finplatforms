package ru.finplatforms.student.utils;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;

@Log4j2
@RequiredArgsConstructor
public class EntityIdResolver implements ObjectIdResolver {

    private final EntityManager entityManager;

    @Override
    public void bindItem(ObjectIdGenerator.IdKey idKey, Object o) {

    }

    @Override
    public Object resolveId(ObjectIdGenerator.IdKey idKey) {
        return entityManager.find(idKey.scope, idKey.key);
    }

    @Override
    public ObjectIdResolver newForDeserialization(Object o) {
        return this;
    }

    @Override
    public boolean canUseFor(ObjectIdResolver objectIdResolver) {
        return false;
    }
}
