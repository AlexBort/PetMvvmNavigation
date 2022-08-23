package com.example.mvvmnavigation.for_dagger

import com.example.mvvmnavigation.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides

fun main() {
    /**
     * init of whole graph
     */
    val appComponent:AppComponent = DaggerAppComponent.create()
    /**
     * we can call create() ot builder() function.
     * If we need to pass some params (dependencies) into it
     * we'll call builder() if creation without params we'll call create()
     */
        // DaggerAppComponent.builder()
//        DaggerAppComponent.create()

    println("print property of Computer: ${appComponent.computerProperty}")
    println("print fun that returns Computer: ${appComponent.computer()}")
}

/**
 * ця анотація показує, що цей інтерфейс помічений, як компонент.
 * І саме з нього можна буде отримувати залежності.
 */

/**
 * але в цю анотацію потрібно якось ці параметри передати.
 * В анотацію @Component можна передати модулі
 */
@Component(modules = [AppModule::class])
interface AppComponent {
    fun passParamsInMainActivity(activity: MainActivity) // тобто ми будемо доставляти необхідні залежності цим методом в MainActivity
    fun computer(): Computer
    val computerProperty: Computer
}

@Module
object AppModule {

    @Provides
    fun provideComputer(processor: Processor,
                        ram: RAM,
                        motherboard: Motherboard): Computer {
        return Computer(
            processor = processor,
            motherboard = motherboard,
            ram = ram
        )
        /**
         * ми це зробили, але проблема у тому, що Computer знає,
         * як створюються компоненти, які ми передаємо йому в конструктор.
         * Краще, щоб ці залежності приходили ззовні
         */
    }

    @Provides
    fun provideRAM() = RAM()

    @Provides
    fun provideProcessor() = Processor()

    @Provides
    fun provideMotherBoard() = Motherboard()
}
