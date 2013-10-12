package net.seabears.db;

import java.io.File;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

public class Database
{
  private final String path;
  private final SQLiteConnection connection;

  public Database(String path) throws SQLiteException
  {
    this.path = path;
    this.connection = new SQLiteConnection(new File(this.path));
    this.connection.open(false);
  }

  public boolean execute(String statement) throws SQLiteException
  {
    SQLiteStatement st = this.connection.prepare(statement);
    boolean value = st.step();
    st.dispose();
    return value;
  }

  public SQLiteStatement statement(String statement) throws SQLiteException
  {
    return this.connection.prepare(statement);
  }

  public <T> T getLastId(Class<T> idClass) throws SQLiteException
  {
    SQLiteStatement st = this.connection.prepare("SELECT last_insert_rowid();");
    T value = st.step() ? idClass.cast(st.columnValue(0)) : null;
    st.dispose();
    return value;
  }

  public void close()
  {
    this.connection.dispose();
  }
}
