package com.reactive.reactiveProgrammingGradle02.repository;

import com.reactive.reactiveProgrammingGradle02.document.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface ItemRepository extends ReactiveMongoRepository<Item,String> {


    Flux<Item> findByDescription(String description);
}
