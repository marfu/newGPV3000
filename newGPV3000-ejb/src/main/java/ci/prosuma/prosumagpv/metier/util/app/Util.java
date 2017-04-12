package ci.prosuma.prosumagpv.metier.util.app;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 




// Referenced classes of package tr.com.pos.util:
//            ArrayHelper

public class Util
{

    public Util()
    {
    }

    public static int toInteger(String s)
    {
        if(s == null || s.length() == 0)
            return 0;
        int i = -1;
        if((i = s.toLowerCase().indexOf('x')) >= 0)
            return Integer.valueOf(s.substring(i + 1), 16).intValue();
        try
        {
            return Integer.parseInt(s);
        }
        catch(Exception exception)
        {
            return 0;
        }
    }

    public static short toShort(String s)
    {
        if(s == null || s.length() == 0)
            return 0;
        int i = -1;
        if((i = s.toLowerCase().indexOf('x')) >= 0)
            return Short.valueOf(s.substring(i + 1), 16).shortValue();
        try
        {
            return Short.parseShort(s);
        }
        catch(Exception exception)
        {
            return 0;
        }
    }

    public static long toLong(String s)
    {
        if(s == null || s.length() == 0)
            return 0L;
        int i = -1;
        if((i = s.toLowerCase().indexOf('x')) >= 0)
            return Long.valueOf(s.substring(i + 1), 16).longValue();
        try
        {
            return Long.parseLong(s);
        }
        catch(Exception exception)
        {
            return 0L;
        }
    }

    public static int toInteger(Object obj)
    {
        try
        {
            if(obj == null)
                return 0;
        }
        catch(Exception exception)
        {
            return 0;
        }
        if(obj.getClass() == (java.lang.String.class))
            return toInteger((String)obj);
        if(obj.getClass() == (java.lang.Integer.class))
            return ((Integer)obj).intValue();
        Number number = (Number)obj;
        return number.intValue();
    }

    public static double toDouble(String s)
    {
        if(s == null || s.length() == 0)
            return 0.0D;
        try
        {
            String s1 = s.replaceAll(",", ".");
            return Double.parseDouble(s1);
        }
        catch(Exception exception)
        {
            return 0.0D;
        }
    }

    public static double toDouble(Object obj)
    {
        try
        {
            if(obj == null)
                return 0.0D;
        }
        catch(Exception exception)
        {
            return 0.0D;
        }
        if(obj.getClass() == (java.lang.String.class))
            return toDouble((String)obj);
        if(obj.getClass() == (java.lang.Double.class))
            return ((Double)obj).doubleValue();
        Number number = (Number)obj;
        return number.doubleValue();
    }

    public static float toFloat(String s)
    {
        if(s == null || s.length() == 0)
            return 0.0F;
        try
        {
            String s1 = s.replaceAll(",", ".");
            return Float.parseFloat(s1);
        }
        catch(Exception exception)
        {
            return 0.0F;
        }
    }

    public static float toFloat(Object obj)
    {
        try
        {
            if(obj == null)
                return 0.0F;
        }
        catch(Exception exception)
        {
            return 0.0F;
        }
        if(obj.getClass() == (java.lang.String.class))
            return toFloat((String)obj);
        if(obj.getClass() == (java.lang.Float.class))
            return ((Float)obj).floatValue();
        Number number = (Number)obj;
        return number.floatValue();
    }

    public static byte toByte(String s)
    {
        if(s == null || s.length() == 0)
            return 0;
        int i = -1;
        if((i = s.toLowerCase().indexOf('x')) >= 0)
            return Byte.valueOf(s.substring(i + 1), 16).byteValue();
        try
        {
            return (byte)Integer.parseInt(s);
        }
        catch(Exception exception)
        {
            return 0;
        }
    }

    public static byte toByte(Object obj)
    {
        try
        {
            if(obj == null)
                return 0;
        }
        catch(Exception exception)
        {
            return 0;
        }
        if(obj.getClass() == (java.lang.String.class))
            return toByte((String)obj);
        if(obj.getClass() == (java.lang.Byte.class))
            return ((Byte)obj).byteValue();
        Number number = (Number)obj;
        return number.byteValue();
    }

    public static String toString(Object obj)
    {
        try
        {
            if(obj == null)
                return "";
        }
        catch(Exception exception)
        {
            return "";
        }
        if(obj instanceof String)
            return (String)obj;
        return obj.toString();
    }

    public static long toLong(Object obj)
    {
        try
        {
            if(obj == null)
                return 0L;
        }
        catch(Exception exception)
        {
            return 0L;
        }
        if(obj instanceof String)
            return toLong((String)obj);
        if(obj instanceof Long)
            return ((Long)obj).longValue();
        Number number = (Number)obj;
        return number.longValue();
    }

    public static boolean toBoolean(Object obj)
    {
        if(obj == null)
            return false;
        if(obj instanceof Boolean)
            return ((Boolean)obj).booleanValue();
        String s;
//       / if(!(obj instanceof String))
        	//break MISSING_BLOCK_LABEL_55;
        s = (String)obj;
        if(s.compareToIgnoreCase("true") == 0)
            return true;
        if(s.compareToIgnoreCase("false") == 0)
            return false;
        try
        {
            Number number = (Number)obj;
            if(number != null)
                return number.intValue() != 0;
        }
        catch(Exception exception) { }
        return false;
    }

    public static String getStringValue(String s)
    {
        return s.substring(0, s.length() - checkChar(s).length());
    }

    public static long getNumericValue(String s)
    {
        return toLong(checkChar(s));
    }

    public static String checkChar(String s)
    {
        String s1 = "";
        for(int i = 0; s.length() > i; i++)
        {
            char c = s.charAt(i);
            if((c < 'A' || c > 'Z') && (c < 'a' || c > 'z'))
                s1 = s1 + s.charAt(i);
        }

        return s1;
    }

    public static boolean isNumeric(String s)
    {
        boolean flag = true;
        String s1 = "0123456789";
        int i = 0;
        do
        {
            if(i >= s.length())
                break;
            flag &= s1.indexOf(s.substring(i, i + 1)) >= 0;
            if(!flag)
                break;
            i++;
        } while(true);
        return flag;
    }

    public static String decimalToString(String s, int i)
    {
        String s1 = s;
        if(i > s1.length())
        {
            for(int j = 0; j < i - s.length(); j++)
                s1 = "0" + s1;

        } else
        if(i < s1.length())
            s1 = s1.substring(s1.length() - i);
        return s1;
    }

    public static String createString(char c, int i)
    {
        String s = "";
        for(int j = 0; j < i; j++)
            s = s + c;

        return s;
    }

    public static String[] split(String s, char c)
    {
        String as[] = new String[0];
        String s1 = "";
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) == c)
            {
                as = (String[])ArrayHelper.extend(as, s1);
                s1 = "";
            } else
            {
                s1 = s1 + s.charAt(i);
            }

        as = (String[])ArrayHelper.extend(as, s1);
        return as;
    }

    public static String replace(String s, String s1, String s2)
    {
        String s3;
        for(s3 = s; s3.indexOf(s1) >= 0; s3 = s3.substring(0, s3.indexOf(s1)) + s2 + s3.substring(s3.indexOf(s1) + s1.length()));
        return s3;
    }
}
