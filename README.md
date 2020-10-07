## Spring Boot Angular Blog

#### Prerequisite
JDK 11, NodeJS 10.13.x/12.11.x or later minor, MySQL

#### Steps:
1) On the command line
    ```
    git clone https://github.com/peradze/spring-angular-blog.git
    ```
2) Create database
    ```
    spring_angular_blog
   ```
3) Run spring boot server. On the command line
    ```
   cd blog-server
   ./mvnw spring-boot:run
   ```
  
4) Run Angular application. On the command line
    ```
   cd blog-client
   npm install
   ng serve
   ```

5) Go to now http://localhost:4200
