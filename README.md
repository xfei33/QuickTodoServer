# QuickTodoServer

QuickTodoServer 是一个基于 Spring Boot 的待办事项管理服务端程序，支持客户端与服务端之间的增量同步。

## 功能特性

- **用户认证**：支持用户注册和登录，使用 JWT 进行身份验证。
- **增量同步**：支持客户端与服务端之间的增量数据同步，减少网络传输开销。

## 技术栈

- **Spring Boot**：用于快速构建服务端程序。
- **Spring Data JPA**：用于操作数据库。
- **JWT**：用于用户身份验证。
- **MySQL**：作为主数据库。
- **H2**：用于本地开发和测试。

## 数据库设计

### 表结构

#### `users` 表
| 字段名     | 类型         | 描述         |
|------------|--------------|--------------|
| id         | BIGINT       | 用户 ID（主键） |
| username   | VARCHAR(255) | 用户名       |
| password   | VARCHAR(255) | 密码         |

#### `todos` 表
| 字段名         | 类型         | 描述                     |
|----------------|--------------|--------------------------|
| id             | BIGINT       | 待办事项 ID（主键）       |
| title          | VARCHAR(255) | 标题                     |
| description    | TEXT         | 描述                     |
| due_date       | DATETIME     | 截止日期                 |
| user_id        | BIGINT       | 用户 ID（外键）           |
| priority       | VARCHAR(50)  | 优先级（LOW, MEDIUM, HIGH）|
| tag            | VARCHAR(255) | 分类标签                 |
| completed      | BOOLEAN      | 是否已完成               |
| last_modified  | DATETIME     | 最后修改时间             |

## API 文档

### 用户认证

#### 注册
- **URL**: `/auth/register`
- **Method**: `POST`
- **参数**:
  - `username`: 用户名
  - `password`: 密码
- **响应**: 注册成功返回 `200 OK`。

#### 登录
- **URL**: `/auth/login`
- **Method**: `POST`
- **参数**:
  - `username`: 用户名
  - `password`: 密码
- **响应**: 登录成功返回 JWT 令牌。

### 待办事项管理

#### 获取待办事项列表
- **URL**: `/todos`
- **Method**: `GET`
- **参数**:
    - `userId`: 用户 ID
- **响应**: 返回待办事项列表。

### 增量同步

#### 获取增量数据
- **URL**: `/todos/sync`
- **Method**: `GET`
- **参数**:
    - `userId`: 用户 ID
    - `lastSyncTime`: 最后同步时间
- **响应**: 返回 `lastModified` 大于 `lastSyncTime` 的待办事项列表。

#### 上传增量数据
- **URL**: `/todos/sync`
- **Method**: `POST`
- **参数**:
  - `userId`: 用户 ID
- **请求体**: 待办事项列表
  ```json
  [
    {
      "title": "Meeting",
      "description": "Team meeting at 3 PM",
      "dueDate": "2023-12-31T15:00:00",
      "user": {
        "id": 1
      },
      "priority": "High",
      "tag": "Work",
      "completed": false,
      "lastModified": "2023-10-05T12:00:00"
    }
  ]
  ```
- **响应**: 上传成功返回 `200 OK`。

---

## 环境配置

### 开发环境
1. **JDK**: 17+
2. **MySQL**: 8.0+
3. **Maven**: 3.8+

### 配置文件
在 `application.properties` 中配置数据库和 JWT：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/quicktodo
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

jwt.secret=yourSecretKey
jwt.expiration=3600000 # 1 小时
```

## 快速启动

1. **克隆项目**:
   ```bash
   git clone https://github.com/yourusername/QuickTodoServer.git
   cd QuickTodoServer
   ```

2. **配置数据库**:
  - 创建 MySQL 数据库 [quicktodo](https://github.com/xfei33/QuickTodoServer/blob/main/src/main/resources/database.sql)。
  - 更新 `application.properties` 中的数据库配置。

3. **构建项目**:
   ```bash
   mvn clean install
   ```

4. **运行项目**:
   ```bash
   mvn spring-boot:run
   ```

5. **访问 API**:
  - 服务启动后，默认端口为 `8080`。
  - 使用 Postman 或 ApiFox 测试 API。

## 贡献指南

1. Fork 项目并克隆到本地。
2. 创建新分支：
   ```bash
   git checkout -b feature/your-feature
   ```
3. 提交更改：
   ```bash
   git commit -m "Add your feature"
   ```
4. 推送到远程分支：
   ```bash
   git push origin feature/your-feature
   ```
5. 提交 Pull Request。

---

## 许可证

无证。

---

## 联系

如有问题或建议，请联系：  
📧 Email: yanxiaoxi8@vip.qq.com  
🌐 GitHub: [xfei33](https://github.com/xfei33)

---

## 致谢

感谢以下开源项目的支持：

- [Spring Boot](https://spring.io/projects/spring-boot)
- [JWT](https://jwt.io/)
- [MySQL](https://www.mysql.com/)