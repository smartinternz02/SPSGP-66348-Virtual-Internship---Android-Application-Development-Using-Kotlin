package singha.com.amit.singletons

object DecimalFormatter {
    fun format(num :Float?): String{
        return String.format("%.2f", num)
    }
}