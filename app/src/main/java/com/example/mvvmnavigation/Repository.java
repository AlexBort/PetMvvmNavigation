package com.example.mvvmnavigation;

import java.util.ArrayList;
import java.util.List;

public class Repository {

  private List<String> testList = new ArrayList<>();

  public Repository() {
    testList.add("sadasdf");
    testList.add("sadasdf");
    testList.add("sadasdf");
    testList.add("sadasdf");
    testList.add("sadasdf");
    testList.add("sadasdf");
    testList.add("sadasdf");
    testList.add("sadasdf");
    testList.add("sadasdf");
    testList.add("sadasdf");
    testList.add("sadasdf");
  }


  public List<String> getTestList() {
    return testList;
  }
}
