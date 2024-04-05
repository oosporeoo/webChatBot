package com.example.chatbot.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtendMethodService {

    private final APIRequestService apiRequestService = new APIRequestService();

    //当前时间
    public String getTime(String str) {
        return "小主，当前时间为：" + java.time.LocalTime.now().toString();
    }

    // 当前日期
    public String getCurrentDate(String str) {
        // 使用默认格式（例如：2022-01-28）格式化日期
        LocalDate today = LocalDate.now();
        return "小主，当前日期为： " + today.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    // 今天吃什么
    public String getEat(String str) {

        String[] vegetables = {"芹菜", "白菜", "菜花","茄子","香菇","竹笋","酸菜","萝卜","土豆","西红柿","青瓜","南瓜","青椒","草莓","西瓜","香蕉","板蓝根","苹果"};
        String[] cookingMethods = {"炸", "蒸", "焖", "炒", "煮","炖","煎","拌"};
        String[] meats = {"鸡蛋", "猪肉", "牛肉","肉","鸡肉","鱼","饭","面","鸽子","田螺","花甲","鱿鱼","火腿","鸡腿","肉丝","瘦肉"};

        Random random = new Random();
        // 随机选择蔬菜、烹饪手法和荤菜
        int vegetableIndex = random.nextInt(vegetables.length);
        int cookingMethodIndex = random.nextInt(cookingMethods.length);
        int meatIndex = random.nextInt(meats.length);
        // 打印拼接的字符串
        String eat = vegetables[vegetableIndex] + cookingMethods[cookingMethodIndex] + meats[meatIndex];
        System.out.println(eat);

        return "今天建议小主吃："+eat;
    }

    //摇色子
    public String getTozi(String str) {
        Random random = new Random();
        // 生成1到6之间的随机数
        int result = 1 + random.nextInt(6);
        return "你摇到了 " + result;
    }

    //API获取每日一言（访问较慢）
    public String getYiyan(String str) {
        String apiUrl = "https://v.api.aa1.cn/api/yiyan/index.php";
        return APIRequestService.getURLrequest(apiUrl);
    }

    //参数输入调用
    public String getExpressStatus(String input) {
        // 在这里解析输入字符串以提取快递号
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String expressNumber = matcher.group(1); // 提取快递号
            // 根据快递号执行相应的逻辑
            return "快递号 " + expressNumber + " 的状态是：已送达";
        } else {
            return "无法识别的快递号。例子：查快递123456";
        }
    }

    public String getBMI(String str) {
        String regex = "(\\d+)\\s(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        double height = 0; // 身高（cm）
        double weight = 0; // 体重（kg）

        if (matcher.find()) {
            height = Double.parseDouble(matcher.group(1));
            weight = Double.parseDouble(matcher.group(2));
        } else {
            System.out.println("输入的格式不正确");
            return "输入格式不正确！ 例子: 身体指数175 70"; // 输入格式不正确，返回一个特殊值表示错误
        }

        // 计算BMI
        double heightMeter = height / 100; // 转换身高为米
        double bmi = weight / (heightMeter * heightMeter);

        // 保留两位小数
        bmi = Math.round(bmi * 100.0) / 100.0;

        return "您的身体指数为：" + bmi;
    }


}
