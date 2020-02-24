package com.github.fabienrenaud.jjb.databind;

import com.alibaba.fastjson.JSON;
import com.bluelinelabs.logansquare.LoganSquare;
import com.github.fabienrenaud.jjb.JsonBench;
import com.github.fabienrenaud.jjb.data.JsonSource;
import com.google.gson.JsonSyntaxException;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;


/**
 * @author Fabien Renaud
 */
public class Deserialization extends JsonBench {

    @Override
    public JsonSource JSON_SOURCE() {
        return CLI_JSON_SOURCE;
    }

    @Benchmark
    @Override
    public Object gson() throws Exception {
        return JSON_SOURCE().provider().gson().fromJson(JSON_SOURCE().nextReader(), JSON_SOURCE().pojoType());
    }

    @Benchmark
    @Override
    public Object jackson() throws Exception {
        return JSON_SOURCE().provider().jackson().readValue(JSON_SOURCE().nextByteArray(), JSON_SOURCE().pojoType());
    }

    @Benchmark
    @Override
    public Object jackson_afterburner() throws IOException {
        return JSON_SOURCE().provider().jacksonAfterburner().readValue(JSON_SOURCE().nextByteArray(), JSON_SOURCE().pojoType());
    }


    @Benchmark
    @Override
    public Object fastjson() {
        return JSON.parseObject(JSON_SOURCE().nextByteArray(), JSON_SOURCE().pojoType());
    }

}
