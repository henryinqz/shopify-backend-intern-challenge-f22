package com.henryinqz.shopifybackendinternchallengef22.repository;

import com.henryinqz.shopifybackendinternchallengef22.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
}
