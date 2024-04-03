package cn.topcheer.pms2.common.utils;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.SM4;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class OptUtil {

    private static ObjectMapper objectMapper;

    public OptUtil(ObjectMapper objectMapper) {
        OptUtil.objectMapper = objectMapper;
    }



    public static byte[] string2ByteArray(String data, String charsetName) throws UnsupportedEncodingException {
        if(data == null){
            return new byte[0];
        }
        Charset charset = Charset.forName(charsetName);
        CharsetEncoder encoder = charset.newEncoder();
        if (!encoder.canEncode(data)) {
            throw new IllegalArgumentException(
                    "string " + data + " contains characters that cannot be encoded to " + charset.displayName());
        }
        return data.getBytes(charsetName);
    }

    public static String byteArray2String(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        return new String(bytes, charsetName);
    }



    /**
     * base64 编码
     * @param credentialsString 要编码的字符串
     * @param charsetName
     * @return
     */
    public static String base64Encode(String credentialsString, String charsetName) throws UnsupportedEncodingException {
        byte[] encodedBytes = base64Encode(string2ByteArray(credentialsString, charsetName));
        return byteArray2String(encodedBytes, charsetName);
    }

    public static byte[] base64Encode(byte[] data){
        return Base64.getEncoder().encode(data);
    }


    /**
     *
     * @param params key1, value1, key2, value2 的形式，生成json
     * @return
     */
    public static String generateSimpleJson(String... params) throws JsonProcessingException {
        Map<String, String> result = new HashMap<>();
        for(int i = 0; i < params.length - 1; i += 2){
            if(StringUtils.isNotBlank(params[i])){
                result.put(params[i], params[i+1]);
            }
        }
        return objectMapper.writeValueAsString(result);
    }


    public static long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

    public static String md5(String str) {
        return DigestUtil.md5Hex(str);
    }


    /**
     * 强制转成128位密钥
     * @param data
     * @param secretKey
     * @return
     */
    public static byte[] sm4Encrypt(byte[] data, byte[] secretKey){
        byte[] key = new byte[16];
        System.arraycopy(secretKey, 0, key, 0, Math.min(16, secretKey.length));
        return new SM4(key).encrypt(data);
    }


    public static void main(String[] args) throws Exception {
        String s = OptUtil.class.getPackage().getName();
//        String data = "ohqeo8irnchimurujimuxu9ece48thuc9m984uncm9orocicmmxb0nn o";
//        String secretKey = "2828282828282828";
//
//        String s2 = szydSm4Encrypt(data, secretKey);
//        String s1 = byteArray2String(base64Encode(sm4Encrypt(string2ByteArray(data, "utf-8"), string2ByteArray(secretKey, "utf-8"))), "utf-8");
//
//
//        secretKey = "020mcrtumcimjxiojijmxo,x,ekx,pp,koekpofkpekfpokforkofkoekforkofrkf";
//        String s3 = byteArray2String(base64Encode(sm4Encrypt(string2ByteArray(data, "utf-8"), string2ByteArray(secretKey, "utf-8"))), "utf-8");
//        String s4 = szydSm4Encrypt(data, secretKey);
//
//        secretKey = "123";
//        String s5 = byteArray2String(base64Encode(sm4Encrypt(string2ByteArray(data, "utf-8"), string2ByteArray(secretKey, "utf-8"))), "utf-8");
//        String s6 = szydSm4Encrypt(data, secretKey);

//
//
//        byte[] b = new byte[9];

        //byte[] b = string2ByteArray("你好", "utf-8");


//
//        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
//        evaluationContext.setMethodResolvers(Collections.singletonList(new StrictMethodResolver()));
//        evaluationContext.setConstructorResolvers(new ArrayList<>());
//        StandardTypeLocator typeLocator = new StandardTypeLocator();
//        typeLocator.registerImport("cn.topcheer.datacenter.apimanager.api.util");
//        evaluationContext.setTypeLocator(typeLocator);
//
//        for(Method method : OptUtil.class.getDeclaredMethods()){
//            evaluationContext.registerFunction(method.getName(), method);
//        }
//
//        Expression exp = new SpelExpressionParser().parseExpression(
//                "T(OptUtil).byteArray2String(T(OptUtil).base64Encode(T(OptUtil).sm4Encrypt(T(OptUtil).string2ByteArray('ohqeo8irnchimurujimuxu9ece48thuc9m984uncm9orocicmmxb0nn o', 'utf-8'), T(OptUtil).string2ByteArray('2828282828282828', 'utf-8'))), 'utf-8')"
//        );
//
//        Object o = exp.getValue(evaluationContext);
//
//        Expression exp2 = new SpelExpressionParser().parseExpression(
//                "#byteArray2String(#base64Encode(#sm4Encrypt(#string2ByteArray('ohqeo8irnchimurujimuxu9ece48thuc9m984uncm9orocicmmxb0nn o', 'utf-8'), #string2ByteArray('2828282828282828', 'utf-8'))), 'utf-8')"
//        );
//
//        Object o2 = exp2.getValue(evaluationContext);

    }

}
