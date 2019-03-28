目录说明：
sc-eureka-single             :单机版eureka服务示例，包含注册中心、服务注册、服务调用
sc-eureka-cluster            :多机集群版eureka服务示例，包含注册中心、服务注册、服务调用
sc-eureka-single-security    :具备权限认证的单机版eureka服务示例，包含注册中心、服务注册、服务调用
sc-gateway                   :网关示例
                             
一、集群条件：
1.不能用localhost比如： 
eureka.client.serviceUrl.defaultZone=http://peer1:8761/eureka/
要采用： 
eureka.instance.hostname=peer1 
eureka.client.serviceUrl.defaultZone=http://peer1:8761/eureka/,http://peer2:8762/eureka/,http://peer3:8763/eureka/

2.spring.application.name要一致

3.相互注册要开启： 
eureka.client.register-with-eureka=true 
eureka.client.fetch-registry=true

二、服务实例实现快速下线快速感知
EurekaServer的ReadWriteMap、ReadOnlyMap缓存机制，造成服务注册下线存在延迟，感知不灵敏。
1.注册中心增加配置
#启用主动失效，并且每次主动失效检测间隔为3s
eureka.server.eviction-interval-timer-in-ms=3000
2.服务提供方增加配置
#服务过期时间配置,超过这个时间没有接收到心跳EurekaServer就会将这个实例剔除
#EurekaServer一定要设置eureka.server.eviction-interval-timer-in-ms否则这个配置无效，这个配置一般为服务刷新时间配置的三倍
#默认90s
eureka.instance.lease-expiration-duration-in-seconds = 15
3.服务调用方(非必须)
#eureka client刷新本地缓存时间.默认30s
eureka.client.registryFetchIntervalSeconds=5

http://10.27.137.35:8761/
http://localhost:10000/SERVICEPROVIDER/hello?name=%22111%22&token=111
