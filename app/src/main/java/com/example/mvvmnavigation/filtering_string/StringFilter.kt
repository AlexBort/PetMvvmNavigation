package com.example.mvvmnavigation.filtering_string

fun String.filterToPureString():String{
    return this.toLowerCase().filter {
        it.isLetter()
    }
}
fun List<String>.filter(){

}
