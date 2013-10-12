package net.seabears.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.StringUtils;

public final class PersistenceUtils
{
  private final static Class<? extends Annotation> ANNOTATION_CLASS_COLUMN = Column.class;
  private final static Class<? extends Annotation> ANNOTATION_CLASS_ENTITY = Entity.class;
  private final static Class<? extends Annotation> ANNOTATION_CLASS_GENERATED_VALUE = GeneratedValue.class;
  private final static Class<? extends Annotation> ANNOTATION_CLASS_ID = Id.class;
  private final static Class<? extends Annotation> ANNOTATION_CLASS_ONE_TO_MANY = OneToMany.class;
  private final static Class<? extends Annotation> ANNOTATION_CLASS_ONE_TO_ONE = OneToOne.class;

  private PersistenceUtils()
  {
    throw new UnsupportedOperationException();
  }

  public static Entity getEntity(Object obj)
  {
    Class<?> clazz = obj.getClass();
    return (Entity) clazz.getAnnotation(ANNOTATION_CLASS_ENTITY);
  }

  public static String getTableName(Object obj)
  {
    Entity e = getEntity(obj);
    return e != null && !StringUtils.isEmpty(e.name()) ? e.name() : obj.getClass().getSimpleName();
  }

  public static String getFieldName(Object obj, Field f)
  {
    Column c = getColumn(f);
    return c != null && !StringUtils.isEmpty(c.name()) ? c.name() : f.getName();
  }

  public static boolean isFieldValueGenerated(Field f)
  {
    return f.isAnnotationPresent(ANNOTATION_CLASS_GENERATED_VALUE);
  }

  public static boolean isFieldOneToMany(Field f)
  {
    return f.isAnnotationPresent(ANNOTATION_CLASS_ONE_TO_MANY);
  }

  public static boolean isFieldOneToOne(Field f)
  {
    return f.isAnnotationPresent(ANNOTATION_CLASS_ONE_TO_ONE);
  }

  public static boolean isFieldId(Field f)
  {
    return f.isAnnotationPresent(ANNOTATION_CLASS_ID);
  }

  public static Id getId(Object obj)
  {
    Field f = getIdField(obj);
    return f != null ? (Id) f.getAnnotation(ANNOTATION_CLASS_ID) : null;
  }

  public static Field[] getFields(Object obj)
  {
    Class<?> clazz = obj.getClass();
    return clazz.getDeclaredFields();
  }

  public static Column getColumn(Field f)
  {
    return f != null ? (Column) f.getAnnotation(ANNOTATION_CLASS_COLUMN) : null;
  }

  public static List<Field> getColumnFields(Object obj)
  {
    ArrayList<Field> fields = new ArrayList<Field>();
    for (Field field : getFields(obj))
    {
      if (field.isAnnotationPresent(ANNOTATION_CLASS_COLUMN)
          || field.isAnnotationPresent(ANNOTATION_CLASS_ID))
      {
        fields.add(field);
      }
    }
    return fields;
  }

  public static Field getIdField(Object obj)
  {
    for (Field field : getFields(obj))
    {
      if (field.isAnnotationPresent(ANNOTATION_CLASS_ID))
      {
        return field;
      }
    }
    return null;
  }
}
