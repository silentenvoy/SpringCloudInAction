## 《Spring微服务实战》源码

### 1.使用步骤

- 获取代码
- 使用IDE导入代码，并导入每一个模块
- 完毕

### 2.postgresql脚本
```sql
-- don't use drop 
-- DROP TABLE IF EXISTS "public"."abtesting";
CREATE TABLE "public"."abtesting" (
  "service_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "active" varchar(1) COLLATE "pg_catalog"."default" NOT NULL,
  "endpoint" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "weight" int4
)
;

INSERT INTO "public"."abtesting" VALUES ('organizationservice', 'Y', 'http://127.0.0.1:8088', 5);
-- don't use drop 
-- DROP TABLE IF EXISTS "public"."license";
CREATE TABLE "public"."license" (
  "id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "organization_id" varchar(40) COLLATE "pg_catalog"."default"
)
;

INSERT INTO "public"."license" VALUES ('2', 'java', '121');
INSERT INTO "public"."license" VALUES ('1', 'c', '121');
INSERT INTO "public"."license" VALUES ('3', 'c#', '121');
INSERT INTO "public"."license" VALUES ('4', 'python', '111');
-- don't use drop 
--- DROP TABLE IF EXISTS "public"."organization";
CREATE TABLE "public"."organization" (
  "id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" date
)
;

INSERT INTO "public"."organization" VALUES ('121', '机械工业出版社', '2021-12-25');
INSERT INTO "public"."organization" VALUES ('111', '人民邮电出版社', '2021-12-25');

-- ----------------------------
ALTER TABLE "public"."abtesting" ADD CONSTRAINT "abtesting_pkey" PRIMARY KEY ("service_name");

ALTER TABLE "public"."license" ADD CONSTRAINT "license_pkey" PRIMARY KEY ("id");

ALTER TABLE "public"."organization" ADD CONSTRAINT "organization_pkey" PRIMARY KEY ("id");

```

### 3.Kafka配置

#### 3.1 版本信息

- 操作系统：Centos7.8.2003 
- kafka版本: kafka_2.13-3.0.0

#### 3.2 配置
- kafka 的server.properties配置
```properties
# server.properties的两项配置，防止连接被拒绝
# 192.168.133.129是kafka部署环境的配置
# 和自己的主机保持一致，尽量不要使用127.0.0.1之类的
zookeeper.connect=192.168.133.129:2181
listeners=PLAINTEXT://192.168.133.129:9092
```