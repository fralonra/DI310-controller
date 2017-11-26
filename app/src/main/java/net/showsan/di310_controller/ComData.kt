package net.showsan.di310_controller

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by zoron on 17-11-25.
 */
class ComData(val port: String, buffer: ByteArray, size: Int) {
    val data = Arrays.copyOfRange(buffer, 0, size)
    val dateFormat = SimpleDateFormat("hh:mm:ss")
    val time = dateFormat.format(java.util.Date())
}