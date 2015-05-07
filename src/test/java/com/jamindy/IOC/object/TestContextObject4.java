package com.jamindy.IOC.object;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestContextObject4 {
    private List<Object> list;
    private Set<Object> set;
    private TestContextObject3 test6;
    private List<Object> refTest;

    public List<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }

    public List<Object> getRefTest() {
        return refTest;
    }

    public void setRefTest(ArrayList<Object> refTest) {
        this.refTest = refTest;
    }

    public Set<Object> getSet() {
        return set;
    }

    public void setSet(HashSet<Object> set) {
        this.set = set;
    }

    public TestContextObject3 getTest6() {
        return test6;
    }

    public void setTest6(TestContextObject3 test6) {
        this.test6 = test6;
    }

    public TestContextObject4() {

    }
}
