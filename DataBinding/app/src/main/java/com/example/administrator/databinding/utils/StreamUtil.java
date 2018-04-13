package com.example.administrator.databinding.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtil {

    @SuppressWarnings({"ResultOfMethodCallIgnored", "MismatchedQueryAndUpdateOfCollection"})
    public void test() {
        //Stream create 从 Collection 和数组
        List<String> strings = new ArrayList<>();
        Object[] o = new Object[];
        strings.stream();
        strings.parallelStream();
        Stream.of();
        Arrays.stream(o);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        }));
        Stream<String> lines = bufferedReader.lines();

        //静态工厂
        IntStream.range(0,10);
        //Files.walk();



    }
}
