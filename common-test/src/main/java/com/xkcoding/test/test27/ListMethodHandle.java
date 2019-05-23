package com.xkcoding.test.test27;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * datetime 2019/5/22 14:18
 *
 * @author sin5
 */
public class ListMethodHandle<T> {

    private Object obj;
    private Class objClass;
    private Class[] argsClass;
    private Object[] args;
    private String methodName;
    private boolean virtual;

    public ListMethodHandle(Object obj, String methodName, Object... args) {
        this.obj = obj;
        this.objClass = obj.getClass();
        int length = args.length;
        this.argsClass = new  Class[length];
        this.args = new Object[length];
        for (int i = 0; i < length; i++) {
            this.argsClass[i] = args[i].getClass();
            this.args[i] = args[i];
        }
        this.methodName = methodName;
        this.virtual = true;
    }

    public ListMethodHandle(Class objClass, String methodName, Object... args) {
        this.objClass = objClass;
        int length = args.length;
        this.argsClass = new Class[length];
        this.args = new Object[length];
        for (int i = 0; i < length; i++) {
            this.args[i] = args[i];
            this.argsClass[i] = args[i].getClass();
        }
        this.methodName = methodName;
    }

    @SuppressWarnings({"unchecked", "JavaLangInvokeHandleSignature"})
    public List<T> invoke() {
        List<T> result = new ArrayList<>();
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType mt = MethodType.methodType(List.class, argsClass);
        try {
            List list;
            if (virtual) {
                MethodHandle mh = lookup.findVirtual(objClass, methodName, mt);
                int length = args.length;
                switch (length) {
                    case 0: list = (List) mh.invoke(obj); break;
                    case 1: list = (List) mh.invoke(obj, args[0]); break;
                    case 2: list = (List) mh.invoke(obj, args[0], args[1]); break;
                    case 3: list = (List) mh.invoke(obj, args[0], args[1], args[2]); break;
                    case 4: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3]); break;
                    case 5: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4]); break;
                    case 6: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5]); break;
                    case 7: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6]); break;
                    case 8: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]); break;
                    case 9: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[8], args[8]); break;
                    case 10: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[8], args[8], args[9]); break;
                    default: list = Collections.emptyList();
                }
            } else {
                MethodHandle mh = lookup.findStatic(objClass, methodName, mt);
                list = (List) mh.invokeWithArguments(args);
            }
            for (Object o : list) {
                result.add((T) o);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        ListMethodHandle<String> hstatic = new ListMethodHandle<>(Test.class, "list", "1");
        ListMethodHandle<String> hvirtual = new ListMethodHandle<>(new Test(), "listVirtual");
        ListMethodHandle<String> hvirtual1 = new ListMethodHandle<>(new Test(), "listVirtual", "4", "6");
        System.out.println(hstatic.invoke());
        System.out.println(hvirtual.invoke());
        System.out.println(hvirtual1.invoke());
    }


    public static class Test {
        public static List<String> list(String start) {
            return Arrays.asList(start, "2", "3");
        }

        public List<String> listVirtual() {
            return Arrays.asList("4", "5", "6");
        }

        public List<String> listVirtual(String start, String end) {
            return Arrays.asList(start, "5", end);
        }
    }
}
