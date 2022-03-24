import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пометки "COM" это примерные вопросы которые могут возникнуть и примерные ответы которые можно ответить.
 *
 * Как добавить новый пуш с новыми фильтрами?
 * 1. Реализацию новых фильтров в класс Filters
 * 2. Сборку нового типа пуша в класс FactoryPush
 * 3. Примените необходимые фильтры на пуш в классе FilterPush
 *
 * Код написан так, чтобы без особых проблем переделать его в много поточный.
 *
 * Долматов Даниил КБ401 17.02.2022 2:00
 */


fun main(){
    PushFilteringSystem().mainLoop()
}

/**
 * Класс реализующий логику сборки пушей.
 * По своей сути состоит из разного рода заглушек, поэтому не соответствует всяким SRP.
 * Но реализует 3 основные системы логики.
 * 1. Заглушка логики ввода данных (readerSystemParams, readerCountPush, readerPushData)
 *      Второй и третий методы должны быть единым целым:
 *          Бесконечный цикл || Что-то, что ждет сигнал, о том что пришел новый пуш
 *          ему не нужно грузить куда-либо данные, они уже должны приходить загружеными
 *          это система для фильтрации, а не генерации пушей.
 * 2. Заглушка логики вывода (outputPush, filtrationPush)
 *      Второй метод скорее заглушка представления и хранения, но в данном контексте он связан только с выводом.
 * 3. Логика работы системы с пушами(mainLoop]):
 *      1. Загрузить параметры системы.
 *      2. Поднять фильтры для пушей.
 *      3. Запустить логику ввода.
 *      4. Запустить логику вывода.
 *
 * P.s. По хорошему все должно быть на колбеках, но это кодфорсес.
 */
class PushFilteringSystem(){
    private var systemParams: SystemParams? = null
    private val factoryPush: FactoryPush = FactoryPush()
    private val filterPush: FilterPush = FilterPush()
    private val notFilteringPush: MutableList<String> = mutableListOf()

    fun mainLoop(){
        // 1. Загрузить параметры системы.
        readerSystemParams()

        // 2. Поднять фильтры для пушей.
        val filter = Filters()
        filter.systemParams = systemParams!!
        filterPush.filters = filter

        // 3. Запустить логику ввода.
        val countPush: Int = readerCountPush()
        readerPushData(countPush)

        // 4. Запустить логику вывода.
        outputPush()
    }

    /**
     * COM
     * Что это за хардкод?
     * Очевидно что данный класс уже будет храниться в системе, или его нужно будет собирать,
     * либо он придет сиреализованным => нет смысла его реализовывать на должном уровне, это заглушка.
     */
    private fun readerSystemParams(){
        var time: Long = 0L
        var age: Int = 0
        var gender: String = ""
        var osVersion: Int = 0
        var xCord: Float = 0f
        var yCord: Float = 0f

        for (i in 1..6){
            val param = readLine()!!.toString()
            val nameParam = param.substringBefore(' ')
            if (nameParam == "time"){
                time = param.substringAfter(' ').toLong()
            } else if (nameParam == "age"){
                age = param.substringAfter(' ').toInt()
            } else if (nameParam == "gender"){
                gender = param.substringAfter(' ')
            } else if (nameParam == "os_version"){
                osVersion = param.substringAfter(' ').toInt()
            } else if (nameParam == "x_coord"){
                xCord = param.substringAfter(' ').toFloat()
            } else if (nameParam == "y_coord"){
                yCord = param.substringAfter(' ').toFloat()
            }
        }
        systemParams = SystemParams(time, age, gender, osVersion, xCord, yCord)
    }

    private fun readerCountPush(): Int{
        return readLine()!!.toInt()
    }

    /**
     * Метод читает данные, собирает их как пуш, отправляет фильтроваться
     *
     * COM
     * Где декомпозиция?
     * Класс заглушек, декомпозиция чего, того что должно было прийти нормально, а пришло строчками в консоли?
     */
    private fun readerPushData(countPush: Int){
        for (i in 1..countPush){
            val countPushParams: Int = readLine()!!.toInt()
            var typePush: String = ""
            val pushData: HashMap<String, String> = HashMap<String, String> ()

            for (j in 1..countPushParams){

                val paramPush: String = readLine()!!.toString()
                val nameParam: String = paramPush.substringBefore(' ')

                if (nameParam == "type"){
                    typePush = paramPush.substringAfter(' ')
                } else {
                    pushData[nameParam] = paramPush.substringAfter(' ')
                }
            }
            val push: Push = factoryPush.getPushByType(typePush, pushData)
            filtrationPush(push)
        }
    }

    /**
     * Очевидно что система вывода не может выглядеть подобным образом.
     * Скорее всего мы должны были сделать очередь показываемых сообщений.
     */
    private fun outputPush(){
        if (notFilteringPush.isNotEmpty()){
            for (textPush in notFilteringPush){
                println(textPush)
            }
        } else {
            println(-1)
        }
    }

    /**
     * Функция собирает все названия не отфильтрованных пушей.
     *
     * Тут можно расширить если задание будет просить выводить пуши в две группы или что-то типа.
     */
    private fun filtrationPush(push: Push){
        val pushMustDisplayed: Boolean = filterPush.filtrating(push)
        if (pushMustDisplayed){
            notFilteringPush.add(push.textPush)
        }
    }
}

/** Класс собирает экземпляры пушей из данных.
 *
 * СOM
 * Почему не when?
 * Код длиннее получается.
 * Почему не метод фабрики, а класс?
 * SRP
 */
class FactoryPush() {

    fun getPushByType(typePush: String, pushData: HashMap<String, String>): Push {
        fun getAge() = pushData["age"]!!.toInt()
        fun getTime() = pushData["expiry_date"]!!.toLong()
        fun getGender() = pushData["gender"]!!.toString()
        fun getRadius() = pushData["radius"]!!.toInt()
        fun getOSVersion() = pushData["os_version"]!!.toInt()
        fun getCordX() = pushData["x_coord"]!!.toFloat()
        fun getCordY() = pushData["y_coord"]!!.toFloat()

        val push: Push = Push(typePush, pushData["text"].toString())

        if (typePush == "LocationPush") {
            push.xCord = getCordX()
            push.yCord = getCordY()
            push.radius = getRadius()
            push.time = getTime()
        } else if (typePush == "AgeSpecificPush") {
            push.age = getAge()
            push.time = getTime()
        } else if (typePush == "TechPush") {
            push.osVersion = getOSVersion()
        } else if (typePush == "LocationAgePush") {
            push.xCord = getCordX()
            push.yCord = getCordY()
            push.radius = getRadius()
            push.age = getAge()
        } else if (typePush == "GenderAgePush") {
            push.gender = getGender()
            push.age = getAge()
        } else if (typePush == "GenderPush") {
            push.gender = getGender()
        }

        return push
    }
}


class FilterPush(){
    lateinit var filters: Filters

    /**
     * Функция по названию пуша применяет к нему фильтры.
     *
     * COM
     * Почему не что-то по типу свича?
     * Выше ^
     */
    fun filtrating(push: Push): Boolean{

        if (push.typePush == "LocationPush"){
            return filters.locationFilter(push.xCord, push.yCord, push.radius) &&
                    filters.timeFilter(push.time)
        } else if (push.typePush == "AgeSpecificPush"){
            return filters.ageFilter(push.age) &&
                    filters.timeFilter(push.time)
        } else if (push.typePush == "TechPush"){
            return filters.techFilter(push.osVersion)
        } else if (push.typePush == "LocationAgePush"){
            return filters.locationFilter(push.xCord, push.yCord, push.radius) &&
                    filters.ageFilter(push.age)
        } else if (push.typePush == "GenderAgePush"){
            return filters.ageFilter(push.age) &&
                    filters.genderFilter(push.gender)
        } else if (push.typePush == "GenderPush"){
            return filters.genderFilter(push.gender)
        }

        return false
    }
}

/** Класс параметров системы.
 *
 * COM
 * Почему не object, синголтон же все таки?
 * Можно и object, но я хотел дата класс поюзать.
 * А вообще было бы прекрасно если бы дата класс можно было делать object.
 */
data class SystemParams(
    val time: Long,
    val age: Int,
    val gender: String,
    val osVersion: Int,
    val xCord: Float,
    val yCord: Float){
}

/** Класс, который содержит в себе все параметры которые могут быть в пуше.
 *
 * COM
 * Почему бы не сделать класс пуша абстрактным, а другие типы пушей его наследниками?
 * Можно и так, но это не совсем рационально из-за разрастания кодовой базы, тем более
 * если количество возможных параметров в пуше будет слишком большим, то скорее всего
 * это результат неверного дизайна, значит или в приложении слишком много пушей, либо
 * они перегружены. Хороший пример идиотского дизайна пушей это Instagram, Vk с их
 * безмерным количеством ненужной информации.
 */
class Push(
    val typePush: String,
    val textPush: String,
){
    var time: Long = 0L
    var age: Int = 0
    var gender: String = ""
    var osVersion: Int = 0
    var xCord: Float = 0f
    var yCord: Float = 0f
    var radius: Int = 0
}

/**
 * Класс с фильтрами, которые можно применять для:
 *  1. Пушей.
 *
 * COM
 * Почему не фильтр == класс?
 * А зачем? Фильтры пушей не большие, так же они могут использоваться где-то еще.
 * Почему бы просто не передавать класс пуша?
 * А зачем фильтрам знать о деталях реализации пуша? Системные параметры они знать могут, ибо по ним они и фильтруют.
 */
class Filters(){
    lateinit var systemParams: SystemParams

    fun ageFilter(age: Int): Boolean{
        return systemParams.age >= age
    }

    fun locationFilter(xCordPush: Float, yCordPush: Float, radiusPush: Int): Boolean{
        val xDest: Float = systemParams.xCord - xCordPush
        val yDest: Float = systemParams.yCord - yCordPush
        return sqrt(xDest * xDest + yDest * yDest) <= radiusPush
    }

    fun techFilter(osVersionPush: Int): Boolean{
        return systemParams.osVersion <= osVersionPush
    }

    fun timeFilter(timePush: Long): Boolean{
        return systemParams.time <= timePush //
    }

    fun genderFilter(genderPush: String): Boolean{
        return systemParams.gender == genderPush
    }
}


