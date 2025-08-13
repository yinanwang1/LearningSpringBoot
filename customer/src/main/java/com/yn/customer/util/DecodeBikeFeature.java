package com.yn.customer.util;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qiji.dto.common.bike.BikeFeature;

import java.io.*;

/**
 * DecodeBikeFeature
 * 将redis中保存的内容二进制下载到桌面
 * 找到qiji项目中的对象，放到此项目中，包括类的包名。
 * 恢复保存之前的内容
 *
 * @author arthurwang
 * @version 1.0
 * 2025/7/21 17:02
 **/
public class DecodeBikeFeature {

    public static void main(String[] args)  {
        File file = new File("/Users/arthurwang/Desktop/bikeFuture");
        try (FileInputStream fileInputStream = new FileInputStream( file)) {
            byte[] bytes = fileInputStream.readAllBytes();
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            BikeFeature bikeFeature = (BikeFeature) objectInputStream.readObject();

            ObjectMapper objectMapper = new ObjectMapper();
            String josn = objectMapper.writeValueAsString(bikeFeature);

            System.out.println(bikeFeature);
            System.out.println(josn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
