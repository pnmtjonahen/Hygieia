package com.capitalone.dashboard.repository;

import com.capitalone.dashboard.model.GogsRepo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GogsCollectorItemRepository extends BaseCollectorItemRepository<GogsRepo> {

    @Query(value="{ 'collectorId' : ?0, options.repoUrl : ?1, options.branch : ?2}")
    GogsRepo findGogsRepo(ObjectId collectorId, String url, String branch);

    @Query(value="{ 'collectorId' : ?0, enabled: true}")
    List<GogsRepo> findEnabledGogsRepos(ObjectId collectorId);
}
