package com.lyj;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2019/9/16.
 */
public class Main2 {

    //HttpURLConnection调用
    public static void main(String[] args) throws Exception {
        //1：创建服务地址(soap:address标签下的location属性)
        URL url = new URL("http://localhost/demo");
        //2：打开到服务地址的一个连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //3：设置连接参数
        //3.1设置发送方式：POST必须大写
        connection.setRequestMethod("POST");
        //3.2设置数据格式：Content-type
        connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
        //3.3设置输入输出，新创建的connection默认是没有读写权限的，
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //4：组织SOAP协议数据，发送给服务端
        String soapXML = getXML("hi");//创建soapXml消息体
        OutputStream os = connection.getOutputStream();
        os.write(soapXML.getBytes());

        //5：接收服务端的响应
        int responseCode = connection.getResponseCode();
        if(200 == responseCode){//表示服务端响应成功
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String temp = null;

            while(null != (temp = br.readLine())){
                sb.append(temp);
            }

            System.out.println(sb.toString());

            is.close();
            isr.close();
            br.close();
        }
        os.close();
    }


    /**
     <?xml version="1.0" ?>
     <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
         <S:Body>
             <ns2:hello xmlns:ns2="http://lyj.com/">
                <arg0>北京</arg0>
             </ns2:hello>
         </S:Body>
     </S:Envelope>
     */
    public static String getXML(String param){
        String soapXML =
                "<?xml version=\"1.0\" ?>  \n" +
                "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">  \n" +
                "    <S:Body>\n" +
                "        <ns2:hello xmlns:ns2=\"http://lyj.com/\">\n" +
                "            <arg0>"+param+"</arg0>\n" +
                "        </ns2:hello>  \n" +
                "    </S:Body>  \n" +
                "</S:Envelope> ";

        return soapXML;
    }

}
