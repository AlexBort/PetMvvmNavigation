package com.example.mvvmnavigation.for_dagger

import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * ця анотація показує, що цей інтерфейс помічений, як компонент.
 * І саме з нього можна буде отримувати залежності.
 */
@Component
/**
 * але в цю анотацію потрібно якось ці параметри передати.
 * В анотцію @Component можна передати модулі
 */
interface AppComponent {
}

@Module
object AppModule {

    @Provides
    fun provideComputer(): Computer {
        return Computer(
            processor = Processor(),
            motherboard = Motherboard(),
            ram = RAM()
        )
    }
}
