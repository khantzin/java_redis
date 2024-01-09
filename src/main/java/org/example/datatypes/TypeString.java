package org.example.datatypes;

import org.example.util.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.List;

/***
 * To Test resdis data type - STRING
 */
public class TypeString extends Connection {

    private final JedisPool pool =  getConnectionPool();
    private final Jedis jedis = pool.getResource();

    // Value can't bigger than 512 MB
    // SET will replace the value if key is already exists
    /***
     *  @CLI-Command
     *  @SET - SET key value
     *  @GET - GET key
     *
      ***/
    public void setAndGet() {
        var res1 = jedis.set("bike:1", "Deimos");
        System.out.println("Set And Get");
        System.out.println("-------------------------------------------------");
        System.out.println(res1);

        var res2 = jedis.get("bike:1");
        System.out.println(res2);


        System.out.println("\n\n");
    }

    /***
     * @OPTIONS - nx and xx
     * @nx - return nil if key is already exist
     * @xx - replace value if key is already exist but key is not already exists return nil
     *
     * @CLI-Command
     * @SET - SET key value option
     *
     ***/

    public void setWithOptions() {
        // return 0 if key already exists
        var res1 = jedis.setnx("bike:1", "bike");
        System.out.println("Set With Options");
        System.out.println("-------------------------------------------------");
        System.out.println("Res1 => " + res1);
        System.out.println("Value from bike:1 => " + jedis.get("bike:1"));

        // return 0 if key not exists else replace value
        var res2 = jedis.set("bike:1", "bike", SetParams.setParams().xx());
        System.out.println("Res2 => " + res2 );
        System.out.println("Value from bike:1 => " + jedis.get("bike:1"));

        System.out.println("\n\n");

    }

    public void setWithExpire() throws Exception {
        System.out.println("Set with Expire after 6 seconds");
        System.out.println("-------------------------------------------------");
        var res1 = jedis.setex("bike:ex", 6, "Bike with Expire");
        System.out.println("Res1 => " + res1);

        Thread.sleep(3000);
        System.out.println("Get after 3s => " + jedis.get("bike:ex"));

        Thread.sleep(4000);
        System.out.println("Get after 7s => " + jedis.get("bike:ex"));


    }

    public void getSet() {
        System.out.println("GETSET");
        System.out.println("-------------------------------------------------");
        var res1 = jedis.set("bike:2", "Road bicycle");
        System.out.println("Res1 " + res1);

        res1 = jedis.getSet("bike:2", "Mountain bike");
        System.out.println("Old value of bike:2 => " + res1);
        System.out.println("New Value of bike:2 => " + jedis.get("bike:2"));
    }

    public void mSetAndMGet() {
        System.out.println("MSET and MGET");
        System.out.println("-------------------------------------------------");

        var res1 = jedis.mset("bike:1", "Deimos", "bike:2", "Ares", "bike:3", "Vanth");
        System.out.println("Res1 => " + res1);

        List<String> res2 = jedis.mget("bike:1", "bike:2", "bike:3");
        System.out.println("Res2 => " + res2);
    }

    public void stringAsCounter() {
        System.out.println("String as Counter");
        System.out.println("-------------------------------------------------");

        jedis.set("total_num", "0");
        var res1 = jedis.incr("total_num");
        System.out.println("Incr Method => " + res1);

        var res2 = jedis.incrBy("total_num", 10);
        System.out.println("incrBy(key, incrNum) => " + res2);
    }

    public void delete() {
        System.out.println("Delete by Key");
        System.out.println("-------------------------------------------------");

        var res1 = jedis.del("mykey");
        System.out.println(res1);
    }
}
