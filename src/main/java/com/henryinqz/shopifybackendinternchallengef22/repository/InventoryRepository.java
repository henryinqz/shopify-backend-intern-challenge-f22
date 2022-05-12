package com.henryinqz.shopifybackendinternchallengef22.repository;

import com.henryinqz.shopifybackendinternchallengef22.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
}
