package net.showsan.di310_controller

/**
 * Created by zoron on 17-11-25.
 */

enum class Commands {
    VERSION,
    BEE,
    RF_RESET,
    RF_CLOSE,
    CPU_SEEK,
    CPU_RESET,
    CPU_APDU,
    M1_SEEK,
    M1_ANTI_COLLISION,
    M1_SELECT,
    M1_KEY_LOAD,
    M1_KEY_VALIDATE,
    M1_RF_STOP,
    M1_RBLOCK,
    M1_WBLOCK,
    M1_WALLET_INIT,
    M1_WALLET_CHARGE,
    M1_WALLET_PAY,
    M1_WALLET_READ,
    PSAM_RESET_COLD,
    PSAM_RESET_HOT,
    PSAM_CLOSE,
    PSAM_APDU
}

val commandsMap = hashMapOf(Commands.VERSION to Pair("取版本号", "D&I00040101"),
                                   Commands.BEE to Pair("蜂鸣器200ms", "D&I000803001417"),// TODO
                                   Commands.RF_RESET to Pair("射频复位", "D&I00041010"),
                                   Commands.RF_CLOSE to Pair("关闭射频", "D&I00042A2A"),
                                   Commands.CPU_SEEK to Pair("非接触 CPU 卡寻卡", "D&I00042020"),
                                   Commands.CPU_RESET to Pair("非接触 CPU 卡上电复位", "D&I00042121"),
                                   Commands.CPU_APDU to Pair("非接触 CPU 数据交互", "D&I?22*"),// TODO
                                   Commands.M1_SEEK to Pair("M1 寻卡", "D&I00041212"),
                                   Commands.M1_ANTI_COLLISION to Pair("防冲撞", "D&I00041313"),
                                   Commands.M1_SELECT to Pair("选择卡", "D&I00041414"),
                                   Commands.M1_KEY_LOAD to Pair("装载密钥", "D&I?15*"),// TODO
                                   Commands.M1_KEY_VALIDATE to Pair("验证密钥", "D&I?16*"),// TODO
                                   Commands.M1_RF_STOP to Pair("终止射频操作", "D&I00041717"),
                                   Commands.M1_RBLOCK to Pair("读块数据", "D&I?18*"),// TODO
                                   Commands.M1_WBLOCK to Pair("写块数据", "D&I?19*"),// TODO
                                   Commands.M1_WALLET_INIT to Pair("钱包初始化", "D&I?1A*"),// TODO
                                   Commands.M1_WALLET_CHARGE to Pair("钱包增值", "D&I?1B*"),// TODO
                                   Commands.M1_WALLET_PAY to Pair("钱包扣值", "D&I?1C*"),// TODO
                                   Commands.M1_WALLET_READ to Pair("读钱包值", "D&I?1D*"),// TODO
                                   Commands.PSAM_RESET_COLD to Pair("冷复位(激活)", "D&I?36*"),// TODO
                                   Commands.PSAM_RESET_HOT to Pair("热复位", "D&I?3A*"),// TODO
                                   Commands.PSAM_CLOSE to Pair("下电", "D&I?39*"),// TODO
                                   Commands.PSAM_APDU to Pair("接触式 CPU 卡数据交互", "D&I?37*"))// TODO