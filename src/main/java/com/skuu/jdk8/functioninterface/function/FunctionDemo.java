package com.skuu.jdk8.functioninterface.function;

import com.skuu.jdk8.functioninterface.OrderOpt;
import com.skuu.jdk8.functioninterface.Req;
import com.skuu.jdk8.functioninterface.Res;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @description 使用模板
 * @create 2026-02-25 14:41
 **/
public class FunctionDemo {

    public static void main(String[] args) {
        List<Req> reqs = new ArrayList<>();
        reqs.add(new Req().setName("dd1"));
        reqs.add(new Req().setName("dd2"));
        reqs.add(new Req().setName("dd3"));
        OrderOpt orderOpt = new OrderOpt();
        List<Res> res = orderOpt.add(reqs, FunctionDemo::singleTrans);
        System.out.println(res);
    }

    public static Res singleTrans(Req a) {
        String name = a.getName();
        Res res = new Res().setName(name);
        if ("dd2".equals(name)) {
            res.setDesc("经过transed 重名");
        } else {
            res.setDesc("经过transed 不重名");
        }
        System.out.println("转换成功");
        return res;
    }
}
