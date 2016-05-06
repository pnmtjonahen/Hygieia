package com.capitalone.dashboard.collector;

import com.capitalone.dashboard.model.Commit;
import com.capitalone.dashboard.model.GogsRepo;

import java.util.List;

/**
 * Client for fetching commit history from Gogs
 */
public interface GogsClient {

    /**
     * Fetch all of the commits for the provided GogsCollectorItem.
     *
     * @param repo SubversionRepo
     * @param firstRun
     * @return all commits in repo
     */

	List<Commit> getCommits(GogsRepo repo, boolean firstRun);

}
