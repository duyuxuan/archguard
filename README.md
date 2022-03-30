# ArchGuard backend

[![CI](https://github.com/archguard/archguard-backend/actions/workflows/ci.yml/badge.svg)](https://github.com/archguard/archguard-backend/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/archguard/archguard-backend/branch/master/graph/badge.svg?token=QS5H866CWH)](https://codecov.io/gh/archguard/archguard-backend)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/archguard/archguard-backend)

Components:

- [ArchGuard Frontend](https://github.com/archguard/archguard-frontend)
- [ArchGuard Scanner](https://github.com/archguard/scanner)

Features: 

- C4 analysis
    - [x] container dependency analysis. (level: HTTP API)
      - [x] basic fe/be call
      - [x] RestTemplate for backend to backend
      - [ ] GraphQL
      - [ ] Kong Gateway
    - [x] component (module) dependency analysis.
    - [x] code dependency analysis. (level: pa``ckage, class, method)
    - [ ] database dependency analysis
- Scanner integration
    - [x] PMD
    - [x] Git with jGit
        - [x] HotFile
    - [x] Java/Jvm only
        - [x] JVM Bytecode (need to rewrite with License issue)
        - [x] CheckStyle
        - [x] Badsmell by DesigniteJava
        - [x] Test Badsmell by Coca (Java only)
    - [x] TypeScript with Chapi
    - [x] Kotlin with Chapi
    - [x] Git Hot File 
- System Info
    - [ ] Custom build command  


| Features/Languages  | Java | Python | Go  | Kotlin | TypeScript | C   | C#  | Scala | C++ |
|---------------------|------|--------|-----|--------|------------|-----|-----|-------|-----|
| http api decl       | ✅    | 🆕     | 🆕  | ✅      | ✅          | 🆕  | 🆕  | 🆕    | 🆕  |
| syntax parse        | ✅    | ✅      | ✅   | 🆕     | ✅          | 🆕  | 🆕  | ✅     | 🆕  |
| function call       | ✅    | 🆕     |     | 🆕     | ✅          |     |     |       |     |
| arch/package        | ✅    |        |     |        | ✅          |     |     | ✅     |     |
| real world validate | ✅    |        |     |        | ✅          |     |     |       |     |

## Metrics

### Code

### Logic Unstable（逻辑不稳定）

- FanInFanOut

### Physical Unstable（物理不稳定）

- UnstableFile/Class, long lines and high changes(from git)
- KnowledgeMap, author changes in package

in Chinese

- 不稳定文件/类，高频变更 + 文件行数长
- 知识地图，文件修改资料 + 作者

### Readable（可读性）

- By length, tooShort or tooLong
- one thing, `And`

Types:

- Class Name
- Method name
- Field name
- API name ?

## Development

todo:

- [ ] tech debt
    - [ ] tech stack upgrading
    - [ ] InfluxDB to 2.0
    - [ ] clean unused code
    - [ ] database lint/checkstyle
    - [ ] api lint
    - [ ] kotlin lint
- [ ] scanner
    - [x] download from GitHub by config
    - [ ] enable scanner failure
    - [x] config scanner by optional
- [ ] System landscape by C4
    - [ ] Context = System (props: name, aliasName...)
    - [ ] Containers = Repository or repository with modules (props: name, path, repository...)
    - [ ] Components = Module (props: name, path, repository...)
    - [ ] Code = Code dependence (props: name...)
- [ ] User Experience Improve
    - [ ] custom build command for SystemInfo
    - [ ] download scanner to local

test projects:

- [https://github.com/domain-driven-design/ddd-lite-example](https://github.com/domain-driven-design/ddd-lite-example)
- [https://github.com/domain-driven-design/ddd-monolithic-code-sample](https://github.com/domain-driven-design/ddd-monolithic-code-sample)

### Tech decision (framework)

- languages：Kotlin
- frameworks：Spring Boot，JDBI
- test frameworks：Junit5，Spring Boot Test，Flyway，H2
- build tool：Gradle
- data storage：MySQL, InfluxDB

### setup 

1. create database: `create database archguard default character set utf8mb4 collate utf8mb4_unicode_ci;`

2. run: `./gradlew bootrun`


#### setup InfluxDB

```
brew install influxdb@1
```

### Docker

#### user `docker-compose`

```
docker-compose up
```

License
---

@2020~2022 Thoughtworks. This code is distributed under the MPL license. See `LICENSE` in this directory.
