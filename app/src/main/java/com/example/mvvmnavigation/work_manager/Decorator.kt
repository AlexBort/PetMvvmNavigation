package com.example.mvvmnavigation.work_manager

class Decorator(var object_: Object):A {

    override fun f1() {
        object_.f1()
    }

    override fun f2() {
        object_.f2()
        TODO("Not yet implemented")
    }

}