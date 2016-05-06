package com.capitalone.dashboard.collector;


import com.capitalone.dashboard.model.Collector;
import com.capitalone.dashboard.model.CollectorType;
import com.capitalone.dashboard.model.GogsRepo;
import com.capitalone.dashboard.repository.BaseCollectorRepository;
import com.capitalone.dashboard.repository.CommitRepository;
import com.capitalone.dashboard.repository.ComponentRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.util.List;
import com.capitalone.dashboard.repository.GogsCollectorItemRepository;

/**
 * CollectorTask that fetches Commit information from Gogs
 */
@Component
public class GogsCollectorTask extends CollectorTask<Collector> {

    private static final Log LOG = LogFactory.getLog(GogsCollectorTask.class);

    private final BaseCollectorRepository<Collector> collectorRepository;
    private final GogsCollectorItemRepository gogsRepoRepository;
    private final CommitRepository commitRepository;
    private final GogsClient gogsClient;
    private final GogsSettings gogsSettings;
    private final ComponentRepository dbComponentRepository;

    @Autowired
    public GogsCollectorTask(TaskScheduler taskScheduler,
            BaseCollectorRepository<Collector> collectorRepository,
            GogsCollectorItemRepository gogsRepoRepository,
            CommitRepository commitRepository,
            GogsClient gogsClient,
            GogsSettings gogsSettings,
            ComponentRepository dbComponentRepository) {
        super(taskScheduler, "Gogs");
        this.collectorRepository = collectorRepository;
        this.gogsRepoRepository = gogsRepoRepository;
        this.commitRepository = commitRepository;
        this.gogsClient = gogsClient;
        this.gogsSettings = gogsSettings;
        this.dbComponentRepository = dbComponentRepository;
    }

    @Override
    public Collector getCollector() {
        Collector protoType = new Collector();
        protoType.setName("Gogs");
        protoType.setCollectorType(CollectorType.SCM);
        protoType.setOnline(true);
        protoType.setEnabled(true);
        return protoType;
    }

    @Override
    public BaseCollectorRepository<Collector> getCollectorRepository() {
        return collectorRepository;
    }

    @Override
    public String getCron() {
        return gogsSettings.getCron();
    }

    /**
     * Clean up unused deployment collector items
     *
     * @param collector the {@link Collector}
     */
//    @SuppressWarnings("PMD.AvoidDeeplyNestedIfStmts") // agreed, fixme
//    private void clean(GogsCollector collector) {
//        Set<ObjectId> uniqueIDs = new HashSet<ObjectId>();
//        /**
//         * Logic: For each component, retrieve the collector item list of the type SCM. Store their IDs in a unique set
//         * ONLY if their collector IDs match with Gogs collectors ID.
//         */
//        for (com.capitalone.dashboard.model.Component comp : dbComponentRepository.findAll()) {
//            if (comp.getCollectorItems() != null && !comp.getCollectorItems().isEmpty()) {
//                List<CollectorItem> itemList = comp.getCollectorItems().get(CollectorType.SCM);
//                if (itemList != null) {
//                    for (CollectorItem ci : itemList) {
//                        if (ci != null && ci.getCollectorId().equals(collector.getId())) {
//                            uniqueIDs.add(ci.getId());
//                        }
//                    }
//                }
//            }
//        }
//
//        /**
//         * Logic: Get all the collector items from the collector_item collection for this collector. If their id is in
//         * the unique set (above), keep them enabled; else, disable them.
//         */
//        List<GogsCollectorItem> repoList = new ArrayList<>();
//        Set<ObjectId> gitID = new HashSet<>();
//        gitID.add(collector.getId());
//        for (GogsCollectorItem repo : gogsRepoRepository.findByCollectorIdIn(gitID)) {
//            if (repo != null) {
//                repo.setEnabled(uniqueIDs.contains(repo.getId()));
//                repoList.add(repo);
//            }
//        }
//        gogsRepoRepository.save(repoList);
//    }

    @Override
    public void collect(Collector collector) {

        logBanner("Starting...");
        long start = System.currentTimeMillis();
        int repoCount = 0;
        int commitCount = 0;

//        clean(collector);
        for (GogsRepo repo : enabledRepos(collector)) {
//            boolean firstRun = false;
//            if (repo.getLastUpdated() == 0) {
//                firstRun = true;
//            }
//            repo.setLastUpdated(System.currentTimeMillis());
//            repo.removeLastUpdateDate();  //moved last update date to collector item. This is to clean old data.
//            gogsRepoRepository.save(repo);
//            LOG.debug(repo.getOptions().toString() + "::" + repo.getBranch());
//            for (Commit commit : gogsClient.getCommits(repo, firstRun)) {
//                LOG.debug(commit.getTimestamp() + ":::" + commit.getScmCommitLog());
//                if (isNewCommit(repo, commit)) {
//                    commit.setCollectorItemId(repo.getId());
//                    commitRepository.save(commit);
//                    commitCount++;
//                }
//            }

            repoCount++;
        }
        log("Repo Count", start, repoCount);
        log("New Commits", start, commitCount);

        log("Finished", start);
    }

    private List<GogsRepo> enabledRepos(Collector collector) {
        return gogsRepoRepository.findEnabledGogsRepos(collector.getId());
    }

//    private boolean isNewCommit(GogsCollectorItem repo, Commit commit) {
//        return commitRepository.findByCollectorItemIdAndScmRevisionNumber(
//                repo.getId(), commit.getScmRevisionNumber()) == null;
//    }

}
