package com.example.chatbot.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class ChatService {

    private final Map<Pattern, String> replies = new LinkedHashMap<>();
    private final ExtendMethodService extendMethodService = new ExtendMethodService(); // ExtendMethod的实例

    @PostConstruct
    public void init() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new ClassPathResource("replies.txt").getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+", 2);
                if (parts.length == 2) {
                    Pattern pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);
                    replies.put(pattern, parts[1]);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getReply(String message) {
        for (Map.Entry<Pattern, String> entry : replies.entrySet()) {
            Matcher matcher = entry.getKey().matcher(message);
            if (matcher.find()) {
                try {

                    if (entry.getValue().endsWith("()")) {
                        String methodName = entry.getValue().replace("()", "");
                        Method method = ExtendMethodService.class.getDeclaredMethod(methodName, String.class);

                        // 调用方法，传入完整的用户输入
                        return (String) method.invoke(extendMethodService, message);
                    } else {
                        // 直接返回配置的静态回复
                        return entry.getValue();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "对不起，处理时发生错误。";
                }
            }
        }
        return "对不起，我不明白您的意思。";
    }



}
