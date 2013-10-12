package net.seabears.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public abstract class DatabaseEntity
{
  private Field getId()
  {
    Field id = PersistenceUtils.getIdField(this);
    if (id == null)
    {
      throw new UnsupportedOperationException("Database objects must have ID fields.");
    }
    return id;
  }

  public String getDeleteString()
  {
    try
    {
      Field idField = this.getId();
      String idName = PersistenceUtils.getFieldName(this, idField);
      String id = SqliteUtils.getColumnText(this, idField);

      return "DELETE FROM " + PersistenceUtils.getTableName(this)
          + " WHERE " + idName + '=' + id + ';';
    } catch (IllegalAccessException e)
    {
      throw new UnsupportedOperationException(e);
    }
  }

  public String getColumnNameString() throws IllegalAccessException
  {
    Map<String, String> dbValues = SqliteUtils.getValues(this);
    return '(' + StringUtils.join(dbValues.keySet(), ',') + ')';
  }

  public String getColumnValueString() throws IllegalAccessException
  {
    Map<String, String> dbValues = SqliteUtils.getValues(this);
    return '(' + StringUtils.join(dbValues.values(), ',') + ')';
  }

  public String getInsertString()
  {
    try
    {
      // build the name-value pairs
      String names = getColumnNameString();
      String values = getColumnValueString();

      return "INSERT INTO " + PersistenceUtils.getTableName(this)
          + ' ' + names + " VALUES " + values + ';';
    } catch (IllegalAccessException e)
    {
      throw new UnsupportedOperationException(e);
    }
  }

  public String getUpdateString()
  {
    try
    {
      // get the id
      Field idField = this.getId();
      String idName = PersistenceUtils.getFieldName(this, idField);
      String id = SqliteUtils.getColumnText(this, idField);

      // build the name-value pairs
      Map<String, String> dbValues = SqliteUtils.getValues(this);
      ArrayList<String> pairs = new ArrayList<String>(dbValues.size());
      for (Map.Entry<String, String> entry : dbValues.entrySet())
      {
        pairs.add(entry.getKey() + '=' + entry.getValue());
      }

      return "UPDATE " + PersistenceUtils.getTableName(this)
          + " SET " + StringUtils.join(pairs, ',')
          + " WHERE " + idName + '=' + id + ';';
    } catch (IllegalAccessException e)
    {
      throw new UnsupportedOperationException(e);
    }
  }
}
