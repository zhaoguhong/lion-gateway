# lion-gateway

一个基于 **Netty + JDK 21 虚拟线程** 的高性能 API 网关，目标是：

- **性能足够高**：充分利用 Netty I/O 模型与虚拟线程并发能力
- **代码足够直观**：使用同步风格编程，降低维护与调试成本
- **扩展足够轻量**：通过插件链 + 规则引擎扩展网关能力

## 为什么做这个项目

在微服务架构中，网关是流量入口与第一道防线。

- APISIX / Kong（Nginx + Lua）性能非常强，但对 Java 团队来说二次开发门槛偏高
- 某些 Java 网关框架在匹配链路或请求处理上存在复杂度较高的问题

`lion-gateway` 希望在 Java 技术栈内提供一个“**更好懂、更好改、性能不妥协**”的实现。

## 技术栈

- **JDK 21**（虚拟线程）
- **Spring Boot 3.x**
- **Netty 4.1.x**
- **Maven**（构建）

## 核心设计

### 1) 虚拟线程执行模型

项目使用 JDK 21 虚拟线程执行阻塞型逻辑（如 HTTP 转发调用），做到：

- 保留同步代码可读性
- 避免复杂的响应式链式调用
- 在高并发 I/O 场景下获得优秀吞吐

### 2) 插件责任链（Plugin Chain）

请求进入后通过插件链按顺序处理，当前包含：

- `waf`：规则匹配与拦截
- `http`：目标请求转发

你可以按同样模式扩展更多插件（鉴权、限流、灰度等）。

### 3) 规则引擎

规则由 `matchMode + conditions` 组成：

- 等值匹配优先走高效匹配路径
- 未命中时退化为循环匹配

在兼顾灵活性的同时，尽量降低常见场景下的匹配开销。

## 项目结构

```text
lion-gateway/
├── lion-gateway-server/                 # 网关服务主模块
│   ├── src/main/java/.../config         # 配置模型与装配
│   ├── src/main/java/.../netty          # Netty 服务端与 Handler
│   ├── src/main/java/.../plugin         # 插件、匹配器、数据加载器
│   └── src/main/resources/application.yml
├── pom.xml
└── README.md
```

## 环境要求

- **JDK 21+**
- **Maven 3.6+**

## 快速开始

### 1. 获取代码

```bash
git clone <repository-url>
cd lion-gateway
```

### 2. 编译与测试

```bash
mvn clean test
```

### 3. 打包

```bash
mvn package
```

### 4. 启动

```bash
java -jar lion-gateway-server/target/lion-gateway-server-1.0-SNAPSHOT.jar
```

默认监听端口：`9999`。

## 配置示例

`lion-gateway-server/src/main/resources/application.yml`

```yaml
spring:
  threads:
    virtual:
      enabled: true

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

> `conditions` 当前格式为：`数据来源,字段|谓词|值`。

## 虚拟线程实践建议

### 推荐

- 使用同步代码组织 I/O 流程
- 在高并发请求下使用虚拟线程承载阻塞调用
- 显式关注超时、熔断、重试等稳定性策略

### 不推荐

- 大量使用 `synchronized`（可优先考虑 `ReentrantLock`）
- 对虚拟线程做线程池化（通常没有必要）
- 在虚拟线程中长时间执行 CPU 密集任务

## 升级背景（JDK 8/Reactor -> JDK 21/Virtual Threads）

项目从旧执行模型迁移后，核心收益是：

- 降低编码复杂度
- 提升故障定位与调试体验
- 保持高并发处理能力

| 维度 | 传统响应式写法 | 虚拟线程同步写法 |
|---|---|---|
| 代码复杂度 | 高 | 低 |
| 调试体验 | 较复杂 | 直观 |
| 学习成本 | 较高 | 低 |

## 常见问题（FAQ）

### Q1：这个项目适合什么场景？

适合 Java 技术栈内，希望构建轻量可控网关、且有高并发 I/O 诉求的团队。

### Q2：是否必须使用响应式编程？

不需要。该项目核心思路是“Netty I/O + 虚拟线程业务处理”，以同步代码风格为主。

### Q3：如何扩展新插件？

按现有 `plugin/handler` 结构新增插件处理器，实现并注册到处理链即可。

## License

本项目遵循 [MIT License](./LICENSE)。
