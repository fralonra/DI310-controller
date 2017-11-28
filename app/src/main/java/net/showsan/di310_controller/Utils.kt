package net.showsan.di310_controller

import android.util.Log
import kotlin.experimental.xor

/**
 * Created by zoron on 17-11-25.
 */

fun bytesToString(src: ByteArray): String {
    val strBuilder = StringBuilder()
    for (i in 0 until src.size) {
        strBuilder.append(src[i].toChar())
    }
    return strBuilder.toString()
}

fun hexStringToBytes(src: String): ByteArray {
    val len = src.length
    val data = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        data[i / 2] = ((Character.digit(src[i], 16) shl 4) + Character.digit(src[i + 1], 16)).toByte()
        i += 2
    }
    return data
}

fun bytesToHexString(src: ByteArray): String {
    val strBuilder = StringBuilder()
    for (i in 0 until src.size) {
        strBuilder.append(byteToHex(src[i]))
        strBuilder.append(" ")
    }
    return strBuilder.toString()
}

fun byteToHex(src: Byte): String {
    return String.format("%02x", src).toUpperCase()
}

fun hexToAscii(value: String): String {
    val output = StringBuilder()
    var i = 0
    while (i < value.length) {
        val str = value.substring(i, i + 2)
        output.append(Integer.parseInt(str, 16).toChar())
        i += 2
    }
    return output.toString()
}

fun getXorCheckSum(src: ByteArray): Byte {
    var checksum = src[0]
    for (i in 1 until src.size) {
        checksum = checksum xor src[i]
    }
    return checksum
}

fun getXorCheckSum(src: String): String {
    var checksum = src.slice(0..1).toInt(16)
    for (i in 2 until src.length step 2) {
        checksum = checksum xor src.slice(i..i + 1).toInt(16)
    }
    return checksum.toString(16)
}

fun getCheckSum(src: String): String {
    var total = 0
    for (i in 0 until src.length step 2) {
        total += src.slice(i..i + 1).toInt(16)
    }
    var hex = (total % 256).toString(16)
    if (hex.length < 2) {
        hex = "0" + hex
    }
    return hex
}