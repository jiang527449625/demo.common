## demo.common

基础包

#分布式雪花算法ID
工作id -> 用服务器ip生成，ip转化为long再模32（如有集群部署，且机器ip是连续的,
如我的集群服务器是192.168.180.54~58共五台机器，这样取模就能避免重复.如服务器ip不连续，慎用，慎用，慎用！！！！！！ 重要事情说三遍）
数据中心ID -> 取0～31的随机数


目前未实现：
  spring cloud + seata 分布式事物
  log4j + logstash + gelf + rabbitmq + elesticesearch 日志收集
