package org.example

fun conditional1() {

    val day = 3
    var text = ""

    if(day == 3) text = "TUE"
    else if (day == 4) text = "WED"
    else text = "SUN"
}

fun conditional2() {

    val day = 3
    val text = if(day == 3) "MON" else if (day == 4) "TUE" else "SUN"
}

fun conditional3() {
    val day = 3
    val text = when(day) {
        3 -> "MON"
        4 -> "TUE"
        else -> "SUN"
    }
}

