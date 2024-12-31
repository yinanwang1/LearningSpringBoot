package com.yn.customer.client;

import java.nio.charset.Charset;

/**
 * Created by heqingpan on 17/8/10.
 */
public class MsgData {
    public static Charset charset = Charset.forName("UTF-8");
    public byte   version;
    public byte   status;
    public short  type;
    public int    length;
    public byte[] body;

    public void setBody(byte[] body) {
        this.body = body;
        this.length = body.length;
    }

    public void setBody(String body) {
        if(body==null) {
            this.body=null;
            this.length=0;
            return;
        }
        this.body = body.getBytes(charset);
        this.length = this.body.length;
    }

    public static int byte2int(byte[] byt, int index) {
        return (int) (byt[index + 3] & 0xff | (byt[index + 2] & 0xff) << 8 | (byt[index + 1] & 0xff) << 16 | (byt[index + 0] & 0xff) << 24);
    }

    public static short byte2short(byte[] byt, int index) {
        return (short) (byt[index + 1] & 0xff | (byt[index + 0] & 0xff) << 8);
    }

    public void decodeHeader(byte[] header) {
        assert header.length == 8;
        this.version = header[0];
        this.status = header[1];
        this.type = byte2short(header, 2);
        this.length = byte2int(header, 4);
    }

    public byte[] toBytes() {
        int l = 8;
        if (body != null && body.length > 0) {
            this.length = body.length;
            l += body.length;
        } else {
            this.length = 0;
        }
        byte[] result = new byte[l];
        result[0] = version;
        result[1] = status;
        result[2] = (byte) ((type >> 8) & 0xff);
        result[3] = (byte) (type & 0xff);
        result[4] = (byte) ((length >> 24) & 0xff);
        result[5] = (byte) ((length >> 16) & 0xff);
        result[6] = (byte) ((length >> 8) & 0xff);
        result[7] = (byte) (length & 0xff);
        if (body != null && body.length > 0) {
            for (int i = 0; i < body.length; i++) {
                result[i + 8] = body[i];
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s:\nversion:%d status:%X type:%d length:%d body:%s",
                this.getClass().getName(), version, status, type, length, body == null ? body : new String(body));
    }
}
