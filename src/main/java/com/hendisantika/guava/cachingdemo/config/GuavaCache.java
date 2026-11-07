package com.hendisantika.guava.cachingdemo.config;

import com.google.common.cache.Cache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.util.Assert;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-guava-caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 08/11/26
 * Time: 06.01
 * <p>
 * Spring {@link org.springframework.cache.Cache} adapter backed by a Guava {@link Cache}.
 * Spring Framework removed its own {@code GuavaCache} in 5.0, so this demo keeps a small
 * local implementation.
 */
public class GuavaCache extends AbstractValueAdaptingCache {

    private final String name;

    private final Cache<Object, Object> cache;

    public GuavaCache(String name, Cache<Object, Object> cache) {
        this(name, cache, true);
    }

    public GuavaCache(String name, Cache<Object, Object> cache, boolean allowNullValues) {
        super(allowNullValues);
        Assert.notNull(name, "Name must not be null");
        Assert.notNull(cache, "Cache must not be null");
        this.name = name;
        this.cache = cache;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Cache<Object, Object> getNativeCache() {
        return this.cache;
    }

    @Override
    protected Object lookup(Object key) {
        return this.cache.getIfPresent(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(Object key, Callable<T> valueLoader) {
        try {
            return (T) fromStoreValue(this.cache.get(key, () -> toStoreValue(valueLoader.call())));
        } catch (ExecutionException | UncheckedExecutionException ex) {
            throw new ValueRetrievalException(key, valueLoader, ex.getCause());
        }
    }

    @Override
    public void put(Object key, Object value) {
        this.cache.put(key, toStoreValue(value));
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        Object existing = this.cache.asMap().putIfAbsent(key, toStoreValue(value));
        return toValueWrapper(existing);
    }

    @Override
    public void evict(Object key) {
        this.cache.invalidate(key);
    }

    @Override
    public boolean evictIfPresent(Object key) {
        return this.cache.asMap().remove(key) != null;
    }

    @Override
    public void clear() {
        this.cache.invalidateAll();
    }

    @Override
    public boolean invalidate() {
        boolean notEmpty = !this.cache.asMap().isEmpty();
        this.cache.invalidateAll();
        return notEmpty;
    }
}
