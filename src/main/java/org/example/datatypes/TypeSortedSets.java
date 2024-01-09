package org.example.datatypes;

import org.example.util.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.HashMap;
import java.util.Map;

public class TypeSortedSets extends Connection {
    private final JedisPool pool = getConnectionPool();
    private final Jedis jedis = pool.getResource();

    public void zAdd() {
        final String key = "exam_scores";
        var res1 = jedis.zadd("sorted_sets", 10.0, "Kaolin");
        System.out.println(res1);

        var res2 = jedis.zadd("sorted_sets", 9.0, "KZW");
        System.out.println(res2);

        var res3 = jedis.zadd(key, new HashMap<>(){{
            put("member1", 5.0);
            put("member2", 7.0);
            put("member3", 2.0);
            put("member4", 9.0);
            put("member5", 2.5);
        }});
        System.out.println(res3);
    }

    public void getSortedSets() {
        final String key = "exam_scores";

        var res1 = jedis.zrange(key, 0, -1);
        System.out.println("Get member order by asc => " + res1);

        var res2 = jedis.zrevrange(key, 0, -1);
        System.out.println("Get members order by desc => " + res2);

        var res3 = jedis.zrangeWithScores(key, 0, -1);
        System.out.println("Get with score order by asc => " + res3);

        var res4 = jedis.zrevrangeWithScores(key, 0, -1);
        System.out.println("Get with score order by desc => " + res4);

        // Get with 5 or fewer points
        // -inf = negative infinity
        var res5 = jedis.zrangeByScore(key, "-inf", "5");
        System.out.println("Range By Score => " + res5);

        // Get with 5 or more points
        // +inf = positive infinity
        var res6 = jedis.zrangeByScore(key, "5", "+inf");
        System.out.println("Range By Score => " + res6);
    }

    public void getScoreByMember() {
        final String key = "exam_scores";

        var res1 = jedis.zrank(key, "member1");
        System.out.println("Member1's score => " + res1);

        var res2 = jedis.zrank(key, "member2");
        System.out.println("Member2's score => " + res2);
    }

    public void lexicographicalScore() {
        final String key = "lex_member";

        var res1 = jedis.zadd(key,new HashMap<>(){{
            put("Zebra", 0.0);
            put("Aerial", 0.0);
            put("Eating", 0.0);
            put("Timmy", 0.0);
            put("nani", 0.0);
            put("BreakingBrainWash", 0.0);
        }});
        System.out.println("Add => " + res1);

        var res2 = jedis.zrange(key, 0, -1);
        System.out.println(key + " => " + res2);

        var res3 = jedis.zrangeByLex(key, "[A", "[n");
        System.out.println("Get by Lex => " + res3);
    }

    public void updateScore() {
        final String key = "lex_member";

        var res1 = jedis.zincrby(key, 5.0, "Eating");
        System.out.println("Increase 5.0 to Eating => " + res1);

        var res2 = jedis.zincrby(key, 3.0, "Aerial");
        System.out.println("Increase 3.0 to Aerial => " + res2);

        Tuple tuple = new Tuple("Test", 10.0);
        System.out.println("Test Tuple => " + tuple);
    }
}
