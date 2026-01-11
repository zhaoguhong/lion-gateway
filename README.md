# lion-gateway

## 背景
API 网关做为微服务架构的守护门神，重要性不言而喻。

项目取名 lion，就像中国很多住宅门前的守护神石狮子一样，守护我们的服务。

相比其他开源网关：
- apisix, kong 基于 nginx，性能出众，但 c/lua 开发门槛高
- SpringCloud Gateway, Zuul2, Shenyu 存在设计缺陷（循环匹配、重复解析等）

本项目基于 Netty + JDK 21 虚拟线程，追求极致性能的同时保持代码简洁。

## 技术栈

- **JDK 21** - 虚拟线程实现百万级并发
- **Spring Boot 3.2** - 企业级框架
- **Netty 4.1** - 高性能网络框架

## 核心特性

### ⚡ 虚拟线程
使用 JDK 21 虚拟线程，无需复杂的响应式编程：
- 百万级并发能力
- 简单的同步代码
- 更好的调试体验
- 零响应式依赖

### 🎯 规则引擎
对于等值条件优先采用哈希匹配，匹配不到退回循环匹配。

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
java -jar lion-gateway-server/target/lion-gateway-server-1.0-SNAPSHOT.jar
```

服务启动后监听 9999 端口。

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

## 升级说明

项目已从 JDK 8 + Reactor 升级到 JDK 21 + 虚拟线程：

### 主要改动
- 移除 Reactor 依赖（reactor-netty, reactor-core）
- 改用 Java 原生 HttpClient + 虚拟线程
- Spring Boot 2.3.6 → 3.2.1
- 所有依赖升级到最新版本

### 收益
| 对比项 | Reactor | 虚拟线程 |
|--------|---------|----------|
| 并发能力 | 百万级 | 百万级+ |
| 代码复杂度 | 高 | 低 |
| 调试体验 | 困难 | 简单 |
| 学习成本 | 高 | 低 |

## 配置

### application.yml

```yaml
spring:
  threads:
    virtual:
      enabled: true  # 启用虚拟线程

lion:
  plugins:
    waf:
      enabled: true
      rules:
        - name: headerRule
          matchMode: and
          conditions:
            - header,aaa|=|bbb
    http:
      enabled: true
      rules:
        - name: uriRule
          matchMode: and
          conditions:
            - header,aaa|!=|bbb
```

## 注意事项

### 虚拟线程最佳实践

✅ **推荐**
- 直接编写同步阻塞代码
- 使用 ReentrantLock
- 大量并发 I/O 操作

❌ **避免**
- `synchronized`（会固定到平台线程，改用 `ReentrantLock`）
- 池化虚拟线程（没必要）
- CPU 密集型任务长时间占用

### 依赖说明

如果代码中使用了 commons-collections，需要更新包名：
```java
// 旧的
import org.apache.commons.collections.xxx;

// 新的
import org.apache.commons.collections4.xxx;
```

## License

详见 LICENSE 文件。
