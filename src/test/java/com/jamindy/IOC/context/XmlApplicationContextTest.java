package com.jamindy.IOC.context;

import com.jamindy.IOC.object.TestContextObject1;
import com.jamindy.IOC.object.TestContextObject2;
import com.jamindy.IOC.object.TestContextObject3;
import com.jamindy.IOC.object.TestContextObject4;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class XmlApplicationContextTest {

    ApplicationContext ctx;

    @Before
    public void setUp() throws Exception {
        ctx = new XmlApplicationContext(
                new String[] { "/context/XmlApplicationContext1.xml" });
    }

    @After
    public void tearDown() throws Exception {
        ctx = null;
    }

    @Test
    public void testGetBean() {

        TestContextObject1 obj1 = (TestContextObject1) ctx
                .getBean("test1");
        assertNotNull(obj1);
    }

    @Test
    public void testSingleton() {
        // test1是单态bean
        TestContextObject1 obj1 = (TestContextObject1) ctx
                .getBean("test1");
        TestContextObject1 obj2 = (TestContextObject1) ctx
                .getBean("test1");
        assertEquals(obj1, obj2);
        // test3不是单态bean
        TestContextObject1 obj3 = (TestContextObject1) ctx
                .getBean("test3");
        TestContextObject1 obj4 = (TestContextObject1) ctx
                .getBean("test3");
        assertFalse(obj3.equals(obj4));
    }

    @Test
    public void testConstructInjection() {
        TestContextObject1 obj1 = (TestContextObject1) ctx
                .getBean("test1");
        TestContextObject2 obj2 = (TestContextObject2) ctx
                .getBean("test2");
        assertNotNull(obj2);
        assertEquals(obj2.getName(), "jamindy");
        assertEquals(obj2.getAge(), 22);
        assertEquals(obj2.getObject1(), obj1);
    }

    @Test
    public void testAutowire() {

        TestContextObject3 obj1 = (TestContextObject3) ctx
                .getBean("test4");
        assertNotNull(obj1);
        TestContextObject1 obj2 = obj1.getObject1();
        System.out.println(obj2);
        assertNotNull(obj2);
        TestContextObject1 obj3 = (TestContextObject1) ctx
                .getBean("object1");
        assertEquals(obj2, obj3);
    }

    @Test
    public void testContainsBean() {
        boolean result = ctx.isExist("test1");
        assertTrue(result);
        result = ctx.isExist("test5");
        assertTrue(result);
        result = ctx.isExist("No exists");
        assertFalse(result);
    }

    @Test
    public void testLazyInit() {
        // test5是延迟加载的, 没有调用过getBean方法, 那么容器中就不会创建这个bean
        Object obj = ctx.getBeanWithoutCreate("test5");
        assertNull(obj);
        // System.out.println(obj);
        obj = ctx.getBean("test5");
        assertNotNull(obj);
        System.out.println(obj);
    }

    @Test
    public void testSetProperties() {
        TestContextObject3 obj1 = (TestContextObject3) ctx
                .getBean("test6");
        TestContextObject1 obj2 = (TestContextObject1) ctx
                .getBean("object1");
        assertEquals(obj1.getName(), "jamindy");
        assertEquals(obj1.getAge(), 22);
        assertEquals(obj1.getObject1(), obj2);
        TestContextObject4 obj4 = (TestContextObject4) ctx
                .getBean("test7");
        System.out.println((ArrayList<Object>) obj4.getList());
        System.out.println((HashSet<Object>) obj4.getSet());
        System.out.println((ArrayList<Object>) obj4.getRefTest());
    }
}