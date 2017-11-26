package net.showsan.di310_controller

import android.graphics.Color
import android.serialport.SerialPortFinder
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.ToggleButton
import org.jetbrains.anko.*

/**
 * Created by zoron on 17-11-25.
 */
class MainActivityUI : AnkoComponent<MainActivity> {
    var recDataTxt: TextView? = null
    var comSpinner: Spinner? = null
    var comBaudrateSpinner: Spinner? = null
    var comToggleButton: ToggleButton? = null

    override fun createView(ui: AnkoContext<MainActivity>) = ui.apply {
        linearLayout {
            recDataTxt = textView {
                textSize = 18f
                backgroundColor = Color.LTGRAY
            }.lparams(0, matchParent, 1.toFloat())
            verticalLayout {
                verticalLayout {
                    linearLayout {
                        button("Clear") {
                            setOnClickListener { ui.owner.clear() }
                        }
                        comSpinner = spinner()
                        comBaudrateSpinner = spinner()
                        comToggleButton = toggleButton {
                            textOn = "Close"
                            textOff = "Open"
                            setOnClickListener {
                                ui.owner.toggleCom(comSpinner?.selectedItem.toString(),
                                        comBaudrateSpinner?.selectedItem.toString(),
                                        comToggleButton)
                            }
                        }
                    }
                    linearLayout {
                        button(commandsMap[Commands.VERSION]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.VERSION]!!) }
                        }
                        button(commandsMap[Commands.BEE]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.BEE]!!) }
                        }
                    }
                    linearLayout {
                        button(commandsMap[Commands.RF_RESET]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.RF_RESET]!!) }
                        }
                        button(commandsMap[Commands.RF_CLOSE]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.RF_CLOSE]!!) }
                        }
                    }
                }

                verticalLayout {
                    textView("非接触 CPU 卡") {
                        textSize = 18f
                    }
                    linearLayout {
                        button(commandsMap[Commands.CPU_SEEK]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.CPU_SEEK]!!) }
                        }
                        button(commandsMap[Commands.CPU_RESET]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.CPU_RESET]!!) }
                        }
                    }
                    button(commandsMap[Commands.CPU_APDU]?.first) {
                        setOnClickListener { ui.owner.send(commandsMap[Commands.CPU_APDU]!!) }
                    }
                }

                verticalLayout {
                    textView("M1 卡") {
                        textSize = 18f
                    }
                    linearLayout {
                        button(commandsMap[Commands.M1_SEEK]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_SEEK]!!) }
                        }
                        button(commandsMap[Commands.M1_ANTI_COLLISION]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_ANTI_COLLISION]!!) }
                        }
                        button(commandsMap[Commands.M1_SELECT]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_SELECT]!!) }
                        }
                    }
                    linearLayout {
                        button(commandsMap[Commands.M1_KEY_LOAD]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_KEY_LOAD]!!) }
                        }
                        button(commandsMap[Commands.M1_KEY_VALIDATE]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_KEY_VALIDATE]!!) }
                        }
                    }
                    linearLayout {
                        button(commandsMap[Commands.M1_RBLOCK]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_RBLOCK]!!) }
                        }
                        button(commandsMap[Commands.M1_WBLOCK]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_WBLOCK]!!) }
                        }
                    }
                    button(commandsMap[Commands.M1_RF_STOP]?.first) {
                        setOnClickListener { ui.owner.send(commandsMap[Commands.M1_RF_STOP]!!) }
                    }
                    linearLayout {
                        button(commandsMap[Commands.M1_WALLET_INIT]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_WALLET_INIT]!!) }
                        }
                        button(commandsMap[Commands.M1_WALLET_CHARGE]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_WALLET_CHARGE]!!) }
                        }
                    }
                    linearLayout {
                        button(commandsMap[Commands.M1_WALLET_PAY]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_WALLET_PAY]!!) }
                        }
                        button(commandsMap[Commands.M1_WALLET_READ]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.M1_WALLET_READ]!!) }
                        }
                    }
                }

                verticalLayout {
                    textView("标准接触式 CPU 卡") {
                        textSize = 18f
                    }
                    linearLayout {
                        button(commandsMap[Commands.PSAM_RESET_COLD]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.PSAM_RESET_COLD]!!) }
                        }
                        button(commandsMap[Commands.PSAM_RESET_HOT]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.PSAM_RESET_HOT]!!) }
                        }
                    }
                    linearLayout {
                        button(commandsMap[Commands.PSAM_CLOSE]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.PSAM_CLOSE]!!) }
                        }
                        button(commandsMap[Commands.PSAM_APDU]?.first) {
                            setOnClickListener { ui.owner.send(commandsMap[Commands.PSAM_APDU]!!) }
                        }
                    }
                }
            }
        }
    }.view

    fun bindView(ui: MainActivity) {
        initDevice(ui)
        initBaudrate(ui)
    }

    fun initDevice(ui: MainActivity) {
        val serialPortFinder = SerialPortFinder()
        val entryValues = serialPortFinder.allDevicesPath
        val allDevices = entryValues.indices.map { entryValues[it] }
        val devicesAdapter = ArrayAdapter(ui, android.R.layout.simple_spinner_item, allDevices)
        devicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        if (allDevices.isNotEmpty()) {
            comSpinner?.adapter = devicesAdapter
            comSpinner?.setSelection(6)
        }
    }

    fun initBaudrate(ui: MainActivity) {
        val baudrates = ui.resources.getStringArray(R.array.baudrates_value)
        val baudrateAdapter = ArrayAdapter(ui, android.R.layout.simple_spinner_item, baudrates)
        baudrateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        comBaudrateSpinner?.adapter = baudrateAdapter
        comBaudrateSpinner?.setSelection(16);
    }
}