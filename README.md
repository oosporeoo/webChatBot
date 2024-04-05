# webChatBot
这是一个简单的智能聊天机器人项目，使用Spring Boot和纯文本文件（`replies.txt`）来处理用户输入并提供相应的回复。它支持全词匹配、正则表达式匹配以及动态方法调用，以实现灵活的对话逻辑。

## 功能特点

- **全词匹配和正则表达式支持**：根据用户输入的内容，通过预定义的规则（全词或正则表达式）来匹配并回复。
- **动态方法调用**：对于特定的输入，支持调用后端的Java方法来生成回复，允许执行更复杂的逻辑。
- **前端交互界面**：提供了一个简单的HTML页面，通过AJAX与后端通信，实现实时聊天体验。

## 技术栈

- 后端：Spring Boot
- 前端：HTML + CSS + JavaScript
- 数据存储：文本文件（`replies.txt`）

## 项目结构

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── chatbot/
│   │               ├── controller/   # 控制器层
│   │               ├── service/      # 服务层
│   │               └── ChatbotApplication.java  # Spring Boot应用主类
│   └── resources/
│       ├── replies.txt  # 存储对话规则的文本文件
│       └── templates/
│           └── index.html  # 聊天界面的前端页面
└── pom.xml  # Maven项目文件
```

## 如何运行
克隆仓库到本地：
```bash
git clone https://github.com/oosporeoo/webChatBot
```

在项目根目录下，使用Maven编译并运行项目：
```bash
mvn spring-boot:run
```

打开浏览器访问：http://localhost:8080，开始与聊天机器人交互。
