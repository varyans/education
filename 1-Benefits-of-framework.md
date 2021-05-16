# Benefits of framework

for the better understanding, we have to look at some design patterns and logic behind of it

> This Document prepared for Beginner Level, so some ideas simplified for avoid confusion

## Dependency Inversion Pattern `SOLID principles` 

 * 
     ```java
    // DO NOT DO THIS 
    
    public class DoNotDoThis {
        public static void main(String[] args) {
            UserService userService = new UserService();
            userService.saveToDb("ismail");
        }
    }
    
    
    class UserService {
    
        private MySqlDatabase mySqlDatabase;
    
        public UserService() {
            mySqlDatabase = new MySqlDatabase();
        }
    
        public void saveToDb(String name) {
            mySqlDatabase.persist(name);
        }
    
    }
    
    class MySqlDatabase {
    
        public MySqlDatabase() {
        }
        public void persist(String name) {
            System.out.println("persisted to MySqlDatabase : "+name);
        }
    
    }
    
    ```

 Problems start with internal initiation in `UserService` this cause almost impossible to test this code 
 
* 
  ```java
  // DO NOT DO THIS 
  
    public class DoNotDoThis {
        public static void main(String[] args) {
            MySqlDatabase mySqlDatabase = new MySqlDatabase();
            UserService userService = new UserService(mySqlDatabase);
            userService.saveToDb("ismail");
        }
    }
    
    class UserService {
    
        private MySqlDatabase mySqlDatabase;
    
        public UserService(MySqlDatabase mySqlDatabase) {
            this.mySqlDatabase = mySqlDatabase;
        }
    
        public void saveToDb(String name) {
            mySqlDatabase.persist(name);
        }
    }
    
    class MySqlDatabase {
    
        public MySqlDatabase() {
        }
        public void persist(String name) {
            System.out.println("persisted to MySqlDatabase : "+name);
        }
    
    }

    ```
  This version is at least testable but `UserService` and `MySqlDatabase` are tightly coupled. Imagine your company want to move from MySql to Oracle so you should touch every class uses to `MySqlDatabase` and **you have to initialize `MySqlDatabase` somewhere else** 

* 
    ```java
  //DO THIS
  
    public class DoThis {
        public static void main(String[] args) {
            MySqlDatabase mySqlDatabase = new MySqlDatabase();
            OracleDatabase oracleDatabase = new OracleDatabase();
            UserService userService = new UserService(oracleDatabase);
            userService.saveToDb("ismail");
        }
    }
    
    class UserService {
    
        private Database database;
    
        public UserService(Database database) {
            this.database = database;
        }
    
        public void saveToDb(String name) {
            database.persist(name);
        }
    }
    
    interface Database {
        void persist(String name);
    }
    
    class MySqlDatabase implements Database {
    
        public MySqlDatabase() {
        }
        public void persist(String name) {
            System.out.println("persisted to MySqlDatabase : "+name);
        }
    
    }
  
      class OracleDatabase implements Database {
    
        public OracleDatabase() {
        }
        public void persist(String name) {
            System.out.println("persisted to OracleDatabase : "+name);
        }
    
    }
    ```
    As you see Dependency Inversion makes code testable and easily replaceable, when the project grows initialization part gets more complicated `Frameworks` shine in this part they provide `Dependency Injection` with many abstraction modules like `Database` interface so users of framework don't need to know what is underlying implementations 