package study

fun getInitialSound(text: String): Char {
    val chs = initialSounds()
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

private fun initialSounds() =
    arrayOf('ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ')

data class InitialSoundAndOrigin(val first:Char, val origin: String)

class InitialSoundsComparator: Comparator<Char> {
    override fun compare(a: Char, b: Char): Int = when {
        a in initialSounds() && b in initialSounds() -> a.compareTo(b)
        a in initialSounds() -> -1
        b in initialSounds() -> 1
        else -> a.compareTo(b)
    }
}

fun main() {
    val names = listOf("하하하", "abcd", "aaaa", "bbbb", "zzz", "be", "ko", "한국", "미국", "하늘")
    val groups = names
        .map { s->InitialSoundAndOrigin(getInitialSound(s),s) }
        .groupBy { v->v.first }
        .toSortedMap(InitialSoundsComparator())
    println(groups)
}