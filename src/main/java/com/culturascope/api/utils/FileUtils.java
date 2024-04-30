package com.culturascope.api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtils {

    public static String generateFileName(String originalFileName) {
        // Get the current timestamp
        LocalDateTime now = LocalDateTime.now();

        // Format the timestamp as desired
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timestamp = now.format(formatter);

        // Extract the file extension from the original file name
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // Generate the new file name using the timestamp and original file extension
        String newFileName = timestamp + fileExtension;

        return newFileName;
    }
}
