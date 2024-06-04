package org.example

fun makeSqr() {
    val nums = mutableListOf(1,5,2, 4, 2, 3)

//    val ops = mutableListOf<Int>()
//    for (itm in nums) {
//        ops.add(itm * itm)
//    }
//    println(ops)

    val ops = nums.map { itm -> itm * itm }
    println(ops)

    // op = [1, 25, 4, 16, 4, 9]
}

fun sumElements() {
    val nums = mutableListOf(1, 5, 2, 2)
//    val op = nums.reduce { acc, i -> acc + i }
    val op = nums.fold(0) { acc, i -> acc + i }
    println(op)
    // op = 10
}

fun maxElement() {
    val nums = mutableListOf(1, 5, 2, 4, 2, 30)
    nums.sortDescending()
    println(nums[0])
}

fun checkFlags() {
    data class Validation(
        val field: String,
        val isValid: Boolean
    )

    val res = arrayOf(
        Validation("userName", true),
        Validation("password", true),
        Validation("fullName", true),
        Validation("dob", true)
    )

    val vlSts = res.all { itm -> itm.isValid }

    val txt = if(vlSts) "Form Is Valid" else "form is invalid"
    println(txt)

    // you need to tell form in valid of not
}