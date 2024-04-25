package com.inframarket.plugins.utils;

import org.apache.commons.io.IOUtils;
import java.io.IOException;

public class FileUtils {
    public static String getFileContents(String filePath) throws IOException {
        return IOUtils.toString(FileUtils.class.getResourceAsStream(filePath), "UTF-8");
    }
}
