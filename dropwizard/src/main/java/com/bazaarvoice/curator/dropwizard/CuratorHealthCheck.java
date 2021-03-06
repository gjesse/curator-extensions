package com.bazaarvoice.curator.dropwizard;

import com.codahale.metrics.health.HealthCheck;
import org.apache.curator.framework.CuratorFramework;

import static com.google.common.base.Preconditions.checkNotNull;

public class CuratorHealthCheck extends HealthCheck {
    private final CuratorFramework _curator;

    public CuratorHealthCheck(CuratorFramework curator) {
        _curator = checkNotNull(curator);
    }

    @Override
    protected Result check() throws Exception {
        boolean connected = _curator.getZookeeperClient().isConnected();

        String description = String.format("Connect String: %s, Connected: %s",
                _curator.getZookeeperClient().getCurrentConnectionString(),
                connected);

        return connected
                ? Result.healthy(description)
                : Result.unhealthy(description);
    }
}
