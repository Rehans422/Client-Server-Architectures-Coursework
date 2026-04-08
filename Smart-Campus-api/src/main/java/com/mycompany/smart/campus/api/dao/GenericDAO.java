/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.dao;

import com.mycompany.smart.campus.api.models.BaseModel;
import java.util.List;

/**
 *
 * @author rsoha
 */
public class GenericDAO<T extends BaseModel> {

    private final List<T> items;

    public GenericDAO(List<T> items) {
        this.items = items;
    }

    public List<T> getAll() {
        return items;
    }

    public T getById(int id) {
        String idToGet = String.valueOf(id);
        for (T item : items) {
            if (item.getId().equals(idToGet)) {
                return item;
            }
        }
        return null;
    }

    public void add(T item) {
        int maxId = 0;
        for (T i : items) {
            int id = Integer.parseInt(i.getId());
            if (id > maxId) {
                maxId = id;
            }
        }
        item.setId(String.valueOf(maxId + 1));
        items.add(item);
    }
    
    public void update(T updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            
            if (item.getId().equals(updatedItem.getId())) {
                items.set(i, updatedItem);
                return;
            }
        }
    }
    
    public void delete(String id) {
        items.removeIf(item -> item.getId().equals(id));
    }
}
