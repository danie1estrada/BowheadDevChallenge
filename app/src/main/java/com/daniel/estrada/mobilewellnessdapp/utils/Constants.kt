package com.daniel.estrada.mobilewellnessdapp.utils

const val LOCAL_ENV = true

val CONTRACT_ADDRESS = if (LOCAL_ENV)
    "0x322d597968ecece7a1cadf1d04a89c46bc352cec"
    else "0xc7c44c64DBddA29e2723FD1056ab876A54ef24CE"

const val API_KEY = "476a376d853046488c7fee48ae8b6afd"

val BASE_URL = if (LOCAL_ENV)
    "http://192.168.1.2:7545"
else "https://rinkeby.infura.io/v3/$API_KEY"

// SHOULD NOT BE HERE
val MASTER_ACCOUNT_PK = if (LOCAL_ENV)
    "fe745504120f8fcdffcd70bf480e620ad04aabfe93ae7bb0168dc8e822fe7c88"
    else "0xdbad477131ba643cd54cb0e3329e6f5b2d2cdb42a1389fe9e93f94d5b064f319"
