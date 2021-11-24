package com.example.mvvmnavigation.coroutine_viewModel_scope

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class StandartViewModel : ViewModel() {

    init {
//        viewModelScope.launch {  } // вот так мы можем запустить корутину
        viewModelScope.coroutineContext.job
        viewModelScope.launch(Dispatchers.Default) {
            delay(5000)
            /** функция delay - это suspend функция,
            тоесть она мб запущена,а потом приостановлена
             */
        }


        viewModelScope.launch { } // этот кусок кода выполнится на основном потоке,
        // потому что viewModelScope относится к MainThread

//        withContext()

        val job: Job = viewModelScope.launch { // этот кусок будет запускаться на MainThread-e
            delay(1000) // также основной поток. Потому что в этом месте мы еще ничего не переопределяем.
            // Соответственно, мы наследуем поток родителя

            val result = withContext(Dispatchers.Default) { // но вот этот блок имеет переопределенный контекст

                    val part1 = async {
                        delay(1000)
                        return@async "part1 done"
                    }

                    val part2 = async {
                        delay(2000)
                        return@async "part2 done"
                    }

                    val part3 = async {
                        delay(3000)
                        return@async "part3 done"
                    }

                    val result1 = part1.await()
                    val result2 = part2.await()
                    val result3 = part3.await()
                    // await дает возможность выполниться задачи до конца, не блокируя поток
                    // тоесть поток заснет, пока не выволнится результат каждого async-a
                    // благодаря этому есть возможность вернуть окончательный результат

                    return@withContext "$result1\n$result2\n$result3"
                }
        }
    }
}