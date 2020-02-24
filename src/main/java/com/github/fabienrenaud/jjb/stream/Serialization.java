package com.github.fabienrenaud.jjb.stream;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.openjdk.jmh.annotations.Benchmark;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.fabienrenaud.jjb.JsonBench;
import com.github.fabienrenaud.jjb.JsonUtils;
import com.github.fabienrenaud.jjb.data.JsonSource;
import com.grack.nanojson.JsonAppendableWriter;
import com.owlike.genson.stream.ObjectWriter;

import io.github.senthilganeshs.parser.json.Generator;
import io.github.senthilganeshs.parser.json.Parser.Value;
import okio.BufferedSink;
import okio.Okio;

/** @author Fabien Renaud */
public class Serialization extends JsonBench {

  @Override
  public JsonSource JSON_SOURCE() {
    return CLI_JSON_SOURCE;
  }

  @Benchmark
  @Override
  public Object jackson() throws Exception {
    ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
    try (JsonGenerator jGenerator =
        JSON_SOURCE().provider().jacksonFactory().createGenerator(baos)) {
      JSON_SOURCE().streamSerializer().jackson(jGenerator, JSON_SOURCE().nextPojo());
    }
    return baos;
  }

  @Benchmark
  @Override
  public Object gson() throws Exception {
    ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
    Writer w = new OutputStreamWriter(baos);
    try (com.google.gson.stream.JsonWriter jw = new com.google.gson.stream.JsonWriter(w)) {
      JSON_SOURCE().streamSerializer().gson(jw, JSON_SOURCE().nextPojo());
    }
    w.close();
    return baos;
  }
}
