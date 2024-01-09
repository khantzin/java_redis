package org.example.datatypes;

import org.example.util.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TypeSets extends Connection {
    private final JedisPool pool =  getConnectionPool();
    private final Jedis jedis = pool.getResource();

    public void setsAdd() {
        var res1 = jedis.sadd("bike:mm", "bike:1");
        System.out.println("Res1 " + res1);

        var res2 = jedis.sadd("bike:mm", "bike:1");
        System.out.println("Res2 " + res2);

        var res3 = jedis.sadd("bike:mm", "bike:2", "bike:3");
        System.out.println("Res3 " + res3);

        var res4 = jedis.sadd("bike:mm", "bike:1", "bike:4");
        System.out.println("Res4 " + res4);
    }

    public void getAllMembersOfASet() {
        var res1 = jedis.sadd("bike:mm", "bike:1", "bike:2", "bike:3");
        System.out.println("Res1 " + res1);

        var res2 = jedis.smembers("bike:mm");
        System.out.println(res2);
    }

    public void isMembers() {
        jedis.sadd("bike:mm", "bike:1", "bike:2", "bike:3");
        jedis.sadd("bike:us", "bike:1", "bike:4");

        var res1 = jedis.sismember("bike:us", "bike:1");
        System.out.println("Res1 " + res1);

        var res2 = jedis.sismember("bike:us", "bike:2");
        System.out.println("Res2 " + res2);
    }

    public void findSameValueIncludeInTwoSets() {
        jedis.sadd("bike:mm", "bike:1", "bike:2", "bike:3");
        jedis.sadd("bike:us", "bike:1", "bike:4");

        var res1 = jedis.sinter("bike:mm", "bike:us");
        System.out.println("Res1 " + res1);
    }

    public void getTotalMemberOfASet() {
        jedis.sadd("bike:mm", "bike:1", "bike:2", "bike:3");

        var res1 = jedis.scard("bike:mm");
        System.out.println("Res1 " + res1);
    }
}
