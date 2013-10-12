package net.seabears.db;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;

public class SqliteUtils
{
  private SqliteUtils()
  {
    throw new UnsupportedOperationException();
  }

  public static final String TEXT_NULL = "NULL";
  public static final String BOOLEAN_FALSE = "0";
  public static final String BOOLEAN_TRUE = "1";
  public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss.SSS");

  public static Map<String, String> getValues(Object obj) throws IllegalAccessException
  {
    List<Field> fields = PersistenceUtils.getColumnFields(obj);
    LinkedHashMap<String, String> values = new LinkedHashMap<String, String>(fields.size());

    for (Field field : fields)
    {
      if (!PersistenceUtils.isFieldValueGenerated(field))
      {
        String name = PersistenceUtils.getFieldName(obj, field);
        values.put(name, getColumnText(obj, field));
      }
    }

    return values;
  }

  public static boolean verifyNullable(Object obj, Field f) throws IllegalArgumentException, IllegalAccessException
  {
    Column c = PersistenceUtils.getColumn(f);
    f.setAccessible(true);
    return f.get(obj) != null || (c == null || c.nullable());
  }

  public static String getColumnText(Object obj, Field f) throws IllegalAccessException
  {
    Class<?> type = f.getType();

    f.setAccessible(true);
    Object rawValue = f.get(obj);

    if (!verifyNullable(obj, f))
    {
      throw new NullPointerException("Non-null field " + f.getName() + " is null.");
    }

    if (type != null && type.isEnum())
    {
      return rawValue == null ? TEXT_NULL : '\'' + rawValue.toString() + '\'';
    }
    else if (String.class.equals(type))
    {
      String value = (String) rawValue;
      return value == null ? TEXT_NULL : '\'' + value + '\'';
    }
    else if (Date.class.equals(type))
    {
      Date value = (Date) rawValue;
      return value == null ? TEXT_NULL : '\'' + FORMAT_DATE.format(value) + '\'';
    }
    else if (Boolean.class.equals(type) || Boolean.TYPE.equals(type))
    {
      Boolean value = (Boolean) rawValue;
      return value == null ? TEXT_NULL : (value ? BOOLEAN_TRUE : BOOLEAN_FALSE);
    }
    else if (Double.class.equals(type) || Double.TYPE.equals(type))
    {
      Double value = (Double) rawValue;
      return value == null ? TEXT_NULL : value + StringUtils.EMPTY;
    }
    else if (Float.class.equals(type) || Float.TYPE.equals(type))
    {
      Float value = (Float) rawValue;
      return value == null ? TEXT_NULL : value + StringUtils.EMPTY;
    }
    else if (Integer.class.equals(type) || Integer.TYPE.equals(type))
    {
      Integer value = (Integer) rawValue;
      return value == null ? TEXT_NULL : value + StringUtils.EMPTY;
    }
    else if (Long.class.equals(type) || Long.TYPE.equals(type))
    {
      Long value = (Long) rawValue;
      return value == null ? TEXT_NULL : value + StringUtils.EMPTY;
    }
    else
    {
      throw new UnsupportedOperationException();
    }
  }
}
