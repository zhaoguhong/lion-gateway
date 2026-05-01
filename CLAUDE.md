# AGENTS.md

This file provides guidance for AI agents working on the Lion Gateway project.

## Project Overview

**Lion Gateway** is a lightweight, high-performance API gateway built with:
- JDK 21+ with virtual threads
- Spring Boot 3.2
- Netty 4.1

## Key File Locations

| Path | Purpose |
|------|---------|
| `/pom.xml` | Root Maven configuration |
| `/lion-gateway-core/` | Core gateway functionality |
| `/lion-gateway-bootstrap/` | Bootstrap with Spring Boot main class |
| `/doc/` | Complete documentation set (Chinese) |
| `/README.md` | Project overview (Chinese) |

## Architecture Quick Reference

```
Client Request → Netty Server → Handler Chain (Plugins) → Response
```

## When to Use Which Agent

- **Explore Agent**: When you need to understand codebase structure or find files
- **Plan Agent**: When implementing new features or refactoring
- **General Agent**: For research, complex tasks, multi-step operations

## Development Guidelines

1. **Virtual Threads**: Use JDK 21 virtual threads instead of reactive programming
2. **Avoid `synchronized`**: Use `ReentrantLock` instead for better virtual thread performance
3. **Code Style**: Follow existing patterns in the codebase
4. **Comments in English**: All code comments and JavaDoc must be in English
5. **Git Commits**: Write all git commit messages in English
6. **Documentation**: Update `/doc/` files for significant changes (in Chinese)
7. **Testing**: Use JUnit 5 (already configured)
8. **Sync Changes**: When modifying code, always update corresponding documentation and unit tests

## Building & Running

```bash
mvn clean compile
mvn package
java -jar lion-gateway-bootstrap/target/lion-gateway-bootstrap-1.0-SNAPSHOT.jar
```

## Common Tasks

- Add new plugin: Extend `AbstractPluginHandler` and follow plugin development doc (`/doc/04-插件开发.md`)
- Add new predicate: Implement `Predicate` interface and register in factory
- Configuration: Add properties to `LionProperties`
