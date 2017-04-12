package ci.prosuma.prosumagpv.metier.util.app;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 




public class ByteUtil
{

    public ByteUtil()
    {
    }

    public static byte[] longToByteArray(long l)
    {
        byte abyte0[] = new byte[8];
        int i = (abyte0.length - 1) * 8;
        for(int j = abyte0.length - 1; i >= 0; j--)
        {
            abyte0[j] = (byte)(int)(l >> i & 255L);
            i -= 8;
        }

        return abyte0;
    }

    public static byte[] intToByteArray(int i)
    {
        byte abyte0[] = new byte[4];
        int j = (abyte0.length - 1) * 8;
        for(int k = abyte0.length - 1; j >= 0; k--)
        {
            abyte0[k] = (byte)(i >> j & 0xff);
            j -= 8;
        }

        return abyte0;
    }

    public static byte[] shortToByteArray(short word0)
    {
        byte abyte0[] = new byte[2];
        int i = (abyte0.length - 1) * 8;
        for(int j = abyte0.length - 1; i >= 0; j--)
        {
            abyte0[j] = (byte)(word0 >> i & 0xff);
            i -= 8;
        }

        return abyte0;
    }

    public static long byteArrayToLong(byte abyte0[])
    {
        return byteArrayToLong(abyte0, 0, abyte0.length);
    }

    public static long byteArrayToLong(byte abyte0[], int i, int j)
    {
        long l = 0L;
        int k = (j - 1) * 8;
        for(int i1 = (i + j) - 1; k >= 0; i1--)
        {
            l |= (abyte0[i1] & 0xff) << k;
            k -= 8;
        }

        return l;
    }

    public static int byteArrayToInteger(byte abyte0[])
    {
        return byteArrayToInteger(abyte0, 0, abyte0.length);
    }

    public static int byteArrayToInteger(byte abyte0[], int i, int j)
    {
        int k = 0;
        int l = (j - 1) * 8;
        for(int i1 = (i + j) - 1; l >= 0; i1--)
        {
            k |= (abyte0[i1] & 0xff) << l;
            l -= 8;
        }

        return k;
    }

    public static short byteArrayToShort(byte abyte0[])
    {
        return byteArrayToShort(abyte0, 0, abyte0.length);
    }

    public static short byteArrayToShort(byte abyte0[], int i, int j)
    {
        short word0 = 0;
        int k = (j - 1) * 8;
        for(int l = (i + j) - 1; k >= 0; l--)
        {
            word0 |= (abyte0[l] & 0xff) << k;
            k -= 8;
        }

        return word0;
    }

    public static byte[][] parseByteArray(byte abyte0[], int i)
        throws Exception
    {
        if(abyte0.length < i)
            throw new Exception("Array length must be equal or greather than part length.");
        byte abyte1[][] = new byte[Math.round(abyte0.length / i)][i];
        for(int j = 0; j < abyte1.length; j++)
            System.arraycopy(abyte0, j * i, abyte1[j], 0, Math.min(abyte0.length - j * i, i));

        return abyte1;
    }

    public static String toHexString(byte byte0)
    {
        return toHexString(byte0 & 0xff, '0', 2, 2);
    }

    public static String toHexString(int i)
    {
        return toHexString(i & 0xffff, '0', 8, 8);
    }

    public static String toHexString(long l)
    {
        return toHexString(l, '0', 16, 16);
    }

    public static String toHexString(long l, char c, int i, int j)
    {
        StringBuffer stringbuffer = new StringBuffer(Long.toHexString(l));
        if(j < i)
            return stringbuffer.toString();
        for(; stringbuffer.length() < j; stringbuffer.insert(0, c));
        return stringbuffer.substring(0, i);
    }

    public static String toFormatedHexString(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("[");
        stringbuffer.append(abyte0.length);
        stringbuffer.append("]");
        for(int i = 0; i < abyte0.length; i++)
            stringbuffer.append(toHexString(abyte0[i]) + " ");

        return stringbuffer.toString();
    }

    public static String toFormatedHexPage(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s = "";
        String s1 = "";
        for(int i = 0; i < abyte0.length; i++)
        {
            if(i % 16 == 0 && i != 0)
            {
                stringbuffer.append("\n").append("\t").append(s).append("\t").append(s1);
                s = "";
                s1 = "";
            }
            if(abyte0[i] < 32)
                s = s + ".";
            else
                s = s + (char)abyte0[i];
            s1 = s1 + toHexString(abyte0[i]) + " ";
        }

        stringbuffer.append("\n").append("\t").append(s);
        for(int j = 0; (long)j < Math.round((double)(Math.abs(s.length() - 16) / 8) + 0.5D); j++)
            stringbuffer.append("\t");

        stringbuffer.append(s1);
        return stringbuffer.toString();
    }

    public static String toFormatedHexPage(char ac[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s = "";
        String s1 = "";
        for(int i = 0; i < ac.length; i++)
        {
            if(i % 16 == 0)
            {
                stringbuffer.append("\n").append("\t").append(s).append("\t").append(s1);
                s = "";
                s1 = "";
            }
            if(ac[i] < ' ')
                s = s + ".";
            else
                s = s + ac[i];
            s1 = s1 + toHexString(ac[i]) + " ";
        }

        return stringbuffer.toString();
    }

    public static String toFormatedHexString(int ai[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("[");
        stringbuffer.append(ai.length);
        stringbuffer.append("]");
        for(int i = 0; i < ai.length; i++)
            stringbuffer.append(toHexString(ai[i]) + " ");

        return stringbuffer.toString();
    }

    public static byte[] extendByteArray(byte abyte0[], byte abyte1[])
    {
        byte abyte2[] = new byte[abyte0.length];
        System.arraycopy(abyte0, 0, abyte2, 0, abyte0.length);
        abyte0 = new byte[abyte0.length + abyte1.length];
        System.arraycopy(abyte2, 0, abyte0, 0, abyte2.length);
        System.arraycopy(abyte1, 0, abyte0, abyte0.length - abyte1.length, abyte1.length);
        return abyte0;
    }

    public static int[] extendIntArray(int ai[], int ai1[])
    {
        int ai2[] = new int[ai.length];
        System.arraycopy(ai, 0, ai2, 0, ai.length);
        ai = new int[ai.length + ai1.length];
        System.arraycopy(ai2, 0, ai, 0, ai2.length);
        System.arraycopy(ai1, 0, ai, ai.length - ai1.length, ai1.length);
        return ai;
    }

    public static byte[] parseHexString(String s)
        throws Exception
    {
        if(s.length() % 2 != 0)
            throw new Exception("The String len must be even.");
        byte abyte0[] = new byte[s.length() / 2];
        for(int i = 0; i < s.length() / 2; i++)
            abyte0[i] = (byte)(Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16) & 0xff);

        return abyte0;
    }

    public static String toHexString(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; i < abyte0.length; i++)
            stringbuffer.append(toHexString(abyte0[i]));

        return stringbuffer.toString();
    }
}
