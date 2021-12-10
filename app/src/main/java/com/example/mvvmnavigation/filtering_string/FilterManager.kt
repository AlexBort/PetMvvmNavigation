package com.example.mvvmnavigation.filtering_string


fun main() {
    FilterManager().filterStringList()
}

class FilterManager {

    fun filterStringList(){
        val list = StringListForFiltering.list
        var filteredList = ArrayList<String>()
       for (i in list.indices){
           val str = list[i].filterToPureString()
           filteredList.add(str)
       }

        println(filteredList.toString())
//        val string:String
    }
}