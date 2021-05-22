public class DoNotDoThis {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.saveToDb("ismail");
    }

}

public class UserService {

    public UserService() {
        MySqlDatabase mySqlDatabase = new MySqlDatabase();
    }

    public void saveToDb(String name) {
        mySqlDatabase.persist(name);
    }

}


public class MySqlDatabase {

  public void persist(String name) {
      System.out.println("persisted to MySqlDatabase : "+name);
  }
}