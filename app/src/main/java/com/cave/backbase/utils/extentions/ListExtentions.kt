package com.cave.backbase.utils.extentions

fun List<com.cave.backbase.data.model.City>.prefixSearch(
    fromIndex: Int = 0,
    toIndex: Int = size,
    prefix: String,
    ignoreCase: Boolean = true
): List<com.cave.backbase.data.model.City>? {
    rangeCheck(size, fromIndex, toIndex)

    var low = fromIndex
    var high = toIndex - 1
    var leftIndex = -1
    var rightIndex = -1

    while (low <= high) {
        val mid = (low + high).ushr(1) // safe from overflows
        val midVal = get(mid)
        if (prefix.compareTo(midVal.city, ignoreCase = ignoreCase) < 0) {
            if (midVal.city.startsWith(prefix, ignoreCase = ignoreCase)) {
                leftIndex = mid
                rightIndex = mid
                while (leftIndex > 1) {
                    if (this[leftIndex - 1].city.startsWith(prefix, ignoreCase = ignoreCase)) {
                        leftIndex--
                    } else {
                        break
                    }
                }
                while (rightIndex < this.size) {
                    if (this[rightIndex + 1].city.startsWith(prefix, ignoreCase = ignoreCase)) {
                        rightIndex++
                    } else {
                        break
                    }
                }
                return if (leftIndex == rightIndex) {
                    this.subList(leftIndex, leftIndex + 1)
                } else {
                    this.subList(leftIndex, rightIndex)
                }
            } else {
                high = mid
                if (low == high) {
                    return this.subList(leftIndex, rightIndex)
                }
            }
        } else if (prefix > midVal.city) {
            low = mid + 1
            if (low == high) {
                return null
            }
        } else {
            return this.subList(mid, mid)
        }
    }
    return if (leftIndex >= 0 && rightIndex >= 0) {
        this.subList(leftIndex, rightIndex)
    } else {
        null
    }
}

private fun rangeCheck(size: Int, fromIndex: Int, toIndex: Int) {
    when {
        fromIndex > toIndex -> throw IllegalArgumentException("fromIndex ($fromIndex) is greater than toIndex ($toIndex).")
        fromIndex < 0 -> throw IndexOutOfBoundsException("fromIndex ($fromIndex) is less than zero.")
        toIndex > size -> throw IndexOutOfBoundsException("toIndex ($toIndex) is greater than size ($size).")
    }
}
