package ci.prosuma.prosumagpv.metier.util.app;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 



import java.lang.reflect.Array;

public class ArrayHelper
{

    public ArrayHelper()
    {
    }

    public static boolean contain(Object obj, Object obj1)
    {
        if(!obj.getClass().isArray())
            throw new IllegalArgumentException();
        int i = Array.getLength(obj);
        if(i <= 0)
            return false;
        for(int j = 0; j < i; j++)
        {
            Object obj2 = Array.get(obj, j);
            if(obj1.equals(obj2))
                return true;
        }

        return false;
    }

    public static Object extend(Object obj, Object obj1)
    {
        if(!obj.getClass().isArray())
            throw new IllegalArgumentException();
        int i = 1;
        if(obj1.getClass().isArray())
            i = Array.getLength(obj1);
        Object obj2 = extend(obj, i);
        if(obj1.getClass().isArray())
        {
            for(int j = 0; j < Array.getLength(obj1); j++)
            {
                Object obj3 = Array.get(obj1, j);
                Array.set(obj2, Array.getLength(obj) + j, obj3);
            }

        } else
        {
            Array.set(obj2, Array.getLength(obj), obj1);
        }
        return obj2;
    }

    @SuppressWarnings("rawtypes")
	public static Object extend(Object obj, int i)
    {
        if(!obj.getClass().isArray())
        {
            throw new IllegalArgumentException();
        } else
        {
            Object obj1 = null;
            Class class1 = obj.getClass().getComponentType();
            int j = Array.getLength(obj);
            Object obj2 = Array.newInstance(class1, j);
            System.arraycopy(obj, 0, obj2, 0, j);
            obj1 = Array.newInstance(class1, j + i);
            System.arraycopy(obj2, 0, obj1, 0, Array.getLength(obj2));
            return obj1;
        }
    }

    @SuppressWarnings("rawtypes")
	public static Object remove(Object obj, Object obj1)
    {
        if(!obj.getClass().isArray())
            throw new IllegalArgumentException();
        Object obj2 = null;
        if(contain(obj, obj1))
        {
            Class class1 = obj.getClass().getComponentType();
            int i = Array.getLength(obj);
            Object obj3 = Array.newInstance(class1, i);
            System.arraycopy(obj, 0, obj3, 0, i);
            if(i > 1)
            {
                obj2 = Array.newInstance(class1, i - 1);
                int j = 0;
                int k = 0;
                for(; j < i; j++)
                {
                    Object obj4 = Array.get(obj3, j);
                    if(!obj4.equals(obj1))
                        Array.set(obj2, k++, obj4);
                }

            } else
            {
                obj2 = Array.newInstance(class1, 0);
            }
        }
        return obj2;
    }

    public static void shiftLeft(Object obj, int i)
    {
        if(!obj.getClass().isArray())
            throw new IllegalArgumentException();
        int j = Array.getLength(obj);
        if(j <= 1 || i == j)
            return;
        for(int k = 0; k < i; k++)
        {
            Object obj1 = Array.get(obj, 0);
            for(int l = j - 1; l > 0; l--)
            {
                Object obj2 = Array.get(obj, l);
                Array.set(obj, l - 1, obj2);
            }

            Array.set(obj, j - 1, obj1);
        }

    }

    public static void shiftRight(Object obj, int i)
    {
        if(!obj.getClass().isArray())
            throw new IllegalArgumentException();
        int j = Array.getLength(obj);
        if(j <= 1 || i == j)
            return;
        for(int k = 0; k < i; k++)
        {
            Object obj1 = Array.get(obj, j - 1);
            for(int l = j - 2; l >= 0; l--)
            {
                Object obj2 = Array.get(obj, l);
                Array.set(obj, l + 1, obj2);
            }

            Array.set(obj, 0, obj1);
        }

    }
}
