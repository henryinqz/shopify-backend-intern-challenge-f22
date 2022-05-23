package com.henryinqz.shopifybackendinternchallengef22.repository;

import com.henryinqz.shopifybackendinternchallengef22.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    @Query(value = "{ 'location.id' : ?0 }")
    List<Inventory> findByLocationId(String locationId);
}
