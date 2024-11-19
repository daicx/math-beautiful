package com.skuu.ffmpeg;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 视频加弹幕
 *
 * @author dcx
 * @since 2024-11-16 00:38
 **/
public class Util {
//    String inputFile = "/Users/dcx/Downloads/迅雷/4.ass"; // 输入的字幕文件路径
//    String outputFile = "41.ass";

    // 调整字幕时间戳的增量（以秒为单位）
    private static final int TIME_INCREMENT = 10;

    public static void main(String[] args) {
        String inputFile = "/Users/dcx/Downloads/迅雷/4.ass"; // 输入的字幕文件路径
        String outputFile = "/Users/dcx/Downloads/迅雷/43.ass"; // 输出的调整后字幕文件路径

        try {
            // 读取字幕文件
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String line;
            int[] start = new int[]{0,0,-10,0};
            int[] end = new int[]{0,0,10,0};
            while ((line = reader.readLine()) != null) {
                // 如果是字幕行，调整时间戳
                if (line.startsWith("Dialogue:")) {
                    // 使用正则表达式匹配时间戳部分
                    String pattern = "(\\d{2}:\\d{2}:\\d{2}\\.\\d{2}),(\\d{2}:\\d{2}:\\d{2}\\.\\d{2})"; // 修正正则表达式
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(line);

                    if (m.find()) {
                        String startTime = m.group(1);  // 捕获开始时间
                        String endTime = m.group(2);    // 捕获结束时间

                        // 解析时间戳
//                        int[] start = parseTime(startTime);
//                        int[] end = parseTime(endTime);

                        // 调整时间戳（每10秒）
                        start = addTime(start, TIME_INCREMENT);
                        end = addTime(start, TIME_INCREMENT);

                        // 格式化新的时间戳
                        String newStartTime = formatTime(start);
                        String newEndTime = formatTime(end);

                        // 替换旧的时间戳为新的时间戳
                        line = line.replace(startTime + "," + endTime, newStartTime + "," + newEndTime);
                    }
                    String pattern1 = "(\\d{3}:\\d{2}:\\d{2}\\.\\d{2}),(\\d{3}:\\d{2}:\\d{2}\\.\\d{2})"; // 修正正则表达式
                    Pattern r1 = Pattern.compile(pattern1);
                    Matcher m1 = r1.matcher(line);
                    if (m1.find()) {
                        String startTime = m1.group(1);  // 捕获开始时间
                        String endTime = m1.group(2);    // 捕获结束时间
                        System.out.println(line);
                        // 解析时间戳
//                        int[] start = parseTime(startTime);
//                        int[] end = parseTime(endTime);

                        // 调整时间戳（每10秒）
                        start = addTime(start, TIME_INCREMENT);
                        end = addTime(start, TIME_INCREMENT);

                        // 格式化新的时间戳
                        String newStartTime = formatTime(start);
                        String newEndTime = formatTime(end);

                        // 替换旧的时间戳为新的时间戳
                        line = line.replace(startTime + "," + endTime, newStartTime + "," + newEndTime);
                        System.out.println(line);
                    }
                }

                // 写入修改后的行
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();
            System.out.println("字幕时间戳调整完成，输出文件：" + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将时间字符串解析为时间数组 [小时, 分钟, 秒, 毫秒]
    private static int[] parseTime(String timeStr) {
        String[] parts = timeStr.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        String[] secondsParts = parts[2].split("\\.");
        int seconds = Integer.parseInt(secondsParts[0]);
        int milliseconds = Integer.parseInt(secondsParts[1]);
        return new int[]{hours, minutes, seconds, milliseconds};
    }

    // 将时间数组转换为时间字符串
    private static String formatTime(int[] time) {
        return String.format("%02d:%02d:%02d.%02d", time[0], time[1], time[2], time[3]);
    }

    // 给时间添加秒数（支持负数）
    private static int[] addTime(int[] time, int secondsToAdd) {
        int totalSeconds = time[2] + time[1] * 60 + time[0] * 3600;
        totalSeconds += secondsToAdd;

        int newHours = totalSeconds / 3600;
        int remainingSeconds = totalSeconds % 3600;
        int newMinutes = remainingSeconds / 60;
        int newSeconds = remainingSeconds % 60;

        return new int[]{newHours, newMinutes, newSeconds, time[3]};
    }

}
