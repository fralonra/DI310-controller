package net.showsan.di310_controller

import android.graphics.Color
import android.serialport.SerialPortFinder
import android.text.Layout
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import org.jetbrains.anko.*

/**
 * Created by zoron on 17-11-25.
 */
class MainActivityUI : AnkoComponent<MainActivity> {
    var recDataTxt: TextView? = null
    var comSpinner: Spinner? = null
    var comBaudrateSpinner: Spinner? = null
    var comToggleButton: ToggleButton? = null

    var layoutA: View? = null
    var layoutB: View? = null
    var layoutC: View? = null
    var layoutD: View? = null

    override fun createView(ui: AnkoContext<MainActivity>) = ui.apply {
        linearLayout {
            scrollView {
                backgroundColor = Color.LTGRAY
                recDataTxt = textView {
                    textSize = 18f
                }
            }.lparams(0, matchParent, 1.toFloat())
            scrollView {
                verticalLayout {
                    layoutA = verticalLayout {
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
                            val sendText = editText("D&I")
                                    .lparams(0, wrapContent, 1.toFloat())
                            button("发送") {
                                setOnClickListener { ui.owner.send(sendText.text.toString()) }
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
                            setOnClickListener { toggleVisibility(layoutB!!) }
                        }
                        layoutB = verticalLayout {
                            linearLayout {
                                button(commandsMap[Commands.CPU_SEEK]?.first) {
                                    setOnClickListener { ui.owner.send(commandsMap[Commands.CPU_SEEK]!!) }
                                }
                                button(commandsMap[Commands.CPU_RESET]?.first) {
                                    setOnClickListener { ui.owner.send(commandsMap[Commands.CPU_RESET]!!) }
                                }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.CPU_APDU]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.CPU_APDU]!!,
                                                                                 params.text.toString()) }
                                }
                            }
                        }
                    }

                    verticalLayout {
                        textView("M1 卡") {
                            textSize = 18f
                            setOnClickListener { toggleVisibility(layoutC!!) }
                        }
                        layoutC = verticalLayout {
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
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.M1_KEY_LOAD]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.M1_KEY_LOAD]!!,
                                            params.text.toString()) }
                                }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.M1_KEY_VALIDATE]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.M1_KEY_VALIDATE]!!,
                                            params.text.toString()) }
                                }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.M1_RBLOCK]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.M1_RBLOCK]!!,
                                            params.text.toString()) }
                                }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.M1_WBLOCK]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.M1_WBLOCK]!!,
                                            params.text.toString()) }
                                }
                            }
                            button(commandsMap[Commands.M1_RF_STOP]?.first) {
                                setOnClickListener { ui.owner.send(commandsMap[Commands.M1_RF_STOP]!!) }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.M1_WALLET_INIT]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.M1_WALLET_INIT]!!,
                                                    params.text.toString()) }
                                }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.M1_WALLET_CHARGE]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.M1_WALLET_CHARGE]!!,
                                            params.text.toString()) }
                                }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.M1_WALLET_PAY]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.M1_WALLET_PAY]!!,
                                            params.text.toString()) }
                                }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.M1_WALLET_READ]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.M1_WALLET_READ]!!,
                                            params.text.toString()) }
                                }
                            }
                        }
                    }

                    verticalLayout {
                        textView("标准接触式 CPU 卡") {
                            textSize = 18f
                            setOnClickListener { toggleVisibility(layoutD!!) }
                        }
                        layoutD = verticalLayout {
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.PSAM_RESET_COLD]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.PSAM_RESET_COLD]!!,
                                            params.text.toString()) }
                                }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.PSAM_RESET_HOT]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.PSAM_RESET_HOT]!!,
                                            params.text.toString()) }
                                }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.PSAM_CLOSE]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.PSAM_CLOSE]!!,
                                            params.text.toString()) }
                                }
                            }
                            linearLayout {
                                val params = editText()
                                        .lparams(0, wrapContent, 1.toFloat())
                                button(commandsMap[Commands.PSAM_APDU]?.first) {
                                    setOnClickListener { ui.owner.sendWithParams(commandsMap[Commands.PSAM_APDU]!!,
                                            params.text.toString()) }
                                }
                            }
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

    fun toggleVisibility(view: View) {
        if (view.visibility == VISIBLE) {
            view.visibility = GONE
        } else {
            view.visibility = VISIBLE
        }
    }
}