# jdbc-test
Test potential pitfalls of JDBC drivers

# How to run
Create a test table in your database (you can check examples under `src/main/resources`).  

Make sure that your JDBC driver in `pom.xml` dependencies

Copy one of the `*.properties` files from `src/main/resources` directory to user home and rename it to `jdbc-test.properties`

Set your database connection settings there.
