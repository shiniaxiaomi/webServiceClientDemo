package com.lyj;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class Main1 {
    //QName调用
    public static void main(String[] args) throws Exception {
        //创建一个连接（地址对应的就是webservice服务器中的地址）
        URL wsdlDocumentLocation = new URL("http://localhost/demo?wsdl");

        //第一个参数是wsdl文档中的<definitions>标签中的targetNamespace属性值
        //第二个参数是wsdl文档中的<definitions>标签中的name属性值
        QName serviceName = new QName("http://lyj.com/","DemoService");

        //Service创建视图
        //参数：
        //1.wsdlDocumentLocation - 使用说明书地址
        //2.serviceName - 服务名称
        Service service = Service.create(wsdlDocumentLocation , serviceName );

        //获取到调用的对象内容(传入接口即可)
        Demo port = service.getPort(Demo.class);

        //方法的调用
        String result = port.hello("hi");
        System.out.println(result);
    }
}
