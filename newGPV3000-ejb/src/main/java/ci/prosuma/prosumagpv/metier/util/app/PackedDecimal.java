package ci.prosuma.prosumagpv.metier.util.app;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 




// Referenced classes of package tr.com.pos.util:
//            Util

public class PackedDecimal
{

    public PackedDecimal(byte abyte0[])
    {
        baData = abyte0;
    }

    public PackedDecimal(byte abyte0[], int i, int j)
    {
        baData = new byte[j];
        System.arraycopy(abyte0, i, baData, 0, j);
    }

    public final byte[] get()
    {
        return baData;
    }

    public final byte byteValue()
    {
        if(baData == null || baData != null && baData.length == 0)
            return 0;
        if(baData.length > 2 || baData.length < 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Can't unpack a PD with length ").append(baData.length).append(" to a byte").toString());
        if(intValue() > 127)
            throw new IllegalArgumentException("This PD value is greather than the byte.");
        byte byte0 = 0;
        for(int i = 0; i < baData.length; i++)
        {
            if((baData[i] & 0xf0) >> 4 < 10)
            {
                byte0 *= 10;
                byte0 += (baData[i] & 0xf0) >> 4;
            }
            if((baData[i] & 0xf) < 10)
            {
                byte0 *= 10;
                byte0 += baData[i] & 0xf;
            }
        }

        return byte0;
    }

    public final int intValue()
    {
        if(longValue() > 0x7fffffffL)
            throw new IllegalArgumentException("This PD value is greather than the int.");
        else
            return unpackToInteger();
    }

    public final short shortValue()
    {
        if(longValue() > 32767L)
            throw new IllegalArgumentException("This PD value is greather than the short.");
        else
            return unpack2ToShort();
    }

    private final short unpack2ToShort()
    {
        if(baData == null || baData != null && baData.length == 0)
            return 0;
        if(baData.length > 2 || baData.length < 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Can't unpack a PD with length ").append(baData.length).append(" to a byte").toString());
        short word0 = 0;
        for(int i = 0; i < baData.length; i++)
        {
            if((baData[i] & 0xf0) >> 4 < 10)
            {
                word0 *= 10;
                word0 += (baData[i] & 0xf0) >> 4;
            }
            if((baData[i] & 0xf) < 10)
            {
                word0 *= 10;
                word0 += baData[i] & 0xf;
            }
        }

        return word0;
    }

    private final int unpackToInteger()
    {
        if(baData == null || baData != null && baData.length == 0)
            return 0;
        if(baData.length > 4 || baData.length < 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Can't unpack a PD with length ").append(baData.length).append(" to an int").toString());
        int i = 0;
        for(int j = 0; j < baData.length; j++)
        {
            if((baData[j] & 0xf0) >> 4 < 10)
            {
                i *= 10;
                i += (baData[j] & 0xf0) >> 4;
            }
            if((baData[j] & 0xf) < 10)
            {
                i *= 10;
                i += baData[j] & 0xf;
            }
        }

        return i;
    }

    public final long longValue()
    {
        if(baData == null || baData != null && baData.length == 0)
            return 0L;
        if(baData.length > 9 || baData.length < 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Can't unpack a PD with length ").append(baData.length).append(" to an int").toString());
        long l = 0L;
        for(int i = 0; i < baData.length; i++)
        {
            if((baData[i] & 0xf0) >> 4 < 10)
            {
                l *= 10L;
                l += (baData[i] & 0xf0) >> 4;
            }
            if((baData[i] & 0xf) < 10)
            {
                l *= 10L;
                l += baData[i] & 0xf;
            }
        }

        return l;
    }

    public static PackedDecimal parse(byte byte0)
    {
        return new PackedDecimal(pack(Util.decimalToString(Byte.toString(byte0), 4), 2));
    }

    public static PackedDecimal parse(int i)
    {
        return new PackedDecimal(pack(Util.decimalToString(Integer.toString(i), 8), 4));
    }

    public static PackedDecimal parse(long l)
    {
        return new PackedDecimal(pack(Util.decimalToString(Long.toString(l), 16), 8));
    }

    public static void main(String args[])
    {
    }

    private static byte[] pack(String s, int i)
    {
        byte abyte0[] = new byte[i];
        for(int j = 0; j < i; j++)
            if(j % 2 == 0)
            {
                abyte0[i - j - 1] = (byte)Integer.parseInt(s.substring(j, j + 1));
            } else
            {
                abyte0[i - j - 1] = (byte)(abyte0[j] * 16);
                abyte0[i - j - 1] = (byte)(abyte0[j] + (byte)Integer.parseInt(s.substring(j, j + 1)));
            }

        return abyte0;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; i < baData.length; i++)
        {
            if((baData[i] & 0xf0) >> 4 < 10)
                stringbuffer.append((baData[i] & 0xf0) >> 4);
            if((baData[i] & 0xf) < 10)
                stringbuffer.append(baData[i] & 0xf);
        }

        return stringbuffer.toString();
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof PackedDecimal)
        {
            if(((PackedDecimal)obj).baData.length != baData.length)
                return false;
            for(int i = 0; i < baData.length; i++)
                if(((PackedDecimal)obj).baData[i] != baData[i])
                    return false;

            return true;
        } else
        {
            return false;
        }
    }

    private byte baData[];
}
