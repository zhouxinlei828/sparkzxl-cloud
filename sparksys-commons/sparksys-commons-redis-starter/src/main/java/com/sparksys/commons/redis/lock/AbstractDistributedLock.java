package com.sparksys.commons.redis.lock;

/*
 * description：
 *
 * @author zhouxinlei
 * @date  2020/6/17 0017
 */
public abstract class AbstractDistributedLock implements DistributedLock {

    public boolean lock(String key) {
        return this.lock(key, WAIT_TIME, SLEEP_MILLIS);
    }

    public boolean lock(String key, long leaseTime) {
        return this.lock(key, WAIT_TIME, leaseTime);
    }

    public boolean lock(String key, int waitTime) {
        return this.lock(key, waitTime, SLEEP_MILLIS);
    }
}
