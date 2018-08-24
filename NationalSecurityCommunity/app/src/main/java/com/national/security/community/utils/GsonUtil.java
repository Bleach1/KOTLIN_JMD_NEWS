package com.national.security.community.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 流解析提升速度
 * @author: ljn
 * @time: 2018/8/24
 */
public class GsonUtil<T> {

    public List<T> readJsonStream(String json) throws IOException {
        InputStream is = new ByteArrayInputStream(json.getBytes());
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
        List<T> messages = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            T message = gson.fromJson(reader, new TypeToken<T>() {
            }.getType());
            messages.add(message);
        }
        reader.endArray();
        reader.close();
        return messages;
    }
}
