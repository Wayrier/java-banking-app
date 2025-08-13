package com.wayrier.bank.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wayrier.bank.model.Account;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

public class JsonStore {
    // Gson with an adapter for java.time.Instant
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new TypeAdapter<Instant>() {
                @Override public void write(JsonWriter out, Instant value) throws IOException {
                    if (value == null) { out.nullValue(); return; }
                    out.value(value.toString()); // ISO-8601
                }
                @Override public Instant read(JsonReader in) throws IOException {
                    String s = in.nextString();
                    return (s == null || s.equals("null")) ? null : Instant.parse(s);
                }
            })
            .create();

    public static void save(Collection<Account> accounts, String path) throws Exception {
        try (FileWriter w = new FileWriter(path)) {
            gson.toJson(accounts, w);
        }
    }
    public static Collection<Account> load(String path) throws Exception {
        Type type = new TypeToken<Collection<Account>>(){}.getType();
        try (FileReader r = new FileReader(path)) {
            Collection<Account> loaded = gson.fromJson(r, type);
            return loaded != null ? loaded : new ArrayList<>();
        }
    }
}
