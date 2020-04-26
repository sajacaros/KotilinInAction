package study

fun getInitialSound(text: String): Char {
    val chs = arrayOf('ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ')
    if (text.isNotEmpty()) {
        val chName = text[0]
        if (chName.toInt() >= 0xAC00) {
            val uniVal = chName.toInt() - 0xAC00
            val cho = (uniVal - uniVal % 28) / 28 / 21
            return chs[cho]
        } else if(chName in 'A'..'z') {
            return chName;
        }
        // todo 2020.04.24
        throw RuntimeException("plz, alphabet or korean.")
    }
    // todo 2020.04.24
    throw RuntimeException("text is empty.")
}

data class FistAndOrigin(val first:Char, val origin: String)

fun main() {
    val names = listOf("abcd", "aaaa", "bbbb", "zzz", "be", "ko", "한국", "미국", "하늘")
    val groups = names.asSequence()
        .map { s->FistAndOrigin(getInitialSound(s),s) }
        .groupBy { v->v.first }
        .toSortedMap()
    println(groups)
}