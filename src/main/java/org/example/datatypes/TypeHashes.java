package org.example.datatypes;

import org.example.TestData;
import org.example.util.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * - Hashes are record types structured as collections of field-value pairs.
 * <p>
 * - Can use hashes to represent basic objects and to store groupings of counters, among other things
 * </p>
 * <p>- Hash can store up to {@code 4,294,967,295} key-value pairs </p>
 * <p>- In practice, hashes are only limited by {@code RAM} on your host </p>
 */
public class TypeHashes extends Connection {

    private final JedisPool pool = getConnectionPool();
    private final Jedis jedis = pool.getResource();

    /***
     * Basic {@code HSET} and {@code HGET}
     */
    public void hsetAndhget() {
        Map<String, String> computer1 = new HashMap<>();
        computer1.put("model", "Thinkpad");
        computer1.put("brand", "Lenovo");
        computer1.put("price", "699");

        var res1 = jedis.hset("computer:1", computer1);
        System.out.println(res1);

        var res2 = jedis.hget("computer:1", "model");
        System.out.println(res2);

        var res3 = jedis.hget("computer:1", "price");
        System.out.println(res3);

        var res4 = jedis.hgetAll("computer:1");
        System.out.println(res4);
    }

    /***
     * {@code HSET} sets multiple fields of the hash
     * While {@code HGET} retrieves a single field, {@code HMGET} is similar to {@code HGET} but returns an array of values
     */
    public void hmget() {
        List<String> res1 = jedis.hmget("computer:1", "model", "price");
        System.out.println(res1);
    }

    /***
     * Integer increase by {@code HINCRBY}
     * Float increase by {@code HINCRBYFLOAT}
     */
    public void increaseBy() {
        Map<String, String> computer = new HashMap<>();
        computer.put("model", "Thinkpad");
        computer.put("brand", "Lenovo");
        computer.put("price", "699.9");
        computer.put("qty", "1");

        var res1 = jedis.hset("computer:2", computer);
        System.out.println(res1);

        var res2 = jedis.hincrBy("computer:2", "qty", 1);
        System.out.println(res2);

        var res3 = jedis.hincrByFloat("computer:2", "price", 1);
        System.out.println(res3);

        var res4 = jedis.hgetAll("computer:2");
        System.out.println(res4);
    }
}
