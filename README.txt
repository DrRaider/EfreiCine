Used those websites to setup:
https://programmerscuriosity.com/2016/09/27/simple-jersey-example-with-intellij-idea-ultimate-and-tomcat/ 
https://gist.github.com/bluekvirus/29789fe8080e31d84f921241311e567d#file-jersey-hibernate-mariadb-tomcat-md
http://websystique.com/spring-security/spring-security-4-hibernate-annotation-example/

TheMovieDbAPI and this wrapper : https://github.com/UweTrottmann/tmdb-java
Thanks for those ;)

Use script.sql to generate and populate your database (it produces one admin with 'sam as id and 'abc125' as password.)

TODO :
- proper html/jsp editing
- proper redirects
- build rest of the Web Service :^)

24-Oct-2017 21:18:30.636 WARNING [localhost-startStop-2] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [ROOT] appears to have started a thread named [OkHttp ConnectionPool] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 java.lang.Object.wait(Native Method)
 okhttp3.ConnectionPool$1.run(ConnectionPool.java:67)
 java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1160)
 java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
 java.lang.Thread.run(Thread.java:795)

DONE :
- Server up
- Communicate with MySQL via hibernate
- User management (ADMIN, DBA and USER level of access)
- Bcryt password encryption
- Login
- Register (with unique SSO ID)


