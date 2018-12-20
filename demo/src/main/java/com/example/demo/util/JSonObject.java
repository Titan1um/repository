/*      */ package com.example.demo.util;
/*      */ 
/*      */ import java.io.Closeable;
/*      */ import java.io.IOException;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.lang.annotation.Annotation;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Set;
/*      */ import org.json.JSONArray;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONPointer;
/*      */ import org.json.JSONPointerException;
/*      */ import org.json.JSONPropertyIgnore;
/*      */ import org.json.JSONPropertyName;
/*      */ import org.json.JSONString;
/*      */ import org.json.JSONTokener;
/*      */ import org.json.JSONWriter;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JSonObject
/*      */ {
/*      */   private final Map<String, Object> map;
/*  167 */   public static final Object NULL = new JSonObject.Null(null);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject()
/*      */   {
/*  179 */     this.map = new LinkedHashMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject(JSonObject jo, String[] names)
/*      */   {
/*  193 */     this(names.length);
/*  194 */     for (int i = 0; i < names.length; i++) {
/*      */       try {
/*  196 */         putOnce(names[i], jo.opt(names[i]));
/*      */       }
/*      */       catch (Exception localException) {}
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject(JSONTokener x)
/*      */     throws JSONException
/*      */   {
/*  212 */     this();
/*      */     
/*      */ 
/*      */ 
/*  216 */     if (x.nextClean() != '{') {
/*  217 */       throw x.syntaxError("A JSonObject text must begin with '{'");
/*      */     }
/*      */     for (;;) {
/*  220 */       char c = x.nextClean();
/*  221 */       switch (c) {
/*      */       case '\000': 
/*  223 */         throw x.syntaxError("A JSonObject text must end with '}'");
/*      */       case '}': 
/*  225 */         return;
/*      */       }
/*  227 */       x.back();
/*  228 */       String key = x.nextValue().toString();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  233 */       c = x.nextClean();
/*  234 */       if (c != ':') {
/*  235 */         throw x.syntaxError("Expected a ':' after a key");
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  240 */       if (key != null)
/*      */       {
/*  242 */         if (opt(key) != null)
/*      */         {
/*  244 */           throw x.syntaxError("Duplicate key \"" + key + "\"");
/*      */         }
/*      */         
/*  247 */         Object value = x.nextValue();
/*  248 */         if (value != null) {
/*  249 */           put(key, value);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  255 */       switch (x.nextClean()) {
/*      */       case ',': 
/*      */       case ';': 
/*  258 */         if (x.nextClean() == '}') {
/*  259 */           return;
/*      */         }
/*  261 */         x.back();
/*      */       }
/*      */     }
/*  264 */     return;
/*      */     
/*  266 */     throw x.syntaxError("Expected a ',' or '}'");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject(Map<?, ?> m)
/*      */   {
/*  283 */     if (m == null) {
/*  284 */       this.map = new LinkedHashMap();
/*      */     } else {
/*  286 */       this.map = new LinkedHashMap(m.size());
/*  287 */       for (Map.Entry<?, ?> e : m.entrySet()) {
/*  288 */         if (e.getKey() == null) {
/*  289 */           throw new NullPointerException("Null key.");
/*      */         }
/*  291 */         Object value = e.getValue();
/*  292 */         if (value != null) {
/*  293 */           this.map.put(String.valueOf(e.getKey()), wrap(value));
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject(Object bean)
/*      */   {
/*  358 */     this();
/*  359 */     populateMap(bean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject(Object object, String[] names)
/*      */   {
/*  377 */     this(names.length);
/*  378 */     Class<?> c = object.getClass();
/*  379 */     for (int i = 0; i < names.length; i++) {
/*  380 */       String name = names[i];
/*      */       try {
/*  382 */         putOpt(name, c.getField(name).get(object));
/*      */       }
/*      */       catch (Exception localException) {}
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject(String source)
/*      */     throws JSONException
/*      */   {
/*  401 */     this(new JSONTokener(source));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject(String baseName, Locale locale)
/*      */     throws JSONException
/*      */   {
/*  415 */     this();
/*  416 */     ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale, 
/*  417 */       Thread.currentThread().getContextClassLoader());
/*      */     
/*      */ 
/*      */ 
/*  421 */     Enumeration<String> keys = bundle.getKeys();
/*  422 */     while (keys.hasMoreElements()) {
/*  423 */       Object key = keys.nextElement();
/*  424 */       if (key != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  430 */         String[] path = ((String)key).split("\\.");
/*  431 */         int last = path.length - 1;
/*  432 */         JSonObject target = this;
/*  433 */         for (int i = 0; i < last; i++) {
/*  434 */           String segment = path[i];
/*  435 */           JSonObject nextTarget = target.optJSonObject(segment);
/*  436 */           if (nextTarget == null) {
/*  437 */             nextTarget = new JSonObject();
/*  438 */             target.put(segment, nextTarget);
/*      */           }
/*  440 */           target = nextTarget;
/*      */         }
/*  442 */         target.put(path[last], bundle.getString((String)key));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected JSonObject(int initialCapacity)
/*      */   {
/*  455 */     this.map = new LinkedHashMap(initialCapacity);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject accumulate(String key, Object value)
/*      */     throws JSONException
/*      */   {
/*  480 */     testValidity(value);
/*  481 */     Object object = opt(key);
/*  482 */     if (object == null) {
/*  483 */       put(key, (value instanceof JSONArray) ? new JSONArray()
/*  484 */         .put(value) : value);
/*      */     }
/*  486 */     else if ((object instanceof JSONArray)) {
/*  487 */       ((JSONArray)object).put(value);
/*      */     } else {
/*  489 */       put(key, new JSONArray().put(object).put(value));
/*      */     }
/*  491 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject append(String key, Object value)
/*      */     throws JSONException
/*      */   {
/*  512 */     testValidity(value);
/*  513 */     Object object = opt(key);
/*  514 */     if (object == null) {
/*  515 */       put(key, new JSONArray().put(value));
/*  516 */     } else if ((object instanceof JSONArray)) {
/*  517 */       put(key, ((JSONArray)object).put(value));
/*      */     } else {
/*  519 */       throw new JSONException("JSonObject[" + key + "] is not a JSONArray.");
/*      */     }
/*      */     
/*  522 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String doubleToString(double d)
/*      */   {
/*  534 */     if ((Double.isInfinite(d)) || (Double.isNaN(d))) {
/*  535 */       return "null";
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  540 */     String string = Double.toString(d);
/*  541 */     if ((string.indexOf('.') > 0) && (string.indexOf('e') < 0) && 
/*  542 */       (string.indexOf('E') < 0)) {
/*  543 */       while (string.endsWith("0")) {
/*  544 */         string = string.substring(0, string.length() - 1);
/*      */       }
/*  546 */       if (string.endsWith(".")) {
/*  547 */         string = string.substring(0, string.length() - 1);
/*      */       }
/*      */     }
/*  550 */     return string;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object get(String key)
/*      */     throws JSONException
/*      */   {
/*  563 */     if (key == null) {
/*  564 */       throw new JSONException("Null key.");
/*      */     }
/*  566 */     Object object = opt(key);
/*  567 */     if (object == null) {
/*  568 */       throw new JSONException("JSonObject[" + quote(key) + "] not found.");
/*      */     }
/*  570 */     return object;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public <E extends Enum<E>> E getEnum(Class<E> clazz, String key)
/*      */     throws JSONException
/*      */   {
/*  586 */     E val = optEnum(clazz, key);
/*  587 */     if (val == null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  592 */       throw new JSONException("JSonObject[" + quote(key) + "] is not an enum of type " + quote(clazz.getSimpleName()) + ".");
/*      */     }
/*      */     
/*  595 */     return val;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(String key)
/*      */     throws JSONException
/*      */   {
/*  609 */     Object object = get(key);
/*  610 */     if (!object.equals(Boolean.FALSE)) { if ((object instanceof String))
/*      */       {
/*  612 */         if (!((String)object).equalsIgnoreCase("false")) {} }
/*  613 */     } else return false;
/*  614 */     if (!object.equals(Boolean.TRUE)) { if ((object instanceof String))
/*      */       {
/*  616 */         if (!((String)object).equalsIgnoreCase("true")) {} }
/*  617 */     } else { return true;
/*      */     }
/*  619 */     throw new JSONException("JSonObject[" + quote(key) + "] is not a Boolean.");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigInteger getBigInteger(String key)
/*      */     throws JSONException
/*      */   {
/*  634 */     Object object = get(key);
/*      */     try {
/*  636 */       return new BigInteger(object.toString());
/*      */     } catch (Exception e) {
/*  638 */       throw new JSONException("JSonObject[" + quote(key) + "] could not be converted to BigInteger.", e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(String key)
/*      */     throws JSONException
/*      */   {
/*  654 */     Object object = get(key);
/*  655 */     if ((object instanceof BigDecimal)) {
/*  656 */       return (BigDecimal)object;
/*      */     }
/*      */     try {
/*  659 */       return new BigDecimal(object.toString());
/*      */     } catch (Exception e) {
/*  661 */       throw new JSONException("JSonObject[" + quote(key) + "] could not be converted to BigDecimal.", e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDouble(String key)
/*      */     throws JSONException
/*      */   {
/*  677 */     Object object = get(key);
/*      */     try {
/*  679 */       return (object instanceof Number) ? ((Number)object).doubleValue() : 
/*  680 */         Double.parseDouble(object.toString());
/*      */     } catch (Exception e) {
/*  682 */       throw new JSONException("JSonObject[" + quote(key) + "] is not a number.", e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getFloat(String key)
/*      */     throws JSONException
/*      */   {
/*  698 */     Object object = get(key);
/*      */     try {
/*  700 */       return (object instanceof Number) ? ((Number)object).floatValue() : 
/*  701 */         Float.parseFloat(object.toString());
/*      */     } catch (Exception e) {
/*  703 */       throw new JSONException("JSonObject[" + quote(key) + "] is not a number.", e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Number getNumber(String key)
/*      */     throws JSONException
/*      */   {
/*  719 */     Object object = get(key);
/*      */     try {
/*  721 */       if ((object instanceof Number)) {
/*  722 */         return (Number)object;
/*      */       }
/*  724 */       return stringToNumber(object.toString());
/*      */     } catch (Exception e) {
/*  726 */       throw new JSONException("JSonObject[" + quote(key) + "] is not a number.", e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInt(String key)
/*      */     throws JSONException
/*      */   {
/*  742 */     Object object = get(key);
/*      */     try {
/*  744 */       return (object instanceof Number) ? ((Number)object).intValue() : 
/*  745 */         Integer.parseInt((String)object);
/*      */     } catch (Exception e) {
/*  747 */       throw new JSONException("JSonObject[" + quote(key) + "] is not an int.", e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSONArray getJSONArray(String key)
/*      */     throws JSONException
/*      */   {
/*  762 */     Object object = get(key);
/*  763 */     if ((object instanceof JSONArray)) {
/*  764 */       return (JSONArray)object;
/*      */     }
/*  766 */     throw new JSONException("JSonObject[" + quote(key) + "] is not a JSONArray.");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject getJSonObject(String key)
/*      */     throws JSONException
/*      */   {
/*  780 */     Object object = get(key);
/*  781 */     if ((object instanceof JSonObject)) {
/*  782 */       return (JSonObject)object;
/*      */     }
/*  784 */     throw new JSONException("JSonObject[" + quote(key) + "] is not a JSonObject.");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLong(String key)
/*      */     throws JSONException
/*      */   {
/*  799 */     Object object = get(key);
/*      */     try {
/*  801 */       return (object instanceof Number) ? ((Number)object).longValue() : 
/*  802 */         Long.parseLong((String)object);
/*      */     } catch (Exception e) {
/*  804 */       throw new JSONException("JSonObject[" + quote(key) + "] is not a long.", e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String[] getNames(JSonObject jo)
/*      */   {
/*  815 */     if (jo.isEmpty()) {
/*  816 */       return null;
/*      */     }
/*  818 */     return (String[])jo.keySet().toArray(new String[jo.length()]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String[] getNames(Object object)
/*      */   {
/*  827 */     if (object == null) {
/*  828 */       return null;
/*      */     }
/*  830 */     Class<?> klass = object.getClass();
/*  831 */     Field[] fields = klass.getFields();
/*  832 */     int length = fields.length;
/*  833 */     if (length == 0) {
/*  834 */       return null;
/*      */     }
/*  836 */     String[] names = new String[length];
/*  837 */     for (int i = 0; i < length; i++) {
/*  838 */       names[i] = fields[i].getName();
/*      */     }
/*  840 */     return names;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getString(String key)
/*      */     throws JSONException
/*      */   {
/*  853 */     Object object = get(key);
/*  854 */     if ((object instanceof String)) {
/*  855 */       return (String)object;
/*      */     }
/*  857 */     throw new JSONException("JSonObject[" + quote(key) + "] not a string.");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean has(String key)
/*      */   {
/*  868 */     return this.map.containsKey(key);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject increment(String key)
/*      */     throws JSONException
/*      */   {
/*  884 */     Object value = opt(key);
/*  885 */     if (value == null) {
/*  886 */       put(key, 1);
/*  887 */     } else if ((value instanceof BigInteger)) {
/*  888 */       put(key, ((BigInteger)value).add(BigInteger.ONE));
/*  889 */     } else if ((value instanceof BigDecimal)) {
/*  890 */       put(key, ((BigDecimal)value).add(BigDecimal.ONE));
/*  891 */     } else if ((value instanceof Integer)) {
/*  892 */       put(key, ((Integer)value).intValue() + 1);
/*  893 */     } else if ((value instanceof Long)) {
/*  894 */       put(key, ((Long)value).longValue() + 1L);
/*  895 */     } else if ((value instanceof Double)) {
/*  896 */       put(key, ((Double)value).doubleValue() + 1.0D);
/*  897 */     } else if ((value instanceof Float)) {
/*  898 */       put(key, ((Float)value).floatValue() + 1.0F);
/*      */     } else {
/*  900 */       throw new JSONException("Unable to increment [" + quote(key) + "].");
/*      */     }
/*  902 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isNull(String key)
/*      */   {
/*  915 */     return NULL.equals(opt(key));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Iterator<String> keys()
/*      */   {
/*  927 */     return keySet().iterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Set<String> keySet()
/*      */   {
/*  939 */     return this.map.keySet();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Set<Map.Entry<String, Object>> entrySet()
/*      */   {
/*  955 */     return this.map.entrySet();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int length()
/*      */   {
/*  964 */     return this.map.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEmpty()
/*      */   {
/*  973 */     return this.map.isEmpty();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSONArray names()
/*      */   {
/*  984 */     if (this.map.isEmpty()) {
/*  985 */       return null;
/*      */     }
/*  987 */     return new JSONArray(this.map.keySet());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String numberToString(Number number)
/*      */     throws JSONException
/*      */   {
/* 1000 */     if (number == null) {
/* 1001 */       throw new JSONException("Null pointer");
/*      */     }
/* 1003 */     testValidity(number);
/*      */     
/*      */ 
/*      */ 
/* 1007 */     String string = number.toString();
/* 1008 */     if ((string.indexOf('.') > 0) && (string.indexOf('e') < 0) && 
/* 1009 */       (string.indexOf('E') < 0)) {
/* 1010 */       while (string.endsWith("0")) {
/* 1011 */         string = string.substring(0, string.length() - 1);
/*      */       }
/* 1013 */       if (string.endsWith(".")) {
/* 1014 */         string = string.substring(0, string.length() - 1);
/*      */       }
/*      */     }
/* 1017 */     return string;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object opt(String key)
/*      */   {
/* 1028 */     return key == null ? null : this.map.get(key);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public <E extends Enum<E>> E optEnum(Class<E> clazz, String key)
/*      */   {
/* 1041 */     return optEnum(clazz, key, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public <E extends Enum<E>> E optEnum(Class<E> clazz, String key, E defaultValue)
/*      */   {
/*      */     try
/*      */     {
/* 1058 */       Object val = opt(key);
/* 1059 */       if (NULL.equals(val)) {
/* 1060 */         return defaultValue;
/*      */       }
/* 1062 */       if (clazz.isAssignableFrom(val.getClass()))
/*      */       {
/*      */ 
/* 1065 */         return (Enum)val;
/*      */       }
/*      */       
/* 1068 */       return Enum.valueOf(clazz, val.toString());
/*      */     } catch (IllegalArgumentException e) {
/* 1070 */       return defaultValue;
/*      */     } catch (NullPointerException e) {}
/* 1072 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean optBoolean(String key)
/*      */   {
/* 1085 */     return optBoolean(key, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean optBoolean(String key, boolean defaultValue)
/*      */   {
/* 1100 */     Object val = opt(key);
/* 1101 */     if (NULL.equals(val)) {
/* 1102 */       return defaultValue;
/*      */     }
/* 1104 */     if ((val instanceof Boolean)) {
/* 1105 */       return ((Boolean)val).booleanValue();
/*      */     }
/*      */     try
/*      */     {
/* 1109 */       return getBoolean(key);
/*      */     } catch (Exception e) {}
/* 1111 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal optBigDecimal(String key, BigDecimal defaultValue)
/*      */   {
/* 1127 */     Object val = opt(key);
/* 1128 */     if (NULL.equals(val)) {
/* 1129 */       return defaultValue;
/*      */     }
/* 1131 */     if ((val instanceof BigDecimal)) {
/* 1132 */       return (BigDecimal)val;
/*      */     }
/* 1134 */     if ((val instanceof BigInteger)) {
/* 1135 */       return new BigDecimal((BigInteger)val);
/*      */     }
/* 1137 */     if (((val instanceof Double)) || ((val instanceof Float))) {
/* 1138 */       return new BigDecimal(((Number)val).doubleValue());
/*      */     }
/* 1140 */     if (((val instanceof Long)) || ((val instanceof Integer)) || ((val instanceof Short)) || ((val instanceof Byte)))
/*      */     {
/* 1142 */       return new BigDecimal(((Number)val).longValue());
/*      */     }
/*      */     try
/*      */     {
/* 1146 */       return new BigDecimal(val.toString());
/*      */     } catch (Exception e) {}
/* 1148 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigInteger optBigInteger(String key, BigInteger defaultValue)
/*      */   {
/* 1164 */     Object val = opt(key);
/* 1165 */     if (NULL.equals(val)) {
/* 1166 */       return defaultValue;
/*      */     }
/* 1168 */     if ((val instanceof BigInteger)) {
/* 1169 */       return (BigInteger)val;
/*      */     }
/* 1171 */     if ((val instanceof BigDecimal)) {
/* 1172 */       return ((BigDecimal)val).toBigInteger();
/*      */     }
/* 1174 */     if (((val instanceof Double)) || ((val instanceof Float))) {
/* 1175 */       return new BigDecimal(((Number)val).doubleValue()).toBigInteger();
/*      */     }
/* 1177 */     if (((val instanceof Long)) || ((val instanceof Integer)) || ((val instanceof Short)) || ((val instanceof Byte)))
/*      */     {
/* 1179 */       return BigInteger.valueOf(((Number)val).longValue());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 1188 */       String valStr = val.toString();
/* 1189 */       if (isDecimalNotation(valStr)) {
/* 1190 */         return new BigDecimal(valStr).toBigInteger();
/*      */       }
/* 1192 */       return new BigInteger(valStr);
/*      */     } catch (Exception e) {}
/* 1194 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double optDouble(String key)
/*      */   {
/* 1208 */     return optDouble(key, NaN.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double optDouble(String key, double defaultValue)
/*      */   {
/* 1223 */     Object val = opt(key);
/* 1224 */     if (NULL.equals(val)) {
/* 1225 */       return defaultValue;
/*      */     }
/* 1227 */     if ((val instanceof Number)) {
/* 1228 */       return ((Number)val).doubleValue();
/*      */     }
/* 1230 */     if ((val instanceof String)) {
/*      */       try {
/* 1232 */         return Double.parseDouble((String)val);
/*      */       } catch (Exception e) {
/* 1234 */         return defaultValue;
/*      */       }
/*      */     }
/* 1237 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float optFloat(String key)
/*      */   {
/* 1250 */     return optFloat(key, NaN.0F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float optFloat(String key, float defaultValue)
/*      */   {
/* 1265 */     Object val = opt(key);
/* 1266 */     if (NULL.equals(val)) {
/* 1267 */       return defaultValue;
/*      */     }
/* 1269 */     if ((val instanceof Number)) {
/* 1270 */       return ((Number)val).floatValue();
/*      */     }
/* 1272 */     if ((val instanceof String)) {
/*      */       try {
/* 1274 */         return Float.parseFloat((String)val);
/*      */       } catch (Exception e) {
/* 1276 */         return defaultValue;
/*      */       }
/*      */     }
/* 1279 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int optInt(String key)
/*      */   {
/* 1292 */     return optInt(key, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int optInt(String key, int defaultValue)
/*      */   {
/* 1307 */     Object val = opt(key);
/* 1308 */     if (NULL.equals(val)) {
/* 1309 */       return defaultValue;
/*      */     }
/* 1311 */     if ((val instanceof Number)) {
/* 1312 */       return ((Number)val).intValue();
/*      */     }
/*      */     
/* 1315 */     if ((val instanceof String)) {
/*      */       try {
/* 1317 */         return new BigDecimal((String)val).intValue();
/*      */       } catch (Exception e) {
/* 1319 */         return defaultValue;
/*      */       }
/*      */     }
/* 1322 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSONArray optJSONArray(String key)
/*      */   {
/* 1334 */     Object o = opt(key);
/* 1335 */     return (o instanceof JSONArray) ? (JSONArray)o : null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject optJSonObject(String key)
/*      */   {
/* 1347 */     Object object = opt(key);
/* 1348 */     return (object instanceof JSonObject) ? (JSonObject)object : null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long optLong(String key)
/*      */   {
/* 1361 */     return optLong(key, 0L);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long optLong(String key, long defaultValue)
/*      */   {
/* 1376 */     Object val = opt(key);
/* 1377 */     if (NULL.equals(val)) {
/* 1378 */       return defaultValue;
/*      */     }
/* 1380 */     if ((val instanceof Number)) {
/* 1381 */       return ((Number)val).longValue();
/*      */     }
/*      */     
/* 1384 */     if ((val instanceof String)) {
/*      */       try {
/* 1386 */         return new BigDecimal((String)val).longValue();
/*      */       } catch (Exception e) {
/* 1388 */         return defaultValue;
/*      */       }
/*      */     }
/* 1391 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Number optNumber(String key)
/*      */   {
/* 1405 */     return optNumber(key, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Number optNumber(String key, Number defaultValue)
/*      */   {
/* 1421 */     Object val = opt(key);
/* 1422 */     if (NULL.equals(val)) {
/* 1423 */       return defaultValue;
/*      */     }
/* 1425 */     if ((val instanceof Number)) {
/* 1426 */       return (Number)val;
/*      */     }
/*      */     
/* 1429 */     if ((val instanceof String)) {
/*      */       try {
/* 1431 */         return stringToNumber((String)val);
/*      */       } catch (Exception e) {
/* 1433 */         return defaultValue;
/*      */       }
/*      */     }
/* 1436 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String optString(String key)
/*      */   {
/* 1449 */     return optString(key, "");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String optString(String key, String defaultValue)
/*      */   {
/* 1463 */     Object object = opt(key);
/* 1464 */     return NULL.equals(object) ? defaultValue : object.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void populateMap(Object bean)
/*      */   {
/* 1477 */     Class<?> klass = bean.getClass();
/*      */     
/*      */ 
/*      */ 
/* 1481 */     boolean includeSuperClass = klass.getClassLoader() != null;
/*      */     
/* 1483 */     Method[] methods = includeSuperClass ? klass.getMethods() : klass.getDeclaredMethods();
/* 1484 */     for (Method method : methods) {
/* 1485 */       int modifiers = method.getModifiers();
/* 1486 */       if ((Modifier.isPublic(modifiers)) && 
/* 1487 */         (!Modifier.isStatic(modifiers)) && 
/* 1488 */         (method.getParameterTypes().length == 0) && 
/* 1489 */         (!method.isBridge()) && 
/* 1490 */         (method.getReturnType() != Void.TYPE) && 
/* 1491 */         (isValidMethodName(method.getName()))) {
/* 1492 */         String key = getKeyNameFromMethod(method);
/* 1493 */         if ((key != null) && (!key.isEmpty())) {
/*      */           try {
/* 1495 */             Object result = method.invoke(bean, new Object[0]);
/* 1496 */             if (result != null) {
/* 1497 */               this.map.put(key, wrap(result));
/*      */               
/*      */ 
/*      */ 
/* 1501 */               if ((result instanceof Closeable)) {
/*      */                 try {
/* 1503 */                   ((Closeable)result).close();
/*      */                 }
/*      */                 catch (IOException localIOException) {}
/*      */               }
/*      */             }
/*      */           }
/*      */           catch (IllegalAccessException localIllegalAccessException) {}catch (IllegalArgumentException localIllegalArgumentException) {}catch (InvocationTargetException localInvocationTargetException) {}
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private boolean isValidMethodName(String name)
/*      */   {
/* 1518 */     return (!"getClass".equals(name)) && (!"getDeclaringClass".equals(name));
/*      */   }
/*      */   
/*      */   private String getKeyNameFromMethod(Method method) {
/* 1522 */     int ignoreDepth = getAnnotationDepth(method, JSONPropertyIgnore.class);
/* 1523 */     if (ignoreDepth > 0) {
/* 1524 */       int forcedNameDepth = getAnnotationDepth(method, JSONPropertyName.class);
/* 1525 */       if ((forcedNameDepth < 0) || (ignoreDepth <= forcedNameDepth))
/*      */       {
/*      */ 
/* 1528 */         return null;
/*      */       }
/*      */     }
/* 1531 */     JSONPropertyName annotation = (JSONPropertyName)getAnnotation(method, JSONPropertyName.class);
/* 1532 */     if ((annotation != null) && (annotation.value() != null) && (!annotation.value().isEmpty())) {
/* 1533 */       return annotation.value();
/*      */     }
/*      */     
/* 1536 */     String name = method.getName();
/* 1537 */     String key; if ((name.startsWith("get")) && (name.length() > 3)) {
/* 1538 */       key = name.substring(3); } else { String key;
/* 1539 */       if ((name.startsWith("is")) && (name.length() > 2)) {
/* 1540 */         key = name.substring(2);
/*      */       } else {
/* 1542 */         return null;
/*      */       }
/*      */     }
/*      */     
/*      */     String key;
/* 1547 */     if (Character.isLowerCase(key.charAt(0))) {
/* 1548 */       return null;
/*      */     }
/* 1550 */     if (key.length() == 1) {
/* 1551 */       key = key.toLowerCase(Locale.ROOT);
/* 1552 */     } else if (!Character.isUpperCase(key.charAt(1))) {
/* 1553 */       key = key.substring(0, 1).toLowerCase(Locale.ROOT) + key.substring(1);
/*      */     }
/* 1555 */     return key;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static <A extends Annotation> A getAnnotation(Method m, Class<A> annotationClass)
/*      */   {
/* 1574 */     if ((m == null) || (annotationClass == null)) {
/* 1575 */       return null;
/*      */     }
/*      */     
/* 1578 */     if (m.isAnnotationPresent(annotationClass)) {
/* 1579 */       return m.getAnnotation(annotationClass);
/*      */     }
/*      */     
/*      */ 
/* 1583 */     Class<?> c = m.getDeclaringClass();
/* 1584 */     if (c.getSuperclass() == null) {
/* 1585 */       return null;
/*      */     }
/*      */     
/*      */ 
/* 1589 */     for (Class<?> i : c.getInterfaces()) {
/*      */       try {
/* 1591 */         Method im = i.getMethod(m.getName(), m.getParameterTypes());
/* 1592 */         return getAnnotation(im, annotationClass);
/*      */       }
/*      */       catch (SecurityException ex) {}catch (NoSuchMethodException ex) {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 1601 */       return getAnnotation(c
/* 1602 */         .getSuperclass().getMethod(m.getName(), m.getParameterTypes()), annotationClass);
/*      */     }
/*      */     catch (SecurityException ex) {
/* 1605 */       return null;
/*      */     } catch (NoSuchMethodException ex) {}
/* 1607 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int getAnnotationDepth(Method m, Class<? extends Annotation> annotationClass)
/*      */   {
/* 1627 */     if ((m == null) || (annotationClass == null)) {
/* 1628 */       return -1;
/*      */     }
/*      */     
/* 1631 */     if (m.isAnnotationPresent(annotationClass)) {
/* 1632 */       return 1;
/*      */     }
/*      */     
/*      */ 
/* 1636 */     Class<?> c = m.getDeclaringClass();
/* 1637 */     if (c.getSuperclass() == null) {
/* 1638 */       return -1;
/*      */     }
/*      */     
/*      */ 
/* 1642 */     for (Class<?> i : c.getInterfaces()) {
/*      */       try {
/* 1644 */         Method im = i.getMethod(m.getName(), m.getParameterTypes());
/* 1645 */         int d = getAnnotationDepth(im, annotationClass);
/* 1646 */         if (d > 0)
/*      */         {
/* 1648 */           return d + 1;
/*      */         }
/*      */       }
/*      */       catch (SecurityException ex) {}catch (NoSuchMethodException ex) {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 1658 */       int d = getAnnotationDepth(c
/* 1659 */         .getSuperclass().getMethod(m.getName(), m.getParameterTypes()), annotationClass);
/*      */       
/* 1661 */       if (d > 0)
/*      */       {
/* 1663 */         return d + 1;
/*      */       }
/* 1665 */       return -1;
/*      */     } catch (SecurityException ex) {
/* 1667 */       return -1;
/*      */     } catch (NoSuchMethodException ex) {}
/* 1669 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject put(String key, boolean value)
/*      */     throws JSONException
/*      */   {
/* 1687 */     return put(key, value ? Boolean.TRUE : Boolean.FALSE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject put(String key, Collection<?> value)
/*      */     throws JSONException
/*      */   {
/* 1705 */     return put(key, new JSONArray(value));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject put(String key, double value)
/*      */     throws JSONException
/*      */   {
/* 1722 */     return put(key, Double.valueOf(value));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject put(String key, float value)
/*      */     throws JSONException
/*      */   {
/* 1739 */     return put(key, Float.valueOf(value));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject put(String key, int value)
/*      */     throws JSONException
/*      */   {
/* 1756 */     return put(key, Integer.valueOf(value));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject put(String key, long value)
/*      */     throws JSONException
/*      */   {
/* 1773 */     return put(key, Long.valueOf(value));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject put(String key, Map<?, ?> value)
/*      */     throws JSONException
/*      */   {
/* 1791 */     return put(key, new JSonObject(value));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject put(String key, Object value)
/*      */     throws JSONException
/*      */   {
/* 1811 */     if (key == null) {
/* 1812 */       throw new NullPointerException("Null key.");
/*      */     }
/* 1814 */     if (value != null) {
/* 1815 */       testValidity(value);
/* 1816 */       this.map.put(key, value);
/*      */     } else {
/* 1818 */       remove(key);
/*      */     }
/* 1820 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject putOnce(String key, Object value)
/*      */     throws JSONException
/*      */   {
/* 1835 */     if ((key != null) && (value != null)) {
/* 1836 */       if (opt(key) != null) {
/* 1837 */         throw new JSONException("Duplicate key \"" + key + "\"");
/*      */       }
/* 1839 */       return put(key, value);
/*      */     }
/* 1841 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSonObject putOpt(String key, Object value)
/*      */     throws JSONException
/*      */   {
/* 1859 */     if ((key != null) && (value != null)) {
/* 1860 */       return put(key, value);
/*      */     }
/* 1862 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object query(String jsonPointer)
/*      */   {
/* 1885 */     return query(new JSONPointer(jsonPointer));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object query(JSONPointer jsonPointer)
/*      */   {
/* 1907 */     return jsonPointer.queryFrom(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object optQuery(String jsonPointer)
/*      */   {
/* 1919 */     return optQuery(new JSONPointer(jsonPointer));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object optQuery(JSONPointer jsonPointer)
/*      */   {
/*      */     try
/*      */     {
/* 1932 */       return jsonPointer.queryFrom(this);
/*      */     } catch (JSONPointerException e) {}
/* 1934 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String quote(String string)
/*      */   {
/* 1949 */     StringWriter sw = new StringWriter();
/* 1950 */     synchronized (sw.getBuffer()) {
/*      */       try {
/* 1952 */         return quote(string, sw).toString();
/*      */       }
/*      */       catch (IOException ignored) {
/* 1955 */         return "";
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static Writer quote(String string, Writer w) throws IOException {
/* 1961 */     if ((string == null) || (string.isEmpty())) {
/* 1962 */       w.write("\"\"");
/* 1963 */       return w;
/*      */     }
/*      */     
/*      */ 
/* 1967 */     char c = '\000';
/*      */     
/*      */ 
/* 1970 */     int len = string.length();
/*      */     
/* 1972 */     w.write(34);
/* 1973 */     for (int i = 0; i < len; i++) {
/* 1974 */       char b = c;
/* 1975 */       c = string.charAt(i);
/* 1976 */       switch (c) {
/*      */       case '"': 
/*      */       case '\\': 
/* 1979 */         w.write(92);
/* 1980 */         w.write(c);
/* 1981 */         break;
/*      */       case '/': 
/* 1983 */         if (b == '<') {
/* 1984 */           w.write(92);
/*      */         }
/* 1986 */         w.write(c);
/* 1987 */         break;
/*      */       case '\b': 
/* 1989 */         w.write("\\b");
/* 1990 */         break;
/*      */       case '\t': 
/* 1992 */         w.write("\\t");
/* 1993 */         break;
/*      */       case '\n': 
/* 1995 */         w.write("\\n");
/* 1996 */         break;
/*      */       case '\f': 
/* 1998 */         w.write("\\f");
/* 1999 */         break;
/*      */       case '\r': 
/* 2001 */         w.write("\\r");
/* 2002 */         break;
/*      */       default: 
/* 2004 */         if ((c < ' ') || ((c >= '') && (c < ' ')) || ((c >= ' ') && (c < '℀')))
/*      */         {
/* 2006 */           w.write("\\u");
/* 2007 */           String hhhh = Integer.toHexString(c);
/* 2008 */           w.write("0000", 0, 4 - hhhh.length());
/* 2009 */           w.write(hhhh);
/*      */         } else {
/* 2011 */           w.write(c);
/*      */         }
/*      */         break; }
/*      */     }
/* 2015 */     w.write(34);
/* 2016 */     return w;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object remove(String key)
/*      */   {
/* 2028 */     return this.map.remove(key);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean similar(Object other)
/*      */   {
/*      */     try
/*      */     {
/* 2041 */       if (!(other instanceof JSonObject)) {
/* 2042 */         return false;
/*      */       }
/* 2044 */       if (!keySet().equals(((JSonObject)other).keySet())) {
/* 2045 */         return false;
/*      */       }
/* 2047 */       for (Map.Entry<String, ?> entry : entrySet()) {
/* 2048 */         String name = (String)entry.getKey();
/* 2049 */         Object valueThis = entry.getValue();
/* 2050 */         Object valueOther = ((JSonObject)other).get(name);
/* 2051 */         if (valueThis != valueOther)
/*      */         {
/*      */ 
/* 2054 */           if (valueThis == null) {
/* 2055 */             return false;
/*      */           }
/* 2057 */           if ((valueThis instanceof JSonObject)) {
/* 2058 */             if (!((JSonObject)valueThis).similar(valueOther)) {
/* 2059 */               return false;
/*      */             }
/* 2061 */           } else if ((valueThis instanceof JSONArray)) {
/* 2062 */             if (!((JSONArray)valueThis).similar(valueOther)) {
/* 2063 */               return false;
/*      */             }
/* 2065 */           } else if (!valueThis.equals(valueOther))
/* 2066 */             return false;
/*      */         }
/*      */       }
/* 2069 */       return true;
/*      */     } catch (Throwable exception) {}
/* 2071 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static boolean isDecimalNotation(String val)
/*      */   {
/* 2082 */     return (val.indexOf('.') > -1) || (val.indexOf('e') > -1) || 
/* 2083 */       (val.indexOf('E') > -1) || ("-0".equals(val));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static Number stringToNumber(String val)
/*      */     throws NumberFormatException
/*      */   {
/* 2097 */     char initial = val.charAt(0);
/* 2098 */     if (((initial >= '0') && (initial <= '9')) || (initial == '-'))
/*      */     {
/* 2100 */       if (isDecimalNotation(val))
/*      */       {
/*      */ 
/* 2103 */         if (val.length() > 14) {
/* 2104 */           return new BigDecimal(val);
/*      */         }
/* 2106 */         Double d = Double.valueOf(val);
/* 2107 */         if ((d.isInfinite()) || (d.isNaN()))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/* 2112 */           return new BigDecimal(val);
/*      */         }
/* 2114 */         return d;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2138 */       BigInteger bi = new BigInteger(val);
/* 2139 */       if (bi.bitLength() <= 31) {
/* 2140 */         return Integer.valueOf(bi.intValue());
/*      */       }
/* 2142 */       if (bi.bitLength() <= 63) {
/* 2143 */         return Long.valueOf(bi.longValue());
/*      */       }
/* 2145 */       return bi;
/*      */     }
/* 2147 */     throw new NumberFormatException("val [" + val + "] is not a valid number.");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Object stringToValue(String string)
/*      */   {
/* 2161 */     if (string.equals("")) {
/* 2162 */       return string;
/*      */     }
/* 2164 */     if (string.equalsIgnoreCase("true")) {
/* 2165 */       return Boolean.TRUE;
/*      */     }
/* 2167 */     if (string.equalsIgnoreCase("false")) {
/* 2168 */       return Boolean.FALSE;
/*      */     }
/* 2170 */     if (string.equalsIgnoreCase("null")) {
/* 2171 */       return NULL;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2179 */     char initial = string.charAt(0);
/* 2180 */     if (((initial >= '0') && (initial <= '9')) || (initial == '-'))
/*      */     {
/*      */       try
/*      */       {
/* 2184 */         if (isDecimalNotation(string)) {
/* 2185 */           Double d = Double.valueOf(string);
/* 2186 */           if ((!d.isInfinite()) && (!d.isNaN())) {
/* 2187 */             return d;
/*      */           }
/*      */         } else {
/* 2190 */           Long myLong = Long.valueOf(string);
/* 2191 */           if (string.equals(myLong.toString())) {
/* 2192 */             if (myLong.longValue() == myLong.intValue()) {
/* 2193 */               return Integer.valueOf(myLong.intValue());
/*      */             }
/* 2195 */             return myLong;
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (Exception localException) {}
/*      */     }
/* 2201 */     return string;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void testValidity(Object o)
/*      */     throws JSONException
/*      */   {
/* 2213 */     if (o != null) {
/* 2214 */       if ((o instanceof Double)) {
/* 2215 */         if ((((Double)o).isInfinite()) || (((Double)o).isNaN())) {
/* 2216 */           throw new JSONException("JSON does not allow non-finite numbers.");
/*      */         }
/*      */       }
/* 2219 */       else if (((o instanceof Float)) && (
/* 2220 */         (((Float)o).isInfinite()) || (((Float)o).isNaN()))) {
/* 2221 */         throw new JSONException("JSON does not allow non-finite numbers.");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JSONArray toJSONArray(JSONArray names)
/*      */     throws JSONException
/*      */   {
/* 2240 */     if ((names == null) || (names.isEmpty())) {
/* 2241 */       return null;
/*      */     }
/* 2243 */     JSONArray ja = new JSONArray();
/* 2244 */     for (int i = 0; i < names.length(); i++) {
/* 2245 */       ja.put(opt(names.getString(i)));
/*      */     }
/* 2247 */     return ja;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*      */     try
/*      */     {
/* 2266 */       return toString(0);
/*      */     } catch (Exception e) {}
/* 2268 */     return null;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public String toString(int indentFactor)
/*      */     throws JSONException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: new 233	java/io/StringWriter
/*      */     //   3: dup
/*      */     //   4: invokespecial 234	java/io/StringWriter:<init>	()V
/*      */     //   7: astore_2
/*      */     //   8: aload_2
/*      */     //   9: invokevirtual 235	java/io/StringWriter:getBuffer	()Ljava/lang/StringBuffer;
/*      */     //   12: dup
/*      */     //   13: astore_3
/*      */     //   14: monitorenter
/*      */     //   15: aload_0
/*      */     //   16: aload_2
/*      */     //   17: iload_1
/*      */     //   18: iconst_0
/*      */     //   19: invokevirtual 276	com/example/demo/util/JSonObject:write	(Ljava/io/Writer;II)Ljava/io/Writer;
/*      */     //   22: invokevirtual 16	java/lang/Object:toString	()Ljava/lang/String;
/*      */     //   25: aload_3
/*      */     //   26: monitorexit
/*      */     //   27: areturn
/*      */     //   28: astore 4
/*      */     //   30: aload_3
/*      */     //   31: monitorexit
/*      */     //   32: aload 4
/*      */     //   34: athrow
/*      */     // Line number table:
/*      */     //   Java source line #2299	-> byte code offset #0
/*      */     //   Java source line #2300	-> byte code offset #8
/*      */     //   Java source line #2301	-> byte code offset #15
/*      */     //   Java source line #2302	-> byte code offset #28
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	35	0	this	JSonObject
/*      */     //   0	35	1	indentFactor	int
/*      */     //   7	10	2	w	StringWriter
/*      */     //   13	18	3	Ljava/lang/Object;	Object
/*      */     //   28	5	4	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   15	27	28	finally
/*      */     //   28	32	28	finally
/*      */   }
/*      */   
/*      */   public static String valueToString(Object value)
/*      */     throws JSONException
/*      */   {
/* 2334 */     return JSONWriter.valueToString(value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Object wrap(Object object)
/*      */   {
/*      */     try
/*      */     {
/* 2351 */       if (object == null) {
/* 2352 */         return NULL;
/*      */       }
/* 2354 */       if (((object instanceof JSonObject)) || ((object instanceof JSONArray)) || 
/* 2355 */         (NULL.equals(object)) || ((object instanceof JSONString)) || ((object instanceof Byte)) || ((object instanceof Character)) || ((object instanceof Short)) || ((object instanceof Integer)) || ((object instanceof Long)) || ((object instanceof Boolean)) || ((object instanceof Float)) || ((object instanceof Double)) || ((object instanceof String)) || ((object instanceof BigInteger)) || ((object instanceof BigDecimal)) || ((object instanceof Enum)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2362 */         return object;
/*      */       }
/*      */       
/* 2365 */       if ((object instanceof Collection)) {
/* 2366 */         Collection<?> coll = (Collection)object;
/* 2367 */         return new JSONArray(coll);
/*      */       }
/* 2369 */       if (object.getClass().isArray()) {
/* 2370 */         return new JSONArray(object);
/*      */       }
/* 2372 */       if ((object instanceof Map)) {
/* 2373 */         Map<?, ?> map = (Map)object;
/* 2374 */         return new JSonObject(map);
/*      */       }
/* 2376 */       Package objectPackage = object.getClass().getPackage();
/*      */       
/* 2378 */       String objectPackageName = objectPackage != null ? objectPackage.getName() : "";
/* 2379 */       if ((objectPackageName.startsWith("java.")) || 
/* 2380 */         (objectPackageName.startsWith("javax.")) || 
/* 2381 */         (object.getClass().getClassLoader() == null)) {
/* 2382 */         return object.toString();
/*      */       }
/* 2384 */       return new JSonObject(object);
/*      */     } catch (Exception exception) {}
/* 2386 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Writer write(Writer writer)
/*      */     throws JSONException
/*      */   {
/* 2401 */     return write(writer, 0, 0);
/*      */   }
/*      */   
/*      */   static final Writer writeValue(Writer writer, Object value, int indentFactor, int indent) throws JSONException, IOException
/*      */   {
/* 2406 */     if ((value == null) || (value.equals(null))) {
/* 2407 */       writer.write("null");
/* 2408 */     } else if ((value instanceof JSONString))
/*      */     {
/*      */       try {
/* 2411 */         o = ((JSONString)value).toJSONString();
/*      */       } catch (Exception e) { Object o;
/* 2413 */         throw new JSONException(e); }
/*      */       Object o;
/* 2415 */       writer.write(o != null ? o.toString() : quote(value.toString()));
/* 2416 */     } else if ((value instanceof Number))
/*      */     {
/* 2418 */       String numberAsString = numberToString((Number)value);
/*      */       
/*      */       try
/*      */       {
/* 2422 */         BigDecimal testNum = new BigDecimal(numberAsString);
/*      */         
/* 2424 */         writer.write(numberAsString);
/*      */       }
/*      */       catch (NumberFormatException ex)
/*      */       {
/* 2428 */         quote(numberAsString, writer);
/*      */       }
/* 2430 */     } else if ((value instanceof Boolean)) {
/* 2431 */       writer.write(value.toString());
/* 2432 */     } else if ((value instanceof Enum)) {
/* 2433 */       writer.write(quote(((Enum)value).name()));
/* 2434 */     } else if ((value instanceof JSonObject)) {
/* 2435 */       ((JSonObject)value).write(writer, indentFactor, indent);
/* 2436 */     } else if ((value instanceof JSONArray)) {
/* 2437 */       ((JSONArray)value).write(writer, indentFactor, indent);
/* 2438 */     } else if ((value instanceof Map)) {
/* 2439 */       Map<?, ?> map = (Map)value;
/* 2440 */       new JSonObject(map).write(writer, indentFactor, indent);
/* 2441 */     } else if ((value instanceof Collection)) {
/* 2442 */       Collection<?> coll = (Collection)value;
/* 2443 */       new JSONArray(coll).write(writer, indentFactor, indent);
/* 2444 */     } else if (value.getClass().isArray()) {
/* 2445 */       new JSONArray(value).write(writer, indentFactor, indent);
/*      */     } else {
/* 2447 */       quote(value.toString(), writer);
/*      */     }
/* 2449 */     return writer;
/*      */   }
/*      */   
/*      */   static final void indent(Writer writer, int indent) throws IOException {
/* 2453 */     for (int i = 0; i < indent; i++) {
/* 2454 */       writer.write(32);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Writer write(Writer writer, int indentFactor, int indent)
/*      */     throws JSONException
/*      */   {
/*      */     try
/*      */     {
/* 2487 */       boolean commanate = false;
/* 2488 */       int length = length();
/* 2489 */       writer.write(123);
/*      */       String key;
/* 2491 */       if (length == 1) {
/* 2492 */         Map.Entry<String, ?> entry = (Map.Entry)entrySet().iterator().next();
/* 2493 */         key = (String)entry.getKey();
/* 2494 */         writer.write(quote(key));
/* 2495 */         writer.write(58);
/* 2496 */         if (indentFactor > 0) {
/* 2497 */           writer.write(32);
/*      */         }
/*      */         try {
/* 2500 */           writeValue(writer, entry.getValue(), indentFactor, indent);
/*      */         } catch (Exception e) {
/* 2502 */           throw new JSONException("Unable to write JSonObject value for key: " + key, e);
/*      */         }
/* 2504 */       } else if (length != 0) {
/* 2505 */         int newindent = indent + indentFactor;
/* 2506 */         for (Map.Entry<String, ?> entry : entrySet()) {
/* 2507 */           if (commanate) {
/* 2508 */             writer.write(44);
/*      */           }
/* 2510 */           if (indentFactor > 0) {
/* 2511 */             writer.write(10);
/*      */           }
/* 2513 */           indent(writer, newindent);
/* 2514 */           String key = (String)entry.getKey();
/* 2515 */           writer.write(quote(key));
/* 2516 */           writer.write(58);
/* 2517 */           if (indentFactor > 0) {
/* 2518 */             writer.write(32);
/*      */           }
/*      */           try {
/* 2521 */             writeValue(writer, entry.getValue(), indentFactor, newindent);
/*      */           } catch (Exception e) {
/* 2523 */             throw new JSONException("Unable to write JSonObject value for key: " + key, e);
/*      */           }
/* 2525 */           commanate = true;
/*      */         }
/* 2527 */         if (indentFactor > 0) {
/* 2528 */           writer.write(10);
/*      */         }
/* 2530 */         indent(writer, indent);
/*      */       }
/* 2532 */       writer.write(125);
/* 2533 */       return writer;
/*      */     } catch (IOException exception) {
/* 2535 */       throw new JSONException(exception);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map<String, Object> toMap()
/*      */   {
/* 2549 */     Map<String, Object> results = new LinkedHashMap();
/* 2550 */     for (Map.Entry<String, Object> entry : entrySet()) { Object value;
/*      */       Object value;
/* 2552 */       if ((entry.getValue() == null) || (NULL.equals(entry.getValue()))) {
/* 2553 */         value = null; } else { Object value;
/* 2554 */         if ((entry.getValue() instanceof JSonObject)) {
/* 2555 */           value = ((JSonObject)entry.getValue()).toMap(); } else { Object value;
/* 2556 */           if ((entry.getValue() instanceof JSONArray)) {
/* 2557 */             value = ((JSONArray)entry.getValue()).toList();
/*      */           } else
/* 2559 */             value = entry.getValue();
/*      */         } }
/* 2561 */       results.put(entry.getKey(), value);
/*      */     }
/* 2563 */     return results;
/*      */   }
/*      */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\util\JSonObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */