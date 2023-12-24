# 01. SpringMVC环境搭建
选择一个支持内嵌Tomcat服务器的Spring 容器作为`ApplicationContext`的实现
`AnnotationConfigServletWebServerApplicationContext`

[入口方法](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springmvc/init/AppTest.java)

[WebConfig](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springmvc/init/WebConfig.java)

`WebConfig`作为配置类，向 Spring 容器中添加内嵌 Web 容器工厂、`DispatcherServlet` 和 `DispatcherServlet 注册对象`。

Tomcat 容器初始化成功，Spring 容器初始化成功，但 `DispatcherServlet` 还未被初始化。


# 02.DispatcherServlet初始化
## 初始化时机
当 Tomcat 服务器首次使用到 `DispatcherServlet` 时，才会由 Tomcat 服务器初始化 `DispatcherServlet`。

配置属性改变启动时机
registrationBean.setLoadOnStartup(1);

## 初始化做的事情
主要逻辑是在这个方法里面
`org.springframework.web.servlet.DispatcherServlet.initStrategies`

* initHandlerMappings()：初始化处理器映射器
* initHandlerAdapters()：初始化处理器适配器
* initHandlerExceptionResolvers()：初始化异常处理器


# 03.RequestMappingHandlerMapping


# 04.




# 03。

# REF
[B站视频](https://www.bilibili.com/video/BV1P44y1N7QG/?p=64&vd_source=550dc9095f2a0980780a8fe0a239112e)

[Spring四十久讲-SpringMVC](https://mofan212.github.io/posts/Spring-Forty-Nine-Lectures-MVC/)