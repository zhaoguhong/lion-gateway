# Lion Gateway

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)

**Lion Gateway** 是一个轻量级、高性能的 API 网关，取名 "Lion（狮子）"，寓意像中国住宅门前的守护神石狮子一样守护后端服务。

## 背景

API 网关作为微服务架构的守护门神，重要性不言而喻。

相比其他开源网关：
- **APISIX/Kong** - 基于 Nginx，性能出众，但 C/Lua 开发门槛高
- **SpringCloud Gateway/Zuul2/Shenyu** - 存在设计缺陷（循环匹配、重复解析等）

本项目基于 **Netty + JDK 21 虚拟线程**，追求极致性能的同时保持代码简洁。

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| JDK | 21+ | 虚拟线程支持 |
| Spring Boot | 3.2.1 | 企业级应用框架 |
| Netty | 4.1.104.Final | 高性能网络框架 |
| Maven | 3.6+ | 项目构建工具 |

## 核心特性

### ⚡ 虚拟线程并发
使用 JDK 21 虚拟线程，无需复杂的响应式编程：
- 百万级并发能力
- 简单的同步代码风格
- 更好的调试体验
- 零响应式依赖

### 🔌 插件化架构
- 责任链模式处理请求
- 可插拔的插件机制
- 内置 WAF（Web 应用防火墙）、HTTP 转发插件

### 🎯 灵活规则引擎
- 支持 AND/OR/NONE 匹配模式
- 13 种断言操作（=, !=, >, <, >=, <=, contains, notContains, startsWith, endsWith, regex, in, empty, notEmpty）
- 支持多种数据源（Header、URI、Cookie 等）
- 等值条件优先哈希匹配，性能优化

## 环境要求

- JDK 21+
- Maven 3.6+

## 快速开始

### 安装 JDK 21

```bash
# macOS
brew install openjdk@21

# 或使用 SDKMAN
sdk install java 21.0.1-open
```

### 编译运行

```bash
# 克隆项目
git clone <repository-url>
cd lion-gateway

# 编译
mvn clean compile

# 打包
mvn package

# 运行
java -jar lion-gateway-bootstrap/target/lion-gateway-bootstrap-1.0-SNAPSHOT.jar
```

服务启动后监听 **9999** 端口。

### 测试服务

```bash
curl http://localhost:9999/test
```

## 项目结构

```
lion-gateway/
├── doc/                          # 完整文档目录
├── lion-gateway-core/            # 核心功能模块
│   ├── src/main/java/com/zhaoguhong/lion/gateway/
│   │   ├── core/                 # 核心上下文
│   │   ├── config/               # 配置管理
│   │   ├── netty/                # Netty 网络层
│   │   ├── plugin/               # 插件系统
│   │   └── rule/                 # 规则引擎
│   └── pom.xml
├── lion-gateway-bootstrap/       # 启动模块
│   ├── src/main/java/com/zhaoguhong/lion/gateway/
│   │   └── LionGatewayApplication.java
│   └── pom.xml
└── pom.xml
```

## 文档导航

完整文档请查看 [`doc/`](doc/README.md) 目录：

- [项目介绍](doc/01-项目介绍.md) - 项目概述、特性、技术栈
- [架构设计](doc/02-架构设计.md) - 系统架构、核心模块、设计模式
- [快速开始](doc/03-快速开始.md) - 环境搭建、运行指南
- [插件开发](doc/04-插件开发.md) - 如何开发自定义插件
- [配置说明](doc/05-配置说明.md) - 配置文件详解

## 虚拟线程使用

### 方式 1：直接使用

```java
import java.util.concurrent.Executors;

// 虚拟线程的 HttpClient
HttpClient httpClient = HttpClient.newBuilder()
    .executor(Executors.newVirtualThreadPerTaskExecutor())
    .build();

// 同步调用，不会阻塞
String response = httpClient.send(request, 
    HttpResponse.BodyHandlers.ofString()).body();
```

### 方式 2：@Async 注解

```java
@Service
public class YourService {
    @Async  // 自动在虚拟线程中执行
    public void asyncMethod() {
        // 阻塞操作完全 OK
    }
}
```

### 方式 3：注入执行器

```java
@Component
public class YourHandler {
    @Resource(name = "virtualThreadExecutor")
    private ExecutorService executor;
    
    public void process() {
        executor.submit(() -> {
            // 在虚拟线程中运行
        });
    }
}
```

## 虚拟线程最佳实践

✅ **推荐**
- 直接编写同步阻塞代码
- 使用 `ReentrantLock` 替代 `synchronized`
- 大量并发 I/O 操作

❌ **避免**
- 使用 `synchronized`（会固定到平台线程）
- 池化虚拟线程（没必要）
- CPU 密集型任务长时间占用

## 为什么选择虚拟线程？

项目已从 JDK 8 + Reactor 升级到 JDK 21 + 虚拟线程：

| 对比项 | Reactor | 虚拟线程 |
|--------|---------|----------|
| 并发能力 | 百万级 | 百万级+ |
| 代码复杂度 | 高 | 低 |
| 调试体验 | 困难 | 简单 |
| 学习成本 | 高 | 低 |
| 堆栈跟踪 | 复杂 | 清晰 |

## 配置示例

### application.yml

```yaml
spring:
  application:
    name: lion-gateway
  threads:
    virtual:
      enabled: true  # 启用虚拟线程

lion:
  plugins:
    waf:
      enabled: true
      rules:
        - name: blockMalicious
          matchMode: or
          conditions:
            - header,User-Agent|contains|bad-bot
            - uri,/admin|=|/admin
    http:
      enabled: true
      rules:
        - name: forwardApi
          matchMode: and
          conditions:
            - header,X-Forward|=|true
```

## 版本历史

### v1.0 (当前)
- JDK 21 + 虚拟线程
- Spring Boot 3.2.1
- Netty 4.1
- 插件化架构
- 规则引擎

### v0.1
- JDK 8 + Reactor
- Spring Boot 2.3.6
- 响应式编程模型

## License

详见 [LICENSE](LICENSE) 文件。
