package com.example.testndk;

/**
 * Created by Kong on 2018/2/12.
 */

public class HelloWorld {

    // 预加载 hello 动态库
    // hello 是在 app/gradle 里面配置的动态库名字
    static {
        System.loadLibrary("hello");
    }

    // 定义原生方法，名字就叫 hello，因为还没有实现，所以报错，先不理会，它的实现是在 C++ 文件当中实现
    public native String hello();

    // 要先编译，生成 class 文件，才能生成 header 文件

}
