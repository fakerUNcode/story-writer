package com.easylive.utils;

import com.easylive.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessUtils {
    private static final Logger logger = LoggerFactory.getLogger(ProcessUtils.class);

    /**
     * 执行命令行并防止缓冲区死锁 (现代 ProcessBuilder 方案)
     */
    public static String executeCommand(String cmd, Boolean showLog) throws BusinessException {
        if (cmd == null || cmd.trim().isEmpty()) {
            return null;
        }

        if (showLog) {
            logger.info("准备执行命令: {}", cmd);
        }

        StringBuilder result = new StringBuilder();
        Process process = null;

        try {
            // 1. 兼容多平台的命令构建
            String[] command;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                command = new String[]{"cmd.exe", "/c", cmd};
            } else {
                command = new String[]{"/bin/sh", "-c", cmd};
            }

            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // 2. 【核心修复】合并标准错误流和标准输出流
            // 这行代码直接取代了你原本需要写两个 Thread 来分别读取流的复杂操作，彻底杜绝死锁
            processBuilder.redirectErrorStream(true);

            // 3. 启动进程
            process = processBuilder.start();

            // 4. 单线程实时读取合并后的流（抽干缓冲区）
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (showLog) {
                        logger.info(line); // 实时打印 FFmpeg 进度，不再是一团黑盒
                    }
                    result.append(line).append("\n");
                }
            }

            // 5. 等待进程执行完毕，拿取退出码
            int exitCode = process.waitFor();
            if (showLog) {
                logger.info("命令执行完毕，退出码: {}", exitCode);
            }

            return result.toString();

        } catch (Exception e) {
            logger.error("执行命令失败 cmd: {}, 异常: {}", cmd, e.getMessage());
            throw new BusinessException("视频转换失败");
        } finally {
            // 6. 安全清理进程，无需使用存在严重内存泄漏风险的 ShutdownHook
            if (process != null) {
                process.destroy();
            }
        }
    }
}