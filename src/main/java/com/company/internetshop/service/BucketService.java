package com.company.internetshop.service;

import com.company.internetshop.model.Item;
import com.company.internetshop.exceptions.DataProcessingExeption;
import com.company.internetshop.model.Bucket;

public interface BucketService extends GenericService<Bucket, Long> {

    void addItem(Bucket bucket, Item item) throws DataProcessingExeption;

    void deleteItem(Bucket bucket, Item item) throws DataProcessingExeption;

    void clear(Bucket bucket) throws DataProcessingExeption;

    Bucket getByUserId(Long userId) throws DataProcessingExeption;
}
