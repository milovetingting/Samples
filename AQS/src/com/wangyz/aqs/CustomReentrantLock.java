package com.wangyz.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class CustomReentrantLock implements Lock {

    private Sync sync = new Sync();

    @Override
    public void lock() {
        System.out.println(Thread.currentThread().getName() + " ready get lock");
        sync.acquire(1);
        System.out.println(Thread.currentThread().getName() + " already got lock");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName() + " ready release lock");
        sync.release(1);
        System.out.println(Thread.currentThread().getName() + " already released lock");
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else if (getExclusiveOwnerThread() == Thread.currentThread()) {
                setState(getState() + 1);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalMonitorStateException();
            }
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setState(getState() - 1);
            if (getState() == 0) {
                setExclusiveOwnerThread(null);
            }
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() > 0;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }
}
