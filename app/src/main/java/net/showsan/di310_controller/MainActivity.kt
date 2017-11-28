package net.showsan.di310_controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ToggleButton
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import java.io.IOException
import java.security.InvalidParameterException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val ui = MainActivityUI()
    private val com = Com()
    private val dispQueue = DispQueueThread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        dispQueue.start()
    }

    fun initView() {
        ui.setContentView(this)
        ui.bindView(this)
    }

    fun clear() {
        ui.recDataTxt?.text = ""
    }

    fun send(name: String, send: String) {
        if (!com.isOpen()) return toast("Port has not opened")
        val dateFormat = SimpleDateFormat("hh:mm:ss")
        val time = dateFormat.format(java.util.Date())
        setRecDataTxt(time, com.getPort(), name)
        sendPortData(com, send)
    }

    fun send(text: String) {
        if (text.length == 0) return;
        send(text, text);
    }

    fun send(command: Pair<String, String>) {
        send(command.first, command.second)
    }

    fun sendWithParams(command: Pair<String, String>, params: String) {
        if (params.length == 0) return toast("Need params")

        var temp = command.second.replace("*", params)

        var length = (2 + params.length + 2).toString(16).toUpperCase() // Command Code length + Command Data length + Checksum length
        while (length.length < 4) {
            length = "0" + length
        }
        temp = temp.replace("?", length)

        val code = command.second.split("?")[1].split("*")[0]
        val checksum = getXorCheckSum((code + params)).toUpperCase()

        val text = temp + checksum
        //send(command.first, text)
        send(text)
    }

    fun toggleCom(port: String, baudrate: String, toggleButton: ToggleButton?) {
        if (!com.isOpen()) {
            com.setPort(port)
            com.setBaudrate(baudrate.toInt())
            openCom(com)
            toggleButton?.isChecked = true
        } else {
            closeCom(com)
            toggleButton?.isChecked = false
        }
    }

    fun setBaudrate(baudrate: String) {
        com.setBaudrate(baudrate.toInt())
    }

    fun openCom(com: Serialer) {
        try {
            com.open()
            if (com.isOpen())
                toast("Port ${com.getPort()} Opened. Baudrate ${com.getBaudrate()}")
        } catch (e: SecurityException) {
            toast("Open failed. No Permission")
        } catch (e: IOException) {
            toast("Open failed. Unkown Errors")
        } catch (e: InvalidParameterException) {
            toast("Open failed. Invalid Parameters")
        }

    }

    fun sendPortData(com: Serialer, sOut: String) {
        if (com.isOpen()) {
            com.sendTxt(sOut)
        }
    }

    fun closeCom(com: Serialer) {
        com.close()
        if (!com.isOpen()) toast("Port ${com.getPort()} Closed")
    }

    fun dispRecData(data: ComData) {
        setRecDataTxt(data.time, data.port, bytesToString(data.data))
    }

    fun setRecDataTxt(time: String, port: String, data: String) {
        val sMsg = StringBuilder()
        sMsg.append(time);
        sMsg.append(" [");
        sMsg.append(port);
        sMsg.append("] ");
        sMsg.append(data)
        sMsg.append("\r\n")
        ui.recDataTxt?.append(sMsg)
    }

    private inner class Com : Serialer() {
        override fun onDataReceived(data: ComData) {
            dispQueue.AddQueue(data)
        }
    }

    private inner class DispQueueThread : Thread() {
        private val queueList = LinkedList<ComData>()
        override fun run() {
            super.run()
            while (!isInterrupted) {
                val data = queueList.poll()
                while (data != null) {
                    runOnUiThread { dispRecData(data) }
                    Thread.sleep(100)
                    break
                }
            }
        }

        @Synchronized
        fun AddQueue(data: ComData) {
            queueList.add(data)
        }
    }
}
