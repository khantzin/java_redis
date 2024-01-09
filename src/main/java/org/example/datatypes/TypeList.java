package org.example.datatypes;

import org.example.util.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.args.ListDirection;

import java.time.LocalTime;

/***
 * To Test redis data type - LIST
 */
public class TypeList extends Connection {
    private final JedisPool pool =  getConnectionPool();
    private final Jedis jedis = pool.getResource();

    /***
     * Tread a list like a queue (first in, first out)
     */
    public void pushAndPop() {
        String key = "bikes:repairs";
        var res1 = jedis.lpush(key, "bike:1");
        System.out.println(res1);
        var res2 = jedis.lpush(key, "bike:2");
        System.out.println(res2);
        var res3 = jedis.lpush(key, "bike:3");
        System.out.println(res3);

        var res4 = jedis.rpop(key);
        System.out.println(res4);
        var res5 = jedis.rpop(key);
        System.out.println(res5);
        var res6 = jedis.rpop(key);
        System.out.println(res6);

        // Check length of a list
        var res7 = jedis.llen(key);
        System.out.println(res7);
    }

    /***
     * Atomically pop an element from one list and push to another
     */
    public void popAndPushToAnother() {
        String key = "bikes:repairs";

        var res1 = jedis.lpush(key, "bike:1");
        System.out.println(res1);

        var res2 = jedis.lpush(key, "bike:2");
        System.out.println(res2);

        var res3 = jedis.lmove(key, "bikes:finished", ListDirection.LEFT, ListDirection.LEFT);
        System.out.println(res3);

        var res4 = jedis.lrange(key, 0, -1);
        System.out.println(res4);

        var res5 = jedis.lrange("bikes:finished", 0, -1);
        System.out.println(res5);
    }

    /***
     * To Limit the length of a list
     */
    public void limitTheLength() {
        var res1 = jedis.lpush("bikes:repairs", "bike:1", "bike:2", "bikes:3", "bikes:4", "bikes:5");
        System.out.println(res1);

        var res2 = jedis.ltrim("bikes:repairs", 0, 2);
        System.out.println(res2);

        var res3 = jedis.lrange("bikes:repairs", 0, -1);
        System.out.println(res3);
    }

    public void testListAddingSpeed() {
        String[] strs = {
                "bike1", "bike2", "bike3", "bike4", "bike5",
                "bike6", "bike7", "bike8", "bike9", "bike10"
        };
        System.out.println("Before 10 elements => " + LocalTime.now());
        var res1 = jedis.lpush("bikes:10elements", strs);
        System.out.println("After 10 elements => " + LocalTime.now());
        System.out.println(res1);


        var size = 1000000;
        String[] elements = new String[size];
        for(int i = 0; i < size; i++) {
            var counter = i + 1;
            elements[i] = "bike" + counter;
        }
        System.out.println("Before 1 millions elements => " + LocalTime.now());
        var res2 = jedis.lpush("bike:1million", elements);
        System.out.println("After 1 millions elements => " + LocalTime.now());
        System.out.println(res2);
    }

    public void extractRangesOfElements() {
        var res1 = jedis.rpush("bikes:repairs", "bike:1");
        System.out.println(res1);

        var res2 = jedis.rpush("bikes:repairs", "bike:2");
        System.out.println(res2);

        var res3 = jedis.rpush("bikes:repairs", "bike:important");
        System.out.println(res3);

        var res4 = jedis.lrange("bikes:repairs", 0, -1);
        System.out.println(res4);
    }

    public void blockingOperation() {
        var res1 = jedis.lpush("bikes:repairs", "bike:1", "bike:2");
        System.out.println("Res1 " +res1);

        var res2 = jedis.brpop(1, "bikes:repairs");
        System.out.println("Res2 " + res2);

        var res3 = jedis.brpop(1, "bikes:repairs");
        System.out.println("Res3 " + res3);

        var res4 = jedis.brpop(1, "bikes:repairs");
        System.out.println("Res4 " + res4);
    }
}
