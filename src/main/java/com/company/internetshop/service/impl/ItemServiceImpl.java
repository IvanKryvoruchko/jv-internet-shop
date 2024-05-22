package com.company.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import com.company.internetshop.dao.ItemDao;
import com.company.internetshop.exceptions.DataProcessingExeption;
import com.company.internetshop.lib.anotations.Inject;
import com.company.internetshop.lib.anotations.Service;
import com.company.internetshop.model.Item;
import com.company.internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) throws DataProcessingExeption {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) throws DataProcessingExeption {
        return itemDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find item with id " + id));
    }

    @Override
    public Item update(Item item) throws DataProcessingExeption {
        return itemDao.update(item);
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingExeption {
        return itemDao.deleteById(id);
    }

    @Override
    public boolean delete(Item item) throws DataProcessingExeption {
        return itemDao.delete(item);
    }

    @Override
    public List<Item> getAll() throws DataProcessingExeption {
        return itemDao.getAll();
    }
}
