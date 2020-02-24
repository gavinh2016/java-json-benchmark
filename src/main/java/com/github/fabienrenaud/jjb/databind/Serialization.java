package com.github.fabienrenaud.jjb.databind;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bluelinelabs.logansquare.LoganSquare;
import com.github.fabienrenaud.jjb.JsonBench;
import com.github.fabienrenaud.jjb.JsonUtils;
import com.github.fabienrenaud.jjb.data.JsonSource;
import okio.BufferedSink;
import okio.Okio;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.ByteArrayOutputStream;

public class Serialization extends JsonBench {

    public JsonSource JSON_SOURCE() {
        return CLI_JSON_SOURCE;
    }

    @Benchmark
    @Override
    public Object gson() {
        StringBuilder b = JsonUtils.stringBuilder();
        JSON_SOURCE().provider().gson().toJson(JSON_SOURCE().nextPojo(), b);
        return b;
    }

    @Benchmark
    @Override
    public Object jackson() throws Exception {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        JSON_SOURCE().provider().jackson().writeValue(baos, JSON_SOURCE().nextPojo());
        return baos;
    }

    @Benchmark
    @Override
    public Object jackson_afterburner() throws Exception {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        JSON_SOURCE().provider().jacksonAfterburner().writeValue(baos, JSON_SOURCE().nextPojo());
        return baos;
    }


    @Benchmark
    @Override
    public Object fastjson() throws Exception {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        JSON.writeJSONString(baos, JSON_SOURCE().nextPojo(), SerializerFeature.EMPTY);
        return baos;
    }

}
