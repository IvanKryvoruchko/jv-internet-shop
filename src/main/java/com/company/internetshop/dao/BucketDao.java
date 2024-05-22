package com.company.internetshop.dao;

import java.util.Optional;

import com.company.internetshop.model.Bucket;
import com.company.internetshop.exceptions.DataProcessingExeption;

public interface BucketDao extends GenericDao<Bucket, Long> {

    Optional<Bucket> getByUserId(Long userId) throws DataProcessingExeption;

    void clear(Bucket bucket) throws DataProcessingExeption;
}
