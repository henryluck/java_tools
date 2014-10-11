1.com.jlx.jaas.module包里的类是验证模块程序
  com.jlx.jaas.context包里是用户信息的入口，实例化LoginContext调用module里的类
2.执行命令： java -Djava.security.auth.login.config=D:\MyDocument\henry\Eclipse-WS\JavaAppStudy\src\JAAS.config JAASSampleApp testuser 123456

3.执行命令:  java -Djava.security.auth.login.config=D:\MyDocument\henry\Eclipse-WS\JavaAppStudy\src\JAAS.config SimpLogin
username:testUser
password:testPassword